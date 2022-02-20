package model;

import enums.Qualification;
import comon.User;
import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Applicant extends User {
    private Qualification qualification;
}
