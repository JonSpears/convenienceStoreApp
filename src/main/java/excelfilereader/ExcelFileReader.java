package excelfilereader;

import enums.Gender;
import enums.Role;
import lombok.*;
import model.Category;
import model.Product;
import model.Staff;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.ArrayList;

@ToString
@Getter
@Setter

public class ExcelFileReader {
//    ArrayList<Product> productArrayList = new ArrayList<Product>();
//    ArrayList<Category>categoryArrayList = new ArrayList<Category>();

    public static void main(String[] args) throws IOException {
        Staff staff1 = new Staff(01, "Wilson Wilder", Gender.MALE, "wwilder@gmail.com", Role.MANAGER);
        Staff staff2 = new Staff(02, "Damian Daniel", Gender.MALE, "ddamian@livemail.com", Role.CASHIER);
        System.out.println(staff2);
        System.out.println(staff1);
    }

}















//        String excelFilePath = "src/main/excelfile/week2.xlsx";
//        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(excelFilePath);
//        XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
//        for (int col = 1; col <= xssfSheet.getLastRowNum(); col++) {
//            XSSFRow xssfRow = xssfSheet.getRow(col);
////            Product newProduct = new Product ();
////            newProduct.setProductName(xssfRow.getCell(1).getStringCellValue());
////            newProduct.setProductCategory(xssfRow.getCell(2).getStringCellValue());
////            newProduct.setUnit(xssfRow.getCell(4).getStringCellValue());
////            newProduct.setProductPrice(xssfRow.getCell(5).getNumericCellValue());
////            newProduct.setProductQuantity((int) xssfRow.getCell(6).getNumericCellValue());
////
////            Category newCategory = new Category(
////                    xssfRow.getCell(2).getStringCellValue(),
////                    xssfRow.getCell(1).getStringCellValue(),
////                    xssfRow.getCell(4).getStringCellValue(),
////                    xssfRow.getCell(5).getNumericCellValue()
////            );
//            for (int rowNum = 1; rowNum<= xssfSheet.getLastRowNum(); rowNum++){
//                if ("Bread/Bakery".equals(xssfRow.getCell(2).getStringCellValue())){
//                    System.out.println(xssfRow.getCell(2).getStringCellValue());
//                }
//            }
//
//            //System.out.println(newProduct);
////            System.out.println(newCategory);
//        }

