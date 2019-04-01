package com.example.ckbur.artfort;

public class Product_category_model {

    public String description,discount,pname,price,productid,productmatchid,quality,stock,uri;
    private long productbaseid;

    public Product_category_model() {
    }

    public Product_category_model(String description, String discount, String pname, String price, String productid, String productmatchid, String quality, String stock, String uri, long productbaseid) {
        this.description = description;
        this.discount = discount;
        this.pname = pname;
        this.price = price;
        this.productid = productid;
        this.productmatchid = productmatchid;
        this.quality = quality;
        this.stock = stock;
        this.uri = uri;
        this.productbaseid = productbaseid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getProductmatchid() {
        return productmatchid;
    }

    public void setProductmatchid(String productmatchid) {
        this.productmatchid = productmatchid;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public long getProductbaseid() {
        return productbaseid;
    }

    public void setProductbaseid(long productbaseid) {
        this.productbaseid = productbaseid;
    }
}
