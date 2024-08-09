package Entities;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Order {
    private String sellerEmail;
    private String buyerEmail;
    private LocalDateTime date;
    ArrayList<String> itemName = new ArrayList<String>();

    public Order(String sellerEmail, String buyerEmail, LocalDateTime date, ArrayList<String> itemId) {
        this.sellerEmail = sellerEmail;
        this.buyerEmail = buyerEmail;
        this.date = date;
        this.itemName = itemId;
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public void setSellerEmail(String sellerEmail) {
        this.sellerEmail = sellerEmail;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public ArrayList<String> getItemName() {
        return itemName;
    }

    public void setItemName(ArrayList<String> itemName) {
        this.itemName = itemName;
    }
}
