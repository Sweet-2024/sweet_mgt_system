package org.example;

import main_entities.*;
import sweet_system.Checks;
import sweet_system.Listing;
import sweet_system.MyApp;
import sweet_system.Updates;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;
import static sweet_system.Listing.*;
import static sweet_system.Updates.*;

import java.util.logging.Logger;
import java.util.logging.Level;

public class Main {
    private static boolean signupFlag;
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    private static final String EXIT_OPTION = "3. Exit";
    private static final String EX2_OPTION = "b. Exit";
    private static final String INVALID_OPTION = "Invalid choice!";
    private static final String CHOOSE_OPTION = "Choose your level:";
    private static final String OWNER_OPTION = "1. Product owner";
    private static final String SUPPLIER_OPTION = "2. Row material supplier";
    private static final String USER_OPTION = "3. Regular User";
    private static final String INVALID_LEVEL_OPTION = "Invalid user level! Try again.";
    private static final String LIST_OPTION = "List of existing products:";
    private static final String INVALID_INPUT_OPTION = "Invalid input! Please enter a numeric value.";
    private static final String PRICE_OPTION = "Enter wholesale price:";
    private static final String QTY_OPTION = "Enter quantity:";
    private static final String SALED_QTY_OPTION = "Enter saled quantity:";
    private static final String DATE_OPTION = "Enter expiration date (YYYY-MM-DD):";
    private static final String INVALID_DATE_OPTION = "Please enter a valid date in the format YYYY-MM-DD:";
    private static final String INVALID_ROW_MATERIAL_OPTION = "Please enter a valid raw material name:";
    private static final String UPDATED_OPTION = "Successfully updated";
    private static final String CITY_OPTION = "\tAvailable cities: Gaza, Nablus, Ramallah, Jenin, Tulkarem, Bethlehem, Hebron.";
    private static final String BACK = "\t2. back";
    private static final String EXIT = "    b. Exit";
    private static final String LIST_OF_ROW_MATERAILS = "List of existing raw materials:";
    private static void logInfo(String message) {
        logger.info(message);
    }
    MyApp myApp;
    Main(MyApp myApp)
    {
        this.myApp = myApp;
    }
    public static void main(String[] args)
    {

        Scanner scanner = new Scanner(System.in);
        String userEmail = "";
        String password = "";
        String username = null;
        String city = null;
        String userChoice;
        String productName;
        String exDate;
        String businessName;
        String businessLocation;
        String materialName;
        String supplierEmail;
        String rawMaterialName;
        String ownerEmail;
        int userType = 0;
        int price = 0;
        int wholesalePrice = 0;
        int quantity = 0;
        int saledQty = 0;
        int productId = 0;
        int rawMaterialId = 0;
        int businessId = 0;
        int userTypeToCommunicate = 0;
        double discount = 0;
        MyApp.userType = 0;
        while (true) {
            MyApp.userEmail = null;
            MyApp.userType = 0;
            userEmail = "";
            password = "";

            logInfo("WELCOME TO OUR SWEET MANAGEMENT SYSTEM");
            logInfo("1. Login");
            logInfo("2. Sign up");
            logInfo(EXIT_OPTION);

            userChoice = scanner.next();
            userChoice = userChoice.trim();


            if (userChoice.equals("1"))
            {
                boolean validEmail = false;
                while (!validEmail)
                {
                    logInfo("Enter your email");
                    userEmail = scanner.next();
                    if (!Checks.checkIfEmailAlreadyUsed(userEmail))
                    {
                        logInfo("Invalid Email! Try again");
                        logInfo("Does not have an account?");
                        logInfo("    a. Enter email again");
                        logInfo("    b. Sign up");
                        userChoice = scanner.next().trim();
                        if (userChoice.equals("b")) {
                            signupFlag = true;
                            break;
                        }
                        else if (!userChoice.equals("a"))
                            logInfo(INVALID_OPTION);
                    }
                    else
                        validEmail = true;
                }//entering email loop

                if(validEmail && !signupFlag)
                {
                    boolean validPass = false;
                    while (!validPass)
                    {
                        logInfo("Enter your password");
                        password = scanner.next();
                        if (!Checks.checkIfUserInDatabase(userEmail, password))
                        {
                            logInfo( "Incorrect Password! Try again");
                            logInfo( "Does not have an account?");
                            logInfo("    a. Enter password again");
                            logInfo( "    b. Sign up");
                            userChoice = scanner.next().trim();
                            if (userChoice.equals("b")) {
                                signupFlag = true;
                                userEmail = null;
                                break;
                            }
                            else if (!userChoice.equals("a"))
                                logInfo( INVALID_OPTION);
                        }//incorrect password
                        else {
                            logInfo("Login successful!");
                            validPass = true;
                        }
                    }//entering password loop
                }
                MyApp.user = new User(userEmail, password);
                MyApp.userEmail = userEmail;
                MyApp.userType = User.userTypeByEmail(userEmail);
                MyApp.isLoggedIn = true;
            } //login if statement

            if(userChoice.equals("2") || signupFlag)
            {
                User u = signUp(userEmail , username, password, city, MyApp.userType);
                Updates.addNewUser(u);
                MyApp.user = u;
            }//end of sign up if statement

            else if (userChoice.equals("3"))
                exit(0);

            else if(!MyApp.isLoggedIn)
            {
                logger.warning(INVALID_OPTION);
                continue;//return to the main menu
            }

            if (MyApp.userType == 1)
            {
                while (true) {
                    logInfo("Welcome Admin! Choose what to do from the list:");
                    logInfo("1. Accounts Management");
                    logInfo("2. Reporting And Monitoring");
                    logInfo( EXIT_OPTION);
                    char uc = scanner.next().charAt(0);

                    if (uc == '1')
                    {
                        while (true) {
                            logInfo( "* Accounts Management:");
                            logInfo( "  a. Add new user");
                            logInfo( "  b. Update existing user information");
                            logInfo( "  c. Delete existing user");
                            logInfo( "  d. Back");

                            uc = scanner.next().charAt(0);
                            if (uc == 'a')
                            {
                                Listing.listAllUsersInTheSystem(2);
                                logInfo( "");

                                Listing.listAllUsersInTheSystem(3);
                                logInfo( "");

                                Listing.listAllUsersInTheSystem(4);
                                logInfo( "");

                                User user = signUp(null, null, null, null, 0);
                                Updates.addNewUser(user);
                                logger.log(Level.INFO, "User added successfully.");
                                logger.log(Level.INFO, "");
                            } // add new user

                            else if (uc == 'b')
                            {
                                Listing.listAllUsersInTheSystem(2);
                                logger.log(Level.INFO, "");

                                Listing.listAllUsersInTheSystem(3);
                                logger.log(Level.INFO, "");

                                Listing.listAllUsersInTheSystem(4);
                                logger.log(Level.INFO, "");

                                String ue = null;
                                boolean userFound = false;
                                while (!userFound)
                                {
                                    logger.log(Level.INFO, "Enter the email of the user you want to update:");
                                    scanner.nextLine();
                                    ue = scanner.nextLine();
                                    if (Checks.checkIfEmailAlreadyUsed(ue))
                                        userFound = true;
                                    else
                                    {
                                        logger.log(Level.WARNING, "User is not in the system! Try again.");
                                        logger.log(Level.INFO, "a. Enter email again");
                                        logger.log(Level.INFO, EX2_OPTION);
                                        userChoice = scanner.nextLine();
                                        if (userChoice.equals("b")) {
                                            break;
                                        }
                                        else if (!userChoice.equals("a"))
                                        {
                                            logger.log(Level.SEVERE, INVALID_OPTION);
                                        }
                                    }
                                }

                                if (userFound)
                                {
                                    User user = signUp(ue, null, null, null, 0);
                                    Updates.updateUser(user);
                                    logger.log(Level.INFO, "User updated successfully.");
                                    logger.log(Level.INFO, "");
                                }
                            }//update user data

                            else if (uc == 'c') {
                                Listing.listAllUsersInTheSystem(2);
                                logger.log(Level.INFO, "");
                                Listing.listAllUsersInTheSystem(3);
                                logger.log(Level.INFO, "");
                                Listing.listAllUsersInTheSystem(4);
                                logger.log(Level.INFO, "");

                                logger.log(Level.INFO, "Enter the email of the user you want to delete:");
                                String ue = null;
                                boolean validEmail = false;

                                while (!validEmail) {
                                    ue = scanner.next();

                                    if (Checks.checkIfEmailAlreadyUsed(ue)) {
                                        validEmail = true;
                                    } else {
                                        logger.log(Level.WARNING, "User is not in the system! Try again.");
                                        logger.log(Level.INFO, "a. Enter email again");
                                        logger.log(Level.INFO, EX2_OPTION);
                                        userChoice = scanner.next();

                                        if (userChoice.equals("b")) {
                                            break;
                                        }
                                    }
                                }

                                if (validEmail) {
                                    Updates.deleteUser(ue);
                                    logger.log(Level.INFO, "Deleted successfully.");
                                }

                                logger.log(Level.INFO, "");
                            }// delete user

                            else if (uc == 'd')
                                break;
                            else
                            {
                                logger.log(Level.SEVERE, "Incorrect choice!");
                            } // incorrect choice
                        }//end of loop to choose from admin mg list
                    }//choosing Account mgmt from the admin list

                    else if (uc == '2')
                    {
                        while (true) {
                            logger.log(Level.INFO, "* Reporting And Monitoring:");
                            logger.log(Level.INFO, "    a. Monitor profits and generate financial reports");
                            logger.log(Level.INFO, "    b. Identify best-selling products in each store");
                            logger.log(Level.INFO, "    c. Gather and display statistics on registered users by City");
                            logger.log(Level.INFO, "    d. Back");

                            uc = scanner.next().charAt(0);

                            if (uc == 'a') {
                                generateFinancialReports();
                            }
                            else if (uc == 'b') {
                                listingBestSellingProduct();
                            }
                            else if (uc == 'c') {
                                statisticsOnUsersByCity();
                            }
                            else if (uc == 'd')
                            {
                                break;
                            } else {
                                logger.log(Level.SEVERE, "Incorrect choice!");
                            }
                        } // end of loop to choose from Reporting And Monitoring list
                    } // choosing Reporting And Monitoring from the admin list

                    else if (uc == '3')
                    {
                        MyApp.userType = 0;
                        break; // to get out of admin area and return to the main list login, sign up, exit..
                    }
                    else
                        logger.log(Level.WARNING, "Invalid choice! Try Again");
                }
            }//logged in as admin

            else if (MyApp.userType == 2) {
                Boolean isCorrectChoice = false;
                while (true) {
                    logInfo("Welcome Owner! choose what to do from the list:");
                    logInfo("1. Product Management");
                    logInfo("2. Communication and Notification");
                    logInfo("3. Accounts Management");
                    logInfo("4. Order Management");
                    logInfo("5. exit");
                    userChoice = scanner.next();

                    userChoice = userChoice.trim();
                    if (userChoice.equals("1")) {
                        while (true) {
                            logInfo("* Product Management:");
                            logInfo("    a. Add new products.");
                            logInfo("    b. update available products.");
                            logInfo("    c. remove available products.");
                            logInfo("    d. Sales and profits.");
                            logInfo("    e. Best-selling products.");
                            logInfo("    f. discount products.");
                            logInfo("    g. Back.");

                            userChoice = scanner.next();
                            if (userChoice.equals("a")) {
                                logInfo(LIST_OPTION);
                                Listing.listingOfProductsForSpecificOwner(MyApp.userEmail);

                                logInfo("Enter product name:");
                                scanner.nextLine();
                                productName = scanner.nextLine();
                                while (!Checks.isValidProductName(productName)) {
                                    logger.warning("Invalid product name. Please enter a valid product name:");
                                    productName = scanner.nextLine();
                                }

                                while (true) {
                                    logInfo("Enter product price:");
                                    if (scanner.hasNextInt()) {
                                        price = scanner.nextInt();
                                        scanner.nextLine();
                                        break;
                                    } else {
                                        logger.warning(INVALID_INPUT_OPTION);
                                        scanner.next();
                                    }
                                }

                                while (true) {
                                    logInfo(PRICE_OPTION);
                                    if (scanner.hasNextInt()) {
                                        wholesalePrice = scanner.nextInt();
                                        scanner.nextLine();
                                        break;
                                    } else {
                                        logger.warning(INVALID_INPUT_OPTION);
                                        scanner.next();
                                    }
                                }

                                while (true) {
                                    logInfo(QTY_OPTION);
                                    if (scanner.hasNextInt()) {
                                        quantity = scanner.nextInt();
                                        scanner.nextLine();
                                        break;
                                    } else {
                                        logger.warning(INVALID_INPUT_OPTION);
                                        scanner.next();
                                    }
                                }

                                while (true) {
                                    logInfo(SALED_QTY_OPTION);
                                    if (scanner.hasNextInt()) {
                                        saledQty = scanner.nextInt();
                                        scanner.nextLine();
                                        break;
                                    } else {
                                        logger.warning(INVALID_INPUT_OPTION);
                                        scanner.next();
                                    }
                                }

                                logInfo(DATE_OPTION);
                                exDate = scanner.nextLine();
                                while (!Checks.isValidDate(exDate)) {
                                    logger.warning(INVALID_DATE_OPTION);
                                    exDate = scanner.nextLine();
                                }

                                MyApp.product = new Product(productName, price, wholesalePrice, quantity, saledQty, exDate, userEmail);
                                addNewProduct(MyApp.product);
                                logInfo("Successfully added");
                            } else if (userChoice.equals("b")) {
                                logInfo(LIST_OPTION);
                                Listing.listingOfProductsForSpecificOwner(MyApp.userEmail);

                                while (true) {
                                    logInfo("Enter product id:");
                                    if (scanner.hasNextInt()) {
                                        productId = scanner.nextInt();
                                        scanner.nextLine();
                                        break;
                                    } else {
                                        logger.warning(INVALID_INPUT_OPTION);
                                        scanner.next();
                                    }
                                }

                                logInfo("Enter product name:");
                                productName = scanner.nextLine();
                                while (!Checks.isValidProductName(productName)) {
                                    logger.warning("Please enter a valid product name:");
                                    productName = scanner.nextLine();
                                }

                                while (true) {
                                    logInfo("Enter product price:");
                                    if (scanner.hasNextInt()) {
                                        price = scanner.nextInt();
                                        scanner.nextLine();
                                        break;
                                    } else {
                                        logger.warning(INVALID_INPUT_OPTION);
                                        scanner.next();
                                    }
                                }

                                while (true) {
                                    logInfo(PRICE_OPTION);
                                    if (scanner.hasNextInt()) {
                                        wholesalePrice = scanner.nextInt();
                                        scanner.nextLine();
                                        break;
                                    } else {
                                        logger.warning(INVALID_INPUT_OPTION);
                                        scanner.next();
                                    }
                                }

                                while (true) {
                                    logInfo(QTY_OPTION);
                                    if (scanner.hasNextInt()) {
                                        quantity = scanner.nextInt();
                                        scanner.nextLine();
                                        break;
                                    } else {
                                        logger.warning(INVALID_INPUT_OPTION);
                                        scanner.next();
                                    }
                                }

                                while (true) {
                                    logInfo(SALED_QTY_OPTION);
                                    if (scanner.hasNextInt()) {
                                        saledQty = scanner.nextInt();
                                        scanner.nextLine();
                                        break;
                                    } else {
                                        logger.warning(INVALID_INPUT_OPTION);
                                        scanner.next();
                                    }
                                }

                                logInfo(DATE_OPTION);
                                exDate = scanner.nextLine();
                                while (!Checks.isValidDate(exDate)) {
                                    logger.warning(INVALID_DATE_OPTION);
                                    exDate = scanner.nextLine();
                                }

                                MyApp.product = new Product(productId, productName, price, wholesalePrice, quantity, saledQty, exDate, userEmail);
                                updateProduct(MyApp.product);
                                logInfo(UPDATED_OPTION);
                            } else if (userChoice.equals("c")) {
                                logInfo(LIST_OPTION);
                                Listing.listingOfProductsForSpecificOwner(MyApp.userEmail);

                                while (true) {
                                    logInfo("Enter product id:");
                                    if (scanner.hasNextInt()) {
                                        productId = scanner.nextInt();
                                        scanner.nextLine();
                                        break;
                                    } else {
                                        logger.warning(INVALID_INPUT_OPTION);
                                        scanner.next();
                                    }
                                }
                                deleteProduct(productId);
                                logInfo("_Deletion completed successfully");
                            } else if (userChoice.equals("d")) {
                                generateFinancialReports();
                                logInfo("");
                            } else if (userChoice.equals("e")) {
                                listingBestSellingProduct();
                                logInfo("");
                            } else if (userChoice.equals("f")) {
                                while (true) {
                                    logInfo("Enter discount number (e.g: 0.20 for 20%):");
                                    if (scanner.hasNextDouble()) {
                                        discount = scanner.nextDouble();
                                        scanner.nextLine();

                                        if (Checks.isValidDiscount(discount)) {
                                            break;
                                        } else {
                                            logger.warning("Invalid discount! Please enter a value between 0.0 and 1.0.");
                                        }
                                    } else {
                                        logger.warning(INVALID_INPUT_OPTION );
                                        scanner.next();
                                    }
                                }
                                Updates.productDiscount(discount);
                                logInfo("The discount has been successfully applied");
                            } else if (userChoice.equals("g")) {
                                break;
                            } else {
                                logger.warning(INVALID_OPTION);
                            }
                        }

                    } else if (userChoice.equals("2")) {
                        while (true) {
                            logInfo("Choose from the following:");
                            logInfo("\ta. communicate with others:");
                            logInfo("\tb. See notification:");
                            logInfo("\tc. Back:");
                            char uc = scanner.next().charAt(0);

                            if (uc == 'a') {
                                logInfo("    a. Communicate with users");
                                logInfo("    b. Communicate with suppliers");
                                logInfo("    c. Communicate with owners");
                                userChoice = scanner.next();
                                if (userChoice.equals("a")) {
                                    userTypeToCommunicate = 4;
                                    communicateWithUser(userEmail, userTypeToCommunicate);
                                } else if (userChoice.equals("b")) {
                                    userTypeToCommunicate = 3;
                                    communicateWithUser(userEmail, userTypeToCommunicate);
                                } else if (userChoice.equals("c")) {
                                    userTypeToCommunicate = 2;
                                    communicateWithUser(userEmail, userTypeToCommunicate);
                                } else
                                    logger.warning(INVALID_OPTION);
                            }//communications
                            else if (uc == 'b') {
                                Listing.listingAllMsgsSentToUser(userEmail);
                            }//notifications
                            else if (uc == 'c') {
                                break;
                            }//back
                            else
                                logger.warning(INVALID_OPTION);
                        }

                    }//communication and notifications

                    else if (userChoice.equals("3")) {
                        while (true) {
                            logInfo("* Accounts Management:");
                            logInfo("    a. Update your account.");
                            logInfo("    b. Business management.");
                            logInfo("    c. Back.");

                            userChoice = scanner.next();

                            if (userChoice.equals("a")) {
                                Listing.listingYourOwnAccount(MyApp.userEmail);
                                User u = signUp(MyApp.userEmail, null, null, null, 0);
                                Updates.updateUser(u);
                                logInfo("Updated Successfully!\n");
                            } else if (userChoice.equals("b")) {
                                while (true) {
                                    logInfo("Enter business id:");
                                    if (scanner.hasNextInt()) {
                                        businessId = scanner.nextInt();
                                        scanner.nextLine();
                                        break;
                                    } else {
                                        logger.warning(INVALID_INPUT_OPTION);
                                        scanner.next();
                                    }
                                }

                                logInfo("Enter business name:");
                                scanner.nextLine();
                                businessName = scanner.nextLine();
                                while (!Checks.isValidProductName(businessName)) {
                                    logger.warning("Please enter a valid business name:");
                                    scanner.nextLine();
                                    businessName = scanner.nextLine();
                                }

                                logInfo("Enter business location:");
                                businessLocation = scanner.nextLine();
                                while (!Checks.isValidCity(businessLocation)) {
                                    logger.warning("Please enter a valid business location:");
                                    scanner.nextLine();
                                    businessLocation = scanner.nextLine();
                                }

                                MyApp.business = new Business(businessId, businessName, businessLocation, userEmail);
                                Updates.updateBusinessInfo(MyApp.business);
                                logInfo(UPDATED_OPTION);
                            } else if (userChoice.equals("c")) {
                                break;
                            } else {
                                logger.warning(INVALID_OPTION);
                            }
                        }
                    } else if (userChoice.equals("4")) {
                        logInfo(LIST_OF_ROW_MATERAILS);
                        Listing.listingOfRawMaterials();

                        logInfo("Please enter the name of the supplier you wish to purchase from:");
                        scanner.nextLine();
                        supplierEmail = scanner.nextLine();
                        while (!Checks.isValidEmail(supplierEmail) || !Checks.checkIfEmailAlreadyUsed(supplierEmail)) {
                            logger.warning("Please enter a valid email:");
                            scanner.nextLine();
                            supplierEmail = scanner.nextLine();
                        }

                        ArrayList<String> items = new ArrayList<>();
                        ArrayList<Integer> qtyList = new ArrayList<>();

                        while (true) {
                            logInfo("Enter the name of the raw material you want to order (or type 'done' to finish):");
                            materialName = scanner.nextLine();
                            while (!Checks.isValidProductName(materialName)) {
                                logger.warning(INVALID_ROW_MATERIAL_OPTION);
                                scanner.nextLine();
                                materialName = scanner.nextLine();
                            }

                            if (materialName.equalsIgnoreCase("done")) {
                                break;
                            }
                            items.add(materialName);

                            logInfo("Enter the quantity for " + materialName + ":");
                            while (true) {
                                try {
                                    quantity = scanner.nextInt();
                                    scanner.nextLine();
                                    qtyList.add(quantity);
                                    break;
                                } catch (InputMismatchException e) {
                                    logger.warning(INVALID_INPUT_OPTION);
                                    scanner.next();
                                }
                            }
                        }
                        MyApp.order = new Order(userEmail, supplierEmail, LocalDateTime.now(), items, qtyList);
                        Updates.addNewOrderForRowMaterials(MyApp.order);
                        logInfo("Order successfully added");
                    } else if (userChoice.equals("5")) {
                        break;
                    } else {
                        logger.warning(INVALID_OPTION);
                    }
                }
            }
            else if (MyApp.userType == 3) {
                while (true) {
                    logInfo("Welcome Supplier! Choose what to do from the list:");
                    logInfo("1. Raw material Management");
                    logInfo("2. Communication and Notification");
                    logInfo(EXIT_OPTION);

                    userChoice = scanner.next();
                    userChoice = userChoice.trim();
                    if (userChoice.equals("1")) {
                        while (true) {
                            logInfo("* Raw material Management");
                            logInfo("    a. Add new raw material.");
                            logInfo("    b. Update available raw material.");
                            logInfo("    c. Remove raw material.");
                            logInfo("    d. Back.");

                            userChoice = scanner.next();
                            if (userChoice.equals("a")) {
                                logInfo(LIST_OF_ROW_MATERAILS);
                                Listing.listingOfRawMaterialsForSpecificSupplier(MyApp.userEmail);

                                logInfo("Enter raw material name:");
                                scanner.nextLine();
                                rawMaterialName = scanner.nextLine();
                                while (!Checks.isValidProductName(rawMaterialName)) {
                                    logger.warning(INVALID_ROW_MATERIAL_OPTION);
                                    scanner.nextLine();
                                    rawMaterialName = scanner.nextLine();
                                }

                                while (true) {
                                    logInfo("Enter raw material price:");
                                    if (scanner.hasNextInt()) {
                                        price = scanner.nextInt();
                                        scanner.nextLine();
                                        break;
                                    } else {
                                        logger.warning(INVALID_INPUT_OPTION);
                                        scanner.next();
                                    }
                                }

                                while (true) {
                                    logInfo(PRICE_OPTION);
                                    if (scanner.hasNextInt()) {
                                        wholesalePrice = scanner.nextInt();
                                        scanner.nextLine();
                                        break;
                                    } else {
                                        logger.warning(INVALID_INPUT_OPTION);
                                        scanner.next();
                                    }
                                }

                                while (true) {
                                    logInfo(QTY_OPTION);
                                    if (scanner.hasNextInt()) {
                                        quantity = scanner.nextInt();
                                        scanner.nextLine();
                                        break;
                                    } else {
                                        logger.warning(INVALID_INPUT_OPTION);
                                        scanner.next();
                                    }
                                }

                                while (true) {
                                    logInfo(SALED_QTY_OPTION);
                                    if (scanner.hasNextInt()) {
                                        saledQty = scanner.nextInt();
                                        scanner.nextLine();
                                        break;
                                    } else {
                                        logger.warning(INVALID_INPUT_OPTION);
                                        scanner.next();
                                    }
                                }

                                logInfo(DATE_OPTION);
                                exDate = scanner.nextLine();
                                while (!Checks.isValidDate(exDate)) {
                                    logger.warning(INVALID_DATE_OPTION);
                                    exDate = scanner.nextLine();
                                }

                                MyApp.rawMaterial = new RawMaterial(rawMaterialName, price, wholesalePrice, quantity, saledQty, exDate, userEmail);
                                addNewRawMaterial(MyApp.rawMaterial);
                                logInfo("Successfully added");
                            } else if (userChoice.equals("b")) {
                                logInfo(LIST_OF_ROW_MATERAILS);
                                Listing.listingOfRawMaterialsForSpecificSupplier(MyApp.userEmail);

                                while (true) {
                                    logInfo("Enter raw material id:");
                                    if (scanner.hasNextInt()) {
                                        rawMaterialId = scanner.nextInt();
                                        scanner.nextLine();
                                        break;
                                    } else {
                                        logger.warning(INVALID_INPUT_OPTION);
                                        scanner.next();
                                    }
                                }

                                logInfo("Enter raw material name:");
                                scanner.nextLine();
                                rawMaterialName = scanner.nextLine();
                                while (!Checks.isValidProductName(rawMaterialName)) {
                                    logger.warning(INVALID_ROW_MATERIAL_OPTION);
                                    scanner.nextLine();
                                    rawMaterialName = scanner.nextLine();
                                }

                                while (true) {
                                    logInfo("Enter raw material price:");
                                    if (scanner.hasNextInt()) {
                                        price = scanner.nextInt();
                                        scanner.nextLine();
                                        break;
                                    } else {
                                        logger.warning(INVALID_INPUT_OPTION);
                                        scanner.next();
                                    }
                                }

                                while (true) {
                                    logInfo(PRICE_OPTION);
                                    if (scanner.hasNextInt()) {
                                        wholesalePrice = scanner.nextInt();
                                        scanner.nextLine();
                                        break;
                                    } else {
                                        logger.warning(INVALID_INPUT_OPTION);
                                        scanner.next();
                                    }
                                }

                                while (true) {
                                    logInfo(QTY_OPTION);
                                    if (scanner.hasNextInt()) {
                                        quantity = scanner.nextInt();
                                        scanner.nextLine();
                                        break;
                                    } else {
                                        logger.warning(INVALID_INPUT_OPTION);
                                        scanner.next();
                                    }
                                }

                                while (true) {
                                    logInfo(SALED_QTY_OPTION);
                                    if (scanner.hasNextInt()) {
                                        saledQty = scanner.nextInt();
                                        scanner.nextLine();
                                        break;
                                    } else {
                                        logger.warning(INVALID_INPUT_OPTION);
                                        scanner.next();
                                    }
                                }

                                logInfo(DATE_OPTION);
                                exDate = scanner.nextLine();
                                while (!Checks.isValidDate(exDate)) {
                                    logger.warning(INVALID_DATE_OPTION);
                                    exDate = scanner.nextLine();
                                }

                                MyApp.rawMaterial = new RawMaterial(rawMaterialId, rawMaterialName, price, wholesalePrice, quantity, saledQty, exDate, userEmail);

                                updateRawMaterial(MyApp.rawMaterial);
                                logInfo(UPDATED_OPTION);
                            } else if (userChoice.equals("c")) {
                                logInfo(LIST_OF_ROW_MATERAILS);
                                Listing.listingOfRawMaterialsForSpecificSupplier(MyApp.userEmail);

                                while (true) {
                                    logInfo("Enter raw material id:");
                                    if (scanner.hasNextInt()) {
                                        rawMaterialId = scanner.nextInt();
                                        scanner.nextLine();
                                        break;
                                    } else {
                                        logger.warning(INVALID_INPUT_OPTION);
                                        scanner.next();
                                    }
                                }

                                deleteRawMaterial(rawMaterialId);
                                logInfo("Deletion completed successfully");
                            } else if (userChoice.equals("d")) {
                                break;
                            } else {
                                logger.warning(INVALID_OPTION);
                            }
                        }
                    } else if (userChoice.equals("2")) {
                        while (true) {
                            logInfo("* Communication and Notification:");
                            logInfo("    a. Communicate with suppliers.");
                            logInfo("    b. Communicate with owners.");
                            logInfo("    c. Back.");
                            userChoice = scanner.next();
                            if (userChoice.equals("a")) {
                                userTypeToCommunicate = 3;
                                communicateWithUser(userEmail, userTypeToCommunicate);
                            }
                            else if (userChoice.equals("b"))
                            {
                                userTypeToCommunicate = 2;
                                communicateWithUser(userEmail, userTypeToCommunicate);
                            }
                            else if (userChoice.equals("c"))
                            {
                                break;
                            }else {
                                logger.warning(INVALID_OPTION);
                            }


                        }
                    } else if (userChoice.equals("3")) {
                        break;
                    } else {
                        logger.warning(INVALID_OPTION);
                    }
                }
            }// logged in as supplier

            if (userType == 4) {
                while (true) {
                    logInfo("Welcome User! choose what to do from the list:");
                    logInfo("\t1. Account Management");
                    logInfo("\t2. Explore Recipes");
                    logInfo("\t3. Create An Order");
                    logInfo("\t4. Communication");
                    logInfo("\t5. Feedback");
                    logInfo("\t6. Exit");

                    char uc = scanner.next().charAt(0);

                    if (uc == '1') {
                        while (true) {
                            logInfo("* Account Management : ");
                            logInfo("\ta. Update your personal information");
                            logInfo("\tb. Post a new personal dessert creation");
                            logInfo("\tc. Back");

                            uc = scanner.next().charAt(0);
                            if (uc == 'a') {
                                User u = signUp(userEmail, null, null, null, 0);
                                Updates.updateUser(u);
                                logInfo("Updated Successfully!\n");
                            } else if (uc == 'b') {
                                String recipeName = null;
                                String recipeDescription = null;
                                String recipeCate = null;

                                while (!Checks.isAcceptableRecipeName(recipeName)) {
                                    logInfo("Enter your new recipe name : ");
                                    scanner.nextLine();
                                    recipeName = scanner.nextLine();
                                    if (!Checks.isAcceptableRecipeName(recipeName)) {
                                        logger.warning("Unacceptable recipe name!");
                                        logInfo("\t1. Enter recipe name again");
                                        logInfo(BACK);

                                        uc = scanner.next().charAt(0);
                                        if (uc == '2')
                                            break;
                                        else if (uc != '1') {
                                            logger.warning(INVALID_OPTION);
                                        }
                                    }
                                }

                                while (!Checks.isAcceptableRecipeDescription(recipeDescription)) {
                                    logInfo("Enter your new recipe description : ");
                                    recipeDescription = scanner.nextLine();
                                    if (!Checks.isAcceptableRecipeDescription(recipeDescription)) {
                                        logger.warning("Unacceptable recipe description!");
                                        logInfo("\t1. Enter recipe description again");
                                        logInfo(BACK);

                                        uc = scanner.next().charAt(0);
                                        if (uc == '2')
                                            break;
                                        else if (uc != '1') {
                                            logger.warning(INVALID_OPTION);
                                        }
                                    }
                                }

                                while (!Checks.isAcceptableRecipeCategory(recipeCate)) {
                                    logInfo("Enter your new recipe category (dietary needs or food allergies) : ");
                                    scanner.nextLine();
                                    recipeCate = scanner.nextLine();
                                    if (!Checks.isAcceptableRecipeCategory(recipeCate)) {
                                        logger.warning("Unacceptable recipe category! knowing that available categories are : dietary needs and food allergies");
                                        logInfo("\t1. Enter recipe category again");
                                        logInfo(BACK);

                                        uc = scanner.next().charAt(0);
                                        if (uc == '2')
                                            break;
                                        else if (uc != '1') {
                                            logger.warning(INVALID_OPTION);
                                        }
                                    }
                                }

                                Recipe recipe = new Recipe(recipeName, recipeDescription, recipeCate, userEmail);
                                Updates.addNewRecipe(recipe);
                            } else if (uc == 'c') {
                                break;
                            } else {
                                logger.warning(INVALID_OPTION);
                            }
                        }
                    } else if (uc == '2') {
                        while (true) {
                            logInfo("* Explore Recipes : ");
                            logInfo("\ta. Explore recipes");
                            logInfo("\tb. Search for recipes");
                            logInfo("\tc. Recipes for dietary needs");
                            logInfo("\td. Recipes for food allergies");
                            logInfo("\te. Back");

                            uc = scanner.next().charAt(0);

                            if (uc == 'a') {
                                logInfo("List of all recipes in the system");
                                Listing.listRecipesInDb();
                            }//explore recipes
                            else if (uc == 'b')
                            {

                                String rName;
                                logInfo("Enter recipe Name you look for : ");
                                rName = scanner.next();
                                Listing.printingRecipeAccordingToRecipeName(rName);
                            }//search for recipes
                            else if (uc == 'c')
                            {
                                logInfo("List of recipes for dietary needs");
                                Listing.listRecipesInDbAccordingToCategory("dietary needs");
                            }//recipes for dietary needs
                            else if (uc == 'd')
                            {
                                logInfo("List of recipes for food allergies");
                                Listing.listRecipesInDbAccordingToCategory("food allergies");
                            }//recipes for food allergies
                            else if (uc == 'e')
                            {
                                break;
                            } else {
                                logger.warning(INVALID_OPTION);
                            }
                        }
                    } else if (uc == '3') {
                        logInfo(LIST_OPTION);
                        Listing.listingOfProducts();

                        logInfo("Please enter the name of the Owner you wish to purchase from:");
                        scanner.nextLine();
                        ownerEmail = scanner.nextLine();
                        while (!Checks.isValidEmail(ownerEmail) || !Checks.checkIfEmailAlreadyUsed(ownerEmail)) {
                            logInfo("Please enter a valid email:");
                            scanner.nextLine();
                            ownerEmail = scanner.nextLine();
                        }

                        ArrayList<String> items = new ArrayList<>();
                        ArrayList<Integer> qtyList = new ArrayList<>();

                        while (true) {
                            logInfo("Enter the name of the product you want to order (or type 'done' to finish):");
                            productName = scanner.nextLine();
                            while (!Checks.isValidProductName(productName)) {
                                logInfo("Please enter a valid raw product name:");
                                scanner.nextLine();
                                productName = scanner.nextLine();
                            }

                            if (productName.equalsIgnoreCase("done")) {
                                break;
                            }
                            items.add(productName);

                            logInfo("Enter the quantity for " + productName + ":");
                            while (true) {
                                try {
                                    quantity = scanner.nextInt();
                                    scanner.nextLine();
                                    qtyList.add(quantity);
                                    break;
                                } catch (InputMismatchException e) {
                                    logger.warning(INVALID_INPUT_OPTION);
                                    scanner.next();
                                }
                            }
                        }
                        MyApp.order = new Order(userEmail, ownerEmail, LocalDateTime.now(), items, qtyList);
                        Updates.addNewOrderForProduct(MyApp.order);
                        logInfo("_Order successfully added_");
                    } else if (uc == '4') {
                        userTypeToCommunicate = 2;
                        communicateWithUser(userEmail, userTypeToCommunicate);
                    } else if (uc == '5') {
                        int chosenOrder;
                        int chosenProduct;
                        char evaluation;

                        while (true) {
                            logInfo("Choose what to give feedback to : ");
                            logInfo("    a. Feedback on purchased products");
                            logInfo("    b. Feedback on shared recipes");
                            logInfo("    c. Back");
                            uc = scanner.next().charAt(0);

                            if (uc == 'a') {
                                logInfo("Feedback on purchased products : ");
                                List<Integer> ordersId = Listing.ordersMadeByThisUser(userEmail);
                                if (!ordersId.isEmpty()) {
                                    boolean exitFlag = true;
                                    while (exitFlag) {
                                        logInfo("Choose the order ID : ");
                                        while (true) {
                                            try {
                                                chosenOrder = scanner.nextInt();
                                                scanner.nextLine();
                                                break;
                                            } catch (InputMismatchException e) {
                                                logger.warning("Invalid input! Please enter a numeric value for the order ID.");
                                                scanner.next();
                                            }
                                        }

                                        if (ordersId.contains(chosenOrder)) {
                                            List<Integer> productsIdsInSelectedOrder = Listing.productsInTheOrder(chosenOrder);
                                            logInfo("\nChoose the product ID to give feedback to : ");
                                            while (true) {
                                                try {
                                                    chosenProduct = scanner.nextInt();
                                                    scanner.nextLine();
                                                    break;
                                                } catch (InputMismatchException e) {
                                                    logger.warning("Invalid input! Please enter a numeric value for the product ID.");
                                                    scanner.next();
                                                }
                                            }

                                            if (productsIdsInSelectedOrder.contains(chosenProduct)) {
                                                while (true) {
                                                    logInfo("Your evaluation is : (please choose number from 1-bad- to 5-good)");
                                                    logInfo("    To get back press *");
                                                    evaluation = scanner.next().charAt(0);
                                                    if (evaluation == '*')
                                                        break;
                                                    evaluation = (char) (evaluation - '0');
                                                    if (evaluation >= 1 && evaluation <= 5) {
                                                        Updates.addNewFeedback(new Feedback(chosenProduct, evaluation), 1);
                                                        logInfo("Feedback added successfully! ");
                                                        logInfo("");
                                                        exitFlag = false;
                                                        break;
                                                    } else
                                                        logger.warning("Invalid evaluation!");
                                                }
                                            } else
                                                logger.warning("This product is not in the order you chosen!");
                                        } else
                                            logger.warning("Please enter a valid order id!");
                                    }
                                }
                            } else if (uc == 'b') {
                                int chosenRecipe;
                                logInfo("Feedback on shared recipes : ");
                                List<Integer> recipesID = Listing.listRecipesInDb();

                                if (!recipesID.isEmpty()) {
                                    logInfo("Choose the recipe ID : ");
                                    while (true) {
                                        try {
                                            chosenRecipe = scanner.nextInt();
                                            break;
                                        } catch (InputMismatchException e) {
                                            logger.warning("Invalid input! Please enter a numeric value for the recipe ID.");
                                            scanner.next();
                                        }
                                    }

                                    if (recipesID.contains(chosenRecipe)) {
                                        while (true) {
                                            logInfo("Your evaluation is : (please choose number from 1-bad- to 5-good)");
                                            logInfo("    To get back press *");
                                            evaluation = scanner.next().charAt(0);
                                            if (evaluation == '*')
                                                break;
                                            evaluation = (char) (evaluation - '0');
                                            if (evaluation >= 1 && evaluation <= 5) {
                                                Updates.addNewFeedback(new Feedback(chosenRecipe, evaluation), 2);
                                                logInfo("Feedback added successfully! ");
                                                logInfo("");
                                                break;
                                            } else
                                                logger.warning("Invalid evaluation!");
                                        }
                                    }
                                }
                            } else if (uc == 'c') {
                                break;
                            } else
                                logger.warning(INVALID_OPTION);
                        }
                    } else if (uc == '6') {
                        break;
                    } else {
                        logger.warning(INVALID_OPTION);
                    }
                }
            }
        }
    }

