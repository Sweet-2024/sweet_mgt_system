package acceptance;

import main_entities.Messaging;
import io.cucumber.java.en.*;
import sweet_system.Checks;
import sweet_system.Listing;
import sweet_system.MyApp;
import sweet_system.Updates;

import static org.junit.Assert.*;

public class owners_communication_Test {

    static MyApp myApp;

    public owners_communication_Test(MyApp myApp) {
        super();
        owners_communication_Test.myApp = myApp;
    }


    @When("choosing Communication and Notification from the list")
    public void choosingCommunicationAndNotificationFromTheList() {

    }

    @When("choosing communication with users")
    public void choosingCommunicationWithUsers() {

    }

    @When("list of users will appear")
    public void listOfUsersWillAppear() {
        MyApp.userEmail = "s12112506@stu.najah.edu";
        int userTypeToCommunicate = 4;
        assertTrue(Listing.listAllUsersInTheSystem(userTypeToCommunicate));

        userTypeToCommunicate = 5;
        assertFalse(Listing.listAllUsersInTheSystem(userTypeToCommunicate));
    }

    @Then("the owner will communicate with selected user")
    public void theOwnerWillCommunicateWithSelectedUser() {
        String senderEmail = "s12112506@stu.najah.edu";
        String receiverEmail = "s12113763@stu.najah.edu";

        assertTrue(Checks.checkIfEmailAlreadyUsed(senderEmail));
        assertTrue(Checks.checkIfEmailAlreadyUsed(receiverEmail));

        String msg = "I want to communicate with you";
        Messaging messaging = new Messaging(senderEmail, receiverEmail, msg);

        Updates.addNewMsg(messaging);
        assertTrue(Checks.isMsgInTheSystem(messaging));
        Listing.listingAllMsgsSentToUser(receiverEmail);
    }

    @When("choosing communication with suppliers")
    public void choosingCommunicationWithSuppliers() {

    }

    @When("list of suppliers will appear")
    public void listOfSuppliersWillAppear() {
        int userTypeToCommunicate = 3;
        assertTrue(Listing.listAllUsersInTheSystem(userTypeToCommunicate));
    }

    @Then("the owner will communicate with selected supplier")
    public void theOwnerWillCommunicateWithSelectedSupplier() {

    }

    @Given("logged in to the system as supplier")
    public void loggedInToTheSystemAsSupplier() {
        MyApp.userType = 3;
        assertEquals(3, MyApp.userType);
    }

    @When("choosing communication with owners")
    public void choosingCommunicationWithOwners() {

    }

    @When("list of owners will appear")
    public void listOfOwnersWillAppear() {
        int userTypeToCommunicate = 2;
        assertTrue(Listing.listAllUsersInTheSystem(userTypeToCommunicate));
    }

    @Then("the supplier will communicate with selected owner")
    public void theSupplierWillCommunicateWithSelectedOwner() {

    }

}
