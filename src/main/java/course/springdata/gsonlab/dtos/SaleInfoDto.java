package course.springdata.gsonlab.dtos;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class SaleInfoDto {

    @Expose
private CarInfoSaleDto car;

    @Expose
private String customerName;

    @Expose
private double Discount;

    @Expose
private double price;

    @Expose
private double priceWithDiscount;


    public SaleInfoDto() {
    }

    public CarInfoSaleDto getCar() {
        return car;
    }

    public void setCar(CarInfoSaleDto car) {
        this.car = car;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getDiscount() {
        return Discount;
    }

    public void setDiscount(double discount) {
        Discount = discount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPriceWithDiscount() {
        return priceWithDiscount;
    }

    public void setPriceWithDiscount(double priceWithDiscount) {
        this.priceWithDiscount = priceWithDiscount;
    }
}

