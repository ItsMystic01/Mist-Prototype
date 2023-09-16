package com.mist.corps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/api/v1/customers")
public class Main {

    private final CustomerRepository CUSTOMER_REPOSITORY;

    public Main(CustomerRepository customerRepository) {
        this.CUSTOMER_REPOSITORY = customerRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @GetMapping
    public List<Customer> getCustomers() {
        return CUSTOMER_REPOSITORY.findAll();
    }

    @PostMapping
    public void addCustomer(@RequestBody NewCustomerRequest request) {
        Customer customer = new Customer();
        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setAge(request.age());

        CUSTOMER_REPOSITORY.save(customer);
    }

    @DeleteMapping("{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Integer id) {
        CUSTOMER_REPOSITORY.deleteById(id);
    }

    @PutMapping("{customerId}")
    public void updateCustomer(@PathVariable("customerId") Integer id, @RequestBody NewCustomerRequest request) {
        Customer customer = CUSTOMER_REPOSITORY.getReferenceById(id);
        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setAge(request.age());

        CUSTOMER_REPOSITORY.save(customer);
    }
}


