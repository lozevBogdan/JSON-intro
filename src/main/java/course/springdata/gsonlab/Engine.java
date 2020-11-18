package course.springdata.gsonlab;

import course.springdata.gsonlab.servises.*;
import course.springdata.gsonlab.servises.SalesService;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.Buffer;

@Component
public class Engine implements CommandLineRunner {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

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

        BufferedReader reader =new BufferedReader(new InputStreamReader(System.in));

        System.out.println();
        System.out.println(ANSI_PURPLE +
                "-------------------Welcome to my Homework 'Exercises: JSON Processing'!-------------------" + ANSI_RESET );

        System.out.println("Enter which exercise want to check ");
        System.out.println("2, 3, 5, 6 or Stop for Exit:");

        String input = reader.readLine();


        while (!input.equalsIgnoreCase("stop")){
        switch (input){

            case "2" :
        System.out.println( ANSI_GREEN + this.userService.seedData());
        System.out.println(this.categoryService.seedCategories());
                System.out.println("Wait a few seconds to seed and Products..");
        System.out.println(this.productService.seedProducts() + ANSI_RESET);
                System.out.println();
                break;

            case "3" :
                //3.1
                System.out.println("Query 1 – Products in Range:");
                 System.out.println(this.productService
                .productsInRange(BigDecimal.valueOf(500),BigDecimal.valueOf(1000)));
                System.out.println();
                System.out.println("End of Query 1 – Products in Range"+
                        "---------------------------------------------------------");
                System.out.println();
                //3.2
                System.out.println("Query 2 – Successfully Sold Products:");
                 System.out.println(this.userService.userSoldProducts());
                System.out.println();
                System.out.println("End of Query 2 – Successfully Sold Products " +
                        "---------------------------------------------------------");
                System.out.println();
                 //3.3

                System.out.println("Query 3 – Categories by Products Count:");
                System.out.println(this.categoryService.categoriesByProductsCountToGson());
                System.out.println();
                System.out.println("End of Query 3 – Categories by Products Count"+
                        "---------------------------------------------------------");
                System.out.println();
                System.out.println();

                System.out.println("Query 4 – Users and Products:");
                System.out.println(this.userService.getInfoForUsersAndSoldProducts());
                System.out.println();
                System.out.println("End of Query 4 – Users and Products"+
                        "---------------------------------------------------------");
                System.out.println();
                break;


            case "5" :
        System.out.println(this.supplierService.seedSupplierData());
        System.out.println(this.partsService.seedPartData());
        System.out.println(this.carsServise.seedCarsData());
        System.out.println(this.customerService.seedCustomerDataInDB());
        System.out.println(this.salesService.seed10SalesDataInDB());
                System.out.println();
                break;

            case "6" :
                //6.1
                System.out.println("Query 1 – Ordered Customers");
                System.out.println(this.customerService.orderCustomersByBirthDate());
                System.out.println();
                System.out.println("End of Query 1 – Ordered Customers"+
                        "---------------------------------------------------------");
                System.out.println();

                //6.2
                System.out.println("Query 2 – Cars from Make Toyota");
                System.out.println(this.carsServise.carsFromMake("Toyota"));
                System.out.println();
                System.out.println("End of Query 2 – Cars from Make Toyota"+
                        "---------------------------------------------------------");
                System.out.println();

                //6.3
                System.out.println("Query 3 – Local Suppliers");
                System.out.println(this.supplierService.getLocalSuppliers());
                System.out.println();
                System.out.println("End of Query 3 – Local Suppliers"+
                        "---------------------------------------------------------");
                System.out.println();

                //6.4
                System.out.println("Query 4 – Cars with Their List of Parts");
                System.out.println(this.carsServise.carsWithListOfParts());
                System.out.println();
                System.out.println("End of Query 4 – Cars with Their List of Parts"+
                        "---------------------------------------------------------");
                System.out.println();

                //6.5
                System.out.println("Query 5 – Total Sales by Customer");
                System.out.println(this.customerService.totalSaleByCustomer());
                System.out.println();
                System.out.println("End of Query 5 – Total Sales by Customer"+
                        "---------------------------------------------------------");
                System.out.println();

                //6.6
                System.out.println("Query 6 – Sales with Applied Discount");
                System.out.println(this.salesService.getAllSales() + ANSI_RESET);
                System.out.println();
                System.out.println("End of Query 6 – Sales with Applied Discount"+
                        "---------------------------------------------------------");
                System.out.println();
                System.out.println();
                break;

            default:
                System.out.println("Please try again:");
                System.out.println();
                break;




        }
            System.out.println("Enter which exercise want to check: ");

            System.out.println("2, 3, 5, 6 or Stop for Exit");
            System.out.println();

            input = reader.readLine();

        }

        System.out.println("Thank you, have you a nice day or night :P !");



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

        //6.1

        //System.out.println(this.customerService.orderCustomersByBirthDate());

        //6.2
        //System.out.println(this.carsServise.carsFromMake("Toyota"));

        //6.3
       // System.out.println(this.supplierService.getLocalSuppliers());

        //6.4

       // System.out.println(this.carsServise.carsWithListOfParts());

        //6.5
        //System.out.println(this.customerService.totalSaleByCustomer());

        //6.6
        //System.out.println(this.salesService.getAllSales());


    }
}
