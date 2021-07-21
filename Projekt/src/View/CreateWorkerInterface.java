package View;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JTextField;
import Ulti.DateUlti;
import Ulti.IntegerArray;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import javax.swing.JRadioButton;
import java.awt.Font;

/**
 * @author Trinh Thanh Tung<br>
 * admin view to create a new worker
 */
@SuppressWarnings("serial")
public class CreateWorkerInterface extends CreatePersonInterface  {
	
	private JTextField txtWorker;
	private int boxcounter = 0;
	private JTextField startText;
	private JTextField endText;
	private int[] arr= {0,0,0,0,0};
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox;
	private JLabel messCB;
	private JLabel messDate;
	private JLabel messDate2;
	private Date date1;
	private Date date2;
	private String sdate4 = "2022-01-01";
	private Date date4;
	private JButton btnCreateWorker;
	private JButton btnBack;
	private SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd"); 
	private JRadioButton rdbtn1;
	private JRadioButton rdbtn2;
	private JRadioButton rdbtn3;
	private JRadioButton rdbtn4;
	private JRadioButton rdbtn5;
	private String NAME_REGEX = "[a-zA-Z][a-zA-Z ]*";
	private JLabel accouncementLabel;
	
	public int[] arr()
	{
		return arr;
	}
	public JRadioButton rdbtn1()
	{
		return rdbtn1;
	}
	public JRadioButton rdbtn2()
	{
		return rdbtn2;
	}
	public JRadioButton rdbtn3()
	{
		return rdbtn3;
	}
	public JRadioButton rdbtn4()
	{
		return rdbtn4;
	}
	public JRadioButton rdbtn5()
	{
		return rdbtn5;
	}
	
	@SuppressWarnings("rawtypes")
	public JComboBox comboBox()
	{
		return comboBox;
	}
	public JButton btnCreateWorker()
	{
		return btnCreateWorker;
	}
	
