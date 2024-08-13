package Entities;

import java.util.Scanner;

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
        this.productName = productName;
        this.price = price;
        this.wholesalePrice = wholesalePrice;
        this.quantity = quantity;
        this.saledQty = saledQty;
        this.exDate = exDate;
        this.ownerEmail = ownerEmail;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getWholesalePrice() {
        return wholesalePrice;
    }

    public void setWholesalePrice(int wholesalePrice) {
        this.wholesalePrice = wholesalePrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSaledQty() {
        return saledQty;
    }

    public void setSaledQty(int saledQty) {
        this.saledQty = saledQty;
    }

    public String getExDate() {
        return exDate;
    }

    public void setExDate(String exDate) {
        this.exDate = exDate;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }
}
