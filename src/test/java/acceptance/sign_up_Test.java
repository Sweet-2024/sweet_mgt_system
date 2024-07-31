package acceptance;

import io.cucumber.java.en.*;
import sweetSys.MyApp;

public class sign_up_Test {

    static MyApp myApp;

    public sign_up_Test(MyApp myApp) {
        super();
        this.myApp = myApp;
    }
    @Given("trying to sign up to sys")
    public void tryingToSignUpToSys() {

    }

    @When("entering an email already exsit in the sys")
    public void enteringAnEmailAlreadyExsitInTheSys() {

    }

    @Then("give a try again msg")
    public void giveATryAgainMsg() {

    }

    @When("signing up as admin")
    public void signingUpAsAdmin() {

    }

    @Then("give a successful sign up msg and go to login")
    public void giveASuccessfulSignUpMsgAndGoToLogin() {

    }

    @When("signing up as regular user")
    public void signingUpAsRegularUser() {

    }

    @When("signing up as owner")
    public void signingUpAsOwner() {

    }

    @When("signing up as supplier")
    public void signingUpAsSupplier() {

    }
}
