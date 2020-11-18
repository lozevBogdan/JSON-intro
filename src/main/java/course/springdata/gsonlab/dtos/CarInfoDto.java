package course.springdata.gsonlab.dtos;

import com.google.gson.annotations.Expose;
import jdk.dynalink.linker.LinkerServices;
import java.util.List;

public class CarInfoDto {

    @Expose
    private String Make;
    @Expose
    private String Model;
    @Expose
    private Long TravelledDistance;

    @Expose
    private List<PartsViewDto> parts;

    public CarInfoDto() {
    }

    public String getMake() {
        return Make;
    }

    public void setMake(String make) {
        Make = make;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public Long getTravelledDistance() {
        return TravelledDistance;
    }

    public void setTravelledDistance(Long travelledDistance) {
        TravelledDistance = travelledDistance;
    }

    public List<PartsViewDto> getParts() {
        return parts;
    }

    public void setParts(List<PartsViewDto> parts) {
        this.parts = parts;
    }
}
