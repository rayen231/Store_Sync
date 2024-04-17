package frontEnd;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import backEnd.ProductManipulator;

public class ProductTableModel extends AbstractTableModel {
    private List<String[]> productList;
    private String[] columnNames = {"ID", "Name", "Quantity", "Type", "Place"}; // Add "ID" column name

    public ProductTableModel() {
        ProductManipulator m = new ProductManipulator();
        this.productList = new ArrayList<>();
        productList = m.loadDataFromDatabase();
    }

    public void setData(List<String[]> productList) {
        this.productList = productList;
        fireTableDataChanged(); // Notify the table that the data has changed
    }

    public void addData(String[] newData) {
        productList.add(newData);
        fireTableRowsInserted(productList.size() - 1, productList.size() - 1); // Notify the table of the new row
    }

    @Override
    public int getRowCount() {
        return productList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int row, int column) {
        String[] rowData = productList.get(row);
        return rowData[column];
    }
}
