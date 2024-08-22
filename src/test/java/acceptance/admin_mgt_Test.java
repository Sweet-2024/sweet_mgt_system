package acceptance;

import main_entities.User;
import io.cucumber.java.en.*;
import sweet_system.Checks;
import sweet_system.Listing;
import sweet_system.MyApp;
import sweet_system.Updates;

import static org.junit.Assert.*;

public class admin_mgt_Test {

    static MyApp myApp;

    public admin_mgt_Test(MyApp myApp) {
        super();
        this.myApp = myApp;
    }

    @Given("login to the system as administrator")
    public void loginToTheSystemAsAdministrator() {
        String email = "admin@gmail.com";
        int uType = User.userTypeByEmail(email);
        assertEquals(uType , 1);
    }

    @When("admin choose managing accounts from the list")
    public void adminChooseManagingAccountsFromTheList() {

    }

    @Then("list of users, owners, and suppliers information will appear")
    public void listOfUsersOwnersAndSuppliersInformationWillAppear() {
        assertTrue(Checks.checkIfThereAreUsersInDatabase());
    }

    @Then("admin can add accounts information")
    public void adminCanAddAccountsInformation() {
        String un = "raheeqQ";
        String email = "s12113763@stu.najah.edu";
        String password = "raheeq_443";
        String location = "jenin";
        int userType = 3;

        User user = new User (un, password, email, location, userType);
        Updates.addNewUser(user);

        assertTrue(Checks.checkIfUserInDatabase(email, password));
        Listing.listAllUsersInTheSystem(userType);
    }

    @Then("admin can delete accounts information")
    public void adminCanDeleteAccountsInformation() {
        String email = "s12113755@stu.najah.edu";
        String password = "123";
        Updates.deleteUser(email);
        assertFalse(Checks.checkIfUserInDatabase(email, password));
    }

    @Then("admin can edit accounts information")
    public void adminCanEditAccountsInformation() {
        String un = "user2";
        String email = "user2@yahoo.com";
        String password = "ali_ali";
        String location = "Gaza";
        int userType = 4;

        User user = new User (un, password, email, location, userType);
        Updates.updateUser(user);

        assertTrue(Checks.checkIfUserInDatabase(email, password));
        Listing.listAllUsersInTheSystem(userType);
    }


}
