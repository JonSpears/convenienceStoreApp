package services.interfaces;

import exceptions.*;
import model.*;

import java.io.IOException;

public interface AdministrativeOperations extends MiscellaneousOperations{
    void hireApplicants (Store store, Staff staff, Applicant applicant) throws ApplicantNotQualifiedException;
    Product[] addProductToStoreFromExcel(Store store, Staff staff) throws StaffNotAuthorizedToPerformOperationException, IOException;
    String sellProductToCustomer(Store store, Staff staff, Customer customer) throws ProductIsOutOfStuckException,
            StaffNotAuthorizedToPerformOperationException, CustomerOutOfFundException;
}
