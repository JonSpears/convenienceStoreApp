package model;

import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Product {
    private String productName;
    private  String productCategory;
    private String unit;
    private double productPrice;
    private int productQuantity;
}
