package acceptance;

import main_entities.Product;
import io.cucumber.java.en.*;
import sweet_system.Listing;
import sweet_system.Checks;

import sweet_system.MyApp;
import sweet_system.Updates;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import static org.junit.Assert.assertTrue;

public class product_mgt_Test {
    static MyApp myApp;

    public product_mgt_Test(MyApp myApp) {
        super();
        this.myApp = myApp;
    }
    @Given("logged in to the system as owner or supplier")
    public void loggedInToTheSystemAsOwnerOrSupplier() {
        myApp.userType = 2;
        assertTrue(myApp.userType == 2 || myApp.userType == 3);
    }

    @When("choosing product management from the list")
    public void choosingProductManagementFromTheList() {

    }

    @When("choosing Add new product")
    public void choosingAddNewProduct() {

    }

    @When("the owner should add valid product information")
    public void theOwnerShouldAddValidProductInformation() {
        String name = "Vanilla cake";
        assertTrue(Checks.isValidProductName(name));

        String validEmail = "s12115544@stu.najah.edu";
        assertTrue(Checks.isValidEmail(validEmail));
    }

    @Then("the product should be added successfully")
    public void theProductShouldBeAddedSuccessfully() {
        String pName = "chocolate cake";
        assertTrue(Checks.isValidProductName(pName));


        int price = 70;
        int wholesalePrice = 60;
        int quantity = 10;
        int saledQty = 6;
        String exDate = "2024-12-12";
        Checks.isValidDate(exDate);

        String ownerEmail = "s12113354@stu.najah.edu";
        assertTrue(Checks.isValidEmail(ownerEmail));

        Product product = new Product (pName, price, wholesalePrice, quantity, saledQty, exDate, ownerEmail);
        Updates.addNewProduct(product);

        Listing.listingOfProductsForSpecificOwner(ownerEmail);
        assertTrue(Checks.checkIfProductInDatabase(pName));
    }

    @When("choosing update product")
    public void choosingUpdateProduct() {

    }

    @When("list of available products will appear")
    public void listOfAvailableProductsWillAppear() {
        Listing.listingOfProducts();
        assertTrue(Checks.checkIfThereAreProductsInDatabase());
    }

    @Then("the owner or supplier will update product")
    public void theOwnerOrSupplierWillUpdateProduct() {

    }

    @Then("updated product will be saved successfully")
    public void updatedProductWillBeSavedSuccessfully() {
        int id = 2;
        String pName = "chocolate cake";
        assertTrue(Checks.isValidProductName(pName));

        int price = 70;
        int wholesalePrice = 60;
        int quantity = 10;
        int saledQty = 6;
        String exDate = "2024-12-25";

        String ownerEmail = "s12113763@stu.najah.edu";
        assertTrue(Checks.isValidEmail(ownerEmail));

        Product product = new Product (id, pName, price, wholesalePrice, quantity, saledQty, exDate, ownerEmail);
        Updates.updateProduct(product);

        assertTrue(Checks.checkIfProductInDbAccordingToId(id));
    }

    @When("choosing Remove product")
    public void choosingRemoveProduct() {

    }

    @Then("the owner or supplier will remove product")
    public void theOwnerOrSupplierWillRemoveProduct()
    {

    }

    @Then("the product will be removed successfully")
    public void theProductWillBeRemovedSuccessfully() {
        int productId = 19;

        Updates.deleteProduct(productId);

        assertFalse(Checks.checkIfProductInDbAccordingToId(productId));
    }

    @When("choosing Monitor sales and profits from the list")
    public void choosingMonitorSalesAndProfitsFromTheList() {
        MyApp.userType = 2;
        MyApp.userEmail = "s12112506@stu.najah.edu";
        assertTrue(Listing.generateFinancialReports());
    }

    @Then("list of sales and profits will appear")
    public void listOfSalesAndProfitsWillAppear() {
        MyApp.userType = 2;
        MyApp.userEmail = "s12112506@stu.najah.edu";
        assertTrue(Listing.listingBestSellingProduct());
    }

    @When("choosing Identify best-selling products from the list")
    public void choosingIdentifyBestSellingProductsFromTheList() {

    }

    @Then("list of best-selling products will appear")
    public void listOfBestSellingProductsWillAppear() {

    }

    @When("the owner or supplier enters discount percentage")
    public void theOwnerOrSupplierEntersDiscountPercentage() {

    }

    @Then("the discount will be applied to products with soon expiry date")
    public void theDiscountWillBeAppliedToProductsWithSoonExpiryDate() {
        double discount = 0.20;
        Checks.isValidDiscount(discount);
        assertTrue(Updates.productDiscount(discount));
    }

}
