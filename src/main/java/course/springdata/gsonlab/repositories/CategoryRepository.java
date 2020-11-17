package course.springdata.gsonlab.repositories;

import course.springdata.gsonlab.entities.Category;
import course.springdata.gsonlab.entities.User;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@EntityScan(basePackages = {"com.mypackage.entity"})
public interface CategoryRepository extends JpaRepository<Category, Long > {

    @Query("select c from Category c order by c.products.size desc ")
    List<Category> findAllOrderByNumberOfProducts();

}
