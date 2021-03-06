package ua.tarastom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.tarastom.dao.CustomerDAO;
import ua.tarastom.entity.Customer;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDAO customerDAO;

    @Override
    @Transactional
    public List<Customer> getCustomers() {
        List<Customer> customers = customerDAO.getCustomers();
        return customers;
    }

    @Override
    @Transactional
    public void saveOrUpdateCustomer(Customer theCustomer) {
        customerDAO.saveOrUpdateCustomer(theCustomer);
    }

    @Override
    @Transactional
    public Customer getCustomer(int theId) {
        Customer theCustomer = customerDAO.getCustomer(theId);
        return theCustomer;
    }

    @Override
    @Transactional
    public void deleteCustomer(int theId) {
        customerDAO.deleteCustomer(theId);
    }
}
