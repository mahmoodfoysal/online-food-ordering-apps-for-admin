package com.example.dreamland.foodlover;

public class orderdata {
    String clientaddress,clientname,clientorder,clientphone,clientquantity;

    public orderdata(String clientaddress, String clientname, String clientorder, String clientphone, String clientquantity) {
        this.clientaddress = clientaddress;
        this.clientname = clientname;
        this.clientorder = clientorder;
        this.clientphone = clientphone;
        this.clientquantity = clientquantity;
    }
    public orderdata(){

    }

    public String getClientaddress() {
        return clientaddress;
    }

    public String getClientname() {
        return clientname;
    }

    public String getClientorder() {
        return clientorder;
    }

    public String getClientphone() {
        return clientphone;
    }

    public String getClientquantity() {
        return clientquantity;
    }
}
