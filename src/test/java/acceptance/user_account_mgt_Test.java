package acceptance;

import Entities.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sweetSys.Checks;
import sweetSys.MyApp;
import sweetSys.Updates;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class user_account_mgt_Test {

    static MyApp myApp;

    public user_account_mgt_Test(MyApp myApp) {
        super();
        this.myApp = myApp;
    }

    @Given("logged in to the system as regular user")
    public void loggedInToTheSystemAsRegularUser() {
        myApp.userType = 4;
        assertTrue(myApp.userType == 4);
    }
    @When("choosing account management from the list")
    public void choosingAccountManagementFromTheList() {

    }

    @When("entering data to be updated in selected format")
    public void enteringDataToBeUpdatedInSelectedFormat() {

    }

    @Then("the account info will be updated in the system")
    public void theAccountInfoWillBeUpdatedInTheSystem() {
        String un = "Ali";
        String email = "s12115055@stu.najah.edu";
        String password = "ali_ali";
        String location = "Gaza";
        int userType = 4;

        User user = new User (un, password, email, location, userType);
        Updates.updateYourOwnAccount(user);

        assertTrue(Checks.checkIfUserInDatabase(email, password));
    }

    @When("entering data with incorrect format")
    public void enteringDataWithIncorrectFormat() {

    }

    @Then("updating account will be canceled")
    public void updatingAccountWillBeCanceled() {
        String un = "AliAliAliAliAliAliAliAliAli";
        assertFalse(Checks.isValidUsername(un));

        String email = "s12115055@stu_najah_edu";
        assertFalse(Checks.isValidEmail(email));

        String password = "ali*";
        assertFalse(Checks.isvalidPassword(password));

        String location = "";
        assertFalse(Checks.isValidCity(location));

        int userType = 10;
        assertFalse(Checks.isValidUserType(userType));
    }

}
