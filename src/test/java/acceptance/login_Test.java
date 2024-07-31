package acceptance;

import Entities.Database;
import Entities.User;
import io.cucumber.java.en.*;
import sweetSys.MyApp;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class login_Test {

    public MyApp myApp;

    public login_Test(MyApp myApp) {
        super();
        this.myApp = myApp;
    }

    @Given("trying to login to sys")
    public void tryingToLoginToSys() {
        assertFalse(myApp.isLoggedIn);
    }

    @When("login with correct email and password")
    public void loginWithCorrectEmailAndPassword() {
        String email = "s12112506@stu.najah.edu";
        String pass = "12345";

        if (Database.checkForUsers(email, pass))
        {
            assertTrue(Database.checkForUsers(email, pass));
            myApp.isLoggedIn = true;
        }
        else
            myApp.isLoggedIn = false;
    }

    @Then("user is now in the system")
    public void userIsNowInTheSystem() {
        assertTrue(myApp.isLoggedIn);
    }



    @When("login with correct email and incorrect password")
    public void loginWithCorrectEmailAndIncorrectPassword() {
        String email = "s12112506@stu.najah.edu";
        String pass = "123456";

        if (!Database.checkForUsers(email, pass))
        {
            assertFalse(Database.checkForUsers(email, pass));
            myApp.isLoggedIn = false;
        }
        else
            myApp.isLoggedIn = true;
    }

    @When("login with incorrect email and correct password")
    public void loginWithIncorrectEmailAndCorrectPassword() {
        String email = "s121125@stu.najah.edu";
        String pass = "123456";

        if (!Database.checkForUsers(email, pass))
        {
            assertFalse(Database.checkForUsers(email, pass));
            myApp.isLoggedIn = false;
        }
        else
            myApp.isLoggedIn = true;
    }

    @Then("given user is not in the system msg will appear")
    public void givenUserIsNotInTheSystemMsgWillAppear() {

    }

    @Then("given wrong password msg will appear")
    public void givenWrongPasswordMsgWillAppear() {

    }

    @Then("given a welcome msg will appear")
    public void givenAWelcomeMsgWillAppear() {

    }

    @Then("go to corresponding page according to user type")
    public void goToCorrespondingPageAccordingToUserType() {

    }
}
