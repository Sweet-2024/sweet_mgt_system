package sweetSys;

import Entities.Database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Listing {

    private static void printingFinantialReportOfOwnersOrSuppliers(String email)
    {
        int price = 0, wholesale_price = 0, saledQty = 0, incomes = 0, outcomes = 0;
        String qry3 = "select * from sweetsystem.product where owner_email = '" + email + "'";
        ResultSet ProductsOfOwnerList = Database.connectionToSelectFromDB(qry3);
        try{
            while (ProductsOfOwnerList.next()) {
                price = ProductsOfOwnerList.getInt("price");
                wholesale_price = ProductsOfOwnerList.getInt("wholesale_price");
                saledQty = ProductsOfOwnerList.getInt("saled_qty");
                incomes += price * saledQty;
                outcomes += wholesale_price * saledQty;
            }
            int total = incomes - outcomes;
            System.out.println("\t\tIncomes : " + incomes);
            System.out.println("\t\tOutcomes : " + outcomes);
            System.out.println("\t\tTotal profit : " + total);
            System.out.println();
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }

    }

    private static void printingBestSellingProduct(String email)
    {
        String qry3 = "select product_name from sweetsystem.product where owner_email = '" + email + "' order by saled_qty desc;";
        ResultSet bestSelling = Database.connectionToSelectFromDB(qry3);
        try
        {
            if (bestSelling.next()) {

                System.out.println("\t\tBest selling product : " + bestSelling.getString("product_name"));
                System.out.println();
            }
        }
        catch (SQLException e)
        {

        }
    }
    public static boolean generateFinanialReports()
    {
        String ownerEmail;
        String qry1 = "select * from sweetsystem.product";
        ResultSet rs = Database.connectionToSelectFromDB(qry1);
        try
        {
            if(rs.next()) // if there are products in the db
            {
                System.out.println("---------------------------------------------------------------------------------");

                //if the user is owner or supplier then printing only his won report
                if (MyApp.userType == 2 || MyApp.userType == 3)
                {
                    System.out.println("FINANAIL REPORT OF YOUR STORE :");
                    ownerEmail = MyApp.userEmail;
                    printingFinantialReportOfOwnersOrSuppliers(ownerEmail);
                }
                //if the user is admin then printing reports for all owners and suppliers in the system
                else if (MyApp.userType == 1)
                {
                    System.out.println("FINANAIL REPORT OF EACH STORE :");
                    String qry2 = "select * from sweetsystem.users where user_type = 2 or user_type = 3";
                    ResultSet ownersAndSuppliersList = Database.connectionToSelectFromDB(qry2);
                    while (ownersAndSuppliersList.next()) {
                        System.out.println("\t* Product Owner Name : " + ownersAndSuppliersList.getString("username"));
                        ownerEmail = ownersAndSuppliersList.getString("user_email");
                        printingFinantialReportOfOwnersOrSuppliers(ownerEmail);
                    }//end of while to get all owners and suppliers in the system
                }



                return true;
            }
            else
            {
                System.out.println("there is no product in the system!");
                return false;
            }
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public static boolean listingBestSellingProduct()
    {
        String qry1, qry2;
        qry1 = "select * from sweetsystem.product";
        ResultSet rs = Database.connectionToSelectFromDB(qry1);
        try {
            if (rs.next()) // if there are products in the db
            {
                System.out.println("---------------------------------------------------------------------------------");

                if(MyApp.userType == 2 || MyApp.userType == 3)
                {
                    System.out.println("LIST OF BEST SELLING PRODUCTS IN YOUR STORE :");
                    printingBestSellingProduct(MyApp.userEmail);
                    return true;
                }

                else if (MyApp.userType == 1)
                {
                    System.out.println("LIST OF BEST SELLING PRODUCTS IN EACH STORE :");
                    qry2 = "select * from sweetsystem.users where user_type = 2 or user_type = 3";
                    ResultSet ownersAndSuppliersList = Database.connectionToSelectFromDB(qry2);
                    while (ownersAndSuppliersList.next()) {
                        String email = ownersAndSuppliersList.getString("user_email");
                        System.out.println("\t* Product owner name : " + ownersAndSuppliersList.getString("username"));
                        printingBestSellingProduct(email);
                    }
                }
                return true;
            }
            else
            {
                System.out.println("there is no product in the system!");
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static boolean statisticsOnUsersByCity()
    {
        String qry = "select user_location, count(user_email) from sweetsystem.users where user_type = 4 group by user_location";
        ResultSet rs = Database.connectionToSelectFromDB(qry);
        try {
            if (rs.next())
            {
                System.out.println("---------------------------------------------------------------------------------");
                System.out.println("STATISTICS ON USERS GATHERED BY CITY : ");
                System.out.println("City\t\tNumber of users");
                System.out.println(rs.getString(1)+"\t\t"+rs.getInt(2));
                while (rs.next())
                {
                    System.out.println(rs.getString(1)+"\t\t"+rs.getInt(2));
                }
                System.out.println("---------------------------------------------------------------------------------");
                return true;
            }
            else return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
