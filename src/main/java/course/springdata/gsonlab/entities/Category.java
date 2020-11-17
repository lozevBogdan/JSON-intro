package course.springdata.gsonlab.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity{

    private String name;
    private Set<Product> products;


    public Category() {
    }


    @Column
    @Length(min = 3,max = 15,
            message = "Category name should be at least 3 and max 15 symbols! ")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(mappedBy = "categories",fetch = FetchType.EAGER)
    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
