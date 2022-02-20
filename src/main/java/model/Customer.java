package model;

import comon.User;
import enums.Gender;
import enums.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Customer extends User {
    private double wallet;
    private final List<Product>itemsInCart = new ArrayList<>();


    public Customer(int userID, String userName, Gender gender, String email, Role role, double wallet) {
        super(userID, userName, gender, email, role);
        this.wallet = wallet;
    }
}
