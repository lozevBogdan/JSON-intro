package course.springdata.gsonlab.servises.impl;


import com.google.gson.Gson;
import course.springdata.gsonlab.dtos.ProductDtoView;
import course.springdata.gsonlab.dtos.ProductSeedDto;
import course.springdata.gsonlab.entities.Category;
import course.springdata.gsonlab.entities.Product;
import course.springdata.gsonlab.entities.User;
import course.springdata.gsonlab.repositories.CategoryRepository;
import course.springdata.gsonlab.repositories.ProductsRepository;
import course.springdata.gsonlab.repositories.UserRepository;
import course.springdata.gsonlab.servises.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    private static final String PRODUCTS_PATH = "src/main/resources/products.json";

    private final ProductsRepository productsRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;


    @Autowired
    public ProductServiceImpl(ProductsRepository productsRepository, ModelMapper modelMapper, Gson gson, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.productsRepository = productsRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public String seedProducts() throws IOException {

        String content = String.join("",
                Files.readAllLines(Path.of("src/main/resources/products.json")));

        ProductSeedDto [] productSeedDtos = this.gson
                .fromJson(content,ProductSeedDto[].class);


        int i = 0;
        for (ProductSeedDto productSeedDto : productSeedDtos) {

            Product product = modelMapper.map(productSeedDto,Product.class);

            if (i % 4 != 0) {
                product.setBuyer(getRandomUser());
            }
            product.setSeller(getRandomUser());

            product.setCategories(getRandomListOfCategories());

            i++;

            this.productsRepository.saveAndFlush(product);

        }

        return "Successful seeded products data !";
    }

    @Override
    public String productsInRange(BigDecimal min, BigDecimal max) throws IOException {

        List<Product> byPriceBetweenAndBuyerIsNullOrderByPrice =
                this.productsRepository
                        .findByPriceBetweenAndBuyerIsNullOrderByPrice(min, max);


        List<ProductDtoView> productDtoViews = new ArrayList<>();

        for (Product product : byPriceBetweenAndBuyerIsNullOrderByPrice) {

            ProductDtoView productDtoView = modelMapper.map(product,ProductDtoView.class);

            productDtoView.setSeller(product.getSeller().getFirstName() + " " +
                    product.getSeller().getLastName());

            productDtoViews.add(productDtoView);

        }

//        ProductDtoView [] productDtoViews
//                    =this.modelMapper.map(byPriceBetweenAndBuyerIsNullOrderByPrice,
//                ProductDtoView[].class);

        String toJson = this.gson.toJson(productDtoViews);


        FileWriter file = new FileWriter("products-in-range.json");
        file.write(toJson);
        file.close();

        return toJson;
    }

    @Override
    public List<Product> findByPriceBetweenAndBuyerIsNullOrderByPrice
            (BigDecimal min, BigDecimal max) {
        return this.productsRepository
                .findByPriceBetweenAndBuyerIsNullOrderByPrice(min,max);

    }

    private Set<Category> getRandomListOfCategories() {

        Set<Category> categorySet = new HashSet<>();
        Random rnd = new Random();

        List<Category> categories = this.categoryRepository.findAll();

        for (int i = 0; i < 2; i++) {
            int index = rnd.nextInt((int) this.categoryRepository.count());
            categorySet.add(categories.get(index));
        }

        return categorySet;
    }

    private User getRandomUser() {

        List<User> allUsers = this.userRepository.findAll();

        Random rnd =new Random();

        int index = rnd.nextInt(allUsers.size());

        return allUsers.get(index);
    }
}
