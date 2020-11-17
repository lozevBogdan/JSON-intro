package course.springdata.gsonlab.repositories;

import java.util.List;
import course.springdata.gsonlab.entities.Customer;
import course.springdata.gsonlab.entities.Supplier;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomersRepository extends JpaRepository<Customer,Long> {


   // List<Customer> findAllOrderByBirthDate
}
