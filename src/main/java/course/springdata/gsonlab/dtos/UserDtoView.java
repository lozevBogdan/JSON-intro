package course.springdata.gsonlab.dtos;
import com.google.gson.annotations.Expose;

import java.util.List;
public class UserDtoView {

    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private List<ProductsDtoForUsersView> soldProducts;

    public UserDtoView() {
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<ProductsDtoForUsersView> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(List<ProductsDtoForUsersView> soldProducts) {
        this.soldProducts = soldProducts;
    }
}
