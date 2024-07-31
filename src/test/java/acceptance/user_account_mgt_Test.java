package acceptance;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sweetSys.MyApp;

public class user_account_mgt_Test {

    static MyApp myApp;

    public user_account_mgt_Test(MyApp myApp) {
        super();
        this.myApp = myApp;
    }

    @Given("logged in to the system as regular user")
    public void loggedInToTheSystemAsRegularUser() {

    }
    @When("choosing account management from the list")
    public void choosingAccountManagementFromTheList() {

    }

    @When("entering data to be updated in selected format")
    public void enteringDataToBeUpdatedInSelectedFormat() {

    }

    @Then("the account info will be updated in the system")
    public void theAccountInfoWillBeUpdatedInTheSystem() {

    }

    @When("entering data with incorrect format")
    public void enteringDataWithIncorrectFormat() {

    }

    @Then("updating account will be canceled")
    public void updatingAccountWillBeCanceled() {

    }

}
