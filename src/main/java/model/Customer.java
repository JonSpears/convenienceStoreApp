package model;

import comon.User;
import enums.Gender;
import enums.Role;
import lombok.*;

import java.util.HashMap;
import java.util.Map;


@Getter
@Setter
public class Customer extends User {
    private double wallet = 0.0;
    private final Map<String, Integer> cart = new HashMap<>();
    private boolean checkOut = false;
    private double totalGoodsPrice = 0.0;


    public Customer(int userID, String userName, Gender gender, String email, Role role, double wallet, boolean checkOut, double totalGoodsPrice) {
        super(userID, userName, gender, email, role);
        this.wallet = wallet;
        this.checkOut = checkOut;
        this.totalGoodsPrice = totalGoodsPrice;
    }

    public Customer(double wallet, boolean checkOut, double totalGoodsPrice) {
        this.wallet = wallet;
        this.checkOut = checkOut;
        this.totalGoodsPrice = totalGoodsPrice;
    }

    public Customer(String name, Gender gender) {
    }
}
