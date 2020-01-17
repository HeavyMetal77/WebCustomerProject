package ua.tarastom.rest;

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

    @GetMapping("/customers/{theId}")
    public Customer getCustomer(@PathVariable int theId) {
        Customer customer = customerService.getCustomer(theId);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer with id=" + theId + " not found!");
        }
        return customer;
    }

    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody Customer theCustomer) {
        theCustomer.setId(0); //устанавливаем 0 для вставки (сохранения) клиента
        customerService.saveOrUpdateCustomer(theCustomer);
        return theCustomer;
    }

    @PutMapping("/customers")
    public Customer updateCustomer(@RequestBody Customer theCustomer) {
       // setId(1) - не устанавливаем для Update клиента
        customerService.saveOrUpdateCustomer(theCustomer);
        return theCustomer;
    }

    @DeleteMapping("/customers/{theCustomerId}")
    public String deleteCustomer(@PathVariable int theCustomerId) {
        Customer customer = customerService.getCustomer(theCustomerId);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer " + theCustomerId + " is not found");
        }
        customerService.deleteCustomer(theCustomerId);
        return "Deleted customer id=" + theCustomerId;
    }
}
