package az.atl.demosecurity3.model;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterRequest {
    String fullName;
    String nickName;
    String password;
    String email;
    Integer age;
    Role role;


}