	public JButton btnBack()
	{
		return btnBack;
	}
	
	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public CreateWorkerInterface() {
		super();
	
		txtWorker = new JTextField();
		txtWorker.setForeground(new Color(128, 0, 128));
		txtWorker.setFont(new Font("Sitka Subheading", Font.BOLD, 20));
		txtWorker.setEditable(false);
		txtWorker.setText("Worker Registration");
		txtWorker.setBounds(48, 0, 305, 36);
		
		txtWorker.setColumns(10);
		
		btnCreateWorker = new JButton("Create Worker");
		
		btnCreateWorker.setBounds(229, 426, 143, 45);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"vollzeit", "teilzeit(vormittag)", "teilzeit(nachmittag)"}));
		comboBox.setBounds(228, 234, 143, 22);
		
		JLabel lblStart = new JLabel("Start");
		lblStart.setBounds(228, 267, 48, 14);
		
		startText = new JTextField();
		startText.setBounds(276, 264, 96, 20);
		
		startText.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("end");
		lblNewLabel.setBounds(228, 311, 48, 14);
		
		endText = new JTextField();
		endText.setBounds(276, 308, 96, 20);
		
		endText.setColumns(10);
		
		messCB = new JLabel("enter at least 1 checkbox");
		messCB.setBounds(58, 386, 200, 14);
		
		messDate = new JLabel("enter between 2021-01-01 and 2022-01-01");
		messDate.setBounds(228, 285, 531, 14);
		
		messDate2 = new JLabel("enter between 2021-01-01 and 2022-01-01");
		messDate2.setBounds(228, 333, 531, 14);
		
		rdbtn1 = new JRadioButton("Arbeit und Beruf");
		rdbtn1.setBounds(48, 255, 174, 23);
		
		rdbtn2 = new JRadioButton("Pass und Ausweis");
		rdbtn2.setBounds(48, 281, 174, 23);
		
		rdbtn3 = new JRadioButton("Bauen");
		rdbtn3.setBounds(48, 307, 109, 23);
		
		rdbtn4 = new JRadioButton("Berufsausbildng");
		rdbtn4.setBounds(48, 333, 174, 23);
			
		rdbtn5 = new JRadioButton("Fahrzeug und Verkehr");
		rdbtn5.setBounds(48, 356, 174, 23);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(48, 426, 150, 45);
		

		JLabel lblAbilities = new JLabel("Abilities");
		lblAbilities.setBounds(48, 234, 143, 14);
		
		
		getContentPane().add(rdbtn5);
		getContentPane().add(rdbtn4);
		getContentPane().add(rdbtn3);
		getContentPane().add(rdbtn2);
		getContentPane().add(rdbtn1);
		getContentPane().add(messDate2);
		getContentPane().add(messDate);
		getContentPane().add(messCB);
		getContentPane().add(endText);
		getContentPane().add(lblNewLabel);
		getContentPane().add(startText);
		getContentPane().add(comboBox);
		getContentPane().add(lblStart);
		getContentPane().add(btnCreateWorker);
		getContentPane().add(txtWorker);
		getContentPane().add(btnBack);
		getContentPane().add(lblAbilities);
		
		accouncementLabel = new JLabel("");
		accouncementLabel.setBounds(48, 194, 200, 14);
		getContentPane().add(accouncementLabel);
		
		/* ------------------------------------ */
		
		JRadioButton[] checkboxsarr = {rdbtn1,rdbtn2,rdbtn3,rdbtn4,rdbtn5};	
		boxcounter = 0;
		checkBoxesvalid();
		IntegerArray.returnIntArr(checkboxsarr, arr);
		//returnIntArr();
		/*------------------action listener button-------------------*/
		
	}
	
	
	public String getStartWorkingDay()
	{
		return startText.getText();
	}
	public String getEndWorkingDay()
	{
		return endText.getText();
	}
	
	
	/*----------------check input functions---------------------------*/
	
	
	/**
	 * Check if at least one radio box is selected
	 */
	private void check(ItemEvent e) {
		if(e.getStateChange() == ItemEvent.SELECTED) {
			//time is there for counting appointments
			boxcounter += 1;
			
		}else {
			boxcounter -= 1;
		}
	}
	
	public void checkBoxesvalid()
	{ 
		
		JRadioButton[] checkboxsarr = {rdbtn1,rdbtn2,rdbtn3,rdbtn4,rdbtn5};
		for(int i=0;i<checkboxsarr.length;i++)
		{
			checkboxsarr[i].addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					check(e);
					}
				});
			} 
		
	}
	
	/*-----------------------UI-------------------------------*/
	/**
	 * display if the value of input is true or not
	 */
	public void counter()
	{
		if(boxcounter > 0)
		{
			messCB.setForeground(Color.GREEN);
			messCB.setText("True");
		}
		else
		{
			messCB.setForeground(Color.RED);
			messCB.setText("Enter at least 1 checkbox");
		}
	}

	/**
	 * @throws ParseException for date parsing
	 * display if the value of input is true or not
	 */
	public void startdateCheck() throws ParseException 
	{
		if(startText.getText().equalsIgnoreCase("") || startText.getText().matches(NAME_REGEX))
		{
			messDate.setForeground(Color.RED);
			messDate.setText("format invalid! example 2021-01-01");
		}
		else
		{
			
			date1 = formatter.parse(startText.getText());
			date4 = formatter.parse(sdate4);
				
			
			if(date1.compareTo(DateUlti.today()) > 0 && date1.compareTo(date4) < 0)
			{
				messDate.setForeground(Color.GREEN);
				messDate.setText("True");
			}
			else if( date1.compareTo(DateUlti.today()) <= 0 || date1.compareTo(date4) > 0 )
			{
				messDate.setForeground(Color.RED);
				messDate.setText("Please enter a date between "+ LocalDate.now() +" and 2022-01-01");
			}
		}
		
	}
	/**
	 * @throws ParseException for date parsing
	 * display if the value of input is true or not
	 */
	public void enddateCheck() throws ParseException 
	{

		if(endText.getText().equalsIgnoreCase("") || endText.getText().matches(NAME_REGEX))
		{
			messDate2.setForeground(Color.RED);
			messDate2.setText("format invalid! example 2021-01-01");
		}
		else
		{
				
				date1 = formatter.parse(getStartWorkingDay());
				date2 = formatter.parse(getEndWorkingDay());
				date4 = formatter.parse(sdate4);
				
				if( date2.compareTo(DateUlti.today()) > 0 && date2.compareTo(date4) < 0 && date2.compareTo(date1) > 0)
				{
					messDate2.setForeground(Color.GREEN);
					messDate2.setText("True");
				}
				else if( date2.compareTo(DateUlti.today()) <= 0 || date2.compareTo(date4) > 0)
				{
					messDate2.setForeground(Color.RED);
					messDate2.setText("Please enter a date between "+LocalDate.now()+" and 2022-01-01");
				}
				else if(date2.compareTo(date1) < 0 || date2.compareTo(date1) == 0)
				{
					messDate2.setForeground(Color.RED);
					messDate2.setText("Please enter a date later than start date");
				}
		}
	}



	
}
