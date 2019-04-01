package com.example.ckbur.artfort;

import java.util.List;

public class place_order {


   private String address,phone,totalt;

    private List<ordermodel>product;

    public place_order() {
    }

    public place_order(String address, String phone, String totalt, List<ordermodel> product) {
        this.address = address;
        this.phone = phone;
        this.totalt = totalt;
        this.product = product;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTotalt() {
        return totalt;
    }

    public void setTotalt(String totalt) {
        this.totalt = totalt;
    }

    public List<ordermodel> getProduct() {
        return product;
    }

    public void setProduct(List<ordermodel> product) {
        this.product = product;
    }
}
