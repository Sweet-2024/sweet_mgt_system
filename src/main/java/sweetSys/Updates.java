package sweetSys;

import Entities.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class Updates
{
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

    public static void addNewRecipe(Recipe recipe)
    {
        String rname = recipe.getRecipeName();
        String rDesc = recipe.getRecipeDescription();
        String rCate = recipe.getRecipeCate();
        String pubEmail = recipe.getPublisherEmail();

        if (Checks.isAcceptableRecipeName(rname)
                && Checks.isAcceptableRecipeDescription(rDesc)
                && Checks.isAcceptableRecipeCategory(rCate)
                && Checks.checkIfEmailAlreadyUsed(pubEmail)
                && !Checks.isExistingRecipe(recipe))
        {
            rCate = rCate.trim();
            String qry = "INSERT INTO sweetsystem.recipe(recipe_name, recipe_description, recipe_category, recipe_publisher_email) VALUES ( '" + rname + "', '" + rDesc + "', '" + rCate + "', '" + pubEmail + "');";
            Database.connectionToInsertOrUpdateDB(qry);
            System.out.println("The recipe published successfully :) ");
        }
        else
            System.err.println("Error in recipe info! try Again ");
    }

    public static void addNewMsg(Messaging msg)
    {
        String sender = msg.getSenderEmail();
        String receiver = msg.getReceiverEmail();
        String message = msg.getMsg();
        LocalDateTime msgDate = LocalDateTime.now();
        if(Checks.isMsgInTheSystem(msg))
        {
            System.out.println("the msg already in the system");
        }
        else
        {
            String addMsgQry = "INSERT INTO `message` ( `sender`, `receiver`, `msg`, `date`) VALUES ('"+sender+"', '"+receiver+"', '"+message+"', '"+msgDate+"');";
            Database.connectionToInsertOrUpdateDB(addMsgQry);
        }
    }

    public static void addNewOrderForProduct(Order order)
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
            if(Checks.checkIfProductInDatabase(items.get(i)))
            {
                String qry2 = "SELECT `product_id` FROM `product` WHERE product_name = '"+items.get(i)+"' ";
                ResultSet rs2 = Database.connectionToSelectFromDB(qry2);
                int productId = -1;
                try {
                    if (rs2.next())
                    {
                        productId = rs2.getInt(1);
                        qry2 = "select * from order_product where order_id = "+orderId+" and product_id = "+productId+" and qty = " + qty.get(i);
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

                String qry3 = "INSERT INTO `order_product`(`order_id`, `product_id`, `qty`) VALUES ("+orderId+","+productId+", "+qty.get(i)+")";
                Database.connectionToInsertOrUpdateDB(qry3);
            }
            else
            {
                System.out.println(items.get(i) + " product is not in the system, please try again");
            }
        }
    }
}//end of class
