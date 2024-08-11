package sweetSys;

import Entities.Database;
import Entities.Order;
import Entities.User;
import Entities.Product;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Updates {
    public static boolean updateBusinessInfo(String bName, String bLocation, int bId, String ownerEmail)
    {
        try {
            String qry1 = "update sweetsystem.business set business_name = '"+bName+"', business_location = '"+bLocation+"', business_owner_email ='"+ownerEmail+"' where business_id = "+bId+" ;";
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
    public static void addNewOrderForRowMaterials(Order order)
    {
        String seller = order.getSellerEmail();
        String buyer = order.getBuyerEmail();
        LocalDateTime date = order.getDate();
        ArrayList<String> items = order.getItemList();
        ArrayList<Integer> qty = order.getItemQty();
        int orderId= 0;

        String qry1 = "SELECT order_id FROM sweetsystem.order order BY order_id DESC;";
        ResultSet rs = Database.connectionToSelectFromDB(qry1);
        try {
            if (rs.next()){
                orderId = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        orderId ++;
        String qry = "INSERT INTO `order`(`order_id`, `seller_email`, `order_date`, `buyer_email`) VALUES ("+orderId+",'"+seller+"','"+date+"','"+buyer+"')";
        Database.connectionToInsertOrUpdateDB(qry);

        for(int i = 0 ; i < items.size() ; i++)
        {
            if(Checks.checkIfRowMaterialInDatabase(items.get(i)))
            {
                String qry2 = "SELECT `rm_id` FROM `row_material` WHERE rm_name = '"+items.get(i)+"' ";
                ResultSet rs2 = Database.connectionToSelectFromDB(qry2);
                int rowMaterialId = -1;
                try {
                    if (rs2.next())
                    {
                        rowMaterialId = rs2.getInt(1);
                        qry2 = "select * from order_material where order_id = "+orderId+" and rm_id = "+rowMaterialId+" and qty = " + qty.get(i);
                        ResultSet rs3 = Database.connectionToSelectFromDB(qry2);
                        if (rs3.next())
                        {
                            System.out.println("Already ordered! ");
                            continue;
                        }
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                String qry3 = "INSERT INTO `order_material`(`order_id`, `rm_id`, `qty`) VALUES ("+orderId+","+rowMaterialId+", "+qty.get(i)+")";
                Database.connectionToInsertOrUpdateDB(qry3);
            }
            else
            {
                System.out.println(items.get(i) + " is not in the system, please try again");
            }
        }
    }
    public static void updateYourOwnAccount(User user)
    {
        String un = user.getUname();
        String pass = user.getPassword();
        String email = user.getEmail();
        String location = user.getLocation();
        int uType = user.getType();
        if (MyApp.userType == 4){
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
    }
}
