package course.springdata.gsonlab.repositories;

import course.springdata.gsonlab.entities.Part;
import course.springdata.gsonlab.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartsRepository extends JpaRepository<Part,Long> {


}
