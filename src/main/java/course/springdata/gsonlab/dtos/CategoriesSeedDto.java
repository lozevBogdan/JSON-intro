package course.springdata.gsonlab.dtos;

import com.google.gson.annotations.Expose;

public class CategoriesSeedDto {

    @Expose
    private String name;


    public CategoriesSeedDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
