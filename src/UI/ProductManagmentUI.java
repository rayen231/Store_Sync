package UI;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class ProductManagmentUI extends JFrame {

    public ProductManagmentUI() {
        super("Sales Management App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);

        // Logo
        ImageIcon logoIcon = new ImageIcon("/Users/Rayen/eclipse-workspace/Project/src/UI/PIC/logo.png");
        Image img = logoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon newLogoIcon = new ImageIcon(img);
        JLabel logoLabel = new JLabel(newLogoIcon);

        // User Account Profile
        ImageIcon userProfileIcon = new ImageIcon("user_profile.png");
        JButton userProfileButton = new JButton(userProfileIcon);
        userProfileButton.setPreferredSize(new Dimension(40, 40));
        userProfileButton.setBackground(Color.WHITE);
        userProfileButton.setFocusPainted(false);

        // User Name (Clickable)
        JLabel userNameLabel = new JLabel("John Doe");
        userNameLabel.setForeground(Color.WHITE);
        userNameLabel.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Set cursor to hand

        // Add action listener to user name label
        userNameLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Perform action here
                JOptionPane.showMessageDialog(null, "User Profile Clicked");
            }
        });

        // Home Page Panel
        JPanel homePagePanel = new JPanel(new BorderLayout());
        homePagePanel.setBackground(new Color(230, 230, 230)); // Light gray
        homePagePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        //-----------------------------------------------------

        JLabel lid = new JLabel("ID");
        JLabel lname = new JLabel("Name");
        JLabel ltype = new JLabel("Type");
        JLabel lplace = new JLabel("Place");
        JLabel lquantity = new JLabel("Quantity");

        JTextField tid = new JTextField(10);
        JTextField tname = new JTextField(10);
        JTextField tplace = new JTextField(10);
        JTextField tquantity = new JTextField(10);
        JTextField trecherche = new JTextField(10);

        JComboBox<String> typeDropdown = new JComboBox<>(new String[]{"m1", "m2", "m3"}); // Assuming some default types

        JButton bajouter = new JButton("Add");
        JButton bannuler = new JButton("Cancel");
        JButton brecherche = new JButton("Search");

        JButton homeButton = new JButton("Home");
        JButton manageSalesButton = new JButton("Manage Sales");
        JButton makeBillButton = new JButton("Make Bill");
        JButton productManagementButton = new JButton("Product Management");

        TableProductManagment tm = new TableProductManagment();
        JTable tableau = new JTable(tm);
        JScrollPane jsp = new JScrollPane(tableau);
        // Change table title color
        tableau.getTableHeader().setBackground(new Color(0, 100, 0)); // Darker green
        tableau.getTableHeader().setForeground(Color.WHITE);

        JPanel p1 = new JPanel(new BorderLayout());
        JPanel p11 = new JPanel(new GridLayout(5, 1)); // 5 rows, 2 columns
        JPanel p12 = new JPanel();
        JPanel p2 = new JPanel(new BorderLayout());
        JPanel p21 = new JPanel();

        JPanel pt = new JPanel();
        JPanel pd = new JPanel();
        JPanel pp = new JPanel();
        JPanel pl = new JPanel();
        JPanel pq = new JPanel();

        // Set background colors for panels
        p1.setBackground(Color.WHITE);
        p2.setBackground(Color.WHITE);
        p11.setBackground(Color.WHITE);
        p12.setBackground(Color.WHITE);
        p21.setBackground(Color.WHITE);

        // Set fonts for labels
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        // Change label text color to darker green
        lid.setFont(labelFont);
        lid.setForeground(new Color(0, 100, 0)); // Darker green
        lname.setFont(labelFont);
        lname.setForeground(new Color(0, 100, 0)); // Darker green
        ltype.setFont(labelFont);
        ltype.setForeground(new Color(0, 100, 0)); // Darker green
        lplace.setFont(labelFont);
        lplace.setForeground(new Color(0, 100, 0)); // Darker green
        lquantity.setFont(labelFont);
        lquantity.setForeground(new Color(0, 100, 0)); // Darker green
        bajouter.setFont(labelFont);
        bannuler.setFont(labelFont);
        brecherche.setFont(labelFont);

        // Set background color and text color for buttons
        // Change button background to darker green and text color to white
        bajouter.setBackground(new Color(0, 100, 0)); // Darker green
        bajouter.setForeground(Color.WHITE);
        bannuler.setBackground(new Color(0, 100, 0)); // Darker green
        bannuler.setForeground(Color.WHITE);
        brecherche.setBackground(new Color(0, 100, 0)); // Darker green
        brecherche.setForeground(Color.WHITE);

        pt.add(lid);
        pt.add(tid);

        pd.add(lname);
        pd.add(tname);

        pp.add(lplace);
        pp.add(tplace);

        pl.add(ltype);
        pl.add(typeDropdown);

        pq.add(lquantity);
        pq.add(tquantity);

        p11.add(pt);
        p11.add(pd);
        p11.add(pp);
        p11.add(pl);
        p11.add(pq);
        p1.add(p11, BorderLayout.NORTH);

        p12.add(bajouter);
        p12.add(bannuler);
        p1.add(p12, BorderLayout.SOUTH);


        p21.add(brecherche);
        p21.add(trecherche);
        p2.add(p21, BorderLayout.NORTH);

        JPanel CORRECTPanel = new JPanel(new BorderLayout());

        CORRECTPanel.add(p1, BorderLayout.NORTH);
        CORRECTPanel.add(jsp, BorderLayout.CENTER);
        CORRECTPanel.add(p2, BorderLayout.SOUTH);

        homePagePanel.add(CORRECTPanel, BorderLayout.CENTER);

        //-----------------------------------------------------

        // Side Bar
        JPanel sideBarPanel = new JPanel();
        sideBarPanel.setBackground(new Color(0, 100, 0)); // Less bright green color
        sideBarPanel.setPreferredSize(new Dimension(200, getHeight()));

        // Add options to the side bar
        styleButton(homeButton);
        styleButton(manageSalesButton);
        styleButton(makeBillButton);
        styleButton(productManagementButton);

        // Add action listeners to the side bar buttons
        homeButton.addActionListener(e -> {
            // Show home page panel
            new Home();
            dispose();
            // Hide other panels if needed
        });
        makeBillButton.addActionListener(e -> {
            // Show home page panel
            new MakeBillUI();
            dispose();
            // Hide other panels if needed
        });
        productManagementButton.addActionListener(e -> {
            // Show home page panel
            new ProductManagmentUI();
            dispose();
            // Hide other panels if needed
        });

        // Add buttons to the side bar panel
        sideBarPanel.setLayout(new BoxLayout(sideBarPanel, BoxLayout.Y_AXIS));
        sideBarPanel.add(Box.createVerticalStrut(40)); // Space at the top
        sideBarPanel.add(homeButton);
        sideBarPanel.add(Box.createVerticalStrut(20)); // Space between buttons
        sideBarPanel.add(manageSalesButton);
        sideBarPanel.add(Box.createVerticalStrut(20)); // Space between buttons
        sideBarPanel.add(makeBillButton);
        sideBarPanel.add(Box.createVerticalStrut(20)); // Space between buttons
        sideBarPanel.add(productManagementButton);

        // Main Panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE); // White background
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(0, 100, 0)); // Less bright green color
        headerPanel.add(logoLabel, BorderLayout.WEST);
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(0, 100, 0)); // Less bright green color
        JLabel appTitleLabel = new JLabel("Sales Management App");
        appTitleLabel.setForeground(Color.WHITE);
        appTitleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titlePanel.add(appTitleLabel);
        headerPanel.add(titlePanel, BorderLayout.CENTER);
        JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        userPanel.setBackground(new Color(0, 100, 0)); // Less bright green color
        userPanel.add(userNameLabel);
        userPanel.add(userProfileButton);
        headerPanel.add(userPanel, BorderLayout.EAST);

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(homePagePanel, BorderLayout.CENTER);
        mainPanel.add(sideBarPanel, BorderLayout.WEST);

        // Add components to the frame
        add(mainPanel);

        setVisible(true);

    }

    // Method to style buttons
    private void styleButton(JButton button) {
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(0, 100, 0)); // Less bright green color
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30)); // Set padding
        button.setAlignmentX(Component.CENTER_ALIGNMENT); // Align buttons to the center horizontally
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, button.getPreferredSize().height));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ProductManagmentUI::new);
    }
}
