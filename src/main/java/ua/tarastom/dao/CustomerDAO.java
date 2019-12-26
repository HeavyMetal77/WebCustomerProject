package ua.tarastom.dao;

import ua.tarastom.entity.Customer;

import java.util.List;

public interface CustomerDAO {
    List<Customer> getCustomers();

    void saveOrUpdateCustomer(Customer theCustomer);

    Customer getCustomer(int theId);
}
