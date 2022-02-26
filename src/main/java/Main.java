import enums.Gender;
import enums.Qualification;
import enums.Role;
import exceptions.ApplicantAlreadyAppliedException;
import exceptions.ApplicantNotQualifiedException;
import exceptions.ProductIsOutOfStuckException;
import exceptions.StaffNotAuthorizedToPerformOperationException;
import model.*;
import services.implementations.AdministrativeOperationsImpl;
import services.implementations.ApplicationImpl;
import services.implementations.CustomerOperationImpl;
import services.interfaces.AdministrativeOperations;
import services.interfaces.CustomerOperation;

import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws ApplicantNotQualifiedException, ApplicantAlreadyAppliedException, IOException, StaffNotAuthorizedToPerformOperationException, ProductIsOutOfStuckException {

        AdministrativeOperationsImpl administrativeOperations = new AdministrativeOperationsImpl();
        CustomerOperation customerOperation = new CustomerOperationImpl();
        ApplicationImpl applicationImpl = new ApplicationImpl();

        Store company = new Store();

        Applicant applicant1;
        Applicant applicant2;
        Applicant applicant3;
        Applicant applicant4;
        String excelFilePath;
        Product groceries;
        Staff manager;
        Staff cashier;
        Customer customer;

        company = new Store("JSpears Groceries", "Lekki Lagos", 20000.0, company.getListOfProductInStore());
        groceries = new Product();
        manager = new Staff(1, "Jonathan Spears", Gender.MALE, "jspears@gmail.com", Role.MANAGER);
        cashier = new Staff(2, "Gabriella Johnsons", Gender.FEMALE, "gabby@gmail.com", Role.CASHIER);
        customer = new Customer("Van Grey", Gender.MALE);
        excelFilePath = "src/main/resources/spreadsheet_for_product_list/ProductList.xlsx";
        applicant1 = new Applicant(101, "Kunle Damian", Gender.MALE, "kdamina@gmail.com", Role.APPLICANT, Qualification.HND);
        applicant2 = new Applicant(102, "Johnbull Mandy", Gender.FEMALE, "mandy@gmail.com", Role.APPLICANT, Qualification.ND);
        applicant3 = new Applicant(103, "Chidi Mike", Gender.MALE, "chimike@gmail.com", Role.APPLICANT, Qualification.MSC);
        applicant4 = new Applicant(004, "Brooks Gideon", Gender.MALE, "brooksg@mail.globe", Role.APPLICANT, Qualification.GSE);

        applicationImpl.apply(applicant1, company);
        applicationImpl.apply(applicant2, company);
        applicationImpl.apply(applicant3, company);
        applicationImpl.apply(applicant4, company);


        administrativeOperations.hireApplicants(company, manager, applicant1);

        int initialSizeOfProductList = company.getListOfProductInStore().length;
        AdministrativeOperations operations = new AdministrativeOperationsImpl();
        operations.addProductToStoreFromExcel(company, manager);


        administrativeOperations.addProductToStoreFromExcel(company, manager);
        customerOperation.fundCustomerWallet(customer, 200_000);
        customerOperation.addProductToCart(customer, company, "Wheat Bread", 5);

        System.out.println("\nCustomer has " +
                "added " + customer.getCart() + " to his/her cart \n");

        customerOperation.purchaseGoodsInCart(customer);
        administrativeOperations.sellProductToCustomer(company, cashier, customer);




        System.out.println(company.getApplicants());
        System.out.println(company.getStaff());

        System.out.println(initialSizeOfProductList);
        System.out.println(company.getListOfProductInStore().length);

    }
}
