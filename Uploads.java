package BIN;
import java.sql.*;
import java.util.Random;

/**
 * Created by Jamil on 23-Dec-17.
 */
public class Uploads implements iUploads {
    private String userId;
    private String userUpload;
    private String uploadShortDescription;
    private String uploadCategory;
    private int uploadRating;
    private String uploadHeading;
    private String userMail;
    private String userName;
    private int mainPageIndex;
    private int uploadId;

    public Uploads()
    {
        this("undefined","undefined","undefined","undefined","undefined","undefined","undefined");
    }
    public Uploads(String id,String upload,String description,String category,String heading,String mail,String name)
    {
        this.userId=id;
        this.userUpload=upload;
        this.uploadShortDescription=description;
        this.uploadCategory=category;
        this.uploadRating=0;
        this.uploadHeading=heading;
        this.userMail=mail;
        this.userName=name;
        try {
            Connection con = DbConnectionManager.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM inventors_hub.uploads");
            int counter=0;
            while (rs.next())
            {
                counter++;
            }
            this.mainPageIndex=(counter/3);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        Random rand=new Random();
        this.uploadId=rand.nextInt(9999)+1000;
    }
    public void upload()
    {
        //System.out.println(this.userId+"\n"+this.uploadHeader+"\n"+this.userUpload+"\n"+this.userUploadDescription+"\n"+this.uploadCategory+"\n"+this.uploadRating);
        try{

            Connection con=DbConnectionManager.getConnection();
            String query="INSERT INTO inventors_hub.uploads (userId,upload,uploadShortDescription,uploadCategory,uploadRating,uploadHeader,userMail,userName,mainPageIndex,uploadId) VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement=con.prepareStatement(query);
            preparedStatement.setString(1,this.userId);
            preparedStatement.setString(2,this.userUpload);
            preparedStatement.setString(3,this.uploadShortDescription);
            preparedStatement.setString(4,this.uploadCategory);
            preparedStatement.setInt(5,this.uploadRating);
            preparedStatement.setString(6,this.uploadHeading);
            preparedStatement.setString(7,this.userMail);
            preparedStatement.setString(8,this.userName);
            preparedStatement.setInt(9,this.mainPageIndex);
            preparedStatement.setInt(10,this.uploadId);
            preparedStatement.execute();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public static boolean isNextEmpty(int index)
    {
        int isNextEmpty=0;
        try {
            Connection con = DbConnectionManager.getConnection();
            Statement stmt =con.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM inventors_hub.uploads WHERE mainPageIndex='"+index+"'");
            if(!rs.next())
            {
                isNextEmpty=1;
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        if(isNextEmpty==1)
        {
            return true;
        }
        else {
            return false;
        }
    }
    public static void increaseUploadRating(int currentRating,int uploadId)
    {
        try {
            Connection con = DbConnectionManager.getConnection();
            int nextRating=currentRating+1;
            String query = "UPDATE inventors_hub.uploads SET uploadRating = ? WHERE uploadId = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1,nextRating);
            preparedStatement.setInt(2,uploadId);
            preparedStatement.executeUpdate();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
