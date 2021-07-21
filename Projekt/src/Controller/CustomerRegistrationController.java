package Controller;

import javax.swing.JOptionPane;

import ConnectDB.ConnectDB;
import Model.Customer;
import Ulti.ValidationUlti;
import View.CreateCustomerInterface;
import View.Login;

/**
 * @author Trinh Thanh Tung 1320718 <br>
 * tung.tt133@gmail.com
 * Control the flow of input data after a customer having his registration done
 */
public class CustomerRegistrationController {
	
	private  Customer customer;
	private  Login loginview;
	private CreateCustomerInterface crCus;
	
	/**
	 * @param m is customer. data will be saved to customer if he has been succesfully registrated
	 * @param l for the view in log in frame
	 */
	public CustomerRegistrationController(Customer m, Login l)
	{
		customer = m;
		loginview = l;
		initView();
	}
	

	/**
	 * auto filled up in login frame
	 */
	@SuppressWarnings("static-access")
	public void initView()
	{
		loginview.emailInput.setText(customer.geteMail());
		loginview.passwordInput.setText(customer.getPassword());
	}
	
	
	/**
	 * @param Cus represents the createcustomerinterface. 
	 * this function is used for the control of data in the create customer interface GUI
	 */
	public CustomerRegistrationController(CreateCustomerInterface Cus)
	{
		crCus = Cus;
		initController();
	}
	
	/**
	 * control the 2 buttons in GUI 
	 */
	private void initController() {
		// TODO Auto-generated method stub
		crCus.btnBack().addActionListener(e->backToLoginFrame());
		crCus.btnCreate().addActionListener(e->createCustomer());
	}

	/**
	 * create a new customer
	 */
	@SuppressWarnings("static-access")
	private void createCustomer() {
		if( ValidationUlti.EmailValid(crCus.getEmail()) && ValidationUlti.nameValid(crCus.getFirstName())
				&& ValidationUlti.lastNameValid(crCus.getLastName()) && ValidationUlti.passwordValid(crCus.getPW()))
				{
						//insert into customer table in database
						ConnectDB.insertIntoTableCustomer();
						JOptionPane.showMessageDialog(null,"Succesful");
						
								
								crCus.dispose();
								Customer c = new Customer(CreateCustomerInterface.getEmail(), CreateCustomerInterface.getPW());
								Login frame = new Login();
								new CustomerRegistrationController(c,frame);
								frame.setVisible(true);
					
				}
				else
				{
					crCus.checkName();
					crCus.checkLastName();
					crCus.checkEmail();
					crCus.checkPW();
					
					JOptionPane.showMessageDialog(null,"Please check your Input");
					
				}
	}

	/**
	 * get out of GUI , back to log in frame
	 */
	private void backToLoginFrame() {
		Login frame = new Login();
		frame.setVisible(true);
		crCus.dispose();
	}

	
	
	/**
	 * set input Email into model Customer to use in other functions
	 */
	public void setCustomerEmail()
	{
		customer.setMail(CreateCustomerInterface.getEmail());
	}
	
}
