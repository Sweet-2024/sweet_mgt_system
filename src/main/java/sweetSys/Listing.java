package sweetSys;

import main_entities.Database;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Listing {


    // financial reports :
    private static void printingFinancialReportOfOwnersOrSuppliers(String email,String username) {
        int incomes = 0, outcomes = 0;
        int numOfProducts = 0;
        String qry3 = "select price , wholesale_price , saled_qty from sweetsystem.product where owner_email = '" + email + "'";
        ResultSet ProductsOfOwnerList = Database.connectionToSelectFromDB(qry3);
        try {
            while (ProductsOfOwnerList.next()) {
                int price = ProductsOfOwnerList.getInt("price");
                int wholesale_price = ProductsOfOwnerList.getInt("wholesale_price");
                int saledQty = ProductsOfOwnerList.getInt("saled_qty");
                incomes += price * saledQty;
                outcomes += wholesale_price * saledQty;
                numOfProducts++;
            }
            if (numOfProducts > 0)
            {
                int total = incomes - outcomes;
                System.out.println("\t* Product Owner Email : " + email);
                System.out.println("\t\tIncomes : " + incomes);
                System.out.println("\t\tOutcomes : " + outcomes);
                System.out.println("\t\tTotal profit : " + total);
                System.out.println();
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static boolean generateFinancialReports() {
        String ownerEmail;
        String qry1 = "select count(product_id) from sweetsystem.product";
        ResultSet rs = Database.connectionToSelectFromDB(qry1);
        try {
            if (rs != null) {
                //if the user is owner or supplier then printing only his won report
                if (MyApp.userType == 2 || MyApp.userType == 3) {
                    System.out.println("FINANCIAL REPORT OF YOUR STORE :");
                    ownerEmail = MyApp.userEmail;
                    printingFinancialReportOfOwnersOrSuppliers(ownerEmail , MyApp.userName);
                }
                //if the user is admin then printing reports for all owners and suppliers in the system
                else if (MyApp.userType == 1) {
                    System.out.println("FINANCIAL REPORT OF EACH STORE :");
                    String qry2 = "select * from sweetsystem.users where user_type = 2 or user_type = 3";
                    ResultSet ownersAndSuppliersList = Database.connectionToSelectFromDB(qry2);
                    while (ownersAndSuppliersList.next()) {
                        String username = ownersAndSuppliersList.getString("username");
                        ownerEmail = ownersAndSuppliersList.getString("user_email");
                        printingFinancialReportOfOwnersOrSuppliers(ownerEmail,username);
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
    private static void printingBestSellingProduct(String email, String username) {
        String qry3 = "select product_name from sweetsystem.product where owner_email = '" + email + "' order by saled_qty desc;";
        ResultSet bestSelling = Database.connectionToSelectFromDB(qry3);
        try {
            if (bestSelling.next()) {
                System.out.println("\t* Product owner email : " + email);
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
            if (rs != null) {
                if (MyApp.userType == 2 || MyApp.userType == 3) {
                    System.out.println("LIST OF BEST SELLING PRODUCTS IN YOUR STORE :");
                    printingBestSellingProduct(MyApp.userEmail, MyApp.userName);
                    return true;
                } else if (MyApp.userType == 1) {
                    System.out.println("LIST OF BEST SELLING PRODUCTS IN EACH STORE :");
                    qry2 = "select * from sweetsystem.users where user_type = 2 or user_type = 3";
                    ResultSet ownersAndSuppliersList = Database.connectionToSelectFromDB(qry2);
                    while (ownersAndSuppliersList.next()) {
                        String email = ownersAndSuppliersList.getString("user_email");
                        String username = ownersAndSuppliersList.getString("username");

                        printingBestSellingProduct(email, username);
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

    public static boolean listAllUsersInTheSystem(int TypeToCommunicate) {
        String qry = "select * from sweetsystem.users where user_type = " + TypeToCommunicate + ";";
        ResultSet rs = Database.connectionToSelectFromDB(qry);
        try {
            if (TypeToCommunicate == 2)
                System.out.println("\n* List of all owners in the system :");
            else if (TypeToCommunicate == 3)
                System.out.println("\n* List of all suppliers in the system :");
            else if (TypeToCommunicate == 4)
                System.out.println("\n* List of all users in the system :");
            else{
                System.out.println("\n* Invalid user type specified.");
                return false;
            }

            System.out.printf("%-15s %-30s %-30s%n", "User Name", "User Email", "User Location");
            System.out.println("--------------------------------------------------------------");

            int numOfUsers = 0;
            while (rs.next()) {
                numOfUsers++;
                String userName = rs.getString("username");
                String userEmail = rs.getString("user_email");
                String userLocation = rs.getString("user_location");
                if (userEmail.equals(MyApp.userEmail))
                 continue;
                System.out.printf("%-15s %-30s %-30s%n", userName, userEmail, userLocation);

            }
            System.out.println("--------------------------------------------------------------");

            if (numOfUsers > 0) {
                return true;
            } else {
                System.out.println("There are no users of this type in the system.");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
    public static void listingOfRawMaterialsForSpecificSupplier(String email){
        String qry = "select * from sweetsystem.row_material WHERE supplier_email = '"+email+"';";
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

    public static void listingOfProductsForSpecificOwner(String email) {
        String qry = "SELECT * FROM sweetsystem.product WHERE owner_email = '"+email+"';";
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
                System.out.println("\tRecipe ID : " + rs.getInt("recipe_id"));
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
    public static ArrayList<Integer> ListRecipesInDb()
    {
        String qry = "select * from sweetsystem.recipe;";
        ResultSet rs = Database.connectionToSelectFromDB(qry);
        ArrayList<Integer> recipesID = new ArrayList<>();
        int numOfRecipes = 0;
        try {

            while(rs.next())
            {
                numOfRecipes++;
                printingRecipeAccordingToRecipeName(rs.getString("recipe_name"));
                recipesID.add(rs.getInt("recipe_id"));
            }
            if(numOfRecipes == 0)
                System.out.println("There is no recipes in the system!");
        } catch (SQLException e) {
            System.err.println(e);
        }
        return recipesID;
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
    public static ArrayList<Integer> ordersMadeByThisUser(String userEmail)
    {
        ArrayList<Integer> ordersID = new ArrayList<>();
        String qry = "select * from `order` where buyer_email = '"+userEmail+"';";
        ResultSet rs = Database.connectionToSelectFromDB(qry);
        try {
            int numOfOrders = 0;
            while (rs.next())
            {
                System.out.println("OrderId : " + rs.getInt("order_id"));
                System.out.println("Ordering Date : " + rs.getDate("order_date"));
                System.out.println("Seller Email : " + rs.getString("seller_email"));
                ordersID.add(rs.getInt("order_id"));
                System.out.println();
                numOfOrders++;
            }
            if (numOfOrders == 0)
                System.out.println("You didn't make any order before!");
            return ordersID;
        }
        catch (SQLException e)
        {
            e.getMessage();
        }
        return ordersID;
    }

    public static ArrayList<Integer> productsInTheOrder(int orderID)
    {
        ArrayList<Integer> productsId = new ArrayList<>();
        String qry = "SELECT * from `product`,`order_product` WHERE `order_product`.`product_id` = `product`.`product_id` and `order_product`.`order_id` = " + orderID;
        ResultSet rs = Database.connectionToSelectFromDB(qry);

        try {
            System.out.printf("%-20s %-20s", "Product id", "Product Name");
            System.out.println("\n-------------------------------------------------");
            while (rs.next())
            {
                System.out.printf("%-20s", rs.getInt("product_id"));
                System.out.printf("%-20s", rs.getString("product_name"));
                System.out.println();
                productsId.add(rs.getInt("product_id"));
            }
            System.out.println("-------------------------------------------------");
        }
        catch (SQLException sqlException)
        {
            sqlException.getMessage();
        }
        return productsId;
    }
    public static void listingAllMsgsSentToUser(String receiverEmail)
    {
        String qry = "select * from message where receiver = '"+receiverEmail+"' order by date DESC";
        ResultSet rs = Database.connectionToSelectFromDB(qry);
        String sender, msg;
        Date date;
        int numOfMsg = 0;
        try {
            System.out.printf("%-27s %-50s %-20s", "sender", "msg","date");
            System.out.println("\n-------------------------------------------------------------------------------------------------------");
            while (rs.next())
            {
                sender = rs.getString("sender");
                msg = rs.getString("msg");
                date = rs.getDate("date");
                System.out.printf("%-27s", sender);
                System.out.printf("%-50s", msg);
                System.out.printf("%-20s", date);
                System.out.println();
                numOfMsg ++;
            }
            System.out.println("\n-------------------------------------------------------------------------------------------------------");
            if(numOfMsg == 0)
                System.out.println("No New Messages!");
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

    }

    public static void listingYourOwnAccount(String email) {
        String qry = "SELECT * FROM sweetsystem.users WHERE users.user_email = '" + email + "';";
        ResultSet rs = Database.connectionToSelectFromDB(qry);

        try {
            if (rs != null) {
                System.out.println("Your Account Information:");
                System.out.println(" Name: " + rs.getString("username"));
                System.out.println(" Password: " + rs.getString("user_password"));
                System.out.println(" Email: " + rs.getString("user_email"));
                System.out.println(" Location: " + rs.getString("user_location"));
                System.out.println(" Type: " + rs.getString("user_type"));
            } else {
                System.out.println("No account found with the provided email.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void listingOfRawMaterials() {
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

}

