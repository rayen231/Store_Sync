package UI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;

import presentation.TableModele;

public class ProductManagmentUI extends JFrame {

    JLabel ltitle = new JLabel("Title");
    JLabel ldesc = new JLabel("Description");
    JLabel ltype = new JLabel("Type");
    JLabel lNumber = new JLabel("Number");
    JLabel lrecherche = new JLabel("Rechercher");

    JTextField ttitle = new JTextField(10);
    JTextField tdesc = new JTextField(10);
    JTextField ttype = new JTextField(10);
    JTextField tNumber = new JTextField(10);
    JTextField trecherche = new JTextField(10);

    JButton bajouter = new JButton("Ajouter");
    JButton bannuler = new JButton("Annuler");
    JButton bsupprimer = new JButton("Supprimer");
    JButton brecherche = new JButton("Recherche");

    JButton homeButton = new JButton("Home");
    JButton manageSalesButton = new JButton("Manage Sales");
    JButton makeBillButton = new JButton("Make Bill");
    JButton productManagementButton = new JButton("Product Management");
    
    
    TableProductManagment tm = new TableProductManagment();
	JTable tableau= new JTable(tm);
	JScrollPane jsp = new JScrollPane(tableau);

    JPanel p1 = new JPanel(new BorderLayout());
    JPanel p11 = new JPanel();
    JPanel p12 = new JPanel();
    JPanel p2 = new JPanel(new BorderLayout());
    JPanel p21 = new JPanel();
    
    

    JPanel pt = new JPanel();
    JPanel pd = new JPanel();
    JPanel pty = new JPanel();
    JPanel pn = new JPanel();

    public ProductManagmentUI() {
        super("Product Management");
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        
        JPanel sideBarPanel = new JPanel();
        sideBarPanel.setLayout(new BoxLayout(sideBarPanel, BoxLayout.Y_AXIS));
        sideBarPanel.setBackground(new Color(52, 73, 94)); // Dark blue color

        // Set background colors for panels
        p1.setBackground(Color.WHITE);
        p2.setBackground(Color.WHITE);
        p11.setBackground(Color.WHITE);
        p12.setBackground(Color.WHITE);
        p21.setBackground(Color.WHITE);

        // Set fonts for labels
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        ltitle.setFont(labelFont);
        ldesc.setFont(labelFont);
        ltype.setFont(labelFont);
        lNumber.setFont(labelFont);
        lrecherche.setFont(labelFont);
        bajouter.setFont(labelFont);
        bannuler.setFont(labelFont);
        bsupprimer.setFont(labelFont);
        brecherche.setFont(labelFont);

        // Set background color and text color for buttons
        bajouter.setBackground(Color.GREEN);
        bajouter.setForeground(Color.WHITE);
        bannuler.setBackground(Color.BLUE);
        bannuler.setForeground(Color.WHITE);
        bsupprimer.setBackground(Color.RED);
        bsupprimer.setForeground(Color.WHITE);
        brecherche.setBackground(Color.ORANGE);
        brecherche.setForeground(Color.WHITE);

        sideBarPanel.setBackground(new Color(52, 73, 94)); // Dark blue color

        sideBarPanel.add(homeButton);
        sideBarPanel.add(manageSalesButton);
        sideBarPanel.add(makeBillButton);
        sideBarPanel.add(productManagementButton);

        pt.add(ltitle);
        pt.add(ttitle);

        pd.add(ldesc);
        pd.add(tdesc);

        pty.add(ltype);
        pty.add(ttype);

        pn.add(lNumber);
        pn.add(tNumber);

        p11.add(pt);
        p11.add(pd);
        p11.add(pty);
        p11.add(pn);
        p1.add(p11, BorderLayout.NORTH);

        p12.add(bajouter);
        p12.add(bannuler);
        p12.add(bsupprimer);
        p1.add(p12, BorderLayout.SOUTH);
        

        p21.add(lrecherche);
        p21.add(trecherche);
        p21.add(brecherche);
        p2.add(p21, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel CORRECTPanel = new JPanel(new BorderLayout());
        
        CORRECTPanel.add(p1, BorderLayout.NORTH);
        CORRECTPanel.add(jsp);
        CORRECTPanel.add(p2, BorderLayout.SOUTH);
        
        mainPanel.add(CORRECTPanel, BorderLayout.CENTER);
        mainPanel.add(sideBarPanel, BorderLayout.WEST);
        add(mainPanel);

        pack();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ProductManagmentUI::new);
    }
}
