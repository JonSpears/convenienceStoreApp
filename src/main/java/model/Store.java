package model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Store {
    private String companyName;
    private String companyAddress;
    private double companyAccountBalance = 0.0;
    private final List<Applicant>applicants = new ArrayList<>();
    private final List<Staff> staff = new ArrayList<>();
    private Product[] listOfProductInStore = new Product[1];

    private Product[] listOfProductInStore(String productName) {
        for (Product product : listOfProductInStore) {
            if (product.getProductID().equals(productName)) return listOfProductInStore;
            }
            return null;
        }
    public boolean containsProduct(String productName){
        for(Product good : listOfProductInStore)
            if(good.getProductName().equals(productName)) return true;
        return false;
    }

    public Product getProduct(String productName){
        for (Product product : listOfProductInStore){
            if(product.getProductName().equals(productName)) return product;
            }
        return null;
    }

    public Product getProduct(int index){
        return listOfProductInStore[index];
    }

    public int getProductsInStoreSize(){
        return listOfProductInStore.length;
    }

}
