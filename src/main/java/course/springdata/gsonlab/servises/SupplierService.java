package course.springdata.gsonlab.servises;

import java.io.IOException;

public interface SupplierService {


    String seedSupplierData() throws IOException;
    String getLocalSuppliers() throws IOException;

}
