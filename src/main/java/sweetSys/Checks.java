package sweetSys;

import Entities.Database;
import Entities.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static java.lang.Character.isAlphabetic;
import static java.lang.Character.isDigit;

public class Checks {
    public static boolean checkIfUserInDatabase(String email, String pass)
    {
        String qry = "select * from sweetSystem.users where users.user_email = '"+email+"' and users.user_password = '"+pass+"';";
        ResultSet rs = Database.connectionToSelectFromDB(qry);

        try {
            if(rs.next()){
                return true;
            }
            else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
    public static boolean checkIfEmailAlreadyUsed(String email)
    {
        String qry = "select * from sweetSystem.users where users.user_email = '"+ email +"'";
        ResultSet rs = Database.connectionToSelectFromDB(qry);

        try {
            if(rs.next()){
                return true;
            }
            else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
    public static boolean isValidEmail(String email)
    {
        if (email == null)
            return false;
        else if(!email.contains("@"))
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
        ArrayList <String> CitiesAL = new ArrayList<>();
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

    public static boolean isValidUserType(int userType) {
        return (userType >= 1 && userType <= 4);
    }

    public static boolean isAcceptableRecipeCategory(String recipeCate) {
        if (recipeCate == null)
            return false;
        String temp = recipeCate.toLowerCase();
        return (temp.trim().equals("dietary needs") || temp.trim().equals("food allergies"));

    }

    public static boolean isAcceptableRecipeName(String recipeName) {
        return (recipeName != null);
    }

    public static boolean isAcceptableRecipeDescription(String recipeDescription) {
        return (recipeDescription != null);
    }
}
