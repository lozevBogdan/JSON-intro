package course.springdata.gsonlab.servises;

import java.io.IOException;

public interface SalesService {

    String seed10SalesDataInDB();

    String getAllSales() throws IOException;

}
