package comon;

import enums.Role;
import enums.Gender;
import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
public abstract class User {
    private int userID;
    private  String userName;
    private Gender gender;
    private String email;
    private Role role;
}
