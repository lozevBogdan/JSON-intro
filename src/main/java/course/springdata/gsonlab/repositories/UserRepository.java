package course.springdata.gsonlab.repositories;

import java.util.List;
import course.springdata.gsonlab.entities.User;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@EntityScan(basePackages = {"com.mypackage.entity"})
public interface UserRepository extends JpaRepository<User, Long > {

    @Query("select u from User u where u.soldProducts.size > 0 " +
            "order by u.lastName,u.firstName")
        List<User> findAllBySoldProductsAtLeastOneOrderByLastNameFirstName();

    @Query("select u from User u where u.soldProducts.size > 0 " +
            "order by u.soldProducts.size desc ,u.lastName")
    List<User> findAllBySoldProductsAtLeastOneOrderBySoldProductsThenByLastName();
}
