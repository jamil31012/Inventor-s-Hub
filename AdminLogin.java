package GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import BIN.Admin;
/**
 * created by: Jamil
 * Admin Login Class
 * Dependency: BIN/Admin
 */
public class AdminLogin {
    //class and instance variables
    private JFrame adminLoginFrame;

    //adminLogin class default constructor
    public AdminLogin()
    {
        adminLoginFrame=new JFrame("Admin Login");
        adminLoginFrame.setSize(350,500);
        adminLoginFrame.setLocationRelativeTo(null);
        adminLoginFrame.setVisible(true);
        adminLoginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adminLoginFrame.setLayout(null);
        adminLoginFrame.setResizable(false);
        adminLoginComponents();
    }

    //adminLoginClass frame components
    private void adminLoginComponents()
    {
        //variable declarations
        JPanel topLogoPanel=new JPanel(null);
        JLabel topLogoPanelLabel=new JLabel("INVENTOR'S HUB");
        JLabel adminIdLabel=new JLabel("Enter your organizational ID:");
        JTextField adminIdTf=new JTextField();
        JLabel adminPasswordLabel=new JLabel("Enter your password:");
        JPasswordField adminPasswordTf=new JPasswordField();
        JButton adminLoginButton=new JButton("LOGIN");
        JButton backButton=new JButton("<<");


        //color settings
        topLogoPanel.setBackground(new Color(71, 72, 73));
        topLogoPanelLabel.setForeground(new Color(255,255,255));

        //font settings
        topLogoPanelLabel.setFont(new Font("Helvetica",Font.BOLD,20));
        backButton.setFont(new Font("Helvetica",Font.BOLD,12));

        //positioning
        topLogoPanel.setBounds(0,0,350,70);
        topLogoPanelLabel.setBounds(80,20,200,20);
        adminIdLabel.setBounds(90,100,200,20);
        adminIdTf.setBounds(90,140,150,20);
        adminPasswordLabel.setBounds(90,180,200,20);
        adminPasswordTf.setBounds(90,220,150,20);
        adminLoginButton.setBounds(115,270,100,20);
        backButton.setBounds(10,10,50,20);

        //adding components to relevant panels
        topLogoPanel.add(topLogoPanelLabel);
        topLogoPanel.add(backButton);

        //adding components to window
        adminLoginFrame.add(topLogoPanel);
        adminLoginFrame.add(adminIdLabel);
        adminLoginFrame.add(adminIdTf);
        adminLoginFrame.add(adminPasswordLabel);
        adminLoginFrame.add(adminPasswordTf);
        adminLoginFrame.add(adminLoginButton);

        //action Listeners
        adminLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id=adminIdTf.getText();
                String pass=adminPasswordTf.getText();
                if(Admin.verifyAdmin(id,pass))
                {
                    adminLoginFrame.dispose();
                    new AdminPanel();
                }
                else {
                    JOptionPane.showMessageDialog(adminLoginFrame,"Wrong Credentials!!!");
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminLoginFrame.dispose();
                new LoginPage();
            }
        });
    }
}
