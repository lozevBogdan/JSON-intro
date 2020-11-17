package course.springdata.gsonlab.entities;


import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cars")

public class Car extends BaseEntity{

    private String make;
    private String model;
    private Long travelledDistance;
    private List<Part> parts;


    public Car() {
    }

    @Column
    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }
    @Column
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
    @Column(name = "travelled_distance")
    public Long getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(Long travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

    @ManyToMany(mappedBy = "car",targetEntity = Part.class)
    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }
}
