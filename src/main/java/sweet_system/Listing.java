package sweet_system;

import main_entities.Database;
import main_entities.DatabaseOperationException;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

public class Listing {
    private static final Logger logger = Logger.getLogger(Listing.class.getName());
    private static final String SALED_QTY = "saled_qty";
    private static final String WHOLESALE_PRICE = "wholesale_price";
    private static final String PRICE = "price";
    private static final String QTY_OPTION = "Quantity";
    private static final String WHOLESALE_PRICE_OPTION = "Wholesale Price";
    private static final String PRICE_OPTION = "Price";
    private static final String SALED_QTY_OPTION = "Saled Qty";
    private static final String EX_DATE_OPTION = "Expiration Date";
    private static final String USERNAME = "username";
    private static final String USER_EMAIL = "user_email";
    private static final String PRODUCT_ID = "product_id";
    private static final String PRODUCT_NAME = "product_name";
    private static final String RECIPE_NAME = "recipe_name";
    private static final String ERROR_MESSAGE = "Error during generation: ";
    private static final String LINE = "----------------------------------------------------------------------------------------------------------------------------------";
    private static void logInfo(String message) {
        logger.info(message);
    }
    private static void logError(String message, Exception e) {
        logger.log(Level.SEVERE, message + e.getMessage(), e);
    }
    private static void printProductDetails(int productId, String productName, int price, int wholesalePrice, int quantity, int saledQty, String exDate, String ownerEmail) {
        System.out.printf("%-20s %-20s %-10d %-15d %-10d %-10d %-15s %-30s%n",
                productId, productName, price, wholesalePrice, quantity,
                saledQty, exDate, ownerEmail);
    }
    private static void printRawMaterialDetails(int rmId, String rmName, int price, int wholesalePrice, int quantity, int saledQty, String exDate, String supplierEmail) {
        System.out.printf("%-20s %-20s %-10d %-15d %-10d %-10d %-15s %-20s%n",
                rmId, rmName, price, wholesalePrice, quantity,
                saledQty, exDate, supplierEmail);
    }
    // financial reports :
    private static void printingFinancialReportOfOwnersOrSuppliers(String email,String username) {
        int incomes = 0;
        int outcomes = 0;
        int numOfProducts = 0;
        String qry3 = "select price , wholesale_price , saled_qty from sweetsystem.product where owner_email = '" + email + "'";

        try {
            ResultSet productsOfOwnerList = Database.connectionToSelectFromDB(qry3);
            while (productsOfOwnerList.next()) {
                int price = productsOfOwnerList.getInt(PRICE);
                int wholesalePrice = productsOfOwnerList.getInt(WHOLESALE_PRICE);
                int saledQty = productsOfOwnerList.getInt(SALED_QTY);
                incomes += price * saledQty;
                outcomes += wholesalePrice * saledQty;
                numOfProducts++;
            }
            if (numOfProducts > 0)
            {
                int total = incomes - outcomes;
                logger.info(String.format("\t* Product Owner Email : %s", email));
                logger.info(String.format("\t\tIncomes : %s", incomes));
                logger.info(String.format("\t\tOutcomes : %s", outcomes));
                logger.info(String.format("\t\tTotal profit : %s", total));
                logger.info("");
            }

        } catch (SQLException | DatabaseOperationException e) {
            logError(ERROR_MESSAGE, e);
        }
    }

