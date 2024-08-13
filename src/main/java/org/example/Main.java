package org.example;

import Entities.*;
import sweetSys.Checks;
import sweetSys.Listing;
import sweetSys.MyApp;
import sweetSys.Updates;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.Character.isDigit;
import static java.lang.System.exit;
import static sweetSys.Listing.*;
import static sweetSys.Updates.*;

public class Main {
    private static boolean signupFlag;
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
        while (true)
        {
            MyApp.userEmail = "";
            MyApp.userType = 0;
            userEmail = "";
            password = "";

            System.out.println("WELCOME TO OUR SWEET MANAGEMENT SYSTEM");
            System.out.println("1. Login");
            System.out.println("2. Sign up");
            System.out.println("3. Exit");

            userChoice = scanner.next();
            userChoice.trim();


            if(userChoice.equals("1"))
            {
                while (!Checks.checkIfEmailAlreadyUsed(userEmail))
                {
                    System.out.println("Enter your email");
                    userEmail = scanner.next();
                    if (!Checks.checkIfEmailAlreadyUsed(userEmail))
                    {
                        System.out.println("Invalid Email! Try again");
                        System.out.println("Does not have an account?");
                        System.out.println("    a. Enter email again");
                        System.out.println("    b. Sign up");
                        userChoice = scanner.next();
                        if(userChoice.equals("a"))
                            continue;
                        else if (userChoice.equals("b")) {
                            signupFlag = true;
                            break;
                        }
                        else
                            System.out.println("Invalid choice!");
                    }
                }//entering email loop

                if(!signupFlag)
                {
                    while (!Checks.checkIfUserInDatabase(userEmail, password))
                    {
                        System.out.println("Enter your password");
                        password = scanner.next();
                        if (!Checks.checkIfUserInDatabase(userEmail, password)) {
                            System.out.println("Incorrect Password! Try again");
                            System.out.println("Does not have an account?");
                            System.out.println("    a. Enter password again");
                            System.out.println("    b. Sign up");
                            userChoice = scanner.next();
                            if (userChoice.equals("a"))
                                continue;
                            else if (userChoice.equals("b")) {
                                signupFlag = true;
                                break;
                            }
                            else
                                System.out.println("Invalid choice!");
                        }
                    }//entering password loop
                }

                MyApp.user = new User(userEmail, password);
                MyApp.userEmail = userEmail;
                MyApp.userType = MyApp.user.userTypeByEmail(userEmail);
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
                System.out.println("Invalid choice! ");
                continue;
            }

            if (MyApp.userType == 1)
            {
                boolean isCorrectChoice = false;
                while (true)
                {
                    System.out.println("Welcome Admin! choose what to do from the list:");
                    System.out.println("1. Accounts Management");
                    System.out.println("2. Reporting And Monitoring");
                    System.out.println("3. Exit");
                    char uc = scanner.next().charAt(0);

                    if (uc == '1')
                    {
                        System.out.println("* Accounts Managements : ");
                        System.out.println("Lists of all users in the system : ");
                        Listing.listAllUsersInTheSystem(2);
                        System.out.println();

                        Listing.listAllUsersInTheSystem(3);
                        System.out.println();

                        Listing.listAllUsersInTheSystem(4);
                        System.out.println();

                        while (true) {
                            System.out.println("Choose what to do : ");
                            System.out.println("a. Add new user");
                            System.out.println("b. Update existing user information");
                            System.out.println("c. Delete existing user");
                            System.out.println("d. Back");

                            uc = scanner.next().charAt(0);
                            if (uc == 'a')
                            {
                                User u = signUp(null, null, null, null, 0);
                                Updates.addNewUser(u);
                                System.out.println("Added Successfully");
                                System.out.println();
                                isCorrectChoice = true;
                            } //add new user
                            else if (uc == 'b')
                            {
                                System.out.println("Enter the email of user you want to update : ");
                                String ue = null;
                                while (true) {
                                    ue = scanner.next();
                                    if (!Checks.checkIfEmailAlreadyUsed(ue)) {
                                        System.out.println("User is not in the system ! Try again");
                                        System.out.println("    a. Enter email again");
                                        System.out.println("    b. Exit");
                                        userChoice = scanner.next();
                                        if (userChoice.equals("a"))
                                            continue;
                                        else if (userChoice.equals("b"))
                                            break;
                                    }
                                }
                                User u = signUp(ue, null, null, null, 0);
                                Updates.updateUser(u);
                                System.out.println("Updated Successfully");
                                System.out.println();
                            } //update user info
                            else if (uc == 'c')
                            {
                                System.out.println("Enter the email of user you want to delete : ");
                                String ue = null;
                                while (!Checks.checkIfEmailAlreadyUsed(ue)) {
                                    ue = scanner.next();
                                    if (!Checks.checkIfEmailAlreadyUsed(ue)) {
                                        System.out.println("User is not in the system ! Try again");
                                        System.out.println("    a. Enter email again");
                                        System.out.println("    b. Exit");
                                        userChoice = scanner.next();
                                        if (userChoice.equals("a"))
                                            continue;
                                        else if (userChoice.equals("b"))
                                            break;
                                    }
                                }
                                Updates.deleteUser(ue);
                                System.out.println("Deleted Successfully");
                                System.out.println();
                            } // delete user
                            else if (uc == 'd')
                                break;
                            else {
                                System.out.println("Incorrect choice!");
                            } //incorrect choice
                        }//end of loop tp choose from admin mg list
                    }//choosing Account mgt from the admin list

                    else if (uc == '2')
                    {
                        while (true) {
                            System.out.println("* Reporting And Monitoring : ");
                            System.out.println("a. Monitor profits and generate financial reports");
                            System.out.println("b. Identify best-selling products in each store");
                            System.out.println("c. Gather and display statistics on registered users by City");
                            System.out.println("d. Back");

                            uc = scanner.next().charAt(0);

                            if (uc == 'a') {
                                generateFinancialReports();
                                break;
                            } else if (uc == 'b') {
                                listingBestSellingProduct();
                                break;
                            } else if (uc == 'c') {
                                statisticsOnUsersByCity();
                                break;
                            } else if (uc == 'd') {
                                break;
                            } else {
                                System.out.println("Incorrect choice!");
                            }
                        }//end of loop to choose from Reporting And Monitoring list
                    }//choosing Reporting And Monitoring from the admin list

                    else if (uc == '3')
                    {
                        MyApp.userType = 0;
                        break; // to get out of admin area and return to the main list login, sign up, exit..
                    }
                    else
                        System.out.println("Invalid choice! Try Again");
                }
            }//logged in as admin

            else if (MyApp.userType == 2)
            {
                while (true)
                {
                    System.out.println("Welcome Owner! choose what to do from the list:");
                    System.out.println("1. Product Management:");
                    System.out.println("2. Communication and Notification");
                    System.out.println("3. Accounts Management");
                    System.out.println("4. Order Management");
                    System.out.println("5. exit");
                    userChoice = scanner.next();
                    userChoice.trim();
                    if (userChoice.equals("1"))
                    {
                        System.out.println("    a. Add new products.");
                        System.out.println("    b. update available products.");
                        System.out.println("    c. remove available products.");
                        System.out.println("    d. Sales and profits.");
                        System.out.println("    e. Best-selling products.");
                        System.out.println("    f. discount products.");
                        //System.out.println("    g. Back.");
                        userChoice = scanner.next();
                        if (userChoice.equals("a")) {
                            System.out.println("Enter product name:");
                            scanner.nextLine();
                            productName = scanner.nextLine();
                            while (!Checks.isValidProductName(productName)) {
                                System.out.println("Please enter a valid product name:");
                                productName = scanner.nextLine();
                            }

                            while (true) {
                                System.out.println("Enter product price:");
                                if (scanner.hasNextInt()) {
                                    price = scanner.nextInt();
                                    scanner.nextLine();
                                    break;
                                } else {
                                    System.out.println("Invalid input! Please enter a numeric value for the price.");
                                    scanner.next();
                                }
                            }

                            while (true) {
                                System.out.println("Enter wholesale price:");
                                if (scanner.hasNextInt()) {
                                    wholesalePrice = scanner.nextInt();
                                    scanner.nextLine();
                                    break;
                                } else {
                                    System.out.println("Invalid input! Please enter a numeric value for the wholesale price.");
                                    scanner.next();
                                }
                            }

                            while (true) {
                                System.out.println("Enter quantity:");
                                if (scanner.hasNextInt()) {
                                    quantity = scanner.nextInt();
                                    scanner.nextLine();
                                    break;
                                } else {
                                    System.out.println("Invalid input! Please enter a numeric value for the quantity.");
                                    scanner.next();
                                }
                            }

                            while (true) {
                                System.out.println("Enter saled quantity:");
                                if (scanner.hasNextInt()) {
                                    saledQty = scanner.nextInt();
                                    scanner.nextLine();
                                    break;
                                } else {
                                    System.out.println("Invalid input! Please enter a numeric value for the saled quantity.");
                                    scanner.next();
                                }
                            }

                            System.out.println("Enter expiration date (YYYY-MM-DD):");
                            exDate = scanner.nextLine();
                            while (!Checks.isValidDate(exDate)) {
                                System.out.println("Please enter a valid date in the format YYYY-MM-DD:");
                                exDate = scanner.nextLine();
                            }

                            MyApp.product = new Product(productName, price, wholesalePrice, quantity, saledQty, exDate, userEmail);
                            addNewProduct(MyApp.product);
                            System.out.println("_________________________Successfully added_________________________");
                        } else if (userChoice.equals("b")) {
                            System.out.println("List of existing products:");
                            Listing.listingOfProducts();

                            while (true) {
                                System.out.println("Enter product id:");
                                if (scanner.hasNextInt()) {
                                    productId = scanner.nextInt();
                                    scanner.nextLine();
                                    break;
                                } else {
                                    System.out.println("Invalid input! Please enter a numeric value for the price.");
                                    scanner.next();
                                }
                            }

                            System.out.println("Enter product name:");
                            productName = scanner.nextLine();
                            while (!Checks.isValidProductName(productName)) {
                                System.out.println("Please enter a valid product name:");
                                productName = scanner.nextLine();
                            }

                            while (true) {
                                System.out.println("Enter product price:");
                                if (scanner.hasNextInt()) {
                                    price = scanner.nextInt();
                                    scanner.nextLine();
                                    break;
                                } else {
                                    System.out.println("Invalid input! Please enter a numeric value for the price.");
                                    scanner.next();
                                }
                            }

                            while (true) {
                                System.out.println("Enter wholesale price:");
                                if (scanner.hasNextInt()) {
                                    wholesalePrice = scanner.nextInt();
                                    scanner.nextLine();
                                    break;
                                } else {
                                    System.out.println("Invalid input! Please enter a numeric value for the wholesale price.");
                                    scanner.next();
                                }
                            }

                            while (true) {
                                System.out.println("Enter quantity:");
                                if (scanner.hasNextInt()) {
                                    quantity = scanner.nextInt();
                                    scanner.nextLine();
                                    break;
                                } else {
                                    System.out.println("Invalid input! Please enter a numeric value for the quantity.");
                                    scanner.next();
                                }
                            }

                            while (true) {
                                System.out.println("Enter saled quantity:");
                                if (scanner.hasNextInt()) {
                                    saledQty = scanner.nextInt();
                                    scanner.nextLine();
                                    break;
                                } else {
                                    System.out.println("Invalid input! Please enter a numeric value for the saled quantity.");
                                    scanner.next();
                                }
                            }

                            System.out.println("Enter expiration date (YYYY-MM-DD):");
                            exDate = scanner.nextLine();
                            while (!Checks.isValidDate(exDate)) {
                                System.out.println("Please enter a valid date in the format YYYY-MM-DD:");
                                exDate = scanner.nextLine();
                            }

                            MyApp.product = new Product(productId, productName, price, wholesalePrice, quantity, saledQty, exDate, userEmail);
                            updateProduct(MyApp.product);
                            System.out.println("_________________________Successfully updated_________________________");
                        } else if (userChoice.equals("c")) {
                            System.out.println("List of existing products:");
                            Listing.listingOfProducts();

                            while (true) {
                                System.out.println("Enter product id:");
                                if (scanner.hasNextInt()) {
                                    productId = scanner.nextInt();
                                    scanner.nextLine();
                                    break;
                                } else {
                                    System.out.println("Invalid input! Please enter a numeric value for the price.");
                                    scanner.next();
                                }
                            }
                            deleteProduct(productId);
                            System.out.println("____________________Deletion completed successfully___________________");
                        } else if (userChoice.equals("d")) {
                            generateFinancialReports();
                            System.out.println("__________________________________________________");
                        } else if (userChoice.equals("e")) {
                            listingBestSellingProduct();
                            System.out.println("__________________________________________________");
                        } else if (userChoice.equals("f")) {
                            while (true) {
                                System.out.println("Enter discount number (e.g: 0.20 for 20%):");
                                if (scanner.hasNextDouble()) {
                                    discount = scanner.nextDouble();
                                    scanner.nextLine();

                                    if (Checks.isValidDiscount(discount)) {
                                        break;
                                    } else {
                                        System.out.println("Invalid discount! Please enter a value between 0.0 and 1.0.");
                                    }
                                } else {
                                    System.out.println("Invalid input! Please enter a numeric value.");
                                    scanner.next();
                                }
                            }
                            Updates.productDiscount(discount);
                            System.out.println("_________________The discount has been successfully applied_____________________");
                        }else{
                            System.out.println("Invalid choice!");
                        }

                    }
                    else if (userChoice.equals("2"))
                    {
                        System.out.println("    a. Communicate with users");
                        System.out.println("    b. Communicate with suppliers");
                        System.out.println("    c. Communicate with owners");
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
                        }else{
                            System.out.println("Invalid choice!");
                        }
                    }
                    else if (userChoice.equals("3"))
                    {
                        System.out.println("    a. Update your account.");
                        System.out.println("    b. Business management.");
                        userChoice = scanner.next();
                        if (userChoice.equals("a")) {
                            //****************************************************************//
                        } else if (userChoice.equals("b")) {
                            while (true) {
                                System.out.println("Enter business id:");
                                if (scanner.hasNextInt()) {
                                    businessId = scanner.nextInt();
                                    scanner.nextLine();
                                    break;
                                } else {
                                    System.out.println("Invalid input! Please enter a numeric value for the price.");
                                    scanner.next();
                                }
                            }

                            System.out.println("Enter business name:");
                            scanner.nextLine();
                            businessName = scanner.nextLine();
                            while (!Checks.isValidProductName(businessName)) {
                                System.out.println("Please enter a valid business name:");
                                scanner.nextLine();
                                businessName = scanner.nextLine();
                            }

                            System.out.println("Enter business location:");
                            businessLocation = scanner.nextLine();
                            while (!Checks.isValidCity(businessLocation)) {
                                System.out.println("Please enter a valid business location:");
                                scanner.nextLine();
                                businessLocation = scanner.nextLine();
                            }

                            MyApp.business = new Business(businessId, businessName, businessLocation, userEmail);
                            Updates.updateBusinessInfo(MyApp.business);
                            System.out.println("________________________Successfully updated__________________________");
                        }else {
                            System.out.println("Invalid choice!");
                        }
                    }
                    else if (userChoice.equals("4"))
                    {
                        System.out.println("List of existing raw materials:");
                        Listing.listingOfRawMaterials();

                        System.out.println("Please enter the name of the supplier you wish to purchase from:");
                        scanner.nextLine();
                        supplierEmail = scanner.nextLine();
                        while (!Checks.isValidEmail(supplierEmail) || !Checks.checkIfEmailAlreadyUsed(supplierEmail)){
                            System.out.println("Please enter a valid email:");
                            scanner.nextLine();
                            supplierEmail = scanner.nextLine();
                        }

                        ArrayList<String> items = new ArrayList<>();
                        ArrayList<Integer> qtyList = new ArrayList<>();

                        while (true) {
                            System.out.println("Enter the name of the raw material you want to order (or type 'done' to finish):");
                            materialName = scanner.nextLine();
                            while (!Checks.isValidProductName(materialName)){
                                System.out.println("Please enter a valid raw material name:");
                                scanner.nextLine();
                                materialName = scanner.nextLine();
                            }

                            if (materialName.equalsIgnoreCase("done")) {
                                break;
                            }
                            items.add(materialName);

                            System.out.println("Enter the quantity for " + materialName + ":");
                            while (true) {
                                try {
                                    quantity = scanner.nextInt();
                                    scanner.nextLine();
                                    qtyList.add(quantity);
                                    break;
                                } catch (InputMismatchException e) {
                                    System.out.println("Invalid input! Please enter a numeric value for the quantity.");
                                    scanner.next();
                                }
                            }
                        }
                        MyApp.order = new Order(userEmail, supplierEmail, LocalDateTime.now(), items, qtyList);
                        Updates.addNewOrderForRowMaterials(MyApp.order);
                        System.out.println("________________________Order successfully added__________________________");
                    }
                    else if (userChoice.equals("5"))
                    {
                        break;
                    }
                    else
                    {
                        System.out.println("Invalid choice!");
                    }
                }
            }//logged in as owner

