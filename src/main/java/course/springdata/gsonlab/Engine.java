package course.springdata.gsonlab;

import course.springdata.gsonlab.servises.CategoryService;
import course.springdata.gsonlab.servises.ProductService;
import course.springdata.gsonlab.servises.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Engine implements CommandLineRunner {

    private final UserService userService;
    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public Engine(UserService userService, ProductService productService, CategoryService categoryService) {
        this.userService = userService;
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {



        //2.	Seed the Database
//        System.out.println(this.userService.seedData());
//        System.out.println(this.categoryService.seedCategories());
//        System.out.println(this.productService.seedProducts());

        //3.1 Products in Range
//        System.out.println(this.productService.productsInRange(
//                BigDecimal.valueOf(500),BigDecimal.valueOf(1000)
//        ));

        //3.2

      //  System.out.println(this.userService.userSoldProducts());

        //3.3
       // System.out.println(this.categoryService.categoriesByProductsCountToGson());

        //4
        //System.out.println(this.userService.getInfoForUsersAndSoldProducts());

    }
}
