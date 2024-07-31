package acceptance;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sweetSys.MyApp;

public class order_mgt_Test {

    static MyApp myApp;

    public order_mgt_Test(MyApp myApp) {
        super();
        this.myApp = myApp;
    }

    @Given("logged in to the system as owner")
    public void loggedInToTheSystemAsOwner() {

    }

    @When("choosing order management from the list")
    public void choosingOrderManagementFromTheList() {

    }

    @When("choosing create an order from suppliers from the list")
    public void choosingCreateAnOrderFromSuppliersFromTheList() {

    }

    @When("choosing the supplier to buy from")
    public void choosingTheSupplierToBuyFrom() {

    }

    @When("listing the required product")
    public void listingTheRequiredProduct() {

    }

    @Then("the order will be saved")
    public void theOrderWillBeSaved() {

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
