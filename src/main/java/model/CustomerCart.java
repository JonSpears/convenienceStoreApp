package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerCart {
    private Map<Product, Integer> itemsInCart;
    private double totalCostOfProductInCart;
}