    private static String enteringEmail(Scanner scanner, String ue)
    {
        while (!Checks.isValidEmail(ue))
        {
            logInfo("Enter Email:");
            ue = scanner.next();
            if (Checks.isValidEmail(ue) && !Checks.checkIfEmailAlreadyUsed(ue))
                break;
            else if (!Checks.isValidEmail(ue))
                logger.warning("Invalid Email format! valid email should contain '@' in addition to a correct domain name.");
            else if (Checks.checkIfEmailAlreadyUsed(ue))
                logger.warning("Email already used! Try again.");
        }
        return ue;
    }

    private static String enteringUsername(Scanner scanner, String username)
    {
        while (!Checks.isValidUsername(username)) {
            logInfo("Enter username:");
            username = scanner.next();
            if (!Checks.isValidUsername(username))
                logger.warning("Invalid username! Try again.");
            else
                break;
        }
        return username;
    }

    private static String enteringPassword(Scanner scanner, String password)
    {
        while (!Checks.isvalidPassword(password))
        {
            logInfo("Enter password:");
            password = scanner.next();
            if (!Checks.isvalidPassword(password))
            {
                logger.warning("Invalid password! Try again.");
                logger.warning("knowing that a valid password longer than 8 characters and contains alphabetics, digits and other characters ");
            }
        }
        return password;
    }

