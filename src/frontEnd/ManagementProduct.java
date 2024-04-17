package frontEnd;

import javax.swing.*;

import backEnd.DatabaseConnector;
import backEnd.ProductManipulator;
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
import java.util.List;

public class ManagementProduct extends JFrame {

    public ManagementProduct(String passed) {
        super("Product Managment App");
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
        homePagePanel.setBorder(BorderFactory.createEmptyBorder(4, 20, 20, 20));

        // Title
        JLabel titleLabel = new JLabel("<html><div style='text-align: center;'><font size='6' color='#006400'>Product Management</font></div></html>");
        homePagePanel.add(titleLabel, BorderLayout.NORTH);

        // Features
        JLabel lidR = new JLabel("Id");
    	JLabel lid = new JLabel("Id");
    	JLabel lnom = new JLabel("Name");
    	JLabel ltype = new JLabel("Type :");
    	JLabel lplace = new JLabel("Place :");
    	JLabel lquantity = new JLabel("Quantity");
    	JLabel lrecherche = new JLabel("Recherche");
    	
    	JTextField tidR = new JTextField(10);
    	JTextField tid = new JTextField(10);
    	JTextField tnom = new JTextField(10);
    	JTextField tplace = new JTextField(10);
    	JTextField tquantity = new JTextField(10);
    	
    	
    	JButton bajouter = new JButton("Ajouter");
    	JButton bedit = new JButton("Edit");
    	JButton bsupprimer = new JButton("Supprimer");
    	JButton brecherche = new JButton("Recherche");
    	
    	JRadioButton bh = new JRadioButton("storehouse 1");
    	JRadioButton bf = new JRadioButton("storehouse 2");
    	
    	JComboBox<String> comboFilliere = new JComboBox<String>(new String[] {"breakable","not breakable"});
    	
    	ButtonGroup bg = new ButtonGroup();
    	
    	ProductTableModel model = new ProductTableModel();
    	JTable tableau= new JTable(model);
    	JScrollPane jsp = new JScrollPane(tableau);
    	
    	JPanel global = new JPanel (new GridLayout(1,1));
    	JPanel p1 = new JPanel (new GridLayout(2,2));
    	p1.setBorder(BorderFactory.createTitledBorder("Add Product"));
    	JPanel p11 = new JPanel ();
    	JPanel p12 = new JPanel ();
    	JPanel p2 = new JPanel (new BorderLayout());
    	
    	JPanel p21 = new JPanel ();
    	JPanel p22 = new JPanel ();
    	p21.setBorder(BorderFactory.createTitledBorder("Search"));
    	
    	JPanel pn = new JPanel ();
    	JPanel pp = new JPanel ();
    	JPanel pl = new JPanel ();
    	JPanel pf = new JPanel ();
    	JPanel ps = new JPanel ();
    	JPanel pfr = new JPanel ();
    	
    	//adding to the panel
    	pn.add(lnom);
		pn.add(tnom);
		
		
		
		pfr.add(lidR);
		pfr.add(tidR);
		
		pl.add(lquantity);
		pl.add(tquantity);
		
		pf.add(ltype);
		pf.add(comboFilliere);
		
		ps.add(lplace);
		ps.add(bh);
		ps.add(bf);
		
		bg.add(bf);
		bg.add(bh);
		
		p11.add(pn);p11.add(pl);p11.add(pf);p11.add(ps);
		p1.add(p11, BorderLayout.NORTH);
		
		p12.add(bajouter);
		
		
		
		p1.add(p12);
		global.add(p1);
		
		p21.add(pfr);p21.add(brecherche);p21.add(bsupprimer);p21.add(bedit);
		p2.add(p21,BorderLayout.SOUTH);
		p2.add(jsp,BorderLayout.NORTH);
		global.add(p2);
		add(global);
        
        
        //adding the main panel
        homePagePanel.add(global, BorderLayout.SOUTH);
        
        //global func
        
        //ajouter
        bajouter.addActionListener(x->{
			
			String nome = tnom.getText();
			String quantity = tquantity.getText();
			String type = (String) comboFilliere.getSelectedItem();
			String place="";
			if(bh.isSelected()) {
				place="Storehouse 1";
			}else if(bf.isSelected()) {
				place="Storehouse 2";
			}
			if(nome.equals("")||quantity.equals("")||place.equals("")) {
				JOptionPane.showMessageDialog(this, "erreur de saisie");
			}else {
				ProductManipulator mm= new ProductManipulator();
				mm.addProduct(nome, type, place,quantity);
				// Refresh the table
			    ProductTableModel tableModel = (ProductTableModel) tableau.getModel();
			    tableModel.setData(mm.loadDataFromDatabase());
				
			}
		});
        
        //Seacrh
        
        brecherche.addActionListener(x -> {
            ProductManipulator mm = new ProductManipulator();
            String id = tidR.getText();
            if (id.isEmpty()) {
                // Refresh the table
                ProductTableModel tableModel = (ProductTableModel) tableau.getModel();
                tableModel.setData(mm.loadDataFromDatabase());
            } else {
                try {
                    int idt = Integer.parseInt(id);
                    List<String[]> productList = mm.searchProduct(idt);
                    
                 // Check if there is a result
                    if (!productList.isEmpty()) {
                        String[] product = productList.get(0); // Assuming only one result is returned
                        String name = product[1];
                        String quantity = product[2];
                        String type = product[3];
                        String place = product[4];
                        System.out.print(name+quantity+type+place);
                        tnom.setText(name);
                        tquantity.setText(quantity);
                        comboFilliere.setSelectedItem(type);
                        if (place.equals("Storehouse 1")) {
                            bh.setSelected(true);
                        } else if (place.equals("Storehouse 2")) {
                            bf.setSelected(true);
                        }
                    }
                    model.setData(productList);
                } catch (NumberFormatException ex) {
                    // Handle the case where the input is not a valid integer
                    ex.printStackTrace();
                    // Optionally, you can display a message to the user
                }
            }
        });
        
        
        //delete 
        bsupprimer.addActionListener(x -> {
		    String idToDelete = tidR.getText(); // Assuming tidR contains the ID of the record to be deleted
		    ProductManipulator mm= new ProductManipulator();
		    int idt = Integer.parseInt(idToDelete);
		    mm.deleteProductById(idt);
		 // Refresh the table
		    ProductTableModel tableModel = (ProductTableModel) tableau.getModel();
		    tableModel.setData(mm.loadDataFromDatabase());
		 // initial all input 
            tidR.setText(""); // Clear the ID text field
            tnom.setText(""); // Clear the name text field
            tquantity.setText(""); // Clear the quantity text field
            comboFilliere.setSelectedIndex(0); // Set the combo box selection to the first item
            bg.clearSelection(); // Deselect all radio buttons
		    
		});
        
     // Edit
        bedit.addActionListener(x -> {
            String idToUpdate = tidR.getText(); // Assuming tidR contains the ID of the record to be updated
            String nome = tnom.getText();
            String quantity = tquantity.getText();
            String type = (String) comboFilliere.getSelectedItem();
            String place = "";
            if (bh.isSelected()) {
                place = "Storehouse 1";
            } else if (bf.isSelected()) {
                place = "Storehouse 2";
            }
            if (idToUpdate.isEmpty() || nome.isEmpty() || quantity.isEmpty() || place.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Erreur de saisie");
            } else {
                try {
                    int id = Integer.parseInt(idToUpdate);
                    int qty = Integer.parseInt(quantity);
                    ProductManipulator mm = new ProductManipulator();
                    boolean success = mm.updateProductById(id, nome, type, place, qty);
                    if (success) {
                        JOptionPane.showMessageDialog(this, "Produit mis à jour avec succès");
                        // Refresh the table
                        ProductTableModel tableModel = (ProductTableModel) tableau.getModel();
                        tableModel.setData(mm.loadDataFromDatabase());
                        
                        // initial all input 
                        tidR.setText(""); // Clear the ID text field
                        tnom.setText(""); // Clear the name text field
                        tquantity.setText(""); // Clear the quantity text field
                        comboFilliere.setSelectedIndex(0); // Set the combo box selection to the first item
                        bg.clearSelection(); // Deselect all radio buttons
                    } else {
                        JOptionPane.showMessageDialog(this, "Impossible de mettre à jour le produit");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "L'ID et la quantité doivent être des entiers");
                }
            }
        });


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
