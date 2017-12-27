package GUI;

import BIN.RegisterUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

/**
 * Created by Jamil on 19-Dec-17.
 */
public class UserRegistration {
    private JFrame userRegistrationFrame;

    public UserRegistration() {
        userRegistrationFrame = new JFrame("New User Registration");
        userRegistrationFrame.setSize(500, 800);
        userRegistrationFrame.setLocationRelativeTo(null);
        userRegistrationFrame.setVisible(true);
        userRegistrationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        userRegistrationFrame.setLayout(null);
        userRegistrationFrame.setResizable(false);
        addUserRegistrationComponents();
    }

    private void addUserRegistrationComponents() {
        //variable declarations
        JPanel topLogoPanel = new JPanel(null);
        JLabel topLogoPanelLabel = new JLabel("INVENTOR'S HUB");
        JLabel userNameEnterLabel = new JLabel("Enter Your Name:");
        JTextField userNameEnterTF = new JTextField();
        JLabel userPasswordEnterLabel = new JLabel("Enter Your Password(Must be at least 6 characters long!)");
        JPasswordField userPasswordEnterTF = new JPasswordField();
        JLabel reTypeUserPasswordLabel = new JLabel("Re-Type Your Password");
        JPasswordField reTypeUserPasswordTf = new JPasswordField();
        JLabel userEmailLabel = new JLabel("Enter your E-Mail here:");
        JTextField userEmailTextField = new JTextField();
        JLabel userIdLabel = new JLabel("Enter your preferred Id:");
        JTextField userIdTextField = new JTextField();
        JButton signUpButton = new JButton("Sign Up");
        JButton backButton=new JButton("<<");


        //color settings
        topLogoPanel.setBackground(new Color(71, 72, 73));

        //font settings
        topLogoPanelLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
        topLogoPanelLabel.setForeground(new Color(255,255,255));
        backButton.setFont(new Font("Helvetica",Font.BOLD,12));


        //positioning
        topLogoPanel.setBounds(0, 0, 500, 70);
        topLogoPanelLabel.setBounds(150, 20, 200, 20);
        backButton.setBounds(0,0,50,30);
        userNameEnterLabel.setBounds(100,150,200,20);
        userNameEnterTF.setBounds(100,180,200,20);
        userIdLabel.setBounds(100,210,200,20);
        userIdTextField.setBounds(100,240,200,20);
        userEmailLabel.setBounds(100,270,200,20);
        userEmailTextField.setBounds(100,300,200,20);
        userPasswordEnterLabel.setBounds(100,330,400,20);
        userPasswordEnterTF.setBounds(100,360,200,20);
        reTypeUserPasswordLabel.setBounds(100,390,200,20);
        reTypeUserPasswordTf.setBounds(100,420,200,20);
        signUpButton.setBounds(150,500,100,20);

        //adding components to relative panels
        topLogoPanel.add(topLogoPanelLabel);

        //adding components to frame
        userRegistrationFrame.add(topLogoPanel);
        userRegistrationFrame.add(userNameEnterLabel);
        userRegistrationFrame.add(userNameEnterTF);
        userRegistrationFrame.add(userPasswordEnterLabel);
        userRegistrationFrame.add(userPasswordEnterTF);
        userRegistrationFrame.add(signUpButton);
        userRegistrationFrame.add(reTypeUserPasswordLabel);
        userRegistrationFrame.add(reTypeUserPasswordTf);
        userRegistrationFrame.add(userEmailLabel);
        userRegistrationFrame.add(userEmailTextField);
        userRegistrationFrame.add(userIdLabel);
        userRegistrationFrame.add(userIdTextField);
        userRegistrationFrame.add(backButton);

        //action listeners
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userRegistrationFrame.dispose();
                new MainView();
            }
        });
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int errorCode=0;
                if(userNameEnterTF.getText().equals("")||userIdTextField.getText().equals("")||userEmailTextField.getText().equals("")||userPasswordEnterTF.getPassword().length==0||reTypeUserPasswordTf.getPassword().length==0)
                {
                    errorCode=1;
                }
                else {
                    if(userPasswordEnterTF.getPassword().length<6)
                    {
                        errorCode=2;
                    }
                    else {
                        if(!(Arrays.equals(userPasswordEnterTF.getPassword(),reTypeUserPasswordTf.getPassword())))
                        {
                            errorCode=3;
                        }
                        else
                        {
                            if(RegisterUser.verifyAlreadyTakenId(userIdTextField.getText()))
                            {
                                errorCode=4;
                            }
                            else
                            {
                                RegisterUser newUser=new RegisterUser(userNameEnterTF.getText(),userIdTextField.getText(),userPasswordEnterTF.getText(),userEmailTextField.getText(),0);
                                newUser.commit();
                            }
                        }
                    }
                }
                switch (errorCode)
                {
                    case 1:
                        JOptionPane.showMessageDialog(userRegistrationFrame,"Please fill up all the fields.");
                        break;
                    case 2:
                        JOptionPane.showMessageDialog(userRegistrationFrame,"Password length does not meet requirements.");
                        break;
                    case 3:
                        JOptionPane.showMessageDialog(userRegistrationFrame,"Passwords don't match.");
                        break;
                    case 4:
                        JOptionPane.showMessageDialog(userRegistrationFrame,"Id is already taken,please choose another Id.");
                        break;
                    case 0:
                        JOptionPane.showMessageDialog(userRegistrationFrame,"Registration successful!");
                }
            }
        });
    }
}
