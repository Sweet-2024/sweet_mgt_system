package acceptance;

import Entities.Order;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sweetSys.Checks;
import sweetSys.MyApp;
import sweetSys.Updates;

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
        assertTrue(Checks.checkIfThereAreRowMaterialsInDatabase());
    }

    @Then("the order will be saved")
    public void theOrderWillBeSaved() {
        String seller = "s12112506@stu.najah.edu";
        String buyer = "s12113763@stu.najah.edu";
        LocalDateTime date = LocalDateTime.now();
        String rowMaterialName = "Sugar";
        ArrayList<String> items = new ArrayList<>();
        items.add(rowMaterialName);

        Order order = new Order(seller, buyer, date, items);
        Updates.addNewOrder(order);
    }

    @Then("a msg will be sent to the selected supplier to notify him")
    public void aMsgWillBeSentToTheSelectedSupplierToNotifyHim() {

    }

    @When("choosing order messages from the list")
    public void choosingOrderMessagesFromTheList() {

    }

    @Then("list of orders' status and info about each order will appear")
    public void listOfOrdersStatusAndInfoAboutEachOrderWillAppear() {

    }

}
