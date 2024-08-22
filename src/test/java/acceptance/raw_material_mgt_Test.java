package acceptance;

import main_entities.RawMaterial;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sweet_system.Checks;
import sweet_system.MyApp;
import sweet_system.Updates;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class raw_material_mgt_Test {
    static MyApp myApp;
    public raw_material_mgt_Test(MyApp myApp){
        super();
        this.myApp = myApp;
    }
    @When("choosing raw material management from the list")
    public void choosingRawMaterialManagementFromTheList() {

    }

    @When("choosing Add new raw material")
    public void choosingAddNewRawMaterial() {

    }

    @When("the owner should add valid raw material information")
    public void theOwnerShouldAddValidRawMaterialInformation() {
        String name = "Eggs";
        assertTrue(Checks.isValidProductName(name));

        String validEmail = "s12113763@stu.najah.edu";
        assertTrue(Checks.isValidEmail(validEmail));
    }

    @Then("the raw material should be added successfully")
    public void theRawMaterialShouldBeAddedSuccessfully() {
        String rmName = "olive oil";
        assertTrue(Checks.isValidProductName(rmName));

        int price = 100;
        int wholesalePrice = 60;
        int quantity = 10;
        int saledQty = 6;
        String exDate = "2024-12-12";

        String supplierEmail = "s12113763@stu.najah.edu";
        assertTrue(Checks.isValidEmail(supplierEmail));

        RawMaterial rawMaterial = new RawMaterial(rmName, price, wholesalePrice, quantity, saledQty, exDate, supplierEmail);
        Updates.addNewRawMaterial(rawMaterial);

        assertTrue(Checks.checkIfRowMaterialInDatabase(rmName));

    }

    @When("choosing update raw material")
    public void choosingUpdateRawMaterial() {

    }

    @When("list of available raw materials will appear")
    public void listOfAvailableRawMaterialsWillAppear() {
        assertTrue(Checks.checkIfThereAreRowMaterialsInDatabase());
    }

    @Then("the supplier will update raw material")
    public void theSupplierWillUpdateRawMaterial() {

    }

    @Then("updated raw material will be saved successfully")
    public void updatedRawMaterialWillBeSavedSuccessfully() {
        int id = 6;
        String rmName = "Cocoa Powder";
        assertTrue(Checks.isValidProductName(rmName));

        int price = 100;
        int wholesalePrice = 60;
        int quantity = 70;
        int saledQty = 50;
        String exDate = "2025-1-25";

        String supplierEmail = "s12113763@stu.najah.edu";
        assertTrue(Checks.isValidEmail(supplierEmail));

        RawMaterial rawMaterial = new RawMaterial(id, rmName, price, wholesalePrice, quantity, saledQty, exDate, supplierEmail);
        Updates.updateRawMaterial(rawMaterial);

        rmName = "vanilla Powder";
        rawMaterial = new RawMaterial(id, rmName, price, wholesalePrice, quantity, saledQty, exDate, supplierEmail);
        Updates.updateRawMaterial(rawMaterial);

        assertTrue(Checks.checkIfRowMaterialInDatabase(rmName));
    }

    @When("choosing Remove raw material")
    public void choosingRemoveRawMaterial() {

    }

    @Then("the supplier will remove raw material")
    public void theSupplierWillRemoveRawMaterial() {

    }

    @Then("the raw material will be removed successfully")
    public void theRawMaterialWillBeRemovedSuccessfully() {
        int rmId = 10;

        Updates.deleteRawMaterial(rmId);

        rmId = 100;
        Updates.deleteRawMaterial(rmId);

        assertFalse(!Checks.checkIfProductInDbAccordingToId(rmId));
    }

}
