package az.atl.demosecurity3.scheduled;


import az.atl.demosecurity3.dao.repository.LoginTableRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.*;

@Component
@Slf4j
@RequiredArgsConstructor
public class TestSchedule {

    @Autowired
    private LoginTableRepository loginTableRepository;
    private ScheduledFuture<?> scheduledFuture;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    @Autowired
    public void setTaskScheduler(TaskScheduler taskScheduler) {
      this.scheduledFuture = taskScheduler.scheduleAtFixedRate(this::deleteLoginTableData, 30000);
    }

    public void deleteLoginTableData() {
        if (loginTableRepository.count() > 0) {
            loginTableRepository.deleteAll();
            System.out.println("DATA WAS DELETED");
        } else {
            System.out.println("LOGIN TABLE IS EMPTY");
            scheduledFuture.cancel(false);
        }
    }

    public void startGreeting(String username) {
        Runnable task = () -> System.out.println("Hello, " + username + "!");
        scheduledFuture = scheduler.scheduleAtFixedRate(task, 1, 1, TimeUnit.SECONDS);
    }

    public void stopGreeting() {
        if (scheduledFuture != null ) {
            System.out.println("There is no message");
            scheduledFuture.cancel(false);
        }
    }
}

//    @Scheduled(fixedRate = 1000)
//    @SneakyThrows
//    public void testScheduling() {
//       log.info("Thread name:" + Thread.currentThread().getName() + "Time" + LocalDateTime.now());
//        Thread.sleep(10000);
//   }



