package course.springdata.gsonlab.dtos;

import java.util.List;
import com.google.gson.annotations.Expose;
import course.springdata.gsonlab.entities.Sale;

import java.time.LocalDateTime;

public class CustomerInfoDto {

    @Expose
    private Long Id;
    @Expose
    private String Name;
    @Expose
    private String BirthDate;
    @Expose
    private boolean IsYoungDriver;
    @Expose
    private List<Sale> Sales;

    public CustomerInfoDto() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(String birthDate) {
        BirthDate = birthDate;
    }

    public boolean isYoungDriver() {
        return IsYoungDriver;
    }

    public void setYoungDriver(boolean youngDriver) {
        IsYoungDriver = youngDriver;
    }

    public List<Sale> getSales() {
        return Sales;
    }

    public void setSales(List<Sale> sales) {
        Sales = sales;
    }
}
