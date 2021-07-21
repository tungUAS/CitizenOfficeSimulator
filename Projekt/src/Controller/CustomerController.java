package Controller;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import ConnectDB.ConnectDB;
import Model.Customer;
import Ulti.TableUlti;
import View.BookingInterface;
import View.CustomerInterface;
import View.Login;

/**
 * @author Trinh Thanh Tung
 * Control the flow of input data over Customer Interface
 */
public class CustomerController {
	private  Customer customer;
	private  CustomerInterface cView; 
	
	/**
	 * @param m for this customer
	 * @param cview for Customer interface
	 * initialize the controller over interface GUI
	 */
	public CustomerController(Customer m, CustomerInterface cview)
	{
		customer = m;
		cView = cview;
		initView();
		initController();
	}
	
	/**
	 * @param cview represents the CustomerInterface
	 * use when customer is already set. from booking frame back to customer interface. we dont need to call the Customer Model again.
	 */
	public CustomerController(CustomerInterface cview)
	{
		cView = cview;
		initView();
		initController();
	}

	/**
	 * auto filled up in customer interface frame
	 */
	@SuppressWarnings("static-access")
	public void initView()
	{
		cView.label.setText(customer.geteMail());
	}
	
	/**
	 *Controller over 3 buttons in customer interface 
	 */
	public void initController()
	{
		cView.btnBook().addActionListener(e->Book());
		cView.btnLogOut().addActionListener(e->Logout());
		cView.btnDelete().addActionListener(e->Delete(CustomerInterface.table));
	}
	
	/**
	 * @param table
	 * delete the selected rows if customer wants to cancel his appointments
	 */
	private void Delete(JTable table) {
		DefaultTableModel tb1Model = (DefaultTableModel)table.getModel();
		
		ConnectDB.deleteSelectedRowsFromTable(tb1Model);
		TableUlti.RemoveAllRows(tb1Model);
		
			ConnectDB.displayAppointmentsFromCustomer( tb1Model);
	}

	/**
	 * log out!<br>
	 * we set customer email and password to null.<br>
	 * we remove him from online users.
	 * 
	 */
	private void Logout() {
		ConnectDB.removeFromOnlineUsers(Customer.geteMail());
		new Customer(null, null);
		Login frame = new Login();
		frame.setVisible(true);
		cView.dispose();
	}

	private void Book() {
		BookingInterface frame = new BookingInterface();
		frame.setVisible(true);
		cView.dispose();
	}

	/**
	 * set input Email into model Customer to use in other functions
	 */
	public void setCustomerEmail()
	{
		customer.setMail(Login.getEmail());
	}
	

	
}