    private static String enteringCity(Scanner scanner, String city)
    {
        while (!Checks.isValidCity(city))
        {
            logInfo("Enter location:");
            logInfo("    Knowing that available cities are: Gaza, Nablus, Ramallah, Jenin, Tulkarem, Bethlehem and Hebron");
            city = scanner.next();
            if (!Checks.isValidCity(city))
                logger.warning("Invalid location! Try again.");
        }
        return city;
    }

    private static int enteringUserType(Scanner scanner, int userType)
    {
        while (!Checks.isValidUserType(userType))
        {
            char ut;
            logInfo(CHOOSE_OPTION);
            logInfo(OWNER_OPTION);
            logInfo(SUPPLIER_OPTION);
            logInfo(USER_OPTION);
            ut = scanner.next().charAt(0);
            if (Character.isDigit(ut))
                userType = ut - '0' +1;

            if (!Checks.isValidUserType(userType) ) {
                logger.warning(INVALID_LEVEL_OPTION);
                logInfo("    Enter user level again");
            } else
                break;
        }
        return userType;
    }

    private static User signUp(String userEmail, String username, String password, String city, int userType) {
        Scanner scanner = new Scanner(System.in);

        String e =  enteringEmail(scanner, userEmail);

        String un = enteringUsername(scanner, username);

        String pass = enteringPassword(scanner, password);

        String location = enteringCity(scanner, city);

        int ut = enteringUserType(scanner,userType);

        User u = new User(un, pass, e, location, ut);
        return u;
    }
    private static void communicateWithUser(String userEmail, int userTypeToCommunicate) {
        String receiverEmail = null;
        Scanner scanner = new Scanner(System.in);
        Listing.listAllUsersInTheSystem(userTypeToCommunicate);

        logInfo("Enter user email you want to communicate with:");
        while (receiverEmail == null || !Checks.checkIfEmailAlreadyUsed(receiverEmail)) {
            receiverEmail = scanner.nextLine();
            if (Checks.checkIfEmailAlreadyUsed(receiverEmail)) {
                break;
            } else {
                logger.warning("Invalid email try again!");
            }
        }

        logInfo("Write your message:");
        String msg = scanner.nextLine();

        MyApp.msg = new Messaging(userEmail, receiverEmail, msg);
        Updates.addNewMsg(MyApp.msg);
        logInfo("_Your message was sent successfully_");
    }

}

