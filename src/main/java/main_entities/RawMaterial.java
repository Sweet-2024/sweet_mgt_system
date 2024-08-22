package main_entities;

public class RawMaterial extends Item {
    private int rmId;
    private String rmName;
    private int price;
    private int wholesalePrice;
    private int quantity;
    private int saledQty;
    private String exDate;
    private String supplierEmail;

    public RawMaterial(String name, int price, int wholesalePrice, int quantity, int saledQty, String exDate, String ownerEmail) {
        super(name,price,wholesalePrice,quantity,saledQty,exDate,ownerEmail);
    }

    public RawMaterial(int id, String name, int price, int wholesalePrice, int quantity, int saledQty, String exDate, String ownerEmail) {
        super(0,name,price,wholesalePrice,quantity,saledQty,exDate,ownerEmail);
    }
}
