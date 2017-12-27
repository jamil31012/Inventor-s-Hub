package GUI;

import BIN.RegisterUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Jamil on 19-Dec-17.
 */
public class RegisteredUserLogin {
    JFrame registeredUserLoginFrame;
    public RegisteredUserLogin()
    {
        registeredUserLoginFrame=new JFrame("Registered User Login");
        registeredUserLoginFrame.setSize(350,500);
        registeredUserLoginFrame.setLocationRelativeTo(null);
        registeredUserLoginFrame.setVisible(true);
        registeredUserLoginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        registeredUserLoginFrame.setLayout(null);
        registeredUserLoginFrame.setResizable(false);
        registeredUserLoginComponents();
    }
    private void registeredUserLoginComponents()
    {
        //variable declarations
        JPanel topLogoPanel=new JPanel(null);
        JLabel topLogoPanelLabel=new JLabel("INVENTOR'S HUB");
        JLabel userIdLabel=new JLabel("Enter your ID:");
        JTextField userIdTf=new JTextField();
        JLabel userPasswordLabel=new JLabel("Enter your password:");
        JPasswordField userPasswordTf=new JPasswordField();
        JButton userLoginButton=new JButton("LOGIN");
        JButton backButton=new JButton("<<");


        //color settings
        topLogoPanel.setBackground(new Color(71, 72, 73));

        //font settings
        topLogoPanelLabel.setFont(new Font("Helvetica",Font.BOLD,20));
        topLogoPanelLabel.setForeground(new Color(255,255,255));
        backButton.setFont(new Font("Helvetica",Font.BOLD,12));

        //positioning
        topLogoPanel.setBounds(0,0,350,70);
        topLogoPanelLabel.setBounds(80,20,200,20);
        userIdLabel.setBounds(90,100,200,20);
        userIdTf.setBounds(90,140,150,20);
        userPasswordLabel.setBounds(90,180,200,20);
        userPasswordTf.setBounds(90,220,150,20);
        userLoginButton.setBounds(115,270,100,20);
        backButton.setBounds(10,10,50,20);

        //adding components to relevant panels
        topLogoPanel.add(topLogoPanelLabel);
        topLogoPanel.add(backButton);

        //adding components to frame
        registeredUserLoginFrame.add(topLogoPanel);
        registeredUserLoginFrame.add(userIdLabel);
        registeredUserLoginFrame.add(userIdTf);
        registeredUserLoginFrame.add(userPasswordLabel);
        registeredUserLoginFrame.add(userPasswordTf);
        registeredUserLoginFrame.add(userLoginButton);

        //action listeners
        userLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(RegisterUser.verifyUser(userIdTf.getText(),userPasswordTf.getText()))
                {
                    registeredUserLoginFrame.dispose();
                    new RegisteredUserPanel();
                }
                else{
                    JOptionPane.showMessageDialog(registeredUserLoginFrame,"Error in user name or password. Please try again!!!");
                    userIdTf.setText("");
                    userPasswordTf.setText("");
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registeredUserLoginFrame.dispose();
                new LoginPage();
            }
        });
    }
}
