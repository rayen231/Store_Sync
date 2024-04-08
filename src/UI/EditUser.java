package UI;

import javax.swing.*;

import Sql.DatabaseConnector;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EditUser extends JFrame {
	public String user;
    public EditUser(String passed) {

        super("Edit User");
    	user=passed;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

      //Change the icon window image
        ImageIcon icon = new ImageIcon("/Users/Rayen/eclipse-workspace/Project/src/UI/PIC/logo.png");
        Image iconImage = icon.getImage();
        
        // Main panel with light gray background
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(230, 230, 230));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Panel for labels and fields with BoxLayout
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.setBackground(new Color(230, 230, 230));

        // Name label and text field
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(20);
        inputPanel.add(nameLabel);
        inputPanel.add(Box.createVerticalStrut(5)); // Add vertical spacing
        inputPanel.add(nameField);
        inputPanel.add(Box.createVerticalStrut(10)); // Add vertical spacing

        // Picture label and button for file upload
        JLabel picLabel = new JLabel("Picture:");
        JButton uploadButton = new JButton("Upload");
        JTextField picField = new JTextField(20);
        picField.setEditable(false); // Make text field read-only
        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open file chooser dialog for selecting a file
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(EditUser.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    picField.setText(selectedFile.getAbsolutePath());
                }
            }
        });
        inputPanel.add(picLabel);
        inputPanel.add(Box.createVerticalStrut(5)); // Add vertical spacing
        JPanel picUploadPanel = new JPanel(new BorderLayout());
        picUploadPanel.add(picField, BorderLayout.CENTER);
        picUploadPanel.add(uploadButton, BorderLayout.EAST);
        inputPanel.add(picUploadPanel);
        inputPanel.add(Box.createVerticalStrut(10)); // Add vertical spacing

        // Description label and text area
        JLabel descLabel = new JLabel("Description:");
        JTextArea descArea = new JTextArea(5, 20);
        JScrollPane descScrollPane = new JScrollPane(descArea);
        inputPanel.add(descLabel);
        inputPanel.add(Box.createVerticalStrut(5)); // Add vertical spacing
        inputPanel.add(descScrollPane);
        inputPanel.add(Box.createVerticalStrut(10)); // Add vertical spacing

        // Panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(230, 230, 230));

        // Save button with green background
        JButton saveButton = new JButton("Save");
        saveButton.setBackground(new Color(0, 100, 0));
        saveButton.setForeground(Color.WHITE);
        saveButton.setFocusPainted(false);
        saveButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Set padding
        buttonPanel.add(saveButton);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get Text
            	String path=picField.getText();
            	String name=nameField.getText();
            	String Desc=descArea.getText();
            	edit(name,Desc,path);
            	dispose();
                
                
            }
        });

        // Cancel button with green background
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBackground(new Color(0, 100, 0));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFocusPainted(false);
        cancelButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Set padding
        cancelButton.addActionListener(e -> dispose());
        buttonPanel.add(cancelButton);

        // Add inputPanel and buttonPanel to mainPanel
        mainPanel.add(inputPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add mainPanel to the frame
        add(mainPanel);

        setVisible(true);
    }
    public void edit(String name,String desc,String path)
    {
    	// SQL query to update the user table
        String sql = "UPDATE user SET NAME = ?, DESCRIPTION = ?, PATH = ? WHERE NAME = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            // Set parameters
            statement.setString(1, name);
            statement.setString(2, desc);
            statement.setString(3, path);
            statement.setString(4, user);
            user=name;

            // Execute the update
            int rowsUpdated = statement.executeUpdate();

            // Check if the update was successful
            if (rowsUpdated > 0) {
                System.out.println("User information updated successfully.");
            } else {
                System.out.println("No user found with the specified ID.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any potential errors
        }
    	
    }
}
