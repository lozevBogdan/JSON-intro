package course.springdata.gsonlab.repositories;

import course.springdata.gsonlab.entities.Customer;
import course.springdata.gsonlab.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomersRepository extends JpaRepository<Customer,Long> {


}
