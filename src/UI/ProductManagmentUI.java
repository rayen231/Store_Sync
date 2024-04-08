package UI;

import javax.swing.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

import java.awt.*;

public class ProductManagmentUI extends JFrame {
	
	
	
	JLabel lidR = new JLabel("Id");
	JLabel lid = new JLabel("Id");
	JLabel lnom = new JLabel("Name");
	JLabel ltype = new JLabel("Type");
	JLabel lplace = new JLabel("Place");
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
	
	
	JTable tableau= new JTable();
	JScrollPane jsp = new JScrollPane(tableau);
	
	JPanel global = new JPanel (new GridLayout(1,1));
	JPanel p1 = new JPanel (new GridLayout(2,2));
	JPanel p11 = new JPanel ();
	JPanel p12 = new JPanel ();
	JPanel p2 = new JPanel (new BorderLayout());
	JPanel p21 = new JPanel ();
	JPanel p22 = new JPanel ();
	
	JPanel pn = new JPanel ();
	JPanel pp = new JPanel ();
	JPanel pl = new JPanel ();
	JPanel pf = new JPanel ();
	JPanel ps = new JPanel ();
	JPanel pfr = new JPanel ();
	
	
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;

	void initialize() {
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
		
		
		bajouter.addActionListener(x->{
			
			String nome = tnom.getText();
			String quantity = tquantity.getText();
			String type = (String) comboFilliere.getSelectedItem();
			String place="";
			if(bh.isSelected()) {
				place="Storehouse 1";
			}else if(bf.isSelected()) {
				place="Storehouse 1";
			}
			if(nome.equals("")||quantity.equals("")||place.equals("")) {
				JOptionPane.showMessageDialog(this, "erreur de saisie");
			}else {
				try {
					
			        pst = con.prepareStatement("insert into products(name, quantity, type, place) values(?, ?, ?, ?)");
			        pst.setString(1, nome);
			        pst.setString(2, quantity);
			        pst.setString(3, type);
			        pst.setString(4, place);
			        pst.executeUpdate();
			        JOptionPane.showMessageDialog(null, "Products Addedddd!!!!!");
			        table_load();
			                       
			        tnom.setText("");
			        tquantity.setText("");
			        comboFilliere.setSelectedIndex(0); 
			        bg.clearSelection();
			        
			       }
			    catch (SQLException e1) 
			        {            
			       e1.printStackTrace();
			    }
			}
		});
		
		brecherche.addActionListener(x -> {
		    try {
		        String id = tidR.getText();
		        pst = con.prepareStatement("select name, quantity, type, place from products where id = ?");
		        pst.setString(1, id);
		        ResultSet rs = pst.executeQuery();
		        if (rs.next()) {
		            String name = rs.getString(1);
		            String quantity = rs.getString(2);
		            String type = rs.getString(3);
		            String place = rs.getString(4); // This should be 4, not 3
		            // Set retrieved values to corresponding text fields
		            tnom.setText(name);
		            tquantity.setText(quantity);
		            // Assuming comboFilliere is a JComboBox, you need to set its selected item, not setText() as it's not a text field
		            comboFilliere.setSelectedItem(type);
		            // Set the selected radio button based on the retrieved place
		            if (place.equals("Storehouse 1")) {
		                bh.setSelected(true);
		            } else if (place.equals("Storehouse 2")) {
		                bf.setSelected(true);
		            }
		        } else {
		            // Clear text fields if no matching record found
		            tnom.setText("");
		            tquantity.setText("");
		            comboFilliere.setSelectedIndex(0);
		            bg.clearSelection();
		        }
		    } catch (SQLException ex) {
		        ex.printStackTrace();
		        // Handle SQL exception
		    }
		});
		
		bedit.addActionListener(x -> {
		    String nome, quantity, type, place, id;
		            
		    nome = tnom.getText();
		    quantity = tquantity.getText();
		    type = (String) comboFilliere.getSelectedItem();
		    place = "";
		    if (bh.isSelected()) {
		        place = "Storehouse 1";
		    } else if (bf.isSelected()) {
		        place = "Storehouse 2";
		    }
		    id = tidR.getText(); // Assuming tidR contains the ID of the product to be edited

		    if (nome.equals("") || quantity.equals("") || place.equals("")) {
		        JOptionPane.showMessageDialog(this, "Erreur de saisie");
		    } else {
		        try {
		            pst = con.prepareStatement("UPDATE products SET name=?, quantity=?, type=?, place=? WHERE id=?");
		            pst.setString(1, nome);
		            pst.setString(2, quantity);
		            pst.setString(3, type);
		            pst.setString(4, place);
		            pst.setString(5, id);
		            pst.executeUpdate();
		            JOptionPane.showMessageDialog(null, "Produit mis à jour!");
		            table_load();
		            
		            // Clear input fields after update
		            tnom.setText("");
		            tquantity.setText("");
		            comboFilliere.setSelectedIndex(0);
		            bg.clearSelection();
		            tidR.setText(""); // Clear ID field after update
		        } catch (SQLException e1) {
		            e1.printStackTrace();
		        }
		    }
		});
		
		bsupprimer.addActionListener(x -> {
		    String idToDelete = tidR.getText(); // Assuming tidR contains the ID of the record to be deleted
		    
		    try {
		        pst = con.prepareStatement("DELETE FROM products WHERE id = ?");
		        pst.setString(1, idToDelete);
		        pst.executeUpdate();
		        JOptionPane.showMessageDialog(null, "Enregistrement supprimé!");
		        table_load(); // Reload the table after deletion
		        
		        // Clear input fields after deletion
		        tnom.setText("");
		        tquantity.setText("");
		        comboFilliere.setSelectedIndex(0);
		        bg.clearSelection(); // Clear selection of radio buttons
		        tidR.setText(""); // Clear ID field after deletion
		    } catch (SQLException e1) {
		        e1.printStackTrace();
		    }
		});

	}
	
	
	public ProductManagmentUI() {
		super("Gestion des Etudients");
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		Connect();
		table_load();
		initialize();
		
		pack();
		
	}
	
	public void table_load()
	{
	    try 
	    {
	    pst = con.prepareStatement("select * from products");
	    rs = pst.executeQuery();
	    tableau.setModel(DbUtils.resultSetToTableModel(rs));
	} 
	    catch (SQLException e) 
	     {
	        e.printStackTrace();
	  } 
	}
	
	public void Connect() {
		
		try{  
			   Class.forName("com.mysql.cj.jdbc.Driver");
			   con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Project","root","");  
			   
		   }catch(Exception e){
			    System.out.println(e);
		   } 
		
		
	}
	
	public static void main(String[] args) {
		
		new ProductManagmentUI();
		
	}

	
}
