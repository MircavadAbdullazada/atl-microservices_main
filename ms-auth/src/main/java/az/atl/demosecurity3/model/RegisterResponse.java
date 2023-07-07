package az.atl.demosecurity3.model;

import az.atl.demosecurity3.dao.entity.LoginTable;
import az.atl.demosecurity3.dao.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponse {
    String fullName;
    String nickName;
    String password;
    String email;
    Integer age;
    Role role;

    public static RegisterResponse buildRegisterDto(User user) {
        return RegisterResponse.builder()
                .fullName(user.getFullName())
                .nickName(user.getNickName())
                .email(user.getEmail())
                .age(user.getAge())
                .password(user.getPassword())
                .role(user.getRole())
                .build();

    }
}

