package sweetSys;

import Entities.*;

import Entities.Order;
import Entities.User;
import Entities.Product;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Updates {
    public static void updateBusinessInfo(Business business) {
        int bId = business.getBusinessId();
        String bName = business.getBusinessName();
        String bLocation = business.getBusinessLocation();
        String ownerEmail = business.getBusinessOwnerEmail();

        if (!Checks.checkIfBusinessIdAlreadyUsed(bId)) {
            System.out.println("This business id doesn't exist, please try again with another business id!");
        } else {
            String qry1 = "update sweetsystem.business set business_name = '" + bName + "', business_location = '" + bLocation + "', business_owner_email ='" + ownerEmail + "' where business_id = " + bId + " ;";
            Database.connectionToInsertOrUpdateDB(qry1);
        }
    }

    public static void addNewUser(User user)
    {
        String un = user.getUname();
        String pass = user.getPassword();
        String email = user.getEmail();
        String location = user.getLocation();
        int uType = user.getType();

        if (Checks.isValidUsername(un)
                && Checks.isvalidPassword(pass)
                && Checks.isValidEmail(email)
                && Checks.isValidCity(location)
                && Checks.isValidUserType(uType)
                && !Checks.checkIfEmailAlreadyUsed(email))
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
        String message = msg.getMsg().replace('\'', ' ' );
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
    public static void deleteProduct(int productId)
    {
        if(!Checks.checkIfProductInDbAccordingToId(productId))
        {
            System.out.println("This product doesn't exist, please try again with another product name!");
        }
        else
        {
            String qry = "delete from sweetsystem.product where product_id = '"+productId+"';";
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

        if(!Checks.checkIfProductInDbAccordingToId(id))
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
        int productId = 0; int price = 0; int discountedPrice = 0;
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
                System.out.println("Discount the product id "+productId+" with price "+price+"₪ ("+discount+") to become "+discountedPrice+"₪");
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
                String qry2 = "SELECT rm_id FROM row_material WHERE rm_name = '"+items.get(i)+"' ";
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

                String qry3 = "INSERT INTO order_material(order_id, rm_id, qty) VALUES ("+orderId+","+rowMaterialId+", "+qty.get(i)+")";
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

    public static void addNewRawMaterial(rawMaterial rawMaterial)
    {
        String rmName = rawMaterial.getRmName();
        int price = rawMaterial.getPrice();
        int wholesalePrice = rawMaterial.getWholesalePrice();
        int quantity = rawMaterial.getQuantity();
        int saledQty = rawMaterial.getSaledQty();
        String exDate = rawMaterial.getExDate();
        String supplierEmail = rawMaterial.getSupplierEmail();

        if(!Checks.checkIfRowMaterialInDatabase(rmName))
        {
            String qry = "insert into `row_material`(`rm_name`, `rm_price`, `qty`, `wholesale_price`, `saled_qty`, `expiry_date`, `supplier_email`) values ('"+rmName+"', "+price+", "+quantity+", "+wholesalePrice+",  "+saledQty+", '"+exDate+"', '"+supplierEmail+"');";
            Database.connectionToInsertOrUpdateDB(qry);
        }
        else
        {
            System.out.println("This raw material already exist, please try again with another raw material name!");
        }
    }

    public static void updateRawMaterial(rawMaterial rawMaterial) {
        int id = rawMaterial.getRmId();
        String rmName = rawMaterial.getRmName();
        int price = rawMaterial.getPrice();
        int wholesalePrice = rawMaterial.getWholesalePrice();
        int quantity = rawMaterial.getQuantity();
        int saledQty = rawMaterial.getSaledQty();
        String exDate = rawMaterial.getExDate();
        String supplierEmail = rawMaterial.getSupplierEmail();

        if(!Checks.checkIfRowMaterialInDatabase(rmName))
        {
            System.out.println("This raw material doesn't exist, please try again with another raw material name!");
        }
        else
        {
            String qry = "UPDATE `row_material` SET `rm_name`='"+rmName+"',`rm_price`="+price+",`qty`="+quantity+",`wholesale_price`="+wholesalePrice+",`saled_qty`="+saledQty+",`expiry_date`='"+exDate+"',`supplier_email`='"+supplierEmail+"' WHERE `rm_id`="+id+";";
            Database.connectionToInsertOrUpdateDB(qry);
        }
    }

    public static void deleteRawMaterial(int rmId) {
        if(!Checks.checkIfRowMaterialInDbAccordingToID(rmId))
        {
            System.out.println("This raw material doesn't exist, please try again with another raw material name!");
        }
        else
        {
            String qry = "DELETE FROM `row_material` WHERE rm_id = '"+rmId+"';";
            Database.connectionToInsertOrUpdateDB(qry);
        }
    }

    public static void addNewFeedback(Feedback f, int productType)
    {
        int productID = f.getProductID();
        int evaluation = f.getEvaluation();
        if(productType == 1)//feedback on a product of owner
        {
            String qry = "INSERT INTO feedback( product_id, evaluation) VALUES (" + productID + "," + evaluation + ")";
            Database.connectionToInsertOrUpdateDB(qry);
        }
        else if(productType == 2)//feedback on a shared recipe
        {
            String qry = "INSERT INTO recipefeedback(recipe_id, evaluation) VALUES ("+productID+", "+evaluation+")";
            Database.connectionToInsertOrUpdateDB(qry);
        }
    }
}
