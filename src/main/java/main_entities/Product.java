package main_entities;

public class Product {
    private int productId;
    private String productName;
    private int price;
    private int wholesalePrice;
    private int quantity;
    private int saledQty;
    private String exDate;
    private String ownerEmail;

    public Product(int productId, String productName, int price, int wholesalePrice, int quantity, int saledQty, String exDate, String ownerEmail) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.wholesalePrice = wholesalePrice;
        this.quantity = quantity;
        this.saledQty = saledQty;
        this.exDate = exDate;
        this.ownerEmail = ownerEmail;
    }

    public Product(String productName, int price, int wholesalePrice, int quantity, int saledQty, String exDate, String ownerEmail) {
        this(0,productName,price,wholesalePrice, quantity,saledQty,exDate,ownerEmail);
    }

    public int getProductId() {
        return productId;
    }


    public String getProductName() {
        return productName;
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

    public String getOwnerEmail() {
        return ownerEmail;
    }

}
