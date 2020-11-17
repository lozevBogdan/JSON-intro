package course.springdata.gsonlab.repositories;

import course.springdata.gsonlab.entities.Car;
import course.springdata.gsonlab.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarsRepository extends JpaRepository<Car,Long> {


}
