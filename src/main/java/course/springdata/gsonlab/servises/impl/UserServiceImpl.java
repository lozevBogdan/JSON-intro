package course.springdata.gsonlab.servises.impl;


import com.google.gson.Gson;
import course.springdata.gsonlab.dtos.*;
import course.springdata.gsonlab.entities.User;
import course.springdata.gsonlab.repositories.UserRepository;
import course.springdata.gsonlab.servises.UserService;
import course.springdata.gsonlab.Config.ApplicationBeanConfiguration;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final String USER_PATH = "src/main/resources/users.json";
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final Gson gson;


    @Autowired
    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository, Gson gson) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.gson = gson;
    }


    @Override
    public String seedData() throws IOException {
        StringBuilder sb = new StringBuilder();

    String  content =
              String.join("",Files.readAllLines(Path.of(USER_PATH)));

       UserSeedDto[] userSeedDtoList = this.gson
                .fromJson(content,UserSeedDto[].class);


        for (UserSeedDto userSeedDto : userSeedDtoList) {

            User user = this.modelMapper.map(userSeedDto, User.class);
            this.userRepository.saveAndFlush(user);


        }

        return "Successfully seeded User data!";
    }


    @Override
    public String userSoldProducts() throws IOException {

        List<User> allUsersWithSolProducts = this.userRepository
                .findAllBySoldProductsAtLeastOneOrderByLastNameFirstName();

        List<UserDtoView> userDtosForViews = new LinkedList<>();

        for (User user : allUsersWithSolProducts) {

            UserDtoView userDtoView = this.modelMapper.map(user,UserDtoView.class);
           ProductsDtoForUsersView [] productsDtoForUsersView =
                    this.modelMapper.map(user.getSoldProducts()
                            ,ProductsDtoForUsersView[].class);
           userDtoView.setSoldProducts(List.of(productsDtoForUsersView));

           userDtosForViews.add(userDtoView);
        }

        String toJson = this.gson.toJson(userDtosForViews);
        FileWriter file = new FileWriter("users-sold-products.json");
        file.write(toJson);
        file.close();

        return toJson;

    }

    @Override
    public String getInfoForUsersAndSoldProducts() throws IOException {

        List<User> sellers = this.userRepository
                .findAllBySoldProductsAtLeastOneOrderBySoldProductsThenByLastName();
        List<SellerUserInfo> collectionOfSellers = new ArrayList<>();

        for (User user : sellers) {


            List<ProductInfo> productInfoList = List.of(
                    this.modelMapper.map(user.getSoldProducts(), ProductInfo[].class));
            int count = productInfoList.size();

            SellProductInfo sellProductInfo = new SellProductInfo();
            sellProductInfo.setCount(count);
            sellProductInfo.setProducts(productInfoList);

            SellerUserInfo sellerUserInfo = this.modelMapper
                    .map(user,SellerUserInfo.class);

            sellerUserInfo.setSoldProducts(sellProductInfo);

            collectionOfSellers.add(sellerUserInfo);

        }

        SellerInfo sellerInfo = new SellerInfo();

        sellerInfo.setUserCount(collectionOfSellers.size());
        sellerInfo.setUsers(collectionOfSellers);

        String toJson = this.gson.toJson(sellerInfo);

        FileWriter fileWriter =new FileWriter("users-and-products.json");

        fileWriter.write(toJson);
        fileWriter.close();

        return toJson;
    }

    @Override
    public List<User> allUsersWithAtLeastOneSoldProduct() {
        return this.userRepository
                .findAllBySoldProductsAtLeastOneOrderByLastNameFirstName();



    }


}
