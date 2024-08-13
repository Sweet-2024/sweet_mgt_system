package sweetSys;

import Entities.Database;

import java.security.PublicKey;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Listing {

    // financial reports :
    private static void printingFinancialReportOfOwnersOrSuppliers(String email) {
        int incomes = 0, outcomes = 0;
        String qry3 = "select price , wholesale_price , saled_qty from sweetsystem.product where owner_email = '" + email + "'";
        ResultSet ProductsOfOwnerList = Database.connectionToSelectFromDB(qry3);
        try {
            while (ProductsOfOwnerList.next()) {
                int price = ProductsOfOwnerList.getInt("price");
                int wholesale_price = ProductsOfOwnerList.getInt("wholesale_price");
                int saledQty = ProductsOfOwnerList.getInt("saled_qty");
                incomes += price * saledQty;
                outcomes += wholesale_price * saledQty;
            }
            int total = incomes - outcomes;
            System.out.println("\t\tIncomes : " + incomes);
            System.out.println("\t\tOutcomes : " + outcomes);
            System.out.println("\t\tTotal profit : " + total);
            System.out.println();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static boolean generateFinancialReports() {
        String ownerEmail;
        String qry1 = "select count(product_id) from sweetsystem.product";
        ResultSet rs = Database.connectionToSelectFromDB(qry1);
        try {
            if (rs.next() && rs.getInt(1) > 0) {
                //if the user is owner or supplier then printing only his won report
                if (MyApp.userType == 2 || MyApp.userType == 3) {
                    System.out.println("FINANCIAL REPORT OF YOUR STORE :");
                    ownerEmail = MyApp.userEmail;
                    printingFinancialReportOfOwnersOrSuppliers(ownerEmail);
                }
                //if the user is admin then printing reports for all owners and suppliers in the system
                else if (MyApp.userType == 1) {
                    System.out.println("FINANCIAL REPORT OF EACH STORE :");
                    String qry2 = "select * from sweetsystem.users where user_type = 2 or user_type = 3";
                    ResultSet ownersAndSuppliersList = Database.connectionToSelectFromDB(qry2);
                    while (ownersAndSuppliersList.next()) {
                        System.out.println("\t* Product Owner Name : " + ownersAndSuppliersList.getString("username"));
                        ownerEmail = ownersAndSuppliersList.getString("user_email");
                        printingFinancialReportOfOwnersOrSuppliers(ownerEmail);
                    }//end of while to get all owners and suppliers in the system
                } else
                    return false; //invalid userType
                return true;
            }// if there are products in the db
            else {
                System.out.println("there is no product in the system!");
                return false;
            }// no products in the database
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    //best-selling items
    private static void printingBestSellingProduct(String email) {
        String qry3 = "select product_name from sweetsystem.product where owner_email = '" + email + "' order by saled_qty desc;";
        ResultSet bestSelling = Database.connectionToSelectFromDB(qry3);
        try {
            if (bestSelling.next()) {

                System.out.println("\t\tBest selling product : " + bestSelling.getString("product_name"));
                System.out.println();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static boolean listingBestSellingProduct() {
        String qry1, qry2;
        qry1 = "select count(product_id) from sweetsystem.product";
        ResultSet rs = Database.connectionToSelectFromDB(qry1);
        try {
            if (rs.next() && rs.getInt(1) > 0) {
                if (MyApp.userType == 2 || MyApp.userType == 3) {
                    System.out.println("LIST OF BEST SELLING PRODUCTS IN YOUR STORE :");
                    printingBestSellingProduct(MyApp.userEmail);
                    return true;
                } else if (MyApp.userType == 1) {
                    System.out.println("LIST OF BEST SELLING PRODUCTS IN EACH STORE :");
                    qry2 = "select * from sweetsystem.users where user_type = 2 or user_type = 3";
                    ResultSet ownersAndSuppliersList = Database.connectionToSelectFromDB(qry2);
                    while (ownersAndSuppliersList.next()) {
                        String email = ownersAndSuppliersList.getString("user_email");
                        System.out.println("\t* Product owner name : " + ownersAndSuppliersList.getString("username"));
                        printingBestSellingProduct(email);
                    }
                } else
                    return false;// invalid userType
                return true;
            }// if there are products in the db
            else {
                System.out.println("there is no product in the system!");
                return false;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    //statistics
    public static boolean statisticsOnUsersByCity() {
        String qry = "select user_location, count(user_email) from sweetsystem.users where user_type = 4 group by user_location";
        ResultSet rs = Database.connectionToSelectFromDB(qry);
        try {
            boolean flag = false;
            System.out.println("STATISTICS ON USERS GATHERED BY CITY : ");
            System.out.println("City\t\tNumber of users");
            while (rs.next()) {
                System.out.println(rs.getString(1) + "\t\t" + rs.getInt(2));
                flag = true;
            }

            if (flag) {
                System.out.println("---------------------------------------------------------------------------------");
                return flag;
            } else {
                System.err.println("No enough data!");
                return flag;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public static boolean listAllUsersInTheSystem(int TypeToCommunicate)
    {
        String qry = "select * from sweetsystem.users where user_type = " + TypeToCommunicate;
        ResultSet rs = Database.connectionToSelectFromDB(qry);
        try {
            if (TypeToCommunicate == 2)
                System.out.println("\n* List of all owners in the system :");
            else if (TypeToCommunicate == 3)
                System.out.println("\n* List of all suppliers in the system :");
            else if (TypeToCommunicate == 4)
                System.out.println("\n* List of all users in the system :");

            System.out.println("\t\tusername : \t\temail : ");
            int numOfUsers = 0;
            while (rs.next()) {
                numOfUsers++;
                String username = rs.getString(1);
                String email = rs.getString(3);
                System.out.println("\t" + numOfUsers + ".\t" + username + "\t\t" + email);
            }
            if (numOfUsers > 0)
                return true;
            else {
                System.out.println("there are no users in the system");
                return false;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }
    public static void listingOfRawMaterials(){
        String qry = "select * from sweetsystem.row_material;";
        ResultSet rs = Database.connectionToSelectFromDB(qry);

        try {
            System.out.printf("%-20s %-20s %-10s %-15s %-10s %-10s %-15s %-20s%n",
                    "raw material id", "raw material Name", "Price", "Wholesale Price", "Quantity",
                    "Saled Qty", "Expiration Date", "Supplier Email");

            System.out.println("----------------------------------------------------------------------------------------------------------------------------------");

            while (rs.next()) {
                int rmId = rs.getInt("rm_id");
                String rmName = rs.getString("rm_name");
                int price = rs.getInt("rm_price");
                int wholesalePrice = rs.getInt("wholesale_price");
                int quantity = rs.getInt("qty");
                int saledQty = rs.getInt("saled_qty");
                String exDate = rs.getString("expiry_date");
                String supplierEmail = rs.getString("supplier_email");

                System.out.printf("%-20s %-20s %-10d %-15d %-10d %-10d %-15s %-20s%n",
                        rmId, rmName, price, wholesalePrice, quantity,
                        saledQty, exDate, supplierEmail);
            }
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void listingOfProducts() {
        String qry = "SELECT * FROM sweetsystem.product;";
        ResultSet rs = Database.connectionToSelectFromDB(qry);

        try {
            System.out.printf("%-20s %-20s %-10s %-15s %-10s %-10s %-20s %-30s%n",
                    "Product id", "Product Name", "Price", "Wholesale Price", "Quantity",
                    "Saled Qty", "Expiration Date", "Owner Email");

            System.out.println("----------------------------------------------------------------------------------------------------------------------------------");

            while (rs.next()) {
                int productId = rs.getInt("product_id");
                String productName = rs.getString("product_name");
                int price = rs.getInt("price");
                int wholesalePrice = rs.getInt("wholesale_price");
                int quantity = rs.getInt("quantity");
                int saledQty = rs.getInt("saled_qty");
                String exDate = rs.getString("ex_date");
                String ownerEmail = rs.getString("owner_email");

                System.out.printf("%-20s %-20s %-10d %-15d %-10d %-10d %-15s %-30s%n",
                        productId, productName, price, wholesalePrice, quantity,
                        saledQty, exDate, ownerEmail);
            }
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void printingRecipeAccordingToRecipeName(String rName)
    {
        String recipeName = rName.trim();
        String qry = "select * from sweetsystem.recipe where recipe_name = '"+recipeName+"';";
        ResultSet rs = Database.connectionToSelectFromDB(qry);
        int numOfRecipes = 0;
        try {
            while (rs.next())
            {
                numOfRecipes++;
                System.out.println("\tRecipe Name : " + rs.getString("recipe_name"));
                System.out.println("\tRecipe Description : " + rs.getString("recipe_description"));
                System.out.println("\tRecipe Category : " + rs.getString("recipe_category"));
                System.out.println("\tRecipe Publisher Email : " + rs.getString("recipe_publisher_email"));
                System.out.println("------------------------------------------------------------------------------------");
            }
            if(numOfRecipes == 0)
                System.out.println("There is no recipes in the system!");
        }
        catch (SQLException e)
        {

        }
    }
    public static void ListRecipesInDb()
    {
        String qry = "select * from sweetsystem.recipe;";
        ResultSet rs = Database.connectionToSelectFromDB(qry);
        int numOfRecipes = 0;
        try {
            while(rs.next())
            {
                numOfRecipes++;
                System.out.println("\t" + numOfRecipes + ".");
                printingRecipeAccordingToRecipeName(rs.getString("recipe_name"));
            }
            if(numOfRecipes == 0)
                System.out.println("There is no recipes in the system!");
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
    public static void ListRecipesInDbAccordingToCategory(String category)
    {
        String qry = "select * from sweetsystem.recipe where recipe.recipe_category = '"+category+"';";
        ResultSet rs = Database.connectionToSelectFromDB(qry);
        int numOfRecipes = 0;
        try {
            while(rs.next())
            {
                System.out.println("\tRecipe Name : " + rs.getString("recipe_name"));
                System.out.println("\tRecipe Description : " + rs.getString("recipe_description"));
                System.out.println("\tRecipe Publisher Email : " + rs.getString("recipe_publisher_email"));
                System.out.println("------------------------------------------------------------------------------------");
                numOfRecipes++;
            }
            if(numOfRecipes == 0)
                System.out.println("There is no recipes from this category!");
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
}

