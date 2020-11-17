package course.springdata.gsonlab.entities;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.agent.builder.AgentBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer extends BaseEntity {

    private String name;
    private String birthDate;
    private boolean isYongDriver;
   // private List<Car> cars;



    public Customer() {
    }


    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "birth_date")
    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    @Column(name = "is_young_driver")
    public boolean isYongDriver() {
        return isYongDriver;
    }

    public void setYongDriver(boolean yongDriver) {
        isYongDriver = yongDriver;
    }

//
//    @OneToMany
//    public List<Car> getCars() {
//        return cars;
//    }
//
//    public void setCars(List<Car> cars) {
//        this.cars = cars;
//    }
}
