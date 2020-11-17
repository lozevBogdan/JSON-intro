package course.springdata.gsonlab.repositories;

import course.springdata.gsonlab.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuppliersRepository extends JpaRepository<Supplier,Long> {


}
