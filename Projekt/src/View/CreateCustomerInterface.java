package View;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;

/**
 * @author Thanh Tung Trinh 1320718
 * tung.tt133@gmail.com<br>
 * create customer registration view. extends from class create person
 */
@SuppressWarnings("serial")
public class CreateCustomerInterface extends CreatePersonInterface {
	
	private JButton btnCreate;
	private JButton btnBack ;
	
	/**
	 * Create the frame.
	 */
	public CreateCustomerInterface() {
		super();
		
		JLabel lblCustomer = new JLabel("Customer Registration");
		lblCustomer.setForeground(new Color(128, 0, 128));
		lblCustomer.setFont(new Font("Sitka Small", Font.BOLD, 20));
		lblCustomer.setBounds(55, 11, 257, 39);
		
		btnCreate = new JButton("create");
		btnCreate.setBounds(249, 205, 132, 45);
		
		btnBack = new JButton("back");
		btnBack.setBounds(48, 314, 89, 23);
		
		
		/*--------------ContentPane-----------------*/
		
		getContentPane().add(lblCustomer);
		getContentPane().add(btnBack);
		getContentPane().add(btnCreate);
	}
	
	public JButton btnBack()
	{
		return btnBack;
	}
	
	public JButton btnCreate()
	{
		return btnCreate;
	}
	

}
