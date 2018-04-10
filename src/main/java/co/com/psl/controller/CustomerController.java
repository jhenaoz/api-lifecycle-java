package co.com.psl.controller;


import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;

import co.com.psl.domain.Customer;
import co.com.psl.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.common.collect.Lists;


@RestController
public class CustomerController {
    @Autowired
    CustomerRepository customerRepository;

    @RequestMapping(value="/customers")
    public ResponseEntity<ArrayList<Customer>> getCustomers() {
        ArrayList <Customer> customers = Lists.newArrayList(customerRepository.findAll());
        for(Customer customer: customers){
            customer.add(linkTo(methodOn(CustomerController.class).getCustomerById(customer.getcustomerId())).withRel("customer"));
        }

        return new ResponseEntity<ArrayList<Customer>>(customers, HttpStatus.OK);
    }

    @RequestMapping(value="/customers/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Long id) {
        Customer customer = customerRepository.findById(id).get();
        customer.add(linkTo(methodOn(CustomerController.class).getCustomerById(customer.getcustomerId())).withSelfRel());

        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }
}
