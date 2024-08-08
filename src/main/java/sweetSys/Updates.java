package sweetSys;

import Entities.Database;
import Entities.User;
import Entities.Product;
import java.sql.ResultSet;

public class Updates {
    public static boolean updateBusinessInfo(String bName, String bLocation, int bId)
    {
        try {
            String qry1 = "update sweetsys.business set business_name = '"+bName+"', business_location = '"+bLocation+"' where business_id = "+bId+" ;";
            Database.connectionToInsertOrUpdateDB(qry1);

            String qry2 = "select * from sweetsys.business where business.business_name = '"+bName+"' and business.business_location = '"+bLocation+"' ;";
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

        String qry = "insert into sweetsys.users values ('"+un+"', '"+pass+"', '"+email+"', '"+location+"', "+uType+");";
        Database.connectionToInsertOrUpdateDB(qry);
    }
    public static void deleteUser(String email)
    {
        String qry = "delete from sweetsys.users where user_email = '"+email+"';";
        Database.connectionToInsertOrUpdateDB(qry);
    }

    public static void updateUser(User user)
    {
        String un = user.getUname();
        String pass = user.getPassword();
        String email = user.getEmail();
        String location = user.getLocation();
        int uType = user.getType();

        String qry = "update sweetsys.users set username= '"+un+"', user_password = '"+pass+"', user_location = '"+location+"', user_type = "+uType+" where user_email = '"+email+"';";
        Database.connectionToInsertOrUpdateDB(qry);
    }
    public static void addNewProduct(Product product)
    {
        int id = product.getProductId();
        String pName = product.getProductName();
        int price = product.getPrice();
        int wholesalePrice = product.getWholesalePrice();
        int quantity = product.getQuantity();
        int saledQty = product.getSaledQty();
        String exDate = product.getExDate();
        String ownerEmail = product.getOwnerEmail();

        String qry = "insert into sweetsys.Product values ("+id+", '"+pName+"', "+price+", "+wholesalePrice+", "+quantity+", "+saledQty+", '"+exDate+"', '"+ownerEmail+"');";
        Database.connectionToInsertOrUpdateDB(qry);
    }
    public static void deleteProduct(String productName)
    {
        String qry = "delete from sweetsys.Product where product_name = '"+productName+"';";
        Database.connectionToInsertOrUpdateDB(qry);
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

        String qry = "update sweetsys.Product set product_name= '"+pName+"', price = "+price+", wholesale_price = "+wholesalePrice+", quantity = "+quantity+", saled_qty = "+saledQty+", ex_date = '"+exDate+"', owner_email = '"+ownerEmail+"' where product_id = "+id+";";
        Database.connectionToInsertOrUpdateDB(qry);
    }
}
