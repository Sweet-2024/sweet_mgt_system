package org.example;

import Entities.User;
import sweetSys.Checks;
import sweetSys.MyApp;
import sweetSys.Updates;

import java.util.Scanner;

import static java.lang.Character.isDigit;
import static java.lang.System.exit;

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
                Updates.addNewUser(u);
                MyApp.user = u;
            }//end of sign up if statement

            else if (userChoice.equals("3"))
                exit(0);
            else
                System.out.println("Invalid choice");

        }
    }
}