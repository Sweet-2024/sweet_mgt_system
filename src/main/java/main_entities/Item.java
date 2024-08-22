package main_entities;

public class Item
{
    private int itemId;
    private String itemName;
    private int price;
    private int wholesalePrice;
    private int quantity;
    private int saledQty;
    private String exDate;
    private String email;

    public Item(int itemId, String itemName, int price, int wholesalePrice, int quantity, int saledQty, String exDate, String supplierEmail) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.price = price;
        this.wholesalePrice = wholesalePrice;
        this.quantity = quantity;
        this.saledQty = saledQty;
        this.exDate = exDate;
        this.email = supplierEmail;
    }
    public Item(String rmName, int price, int wholesalePrice, int quantity, int saledQty, String exDate, String supplierEmail) {
        this(0,rmName,price,wholesalePrice,quantity,saledQty,exDate,supplierEmail);
    }

    public String getName() {
        return itemName;
    }

    public int getId() {
        return itemId;
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


    public String getEmail() {
        return email;
    }


    @Override
    public String toString() {
        return "rawMaterial{" +
                "rmId=" + itemId +
                ", rmName='" +  + '\'' +
                ", price=" + price +
                ", wholesalePrice=" + wholesalePrice +
                ", quantity=" + quantity +
                ", saledQty=" + saledQty +
                ", exDate='" + exDate + '\'' +
                ", supplierEmail='" + email + '\'' +
                '}';
    }
}
