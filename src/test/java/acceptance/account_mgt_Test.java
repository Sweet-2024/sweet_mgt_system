package acceptance;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sweetSys.MyApp;

public class account_mgt_Test {
    static MyApp myApp;

    public account_mgt_Test(MyApp myApp) {
        super();
        this.myApp = myApp;
    }

    @When("choosing business management from the list")
    public void choosingBusinessManagementFromTheList() {

    }

    @Then("the business info will be updated in the system")
    public void theBusinessInfoWillBeUpdatedInTheSystem() {

    }
}