            else if (MyApp.userType == 3)
            {
                while (true)
                {
                    System.out.println("Welcome Supplier! choose what to do from the list:");
                    System.out.println("1. Raw material Management");
                    System.out.println("2. Communication and Notification");
                    System.out.println("3. Exit");

                    userChoice = scanner.next();
                    userChoice.trim();
                    if (userChoice.equals("1"))
                    {
                        System.out.println("    a. Add new raw material.");
                        System.out.println("    b. update available raw material.");
                        System.out.println("    c. remove raw material.");
                        userChoice = scanner.next();
                        if (userChoice.equals("a")) {
                            System.out.println("List of existing raw materials:");
                            Listing.listingOfRawMaterials();

                            System.out.println("Enter raw material name:");
                            scanner.nextLine();
                            rawMaterialName = scanner.nextLine();
                            while (!Checks.isValidProductName(rawMaterialName)){
                                System.out.println("Please enter a valid raw material name:");
                                scanner.nextLine();
                                rawMaterialName = scanner.nextLine();
                            }

                            while (true)
                            {
                                System.out.println("Enter raw material price:");
                                if (scanner.hasNextInt()) {
                                    price = scanner.nextInt();
                                    scanner.nextLine();
                                    break;
                                } else {
                                    System.out.println("Invalid input! Please enter a numeric value for the price.");
                                    scanner.next();
                                }
                            }

                            while (true) {
                                System.out.println("Enter wholesale price:");
                                if (scanner.hasNextInt()) {
                                    wholesalePrice = scanner.nextInt();
                                    scanner.nextLine();
                                    break;
                                } else {
                                    System.out.println("Invalid input! Please enter a numeric value for the wholesale price.");
                                    scanner.next();
                                }
                            }

                            while (true) {
                                System.out.println("Enter quantity:");
                                if (scanner.hasNextInt()) {
                                    quantity = scanner.nextInt();
                                    scanner.nextLine();
                                    break;
                                } else {
                                    System.out.println("Invalid input! Please enter a numeric value for the quantity.");
                                    scanner.next();
                                }
                            }

                            while (true) {
                                System.out.println("Enter saled quantity:");
                                if (scanner.hasNextInt()) {
                                    saledQty = scanner.nextInt();
                                    scanner.nextLine();
                                    break;
                                } else {
                                    System.out.println("Invalid input! Please enter a numeric value for the saled quantity.");
                                    scanner.next();
                                }
                            }

                            System.out.println("Enter expiration date (YYYY-MM-DD):");
                            exDate = scanner.nextLine();
                            while (!Checks.isValidDate(exDate)) {
                                System.out.println("Please enter a valid date in the format YYYY-MM-DD:");
                                exDate = scanner.nextLine();
                            }

                            MyApp.rawMaterial = new rawMaterial(rawMaterialName, price, wholesalePrice, quantity, saledQty, exDate, userEmail);
                            addNewRawMaterial(MyApp.rawMaterial);
                            System.out.println("_________________________Successfully added_________________________");
                        } else if (userChoice.equals("b")) {
                            System.out.println("List of existing raw materials:");
                            Listing.listingOfRawMaterials();

                            while (true) {
                                System.out.println("Enter raw material id:");
                                if (scanner.hasNextInt()) {
                                    rawMaterialId = scanner.nextInt();
                                    scanner.nextLine();
                                    break;
                                } else {
                                    System.out.println("Invalid input! Please enter a numeric value for the price.");
                                    scanner.next();
                                }
                            }

                            System.out.println("Enter raw material name:");
                            scanner.nextLine();
                            rawMaterialName = scanner.nextLine();
                            while (!Checks.isValidProductName(rawMaterialName)){
                                System.out.println("Please enter a valid raw material name:");
                                scanner.nextLine();
                                rawMaterialName = scanner.nextLine();
                            }

                            while (true) {
                                System.out.println("Enter raw material price:");
                                if (scanner.hasNextInt()) {
                                    price = scanner.nextInt();
                                    scanner.nextLine();
                                    break;
                                } else {
                                    System.out.println("Invalid input! Please enter a numeric value for the price.");
                                    scanner.next();
                                }
                            }

                            while (true) {
                                System.out.println("Enter wholesale price:");
                                if (scanner.hasNextInt()) {
                                    wholesalePrice = scanner.nextInt();
                                    scanner.nextLine();
                                    break;
                                } else {
                                    System.out.println("Invalid input! Please enter a numeric value for the wholesale price.");
                                    scanner.next();
                                }
                            }

                            while (true) {
                                System.out.println("Enter quantity:");
                                if (scanner.hasNextInt()) {
                                    quantity = scanner.nextInt();
                                    scanner.nextLine();
                                    break;
                                } else {
                                    System.out.println("Invalid input! Please enter a numeric value for the quantity.");
                                    scanner.next();
                                }
                            }

                            while (true) {
                                System.out.println("Enter saled quantity:");
                                if (scanner.hasNextInt()) {
                                    saledQty = scanner.nextInt();
                                    scanner.nextLine();
                                    break;
                                } else {
                                    System.out.println("Invalid input! Please enter a numeric value for the saled quantity.");
                                    scanner.next();
                                }
                            }

                            System.out.println("Enter expiration date (YYYY-MM-DD):");
                            exDate = scanner.nextLine();
                            while (!Checks.isValidDate(exDate)) {
                                System.out.println("Please enter a valid date in the format YYYY-MM-DD:");
                                exDate = scanner.nextLine();
                            }


                            MyApp.rawMaterial = new rawMaterial(rawMaterialId, rawMaterialName, price, wholesalePrice, quantity, saledQty, exDate, userEmail);
                            updateRawMaterial(MyApp.rawMaterial);
                            System.out.println("_________________________Successfully updated_________________________");
                        } else if (userChoice.equals("c")) {
                            System.out.println("List of existing raw materials:");
                            Listing.listingOfRawMaterials();

                            while (true) {
                                System.out.println("Enter raw material id:");
                                if (scanner.hasNextInt()) {
                                    rawMaterialId = scanner.nextInt();
                                    scanner.nextLine();
                                    break;
                                } else {
                                    System.out.println("Invalid input! Please enter a numeric value for the price.");
                                    scanner.next();
                                }
                            }

                            deleteRawMaterial(rawMaterialId);
                            System.out.println("____________________Deletion completed successfully___________________");
                        }else{
                            System.out.println("Invalid choice!");
                        }
                    }

                    else if (userChoice.equals("2"))
                    {
                        System.out.println("    a. Communicate with suppliers");
                        System.out.println("    b. Communicate with owners");
                        userChoice = scanner.next();
                        if (userChoice.equals("a")) {
                            userTypeToCommunicate = 4;
                            communicateWithUser(userEmail, userTypeToCommunicate);
                        } else if (userChoice.equals("b")) {
                            userTypeToCommunicate = 3;
                            communicateWithUser(userEmail, userTypeToCommunicate);
                        } else{
                            System.out.println("Invalid choice!");
                        }
                    }
                    else if (userChoice.equals("3")){
                        break;
                    }else{
                        System.out.println("Invalid choice!");
                    }
                }
            }//logged in as supplier

