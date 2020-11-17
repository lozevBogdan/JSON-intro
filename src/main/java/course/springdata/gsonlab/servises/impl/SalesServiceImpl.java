package course.springdata.gsonlab.servises.impl;

import com.google.gson.Gson;
import course.springdata.gsonlab.entities.Car;
import course.springdata.gsonlab.entities.Customer;
import course.springdata.gsonlab.entities.Sale;
import course.springdata.gsonlab.repositories.CarsRepository;
import course.springdata.gsonlab.repositories.CustomersRepository;
import course.springdata.gsonlab.repositories.SalesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class SalesServiceImpl implements SalesService {

    private final SalesRepository salesRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final CarsRepository carsRepository;
    private final CustomersRepository customersRepository;

    public SalesServiceImpl
            (SalesRepository salesRepository,
             ModelMapper modelMapper, Gson gson, CarsRepository carsRepository, CustomersRepository customersRepository) {
        this.salesRepository = salesRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.carsRepository = carsRepository;
        this.customersRepository = customersRepository;
    }


    @Override
    public String seed10SalesDataInDB() {

        for (int i = 0; i < 10; i++) {
            Sale sale = new Sale();
            sale.setDiscount(getRandomDisscount());
            sale.setCar(getRandomCar());
            sale.setCustomer(getRandomCustomer());
            this.salesRepository.saveAndFlush(sale);
        }

        return "Successfully seeded sales data in DB!";
    }

    private Customer getRandomCustomer() {
        List<Customer> customers = this.customersRepository.findAll();
        Random rnd = new Random();
        int index = rnd.nextInt(customers.size() - 1);

        return customers.get(index);

    }

    private Car getRandomCar() {
        List<Car> cars = this.carsRepository.findAll();
        Random rnd = new Random();
        int index = rnd.nextInt(cars.size() - 1);

        return cars.get(index);

    }

    private double getRandomDisscount() {
        List<Integer> discounts =
                List.of(0,5,10,15,20,30,40,50);

        Random rnd = new Random();
        int index = rnd.nextInt(discounts.size() - 1);

        return discounts.get(index);
    }
}
