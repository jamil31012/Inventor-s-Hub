package GUI;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import BIN.RegisterUser;
import BIN.Uploads;
/**
 * Created by Jamil on 19-Dec-17.
 */
public class RegisteredUserPanel {
    private JFrame registeredUserPanelFrame;
    public RegisteredUserPanel()
    {
        registeredUserPanelFrame=new JFrame("User Panel");
        registeredUserPanelFrame.setSize(800,800);
        registeredUserPanelFrame.setVisible(true);
        registeredUserPanelFrame.setLayout(null);
        registeredUserPanelFrame.setLocationRelativeTo(null);
        registeredUserPanelFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        registeredUserPanelFrame.setResizable(false);
        registeredUserPanelComponents();
    }

    private void registeredUserPanelComponents()
    {
        //variable declarations
        JPanel topLogoPanel=new JPanel(null);
        JLabel topLogoPanelLabel=new JLabel("INVENTOR'S HUB");
        JButton logOutButton=new JButton("Log Out");
        JTabbedPane registeredUserPanelTP=new JTabbedPane();
            //panel 1
        JPanel userProfilePanel=new JPanel(null);
        JLabel userNameLabel=new JLabel("User Name:");
        JLabel userNameFieldLabel=new JLabel(RegisterUser.getUserName());
        JLabel userIdLabel=new JLabel("User Id:");
        JLabel userIdFieldLabel=new JLabel(RegisterUser.getUserId());
        JLabel userMailLabel=new JLabel("User Mail:");
        JLabel userMailFieldLabel=new JLabel(RegisterUser.getUserMail());
        JLabel userTotalSubmissionsLabel=new JLabel("Total uploads:");
        JLabel userTotalSubmissionsFieldLabel=new JLabel(Integer.toString(RegisterUser.getTotalSubmissions()));
            //Panel 2
        JPanel uploadInventionPanel=new JPanel(null);
        JLabel uploadInventionHeader=new JLabel("Write about your project here:");
        JTextArea uploadInventionTA=new JTextArea();
        JScrollPane uploadInventionTAScrollPane=new JScrollPane(uploadInventionTA);
        JLabel uploadInventionShortDescriptionLabel=new JLabel("Enter a short description of your project(Max 250 characters!!!):");
        JTextArea uploadInventionShortDescriptionTA=new JTextArea();
        String types[]={"None","Technology","Science","Arts","Life Style","Commerce"};
        JComboBox uploadTypesCB=new JComboBox(types);
        JLabel uploadTypeLabel=new JLabel("Select the field of your project:");
        JLabel characterCount=new JLabel("Total Characters:");
        JLabel characterCountField=new JLabel("0/ 400");
        JButton submitButton=new JButton("Submit");
        JLabel addInventionTopicName=new JLabel("Insert project heading:");
        JTextField addInventionTopicFieldTF=new JTextField();
            //panel 3
        JPanel changeProfileInfoPanel=new JPanel(null);
        JLabel changeInfoUserName=new JLabel("User Name:");
        JLabel changeInfoUserNameField=new JLabel(RegisterUser.getUserName());
        JButton changeInfoUserNameButton=new JButton("Change");
        JLabel changeInfoUserIdLabel=new JLabel("User Id:");
        JLabel changeInfoUserIdField=new JLabel(RegisterUser.getUserId());
        JButton changeInfoUserIdButton=new JButton("Change");
        JLabel changeInfoUserMailLabel=new JLabel("User Mail:");
        JLabel changeInfoUserMailField=new JLabel(RegisterUser.getUserMail());
        JButton changeInfoUserMailButton=new JButton("Change");
        JLabel changeInfoUserPasswordLabel=new JLabel("Change Password:");
        JButton changeInfoUserPasswordButton=new JButton("Change");

        //text area wrap settings
        uploadInventionTA.setLineWrap(true);
        uploadInventionShortDescriptionTA.setLineWrap(true);

        //scrollbar policy
        uploadInventionTAScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        //color settings
        topLogoPanel.setBackground(new Color(71, 72, 73));

        //font settings
        topLogoPanelLabel.setFont(new Font("Helvetica",Font.BOLD,20));
        topLogoPanelLabel.setForeground(new Color(255,255,255));

        //positioning
            //top panel
        topLogoPanel.setBounds(0,0,800,100);
        topLogoPanelLabel.setBounds(300,30,200,20);
        logOutButton.setBounds(700,0,80,30);
        registeredUserPanelTP.setBounds(0,100,800,700);
            //tabbed pane panel 1
        userProfilePanel.setBounds(0,0,800,600);
        userNameLabel.setBounds(20,100,70,20);
        userNameFieldLabel.setBounds(90,100,200,20);
        userIdLabel.setBounds(40,130,50,20);
        userIdFieldLabel.setBounds(90,130,100,20);
        userMailLabel.setBounds(27,160,100,20);
        userMailFieldLabel.setBounds(90,160,200,20);
        userTotalSubmissionsLabel.setBounds(3,190,100,20);
        userTotalSubmissionsFieldLabel.setBounds(90,190,100,20);


            //tabbed pane panel 2
        uploadInventionPanel.setBounds(0,0,800,600);
        uploadTypeLabel.setBounds(20,20,200,20);
        uploadTypesCB.setBounds(210,20,100,20);
        addInventionTopicName.setBounds(330,20,150,20);
        addInventionTopicFieldTF.setBounds(490,20,200,20);
        uploadInventionHeader.setBounds(20,60,200,20);
        uploadInventionTAScrollPane.setBounds(20,80,600,250);
        uploadInventionShortDescriptionLabel.setBounds(20,360,400,20);
        characterCount.setBounds(450,360,100,20);
        characterCountField.setBounds(560,360,50,20);
        uploadInventionShortDescriptionTA.setBounds(20,380,600,200);
        submitButton.setBounds(250,600,100,20);
            //tabbed pane panel 3
        changeProfileInfoPanel.setBounds(0,0,800,600);
        changeInfoUserName.setBounds(20,40,100,20);
        changeInfoUserNameField.setBounds(100,40,200,20);
        changeInfoUserNameButton.setBounds(310,40,100,20);
        changeInfoUserIdLabel.setBounds(42,70,100,20);
        changeInfoUserIdField.setBounds(100,70,200,20);
        changeInfoUserIdButton.setBounds(310,70,100,20);
        changeInfoUserMailLabel.setBounds(28,100,100,20);
        changeInfoUserMailField.setBounds(100,100,200,20);
        changeInfoUserMailButton.setBounds(310,100,100,20);
        changeInfoUserPasswordLabel.setBounds(20,130,200,20);
        changeInfoUserPasswordButton.setBounds(310,130,100,20);



        //adding components to relevant panel
            //top panel
        topLogoPanel.add(topLogoPanelLabel);
        topLogoPanel.add(logOutButton);
            //user profile panel
        userProfilePanel.add(userNameLabel);
        userProfilePanel.add(userNameFieldLabel);
        userProfilePanel.add(userIdLabel);
        userProfilePanel.add(userIdFieldLabel);
        userProfilePanel.add(userMailLabel);
        userProfilePanel.add(userMailFieldLabel);
        userProfilePanel.add(userTotalSubmissionsLabel);
        userProfilePanel.add(userTotalSubmissionsFieldLabel);
            //upload panel
        uploadInventionPanel.add(uploadTypeLabel);
        uploadInventionPanel.add(uploadTypesCB);
        uploadInventionPanel.add(uploadInventionHeader);
        uploadInventionPanel.add(uploadInventionTAScrollPane);
        uploadInventionPanel.add(uploadInventionShortDescriptionLabel);
        uploadInventionPanel.add(uploadInventionShortDescriptionTA);
        uploadInventionPanel.add(characterCount);
        uploadInventionPanel.add(characterCountField);
        uploadInventionPanel.add(submitButton);
        uploadInventionPanel.add(addInventionTopicName);
        uploadInventionPanel.add(addInventionTopicFieldTF);
            //change info panel
        changeProfileInfoPanel.add(changeInfoUserName);
        changeProfileInfoPanel.add(changeInfoUserNameField);
        changeProfileInfoPanel.add(changeInfoUserNameButton);
        changeProfileInfoPanel.add(changeInfoUserIdLabel);
        changeProfileInfoPanel.add(changeInfoUserIdField);
        changeProfileInfoPanel.add(changeInfoUserIdButton);
        changeProfileInfoPanel.add(changeInfoUserMailLabel);
        changeProfileInfoPanel.add(changeInfoUserMailField);
        changeProfileInfoPanel.add(changeInfoUserMailButton);
        changeProfileInfoPanel.add(changeInfoUserPasswordLabel);
        changeProfileInfoPanel.add(changeInfoUserPasswordButton);



        //adding components to tab
        registeredUserPanelTP.addTab("User Profile",userProfilePanel);
        registeredUserPanelTP.addTab("Upload",uploadInventionPanel);
        registeredUserPanelTP.addTab("Change Profile Information",changeProfileInfoPanel);


        //adding components to frame
        registeredUserPanelFrame.add(topLogoPanel);
        registeredUserPanelFrame.add(registeredUserPanelTP);


        //action listeners
        uploadInventionShortDescriptionTA.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                update();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                update();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                update();
            }
            public void update()
            {
                characterCountField.setText((uploadInventionShortDescriptionTA.getText().length()+"/ 400"));
            }
        });
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registeredUserPanelFrame.dispose();
                new MainView();
            }
        });
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (uploadInventionShortDescriptionTA.getText().length() <= 400) {
                    if(uploadTypesCB.getSelectedItem().equals("None") || uploadInventionHeader.getText().equals(""))
                    {
                        JOptionPane.showMessageDialog(registeredUserPanelFrame,"Please select a category and add a header if you haven't done so.");
                    }
                    else {
                        Uploads newUpload = new Uploads(RegisterUser.getUserId(),uploadInventionTA.getText(),uploadInventionShortDescriptionTA.getText(),uploadTypesCB.getSelectedItem().toString(),addInventionTopicFieldTF.getText(),RegisterUser.getUserMail(),RegisterUser.getUserName());
                        newUpload.upload();
                        RegisterUser.increaseTotalSubmission(RegisterUser.getUserId());
                        JOptionPane.showMessageDialog(registeredUserPanelFrame,"Upload Successful");
                        /*uploadInventionTA.setText("");
                        uploadInventionHeader.setText("");
                        uploadTypesCB.setSelectedItem("None");
                        uploadInventionShortDescriptionTA.setText("");*/
                    }
                }
                else{
                    JOptionPane.showMessageDialog(registeredUserPanelFrame,"Maximum limit of characters exceeded in description field");
                }
            }
        });
        changeInfoUserNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame nameChangeFrame=createChangeInfoPopUpFrame("name");
                nameChangeFrame.setVisible(true);
            }
        });
        changeInfoUserIdButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame nameChangeFrame=createChangeInfoPopUpFrame("id");
                nameChangeFrame.setVisible(true);
            }
        });
        changeInfoUserMailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame nameChangeFrame=createChangeInfoPopUpFrame("mail");
                nameChangeFrame.setVisible(true);
            }
        });
        changeInfoUserPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //frame declaration
                JFrame changePassWordFrame=new JFrame("Change Password Frame");
                changePassWordFrame.setSize(370,350);
                changePassWordFrame.setVisible(true);
                changePassWordFrame.setLayout(null);
                changePassWordFrame.setLocationRelativeTo(null);
                changePassWordFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                changePassWordFrame.setResizable(false);

                //frame component declaration
                JLabel enterOldPasswordLabel=new JLabel("Enter previous password:");
                JPasswordField enterOldPasswordTF=new JPasswordField();
                JLabel enterNewPasswordLabel=new JLabel("Enter new password(Must have 6 characters at least):");
                JPasswordField enterNewPasswordTF=new JPasswordField();
                JButton changePasswordButton=new JButton("Change");

                //positioning
                enterOldPasswordLabel.setBounds(20,40,250,20);
                enterOldPasswordTF.setBounds(20,70,200,20);
                enterNewPasswordLabel.setBounds(20,100,320,20);
                enterNewPasswordTF.setBounds(20,130,200,20);
                changePasswordButton.setBounds(80,170,100,20);

                //adding to frame
                changePassWordFrame.add(enterOldPasswordLabel);
                changePassWordFrame.add(enterOldPasswordTF);
                changePassWordFrame.add(enterNewPasswordLabel);
                changePassWordFrame.add(enterNewPasswordTF);
                changePassWordFrame.add(changePasswordButton);

                //action listeners
                changePasswordButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(enterNewPasswordTF.getPassword().length<6)
                        {
                            JOptionPane.showMessageDialog(changePassWordFrame,"New password length does not meet requirements.");
                        }
                        int code=RegisterUser.updatePassWord(enterOldPasswordTF.getText(),enterNewPasswordTF.getText());
                        if(code==0)
                        {
                            JOptionPane.showMessageDialog(changePassWordFrame,"Change Successful");
                        }
                        if(code==1)
                        {
                            JOptionPane.showMessageDialog(changePassWordFrame,"Previous PassWord does not match given password");
                        }
                        if(code==2)
                        {
                            JOptionPane.showMessageDialog(changePassWordFrame,"There is connection problem in database server.");
                        }
                    }
                });
            }
        });
    }

    private JFrame createChangeInfoPopUpFrame(String credential)
    {
        //frame declaration
        JFrame popUpFrame=new JFrame("Change credentials");
        popUpFrame.setSize(300,300);
        popUpFrame.setLayout(null);
        popUpFrame.setLocationRelativeTo(null);
        popUpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        popUpFrame.setResizable(false);
        //frame component declaration
        JLabel changeFieldLabel=new JLabel("Enter your new "+credential);
        JTextField changeFieldTF=new JTextField();
        JButton changeFieldButton=new JButton("Change");
        //component positioning
        changeFieldLabel.setBounds(20,40,200,20);
        changeFieldTF.setBounds(20,70,200,20);
        changeFieldButton.setBounds(80,100,100,20);
        //component addition to frame
        popUpFrame.add(changeFieldLabel);
        popUpFrame.add(changeFieldTF);
        popUpFrame.add(changeFieldButton);
        if(credential.equals("name"))
        {
            changeFieldButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(RegisterUser.updateInfo(changeFieldTF.getText(),"name"))
                    {
                        JOptionPane.showMessageDialog(popUpFrame,"Update Successful!!");
                    }
                    else {
                        JOptionPane.showMessageDialog(popUpFrame,"Update Failed.");
                    }
                }
            });
        }
        if(credential.equals("mail"))
        {
            changeFieldButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(RegisterUser.updateInfo(changeFieldTF.getText(),"mail"))
                    {
                        JOptionPane.showMessageDialog(popUpFrame,"Update Successful!!");
                    }
                    else {
                        JOptionPane.showMessageDialog(popUpFrame,"Update Failed.");
                    }
                }
            });
        }
        if(credential.equals("id"))
        {
            changeFieldButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(RegisterUser.verifyAlreadyTakenId(changeFieldTF.getText()))
                    {
                        JOptionPane.showMessageDialog(popUpFrame,"Id already taken!! Please choose another id.");
                    }
                    else {
                        if (RegisterUser.updateInfo(changeFieldTF.getText(), "id")) {
                            JOptionPane.showMessageDialog(popUpFrame, "Update Successful!!");
                        } else {
                            JOptionPane.showMessageDialog(popUpFrame, "Update Failed.");
                        }
                    }
                }
            });
        }
        return popUpFrame;
    }
}
