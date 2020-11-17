package course.springdata.gsonlab.dtos;

import com.google.gson.annotations.Expose;
import course.springdata.gsonlab.entities.User;
import jdk.dynalink.linker.LinkerServices;

import java.util.*;

public class SellerInfo {

    @Expose
    private int userCount;
    @Expose
    private List<SellerUserInfo>users;



    public SellerInfo() {

    }

    public int getUserCount() {
        return userCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }

    public List<SellerUserInfo> getUsers() {
        return users;
    }

    public void setUsers(List<SellerUserInfo> users) {
        this.users = users;
    }
}
