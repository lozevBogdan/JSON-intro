package course.springdata.gsonlab.entities;


import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    @Expose
    private String name;
    @Expose
    private BigDecimal price;
    private User buyer;
    private User seller;
    private Set<Category> categories;

    public Product() {
    }


    @Column
    @Length(min = 3,message = "Product name should be at least 3 symbols!")
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "buyer_id",referencedColumnName = "id")
    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seller_id",referencedColumnName = "id")
    public User getSeller() {
        return seller;
    }
    public void setSeller(User seller) {
        this.seller = seller;
    }

    @ManyToMany
    @JoinTable(name = "products_categories",
    joinColumns = @JoinColumn(name = "products_id",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "categories_id",referencedColumnName = "id"))
    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
