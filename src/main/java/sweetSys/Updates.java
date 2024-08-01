package sweetSys;

import Entities.Database;
import Entities.User;
import java.sql.ResultSet;
import java.sql.SQLException;
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
}
