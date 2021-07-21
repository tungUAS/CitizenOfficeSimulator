package View;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import ConnectDB.ConnectDB;
import Controller.CreateWorkerController;
import Model.Admin;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

/**
 * @author Trinh Thanh Tung 1320718<br>
 * create admin view
 */
@SuppressWarnings("serial")
public class AdminInterface extends JFrame  {
	private JButton btnCustomerDisplay;
	private JButton btnWorkerDisplay;
	private JButton btnListOfAppointments;
	public static JTable table ;
	public static JScrollPane scrollPane ;
	
	/**
	 * Create the frame.
	 */
	public AdminInterface() {
		JPanel contentPane;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 823, 603);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnCreateWorker = new JButton("Create Worker");
		btnCreateWorker.setVerticalAlignment(SwingConstants.TOP);
		btnCreateWorker.setBounds(566, 219, 153, 76);
		
		btnListOfAppointments = new JButton("List Of Appointments");
		btnListOfAppointments.setVerticalAlignment(SwingConstants.TOP);
		btnListOfAppointments.setBounds(355, 219, 168, 76);
		
		btnWorkerDisplay = new JButton("Worker Display");
		btnWorkerDisplay.setVerticalAlignment(SwingConstants.TOP);
		btnWorkerDisplay.setBounds(355, 124, 168, 84);
		
		btnCustomerDisplay = new JButton("Customer Display");	
		btnCustomerDisplay.setBounds(566, 124, 153, 84);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(299, 318, 470, 179);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 34, 807, 56);
		
		panel.setBackground(Color.DARK_GRAY);
		
		JLabel labelAdmin = new JLabel("Admin Portal");
		labelAdmin.setForeground(Color.WHITE);
		
		labelAdmin.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		
		JLabel label = new JLabel("");
		label.setBounds(0, 86, 294, 478);
		
		label.setBackground(Color.DARK_GRAY);
		
		ImageIcon imageLaptop = new ImageIcon("admin.jpg");
		label.setIcon(imageLaptop);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setBounds(0, 86, 289, 478);
		

		JButton btnBack = new JButton("Back");
		btnBack.setBounds(681, 503, 89, 23);
		contentPane.add(btnCreateWorker);
		contentPane.add(btnListOfAppointments);
		contentPane.add(btnWorkerDisplay);
		contentPane.add(btnCustomerDisplay);
		contentPane.add(scrollPane);
		contentPane.add(btnBack);
		contentPane.add(panel);
		panel.add(labelAdmin);
		contentPane.add(label);
		contentPane.add(panel_1);
		
		
		
		
		
		ImageIcon icon = new ImageIcon("create.png");
		Image scaledImg = icon.getImage().getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);  
		icon = new ImageIcon(scaledImg);
		btnCreateWorker.setBackground(Color.WHITE);
		btnCreateWorker.setHorizontalTextPosition(JButton.CENTER);
		btnCreateWorker.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnCreateWorker.setIcon(icon);
		btnCreateWorker.setOpaque(false);
		btnCreateWorker.setFocusPainted(false);
		btnCreateWorker.setBorderPainted(false);
		btnCreateWorker.setContentAreaFilled(false);
		btnCreateWorker.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		
		
		ImageIcon icon2 = new ImageIcon("appointments.jpg");
		Image scaledImg2 = icon2.getImage().getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);  
		icon2 = new ImageIcon(scaledImg2);
		btnListOfAppointments.setBackground(Color.WHITE);
		btnListOfAppointments.setHorizontalTextPosition(JButton.CENTER);
		btnListOfAppointments.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnListOfAppointments.setIcon(icon2);
		btnListOfAppointments.setOpaque(false);
		btnListOfAppointments.setFocusPainted(false);
		btnListOfAppointments.setBorderPainted(false);
		btnListOfAppointments.setContentAreaFilled(false);
		btnListOfAppointments.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		
		
		ImageIcon icon3 = new ImageIcon("worker.png");
		Image scaledImg3 = icon3.getImage().getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);  
		icon3 = new ImageIcon(scaledImg3);
		btnWorkerDisplay.setBackground(Color.WHITE);
		btnWorkerDisplay.setHorizontalTextPosition(JButton.CENTER);
		btnWorkerDisplay.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnWorkerDisplay.setIcon(icon3);
		btnWorkerDisplay.setOpaque(false);
		btnWorkerDisplay.setFocusPainted(false);
		btnWorkerDisplay.setBorderPainted(false);
		btnWorkerDisplay.setContentAreaFilled(false);
		btnWorkerDisplay.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		
		
		
		ImageIcon icon4 = new ImageIcon("customer.png");
		Image scaledImg4 = icon4.getImage().getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);  
		icon4 = new ImageIcon(scaledImg4);
		btnCustomerDisplay.setBackground(Color.WHITE);
		btnCustomerDisplay.setHorizontalTextPosition(JButton.CENTER);
		btnCustomerDisplay.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnCustomerDisplay.setIcon(icon4);
		btnCustomerDisplay.setOpaque(false);
		btnCustomerDisplay.setFocusPainted(false);
		btnCustomerDisplay.setBorderPainted(false);
		btnCustomerDisplay.setContentAreaFilled(false);
		btnCustomerDisplay.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		
		
		table = new JTable();
		
		/*-------------------------close frame or jump to other frame-----------------------------*/

		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					ConnectDB.removeFromOnlineUsers(Admin.geteMail());
					Login frame = new Login();
					frame.setVisible(true);
					dispose();
			}
		});
		
		
		btnCreateWorker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateWorkerInterface frame = new CreateWorkerInterface();
				new CreateWorkerController(frame);
				frame.setVisible(true);
				dispose();
			}
		});
	
	}
	
	public JButton btnListOfAppointments()
	{
		return btnListOfAppointments;
	}
	
	public JButton btnWorkerDisplay()
	{
		return btnWorkerDisplay;
	}
	
	public JButton btnCustomerDisplay()
	{
		return btnCustomerDisplay;
	}
}
