package UI;

import javax.swing.table.AbstractTableModel;

public class SalesTableModel extends AbstractTableModel {
    private Object[][] data;
    private String[] columnNames;

    public SalesTableModel(Object[][] data, String[] columnNames) {
        this.data = data;
        this.columnNames = columnNames;
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}
