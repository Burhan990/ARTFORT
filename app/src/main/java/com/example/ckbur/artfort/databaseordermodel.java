package com.example.ckbur.artfort;

public class databaseordermodel {

    public String productid;
    public String productname;
    public String quantity;
    public String price;
    public String discount;

    public databaseordermodel() {
    }

    public databaseordermodel(String productid, String productname, String quantity, String price, String discount) {
        this.productid = productid;
        this.productname = productname;
        this.quantity = quantity;
        this.price = price;
        this.discount = discount;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }
}
