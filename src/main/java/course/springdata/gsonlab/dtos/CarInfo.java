package course.springdata.gsonlab.dtos;

import com.google.gson.annotations.Expose;

import java.util.List;

public class CarInfo {

    @Expose
    private List<CarInfoDto> car;

    public CarInfo() {
    }

    public List<CarInfoDto> getCars() {
        return car;
    }

    public void setCars(List<CarInfoDto> cars) {
        this.car = cars;
    }
}
