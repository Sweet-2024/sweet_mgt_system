package sweetSys;

import Entities.Database;
import Entities.User;
import Entities.Product;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Updates {
    public static boolean updateBusinessInfo(String bName, String bLocation, int bId)
    {
        try {
            String qry1 = "update sweetsystem.business set business_name = '"+bName+"', business_location = '"+bLocation+"' where business_id = "+bId+" ;";
            Database.connectionToInsertOrUpdateDB(qry1);

            String qry2 = "select * from sweetsystem.business where business.business_name = '"+bName+"' and business.business_location = '"+bLocation+"' ;";
            ResultSet rs2 = Database.connectionToSelectFromDB(qry2);
            if(rs2.next()){
                return true;
            }
            else {
                return false;
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
            return false;
        }
    }
    public static void addNewUser(User user)
    {
        String un = user.getUname();
        String pass = user.getPassword();
        String email = user.getEmail();
        String location = user.getLocation();
        int uType = user.getType();

        if(!Checks.checkIfEmailAlreadyUsed(email))
        {
            String qry = "insert into sweetsystem.users values ('" + un + "', '" + pass + "', '" + email + "', '" + location + "', " + uType + ");";
            Database.connectionToInsertOrUpdateDB(qry);
        }
        else
        {
            System.out.println("This email already used, please try again with another email address!");
        }
    }
    public static void deleteUser(String email)
    {
        if(!Checks.checkIfEmailAlreadyUsed(email))
        {
            System.out.println("This email doesn't exist, please try again with another email address!");
        }
        else
        {
            String qry = "delete from sweetsystem.users where user_email = '"+email+"';";
            Database.connectionToInsertOrUpdateDB(qry);
        }

    }
    public static void updateUser(User user)
    {
        String un = user.getUname();
        String pass = user.getPassword();
        String email = user.getEmail();
        String location = user.getLocation();
        int uType = user.getType();

        if(!Checks.checkIfEmailAlreadyUsed(email))
        {
            System.out.println("This email doesn't exist, please try again with another email address!");
        }
        else
        {
            String qry = "update sweetsystem.users set username= '"+un+"', user_password = '"+pass+"', user_location = '"+location+"', user_type = "+uType+" where user_email = '"+email+"';";
            Database.connectionToInsertOrUpdateDB(qry);
        }

    }
    public static void addNewProduct(Product product)
    {
        String pName = product.getProductName();
        int price = product.getPrice();
        int wholesalePrice = product.getWholesalePrice();
        int quantity = product.getQuantity();
        int saledQty = product.getSaledQty();
        String exDate = product.getExDate();
        String ownerEmail = product.getOwnerEmail();

        if(!Checks.checkIfProductInDatabase(pName))
        {
            String qry = "insert into sweetsystem.product (`product_name`, `price`, `wholesale_price`, `quantity`, `saled_qty`, `ex_date`, `owner_email`)  values ('"+pName+"', "+price+", "+wholesalePrice+", "+quantity+", "+saledQty+", '"+exDate+"', '"+ownerEmail+"');";
            Database.connectionToInsertOrUpdateDB(qry);
        }
        else
        {
            System.out.println("This product already exist, please try again with another product name!");
        }
    }
    public static void deleteProduct(String productName)
    {
        if(!Checks.checkIfProductInDatabase(productName))
        {
            System.out.println("This product doesn't exist, please try again with another product name!");
        }
        else
        {
            String qry = "delete from sweetsystem.product where product_name = '"+productName+"';";
            Database.connectionToInsertOrUpdateDB(qry);
        }
    }
    public static void updateProduct(Product product)
    {
        int id = product.getProductId();
        String pName = product.getProductName();
        int price = product.getPrice();
        int wholesalePrice = product.getWholesalePrice();
        int quantity = product.getQuantity();
        int saledQty = product.getSaledQty();
        String exDate = product.getExDate();
        String ownerEmail = product.getOwnerEmail();

        if(!Checks.checkIfProductInDatabase(pName))
        {
            System.out.println("This product doesn't exist, please try again with another product name!");
        }
        else
        {
            String qry = "update sweetsystem.product set product_name= '"+pName+"', price = "+price+", wholesale_price = "+wholesalePrice+", quantity = "+quantity+", saled_qty = "+saledQty+", ex_date = '"+exDate+"', owner_email = '"+ownerEmail+"' where product_id = "+id+";";
            Database.connectionToInsertOrUpdateDB(qry);
        }
    }
    public static boolean productDiscount(double discount)
    {
        int productId = 0; int price; int discountedPrice = 0;
        String qry1 = "select * from sweetsystem.product";
        ResultSet rs = Database.connectionToSelectFromDB(qry1);
        try {
            if(rs.next())
            {
                String qry2 = "SELECT product_id, price FROM product WHERE ex_date = (SELECT MIN(ex_date) FROM product);";
                ResultSet ProductsWithSoonExpiryDate = Database.connectionToSelectFromDB(qry2);
                while(ProductsWithSoonExpiryDate.next())
                {
                    productId = ProductsWithSoonExpiryDate.getInt("product_id");
                    price = ProductsWithSoonExpiryDate.getInt("price");
                    discountedPrice = (int) (price * (1 - discount));

                }
                String qry3 ="UPDATE sweetsystem.product SET price = "+discountedPrice+" WHERE product_id ="+productId+"";
                Database.connectionToInsertOrUpdateDB(qry3);
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
}
