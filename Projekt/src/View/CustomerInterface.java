package View;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import ConnectDB.ConnectDB;
import Model.Customer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.util.ArrayList;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Color;

/**
 * @author Trinh Thanh Tung<br>
 * customer view
 */
@SuppressWarnings("serial")
public class CustomerInterface extends JFrame {

	
	public static JTable table;
	private JButton btnDelete;
	private JButton btnBook;
	ArrayList<String> slots = new ArrayList<String>();
	private JButton btnLogout;
	//used in Controller
	public JLabel label;
	
	public JButton btnBook()
	{
		return btnBook;
	}
	public JButton btnLogOut()
	{
		return btnLogout;
	}
	public JButton btnDelete()
	{
		return btnDelete;
	}
	
	
	/**
	 * Create the frame.
	 */
	public CustomerInterface() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel contentPane;
		setBounds(100, 100, 669, 525);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnBook= new JButton("Book Appointments");
		btnBook.setBounds(322, 153, 299, 23);
		
		
		label = new JLabel("");
		label.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		label.setBounds(33, 106, 331, 36);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(33, 187, 588, 218);
		

		btnDelete = new JButton("delete Selected rows");
		btnDelete.setBounds(33, 153, 292, 23);
		
		
		btnLogout = new JButton("Log Out");
		btnLogout.setBounds(33, 430, 89, 23);
		
		table = new JTable();
		
		
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"worker","Date", "TimeSlot","Check"
				}
			) {
				@SuppressWarnings("rawtypes")
				Class[] columnTypes = new Class[] {
					Object.class,Object.class, Object.class,Boolean.class
				};
				@SuppressWarnings({ "unchecked", "rawtypes" })
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			});
		
		ImageIcon icon = new ImageIcon("smile.png");
		Image scaledImg = icon.getImage().getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH);  
		icon = new ImageIcon(scaledImg);
		label.setText(Customer.geteMail());
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.ORANGE);
		panel.setBounds(0, 0, 653, 91);
		
		
		JLabel lblCustomerPortal = new JLabel("Customer Portal");
		lblCustomerPortal.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblCustomerPortal.setForeground(Color.WHITE);
		lblCustomerPortal.setBackground(Color.WHITE);
		
		
		/*-----------------------------------------------*/
		
		
		scrollPane.setViewportView(table);
		contentPane.add(scrollPane);
		contentPane.add(label);
		contentPane.add(btnDelete);
		contentPane.add(btnBook);
		contentPane.add(btnLogout);
		contentPane.add(panel);
		panel.add(lblCustomerPortal);
		
		
		DefaultTableModel tb1Model = (DefaultTableModel)table.getModel();
		ConnectDB.displayAppointmentsFromCustomer( tb1Model);
	}


	
}
