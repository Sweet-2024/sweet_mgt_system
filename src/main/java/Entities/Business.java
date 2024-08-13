package Entities;

public class Business {
    private int businessId;
    private String businessName;
    private String businessLocation;
    private String businessOwnerEmail;

    public Business(int businessId, String businessName, String businessLocation, String businessOwnerEmail) {
        this.businessId = businessId;
        this.businessName = businessName;
        this.businessLocation = businessLocation;
        this.businessOwnerEmail = businessOwnerEmail;
    }

    public int getBusinessId() {
        return businessId;
    }

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessLocation() {
        return businessLocation;
    }

    public void setBusinessLocation(String businessLocation) {
        this.businessLocation = businessLocation;
    }

    public String getBusinessOwnerEmail() {
        return businessOwnerEmail;
    }

    public void setBusinessOwnerEmail(String businessOwnerEmail) {
        this.businessOwnerEmail = businessOwnerEmail;
    }
}