            else if (MyApp.userType == 4)
            {
                while(true)
                {
                    System.out.println("Welcome User! choose what to do from the list:");
                    System.out.println("\t1. Account Management");
                    System.out.println("\t2. Explore Recipes");
                    System.out.println("\t3. Create An Order");
                    System.out.println("\t4. Communication");
                    System.out.println("\t5. Feedback");
                    System.out.println("\t6. Exit");

                    char uc = scanner.next().charAt(0);

                    if(uc == '1')
                    {
                        while (true)
                        {
                            System.out.println("* Account Management : ");
                            System.out.println("\ta. Update your personal information");
                            System.out.println("\tb. Post a new personal dessert creation");
                            System.out.println("\tc. Back");

                            uc = scanner.next().charAt(0);
                            if(uc == 'a')
                            {
                                User u = signUp(MyApp.userEmail, null,null, null, 0);
                                Updates.updateUser(u);
                                System.out.println("Updated Successfully!\n");
                            }//update account
                            else if (uc == 'b')
                            {
                                String recipeName = null;
                                String recipeDescription = null;
                                String recipeCate = null;

                                while(!Checks.isAcceptableRecipeName(recipeName))
                                {
                                    System.out.println("Enter your new recipe name : ");
                                    scanner.nextLine();
                                    recipeName = scanner.nextLine();
                                    if (!Checks.isAcceptableRecipeName(recipeName))
                                    {
                                        System.out.println("Unacceptable recipe name!");
                                        System.out.println("\t1. Enter recipe name again");
                                        System.out.println("\t2. back");

                                        uc = scanner.next().charAt(0);
                                        if(uc == '1')
                                            continue;
                                        else if(uc == '2')
                                            break;
                                        else
                                            System.out.println("Invalid Choice!");
                                    }
                                }//reading recipe name loop

                                while(!Checks.isAcceptableRecipeDescription(recipeDescription))
                                {
                                    System.out.println("Enter your new recipe description : ");
                                    recipeDescription = scanner.nextLine();
                                    if (!Checks.isAcceptableRecipeDescription(recipeDescription))
                                    {
                                        System.out.println("Unacceptable recipe description!");
                                        System.out.println("\t1. Enter recipe description again");
                                        System.out.println("\t2. back");

                                        uc = scanner.next().charAt(0);
                                        if(uc == '1')
                                            continue;
                                        else if(uc == '2')
                                            break;
                                        else
                                            System.out.println("Invalid Choice!");
                                    }
                                }//reading recipe description loop

                                while(!Checks.isAcceptableRecipeCategory(recipeCate))
                                {
                                    System.out.println("Enter your new recipe category : ");
                                    recipeCate = scanner.nextLine();
                                    if (!Checks.isAcceptableRecipeDescription(recipeCate))
                                    {
                                        System.out.println("Unacceptable recipe category!");
                                        System.out.println("\t1. Enter recipe category again");
                                        System.out.println("\t2. back");

                                        uc = scanner.next().charAt(0);
                                        if(uc == '1')
                                            continue;
                                        else if(uc == '2')
                                            break;
                                        else
                                            System.out.println("Invalid Choice!");
                                    }
                                }//reading recipe category loop

                                Recipe recipe = new Recipe(recipeName, recipeDescription, recipeCate, MyApp.userEmail);
                                Updates.addNewRecipe(recipe);

                                System.out.println("Added Successfully!");
                            }//post new recipe
                            else if (uc == 'c')
                            {
                                break;
                            }//back
                            else
                                System.out.println("Invalid choice!");
                        }//end of account mgt loop
                    }//choosing account mgt from the list

                    else if(uc == '2')
                    {
                        while (true)
                        {
                            System.out.println("* Explore Recipes : ");
                            System.out.println("\ta. Explore recipes");
                            System.out.println("\tb. Search for recipes");
                            System.out.println("\tc. Recipes for dietary needs");
                            System.out.println("\td. Recipes for food allergies");
                            System.out.println("\te. Back");

                            uc = scanner.next().charAt(0);
                            if(uc == 'a')
                            {
                                System.out.println("List of all recipes in the system");
                                Listing.ListRecipesInDb();
                            }//explore recipes
                            else if (uc == 'b')
                            {
                                String rName;
                                System.out.println("Enter recipe Name you look for : ");
                                rName = scanner.next();
                                Listing.printingRecipeAccordingToRecipeName(rName);
                            }//search for recipes
                            else if (uc == 'c')
                            {
                                System.out.println("List of recipes for dietary needs");
                                Listing.ListRecipesInDbAccordingToCategory("dietary needs");
                            }//recipes for dietary needs
                            else if (uc == 'd')
                            {
                                System.out.println("List of recipes for food allergies");
                                Listing.ListRecipesInDbAccordingToCategory("food allergies");
                            }//recipes for food allergies
                            else if (uc == 'e')
                            {
                                break;
                            }//back
                            else
                                System.out.println("Invalid choice!");
                        }//end of account mgt loop
                    }//choosing Explore recipe from the list
                    else if(uc == '3')
                    {
                        System.out.println("List of existing products:");
                        Listing.listingOfProducts();

                        System.out.println("Please enter the name of the Owner you wish to purchase from:");
                        scanner.nextLine();
                        ownerEmail = scanner.nextLine();
                        while (!Checks.isValidEmail(ownerEmail) || !Checks.checkIfEmailAlreadyUsed(ownerEmail)){
                            System.out.println("Please enter a valid email:");
                            scanner.nextLine();
                            ownerEmail = scanner.nextLine();
                        }

                        ArrayList<String> items = new ArrayList<>();
                        ArrayList<Integer> qtyList = new ArrayList<>();

                        while (true) {
                            System.out.println("Enter the name of the product you want to order (or type 'done' to finish):");
                            productName = scanner.nextLine();
                            while (!Checks.isValidProductName(productName)){
                                System.out.println("Please enter a valid raw product name:");
                                scanner.nextLine();
                                productName = scanner.nextLine();
                            }

                            if (productName.equalsIgnoreCase("done")) {
                                break;
                            }
                            items.add(productName);

                            System.out.println("Enter the quantity for " + productName + ":");
                            while (true) {
                                try {
                                    quantity = scanner.nextInt();
                                    scanner.nextLine();
                                    qtyList.add(quantity);
                                    break;
                                } catch (InputMismatchException e) {
                                    System.out.println("Invalid input! Please enter a numeric value for the quantity.");
                                    scanner.next();
                                }
                            }
                        }
                        MyApp.order = new Order(userEmail, ownerEmail, LocalDateTime.now(), items, qtyList);
                        Updates.addNewOrderForProduct(MyApp.order);
                        System.out.println("________________________Order successfully added__________________________");
                    }//choosing create an order from the list

                    else if(uc == '4')
                    {
                        userTypeToCommunicate = 2;
                        communicateWithUser(userEmail, userTypeToCommunicate);
                    }//choosing communication and feedback from the list
                    else if(uc == '5')
                    {
                        String chosenOrder = "";
                        char evaluation;
                        System.out.println("Feedback : ");

                        ArrayList<Integer> ordersId = Listing.userFeedback(MyApp.userEmail);

                        if(!ordersId.isEmpty())
                        {
                            boolean exitFlag = true;
                            while (exitFlag)
                            {
                                System.out.println("Choose the order ID to give feedback to : ");
                                System.out.println("    To get back press *");
                                chosenOrder = scanner.next();

                                if (chosenOrder.equals("*"))
                                    break;
                                int orderID = Integer.parseInt(chosenOrder);

                                if (ordersId.contains(orderID))
                                {
                                    while(true)
                                    {
                                        System.out.println("Your evaluation is : (please choose number from 1-bad- to 5-good)");
                                        System.out.println("    To get back press *");
                                        evaluation = scanner.next().charAt(0);
                                        if (evaluation == '*')
                                            break;
                                        evaluation = (char) (evaluation - '0');
                                        if (evaluation >= 1 && evaluation <= 5) {
                                            Updates.addNewFeedback(new Feedback(orderID, evaluation));
                                            System.out.println("Feedback added successfully! ");
                                            System.out.println();
                                            exitFlag = false;
                                            break;
                                        }
                                        else
                                            System.out.println("Invalid evaluation!");
                                    }//send feedback

                                } else
                                    System.out.println("Please enter a valid order id!");
                            }
                        }
                    }//choosing feedback from the list
                    else if(uc == '6')
                    {

                        break;
                    }//choosing exit from the list
                    else
                    {
                        System.out.println("Invalid Choice!");
                    }

                }
            }//logged in as regular user
        }
    }

    private static User signUp(String userEmail, String username, String password, String city, int userType)
    {
        Scanner scanner = new Scanner(System.in);
        String userChoice;
        while (!Checks.isValidEmail(userEmail))
        {
            System.out.println("Enter your email");
            userEmail = scanner.next();
            if (Checks.isValidEmail(userEmail) && !Checks.checkIfEmailAlreadyUsed(userEmail))
                break;

            else if (!Checks.isValidEmail(userEmail))
                System.out.println("Invalid Email format! valid email should contains @ in addition to correct domain name");

            else if (Checks.checkIfEmailAlreadyUsed(userEmail))
                System.out.println("Email already used! Try again");

            System.out.println("    a. Enter email again");
            System.out.println("    b. Exit");
            userChoice = scanner.next();
            if(userChoice.equals("a"))
                continue;
            else if (userChoice.equals("b")) {
               break;
            }
            else
                System.out.println("Invalid choice!");
        }//entering email loop

        while (!Checks.isValidUsername(username))
        {
            System.out.println("Enter your username");
            username = scanner.next();
            if (!Checks.isValidUsername(username))
            {
                System.out.println("Invalid username! Try again");
                System.out.println("    a. Enter username again");
                System.out.println("    b. Exit");
                userChoice = scanner.next();
                if(userChoice.equals("a"))
                    continue;
                else if (userChoice.equals("b")) {
                    break;
                }
                else
                    System.out.println("Invalid choice!");
            }
        }//entering username loop

        while (!Checks.isvalidPassword(password))
        {
            System.out.println("Enter your password");
            password = scanner.next();
            if (!Checks.isvalidPassword(password))
            {
                System.out.println("Invalid password! Try again");
                System.out.println("    a. Enter password again");
                System.out.println("    b. Exit");
                userChoice = scanner.next();
                if(userChoice.equals("a"))
                    continue;
                else if (userChoice.equals("b")) {
                    break;
                }
                else
                    System.out.println("Invalid choice!");
            }
        }//entering password loop

        while (!Checks.isValidCity(city))
        {
            System.out.println("Enter your location");
            System.out.println("\tKnowing that available cities are : Gaza, Nablus, Ramallah, Jenin, Tulkarem, Bethlehem and Hebron");
            city = scanner.next();
            if (!Checks.isValidCity(city))
            {
                System.out.println("Invalid location! Try again");
                System.out.println("    a. Enter location again");
                System.out.println("    b. Exit");
                userChoice = scanner.next();
                if(userChoice.equals("a"))
                    continue;
                else if (userChoice.equals("b")) {
                    break;
                }
                else
                    System.out.println("Invalid choice!");
            }
        }//entering city loop

        while (!Checks.isValidUserType(userType))
        {
            char ut;
            System.out.println("Choose your level");
            System.out.println("1. Product owner");
            System.out.println("2. Row material Supplier");
            System.out.println("3. Regular User");
            ut = scanner.next().charAt(0);
            if(isDigit(ut))
                userType = ut - '0';

            if (!Checks.isValidUserType(userType))
            {
                System.out.println("Invalid user level! Try again");
                System.out.println("    a. Enter user level again");
                System.out.println("    b. Exit");
                userChoice = scanner.next();
                if(userChoice.equals("a"))
                    continue;
                else if (userChoice.equals("b")) {
                    break;
                }
                else
                    System.out.println("Invalid choice!");
            }
        }//entering user type loop

        User u = new User(username, password, userEmail, city, userType+1);
        return u;
    }
    private static void communicateWithUser(String userEmail, int userTypeToCommunicate)
    {
        String receiverEmail = null;
        Scanner scanner = new Scanner(System.in);
        Listing.listAllUsersInTheSystem(userTypeToCommunicate);

        System.out.println("Enter user email you want to communicate with:");
        //scanner.nextLine();
        while(!Checks.checkIfEmailAlreadyUsed(receiverEmail))
        {
            receiverEmail = scanner.nextLine();
            if(Checks.checkIfEmailAlreadyUsed(receiverEmail))
                break;
            else
                System.out.println("Invalid email try again!");

        }

        System.out.println("Write your message:");
        String msg = scanner.nextLine();

        MyApp.Msg = new Messaging(userEmail, receiverEmail, msg);
        Updates.addNewMsg(MyApp.Msg);
        System.out.println("________________________Your message was sent successfully__________________________");
    }
}