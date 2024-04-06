package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ManageSalesUI extends JFrame {

    public ManageSalesUI() {
    	
        super("Sales Management App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        
        //Change the icon window image
        ImageIcon icon = new ImageIcon("/Users/Rayen/eclipse-workspace/Project/src/UI/PIC/logo.png");
        Image iconImage = icon.getImage();
        setIconImage(iconImage);
        
        //Table 
        Object[][] data = {
                {"2024-01-01", 1000, 2000, 1000},
                {"2024-01-02", 1500, 2500, 1000},
                {"2024-01-03", 1200, 1800, 600},
                {"2024-01-04", 800, 2200, 1400},
                {"2024-01-05", 1100, 2100, 1000},
                {"2024-01-06", 1300, 2400, 1100}
        };
        String[] columns = {"Date", "Expenses", "Income", "Profit"};

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
                new EditUser();
            }
        });

        // Home Page Panel
        JPanel homePagePanel = new JPanel(new BorderLayout());
        homePagePanel.setBackground(new Color(230, 230, 230)); // Light gray
        homePagePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title

        // Features
        //labels
        JLabel todaySaleLabel = new JLabel("<html><font size='6' color='#006400'>Today's Income</font></html>");
        JLabel totalRevenueLabel = new JLabel("<html><font size='6' color='#006400'>Month's income</font></html>");
        JLabel Profit = new JLabel("<html><font size='6' color='#006400'>income deviation</font></html>");
        
        JLabel todaySaleLabelT = new JLabel("<html><font size='6' color='#006400'>12$</font></html>");
        JLabel totalRevenueLabelT = new JLabel("<html><font size='6' color='#006400'>120$</font></html>");
        JLabel ProfitT = new JLabel("<html><font size='6' color='#006400'>+50$</font></html>");
        
        JLabel from = new JLabel("<html><font size='4' color='#006400'>From</font></html>");
        JLabel to = new JLabel("<html><font size='4' color='#006400'>To</font></html>");
        JTextField fromT = new JTextField(20);
        JTextField toT = new JTextField(20);
        JButton searchButton = new JButton("Search");
        searchButton.setForeground(Color.WHITE);
        searchButton.setBackground(new Color(46, 139, 87)); // Dark green color
        
        
        // panels
        JPanel featurePanel = new JPanel();
        JPanel toppanel = new JPanel();
        JPanel midpanel = new JPanel();
        JPanel labelPanel  = new JPanel();
        JPanel labelPanel2  = new JPanel();
        labelPanel.setLayout(new FlowLayout());
        featurePanel.setLayout(new BorderLayout());
        toppanel.setLayout(new BorderLayout());
        
        //Table 
        SalesTableModel model = new SalesTableModel(data, columns);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        
        
        // LABEL PANEL 1
        labelPanel.setLayout(new FlowLayout()); // Use FlowLayout for horizontal arrangement
        labelPanel.add(todaySaleLabel);
        labelPanel.add(Box.createHorizontalStrut(20)); // Add 20 pixels of space to make spaces beetwenn the labels
        labelPanel.add(totalRevenueLabel);
        labelPanel.add(Box.createHorizontalStrut(20)); // Add another 20 pixels of space
        labelPanel.add(Profit);
        //LABEL PANEL 2
        labelPanel2.setLayout(new FlowLayout()); // Use FlowLayout for horizontal arrangement
        labelPanel2.add(Box.createHorizontalStrut(65));
        labelPanel2.add(todaySaleLabelT);
        labelPanel2.add(Box.createHorizontalStrut(150)); // Add 20 pixels of space to make spaces beetwenn the labels and be under the label of their text
        labelPanel2.add(totalRevenueLabelT);
        labelPanel2.add(Box.createHorizontalStrut(125)); // Add another 125 pixels of space
        labelPanel2.add(ProfitT);
        labelPanel2.add(Box.createHorizontalStrut(100)); // Add another 100 pixels of space
        //ADIING TO toppanel PANEL
        toppanel.add(labelPanel ,BorderLayout.NORTH);
        toppanel.add(labelPanel2 ,BorderLayout.CENTER);
        //MIDPANNEL
        midpanel.add(from);
        midpanel.add(fromT);
        labelPanel2.add(Box.createHorizontalStrut(200));
        midpanel.add(to);
        midpanel.add(toT);
        labelPanel2.add(Box.createHorizontalStrut(200));
        midpanel.add(searchButton);
        //ADDING TO THE FEATURE PANEL
        featurePanel.add(toppanel, BorderLayout.NORTH);
        featurePanel.add(midpanel, BorderLayout.CENTER);
        featurePanel.add(scrollPane,BorderLayout.SOUTH);
        //ADDING TO THE MAIN PANEL
        homePagePanel.add(featurePanel, BorderLayout.NORTH);
        // Side Bar
        JPanel sideBarPanel = new JPanel();
        sideBarPanel.setBackground(new Color(0, 100, 0)); // Less bright green color
        sideBarPanel.setPreferredSize(new Dimension(200, getHeight()));

        // Add options to the side bar
        JButton homeButton = new JButton("Home");
        styleButton(homeButton);
        JButton manageSalesButton = new JButton("Manage Sales");
        styleButton(manageSalesButton);
        JButton makeBillButton = new JButton("Make Bill");
        styleButton(makeBillButton);
        JButton productManagementButton = new JButton("Product Management");
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
        manageSalesButton.addActionListener(e -> {
            // Show home page panel
            new ManageSalesUI();
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
        SwingUtilities.invokeLater(ManageSalesUI::new);
    }
}
