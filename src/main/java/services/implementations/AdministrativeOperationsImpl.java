package services.implementations;

import enums.*;
import exceptions.*;
import model.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import services.interfaces.AdministrativeOperations;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

public class AdministrativeOperationsImpl implements AdministrativeOperations {

    private boolean isApplicantQualified(Applicant applicant) {
        return applicant.getQualification().equals(Qualification.HND) ||
                applicant.getQualification().equals(Qualification.BSC) ||
                applicant.getQualification().equals(Qualification.MSC);
    }

    private Staff makeApplicantACashier(Applicant applicant) {
        return new Staff(
                applicant.getUserID(),
                applicant.getUserName(),
                applicant.getGender(),
                applicant.getEmail(),
                Role.CASHIER);
    }

    @Override
    public void hireApplicants(Store store, Staff staff, Applicant applicant) throws ApplicantNotQualifiedException {
        if (!isApplicantQualified(applicant)) throw new ApplicantNotQualifiedException("You do not meet the criteria for the position.");
        store.getStaff().add(makeApplicantACashier(applicant));
    }

    @Override
    public Product[] addProductToStoreFromExcel(Store store, Staff staff) throws StaffNotAuthorizedToPerformOperationException, IOException {
        Product[] listOfProductInStore = store.getListOfProductInStore();
        if (!staff.getRole().equals(Role.MANAGER)) {
            throw new StaffNotAuthorizedToPerformOperationException("You are not authorise to perform this operation");
        } else {
                String path = "src/main/resources/spreadsheet_for_product_list/ProductList.xlsx";

                FileInputStream inputStream = new FileInputStream(path);
                XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
                XSSFSheet xssfSheet = workbook.getSheetAt(0);
                Product[] listOfProductFromFile = new Product[xssfSheet.getLastRowNum()];
                for (int i = 1; i <= xssfSheet.getLastRowNum(); i++) {
                    XSSFRow xssfRow = xssfSheet.getRow(i);
                    listOfProductFromFile[i-1] = new Product(
                            xssfRow.getCell(0).getStringCellValue(),
                            xssfRow.getCell(1).getStringCellValue(),
                            xssfRow.getCell(2).getStringCellValue(),
                            xssfRow.getCell(4).getStringCellValue(),
                            xssfRow.getCell(5).getNumericCellValue(),
                            (int) xssfRow.getCell(6).getNumericCellValue()
                    );

                }
                store.setListOfProductInStore(listOfProductFromFile);
            }
        return listOfProductInStore;
    }

    @Override
    public String sellProductToCustomer(Store store, Staff staff, Customer customer) throws StaffNotAuthorizedToPerformOperationException, CustomerOutOfFundException {
        StringBuilder receiptBody = new StringBuilder();
        if (!staff.getRole().equals(Role.CASHIER)) throw new StaffNotAuthorizedToPerformOperationException("Only Cashiers can sell and dispense receipts to customers!");
        else if (!customer.isCheckOut()) throw new InvalidOperationException("Customer has not checked out!");
            String heading = "\t -----------" + store.getCompanyName() + "-----------"
                    + "\n Product name ---- " + "Price ---- "
                    + "Units ---- " + " Total Price";
            receiptBody.append(heading);
            int snNum = 1;
            for (var product : customer.getCart().entrySet()) {
                int quantityBought = product.getValue();
                Product companyProduct = store.getProduct("Wheat Bread");
                System.out.println(companyProduct);
                reduceCompanyProduct(companyProduct, quantityBought);
                receiptBody.append("\n")
                        .append(snNum)
                        .append(". ")
                        .append(generateReceiptRow(companyProduct, quantityBought));
            }
            customer.setCheckOut(false);
            customer.setWallet(customer.getWallet() - customer.getTotalGoodsPrice());
            store.setCompanyAccountBalance(customer.getTotalGoodsPrice() + store.getCompanyAccountBalance());
            createAFileToSaveData("receipt" + new Date().getTime() + ".txt", receiptBody.toString());
            customer.getCart().clear();

        return receiptBody.toString();
    }

    private void reduceCompanyProduct(Product companyProduct, int quantityBought){
        companyProduct.setProductQuantity(companyProduct.getProductQuantity() - quantityBought);
    }

    private String generateReceiptRow(Product product,  int quantityBought){
        return product.getProductName() + "\t | "
                +  product.getProductPrice() + "\t | "
                + quantityBought + "\t |  "
                + product.getProductPrice() * quantityBought;
    }
}


