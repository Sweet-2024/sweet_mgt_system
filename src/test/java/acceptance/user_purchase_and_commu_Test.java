package acceptance;

import Entities.Messaging;
import Entities.Order;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import sweetSys.Checks;
import sweetSys.MyApp;
import sweetSys.Updates;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

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
        String buyerEmail = "user5@yahoo.com";
        String sellerEmail = "s12113763@stu.najah.edu";
        assertTrue(Checks.checkIfEmailAlreadyUsed(buyerEmail));
        assertTrue(Checks.checkIfEmailAlreadyUsed(sellerEmail));

        ArrayList<String> items = new ArrayList<>();
        items.add("donats");
        items.add("cupcake");

        ArrayList<Integer> qty = new ArrayList<>();
        qty.add(3);
        qty.add(2);

        Updates.addNewOrder(new Order(sellerEmail, buyerEmail, LocalDateTime.now(), items, qty));

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
        String senderEmail = "user4@yahoo.com";
        String receiverEmail = "s12112506@stu.najah.edu";

        assertTrue(Checks.checkIfEmailAlreadyUsed(senderEmail));
        assertTrue(Checks.checkIfEmailAlreadyUsed(receiverEmail));

        String msg = "I want to communicate with you";
        Messaging Msg = new Messaging(senderEmail, receiverEmail, msg);

        Updates.addNewMsg(Msg);
        assertTrue(Checks.isMsgInTheSystem(Msg));
    }
}
