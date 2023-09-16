package com.mist.corps.CustomerController;

import com.mist.corps.Customer;
import com.mist.corps.CustomerRepository;
import com.mist.corps.NewCustomerRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class ControlRoom {
    private final CustomerRepository CUSTOMER_REPOSITORY;

    public ControlRoom(CustomerRepository customerRepository) {
        CUSTOMER_REPOSITORY = customerRepository;
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
