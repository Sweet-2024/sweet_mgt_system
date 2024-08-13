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
        String receiverEmail;
        String msg;
        String materialName;
        String supplierEmail;
        int price = 0;
        int wholesalePrice = 0;
        int quantity = 0;
        int saledQty = 0;
        int productId = 0;
        int businessId = 0;
        int userTypeToCommunicate = 0;
        double discount = 0;
        MyApp.userType = 0;
        while (true)
        {
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
                while(true) {
                    System.out.println("Welcome Admin! choose what to do from the list:");
                    System.out.println("1. Accounts Management");
                    System.out.println("2. Reporting And Monitoring");
                    System.out.println("3. Exit");
                    char uc = scanner.next().charAt(0);

                    if (uc == '1') {
                        System.out.println("* Accounts Managements : ");
                        System.out.println("Lists of all users in the system : ");
                        Listing.listAllUsersInTheSystem(2);
                        System.out.println();

                        Listing.listAllUsersInTheSystem(3);
                        System.out.println();

                        Listing.listAllUsersInTheSystem(4);
                        System.out.println();

                        System.out.println("Choose what to do : ");
                        System.out.println("1. Add new user");
                        System.out.println("2. Update existing user information");
                        System.out.println("3. Delete existing user");

                        uc = scanner.next().charAt(0);
                        if (uc == '1') {
                            User u = signUp(null, null, null, null, 0);
                            Updates.addNewUser(u);
                        } else if (uc == '2') {
                            System.out.println("Enter the email of user you want to update : ");
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
                                        exit(0);
                                }
                            }
                            User u = signUp(ue, null, null, null, 0);
                            Updates.updateUser(u);
                            System.out.println("Updated Successfully");
                            System.out.println();
                        } else if (uc == '3') {
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
                                        exit(0);
                                }
                            }
                            Updates.deleteUser(ue);
                            System.out.println("Deleted Successfully");
                            System.out.println();
                        } else {

                        }
                    }
                }
            }//logged in as admin
            else if (MyApp.userType == 2)
            {
                while (true) {
                    System.out.println("Welcome Owner! choose what to do from the list:");
                    System.out.println("1. Product Management:");
                    System.out.println("2. Communication and Notification");
                    System.out.println("3. Accounts Management");
                    System.out.println("4. Order Management");
                    System.out.println("5. exit");
                    userChoice = scanner.next();
                    userChoice.trim();
                    if (userChoice.equals("1")) {
                        System.out.println("    a. Add new products.");
                        System.out.println("    b. update available products.");
                        System.out.println("    c. remove available products.");
                        System.out.println("    d. Sales and profits.");
                        System.out.println("    e. Best-selling products.");
                        System.out.println("    f. discount products.");
                        userChoice = scanner.next();
                        if (userChoice.equals("a")) {
                            System.out.println("Enter product name:");
                            scanner.nextLine();
                            productName = scanner.nextLine();

                            System.out.println("Enter product price:");
                            price = scanner.nextInt();

                            System.out.println("Enter wholesale price:");
                            wholesalePrice = scanner.nextInt();

                            System.out.println("Enter quantity:");
                            quantity = scanner.nextInt();

                            System.out.println("Enter saled quantity:");
                            saledQty = scanner.nextInt();

                            System.out.println("Enter expiration date (YYYY-MM-DD):");
                            scanner.nextLine();
                            exDate = scanner.nextLine();

                            MyApp.product = new Product(productName, price, wholesalePrice, quantity, saledQty, exDate, userEmail);
                            addNewProduct(MyApp.product);
                            System.out.println("_________________________Successfully added_________________________");
                        } else if (userChoice.equals("b")) {
                            System.out.println("List of existing raw materials:");
                            Listing.listingOfProducts();

                            System.out.println("Enter product id:");
                            productId = scanner.nextInt();

                            System.out.println("Enter product name:");
                            scanner.nextLine();
                            productName = scanner.nextLine();

                            System.out.println("Enter product price:");
                            price = scanner.nextInt();

                            System.out.println("Enter wholesale price:");
                            wholesalePrice = scanner.nextInt();

                            System.out.println("Enter quantity:");
                            quantity = scanner.nextInt();

                            System.out.println("Enter saled quantity:");
                            saledQty = scanner.nextInt();

                            System.out.println("Enter expiration date (YYYY-MM-DD):");
                            scanner.nextLine();
                            exDate = scanner.nextLine();

                            MyApp.product = new Product(productId, productName, price, wholesalePrice, quantity, saledQty, exDate, userEmail);
                            updateProduct(MyApp.product);
                            System.out.println("_________________________Successfully updated_________________________");
                        } else if (userChoice.equals("c")) {
                            System.out.println("List of existing raw materials:");
                            Listing.listingOfProducts();

                            System.out.println("Enter the product name you would like to delete:");
                            scanner.nextLine();
                            productName = scanner.nextLine();
                            deleteProduct(productName);
                            System.out.println("____________________Deletion completed successfully___________________");
                        } else if (userChoice.equals("d")) {
                            Listing.generateFinancialReports();
                            System.out.println("__________________________________________________");
                        } else if (userChoice.equals("e")) {
                            Listing.listingBestSellingProduct();
                            System.out.println("__________________________________________________");
                        } else if (userChoice.equals("f")) {
                            System.out.println("Enter discount number (e.g: 0.20 for 20%):");
                            discount = scanner.nextDouble();
                            Updates.productDiscount(discount);
                            System.out.println("_________________The discount has been successfully applied_____________________");
                        }

                    } else if (userChoice.equals("2")) {
                        System.out.println("    a. Communicate with users");
                        System.out.println("    b. Communicate with suppliers");
                        System.out.println("    c. Communicate with owners");
                        userChoice = scanner.next();
                        if (userChoice.equals("a")) {
                            userTypeToCommunicate = 4;
                            Listing.listAllUsersInTheSystem(userTypeToCommunicate);

                            System.out.println("Enter user email you want to communicate with:");
                            scanner.nextLine();
                            receiverEmail = scanner.nextLine();

                            System.out.println("Write your message:");
                            //scanner.nextLine();
                            msg = scanner.nextLine();

                            MyApp.Msg = new Messaging(userEmail, receiverEmail, msg);
                            Updates.addNewMsg(MyApp.Msg);
                            System.out.println("________________________Your message was sent successfully__________________________");
                        } else if (userChoice.equals("b")) {
                            userTypeToCommunicate = 3;
                            Listing.listAllUsersInTheSystem(userTypeToCommunicate);

                            System.out.println("Enter user email you want to communicate with:");
                            scanner.nextLine();
                            receiverEmail = scanner.nextLine();

                            System.out.println("Write your message:");
                            //scanner.nextLine();
                            msg = scanner.nextLine();

                            MyApp.Msg = new Messaging(userEmail, receiverEmail, msg);
                            Updates.addNewMsg(MyApp.Msg);
                            System.out.println("________________________Your message was sent successfully__________________________");
                        } else if (userChoice.equals("c")) {
                            userTypeToCommunicate = 2;
                            Listing.listAllUsersInTheSystem(userTypeToCommunicate);

                            System.out.println("Enter user email you want to communicate with:");
                            scanner.nextLine();
                            receiverEmail = scanner.nextLine();

                            System.out.println("Write your message:");
                            //scanner.nextLine();
                            msg = scanner.nextLine();

                            MyApp.Msg = new Messaging(userEmail, receiverEmail, msg);
                            Updates.addNewMsg(MyApp.Msg);
                            System.out.println("________________________Your message was sent successfully__________________________");
                        }
                    } else if (userChoice.equals("3")) {
                        System.out.println("    a. Update your account.");
                        System.out.println("    b. Business management.");
                        userChoice = scanner.next();
                        if (userChoice.equals("a")) {
                            //****************************************************************//
                        } else if (userChoice.equals("b")) {
                            System.out.println("Enter business id:");
                            businessId = scanner.nextInt();

                            System.out.println("Enter business name:");
                            scanner.nextLine();
                            businessName = scanner.nextLine();

                            System.out.println("Enter business location:");
                            businessLocation = scanner.nextLine();

                            MyApp.business = new Business(businessId, businessName, businessLocation, userEmail);
                            Updates.updateBusinessInfo(MyApp.business);
                            System.out.println("________________________Successfully updated__________________________");
                        }
                    } else if (userChoice.equals("4")) {
                        System.out.println("List of existing raw materials:");
                        Listing.listingOfRawMaterials();

                        System.out.println("Please enter the name of the supplier you wish to purchase from:");
                        scanner.nextLine();
                        supplierEmail = scanner.nextLine();

                        ArrayList<String> items = new ArrayList<>();
                        ArrayList<Integer> qtyList = new ArrayList<>();

                        while (true) {
                            System.out.println("Enter the name of the raw material you want to order (or type 'done' to finish):");
                            materialName = scanner.nextLine();

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
                    } else if (userChoice.equals("5")) {
                        return;
                    }
                }
            }
            else if (MyApp.userType == 3)
            {
                while (true) {
                    System.out.println("Welcome Supplier! choose what to do from the list:");
                    System.out.println("1. Raw material Management:");
                    System.out.println("2. Communication and Notification");
                    System.out.println("3. exit");
                    userChoice = scanner.next();
                    userChoice.trim();
                    if (userChoice.equals("1")) {
                    }
                }
            }
            else if (MyApp.userType == 4)
            {
                while(true) {
                    System.out.println("Welcome User! choose what to do from the list:");
                }
            }
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
                exit(0);
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
                    exit(0);
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
                    exit(0);
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
                    exit(0);
                }
                else
                    System.out.println("Invalid choice!");
            }
        }//entering city loop

        while (!Checks.isValidUserType(MyApp.userType))
        {
            char ut;
            System.out.println("Choose your level");
            System.out.println("1. Product owner");
            System.out.println("2. Row material Supplier");
            System.out.println("3. Regular User");
            ut = scanner.next().charAt(0);
            if(isDigit(ut))
                MyApp.userType = ut - '0';

            if (!Checks.isValidUserType(MyApp.userType))
            {
                System.out.println("Invalid user level! Try again");
                System.out.println("    a. Enter user level again");
                System.out.println("    b. Exit");
                userChoice = scanner.next();
                if(userChoice.equals("a"))
                    continue;
                else if (userChoice.equals("b")) {
                    exit(0);
                }
                else
                    System.out.println("Invalid choice!");
            }
        }//entering user type loop

        User u = new User(username, password, userEmail, city, MyApp.userType);
        return u;
    }
}