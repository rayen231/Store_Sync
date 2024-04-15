package frontEnd;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class SalesTableModel extends AbstractTableModel {
    private List<Object[]> data;
    private String[] columnNames;

    public SalesTableModel(String[] columnNames) {
        this.columnNames = columnNames;
        this.data = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data.get(rowIndex)[columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    // Method to add a row to the table
    public void addRow(Object[] rowData) {
        data.add(rowData);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    // Method to remove a row from the table
    public void removeRow(int rowIndex) {
        data.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }
    public void clearData() {
        int rowCount = getRowCount();
        data.clear();
        fireTableRowsDeleted(0, rowCount - 1);
    }

    // Method to update a row in the table
    public void updateRow(int rowIndex, Object[] rowData) {
        data.set(rowIndex, rowData);
        fireTableRowsUpdated(rowIndex, rowIndex);
    }
}
