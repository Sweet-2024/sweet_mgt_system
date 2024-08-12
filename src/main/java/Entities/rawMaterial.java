package Entities;

public class rawMaterial {
    private int rmId;
    private String rmName;
    private int price;
    private int wholesalePrice;
    private int quantity;
    private int saledQty;
    private String exDate;
    private String supplierEmail;

    public rawMaterial(String rmName, int price, int wholesalePrice, int quantity, int saledQty, String exDate, String supplierEmail) {
        this.rmName = rmName;
        this.price = price;
        this.wholesalePrice = wholesalePrice;
        this.quantity = quantity;
        this.saledQty = saledQty;
        this.exDate = exDate;
        this.supplierEmail = supplierEmail;
    }
    public rawMaterial(int rmId, String rmName, int price, int wholesalePrice, int quantity, int saledQty, String exDate, String supplierEmail) {
        this.rmId = rmId;
        this.rmName = rmName;
        this.price = price;
        this.wholesalePrice = wholesalePrice;
        this.quantity = quantity;
        this.saledQty = saledQty;
        this.exDate = exDate;
        this.supplierEmail = supplierEmail;
    }

    public int getRmId() {
        return rmId;
    }

    public void setRmId(int rmId) {
        this.rmId = rmId;
    }

    public String getRmName() {
        return rmName;
    }

    public void setRmName(String rmName) {
        this.rmName = rmName;
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

    public String getSupplierEmail() {
        return supplierEmail;
    }

    public void setSupplierEmail(String supplierEmail) {
        this.supplierEmail = supplierEmail;
    }

    @Override
    public String toString() {
        return "rawMaterial{" +
                "rmId=" + rmId +
                ", rmName='" + rmName + '\'' +
                ", price=" + price +
                ", wholesalePrice=" + wholesalePrice +
                ", quantity=" + quantity +
                ", saledQty=" + saledQty +
                ", exDate='" + exDate + '\'' +
                ", supplierEmail='" + supplierEmail + '\'' +
                '}';
    }
}
