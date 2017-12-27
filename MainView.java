package GUI;


import BIN.DbConnectionManager;
import BIN.Uploads;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


/**
 * Created by Jamil on 19-Dec-17.
 */
public class MainView {
    JFrame mainFrame;
    private int currentIndex;

    public MainView()
    {
        currentIndex=0;
        mainFrame=new JFrame("Main Window");
        mainFrame.setSize(800,900);
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setLayout(null);
        mainFrame.getContentPane().setBackground(new Color(80, 81, 84));
        mainFrameComponents();
    }
    public MainView(int nextIndex)
    {
        currentIndex=nextIndex;
        mainFrame=new JFrame("Main Window");
        mainFrame.setSize(800,900);
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setLayout(null);
        mainFrame.getContentPane().setBackground(new Color(80, 81, 84));
        mainFrameComponents();
    }
    private void mainFrameComponents()
    {
        //variable declarations
        JPanel topLogoPanel=new JPanel(null);
        JLabel topLogoPanelLabel=new JLabel("INVENTOR'S HUB");
        JButton loginButton=new JButton("Login");
        JButton userSignUpButton=new JButton("Sign Up");
        JLabel searchLabel=new JLabel("Search:");
        JTextField searchTF=new JTextField();
        JButton searchButton=new JButton("Search");
        JButton nextPageButton=new JButton("Next Page");
        JButton backButton=new JButton("Prev Page");


        //button settings
        if(currentIndex==0) {
            backButton.setEnabled(false);
        }
        if(Uploads.isNextEmpty(currentIndex+1))
        {
            nextPageButton.setEnabled(false);
        }
        //color settings
        topLogoPanel.setBackground(new Color(71, 72, 73));
        searchLabel.setForeground(new Color(255,255,255));
        topLogoPanelLabel.setForeground(new Color(255,255,255));

        //font settings
        topLogoPanelLabel.setFont(new Font("Helvetica",Font.BOLD,20));


        //positioning
        topLogoPanel.setBounds(0,0,800,100);
        topLogoPanelLabel.setBounds(270,30,200,20);
        loginButton.setBounds(670,30,100,20);
        userSignUpButton.setBounds(20,30,100,20);
        searchLabel.setBounds(20,70,100,20);
        searchTF.setBounds(80,70,150,20);
        searchButton.setBounds(240,70,100,20);
        nextPageButton.setBounds(600,800,100,20);
        backButton.setBounds(480,800,100,20);

        //adding components to relevant panel
        topLogoPanel.add(topLogoPanelLabel);
        topLogoPanel.add(loginButton);
        topLogoPanel.add(userSignUpButton);
        topLogoPanel.add(searchLabel);
        topLogoPanel.add(searchTF);
        topLogoPanel.add(searchButton);

        //adding components to frame
        mainFrame.add(topLogoPanel);
        mainFrame.add(nextPageButton);
        mainFrame.add(backButton);
        addInventionsToFrame(currentIndex);

        //action listeners
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchItem=searchTF.getText();
                //frame
                if(!(searchItem.equals("")))
                {
                    try {
                        JFrame searchResults=new JFrame("Search Results");
                        searchResults.setSize(700,700);
                        searchResults.setVisible(true);
                        searchResults.setResizable(false);
                        searchResults.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        searchResults.setLocationRelativeTo(null);
                        searchResults.setLayout(null);
                        Connection con = DbConnectionManager.getConnection();
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery("SELECT * FROM inventors_hub.uploads");

                        JLabel foundResults=new JLabel("Found Results:");
                        foundResults.setBounds(20,40,200,20);
                        int heightPosition=70;
                        while (rs.next())
                        {
                            if(rs.getString(6).toLowerCase().contains(searchItem.toLowerCase())) {

                                String fullUpload=rs.getString(2);
                                String fullUploadHeader=rs.getString(6);
                                JLabel searchItemHeader = new JLabel(rs.getString(6));
                                JButton viewMoreButton=new JButton("View More");

                                searchItemHeader.setBounds(20, heightPosition, 400, 20);
                                heightPosition+=30;
                                viewMoreButton.setBounds(20,heightPosition,100,20);

                                searchResults.add(searchItemHeader);
                                searchResults.add(viewMoreButton);
                                heightPosition+=30;
                                viewMoreButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        JFrame fullViewFrame=getSeeMoreFrame(fullUpload,fullUploadHeader);
                                        fullViewFrame.setVisible(true);
                                    }
                                });
                            }
                        }
                    }catch (Exception ex)
                    {
                        ex.printStackTrace();
                    }
                }

            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                new LoginPage();
            }
        });
        userSignUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                new UserRegistration();
            }
        });
        nextPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentIndex++;
                mainFrame.dispose();
                new MainView(currentIndex);
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentIndex--;
                mainFrame.dispose();
                new MainView(currentIndex);
            }
        });
    }
    public void addInventionsToFrame(int currentIndex)
    {
        try{
            Connection con= DbConnectionManager.getConnection();
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM inventors_hub.uploads WHERE mainPageIndex='"+currentIndex+"'");
            int heightPos=120;
            while (rs.next())
            {
                //variable declarations
                JPanel invention=new JPanel(null);
                JLabel uploaderId=new JLabel("Uploader: "+rs.getString(1));
                JLabel header=new JLabel("Upload Topic: "+rs.getString(6));
                JLabel uploadCategory=new JLabel("Upload Category: "+rs.getString(4));
                JTextArea shortDescription=new JTextArea(rs.getString(3));
                shortDescription.setLineWrap(true);
                shortDescription.setEditable(false);
                JButton likeButton=new JButton("Like");
                JButton seeMoreButton=new JButton("See More");
                int uploadId=rs.getInt(10);
                int currentRating=rs.getInt(5);
                String fullUpload=rs.getString(2);
                String fullUploadHeader=rs.getString(6);
                JLabel likeCounter=new JLabel("Total likes:");
                JLabel likeField=new JLabel(Integer.toString(rs.getInt(5)));

                //color settings
                invention.setBackground(new Color(80, 81, 84));
                uploaderId.setForeground(new Color(255,255,255));
                header.setForeground(new Color(255,255,255));
                uploadCategory.setForeground(new Color(255,255,255));
                likeCounter.setForeground(new Color(255,255,255));
                likeField.setForeground(new Color(255,255,255));


                //positioning
                invention.setBounds(20,heightPos,800,200);
                uploaderId.setBounds(0,0,200,20);
                uploadCategory.setBounds(220,0,200,20);
                header.setBounds(0,30,400,20);
                shortDescription.setBounds(0,50,600,120);
                likeButton.setBounds(20,180,100,20);
                likeCounter.setBounds(150,180,100,20);
                likeField.setBounds(220,180,100,20);
                seeMoreButton.setBounds(500,180,100,20);




                //adding to panel
                invention.add(header);
                invention.add(uploadCategory);
                invention.add(uploaderId);
                invention.add(shortDescription);
                invention.add(likeButton);
                invention.add(seeMoreButton);
                invention.add(likeCounter);
                invention.add(likeField);

                //action listeners
                likeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Uploads.increaseUploadRating(currentRating,uploadId);
                        likeButton.setEnabled(false);

                    }
                });
                seeMoreButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JFrame fullViewFrame=getSeeMoreFrame(fullUpload,fullUploadHeader);
                        fullViewFrame.setVisible(true);
                    }
                });

                //adding to frame
                mainFrame.add(invention);
                heightPos+=210;
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    private JFrame getSeeMoreFrame(String fullDescription,String descriptionHeader)
    {
        //frame settings
        JFrame description=new JFrame("Full Description");
        description.setSize(600,800);
        description.setResizable(false);
        description.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        description.setLocationRelativeTo(null);
        description.setLayout(null);

        //variables
        JLabel uploadHeader=new JLabel(descriptionHeader);
        JTextArea fullDescriptionTA=new JTextArea();
        JScrollPane fullDescriptionScrollPane=new JScrollPane(fullDescriptionTA);
        fullDescriptionScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);


        fullDescriptionTA.setLineWrap(true);
        fullDescriptionTA.setText(fullDescription);
        fullDescriptionTA.setEditable(false);

        //positioning
        uploadHeader.setBounds(20,40,300,20);
        fullDescriptionScrollPane.setBounds(20,100,500,600);

        //adding to frame
        description.add(uploadHeader);
        description.add(fullDescriptionScrollPane);

        return description;
    }
}