    public static boolean generateFinancialReports() {
        String ownerEmail;
        String qry1 = "select count(product_id) from sweetsystem.product";

        try {
            ResultSet rs = Database.connectionToSelectFromDB(qry1);
            if (rs != null) {
                //if the user is owner or supplier then printing only his won report
                if (MyApp.userType == 2 || MyApp.userType == 3) {
                    logInfo("FINANCIAL REPORT OF YOUR STORE :");
                    ownerEmail = MyApp.userEmail;
                    printingFinancialReportOfOwnersOrSuppliers(ownerEmail , MyApp.userName);
                }
                //if the user is admin then printing reports for all owners and suppliers in the system
                else if (MyApp.userType == 1) {
                    logInfo("FINANCIAL REPORT OF EACH STORE :");
                    String qry2 = "select * from sweetsystem.users where user_type = 2 or user_type = 3";
                    ResultSet ownersAndSuppliersList = Database.connectionToSelectFromDB(qry2);
                    while (ownersAndSuppliersList.next()) {
                        String username = ownersAndSuppliersList.getString(USERNAME);
                        ownerEmail = ownersAndSuppliersList.getString(USER_EMAIL);
                        printingFinancialReportOfOwnersOrSuppliers(ownerEmail,username);
                    }//end of while to get all owners and suppliers in the system
                } else
                    return false; //invalid userType
                return true;
            }// if there are products in the db
            else {
                logInfo("there is no product in the system!");
                return false;
            }// no products in the database
        } catch (SQLException | DatabaseOperationException e) {
            logError(ERROR_MESSAGE, e);
            return false;
        }
    }

    //best-selling items
    private static void printingBestSellingProduct(String email, String username) {
        String qry3 = "select product_name from sweetsystem.product where owner_email = '" + email + "' order by saled_qty desc;";

        try {
            ResultSet bestSelling = Database.connectionToSelectFromDB(qry3);
            if (bestSelling.next()) {
                logger.info(String.format("\t* Product owner email : %s", email));
                logger.info(String.format("\t\tBest selling product : %s", bestSelling.getString(PRODUCT_NAME)));
                logInfo("");
            }
        }catch (SQLException | DatabaseOperationException e) {
            logError(ERROR_MESSAGE, e);
        }

    }

    public static boolean listingBestSellingProduct() {
        String qry1;
        String qry2;
        qry1 = "select count(product_id) from sweetsystem.product";

        try {
            ResultSet rs = Database.connectionToSelectFromDB(qry1);
            if (rs != null) {
                if (MyApp.userType == 2 || MyApp.userType == 3) {
                    logInfo("LIST OF BEST SELLING PRODUCTS IN YOUR STORE :");
                    printingBestSellingProduct(MyApp.userEmail, MyApp.userName);
                    return true;
                } else if (MyApp.userType == 1) {
                    logInfo("LIST OF BEST SELLING PRODUCTS IN EACH STORE :");
                    qry2 = "select * from sweetsystem.users where user_type = 2 or user_type = 3";
                    ResultSet ownersAndSuppliersList = Database.connectionToSelectFromDB(qry2);
                    while (ownersAndSuppliersList.next()) {
                        String email = ownersAndSuppliersList.getString(USER_EMAIL);
                        String username = ownersAndSuppliersList.getString(USERNAME);

                        printingBestSellingProduct(email, username);
                    }
                } else
                    return false;// invalid userType
                return true;
            }// if there are products in the db
            else {
                logInfo("there is no product in the system!");
                return false;
            }
        } catch (SQLException | DatabaseOperationException e) {
            logError(ERROR_MESSAGE, e);
            return false;
        }
    }

    //statistics
    public static boolean statisticsOnUsersByCity() {
        String qry = "select user_location, count(user_email) from sweetsystem.users where user_type = 4 group by user_location";

        try {
            ResultSet rs = Database.connectionToSelectFromDB(qry);
            boolean flag = false;
            logInfo("STATISTICS ON USERS GATHERED BY CITY : ");
            logInfo("City\t\tNumber of users");
            while (rs.next()) {
                System.out.println(rs.getString(1) + "\t\t" + rs.getInt(2));
                flag = true;

            }

            if (flag) {
                logger.info("---------------------------------------------------------------------------------");
                return flag;
            } else {
                logger.warning("No enough data!");
                return flag;
            }
        } catch (SQLException | DatabaseOperationException e) {
            logError(ERROR_MESSAGE, e);
            return false;
        }
    }

