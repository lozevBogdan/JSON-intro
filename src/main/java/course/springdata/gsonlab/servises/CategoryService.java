package course.springdata.gsonlab.servises;

import java.io.IOException;

public interface CategoryService {

    String seedCategories() throws IOException;

    String categoriesByProductsCountToGson() throws IOException;

}
