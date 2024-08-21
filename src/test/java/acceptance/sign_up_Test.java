package acceptance;

import main_entities.User;
import io.cucumber.java.en.*;
import sweet_system.Checks;
import sweet_system.MyApp;
import sweet_system.Updates;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class sign_up_Test {

    static MyApp myApp;
    public sign_up_Test(MyApp myApp) {
        super();
        this.myApp = myApp;
    }

    @Given("trying to sign up to sys")
    public void tryingToSignUpToSys() {

    }

    @When("entering an email already exist in the sys")
    public void enteringAnEmailAlreadyExistInTheSys() {
        String existEmail = "s12112506@stu.najah.edu";
        assertTrue(Checks.checkIfEmailAlreadyUsed(existEmail));

        String newEmail = "ayasawalha2003@yahoo.com";
        assertTrue(Checks.checkIfEmailAlreadyUsed(newEmail));
    }

    @When("entering valid user data")
    public void enteringValidUserData() {
        String un = "raheeqQ";
        String email = "s12113763@stu.najah.edu";
        String password = "raheeq_443";
        String location = "Jenin";
        int userType = 3;

        assertTrue(Checks.isValidUsername(un));
        assertTrue(Checks.isvalidPassword(password));
        assertTrue(Checks.isValidEmail(email));
        assertTrue(Checks.isValidCity(location));
        assertTrue(Checks.isValidUserType(userType));

        User user = new User(un, password, email, location, userType);
        Updates.addNewUser(user);

        assertTrue(Checks.checkIfUserInDatabase(email,password));
    }

    @When("entering an invalid email")
    public void enteringAnInvalidEmail() {
        //invalid email is the email that does not contain '@' or does not contain domain name
        //or top level domain
        String invalidEmail = "s12112506#stu.najah.edu";
        assertFalse(Checks.isValidEmail(invalidEmail));

        invalidEmail = "s12112506@stu?najah?edu";
        assertFalse(Checks.isValidEmail(invalidEmail));

        String validEmail = "s12112506@stu.najah.edu";
        assertTrue(Checks.isValidEmail(validEmail));
    }

    @When("entering a username with more than twenty character")
    public void enteringAUsernameWithMoreThanTwentyCharacter() {
        String longUsername = "aaaabbbbccccddddeeeeffff";
        assertFalse(Checks.isValidUsername(longUsername));

        String shortUsername = "aaaa_bbbb";
        assertTrue(Checks.isValidUsername(shortUsername));
    }

    @When("entering a password with less than eight characters or all characters are from the same type")
    public void enteringAPasswordWithLessThanEightCharactersOrAllCharactersAreFromTheSameType() {
        // valid pass should contain more than 8 char
        //and contains digit, alphabetics and other characters
        String testPass = "short";
        assertFalse(Checks.isvalidPassword(testPass));

        testPass = "aaa222aaa";
        assertFalse(Checks.isvalidPassword(testPass));

        testPass = "aaa@@@aaa";
        assertFalse(Checks.isvalidPassword(testPass));

        testPass = "111+++111";
        assertFalse(Checks.isvalidPassword(testPass));

        testPass = "thisIS_valid78Pass";
        assertTrue(Checks.isvalidPassword(testPass));
    }

    @When("entering a city is not valid")
    public void enteringACityIsNotValid() {
        String city = "any city";
        assertFalse(Checks.isValidCity(city));

        city = "nablus";
        assertTrue(Checks.isValidCity(city));

        city = "JenIN";
        assertTrue(Checks.isValidCity(city));

        city = " ramallah ";
        assertTrue(Checks.isValidCity(city));
    }

    @When("entering an invalid user type")
    public void enteringAnInvalidUserType() {
        int userType = 0;
        assertFalse(Checks.isValidUserType(userType));

        userType = 5;
        assertFalse(Checks.isValidUserType(userType));

        userType = 1;
        assertTrue(Checks.isValidUserType(userType));

        userType = 4;
        assertTrue(Checks.isValidUserType(userType));
    }

    @Then("give a successful sign up msg and go to login")
    public void giveASuccessfulSignUpMsgAndGoToLogin() {
        //gui related
    }

    @Then("give a try again msg")
    public void giveATryAgainMsg() {
        //gui related
    }
}
