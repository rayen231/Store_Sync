package frontEnd;

import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import backEnd.DatabaseConnector;
import backEnd.ProfilManipulator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import backEnd.HomeManipulator;

public class Home extends JFrame {
	private DefaultCategoryDataset dataset;

    public Home(String passed) {
        super("Sales Management App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1130,700);
        setLocationRelativeTo(null);
        //Change the icon window image
        ImageIcon icon = new ImageIcon("/Users/Rayen/eclipse-workspace/Project/src/UI/PIC/logo.png");
        Image iconImage = icon.getImage();
        setIconImage(iconImage);
        // Logo
        ImageIcon logoIcon = new ImageIcon("/Users/Rayen/eclipse-workspace/Project/src/UI/PIC/logo.png");
        Image img = logoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon newLogoIcon = new ImageIcon(img);
        JLabel logoLabel = new JLabel(newLogoIcon);
        
        //instance of profilmanipulator*
        ProfilManipulator m= new ProfilManipulator();

        // User Account Profile
        ImageIcon userProfileIcon = new ImageIcon(m.path(passed));
        JButton userProfileButton = new JButton(userProfileIcon);
        userProfileButton.setPreferredSize(new Dimension(40, 40));
        userProfileButton.setBackground(Color.WHITE);
        userProfileButton.setFocusPainted(false);

     // User Name (Clickable)
        JLabel userNameLabel = new JLabel(passed);
        userNameLabel.setForeground(Color.WHITE);
        userNameLabel.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Set cursor to hand

        // Add action listener to user name label
        userNameLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Perform action here
                //JOptionPane.showMessageDialog(null, "User Profile Clicked");
                new EditUser(passed);
            }
        });

        
        
        
        
        // Home Page Panel
        JPanel homePagePanel = new JPanel(new BorderLayout());
        homePagePanel.setBackground(new Color(230, 230, 230)); // Light gray
        homePagePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title
        JLabel titleLabel = new JLabel("<html><div style='text-align: center;'><font size='6' color='#006400'>Welcome to Sales Management App</font></div></html>");
        homePagePanel.add(titleLabel, BorderLayout.NORTH);

        // Features panel
        JPanel featurePanel = new JPanel(new GridBagLayout());
        					//featurePanel.setLayout(new BoxLayout(featurePanel, BoxLayout.Y_AXIS));
         
        // Create dataset
        DefaultCategoryDataset dataset = HomeManipulator.generateDataset();
        
        
        // Create chart
        JFreeChart chart = ChartFactory.createLineChart(
                "Dynamic Graph Example",
                "X-Axis",
                "Y-Axis",
                dataset
        );
        //create total income labels
        JLabel topLabel = new JLabel("<html><div style='text-align: center;'><font size='6' color='#5C3317'>Total income</font></div></html>");
        JLabel bottompanel = new JLabel("<html><div style='text-align: center;'><font size='6' color='#8B0000'>"+HomeManipulator.getTotalIncome()+"</font></div></html>");
        

        // Create Panel
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(600, 400));
        
        //create totalincomepanel
        JPanel totalincomepanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5); // Adjust the insets to reduce spacing
        
        totalincomepanel.add(topLabel, gbc);
        gbc.gridy = 1;
        totalincomepanel.add(bottompanel, gbc);
        
     // Define GridBagConstraints for chartPanel
        GridBagConstraints gbcChartPanel = new GridBagConstraints();
        gbcChartPanel.gridx = 0;
        gbcChartPanel.gridy = 0;
        gbcChartPanel.weightx = 0.8; // 80% of the available horizontal space
        gbcChartPanel.fill = GridBagConstraints.BOTH;

        // Define GridBagConstraints for totalIncomePanel
        GridBagConstraints gbcTotalIncomePanel = new GridBagConstraints();
        gbcTotalIncomePanel.gridx = 1;
        gbcTotalIncomePanel.gridy = 0;
        gbcTotalIncomePanel.weightx = 0.2; // 20% of the available horizontal space
        gbcTotalIncomePanel.fill = GridBagConstraints.BOTH;

        
        // Add components to featurePanel
        featurePanel.add(chartPanel, gbcChartPanel);
        featurePanel.add(totalincomepanel, gbcTotalIncomePanel);
        //home panel adding
        homePagePanel.add(featurePanel, BorderLayout.SOUTH);

        
        
        
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
            new Home(passed);
            dispose();
            // Hide other panels if needed
        });
        makeBillButton.addActionListener(e -> {
            // Show home page panel
        	new MakeBillUI(passed);
        	 dispose();
            // Hide other panels if needed
        });
        productManagementButton.addActionListener(e -> {
            // Show home page panel
            new ManagementProduct(passed);
            dispose();
            // Hide other panels if needed
        });
        manageSalesButton.addActionListener(e -> {
            // Show home page panel
            new ManageSalesUI(passed);
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
    
    
    
    public static void main(String[] args, String passed) {
    	SwingUtilities.invokeLater(() -> new Home(passed));
    }
}
