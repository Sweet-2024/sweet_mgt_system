package main_entities;

public class Product extends Item {
    private int productId;
    private String productName;
    private int price;
    private int wholesalePrice;
    private int quantity;
    private int saledQty;
    private String exDate;
    private String ownerEmail;

    public Product(int productId, String productName, int price, int wholesalePrice, int quantity, int saledQty, String exDate, String ownerEmail) {
        super(productId, productName, price,wholesalePrice,quantity,saledQty,exDate,ownerEmail);
    }

    public Product(String productName, int price, int wholesalePrice, int quantity, int saledQty, String exDate, String ownerEmail) {
        super(0,productName,price,wholesalePrice, quantity,saledQty,exDate,ownerEmail);
    }
}
