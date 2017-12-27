package GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import BIN.Admin;
import BIN.DbConnectionManager;

import java.util.*;
import java.sql.*;
import javax.swing.table.*;
/**
 * Created By: Jamil
 * Admin Panel Class
 * Dependency: BIN/Admin
 */
public class AdminPanel {
    private JFrame adminPanelFrame;
    public AdminPanel()
    {
        adminPanelFrame=new JFrame("Admin Panel");
        adminPanelFrame.setSize(800,600);
        adminPanelFrame.setLocationRelativeTo(null);
        adminPanelFrame.setVisible(true);
        adminPanelFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adminPanelFrame.setLayout(null);
        adminPanelFrame.setResizable(false);
        adminPanelComponents();
    }
    private void adminPanelComponents()
    {
        //variable declarations
            //top panel
        JPanel topLogoPanel=new JPanel(null);
        JLabel topLogoPanelLabel=new JLabel("INVENTOR'S HUB");
        JButton logOutButton=new JButton("Log Out");

        JTabbedPane adminPanelTabbedPane=new JTabbedPane();
            //panel 1
        JPanel adminInfoPanel=new JPanel(null);
        JLabel adminNameLabel=new JLabel("Admin Name:");
        JLabel adminIdLabel=new JLabel("Admin Id:");
        JLabel adminName=new JLabel(Admin.getAdminName());
        JLabel adminId=new JLabel(Admin.getAdminId());

            //panel 2
        JPanel viewRegisteredUsersPanel=new JPanel(null);
            //panel 3
        JPanel removeUserPanel=new JPanel(null);
        JLabel removeUserEnterUserIdLabel=new JLabel("Enter user Id:");
        JTextField removeUserEnterUserNameField=new JTextField();
        JButton removeUserDropUserButton=new JButton("Remove User");

        //color settings
        topLogoPanel.setBackground(new Color(71, 72, 73));
        topLogoPanelLabel.setForeground(new Color(255,255,255));

        //font settings
        topLogoPanelLabel.setFont(new Font("Helvetica",Font.BOLD,20));


        //positioning
        topLogoPanel.setBounds(0,0,800,70);
        topLogoPanelLabel.setBounds(150,20,200,20);
        logOutButton.setBounds(680,0,80,20);
        adminPanelTabbedPane.setBounds(0,70,800,500);
        adminInfoPanel.setBounds(0,0,800,400);
        adminNameLabel.setBounds(20,70,100,20);
        adminName.setBounds(130,70,100,20);
        adminIdLabel.setBounds(40,150,100,20);
        adminId.setBounds(130,150,100,20);
        viewRegisteredUsersPanel.setBounds(0,0,800,450);
        removeUserPanel.setBounds(0,0,800,400);
        removeUserEnterUserIdLabel.setBounds(200,100,200,20);
        removeUserEnterUserNameField.setBounds(300,100,200,20);
        removeUserDropUserButton.setBounds(290,150,120,20);

        //adding components to relevant panel
        topLogoPanel.add(topLogoPanelLabel);
        topLogoPanel.add(logOutButton);
        adminInfoPanel.add(adminNameLabel);
        adminInfoPanel.add(adminIdLabel);
        adminInfoPanel.add(adminName);
        adminInfoPanel.add(adminId);
        removeUserPanel.add(removeUserEnterUserIdLabel);
        removeUserPanel.add(removeUserEnterUserNameField);
        removeUserPanel.add(removeUserDropUserButton);

        //populating frame with table
        addTableToPanel(viewRegisteredUsersPanel);

        //adding components to tabbed pane
        adminPanelTabbedPane.addTab("Admin Info",adminInfoPanel);
        adminPanelTabbedPane.addTab("View Registered User List",viewRegisteredUsersPanel);
        adminPanelTabbedPane.addTab("Remove User",removeUserPanel);

        //adding components to frame
        adminPanelFrame.add(topLogoPanel);
        adminPanelFrame.add(adminPanelTabbedPane);
        SwingUtilities.updateComponentTreeUI(adminPanelFrame);

        //action listeners
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminPanelFrame.dispose();
                new MainView();
            }
        });
        removeUserDropUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Admin.removeUser(removeUserEnterUserNameField.getText()))
                {
                    JOptionPane.showMessageDialog(adminPanelFrame,"User removed");
                }
                else {
                    JOptionPane.showMessageDialog(adminPanelFrame,"User not found!!");

                }
            }
        });
    }
    private void addTableToPanel(JPanel tablePanel)
    {
        try {
            JTable table;
            Connection con=DbConnectionManager.getConnection();
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM inventors_hub.registereduser");
            ResultSetMetaData metaData = rs.getMetaData();

            // names of columns
            Vector<String> columnNames = new Vector<String>();
            int columnCount = metaData.getColumnCount();
            for (int column = 1; column <= columnCount; column++) {
                columnNames.add(metaData.getColumnName(column));
            }

            // data of the table
            Vector<Vector<Object>> data = new Vector<Vector<Object>>();
            while (rs.next()) {
                Vector<Object> vector = new Vector<Object>();
                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    vector.add(rs.getObject(columnIndex));
                }
                data.add(vector);
            }
            DefaultTableModel model=new DefaultTableModel(data,columnNames);
            table=new JTable(model);
            table.setBounds(20,20,700,400);
            JScrollPane scrollPane=new JScrollPane(table);
            scrollPane.setBounds(20,20,700,400);
            tablePanel.add(scrollPane);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
