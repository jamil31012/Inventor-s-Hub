package BIN;
import java.sql.*;
import BIN.DbConnectionManager;
/**
 * Admin Class
 * Package BIN
 * Database Inventors Hub
 * Table adminInfo
 */
public class Admin {

    //class and instance variables
    private String adminName;
    private String adminId;
    private static Admin adminUser=new Admin();

    //constructor parameter-less
    public Admin()
    {
        this("Unknown","Not set");
    }

    //constructor with parameter
    public Admin(String name,String id)
    {
        this.adminName=name;
        this.adminId=id;
    }

    //methods

    public static boolean verifyAdmin(String id,String password)
    {
        int found=0;
        try {
            Connection con=DbConnectionManager.getConnection();
            Statement stmt=con.createStatement();
            ResultSet rs;
            rs=stmt.executeQuery("SELECT * FROM admininfo");
            while (rs.next())
            {
                if(rs.getString(1).equals(id) && rs.getString(3).equals(password))
                {
                    found=1;
                    adminUser.adminName=rs.getString(2);
                    adminUser.adminId=rs.getString(1);

                }
            }
            con.close();
        }catch (Exception e)
        {
            System.out.println(e);
        }
        if(found==1)
        {
            return true;
        }
        else {
            return false;
        }
    }
    public static String getAdminName()
    {
        return adminUser.adminName;
    }
    public static String getAdminId()
    {
        return adminUser.adminId;
    }

    public static boolean removeUser(String id)
    {
        int found=0;
        try {
            Connection con=DbConnectionManager.getConnection();
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM inventors_hub.registereduser");
            while (rs.next())
            {
                if(rs.getString(2).equals(id))
                {
                    found=1;
                    break;
                }
            }
            if(found==1)
            {
                String query="DELETE FROM inventors_hub.registereduser WHERE userId=?";
                PreparedStatement preparedStatement=con.prepareStatement(query);
                preparedStatement.setString(1,id);
                preparedStatement.executeUpdate();
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        if(found==1)
        {
            return true;
        }
        else {
            return false;
        }
    }
}