    public static boolean listAllUsersInTheSystem(int typeToCommunicate)
    {
        String qry = "select * from sweetsystem.users where user_type = " + typeToCommunicate + ";";

        try {
            ResultSet rs = Database.connectionToSelectFromDB(qry);
            if (typeToCommunicate == 2)
                logInfo("\n* List of all owners in the system :");
            else if (typeToCommunicate == 3)
                logInfo("\n* List of all suppliers in the system :");
            else if (typeToCommunicate == 4)
                logInfo("\n* List of all users in the system :");
            else{
                logInfo("\n* Invalid user type specified.");
                return false;
            }

            System.out.printf("%-15s %-30s %-30s%n", "User Name", "User Email", "User Location");
            System.out.println("--------------------------------------------------------------");

            int numOfUsers = 0;
            while (rs.next()) {
                numOfUsers++;
                String userName = rs.getString(USERNAME);
                String userEmail = rs.getString(USER_EMAIL);
                String userLocation = rs.getString("user_location");
                if (userEmail.equals(MyApp.userEmail))
                 continue;
                System.out.printf("%-15s %-30s %-30s%n", userName, userEmail, userLocation);

            }
            System.out.println("--------------------------------------------------------------");

            if (numOfUsers > 0) {
                return true;
            } else {
                logInfo("There are no users of this type in the system.");
                return false;
            }

        } catch (SQLException | DatabaseOperationException e) {
            logError(ERROR_MESSAGE, e);
            return false;
        }
    }
    public static void listingOfRawMaterialsForSpecificSupplier(String email){
        String qry = "select * from sweetsystem.row_material WHERE supplier_email = '"+email+"';";


        try {
            ResultSet rs = Database.connectionToSelectFromDB(qry);
            System.out.printf("%-20s %-20s %-10s %-15s %-10s %-10s %-15s %-20s%n",
                    "raw material id", "raw material Name", PRICE_OPTION, WHOLESALE_PRICE_OPTION, QTY_OPTION,
                    SALED_QTY_OPTION, EX_DATE_OPTION, "Supplier Email");

            System.out.println(LINE);

            while (rs.next()) {
                int rmId = rs.getInt("rm_id");
                String rmName = rs.getString("rm_name");
                int price = rs.getInt("rm_price");
                int wholesalePrice = rs.getInt(WHOLESALE_PRICE);
                int quantity = rs.getInt("qty");
                int saledQty = rs.getInt(SALED_QTY);
                String exDate = rs.getString("expiry_date");
                String supplierEmail = rs.getString("supplier_email");
                printRawMaterialDetails(rmId, rmName, price, wholesalePrice, quantity, saledQty, exDate, supplierEmail);
            }
            System.out.println(LINE);
        }catch (SQLException | DatabaseOperationException e) {
            logError(ERROR_MESSAGE, e);
        }
    }

    public static void listingOfProductsForSpecificOwner(String email) {
        String qry = "SELECT * FROM sweetsystem.product WHERE owner_email = '"+email+"';";


        try {
            ResultSet rs = Database.connectionToSelectFromDB(qry);
            System.out.printf("%-20s %-20s %-10s %-15s %-10s %-10s %-20s %-30s%n",
                    "Product id", "Product Name", PRICE_OPTION, WHOLESALE_PRICE_OPTION, QTY_OPTION,
                    SALED_QTY_OPTION, EX_DATE_OPTION, "Owner Email");

            System.out.println(LINE);
            while (rs.next()) {
                int productId = rs.getInt(PRODUCT_ID);
                String productName = rs.getString(PRODUCT_NAME);
                int price = rs.getInt(PRICE);
                int wholesalePrice = rs.getInt(WHOLESALE_PRICE);
                int quantity = rs.getInt("quantity");
                int saledQty = rs.getInt(SALED_QTY);
                String exDate = rs.getString("ex_date");
                String ownerEmail = rs.getString("owner_email");

                printProductDetails(productId, productName, price, wholesalePrice, quantity, saledQty, exDate, ownerEmail);
            }
            System.out.println(LINE);
        } catch (SQLException | DatabaseOperationException e) {
            logError(ERROR_MESSAGE, e);
        }
    }
    public static void listingOfProducts() {
        String qry = "SELECT * FROM sweetsystem.product;";

        try {
            ResultSet rs = Database.connectionToSelectFromDB(qry);
            System.out.printf("%-20s %-20s %-10s %-15s %-10s %-10s %-20s %-30s%n",
                    "Product id", "Product Name", PRICE_OPTION, WHOLESALE_PRICE_OPTION, QTY_OPTION,
                    SALED_QTY_OPTION, EX_DATE_OPTION, "Owner Email");

            System.out.println(LINE);
            while (rs.next()) {
                int productId = rs.getInt(PRODUCT_ID);
                String productName = rs.getString(PRODUCT_NAME);
                int price = rs.getInt(PRICE);
                int wholesalePrice = rs.getInt(WHOLESALE_PRICE);
                int quantity = rs.getInt("quantity");
                int saledQty = rs.getInt(SALED_QTY);
                String exDate = rs.getString("ex_date");
                String ownerEmail = rs.getString("owner_email");

                printProductDetails(productId, productName, price, wholesalePrice, quantity, saledQty, exDate, ownerEmail);
            }
            System.out.println(LINE);
        }catch (SQLException | DatabaseOperationException e) {
            logError(ERROR_MESSAGE, e);
        }
    }

