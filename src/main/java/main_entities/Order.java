package main_entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order
{
    private String sellerEmail;
    private String buyerEmail;
    private LocalDateTime date;
    List<String> itemList = new ArrayList<>();
    List<Integer> itemQty = new ArrayList<>();



    public Order(String sellerEmail, String buyerEmail, LocalDateTime date, List<String> itemList, List<Integer> itemQty) {
        this.sellerEmail = sellerEmail;
        this.buyerEmail = buyerEmail;
        this.date = date;
        this.itemList = itemList;
        this.itemQty = itemQty;
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public List<Integer> getItemQty() {
        return itemQty;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public List<String> getItemList() {
        return itemList;
    }

}
