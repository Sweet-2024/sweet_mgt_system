package acceptance;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sweetSys.*;
import sweetSys.MyApp;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static sweetSys.Listing.ListRecipesInDbAccordingToCategory;

public class exploration_mgt_Test {

    static MyApp myApp;

    public exploration_mgt_Test(MyApp myApp) {
        super();
        this.myApp = myApp;
    }
    @When("choose Explore recipes from the list")
    public void chooseExploreRecipesFromTheList() {

    }

    @Then("list of dessert recipes exist in the system")
    public void listOfDessertRecipesExistInTheSystem()
    {
        Listing.ListRecipesInDb();
        assertTrue(Checks.checkIfThereAreRecipesInDatabase());
    }

    @When("choose search for recipes from the list")
    public void chooseSearchForRecipesFromTheList() {

    }

    @When("enter recipe name to search for")
    public void enterRecipeNameToSearchFor() {

    }

    @Then("recipe will appear")
    public void recipeWillAppear() {
        String recipeName = "Spaghetti Bolognese";
        assertTrue(Checks.checkIfRecipeInDatabase(recipeName));
    }

    @When("recipe name doesn't exist")
    public void recipeNameDoesnTExist() {
        String recipeName = "Spaghetti with meat";
        assertFalse(!Checks.checkIfRecipeInDatabase(recipeName));
    }

    @When("choose recipes for dietary needs from the list")
    public void chooseRecipesForDietaryNeedsFromTheList() {

    }

    @Then("list of dietary needs recipes will appear with description")
    public void listOfDietaryNeedsRecipesWillAppearWithDescription() {
        String recipeCategory = "dietary needs";
        ListRecipesInDbAccordingToCategory("dietary needs");
        assertTrue(Checks.checkIfRecipesInDbAccordingToCategory(recipeCategory));
    }

    @When("choose recipes for food allergies from the list")
    public void chooseRecipesForFoodAllergiesFromTheList() {

    }

    @Then("list of food allergies recipes will appear with description")
    public void listOfFoodAllergiesRecipesWillAppearWithDescription() {
        String recipeCategory = "food allergies";
        ListRecipesInDbAccordingToCategory("food allergies");
        assertTrue(Checks.checkIfRecipesInDbAccordingToCategory(recipeCategory));
    }

}
