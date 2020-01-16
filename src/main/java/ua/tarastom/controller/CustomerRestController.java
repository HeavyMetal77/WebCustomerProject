package ua.tarastom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.tarastom.entity.Customer;
import ua.tarastom.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        List<Customer> customers = customerService.getCustomers();
        return customers;
    }

    @GetMapping("/customer/{theId}")
    public Customer getCustomer(@PathVariable int theId) {
        Customer customer = customerService.getCustomer(theId);
        return customer;
    }
}
