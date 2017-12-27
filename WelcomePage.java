package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Jamil on 27-Dec-17.
 */
public class WelcomePage {
    private JFrame welcomePageFrame;
    public WelcomePage()
    {
        welcomePageFrame=new JFrame("Welcome to Inventor's Hub");
        welcomePageFrame.setSize(800,700);
        welcomePageFrame.setVisible(true);
        welcomePageFrame.setResizable(false);
        welcomePageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        welcomePageFrame.setLocationRelativeTo(null);
        welcomePageFrame.setLayout(null);
        welcomePageFrame.getContentPane().setBackground(new Color(80, 81, 84));
        welcomePageComponents();

    }
    public void welcomePageComponents()
    {
        JLabel appLabel=new JLabel("WELCOME TO INVENTOR'S HUB");
        JButton getStartedButton=new JButton("Get Started>>");

        appLabel.setBounds(270,280,300,20);
        getStartedButton.setBounds(330,400,150,20);

        appLabel.setForeground(new Color(255,255,255));

        appLabel.setFont(new Font("Helvatica",Font.BOLD,17));

        welcomePageFrame.add(appLabel);
        welcomePageFrame.add(getStartedButton);

        getStartedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welcomePageFrame.dispose();
                new MainView();
            }
        });
    }
}
