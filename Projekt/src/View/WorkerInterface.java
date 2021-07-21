package View;


import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import ConnectDB.ConnectDB;
import java.awt.Color;

/**
 * @author Trinh Thanh Tung<br>
 *Worker view
 */
@SuppressWarnings("serial")
public class WorkerInterface extends JFrame  {
 
	private JTable table;
	
	private JButton btnBack;
	public JLabel label;
	private JPanel panel;
	private JLabel lblWorkerPortal;
	private JButton btnSalary ;


	public JButton btnSalary()
	{
		return btnSalary ;
	}
	
	public JButton btnBack()
	{
		return btnBack;
	}
	
	/**
	 * Create the frame.
	 */
	public WorkerInterface() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel contentPane;
		setBounds(100, 100, 669, 525);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		label = new JLabel("");
		label.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label.setBounds(34, 106, 587, 36);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(33, 187, 588, 218);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"customer","Date", "TimeSlot"
				}
			) {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;
				@SuppressWarnings("rawtypes")
				Class[] columnTypes = new Class[] {
					Object.class,Object.class, Object.class
				};
				@SuppressWarnings({ "unchecked", "rawtypes" })
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			});
		
		panel = new JPanel();
		panel.setBackground(Color.ORANGE);
		panel.setBounds(0, 0, 653, 81);
		
		
		lblWorkerPortal = new JLabel("WORKER PORTAL");
		lblWorkerPortal.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblWorkerPortal.setForeground(Color.WHITE);
		
		
		btnBack = new JButton("back");
		btnBack.setBounds(34, 430, 89, 23);
		
		contentPane.add(btnBack);
		contentPane.add(label);
		contentPane.add(scrollPane);
		contentPane.add(panel);
		panel.add(lblWorkerPortal);
		
		btnSalary = new JButton("salary");
		btnSalary.setBounds(34, 153, 89, 23);
		contentPane.add(btnSalary);
		
		DefaultTableModel tb1Model = (DefaultTableModel)table.getModel();
		ConnectDB.displayAppointmentsFromWorker(tb1Model);
	}


}
