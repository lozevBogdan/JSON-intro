package course.springdata.gsonlab.servises;

import course.springdata.gsonlab.entities.Product;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    String seedProducts() throws IOException;


    String productsInRange(BigDecimal min,BigDecimal max) throws IOException;

    List<Product> findByPriceBetweenAndBuyerIsNullOrderByPrice
            (BigDecimal min,BigDecimal max);



}
