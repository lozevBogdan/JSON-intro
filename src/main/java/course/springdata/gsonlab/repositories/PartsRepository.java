package course.springdata.gsonlab.repositories;

import java.util.List;

import course.springdata.gsonlab.entities.Car;
import course.springdata.gsonlab.entities.Part;
import course.springdata.gsonlab.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PartsRepository extends JpaRepository<Part,Long> {


    List<Part> getAllByCarContaining(Car car);
}
