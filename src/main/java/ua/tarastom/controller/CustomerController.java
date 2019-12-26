package ua.tarastom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.tarastom.entity.Customer;
import ua.tarastom.service.CustomerService;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    // need to inject the customerService
    @Autowired
    private CustomerService customerService;

    @GetMapping("/list")
    public String listCustomers(Model theModel) {
        // get customers from the customerService
        List<Customer> theCustomers = customerService.getCustomers();
        // add the customers to the model
        theModel.addAttribute("customers", theCustomers);
        return "list-customers";
    }

    @GetMapping("/showFormForAdd")
    public String showFormAddCustomer(Model theModel) {
        Customer theCustomer = new Customer();
        theModel.addAttribute("customer", theCustomer);
        return "customer-form";
    }
    @PostMapping("/saveCustomer")
    public String saveOrUpdateCustomer(@ModelAttribute("customer") Customer theCustomer) {
        customerService.saveOrUpdateCustomer(theCustomer);
        return "redirect:/customer/list";
    }
    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel) {
        Customer theСustomer = customerService.getCustomer(theId);
        theModel.addAttribute("customer", theСustomer);
        return "customer-form";
    }
}
