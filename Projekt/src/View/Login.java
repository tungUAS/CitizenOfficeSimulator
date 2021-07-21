package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import ConnectDB.ConnectDB;
import Controller.AdminController;
import Controller.CustomerController;
import Controller.CustomerRegistrationController;
import Controller.WorkerController;
import Model.Customer;
import Model.FulltimeWorker;
import Model.PartTimeWorker;
import Model.Worker;
import Model.Admin;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Color;

/**
 * @author Thanh Tung Trinh<br>
 *Login view
 */
@SuppressWarnings("serial")
public class Login extends JFrame implements ActionListener {

		 
	private JPanel contentPane;
	public static JTextField emailInput;
	private JLabel message;
	private JButton btnLogIn;
	@SuppressWarnings("rawtypes")
	private static JComboBox selectPerson;
	public static String customerLogin = "select cpassword from customer where cmail = ? ";
	public static String workerLogin = "select wpassword from worker where wmail = ? ";
	private JCheckBox showPassword;
	public static JPasswordField passwordInput;
	private JButton btnCustomerRegistration;
	public static String cMail = null;
	public static String wMail = null;
	private JLabel welcomeLabel;
	private JLabel label;
	private JLabel lblStopWaitingKeep;
	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Login() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 655, 448);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		emailInput = new JTextField();
		emailInput.setBounds(432, 92, 192, 20);
		emailInput.setText("");
		emailInput.setColumns(10);
		
		String[] messString = {"customer","worker","admin"};
		selectPerson = new JComboBox(messString);
		selectPerson.setBounds(432, 193, 96, 22);
			
		btnLogIn = new JButton("Log In");
		btnLogIn.setForeground(Color.WHITE);
		btnLogIn.setBounds(432, 254, 89, 23);
		btnLogIn.setBackground(Color.RED);
		
		message = new JLabel("");
		message.setBounds(432, 226, 111, 14);

		showPassword = new JCheckBox("show password");
		showPassword.setBounds(432, 163, 121, 23);
		
		passwordInput = new JPasswordField();
		passwordInput.setBounds(432, 136, 192, 20);
		passwordInput.setEditable(true);
		
		btnCustomerRegistration = new JButton("Customer Registration");
		btnCustomerRegistration.setBounds(432, 378, 192, 20);
		
		welcomeLabel = new JLabel("Schwind Citizen Office");
		welcomeLabel.setBounds(439, 42, 210, 25);
		welcomeLabel.setForeground(Color.BLUE);
		welcomeLabel.setFont(new Font("Bell MT", Font.BOLD, 15));
		
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(0, 254, 411, 191);
		
		
		lblStopWaitingKeep = new JLabel("Stop waiting, keep booking ...");
		lblStopWaitingKeep.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
		lblStopWaitingKeep.setForeground(Color.WHITE);
		
		
		JLabel lblEmail = new JLabel("EMAIL");
		lblEmail.setBounds(432, 78, 96, 14);
		
		
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setBounds(432, 123, 96, 14);
		
		
		ImageIcon imageLaptop = new ImageIcon("office.jpg");
		label = new JLabel("");
		label.setBounds(0, 0, 411, 261);
		
		/*------------------------------------------*/
		contentPane.setLayout(null);
		
		contentPane.add(btnCustomerRegistration);
		contentPane.add(message);
		contentPane.add(passwordInput);
		contentPane.add(btnLogIn);
		contentPane.add(selectPerson);
		contentPane.add(showPassword);
		contentPane.add(emailInput);
		contentPane.add(label);
		label.setIcon(imageLaptop);
		contentPane.add(welcomeLabel);
		contentPane.add(panel);
		panel.add(lblStopWaitingKeep);
		contentPane.add(lblEmail);
		contentPane.add(lblPassword);
		
		
		btnCustomerRegistration.addActionListener(this);
		showPassword.addActionListener(this);
		btnLogIn.addActionListener(this);
		
	}
	
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource().equals(btnLogIn))
		{
			//whenever the application starts, it will check the date and delete from yesterday all apointments.
				ConnectDB.deleteOldAppointments();
				//check combo box selected item
				
			      if("admin".equalsIgnoreCase(String.valueOf(selectPerson.getSelectedItem())))
					{
			    	  		//check if input values are true
							if( getEmail().equalsIgnoreCase(Admin.geteMail()) && getPW().equalsIgnoreCase(Admin.getPassword()) )
							{
								//check if he is already logged in
								if(ConnectDB.checkOnlineUser(getEmail()) == 0)
								{
									// goto admin interface
									AdminInterface frame = new AdminInterface();
									new AdminController(frame);
									frame.setVisible(true);
									ConnectDB.addToOnlineUsers(Admin.geteMail());
									dispose();
								}
								else
								{
									message.setText("Logout First!");
								}
							}
							else
							{
								message.setText("Invalid Input");
							}
					}//check combo box selected item
					else if("customer".equalsIgnoreCase(String.valueOf(selectPerson.getSelectedItem()))) {
							//check if input values are true
							if(ConnectDB.checkLogin(customerLogin, getEmail(), getPW()))
							{
								//check if he is already logged in
								if( ConnectDB.checkOnlineUser(getEmail()) == 0 )
								{
									//goto Customer Interface
									Customer c = new Customer(Login.getEmail(), Login.getPW());
									CustomerInterface frame = new CustomerInterface();
									new CustomerController(c, frame);
									frame.setVisible(true);
									ConnectDB.addToOnlineUsers(Customer.geteMail());
									dispose();
								}
								else
								{
									message.setText("Logout First!");
								}
								
							}
							else
							{
								message.setText("Invalid Input");
							}
	
				   	}
					else if("worker".equalsIgnoreCase(String.valueOf(selectPerson.getSelectedItem()))) {
						//check if input values are true
							if(ConnectDB.checkLogin(workerLogin,getEmail(), getPW()))
							{
								//check if he is online 
								if( ConnectDB.checkOnlineUser(getEmail()) == 0 ) {
									//check if he is fulltime worker
									if(ConnectDB.fullTime() == 1)
									{
									//goto Worker Interface
										
										Worker w = new FulltimeWorker(Login.getEmail(), ConnectDB.getWorkingHours());
										WorkerInterface frame = new WorkerInterface();
										new WorkerController(w, frame);
										frame.setVisible(true);	
										ConnectDB.addToOnlineUsers(Worker.geteMail());
										dispose();
									}
									// part time worker
									else
									{
										Worker w = new PartTimeWorker(Login.getEmail(), ConnectDB.getWorkingHours());
										WorkerInterface frame = new WorkerInterface();
										new WorkerController(w, frame);
										frame.setVisible(true);	
										ConnectDB.addToOnlineUsers(Worker.geteMail());
										dispose();
									}
								}
								else
								{
									message.setText("Logout First!");
								}
							}
							else
							{
								message.setText("Invalid Input");
							}
					
					}
		}
		else if (ae.getSource().equals(showPassword))
		{
			//set password field to be hidden
			if(showPassword.isSelected())
				passwordInput.setEchoChar((char)0);
			else
				passwordInput.setEchoChar('*');
		} 
		else if(ae.getSource().equals(btnCustomerRegistration))
		{
			
	    	  	CreateCustomerInterface frame = new CreateCustomerInterface();
	    	  	new CustomerRegistrationController(frame);
				frame.setVisible(true);
				this.dispose();
		}
		
	  
	}
	
	public static String getEmail()
	{
		return emailInput.getText();
	}
	
	public static String getPW()
	{
		return String.copyValueOf(passwordInput.getPassword());
	}
	
}
