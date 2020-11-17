package course.springdata.gsonlab.dtos;

import com.google.gson.annotations.Expose;

public class CarViewDto {

    @Expose
    private Long Id;
    @Expose
    private String Make;
    @Expose
    private String Model;
    @Expose
    private String TravelledDistance;

    public CarViewDto() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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

    public String getTravelledDistance() {
        return TravelledDistance;
    }

    public void setTravelledDistance(String travelledDistance) {
        TravelledDistance = travelledDistance;
    }
}
