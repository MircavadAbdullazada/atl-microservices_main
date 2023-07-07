package az.atl.demosecurity3.model;

import az.atl.demosecurity3.dao.entity.LoginTable;
import az.atl.demosecurity3.dao.entity.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class AuthenticationResponse {
    String token;

}
