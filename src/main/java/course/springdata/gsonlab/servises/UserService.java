package course.springdata.gsonlab.servises;

import course.springdata.gsonlab.entities.User;
import org.modelmapper.ModelMapper;

import java.io.IOException;
import java.util.List;
public interface UserService {

    String seedData() throws IOException;

    List<User> allUsersWithAtLeastOneSoldProduct();

    String userSoldProducts() throws IOException;

    String getInfoForUsersAndSoldProducts() throws IOException;

}
