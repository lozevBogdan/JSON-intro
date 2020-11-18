package course.springdata.gsonlab.servises;

import java.util.List;
import course.springdata.gsonlab.entities.Customer;

import java.io.IOException;

public interface CustomerService {

    String seedCustomerDataInDB() throws IOException;

    String orderCustomersByBirthDate() throws IOException;

    String totalSaleByCustomer() throws IOException;


}
