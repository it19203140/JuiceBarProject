package com.example.sa.JuiceBar;


public class OrderClass {
    public String Itemprice;
    private String Itemid;
    private String Itemname;
    private String Itemquantity;

    public OrderClass(String iId, String iName, String iQuantity,String iPrice)
    {
        Itemid = iId;
        Itemname = iName;
        Itemquantity = iQuantity;
        Itemprice = iPrice;

    }

    public OrderClass() {

    }

    public void setItemid(String itemid) {
        Itemid = itemid;
    }

    public void setItemname(String itemname) {
        Itemname = itemname;
    }

    public void setItemprice(String itemprice) {
        Itemprice = itemprice;
    }

    public String getItemId() {
        return Itemid;
    }

    public String getItemName() {
        return Itemname;
    }

    public String getItemPrice() {return Itemprice;}

    public String getItemquantity() {return Itemquantity;}

    public void setItemquantity(String itemquantity) {
        Itemquantity = itemquantity;
    }


}
