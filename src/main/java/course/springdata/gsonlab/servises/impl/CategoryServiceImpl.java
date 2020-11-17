package course.springdata.gsonlab.servises.impl;


import com.google.gson.Gson;
import course.springdata.gsonlab.dtos.CategoriesSeedDto;
import course.springdata.gsonlab.dtos.CategoryDtoView;
import course.springdata.gsonlab.entities.Category;
import course.springdata.gsonlab.entities.Product;
import course.springdata.gsonlab.repositories.CategoryRepository;
import course.springdata.gsonlab.servises.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {


    private final String CATEGORY_PATH = "src/main/resources/categories.json";

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper, Gson gson) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }


    @Override
    public String seedCategories() throws IOException {


        String content = String.join
                ("",Files.readAllLines(Path.of(CATEGORY_PATH)));

        CategoriesSeedDto [] categoriesSeedDtos =
                this.gson.fromJson(content,CategoriesSeedDto[].class);

        for (CategoriesSeedDto categoriesSeedDto : categoriesSeedDtos) {

            Category category = this.modelMapper
                    .map(categoriesSeedDto,Category.class);

            this.categoryRepository.saveAndFlush(category);
        }

        return "Successful seeded category data !";
    }


    @Override
    public String categoriesByProductsCountToGson() throws IOException {

        List<Category> categories =
                this.categoryRepository.findAllOrderByNumberOfProducts();

        List <CategoryDtoView> categoryDtoViews = new ArrayList<>();

        for (Category category : categories) {

            CategoryDtoView categoryDtoView = new CategoryDtoView();
          categoryDtoView.setCategory(category.getName());
          categoryDtoView.setProductCount(category.getProducts().size());
          categoryDtoView.setAveragePrice(category.getProducts()
          .stream()
          .mapToDouble(p->Double.valueOf(p.getPrice().toString()))
          .average().orElseThrow());
          categoryDtoView.setTotalRevenue(category.getProducts()
          .stream()
          .mapToDouble(p->Double.valueOf(p.getPrice().toString()))
          .sum());

          categoryDtoViews.add(categoryDtoView);

        }

        String toJson = this.gson.toJson(categoryDtoViews);

        FileWriter fileWriter = new FileWriter("categories-by-products.json");
        fileWriter.write(toJson);
        fileWriter.close();

        return toJson;
    }
}
