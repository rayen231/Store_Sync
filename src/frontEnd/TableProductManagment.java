package frontEnd;

import javax.swing.table.AbstractTableModel;

public class TableProductManagment extends AbstractTableModel {
    
    private final String[] columnNames = {"ID", "Name", "Type", "Place", "Quantity", "Actions"};
    private final Object[][] data = {
            {"1", "Product 1", "Type 1", "Place 1", "10", "Edit/Delete"},
            {"2", "Product 2", "Type 2", "Place 2", "20", "Edit/Delete"},
            {"3", "Product 3", "Type 3", "Place 3", "30", "Edit/Delete"},
            {"4", "Product 4", "Type 1", "Place 4", "40", "Edit/Delete"},
            {"5", "Product 5", "Type 2", "Place 5", "50", "Edit/Delete"},
            {"1", "Product 1", "Type 1", "Place 1", "10", "Edit/Delete"},
            {"2", "Product 2", "Type 2", "Place 2", "20", "Edit/Delete"},
            {"3", "Product 3", "Type 3", "Place 3", "30", "Edit/Delete"},
            {"4", "Product 4", "Type 1", "Place 4", "40", "Edit/Delete"},
            {"5", "Product 5", "Type 2", "Place 5", "50", "Edit/Delete"}
    };
    
    @Override
    public int getRowCount() {
        return data.length;
    }
    
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    
    @Override
    public Object getValueAt(int row, int col) {
        return data[row][col];
    }
    
    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }
    
    @Override
    public boolean isCellEditable(int row, int col) {
        // Make only the last column editable, assuming it's the Actions column
        return col == 5;
    }
}
