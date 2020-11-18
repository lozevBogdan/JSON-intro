package course.springdata.gsonlab.servises;

import java.io.IOException;

public interface CarsServise {

    String seedCarsData() throws IOException;
    String carsFromMake(String make) throws IOException;
    String carsWithListOfParts() throws IOException;

}
