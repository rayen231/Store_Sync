import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        //pack();
        setLocationRelativeTo(null);

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

        setVisible(true);
    }

    // Dummy method to simulate login validation
    private boolean isValidLogin(String username, String password) {
        // Replace this with actual login validation logic
        return username.equals("admin") && password.equals("admin");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginUI();
            }
        });
    }
}
