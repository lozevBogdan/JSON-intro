package course.springdata.gsonlab.dtos;

import java.util.List;

import com.google.gson.annotations.Expose;
import course.springdata.gsonlab.entities.Product;

public class SellProductInfo {
    @Expose
    private int count;
    @Expose
    private List<ProductInfo> products;

    public SellProductInfo() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ProductInfo> getProducts() {
        return products;
    }

    public void setProducts(List<ProductInfo> products) {
        this.products = products;
    }
}
