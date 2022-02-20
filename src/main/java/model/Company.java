package model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@ToString
@Getter
@Setter

public class Company {
    private final String companyName;
    private final String companyAddress;
    private double companyAccountBalance = 0;
    private final List<Applicant>applicants = new ArrayList<>();
    private final List<Staff> staff = new ArrayList<>();
    private final List<Product> products = new ArrayList<>();

}
