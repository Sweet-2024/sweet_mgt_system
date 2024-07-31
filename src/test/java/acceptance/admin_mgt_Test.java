package acceptance;

import io.cucumber.java.en.*;
import sweetSys.MyApp;

public class admin_mgt_Test {

    static MyApp myApp;

    public admin_mgt_Test(MyApp myApp) {
        super();
        this.myApp = myApp;
    }

    @Given("login to the system as administrator")
    public void loginToTheSystemAsAdministrator() {

    }

    @When("admin choose managing accounts from the list")
    public void adminChooseManagingAccountsFromTheList() {

    }

    @Then("list of users, owners, and suppliers information will appear")
    public void listOfUsersOwnersAndSuppliersInformationWillAppear() {

    }

    @Then("admin can edit, add, delete, and update information")
    public void adminCanEditAddDeleteAndUpdateInformation() {

    }

}
