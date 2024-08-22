package main_entities;

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

    public String getBusinessName() {
        return businessName;
    }

    public String getBusinessLocation() {
        return businessLocation;
    }

    public String getBusinessOwnerEmail() {
        return businessOwnerEmail;
    }
}
