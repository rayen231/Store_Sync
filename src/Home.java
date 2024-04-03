import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame {

    public Home() {
        super("Sales Management App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);

        // Logo
        ImageIcon logoIcon = new ImageIcon("/Users/Rayen/eclipse-workspace/Project/src/logo.png");
        Image img = logoIcon.getImage();
        Image newImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon newLogoIcon = new ImageIcon(newImg);
        JLabel logoLabel = new JLabel(newLogoIcon);

        // User Account Profile
        ImageIcon userProfileIcon = new ImageIcon("user_profile.png");
        JButton userProfileButton = new JButton(userProfileIcon);
        userProfileButton.setPreferredSize(new Dimension(40, 40));

        // User Name
        JLabel userNameLabel = new JLabel("John Doe");
        userNameLabel.setForeground(Color.WHITE);

        // Home Page Panel
        JPanel homePagePanel = new JPanel(new BorderLayout());
        homePagePanel.setBackground(Color.WHITE);
        homePagePanel.setPreferredSize(new Dimension(600, 400));
        
        // Title
        JLabel titleLabel = new JLabel("<html><div style='text-align: center;'><font size='6' color='#2E8B57'>Welcome to Sales Management App</font></div></html>");
        homePagePanel.add(titleLabel, BorderLayout.NORTH);
        
        // Features
        JLabel todaySaleLabel = new JLabel("<html><font size='6' color='#2E8B57'>Today's Sale: $5000</font></html>");
        JLabel totalRevenueLabel = new JLabel("<html><font size='6' color='#2E8B57'>Total Sales Revenue: $10000</font></html>");
        JPanel featurePanel = new JPanel();
        featurePanel.setLayout(new BoxLayout(featurePanel, BoxLayout.Y_AXIS));
        featurePanel.add(Box.createVerticalGlue());
        featurePanel.add(todaySaleLabel);
        featurePanel.add(Box.createVerticalStrut(10));
        featurePanel.add(totalRevenueLabel);
        featurePanel.add(Box.createVerticalGlue());
        homePagePanel.add(featurePanel, BorderLayout.CENTER);

        // Side Bar
        JPanel sideBarPanel = new JPanel();
        sideBarPanel.setLayout(new BoxLayout(sideBarPanel, BoxLayout.Y_AXIS));
        sideBarPanel.setBackground(new Color(52, 73, 94)); // Dark blue color

        // Add options to the side bar
        JButton homeButton = new JButton("Home");
        homeButton.setForeground(Color.WHITE);
        homeButton.setBackground(new Color(41, 128, 185)); // Blue color
        JButton manageSalesButton = new JButton("Manage Sales");
        manageSalesButton.setForeground(Color.WHITE);
        manageSalesButton.setBackground(new Color(41, 128, 185)); // Blue color
        JButton makeBillButton = new JButton("Make Bill");
        makeBillButton.setForeground(Color.WHITE);
        makeBillButton.setBackground(new Color(41, 128, 185)); // Blue color
        JButton productManagementButton = new JButton("Product Management");
        productManagementButton.setForeground(Color.WHITE);
        productManagementButton.setBackground(new Color(41, 128, 185)); // Blue color
        // Add more options as needed

        // Add action listeners to the side bar buttons
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show home page panel
                homePagePanel.setVisible(true);
                // Hide other panels if needed
            }
        });

        // Add buttons to the side bar panel
        sideBarPanel.add(Box.createVerticalStrut(20)); // Space at the top
        sideBarPanel.add(homeButton);
        sideBarPanel.add(Box.createVerticalStrut(10)); // Space between buttons
        sideBarPanel.add(manageSalesButton);
        sideBarPanel.add(Box.createVerticalStrut(10)); // Space between buttons
        sideBarPanel.add(makeBillButton);
        sideBarPanel.add(Box.createVerticalStrut(10)); // Space between buttons
        sideBarPanel.add(productManagementButton);
        // Add more buttons as needed

        // Main Panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel logoPanel = new JPanel(new BorderLayout()); // Modified layout to BorderLayout
        logoPanel.setBackground(new Color(52, 73, 94)); // Dark blue color
        logoPanel.add(logoLabel, BorderLayout.WEST); // Logo at the left side
        mainPanel.add(logoPanel, BorderLayout.NORTH); // Add logoPanel to the mainPanel
        JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        userPanel.setBackground(new Color(52, 73, 94)); // Dark blue color
        userPanel.add(userProfileButton);
        userPanel.add(userNameLabel);
        mainPanel.add(userPanel, BorderLayout.EAST);
        mainPanel.add(homePagePanel, BorderLayout.CENTER);
        mainPanel.add(sideBarPanel, BorderLayout.WEST);

        // Add components to the frame
        add(mainPanel);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Home();
            }
        });
    }
}
