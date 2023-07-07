package az.atl.demosecurity3.dao.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "loginTable")
public class LoginTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickName;
    private String password;
    private Date loginDate;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}