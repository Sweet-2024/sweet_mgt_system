package acceptance;

import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import sweetSys.MyApp;

public class user_purchase_and_commu_Test {

    static MyApp myApp;

    public user_purchase_and_commu_Test(MyApp myApp) {
        super();
        this.myApp = myApp;
    }
    @When("choose purchase desserts from the list")
    public void choosePurchaseDessertsFromTheList() {

    }

    @When("choosing owner name to buy from the list")
    public void choosingOwnerNameToBuyFromTheList() {

    }

    @When("choosing product name to buy from the selected owner")
    public void choosingProductNameToBuyFromTheSelectedOwner() {

    }

    @Then("the order will be created successfully")
    public void theOrderWillBeCreatedSuccessfully() {

    }

    @Then("a msg will be sent to the owner to notify him")
    public void aMsgWillBeSentToTheOwnerToNotifyHim() {

    }

    @When("choose communication and feedback from the list")
    public void chooseCommunicationAndFeedbackFromTheList() {

    }

    @When("writing msg to the owner")
    public void writingMsgToTheOwner() {

    }

    @When("sending the msg")
    public void sendingTheMsg() {

    }

    @Then("the owner will receive the msg successfully")
    public void theOwnerWillReceiveTheMsgSuccessfully() {

    }
}