    public static void printingRecipeAccordingToRecipeName(String rName)
    {
        String recipeName = rName.trim();
        String qry = "select * from sweetsystem.recipe where recipe_name = '"+recipeName+"';";
        int numOfRecipes = 0;
        try {
            ResultSet rs = Database.connectionToSelectFromDB(qry);
            while (rs.next())
            {
                numOfRecipes++;
                logInfo("\tRecipe ID : " + rs.getInt("recipe_id"));
                logInfo("\tRecipe Name : " + rs.getString(RECIPE_NAME));
                logInfo("\tRecipe Description : " + rs.getString("recipe_description"));
                logInfo("\tRecipe Category : " + rs.getString("recipe_category"));
                logInfo("\tRecipe Publisher Email : " + rs.getString("recipe_publisher_email"));
                System.out.println("------------------------------------------------------------------------------------");
            }if(numOfRecipes == 0)
                logInfo("There is no recipes in the system!");
        } catch (SQLException | DatabaseOperationException e) {
            logError(ERROR_MESSAGE, e);
        }
    }
    public static List<Integer> listRecipesInDb()
    {
        String qry = "select * from sweetsystem.recipe;";

        List<Integer> recipesID = new ArrayList<>();
        int numOfRecipes = 0;
        try {
            ResultSet rs = Database.connectionToSelectFromDB(qry);
            while(rs.next())
            {
                numOfRecipes++;
                printingRecipeAccordingToRecipeName(rs.getString(RECIPE_NAME));
                recipesID.add(rs.getInt("recipe_id"));
            }
            if(numOfRecipes == 0)
                logInfo("There is no recipes in the system!");
        }catch (SQLException | DatabaseOperationException e) {
            logError(ERROR_MESSAGE, e);
        }
        return recipesID;
    }

    public static void listRecipesInDbAccordingToCategory(String category)
    {
        String qry = "select * from sweetsystem.recipe where recipe.recipe_category = '"+category+"';";

        int numOfRecipes = 0;
        try {
            ResultSet rs = Database.connectionToSelectFromDB(qry);
            while(rs.next())
            {
                logInfo("\tRecipe Name : " + rs.getString(RECIPE_NAME));
                logInfo("\tRecipe Description : " + rs.getString("recipe_description"));
                logInfo("\tRecipe Publisher Email : " + rs.getString("recipe_publisher_email"));
                System.out.println("------------------------------------------------------------------------------------");
                numOfRecipes++;
            }
            if(numOfRecipes == 0)
                logInfo("There is no recipes from this category!");
        }catch (SQLException | DatabaseOperationException e) {
            logError(ERROR_MESSAGE, e);
        }
    }
    public static List<Integer> ordersMadeByThisUser(String userEmail)
    {
        List<Integer> ordersID = new ArrayList<>();
        String qry = "select * from `order` where buyer_email = '"+userEmail+"';";

        try {
            ResultSet rs = Database.connectionToSelectFromDB(qry);
            int numOfOrders = 0;
            if(rs != null){
                while (rs.next()) {
                    logInfo("OrderId : " + rs.getInt("order_id"));
                    logInfo("Ordering Date : " + rs.getDate("order_date"));
                    logInfo("Seller Email : " + rs.getString("seller_email"));
                    ordersID.add(rs.getInt("order_id"));
                    logInfo("\n");
                    numOfOrders++;
                }
            }
            if (numOfOrders == 0)
                logInfo("You didn't make any order before!");
            return ordersID;
        }catch (SQLException | DatabaseOperationException e) {
            logError(ERROR_MESSAGE, e);
        }
        return ordersID;
    }

