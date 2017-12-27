package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Jamil on 25-Dec-17.
 */
public class LoginPage {
    private JFrame loginPageFrame;
    public LoginPage()
    {
        loginPageFrame=new JFrame("Login Page");
        loginPageFrame.setSize(500,500);
        loginPageFrame.setLocationRelativeTo(null);
        loginPageFrame.setVisible(true);
        loginPageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginPageFrame.setLayout(null);
        loginPageFrame.setResizable(false);
        loginPageFrame.getContentPane().setBackground(new Color(255,255,255));
        loginPageComponents();
    }
    private void loginPageComponents()
    {
        //variable declarations
        JPanel topLogoPanel=new JPanel(null);
        JLabel topLogoPanelLabel=new JLabel("INVENTOR'S HUB");
        JPanel midPanel=new JPanel(null);
        JLabel adminLoginLabel=new JLabel("Admin Login");
        JButton adminLoginButton=new JButton("Login");
        JLabel userLoginLabel=new JLabel("User Login");
        JButton userLoginButton=new JButton("Login");
        JButton backButton=new JButton("<<");

        //color settings
        topLogoPanel.setBackground(new Color(71, 72, 73));
        topLogoPanelLabel.setForeground(new Color(255,255,255));
        midPanel.setBackground(new Color(80, 81, 84));
        adminLoginLabel.setForeground(new Color(255,255,255));
        userLoginLabel.setForeground(new Color(255,255,255));

        //font settings
        topLogoPanelLabel.setFont(new Font("Helvetica",Font.BOLD,20));
        backButton.setFont(new Font("Helvetica",Font.BOLD,12));

        //positioning
        topLogoPanel.setBounds(0,0,500,100);
        topLogoPanelLabel.setBounds(150,30,200,20);
        midPanel.setBounds(0,100,500,400);
        adminLoginLabel.setBounds(35,70,100,20);
        adminLoginButton.setBounds(20,110,100,20);
        userLoginLabel.setBounds(370,70,100,20);
        userLoginButton.setBounds(350,110,100,20);
        backButton.setBounds(10,10,50,20);


        //adding components to relevant panels
        topLogoPanel.add(topLogoPanelLabel);
        topLogoPanel.add(backButton);
        midPanel.add(adminLoginLabel);
        midPanel.add(adminLoginButton);
        midPanel.add(userLoginLabel);
        midPanel.add(userLoginButton);

        //adding components to frame
        loginPageFrame.add(topLogoPanel);
        loginPageFrame.add(midPanel);

        //action listeners
        adminLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginPageFrame.dispose();
                new AdminLogin();
            }
        });
        userLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginPageFrame.dispose();
                new RegisteredUserLogin();
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginPageFrame.dispose();
                new MainView();
            }
        });
    }
}
