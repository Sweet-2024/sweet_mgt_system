package Entities;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Order
{
    private String sellerEmail;
    private String buyerEmail;
    private LocalDateTime date;
    ArrayList<String> itemList = new ArrayList<String>();
    ArrayList<Integer> itemQty = new ArrayList<Integer>();



    public Order(String sellerEmail, String buyerEmail, LocalDateTime date, ArrayList<String> itemList, ArrayList<Integer> itemQty) {
        this.sellerEmail = sellerEmail;
        this.buyerEmail = buyerEmail;
        this.date = date;
        this.itemList = itemList;
        this.itemQty = itemQty;
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

    public ArrayList<Integer> getItemQty() {
        return itemQty;
    }

    public void setItemQty(ArrayList<Integer> itemQty) {
        this.itemQty = itemQty;
    }

    public ArrayList<String> getItemList() {
        return itemList;
    }

    public void setItemList(ArrayList<String> itemList) {
        this.itemList = itemList;
    }


}
