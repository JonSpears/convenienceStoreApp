package model;

import lombok.*;
import org.apache.poi.xssf.usermodel.XSSFCell;

import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@Setter

public class Category {
    private String categoryName;
    private String productNameByCategory;
    private String unitOfProductInCategory;
    private double pricePerUnitOfProduct;

    public Category(String categoryName, String productNameByCategory, String unitOfProductInCategory, double pricePerUnitOfProduct) {
        this.categoryName = categoryName;
        this.productNameByCategory = productNameByCategory;
        this.unitOfProductInCategory = unitOfProductInCategory;
        this.pricePerUnitOfProduct = pricePerUnitOfProduct;
    }
}
