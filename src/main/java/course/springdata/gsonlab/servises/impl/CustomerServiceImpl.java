package course.springdata.gsonlab.servises.impl;

import com.google.gson.Gson;
import course.springdata.gsonlab.dtos.CustomerDtoForSales;
import course.springdata.gsonlab.dtos.CustomerInfoDto;
import course.springdata.gsonlab.dtos.CustomerSeedDto;
import course.springdata.gsonlab.entities.Car;
import course.springdata.gsonlab.entities.Customer;
import course.springdata.gsonlab.entities.Part;
import course.springdata.gsonlab.repositories.CarsRepository;
import course.springdata.gsonlab.repositories.CustomersRepository;
import course.springdata.gsonlab.repositories.PartsRepository;
import course.springdata.gsonlab.servises.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class CustomerServiceImpl implements CustomerService {


    private final String CUSTOMER_DATA_PATH = "src/main/resources/customers.json";

    private final CustomersRepository customersRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final CarsRepository carsRepository;
    private final PartsRepository partsRepository;

    @Autowired
    public CustomerServiceImpl(CustomersRepository customersRepository, ModelMapper modelMapper, Gson gson, CarsRepository carsRepository, PartsRepository partsRepository) {
        this.customersRepository = customersRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.carsRepository = carsRepository;
        this.partsRepository = partsRepository;
    }


    @Override
    @Transactional
    public String seedCustomerDataInDB() throws IOException {
        String content = String.join("",
                Files.readAllLines(Path.of(CUSTOMER_DATA_PATH)));

        CustomerSeedDto[] customerSeedDtos =
                this.gson.fromJson(content,CustomerSeedDto[].class);


        for (CustomerSeedDto customerSeedDto : customerSeedDtos) {

            Customer customer =
            this.modelMapper.map(customerSeedDto,Customer.class);

            customer.setCars(getRandomListOfCars());
            customer.setId(null);

            this.customersRepository.saveAndFlush(customer);


        }
        return "Successfully seeded Customer data in DB!";
    }

    private List<Car> getRandomListOfCars() {

        List<Car> cars = this.carsRepository.findAll();
        List<Car> randomList = new ArrayList<>();

        Random rnd = new Random();

        int numberOfCars = rnd.nextInt(3);

        for (int i = 0; i < numberOfCars; i++) {

            int index = rnd.nextInt(cars.size());

            if (!randomList.contains(cars.get(index))){
                randomList.add(cars.get(index));
            }

        }
        return randomList;
    }

    @Override
    public String orderCustomersByBirthDate() throws IOException {
        List<Customer> customers = this.customersRepository
                .findAllOrderByBirthDateAAndByIsYongDriver();

        List<CustomerInfoDto> customerInfoDtos = new ArrayList<>();

        for (Customer customer : customers) {

            CustomerInfoDto customerInfoDto1 =
                    this.modelMapper.map(customer,CustomerInfoDto.class);
            customerInfoDto1.setBirthDate(customer.getBirthDate().toString());
            customerInfoDto1.setSales(new ArrayList<>());

            customerInfoDtos.add(customerInfoDto1);

        }
        String toJson = this.gson.toJson(customerInfoDtos);

        FileWriter file = new FileWriter("ordered-customers.json");
        file.write(toJson);
        file.close();

        return toJson;
    }

    @Override
    public String totalSaleByCustomer() throws IOException {

        List<Customer> customers = this.customersRepository
                .findAllWithATLeastOneCar();
//        List<Customer> allCustomers = this.customersRepository
//                .findAll();

        Map<String,Integer> nameBoughtCar = new HashMap<>();
        Map<String, BigDecimal> nameSpentMoney = new HashMap<>();

        for (Customer customer : customers) {

            double spentMoney =0 ;

            List<Car> cars = customer.getCars();

           // Optional<Part> byId = this.partsRepository.findById(2L);

            for (Car car : cars) {
                List<Part> parts = car.getParts();
                for (Part part : parts) {
                    BigDecimal price = part.getPrice();
                    spentMoney +=  price.doubleValue();
                    System.out.println();
                }
            }

            nameBoughtCar.put(customer.getName(), cars.size());
            nameSpentMoney.put(customer.getName(),BigDecimal
                    .valueOf(spentMoney));
        }

        List<CustomerDtoForSales> customerDtoForSales =
                new ArrayList<>();

        for (Map.Entry<String, BigDecimal> entry : nameSpentMoney.entrySet()) {

            CustomerDtoForSales customerDtoForSales1 =
                    new CustomerDtoForSales();
            customerDtoForSales1.setFullName(entry.getKey());
            customerDtoForSales1
                    .setBoughtCars(nameBoughtCar.get(entry.getKey()));
            customerDtoForSales1.setSpentMoney(entry.getValue());

            customerDtoForSales.add(customerDtoForSales1);

        }

        customerDtoForSales = customerDtoForSales
                .stream()
                .sorted((c1,c2)->{

                    int  result =
                            c2.getBoughtCars() - c1.getBoughtCars();


                    if (result == 0){
                        result = c2.getSpentMoney()
                                .compareTo(c1.getSpentMoney());
                    }

                    return result;

                }).collect(Collectors.toList());

        String toJson = this.gson.toJson(customerDtoForSales);

        FileWriter fileWriter = new FileWriter
                ("customers-total-sales.json");

        fileWriter.write(toJson);
        fileWriter.close();

        return toJson;
    }

    private List<Part> getAllPartsFromThisCar(Car car) {


    return this.partsRepository.getAllByCarContaining(car);


    }
}
