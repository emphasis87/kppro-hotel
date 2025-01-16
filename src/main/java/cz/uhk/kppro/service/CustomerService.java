package cz.uhk.kppro.service;

import cz.uhk.kppro.model.Customer;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {
    Customer createCustomer(
            String firstName,
            String lastName);
}
