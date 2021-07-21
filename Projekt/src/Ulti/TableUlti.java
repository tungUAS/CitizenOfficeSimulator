package Ulti;

import javax.swing.table.DefaultTableModel;

/**
 * @author Thanh Tung Trinh
 * responsible for table handling
 */
public class TableUlti {
	/**
	 * Function is used in AdminInterface,Booking,CustomerInterface,WorkerInterface
	 * It removes all rows from chosen table
	 * @param tb1Model is table model for output. each tables has its own model which is implemented in its class
	 */
	public static void RemoveAllRows(DefaultTableModel tb1Model)
	{
		for (int i = tb1Model.getRowCount() - 1; i >= 0; i--) {
		    tb1Model.removeRow(i);
		}
	}
}
