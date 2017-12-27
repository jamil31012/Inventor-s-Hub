package BIN;

import javax.swing.*;
import java.sql.*;
/**
 * Created by Jamil on 19-Dec-17.
 */
public class RegisterUser implements iUserRegistrationClass{
    private String userName;
    private String userId;
    private String userPassword;
    private int totalSubmissions;
    private String userMail;
    private static RegisterUser userInfo=new RegisterUser();
    public RegisterUser()
    {
        this("Undefined","Undefined","Undefined","Undefined",0);
    }
    public RegisterUser(String name,String id,String pass,String mail,int submissions)
    {
        this.userName=name;
        this.userId=id;
        this.userPassword=pass;
        this.userMail=mail;
        this.totalSubmissions=submissions;
    }
    public void commit()
    {
        try{
            Connection con=DbConnectionManager.getConnection();
            String query="INSERT INTO inventors_hub.registereduser (userName, userId, userPassword, userMail, userTotalSubmissions) VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement=con.prepareStatement(query);
            preparedStatement.setString(1,this.userName);
            preparedStatement.setString(2,this.userId);
            preparedStatement.setString(3,this.userPassword);
            preparedStatement.setString(4,this.userMail);
            preparedStatement.setInt(5,this.totalSubmissions);
            preparedStatement.execute();
            con.close();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public static boolean verifyUser(String id,String pass) {
        int userFound = 0;
        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventors_hub", "root", "");
            ResultSet rs;
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM inventors_hub.registereduser");
            while (rs.next()) {
                if (rs.getString(2).equals(id) && rs.getString(3).equals(pass)) {
                    userFound = 1;
                    userInfo=new RegisterUser(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5));
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(userFound==1) {
            return true;
        }
        else {
            return false;
        }
    }
    public static String getUserName()
    {
        return userInfo.userName;
    }
    public static String getUserId()
    {
        return userInfo.userId;
    }
    public static String getUserMail()
    {
        return userInfo.userMail;
    }
    public static int getTotalSubmissions()
    {
        return userInfo.totalSubmissions;
    }
    public static boolean verifyAlreadyTakenId(String id)
    {
        int alreadyTaken=0;
        try {
            Connection con = DbConnectionManager.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM inventors_hub.registereduser");
            while (rs.next())
            {
                if(rs.getString(2).equals(id))
                {
                    alreadyTaken=1;
                    break;
                }
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        if(alreadyTaken==1)
        {
            return true;
        }
        else{
            return false;
        }
    }
    public static boolean updateInfo(String newInfo,String type)
    {
        int updateSuccess=0;
        if(type.equals("name"))
        {
            userInfo.userName=newInfo;
        }
        if(type.equals("id"))
        {
            userInfo.userId=newInfo;
        }
        if(type.equals("mail"))
        {
            userInfo.userMail=newInfo;
        }
        try {
            Connection con = DbConnectionManager.getConnection();
            String updateNameQuery="UPDATE inventors_hub.registereduser SET userName = ? WHERE userId=?";
            String updateMailQuery="UPDATE inventors_hub.registereduser SET userMail = ? WHERE userId=?";
            String updateIdQuery="UPDATE inventors_hub.registereduser SET userId = ? WHERE userMail=?";
            if(type.equals("name")) {
                PreparedStatement preparedStatement = con.prepareStatement(updateNameQuery);
                preparedStatement.setString(1, newInfo);
                preparedStatement.setString(2, userInfo.userId);
                preparedStatement.executeUpdate();
                String updateUploadQuery="UPDATE inventors_hub.uploads SET userName = ? WHERE userId = ?";
                PreparedStatement updateUploadPS=con.prepareStatement(updateUploadQuery);
                updateUploadPS.setString(1,newInfo);
                updateUploadPS.setString(2,userInfo.userId);
            }
            if(type.equals("mail"))
            {
                PreparedStatement preparedStatement = con.prepareStatement(updateMailQuery);
                preparedStatement.setString(1, newInfo);
                preparedStatement.setString(2, userInfo.userId);
                preparedStatement.executeUpdate();
            }
            if(type.equals("id"))
            {
                PreparedStatement preparedStatement = con.prepareStatement(updateIdQuery);
                preparedStatement.setString(1, newInfo);
                preparedStatement.setString(2, userInfo.userMail);
                preparedStatement.executeUpdate();
                String updateUploadQuery="UPDATE inventors_hub.uploads SET userId = ? WHERE userMail = ?";
                PreparedStatement updateUploadPS=con.prepareStatement(updateUploadQuery);
                updateUploadPS.setString(1,newInfo);
                updateUploadPS.setString(2,userInfo.userMail);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
            updateSuccess=1;
        }
        if(updateSuccess==0)
        {
            return true;
        }
        else{
            return false;
        }
    }
    public static int updatePassWord(String oldPass,String newPass)
    {
        int errorCode=0;
        if(userInfo.userPassword.equals(oldPass))
        {
            try {
                userInfo.userPassword = newPass;
                Connection con = DbConnectionManager.getConnection();
                String query = "UPDATE inventors_hub.registereduser SET userPassword = ? WHERE userId = ?";
                PreparedStatement preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1,newPass);
                preparedStatement.setString(2,userInfo.userId);
            }catch (Exception e)
            {
                e.printStackTrace();
                errorCode=2;//sql error
            }
        }
        else {
            errorCode=1;//pass does not match
        }
        return errorCode;
    }
    public static void increaseTotalSubmission(String userId)
    {
        try {
            Connection con=DbConnectionManager.getConnection();
            String query="UPDATE inventors_hub.registereduser SET userTotalSubmissions=userTotalSubmissions+1 WHERE userId=?";
            PreparedStatement preparedStatement=con.prepareStatement(query);
            preparedStatement.setString(1,userId);
            preparedStatement.executeUpdate();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
