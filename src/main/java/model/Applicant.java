package model;

import enums.Gender;
import enums.Qualification;
import comon.User;
import enums.Role;
import lombok.*;

@ToString
//@AllArgsConstructor
//@NoArgsConstructor
@Getter
@Setter

public class Applicant extends User {
    private Qualification qualification;

    public Applicant(int userID, String userName, Gender gender, String email, Role role, Qualification qualification) {
        super(userID, userName, gender, email, role);
        this.qualification = qualification;
    }

    public Applicant(Qualification qualification) {
        this.qualification = qualification;
    }
}
