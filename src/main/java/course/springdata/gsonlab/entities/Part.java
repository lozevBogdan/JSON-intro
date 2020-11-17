package course.springdata.gsonlab.entities;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


@Entity
@Table(name = "parts")
public class Part extends BaseEntity {

    private String name;
    private BigDecimal price;
    private int quantity;

    //@Column(name = "supplier_id")
    private Supplier supplier;
    private List<Car> car;


    public Part() {
    }


    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    @Column
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    @ManyToOne
    @JoinColumn(name = "supplier_id")
    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }


    @ManyToMany
    @JoinTable(name = "parts_cars",
            joinColumns = @JoinColumn(name = "part_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "car_id", referencedColumnName = "id"))
    public List<Car> getCar() {
        return car;
    }

    public void setCar(List<Car> car) {
        this.car = car;
    }
}
