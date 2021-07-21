package View;

import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ConnectDB.ConnectDB;
import Controller.CustomerController;
import Model.Customer;
import Ulti.DateUlti;
import Ulti.TableUlti;
import Ulti.IntegerArray;

import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.UIManager;

/**
 * @author Thanh Tung Trinh<br>
 * create booking view. booking view uses many database handlers from ConnectDB !
 */

@SuppressWarnings("serial")
public class BookingInterface extends JFrame{

	
	private JTextField dateInput;
	private int idWorker = 0;
	private int idDate = 0;
	private int[] arr= {0,0,0,0,0};
	ArrayList<String> slots = new ArrayList<String>();
	private JLabel labelMess;
	private int radioBoxCounter = 0;
	private int checkBoxCounter = 0;
	private JLabel announcementLabel;
	
	
	/**
	 * Refresh the frame after having appointments booked
	 */
	private void RefreshFrame(DefaultTableModel tb1Model,JRadioButton radio1,JRadioButton radio2,JRadioButton radio3,JRadioButton radio4,JRadioButton radio5)
	{
		announcementLabel.setText("");
		dateInput.setText("");
		announcementLabel.setText("");
		
		TableUlti.RemoveAllRows(tb1Model);
		JRadioButton[] buttonsarr = {radio1,radio2,radio3,radio4,radio5};

		for(int i=0;i<buttonsarr.length;i++)
		{
			@SuppressWarnings("deprecation")
			final Integer innerMi = new Integer(i);
			if(buttonsarr[innerMi].isSelected()) {
						buttonsarr[innerMi].setSelected(false);	
				}
		}
		slots.removeAll(slots);
		radioBoxCounter = 0;
		checkBoxCounter = 0;
		
	}
	
	
	/** Check if at least one radio box is selected
	 * @param e is an event 
	 * @param enterButton will be enabled if at least one check box is selected
	 * @param txtpnWelcome
	 */
	private void check(ItemEvent e, Component enterButton, JLabel txtpnWelcome) {
		if(e.getStateChange() == ItemEvent.SELECTED) {
			//time is there for counting appointments
			radioBoxCounter += 1;
			enterButton.setEnabled(true);
			txtpnWelcome.setText("you need "+ Integer.toString(radioBoxCounter) + " hours."); 
			
			
		}else {
			radioBoxCounter -= 1;
				
			if(radioBoxCounter == 0)
			{
				
				txtpnWelcome.setText("you need to enter at least one checkbox");
				enterButton.setEnabled(false);
			}
			else
			{
				
				txtpnWelcome.setText("you need "+ Integer.toString(radioBoxCounter) + " Appointments");
			}
			
		}
	}
	
	
	/**Go through all 5 buttons,Check if at least one radio box is selected
	 * @param buttonsarr array of all 5 buttons
	 * @param viewAppBtn will be enabled if at least one check box is selected
	 */
	private void checkTimeCounter(JRadioButton[] buttonsarr,JButton viewAppBtn)
	{
		for(int i=0;i<buttonsarr.length;i++)
		{
			buttonsarr[i].addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					//check how many appointments user needs
					 check( e,  viewAppBtn, announcementLabel);	
				}
			});
		}
	}
	
	
	 /**
	 * @param a is our integer array from the worker abteilung abilities
	 * @return a String for our query
	 */
	public static String abteilungquery(int[] a){
	        StringBuilder query = new StringBuilder();

	        if(a[0]==1){
	            query.append(" arbeit").append(" = 1 and ");
	        }
	        if(a[1]==1){
	            query.append(" pass").append(" = 1 and ");
	        }

	        if(a[2]==1){
	            query.append(" berufsausbildung").append(" = 1 and");
	        }

	        if(a[3]==1){
	            query.append(" bauen").append(" = 1 and");
	        }

	        if(a[4]==1){
	            query.append(" fahrzeug").append(" = 1 and");
	        }

	        int lastAnd = query.lastIndexOf("and");
	        if(lastAnd != -1){
	            return query.substring(0,lastAnd);
	        }


	        return query.toString();
	    }
    

	public BookingInterface() {
		initialize();
	}

	/**
	 * Create the frame.
	 */
	public void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel contentPane;
		setBounds(100, 100, 758, 540);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JRadioButton radio1 = new JRadioButton("Arbeit und Beruf");
		radio1.setBackground(Color.ORANGE);
		radio1.setBounds(38, 32, 165, 23);
		
		JRadioButton radio2 = new JRadioButton("Pass und Ausweis");
		radio2.setBounds(38, 58, 165, 23);
		radio2.setBackground(SystemColor.info);
		
		JRadioButton radio3 = new JRadioButton("Berufsausbildung");
		radio3.setBounds(38, 84, 165, 23);
		radio3.setBackground(UIManager.getColor("ToolBar.light"));
		
		JRadioButton radio4 = new JRadioButton("Bauen");
		radio4.setBounds(38, 110, 165, 23);
		radio4.setBackground(SystemColor.inactiveCaption);
		
		JRadioButton radio5 = new JRadioButton("Fahrzeug");
		radio5.setBounds(38, 136, 165, 23);
		radio5.setBackground(Color.PINK);
		
		dateInput = new JTextField();
		dateInput.setBounds(244, 59, 172, 20);
		dateInput.setColumns(10);
		
		JButton viewAppBtn = new JButton("View Appointments");
		
		viewAppBtn.setEnabled(false);
		viewAppBtn.setBounds(244, 84, 172, 23);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(38, 222, 402, 229);
		
		JTable table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Date", "TimeSlot",  "Check"
				}
			) {
				@SuppressWarnings("rawtypes")
				Class[] columnTypes = new Class[] {
					Object.class, Object.class, Boolean.class
				};
				@SuppressWarnings({ "unchecked", "rawtypes" })
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			});
		
		JButton selectBtn = new JButton("Select");
		selectBtn.setEnabled(false);
		selectBtn.setBounds(351, 462, 89, 23);
		
		JLabel label = new JLabel("");
		label.setFont(new Font("Segoe Print", Font.BOLD, 14));
		label.setBounds(38, 11, 250, 14);
	
		JLabel lblPleaseEnterDate = new JLabel("Please enter date e.g 2021-04-01");
		lblPleaseEnterDate.setBounds(244, 36, 250, 14);
	
		ImageIcon icon = new ImageIcon("booking.png");
		Image scaledImg = icon.getImage().getScaledInstance(150, 150,  java.awt.Image.SCALE_SMOOTH);  
		icon = new ImageIcon(scaledImg);
		
		labelMess = new JLabel("");
		labelMess.setFont(new Font("Tahoma", Font.PLAIN, 19));
		labelMess.setForeground(Color.RED);
		labelMess.setBounds(158, 191, 282, 20);
	
		JButton btnBack = new JButton("back");	
		btnBack.setBounds(263, 462, 89, 23);
		
		label.setText(Customer.geteMail());
		
		JLabel workerLabel = new JLabel("");
		workerLabel.setBounds(38, 188, 89, 23);
		
		announcementLabel = new JLabel("Please enter at least one radio box");
		announcementLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		announcementLabel.setBounds(38, 166, 250, 14);
		
		JLabel imageLabel = new JLabel("");
		imageLabel.setBounds(540, 140, 157, 213);
		imageLabel.setIcon(icon);		
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(442, 0, 300, 501);
		
		JLabel lblBookingPortal = new JLabel("BOOKING PORTAL");
		lblBookingPortal.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblBookingPortal.setForeground(Color.WHITE);
		
		
		contentPane.add(radio1);
		contentPane.add(radio1);
		contentPane.add(radio2);
		contentPane.add(radio3);
		contentPane.add(radio4);
		contentPane.add(radio5);
		contentPane.add(dateInput);
		contentPane.add(viewAppBtn);
		contentPane.add(scrollPane);
		contentPane.add(selectBtn);
		contentPane.add(label);
		contentPane.add(lblPleaseEnterDate);
		contentPane.add(labelMess);
		contentPane.add(btnBack);
		contentPane.add(workerLabel);
		contentPane.add(announcementLabel);
		contentPane.add(imageLabel);
		contentPane.add(panel);
		panel.add(lblBookingPortal);

		DefaultTableModel tb1Model = (DefaultTableModel)table.getModel();
		JRadioButton[] buttonsarr = {radio1,radio2,radio3,radio4,radio5};
		
		/*-------------------------------------------------------------*/
		
		//check the count of radio buttons
		checkTimeCounter(buttonsarr, viewAppBtn);
		//return an array of integer numbers for query 
		IntegerArray.returnIntArr(buttonsarr, arr);
		//idWorker display
		label.setText(Customer.geteMail());
		
		
		// action listener
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerInterface frame = new CustomerInterface();
				new CustomerController(null,frame);
				frame.setVisible(true);
				dispose();
			}
		});
		
		viewAppBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					//check if the input date is in the right format
					if(DateUlti.dateValid(getDateInput()))
					{
						//set Button to be enabled
						selectBtn.setEnabled(true);
						//convert
						idDate = DateUlti.ConvertDateToInt(getDateInput());
						//Check if the input date is on weekend or not
						if(ConnectDB.checkWeekend(idDate))
						{
							// check if there is an available worker
							if(ConnectDB.lookForAvailableWorker(idDate, radioBoxCounter, arr) != 0)
							{
								//return the idWorker if found
								idWorker = ConnectDB.lookForAvailableWorker(idDate, radioBoxCounter, arr);
								//display the idWorker
								String index = String.valueOf(idWorker);
								workerLabel.setText("worker " + index);
									
								//display the appointments table
								ConnectDB.displayTableAvailableAppointments(tb1Model, idDate, idWorker);
								 
								selectBtn.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									//count of check boxes and time slot array have to be refreshed every time we enter the button
									slots.removeAll(slots);							
									checkBoxCounter=0;
									//count the selected checkboxes from the appointments table
									for(int i=0;i<tb1Model.getRowCount();i++)
									{
										if(String.valueOf(tb1Model.getValueAt(i,2)).toString()=="true")
										{
											checkBoxCounter+=1;
										}
									}
									
										//check if the number of checkboxes on the right equal to the number of checkboxes on the left
										if(checkBoxCounter != radioBoxCounter )
										{
											labelMess.setText("Please enter "+ radioBoxCounter + " checkboxes");
										}
										else
										{
											labelMess.setText(Login.cMail);
											int j = 0;
											// from the selected checkbox. we put all the timeslot in an array
											for(int i=0;i<tb1Model.getRowCount();i++)
											{
												//equals
												if(String.valueOf(tb1Model.getValueAt(i,2)).toString()=="true")
												{
													slots.add(j,table.getModel().getValueAt(i, 1).toString());
													j++;
												}
											}
												// check if at that moment, out selected appointments are still available
												
												if(ConnectDB.checkAvailityOfSelectedAppointments(slots, idDate,idWorker))
												{
													//store in database
													ConnectDB.insertIntoCalendar(slots,idDate,idWorker,radioBoxCounter);
													 															
													labelMess.setText("Successful!");
													//refresh the frame
													RefreshFrame(tb1Model,radio1,radio2,radio3,radio4,radio5);	
												}
												else
												{
													labelMess.setText("Sorry. Your Selected Appointments have been booked recently!");
													ConnectDB.displayTableAvailableAppointments(tb1Model, idDate, idWorker);
												}
	
										}
									
								}
							});
														
						}
						else
						{
						//Remove rows one by one from the end of the table
						//remove Row function
						TableUlti.RemoveAllRows(tb1Model);
						JOptionPane.showMessageDialog(null,"Fully Booked!");
						workerLabel.setText("");
						}
					}
					else
					{
					//Remove rows one by one from the end of the table
					//remove Row function
						TableUlti.RemoveAllRows(tb1Model);
					JOptionPane.showMessageDialog(null,"Please enter a working day");
					workerLabel.setText("");
					}
				}
				else {
					//Remove rows one by one from the end of the table
					//remove Row function
					TableUlti.RemoveAllRows(tb1Model);
				JOptionPane.showMessageDialog(null,"Please enter the right date format");
				workerLabel.setText("");
				} 
			}
			catch (HeadlessException | ParseException e1) {
		// TODO Auto-generated catch block
			e1.printStackTrace();
		}
				
	  }			
		
			
	});
		
	//end of function
	}

	/*--------------------------------------------------*/
	
	private String getDateInput()
	{
		return dateInput.getText();
	}
}
