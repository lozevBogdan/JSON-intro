package course.springdata.gsonlab.servises.impl;


import com.google.gson.Gson;
import course.springdata.gsonlab.dtos.UserSeedDto;
import course.springdata.gsonlab.entities.User;
import course.springdata.gsonlab.repositories.UserRepository;
import course.springdata.gsonlab.servises.UserService;
import course.springdata.gsonlab.Config.ApplicationBeanConfiguration;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

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

        String content =
                String.join(",",Files.readAllLines(Path.of(USER_PATH)));

        UserSeedDto[] userSeedDtos = this.gson.fromJson(content,UserSeedDto[].class);

        for (UserSeedDto userSeedDto : userSeedDtos) {

            if (userSeedDto != null) {
                User user = this.modelMapper.map(userSeedDto, User.class);
                this.userRepository.saveAndFlush(user);
            }
        }

        return "Successfully seeded data!";
    }
}
