package course.springdata.gsonlab.dtos;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class PartsViewDto {

    @Expose
    private String Name;

    @Expose
    private BigDecimal Price;


    public PartsViewDto() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public BigDecimal getPrice() {
        return Price;
    }

    public void setPrice(BigDecimal price) {
        Price = price;
    }
}
