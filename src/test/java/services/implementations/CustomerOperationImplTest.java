package services.implementations;

import enums.Gender;
import enums.Role;
import exceptions.CustomerOutOfFundException;
import exceptions.ProductIsOutOfStuckException;
import exceptions.StaffNotAuthorizedToPerformOperationException;
import model.*;
import org.junit.jupiter.api.Assertions;
import services.interfaces.AdministrativeOperations;
import services.interfaces.CustomerOperation;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;


class CustomerOperationImplTest {
    AdministrativeOperations administrativeOperations;
    CustomerOperation customerOperation;
    Store company = new Store();
    String excelFilePath;
    Product groceries;
    Staff manager;
    Staff cashier;
    Customer customer;



    @BeforeEach
    void setUp(){
        administrativeOperations = new AdministrativeOperationsImpl();
        customerOperation = new CustomerOperationImpl();
        company = new Store("JSpears Groceries", "Lekki Lagos", 0.0, company.getListOfProductInStore());
        groceries = new Product();
        manager = new Staff(1, "Jonathan Spears",Gender.MALE, "jspears@gmail.com",Role.MANAGER);
        cashier = new Staff(2, "Gabriella Johnsons", Gender.FEMALE, "gabby@gmail.com", Role.CASHIER);
        customer = new Customer("Van Grey", Gender.MALE);
    }

    @Test
    void fundCustomerWallet() {
        customerOperation.fundCustomerWallet(customer, 200_000);
        assertEquals(200_000, customer.getWallet());
    }

    @Test
    void addProductToCart() throws IOException, StaffNotAuthorizedToPerformOperationException, ProductIsOutOfStuckException {
        administrativeOperations.addProductToStoreFromExcel(company, manager);
        customerOperation.fundCustomerWallet(customer, 200_000);
        customerOperation.addProductToCart(customer, company, "Wheat Bread", 5);
        assertEquals(5, customer.getCart().get("Wheat Bread"));
    }

    @Test
    public void customerCanViewProductByCategory() throws IOException, StaffNotAuthorizedToPerformOperationException {
        administrativeOperations.addProductToStoreFromExcel(company, manager);
        Assertions.assertEquals("Beverages", administrativeOperations.viewProductByCategory(company, "Beverages").get(0).getProductCategory());
        Assertions.assertEquals("Bread/Bakery", administrativeOperations.viewProductByCategory(company, "Bread/Bakery").get(1).getProductCategory());
    }

    @Test
    public void shouldThrowAnExceptionIfCustomerOrderIsMoreThanProductInStock() throws IOException, StaffNotAuthorizedToPerformOperationException {
        administrativeOperations.addProductToStoreFromExcel(company, manager);
        assertThrows(ProductIsOutOfStuckException.class,
                ()-> customerOperation.addProductToCart(customer, company, "Wheat Bread", 60));
    }
}