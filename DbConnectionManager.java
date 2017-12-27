package BIN;

/**
 * Created by Jamil on 24-Dec-17.
 */
import java.sql.*;
public class DbConnectionManager {
   private static String className="com.mysql.jdbc.Driver";
   private static String dbURL="jdbc:mysql://localhost:3306/inventors_hub";
   private static String userName="root";
   private static String dbPassWord="";
   private static Connection con;

   public static Connection getConnection()
   {
       try{
           Class.forName(className);
           try{
                con=DriverManager.getConnection(dbURL,userName,dbPassWord);

           }catch (Exception connectionNotEstablishedException)
           {
               connectionNotEstablishedException.printStackTrace();
           }
       }catch (Exception classNotFoundException)
       {
           classNotFoundException.printStackTrace();
       }
       return con;
   }

}
