package services.implementations;

import exceptions.CustomerOutOfFundException;
import exceptions.ProductIsOutOfStuckException;
import exceptions.StaffNotAuthorizedToPerformOperationException;
import model.Customer;
import model.Product;
import model.Store;
import services.interfaces.CustomerOperation;

public class CustomerOperationImpl implements CustomerOperation {

    @Override
    public void fundCustomerWallet(Customer customer, double amount) {
        customer.setWallet(amount + customer.getWallet());
    }

    @Override
    public void addProductToCart(Customer customer, Store store, String productName, int quantity) throws ProductIsOutOfStuckException, StaffNotAuthorizedToPerformOperationException {
        for (Product eachProduct : store.getListOfProductInStore()) {
            if (eachProduct.getProductName().equalsIgnoreCase(productName)) {
                if (eachProduct.getProductQuantity() < quantity) throw new ProductIsOutOfStuckException("We are currently low on this. Quantity in stock is " + quantity);
                else if (eachProduct.getProductQuantity() <= 0) throw new ProductIsOutOfStuckException("We are currently out of stock on " + eachProduct.getProductName());
          customer.getCart().put(eachProduct.getProductName(), quantity);
            }
        }
    }

    @Override
    public void purchaseGoodsInCart(Customer customer) throws CustomerOutOfFundException {
        if (customer.getTotalGoodsPrice() > customer.getWallet()) throw new CustomerOutOfFundException("Customer does not have sufficient fund!");
        else customer.setCheckOut(true);
    }
}
