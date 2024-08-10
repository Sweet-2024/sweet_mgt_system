package acceptance;

import io.cucumber.java.en.*;
import sweetSys.Listing;
import sweetSys.MyApp;

import static org.junit.Assert.assertTrue;

public class product_mgt_Test {
    static MyApp myApp;

    public product_mgt_Test(MyApp myApp) {
        super();
        this.myApp = myApp;
    }
    @Given("logged in to the system as owner or supplier")
    public void loggedInToTheSystemAsOwnerOrSupplier() {

    }

    @When("choosing product management from the list")
    public void choosingProductManagementFromTheList() {

    }

    @When("choosing Add new product")
    public void choosingAddNewProduct() {

    }

    @When("the owner should add valid product information")
    public void theOwnerShouldAddValidProductInformation() {

    }

    @Then("the product should be added successfully")
    public void theProductShouldBeAddedSuccessfully() {

    }

    @When("choosing update product")
    public void choosingUpdateProduct() {

    }

    @When("list of available products will appear")
    public void listOfAvailableProductsWillAppear() {

    }

    @Then("the owner or supplier will update product")
    public void theOwnerOrSupplierWillUpdateProduct() {

    }

    @Then("updated product will be saved successfully")
    public void updatedProductWillBeSavedSuccessfully() {

    }

    @When("choosing Remove product")
    public void choosingRemoveProduct() {

    }

    @Then("the owner or supplier will remove product")
    public void theOwnerOrSupplierWillRemoveProduct() {

    }

    @Then("the product will be removed successfully")
    public void theProductWillBeRemovedSuccessfully() {

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

    }

}
