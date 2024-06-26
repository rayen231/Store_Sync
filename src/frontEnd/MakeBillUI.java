package frontEnd;

import javax.swing.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import backEnd.DatabaseConnector;
import backEnd.MakeBillManipulator;
import backEnd.ProfilManipulator;

import org.apache.pdfbox.pdmodel.font.PDType1Font;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

public class MakeBillUI extends JFrame {

    protected static final Integer Qts = null;
    private Map<String, Integer> textQuantityDict;

	public MakeBillUI(String passed) {
        super("Sales Management App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1130,700);
        setLocationRelativeTo(null);
        
        // Initialize the map in the constructor
        textQuantityDict = new HashMap<>();
        
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
            	new EditUser(passed);
            }
        });

        // Home Page Panel
        JPanel homePagePanel = new JPanel(new BorderLayout());
        homePagePanel.setBackground(new Color(230, 230, 230)); // Light gray
        homePagePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title Panel (from MakeBillUI)
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(new Color(46, 139, 87)); // Dark green color
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("Create New Bill");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titlePanel.add(titleLabel, BorderLayout.CENTER);

        homePagePanel.add(titlePanel, BorderLayout.NORTH);

        // Form Panel (from MakeBillUI)
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel cinLabel = new JLabel("Customer Identification Number (CIN):");
        cinLabel.setForeground(new Color(46, 139, 87)); // Dark green color
        formPanel.add(cinLabel, gbc);

        JTextField cinTextField = new JTextField(20);
        gbc.gridx = 1;
        formPanel.add(cinTextField, gbc);

        JLabel nameLabel = new JLabel("Customer Name:");
        nameLabel.setForeground(new Color(46, 139, 87)); // Dark green color
        gbc.gridx = 0;
        formPanel.add(nameLabel, gbc);

        JTextField nameTextField = new JTextField(20);
        gbc.gridx = 1;
        formPanel.add(nameTextField, gbc);

        JLabel itemsLabel = new JLabel("Items Purchased:");
        itemsLabel.setForeground(new Color(46, 139, 87)); // Dark green color
        gbc.gridx = 0;
        formPanel.add(itemsLabel, gbc);

        JTextField itemsTextField = new JTextField(20);
        gbc.gridx = 1;
        formPanel.add(itemsTextField, gbc);

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setForeground(new Color(46, 139, 87)); // Dark green color
        gbc.gridx = 0;
        formPanel.add(quantityLabel, gbc);

        JTextField quantityTextField = new JTextField(5);
        gbc.gridx = 1;
        formPanel.add(quantityTextField, gbc);

        homePagePanel.add(formPanel, BorderLayout.CENTER);

        // Buttons Panel (from MakeBillUI)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.WHITE);

        JButton addButton = new JButton("Add Item");
        addButton.setForeground(Color.WHITE);
        addButton.setBackground(new Color(46, 139, 87)); // Dark green color
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the item and quantity from text fields
                String item = itemsTextField.getText();
                String quantityText = quantityTextField.getText();
                
                try {
                    // Convert the quantity text to an integer
                    int quantity = Integer.parseInt(quantityText);
                    
                    // If quantity is valid, store the item and quantity
                    textQuantityDict.put(item, quantity);
                    
                    // Output for verification
                    System.out.println("Item: " + item + ", Quantity: " + quantity);
                    
                    // Reset text fields
                    itemsTextField.setText("");
                    quantityTextField.setText("");
                } catch (NumberFormatException ex) {
                    // Handle the case where the text cannot be parsed as an integer
                    JOptionPane.showMessageDialog(null, "Please enter a valid integer for quantity.");
                }
            }
        });

        JButton submitButton = new JButton("Submit Bill");
        submitButton.setForeground(Color.WHITE);
        submitButton.setBackground(new Color(46, 139, 87)); // Dark green color
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	//Make instance 
            	MakeBillManipulator m= new MakeBillManipulator();
            	
            	//adding bill into the DB
            	String cin=cinTextField.getText();
            	String name=nameTextField.getText();
            	m.add(cin,name,textQuantityDict);
            	
            	//Making pdf content
            	String content="NAME : "+name+"\nCIN : "+cin+"\nITEMS : \n"+textQuantityDict.toString();
            	
            	// Generate PDF file
                try {
                    Document document = new Document();
                    PdfWriter.getInstance(document, new FileOutputStream("output.pdf"));
                    document.open();
                    document.add(new Paragraph(content));
                    document.close();
                    System.out.println("PDF created successfully.");
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

                // Print the generated PDF file
                m.printPDF("output.pdf");
            	
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(submitButton);
        homePagePanel.add(buttonPanel, BorderLayout.SOUTH);

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
        makeBillButton.addActionListener(e -> {
            // Show home page panel
        	new MakeBillUI(passed);
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
        JPanel titlePanel2 = new JPanel();
        titlePanel2.setBackground(new Color(0, 100, 0)); // Less bright green color
        JLabel appTitleLabel = new JLabel("Sales Management App");
        appTitleLabel.setForeground(Color.WHITE);
        appTitleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titlePanel2.add(appTitleLabel);
        headerPanel.add(titlePanel2, BorderLayout.CENTER);
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
    	SwingUtilities.invokeLater(() -> new MakeBillUI(passed));
    }
}
