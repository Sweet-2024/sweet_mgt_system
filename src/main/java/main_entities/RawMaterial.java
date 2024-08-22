package main_entities;

public class RawMaterial {
    private int rmId;
    private String rmName;
    private int price;
    private int wholesalePrice;
    private int quantity;
    private int saledQty;
    private String exDate;
    private String supplierEmail;

    public RawMaterial(int rmId, String rmName, int price, int wholesalePrice, int quantity, int saledQty, String exDate, String supplierEmail) {
        this.rmId = rmId;
        this.rmName = rmName;
        this.price = price;
        this.wholesalePrice = wholesalePrice;
        this.quantity = quantity;
        this.saledQty = saledQty;
        this.exDate = exDate;
        this.supplierEmail = supplierEmail;
    }
    public RawMaterial(String rmName, int price, int wholesalePrice, int quantity, int saledQty, String exDate, String supplierEmail) {
        this(0,rmName,price,wholesalePrice,quantity,saledQty,exDate,supplierEmail);
    }


    public int getRmId() {
        return rmId;
    }


    public String getRmName() {
        return rmName;
    }


    public int getPrice() {
        return price;
    }


    public int getWholesalePrice() {
        return wholesalePrice;
    }


    public int getQuantity() {
        return quantity;
    }


    public int getSaledQty() {
        return saledQty;
    }


    public String getExDate() {
        return exDate;
    }


    public String getSupplierEmail() {
        return supplierEmail;
    }

}
