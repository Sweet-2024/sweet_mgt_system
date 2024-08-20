package sweetSys;

import Entities.*;

import java.sql.DriverManager;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalDateTime;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Character.isAlphabetic;
import static java.lang.Character.isDigit;

public class Checks {



    private static boolean checkIfInDB(String qry)
    {
        String inQRY = qry;
        try
        {
            ResultSet rs = Database.connectionToSelectFromDB(qry);
            if (rs != null)
                return true;
            else
                return false;
        }
        catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
    public static boolean checkIfEmailAlreadyUsed(String email)
    {
        String qry = "select count(user_email) from sweetSystem.users where users.user_email = '" + email + "'";
        return checkIfInDB(qry);
    }

    public static boolean checkIfUserInDatabase (String email, String pass)
    {
        String qry = "select * from sweetsystem.users where users.user_email = '"+email+"' and users.user_password = '"+pass+"';";
        return checkIfInDB(qry);
    }
    public static boolean checkIfThereAreUsersInDatabase()
    {
        String qry = "SELECT * FROM sweetsystem.users WHERE user_type = 2 OR user_type = 3 OR user_type = 4;";
        return checkIfInDB(qry);
    }
    public static boolean checkIfThereAreSuppliersInDatabase()
    {
        String qry = "SELECT * FROM sweetsystem.users WHERE user_type = 3;";
        return checkIfInDB(qry);
    }
    public static boolean checkIfProductInDatabase(String name)
    {
        String qry = "select * from sweetsystem.Product where Product.product_name = '"+name+"';";
        return checkIfInDB(qry);
    }
    public static boolean checkIfThereAreProductsInDatabase()
    {
        String qry = "select * from sweetsystem.Product;";
        return checkIfInDB(qry);
    }
    public static boolean checkIfThereAreRowMaterialsInDatabase()
    {
        String qry = "select * from sweetsystem.row_material;";
        return checkIfInDB(qry);
    }
    public static boolean checkIfThereAreRecipesInDatabase()
    {
        String qry = "select * from sweetsystem.recipe;";
        return checkIfInDB(qry);
    }
    public static boolean checkIfRecipeInDatabase(String name)
    {
        String qry = "select * from sweetsystem.recipe where recipe.recipe_name = '"+name+"';";
        return checkIfInDB(qry);
    }
    public static boolean checkIfRecipesInDbAccordingToCategory(String category)
    {
        String qry = "select * from sweetsystem.recipe where recipe.recipe_category = '"+category+"';";
        return checkIfInDB(qry);
    }
    public static boolean isValidProductName(String productName)
    {
        if (productName == null) return false;
        return (productName.length() <= 50);
    }
      
    public static boolean isValidEmail(String email)
    {
        if (email == null)
            return false;
        else if(!email.contains("@"))
            return false;
        else if (email.length() > 50)
            return false;
        String domain = email.substring(email.indexOf("@")+1);

        if(!domain.contains("."))
            return false;

        return true;
    }

    public static boolean isValidUsername(String username)
    {
        if (username == null) return false;
        return (username.length() <= 20);
    }

    public static boolean isvalidPassword(String pass)
    {
        if(pass == null)
            return false;
        if(pass.length() < 8)
            return false;
        else
        {
            boolean alphFlag = false;
            boolean digitFlag = false;
            boolean charFlag = false;
            Character currentChar;
            for (int i = 0 ; i < pass.length()-1 ; i++)
            {
                currentChar = pass.charAt(i);
                if (isAlphabetic(currentChar))
                    alphFlag = true;
                else if (isDigit(currentChar))
                    digitFlag = true;
                else if (!isAlphabetic(currentChar) && !isDigit(currentChar))
                    charFlag = true;
            }
            return (alphFlag && digitFlag && charFlag);
        }
    }

    public static boolean isValidCity(String city)
    {
        if (city == null)
            return false;
        ArrayList<String> CitiesAL = new ArrayList<>();
        CitiesAL.add("Gaza");
        CitiesAL.add("Nablus");
        CitiesAL.add("Ramallah");
        CitiesAL.add("Jenin");
        CitiesAL.add("Tulkarem");
        CitiesAL.add("Bethlehem");
        CitiesAL.add("Hebron");

        String temp = city.toLowerCase();

        for(String c: CitiesAL) {
            if (temp.trim().equals(c.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isValidUserType(int userType)
    {
        return (userType >= 1 && userType <= 4);
    }


    public static boolean isMsgInTheSystem(Messaging msg)
    {
        String sender = msg.getSenderEmail();
        String receiver = msg.getReceiverEmail();
        String message = msg.getMsg();
        LocalDateTime msgDate = LocalDateTime.now();
        String qry = "select * from sweetsystem.message where sender = '" + sender + "' and receiver = '" + receiver + "' and msg = '" + message + "';";
        return checkIfInDB(qry);
    }

    public static boolean checkIfRowMaterialInDatabase(String name)
    {
        String qry = "select * from sweetsystem.row_material where row_material.rm_name = '"+name+"';";
        return checkIfInDB(qry);
    }


    public static boolean isExistingRecipe(Recipe recipe)
    {
        String rName = recipe.getRecipeName();
        String rDescription = recipe.getRecipeDescription();
        String rPublisher = recipe.getPublisherEmail();
        String qry = "SELECT count(recipe_id) FROM `recipe` WHERE recipe_name = '"+rName+"' and recipe_description = '"+rDescription+"' and recipe_publisher_email = '"+rPublisher+"'";
        return checkIfInDB(qry);
    }

    public static boolean checkIfThereAreOrdersInDatabase() {
        String qry = "select * from sweetsystem.order;";
        return checkIfInDB(qry);
    }
    public static boolean isAcceptableRecipeCategory(String recipeCate)
    {
        if (recipeCate == null)
            return false;
        String temp = recipeCate.toLowerCase().trim();
        return (temp.equals("dietary needs") || temp.equals("food allergies"));
    }

    public static boolean isAcceptableRecipeName(String recipeName)
    {
        return (recipeName != null && recipeName.trim() != "");
    }

    public static boolean isAcceptableRecipeDescription(String recipeDescription)
    {
        return recipeDescription != null && recipeDescription.trim() != "";
    }
    public static boolean checkIfBusinessIdAlreadyUsed(int bId)
    {
        String qry = "select * from sweetsystem.business where business_id = '"+bId+"';";
        return checkIfInDB(qry);
    }
    public static boolean isValidDate(String dateStr)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            LocalDate.parse(dateStr, formatter);
            return true;
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please enter the date in YYYY-MM-DD format.");
            return false;
        }
    }
    public static boolean isValidDiscount(double discount)
    {
        return discount >= 0.0 && discount <= 1.0;
    }

    public static boolean checkIfProductInDbAccordingToId(int id)
    {
        String qry = "select * from sweetsystem.product where product.product_id = "+id+";";
        return checkIfInDB(qry);
    }

    public static boolean checkIfRowMaterialInDbAccordingToID(int rmId)
    {
        String qry = "select * from sweetsystem.row_material where rm_id = "+rmId+";";
        return checkIfInDB(qry);
    }
}//end of class