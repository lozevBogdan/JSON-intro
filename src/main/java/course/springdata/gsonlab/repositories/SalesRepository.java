package course.springdata.gsonlab.repositories;


import course.springdata.gsonlab.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRepository extends JpaRepository<Sale,Long> {
}
