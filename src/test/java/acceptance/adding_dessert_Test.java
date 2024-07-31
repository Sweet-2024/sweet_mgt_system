package acceptance;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sweetSys.MyApp;

public class adding_dessert_Test {

    static MyApp myApp;

    public adding_dessert_Test(MyApp myApp) {
        super();
        this.myApp = myApp;
    }


    @When("choose adding new dessert creations from the list")
    public void chooseAddingNewDessertCreationsFromTheList() {

    }

    @When("entering dessert data to be added in selected format")
    public void enteringDessertDataToBeAddedInSelectedFormat() {

    }

    @Then("new recipe will be added to the system successfully")
    public void newRecipeWillBeAddedToTheSystemSuccessfully() {

    }

    @When("entering dessert data with incorrect format")
    public void enteringDessertDataWithIncorrectFormat() {

    }

    @Then("warning msg will be appeared")
    public void warningMsgWillBeAppeared() {

    }

    @Then("adding recipe will be canceled")
    public void addingRecipeWillBeCanceled() {

    }

}
