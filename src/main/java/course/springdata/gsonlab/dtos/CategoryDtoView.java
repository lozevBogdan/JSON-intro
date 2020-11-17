package course.springdata.gsonlab.dtos;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class CategoryDtoView {

    @Expose
    private String category;
    @Expose
    private int productCount;
    @Expose
    private double averagePrice;
    @Expose
    private double totalRevenue;

    public CategoryDtoView() {
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    public double getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(double averagePrice) {
        this.averagePrice = averagePrice;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
