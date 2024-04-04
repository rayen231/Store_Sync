package UI;

import java.util.List;

import javax.swing.table.AbstractTableModel;



public class TableProductManagment extends AbstractTableModel {
	
	@Override
	public int getRowCount() {
		return 5;
	}
	
	@Override
	public int getColumnCount() {
		return 5;
	}


	@Override
	public Object getValueAt(int l, int c) {
		switch(c) {
			case 0:return "sdf";
			case 1:return "sdf";
			case 2:return "sdf";
			case 3:return "sdf";
			case 4:return "sdf";
		}
	
		return null;
	}
	
}
