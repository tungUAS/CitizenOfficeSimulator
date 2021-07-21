package View;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ConnectDB.ConnectDB;
import Ulti.ValidationUlti;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Font;

/**
 * @author 
 * Trinh Thanh Tung<br>
 * create person has the fixed GUI , which can be used for worker and customer
 */
@SuppressWarnings("serial")
public class CreatePersonInterface extends JFrame {

	
	private static JTextField firstNameInput;
	private static JTextField lastNameInput;
	private static JTextField emailInput;
	private static JPasswordField passwordInput;
	private static JLabel messMail;
	private static JLabel messLN;
	private static JLabel messFN;
	private static JLabel messPW;
	
	/**
	 * Create the frame.
	 */
	public CreatePersonInterface() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel contentPane;
		
		setBounds(100, 100, 566, 604);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		firstNameInput = new JTextField();
		firstNameInput.setBounds(48, 86, 185, 20);
		firstNameInput.setColumns(10);
		
		lastNameInput = new JTextField();
		lastNameInput.setColumns(10);
		lastNameInput.setBounds(279, 86, 171, 20);
		
		emailInput = new JTextField();
		
		emailInput.setColumns(10);
		emailInput.setBounds(48, 142, 185, 20);
		
		messMail = new JLabel("example@example.example");
		messMail.setFont(new Font("Tahoma", Font.ITALIC, 11));
		messMail.setBounds(108, 117, 160, 14);
		
		
		messLN = new JLabel("only characters allowed");
		messLN.setFont(new Font("Tahoma", Font.ITALIC, 11));
		messLN.setBounds(361, 61, 197, 14);
		
		
		messFN = new JLabel("only characters allowed");
		messFN.setFont(new Font("Tahoma", Font.ITALIC, 11));
		messFN.setBounds(126, 61, 160, 14);
		
		
		messPW = new JLabel("between 8 and 15 characters");
		messPW.setFont(new Font("Tahoma", Font.ITALIC, 11));
		messPW.setBounds(361, 117, 199, 14);
		
		
		JCheckBox chckbxShowPassword = new JCheckBox("show Password");
		chckbxShowPassword.setBounds(278, 165, 140, 23);
		
		
		passwordInput = new JPasswordField();
		passwordInput.setBounds(279, 142, 171, 20);
		
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFirstName.setBounds(48, 55, 92, 20);
		
		
		JLabel lblLastname = new JLabel("Last Name");
		lblLastname.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLastname.setBounds(279, 58, 92, 20);
		
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEmail.setBounds(48, 117, 84, 14);
		
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPassword.setBounds(279, 117, 92, 14);
		contentPane.add(lblPassword);
		
		
		contentPane.add(firstNameInput);
		contentPane.add(lastNameInput);
		contentPane.add(lblEmail);
		contentPane.add(lblLastname);
		contentPane.add(lblFirstName);
		contentPane.add(passwordInput);
		contentPane.add(chckbxShowPassword);
		contentPane.add(messFN);
		contentPane.add(messPW);
		contentPane.add(messLN);
		contentPane.add(messMail);
		contentPane.add(emailInput);
		
		
		chckbxShowPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxShowPassword.isSelected()) 
					passwordInput.setEchoChar((char) 0);
				else
					passwordInput.setEchoChar('*');
			}
		});
		
		
	}
	
	
	/**
	 * @return input firstname
	 */
	public static String getFirstName()
	{
		return firstNameInput.getText();
	}
	
	/**
	 * @return input last name
	 */
	public static String getLastName()
	{
		return lastNameInput.getText();
	}
	
	/**
	 * @return input Email
	 */
	public static String getEmail()
	{
		return emailInput.getText();
	}
	
	/**
	 * @return input password
	 */
	public static String getPW()
	{
		return String.copyValueOf(passwordInput.getPassword());
	}
	
	/**
	 * display if the value of input is true or not
	 */
	public static void checkEmail()
	{
		if(ValidationUlti.EmailValid(emailInput.getText()))
		{
			messMail.setForeground(Color.GREEN);
			messMail.setText("True");
		}
		else
		{
			if(ConnectDB.repeatedEmail(emailInput.getText())!=0)
			{
				messMail.setForeground(Color.RED);
				messMail.setText("This Email has been used!");
			}
			else {
			messMail.setForeground(Color.RED);
			messMail.setText("Invalid Email");
			}
		}
	}
	/**
	 * display if the value of input is true or not
	 */
	public static void checkName()
	{
		if(ValidationUlti.nameValid(firstNameInput.getText()))
		{
			messFN.setForeground(Color.GREEN);
			messFN.setText("True");
		}
		else
		{

			messFN.setForeground(Color.RED);
			messFN.setText("Invalid Name");
		}
	}
	/**
	 * display if the value of input is true or not
	 */
	public static void checkLastName()
	{
		if(ValidationUlti.lastNameValid(lastNameInput.getText()))
		{
			messLN.setForeground(Color.GREEN);
			messLN.setText("True");
		}
		else
		{
			messLN.setForeground(Color.RED);
			messLN.setText("Invalid Name");
		}
	}
	/**
	 * display if the value of input is true or not
	 */
	public static void checkPW()
	{
		if(ValidationUlti.passwordValid(String.valueOf(passwordInput.getPassword())))
		{
			messPW.setForeground(Color.GREEN);
			messPW.setText("True");
		}
		else
		{
			messPW.setForeground(Color.RED);
			messPW.setText("Invalid Password");
		}
	}

	
	

}
