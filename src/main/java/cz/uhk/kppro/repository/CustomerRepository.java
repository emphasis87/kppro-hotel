package cz.uhk.kppro.repository;

import cz.uhk.kppro.model.Customer;
import cz.uhk.kppro.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
