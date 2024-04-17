package frontEnd;

import javax.swing.*;

import backEnd.LoginManipulator;

import java.awt.*;
import java.awt.event.*;

public class LoginUI extends JFrame {
    // Labels
    JLabel usernameLabel = new JLabel("Username:");
    JLabel passwordLabel = new JLabel("Password:");
    
    
    
    // Text Fields
    JTextField usernameField = new JTextField(15);
    JPasswordField passwordField = new JPasswordField(15);	

    // Buttons
    JButton loginButton = new JButton("Login");
    JButton cancelButton = new JButton("Cancel");

    public LoginUI() {
        super("Login");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);
        
      //Change the icon window image
        ImageIcon icon = new ImageIcon("/Users/Rayen/eclipse-workspace/Project/src/UI/PIC/logo.png");
        Image iconImage = icon.getImage();
        setIconImage(iconImage);

        // Panels
        JPanel mainPanel = new JPanel(new GridLayout(3, 2));
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        // Adding components to the main panel
        mainPanel.add(usernameLabel);
        mainPanel.add(usernameField);
        mainPanel.add(passwordLabel);
        mainPanel.add(passwordField);

        // Adding buttons to the button panel
        buttonPanel.add(loginButton);
        buttonPanel.add(cancelButton);

        // Adding action listeners to the buttons
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform login action here
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                // Validate username and password
                if (isValidLogin(username, password)) {
                    JOptionPane.showMessageDialog(LoginUI.this, "Login successful");
                    // Redirect to main application window or perform any other action
                    // Here, you can open the main application window
                    new Home(username);
                    dispose(); // Close the login window
                } else {
                    JOptionPane.showMessageDialog(LoginUI.this, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the login window
            }
        });

        // Adding panels to the frame
        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Adjusting colors and styles
        customizeUI();

        setVisible(true);
    }

    // Dummy method to simulate login validation
    private boolean isValidLogin(String username, String password) {
        // Replace this with actual login validation logic
        //return username.equals("admin") && password.equals("admin");
    	LoginManipulator l= new LoginManipulator();
    	return l.check(username, password);
    	
    	
    }

    // Method to adjust colors and styles
 // Method to adjust colors and styles
    private void customizeUI() {
        getContentPane().setBackground(new Color(0, 100, 0)); // Less bright green color

        usernameLabel.setForeground(new Color(0, 100, 0)); // Less bright green color
        passwordLabel.setForeground(new Color(0, 100, 0)); // Less bright green color

        usernameField.setBackground(new Color(230, 230, 230)); // Light gray
        passwordField.setBackground(new Color(230, 230, 230)); // Light gray

        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(new Color(0, 100, 0)); // Less bright green color
        loginButton.setFocusPainted(false);

        cancelButton.setForeground(Color.WHITE);
        cancelButton.setBackground(new Color(0, 100, 0)); // Less bright green color
        cancelButton.setFocusPainted(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginUI::new);
    }

}
