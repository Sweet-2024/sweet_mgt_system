package sweetSys;

import Entities.Database;
import Entities.Recipe;
import Entities.User;

public class Updates
{
    public static void addNewUser(User user)
    {
        String un = user.getUname();
        String pass = user.getPassword();
        String email = user.getEmail();
        String location = user.getLocation();
        int uType = user.getUserType();

        if(!Checks.checkIfEmailAlreadyUsed(email))
        {
            String qry = "insert into sweetsystem.users values ('" + un + "', '" + pass + "', '" + email + "', '" + location + "', " + uType + ");";
            Database.connectionToInsertOrUpdateDB(qry);
        }
        else
        {
            System.out.println("This email already used, please try again with another email address!");
        }
    }

    public static void addNewRecipe(Recipe recipe)
    {
        String rname = recipe.getRecipeName();
        String rDesc = recipe.getRecipeDescription();
        String rCate = recipe.getRecipeCate();
        String pubEmail = recipe.getPublisherEmail();

        if (Checks.isAcceptableRecipeName(rname) && Checks.isAcceptableRecipeDescription(rDesc) && Checks.isAcceptableRecipeCategory(rCate) && Checks.checkIfEmailAlreadyUsed(pubEmail))
        {
            rCate = rCate.trim();
            String qry = "INSERT INTO sweetsystem.recipe(recipe_name, recipe_description, recipe_category, recipe_publisher_email) VALUES ( '" + rname + "', '" + rDesc + "', '" + rCate + "', '" + pubEmail + "');";
            Database.connectionToInsertOrUpdateDB(qry);
            System.out.println("The recipe published successfully :) ");
        }
        else
            System.out.println("Error in recipe info! try Again ");
    }
}
