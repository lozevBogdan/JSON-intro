package course.springdata.gsonlab;

import course.springdata.gsonlab.servises.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Engine implements CommandLineRunner {

    private final UserService userService;

    @Autowired
    public Engine(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println(this.userService.seedData());


    }
}