    public static List<Integer> productsInTheOrder(int orderID)
    {
        List<Integer> productsId = new ArrayList<>();
        String qry = "SELECT * from `product`,`order_product` WHERE `order_product`.`product_id` = `product`.`product_id` and `order_product`.`order_id` = " + orderID;


        try {
            ResultSet rs = Database.connectionToSelectFromDB(qry);
            System.out.printf("%-20s %-20s", "Product id", "Product Name");
            System.out.println("\n-------------------------------------------------");
            while (rs.next())
            {
                System.out.printf("%-20s", rs.getInt(PRODUCT_ID));
                System.out.printf("%-20s", rs.getString(PRODUCT_NAME));
                System.out.println();
                productsId.add(rs.getInt(PRODUCT_ID));
            }
            System.out.println("-------------------------------------------------");
        }catch (SQLException | DatabaseOperationException e) {
            logError(ERROR_MESSAGE, e);
        }
        return productsId;
    }
    public static void listingAllMsgsSentToUser(String receiverEmail)
    {
        String qry = "select * from message where receiver = '"+receiverEmail+"' order by date DESC";

        String sender;
        String msg;
        Date date;
        int numOfMsg = 0;
        try {
            ResultSet rs = Database.connectionToSelectFromDB(qry);
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
                logInfo("No New Messages!");
        } catch (SQLException | DatabaseOperationException e) {
            logError(ERROR_MESSAGE, e);
        }
    }

    public static void listingYourOwnAccount(String email) {
        String qry = "SELECT * FROM sweetsystem.users WHERE users.user_email = '" + email + "';";
        try {
            ResultSet rs = Database.connectionToSelectFromDB(qry);
            if (rs != null) {
                logInfo("Your Account Information:");
                logInfo(" Name: " + rs.getString(USERNAME));
                logInfo(" Password: " + rs.getString("user_password"));
                logInfo(" Email: " + rs.getString(USER_EMAIL));
                logInfo(" Location: " + rs.getString("user_location"));
                logInfo(" Type: " + rs.getString("user_type"));
            } else {
                logInfo("No account found with the provided email.");
            }
        }catch (SQLException | DatabaseOperationException e) {
            logError(ERROR_MESSAGE, e);
        }
    }

    public static void listingOfRawMaterials() {
        String qry = "select * from sweetsystem.row_material;";


        try {
            ResultSet rs = Database.connectionToSelectFromDB(qry);
            System.out.printf("%-20s %-20s %-10s %-15s %-10s %-10s %-15s %-20s%n",
                    "raw material id", "raw material Name", PRICE_OPTION, WHOLESALE_PRICE_OPTION, QTY_OPTION,
                    SALED_QTY_OPTION, EX_DATE_OPTION, "Supplier Email");

            System.out.println(LINE);
            while (rs.next()) {
                int rmId = rs.getInt("rm_id");
                String rmName = rs.getString("rm_name");
                int price = rs.getInt("rm_price");
                int wholesalePrice = rs.getInt(WHOLESALE_PRICE);
                int quantity = rs.getInt("qty");
                int saledQty = rs.getInt(SALED_QTY);
                String exDate = rs.getString("expiry_date");
                String supplierEmail = rs.getString("supplier_email");

                printRawMaterialDetails(rmId, rmName, price, wholesalePrice, quantity, saledQty, exDate, supplierEmail);
            }
            logger.info(LINE);
        } catch (SQLException | DatabaseOperationException e) {
            logError(ERROR_MESSAGE, e);
        }
    }

}

