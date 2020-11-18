package course.springdata.gsonlab.repositories;

import java.util.List;
import course.springdata.gsonlab.entities.Car;
import course.springdata.gsonlab.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.w3c.dom.stylesheets.LinkStyle;

public interface CarsRepository extends JpaRepository<Car,Long> {


    @Query("select c from Car c where c.make = :make " +
            "order by c.model,c.travelledDistance desc ")
    List<Car> findAllByMakeOrderByModelAndTravelledDistance(String make);


    @Query("select c from Car c")
    List<Car> getAllCars();
}
