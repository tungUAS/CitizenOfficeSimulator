package Controller;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ConnectDB.ConnectDB;
import View.AdminInterface;

/**
 * @author Trinh Thanh Tung 1320718.
 * Control the 3 buttons in Admin Interface
 *
 */
public class AdminController {
	private AdminInterface aView;
	
	/**
	 * @param adView for admininterface
	 * initialize
	 */
	public AdminController( AdminInterface adView)
	{
		aView = adView;
		initController();
	}
	
	/**
	 * create the controller over the interface
	 */
	private void initController() {
		aView.btnCustomerDisplay().addActionListener(e->displayListOfCustomer(AdminInterface.table,AdminInterface.scrollPane));
		aView.btnListOfAppointments().addActionListener(e->displayListOfAppointments(AdminInterface.table,AdminInterface.scrollPane));
		aView.btnWorkerDisplay().addActionListener(e->displayListOfWorker(AdminInterface.table,AdminInterface.scrollPane));
	}

	/**
	 * @param table
	 * @param scrollPane
	 * return the list of all booked appointments in database
	 */
	@SuppressWarnings("serial")
	private void displayListOfAppointments(JTable table, JScrollPane scrollPane) {
		try {
			table.setModel(new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
						"Worker","Date","TimeSlot","Customer"
					}
				) {
					@SuppressWarnings("rawtypes")
					Class[] columnTypes = new Class[] {
						Object.class, Object.class, Object.class,Object.class
					};
					@SuppressWarnings({ "unchecked", "rawtypes" })
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});
			scrollPane.setViewportView(table);
			DefaultTableModel tb1Model = (DefaultTableModel)table.getModel();
			ConnectDB.displayAppointments(tb1Model);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/**
	 * @param table
	 * @param scrollPane
	 * return the list of all workers
	 */
	@SuppressWarnings("serial")
	private void displayListOfWorker(JTable table, JScrollPane scrollPane) {
		try {
			table.setModel(new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
						"idWorker","vorname","nachname","email","password","appointments"
					}
				) {
					@SuppressWarnings("rawtypes")
					Class[] columnTypes = new Class[] {
						Object.class, Object.class, Object.class,Object.class,Object.class,Object.class
					};
					@SuppressWarnings({ "unchecked", "rawtypes" })
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});
			scrollPane.setViewportView(table);
			DefaultTableModel tb1Model = (DefaultTableModel)table.getModel();
			ConnectDB.workerDisplay(tb1Model);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/**
	 * @param table
	 * @param scrollPane
	 * return the list of all customers
	 */
	@SuppressWarnings("serial")
	private void displayListOfCustomer(JTable table,JScrollPane scrollPane ) {
		try {
			table.setModel(new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
						"idCustomer","vorname","nachname","email","password"
					}
				) {
					@SuppressWarnings("rawtypes")
					Class[] columnTypes = new Class[] {
						 Object.class, Object.class,Object.class,Object.class,Object.class
					};
					@SuppressWarnings({ "unchecked", "rawtypes" })
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});
			scrollPane.setViewportView(table);
			DefaultTableModel tb1Model = (DefaultTableModel)table.getModel();
			ConnectDB.customerDisplay(tb1Model);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
}
