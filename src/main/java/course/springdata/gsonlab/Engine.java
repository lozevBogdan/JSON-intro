package course.springdata.gsonlab;

import course.springdata.gsonlab.servises.*;
import course.springdata.gsonlab.servises.impl.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Engine implements CommandLineRunner {

    private final UserService userService;
    private final ProductService productService;
    private final CategoryService categoryService;
    private final SupplierService supplierService;
    private final PartsService partsService;
    private final CarsServise carsServise;
    private final CustomerService customerService;
    private final SalesService salesService;

    @Autowired
    public Engine(UserService userService, ProductService productService, CategoryService categoryService, SupplierService supplierService, PartsService partsService, CarsServise carsServise, CustomerService customerService, SalesService salesService) {
        this.userService = userService;
        this.productService = productService;
        this.categoryService = categoryService;
        this.supplierService = supplierService;
        this.partsService = partsService;
        this.carsServise = carsServise;
        this.customerService = customerService;
        this.salesService = salesService;
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


        //5.	Car Dealer Import Data
//        System.out.println(this.supplierService.seedSupplierData());
//        System.out.println(this.partsService.seedPartData());
//        System.out.println(this.carsServise.seedCarsData());
//        System.out.println(this.customerService.seedCustomerDataInDB());
//        System.out.println(this.salesService.seed10SalesDataInDB());

        //6.

        //6.2
        //System.out.println(this.carsServise.carsFromMake("Toyota"));

        //6.3
        System.out.println(this.supplierService.getLocalSuppliers());


    }
}
