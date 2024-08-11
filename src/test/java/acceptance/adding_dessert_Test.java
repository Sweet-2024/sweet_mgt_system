package acceptance;

import Entities.Recipe;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sweetSys.Checks;
import sweetSys.MyApp;
import sweetSys.Updates;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class adding_dessert_Test {

    static MyApp myApp;

    public adding_dessert_Test(MyApp myApp)
    {
        super();
        this.myApp = myApp;
    }

    @When("choose adding new dessert creations from the list")
    public void chooseAddingNewDessertCreationsFromTheList() {

    }

    @When("entering acceptable dessert name")
    public void enteringAcceptableDessertName()
    {
        String recipeName = "any name";
        assertTrue(Checks.isAcceptableRecipeName(recipeName));
    }

    @When("entering dessert data with incorrect format")
    public void enteringDessertDataWithIncorrectFormat()
    {
        String recipeName = null;
        assertFalse(Checks.isAcceptableRecipeName(recipeName));

        String recipeCate = null;
        assertFalse(Checks.isAcceptableRecipeCategory(recipeCate));
    }

    @When("entering acceptable dessert category")
    public void enteringAcceptableDessertCategory()
    {
        String recipeCate = " dietary needs";
        assertTrue(Checks.isAcceptableRecipeCategory(recipeCate));

        recipeCate = "  foOd Allergies ";
        assertTrue(Checks.isAcceptableRecipeCategory(recipeCate));
    }

    @Then("new recipe will be added to the system successfully")
    public void newRecipeWillBeAddedToTheSystemSuccessfully()
    {
        String recipeName = "cupcake";
        assertTrue(Checks.isAcceptableRecipeName(recipeName));

        String recipeCate = " dietary needs ";
        assertTrue(Checks.isAcceptableRecipeCategory(recipeCate));

        String recipeDescription = "this is a recipe for cupcakes";
        assertTrue(Checks.isAcceptableRecipeDescription(recipeDescription));

        String publisherEmail = "s12112506@stu.najah.edu";
        assertTrue(Checks.checkIfEmailAlreadyUsed(publisherEmail));

        Recipe recipe = new Recipe(recipeName, recipeDescription, recipeCate, publisherEmail);

        Updates.addNewRecipe(recipe);
        assertTrue(Checks.isExistingRecipe(recipe));
    }

    @Then("warning msg will be appeared")
    public void warningMsgWillBeAppeared()
    {
        String recipeName = null;
        String recipeCate = null;
        String recipeDescription = null;
        String publisherEmail = "s12112506@stu.najah.edu";


        Recipe recipe = new Recipe(recipeName, recipeDescription, recipeCate, publisherEmail);
        Updates.addNewRecipe(recipe);
    }
}
