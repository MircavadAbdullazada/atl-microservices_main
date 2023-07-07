package az.atl.demosecurity3.service;


import az.atl.demosecurity3.dao.entity.LoginTable;
import az.atl.demosecurity3.dao.entity.User;
import az.atl.demosecurity3.dao.repository.LoginTableRepository;
import az.atl.demosecurity3.dao.repository.UserRepository;
import az.atl.demosecurity3.model.*;
import az.atl.demosecurity3.scheduled.TestSchedule;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

import static az.atl.demosecurity3.model.RegisterResponse.buildRegisterDto;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final LoginTableRepository loginTableRepository;
    @Autowired
    private TestSchedule testSchedule;



    public RegisterResponse register(RegisterRequest request){
        var isExist=userRepository.findByNickName(request.getNickName()).isPresent();
        if(isExist){
            throw new RuntimeException("Nickname is already exists");
        }
        var user= User.builder()
                .fullName(request.getFullName())
                .nickName(request.getNickName())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .age(request.getAge())
                .role(request.getRole())
                .build();

        var userEntity=userRepository.save(user);

        return buildRegisterDto(userEntity);

    }

    public AuthenticationResponse authenticate(AuthenticationRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getNickName(),
                        request.getPassword()
                )
        );



        var user=userRepository.findByNickName(request.getNickName())
                .orElseThrow(()->new RuntimeException("User Not Found!"));
        var jwtToken=jwtService.generateToken(user);




        LoginTable login = new LoginTable();
        login.setNickName(request.getNickName());
        login.setPassword(request.getPassword());
        login.setLoginDate(new Date());
        loginTableRepository.save(login);

        testSchedule.startGreeting(request.getNickName());



        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();



    }

    public void logout() {
        testSchedule.stopGreeting();
    }

}
