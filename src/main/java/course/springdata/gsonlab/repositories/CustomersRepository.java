package course.springdata.gsonlab.repositories;

import java.util.List;
import course.springdata.gsonlab.entities.Customer;
import course.springdata.gsonlab.entities.Supplier;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomersRepository extends JpaRepository<Customer,Long> {


    @Query("select c from Customer c order by c.birthDate,c.youngDriver")
    List<Customer> findAllOrderByBirthDateAAndByIsYongDriver();


    @Query("select c from Customer c where c.cars.size > 0")
    List<Customer> findAllWithATLeastOneCar();
}
