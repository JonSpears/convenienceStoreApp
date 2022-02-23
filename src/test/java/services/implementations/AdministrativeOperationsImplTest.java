package services.implementations;

import enums.Gender;
import enums.Qualification;
import enums.Role;
import exceptions.ApplicantNotQualifiedException;
import exceptions.InvalidOperationException;
import exceptions.ProductIsOutOfStuckException;
import exceptions.StaffNotAuthorizedToPerformOperationException;
import model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.interfaces.AdministrativeOperations;
import services.interfaces.CustomerOperation;

import java.io.IOException;

import static org.junit.Assert.*;

public class AdministrativeOperationsImplTest {
    AdministrativeOperationsImpl administrativeOperations;
    CustomerOperation customerOperation;
    Store company = new Store();
    String excelFilePath;
    Product groceries;
    Staff manager;
    Staff cashier;
    Customer customer;
    Applicant applicant1;
    Applicant applicant2;
    Applicant applicant3;


    @BeforeEach
    void setUp() {
        administrativeOperations = new AdministrativeOperationsImpl();
        customerOperation = new CustomerOperationImpl();
        company = new Store("JSpears Groceries", "Lekki Lagos", 0.0, company.getListOfProductInStore());
        groceries = new Product();
        manager = new Staff(1, "Jonathan Spears", Gender.MALE, "jspears@gmail.com", Role.MANAGER);
        cashier = new Staff(2, "Gabriella Johnsons", Gender.FEMALE, "gabby@gmail.com", Role.CASHIER);
        customer = new Customer("Van Grey", Gender.MALE);
        excelFilePath = "src/main/resources/spreadsheet_for_product_list/ProductList.xlsx";
        applicant1 = new Applicant(101, "Kunle Damian", Gender.MALE, "kdamina@gmail.com", Role.APPLICANT, Qualification.HND);
        applicant2 = new Applicant(102, "Johnbull Mandy", Gender.FEMALE, "mandy@gmail.com", Role.APPLICANT, Qualification.ND);
        applicant3 = new Applicant(103, "Chidi Mike", Gender.MALE, "chimike@gmail.com", Role.APPLICANT, Qualification.MSC);
    }

    @Test
    public void shouldUpdateNumbersOfHiredApplicant() throws ApplicantNotQualifiedException {
        administrativeOperations.hireApplicants(company, manager, applicant1);
        administrativeOperations.hireApplicants(company, manager, applicant3);
        assertEquals(2, company.getStaff().size());
    }

    @Test
    public void shouldThrowExceptionWhenApplicantDoesNotMeetRequirement() {
        assertThrows(ApplicantNotQualifiedException.class, ()->administrativeOperations.hireApplicants(company, manager, applicant2));
    }

    @Test
    public void shouldThrowStaffNotAuthorizedToPerformOperationException() {
        assertThrows(StaffNotAuthorizedToPerformOperationException.class,
        ()-> administrativeOperations.addProductToStoreFromExcel(company, cashier));
    }

    @Test
    public void staffNotAuthorizedExceptionShouldReturnExactExceptionMessageWhenThrown() {
        Exception exception = assertThrows(StaffNotAuthorizedToPerformOperationException.class,
                () -> administrativeOperations.addProductToStoreFromExcel(company, cashier));
        Assertions.assertEquals("You are not authorise to perform this operation", exception.getMessage());
    }

    @Test
    public void productListAfterAddingFromExcelShouldBeGreaterThanProductListBeforeAdding() throws IOException, StaffNotAuthorizedToPerformOperationException {
        int initialSizeOfProductList = company.getListOfProductInStore().length;
        AdministrativeOperations operations = new AdministrativeOperationsImpl();
        operations.addProductToStoreFromExcel(company, manager);
        Assertions.assertTrue(company.getListOfProductInStore().length > initialSizeOfProductList);
    }

    @Test
    public void companyProductShouldReduceAfterPurchase() throws InvalidOperationException, IOException, StaffNotAuthorizedToPerformOperationException, ProductIsOutOfStuckException {
        administrativeOperations.addProductToStoreFromExcel(company, manager);
        Assertions.assertEquals(25, company.getProductsInStoreSize());
        customerOperation.fundCustomerWallet(customer, 200_000);
        customerOperation.addProductToCart(customer, company, "Wheat Bread", 5);
        customerOperation.purchaseGoodsInCart(customer);
        administrativeOperations.sellProductToCustomer(company, cashier, customer);
        Assertions.assertEquals(45, company.getProduct("Wheat Bread").getProductQuantity());
    }

    @Test
    public void sizeOfTheProductListShouldBeUpdatedAfterAddingFromExcel() throws IOException, StaffNotAuthorizedToPerformOperationException {
        administrativeOperations.addProductToStoreFromExcel(company, manager);
        Assertions.assertEquals(25, company.getListOfProductInStore().length);
    }

    @Test
    public void testToEnsureThatOnlyCashierIsAuthorizedToSellProducts() throws IOException, StaffNotAuthorizedToPerformOperationException, ProductIsOutOfStuckException {
        administrativeOperations.addProductToStoreFromExcel(company, manager);
        customerOperation.addProductToCart(customer, company, "Totilas", 4);
        assertThrows(StaffNotAuthorizedToPerformOperationException.class, () -> administrativeOperations.sellProductToCustomer(company, manager, customer));
    }

    @Test
    public void cashierCanViewProductByCategory() throws IOException, StaffNotAuthorizedToPerformOperationException {
        administrativeOperations.addProductToStoreFromExcel(company, manager);
        Assertions.assertEquals("Beverages", administrativeOperations.viewProductByCategory(company, "Beverages").get(0).getProductCategory());
        Assertions.assertEquals("Bread/Bakery", administrativeOperations.viewProductByCategory(company, "Bread/Bakery").get(1).getProductCategory());
    }



}
