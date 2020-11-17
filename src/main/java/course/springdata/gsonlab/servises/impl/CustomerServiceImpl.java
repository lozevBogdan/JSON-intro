package course.springdata.gsonlab.servises.impl;

import com.google.gson.Gson;
import course.springdata.gsonlab.dtos.CustomerSeedDto;
import course.springdata.gsonlab.entities.Customer;
import course.springdata.gsonlab.repositories.CustomersRepository;
import course.springdata.gsonlab.servises.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.FormatStyle;

import static java.time.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME;


@Service
public class CustomerServiceImpl implements CustomerService {


    private final String CUSTOMER_DATA_PATH = "src/main/resources/customers.json";

    private final CustomersRepository customersRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;

    public CustomerServiceImpl(CustomersRepository customersRepository, ModelMapper modelMapper, Gson gson) {
        this.customersRepository = customersRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }


    @Override
    public String seedCustomerDataInDB() throws IOException {


        String content = String.join("",
                Files.readAllLines(Path.of(CUSTOMER_DATA_PATH)));

        CustomerSeedDto[] customerSeedDtos =
                this.gson.fromJson(content,CustomerSeedDto[].class);


//        DateTimeFormatterBuilder formatter =
//                DateTimeFormatterBuilder.ISO_OFFSET_DATE_TIME;
//        LocalDate date = LocalDate.now();

        for (CustomerSeedDto customerSeedDto : customerSeedDtos) {

            Customer customer =
            this.modelMapper.map(customerSeedDto,Customer.class);
//            customer.setBirthDate(
//                    date.format()
//            );

            this.customersRepository.saveAndFlush(customer);


        }


        return "Successfully seeded Customer data in DB!";
    }
}
