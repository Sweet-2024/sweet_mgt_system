package acceptance;

import main_entities.Order;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sweet_system.Checks;
import sweet_system.Listing;
import sweet_system.MyApp;
import sweet_system.Updates;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class order_mgt_Test {

    static MyApp myApp;

    public order_mgt_Test(MyApp myApp) {
        super();
        this.myApp = myApp;
    }

    @Given("logged in to the system as owner")
    public void loggedInToTheSystemAsOwner() {
        myApp.userType = 2;
        assertTrue(myApp.userType == 2);
    }

    @When("choosing order management from the list")
    public void choosingOrderManagementFromTheList() {

    }

    @When("choosing create an order from suppliers from the list")
    public void choosingCreateAnOrderFromSuppliersFromTheList() {

    }

    @When("choosing the supplier to buy from")
    public void choosingTheSupplierToBuyFrom() {

        assertTrue(Checks.checkIfThereAreSuppliersInDatabase());
    }

    @When("listing the required product")
    public void listingTheRequiredProduct() {
        Listing.listingOfRawMaterialsForSpecificSupplier("s12113763@stu.najah.edu");
        assertTrue(Checks.checkIfThereAreRowMaterialsInDatabase());
    }

    @Then("the order will be saved")
    public void theOrderWillBeSaved() {
        String seller = "s12112506@stu.najah.edu";
        String buyer = "s12113763@stu.najah.edu";
        assertTrue(Checks.checkIfEmailAlreadyUsed(buyer));
        assertTrue(Checks.checkIfEmailAlreadyUsed(seller));

        ArrayList<String> items = new ArrayList<>();
        items.add("Sugar");
        items.add("Flour");

        ArrayList<Integer> qty = new ArrayList<>();
        qty.add(20);
        qty.add(25);

        Listing.listingOfRawMaterials();
        Updates.addNewOrderForRowMaterials(new Order(seller, buyer, LocalDateTime.now(), items, qty));
    }

    @Then("a msg will be sent to the selected supplier to notify him")
    public void aMsgWillBeSentToTheSelectedSupplierToNotifyHim() {

    }

    @When("choosing order messages from the list")
    public void choosingOrderMessagesFromTheList() {

    }

    @Then("list of orders' status and info about each order will appear")
    public void listOfOrdersStatusAndInfoAboutEachOrderWillAppear() {
        assertTrue(Checks.checkIfThereAreOrdersInDatabase());
    }

}
