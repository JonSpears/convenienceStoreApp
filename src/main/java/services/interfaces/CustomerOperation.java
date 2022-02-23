package services.interfaces;

import exceptions.CustomerOutOfFundException;
import exceptions.ProductIsOutOfStuckException;
import exceptions.StaffNotAuthorizedToPerformOperationException;
import model.Customer;
import model.Store;


public interface CustomerOperation extends MiscellaneousOperations {
    void fundCustomerWallet(Customer customer, double amount);
    void addProductToCart(Customer customer, Store store, String productName, int quantity) throws ProductIsOutOfStuckException, StaffNotAuthorizedToPerformOperationException;
    void purchaseGoodsInCart(Customer customer) throws CustomerOutOfFundException;
}
