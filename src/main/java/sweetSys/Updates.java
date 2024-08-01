package sweetSys;

import Entities.Database;
import Entities.User;

public class Updates
{
    public static void addNewUser(User user)
    {
        String un = user.getUname();
        String pass = user.getPassword();
        String email = user.getEmail();
        String location = user.getLocation();
        int uType = user.getUserType();

        String qry = "insert into sweetsystem.users values ('"+un+"', '"+pass+"', '"+email+"', '"+location+"', "+uType+");";
        Database.connectionToInsertOrUpdateDB(qry);
    }
}
