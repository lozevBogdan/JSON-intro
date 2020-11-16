package course.springdata.gsonlab.repositories;

import course.springdata.gsonlab.entities.Category;
import course.springdata.gsonlab.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long > {

}
