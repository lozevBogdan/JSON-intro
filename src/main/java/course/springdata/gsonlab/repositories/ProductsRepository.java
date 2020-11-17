package course.springdata.gsonlab.repositories;


import course.springdata.gsonlab.entities.Product;
import course.springdata.gsonlab.entities.User;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
@EntityScan(basePackages = {"com.mypackage.entity"})
public interface ProductsRepository extends JpaRepository<Product, Long > {

List<Product> findByPriceBetweenAndBuyerIsNullOrderByPrice(BigDecimal min, BigDecimal max);

}
