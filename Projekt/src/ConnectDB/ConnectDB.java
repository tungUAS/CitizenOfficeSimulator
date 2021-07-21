package ConnectDB;

/**
 * @author Thanh Tung Trinh 1320718
 * tung.tt133@gmail.com
 *
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import com.mysql.cj.protocol.Resultset;
import Model.Customer;
import Model.Worker;
import Ulti.DateUlti;
import Ulti.TableUlti;
import View.BookingInterface;
import View.CreateCustomerInterface;
import View.CreateWorkerInterface;
import View.Login;

/**
 * Class ConnectDB is responsible for all functions/methods that need to be connected to the database.
 */
public class ConnectDB {
	
	
	
	public static void main(String[] args) throws Exception {

	}
	
	private static ConnectDB connectDB = null;

	private static Connection conn;
	private ConnectDB(){
		
	}

	/**
	 * @return Connection 
	 */
	public static ConnectDB getInstance(){
		if(connectDB == null){
			connectDB = new ConnectDB();
		}
		return connectDB;
	}

	/**get Connection if initialized. The function is used in Login Frame only. Program has to start from there!
	 * @throws Exception for SQL
	 */
	public void init() throws Exception {
		getConnection();
	}
	
	/**
	 * mySql Workbench connection established
	 * MySql Connection : test
	 * user : "root"
	 * password : ""
	 * @return Connection to Database
	 * @throws Exception for jdbc 
	 */
	public Connection getConnection() throws Exception{
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://127.0.0.1:3306/test?useTimezone=true&serverTimezone=UTC";
			String username ="root";
			String password ="";
			
			Class.forName(driver);
			conn = DriverManager.getConnection(url,username,password);
			return conn;
		}catch(Exception e) {
			
			System.out.println("error "+e.getMessage());
		}
		
		return null;
	}
	
	
	/**
	 * @param email is email input from Login Frame
	 * We add this Email to online Users in database.
	 */
	public static void addToOnlineUsers(String email)
	{
		try {
			String query = "insert into test.online values ( ? )";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString(1,email);
		      // execute the java preparedstatement
		    preparedStmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @param email is email from logged in admin/customer/worker
	 * if he logs out, database will delete him from online users.
	 */
	public static void removeFromOnlineUsers(String email)
	{
		try {
			String query = "delete from test.online where onlineuser = ? ";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString(1,email);
		      // execute the java preparedstatement
		    preparedStmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @param email is the email input from login frame
	 * @return 1 if he/she is already logged in
	 */
	public static int checkOnlineUser(String email)
	{
		try {
			String query = "select exists(select * from online where onlineuser = ? )";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString(1,email);
		    // execute the java preparedstatement
		    ResultSet rs = preparedStmt.executeQuery();
		    rs.next();
		    return (rs.getInt(1));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	
	/** we connect to database and look for the input email. if it exists, we continue comparing if the password is true or not
	 * @param a is a String query 
	 * @param Email is the input Email from user/worker
	 * @param password is the input password from user/worker
	 * @return true if Email and Password are correctly entered and found in the database
	 */
	public static boolean checkLogin(String a,String Email,String password) 
	{
		boolean check= false;
		try {
		
			//the a string query goes to database and look for the input email and password. 
			PreparedStatement preparedStmt = conn.prepareStatement(a);
			
		      preparedStmt.setString(1,Login.getEmail());
		      // execute the java preparedstatement
		      ResultSet rs = preparedStmt.executeQuery();
		      while(rs.next()) {
				  //return true if email is found and their password matches input  
		    	  if(rs.getString(1).equalsIgnoreCase(password) && a == Login.customerLogin)
		    	  {
		    		  check = true;
		    	  }
		    	  else if(rs.getString(1).equalsIgnoreCase(password) && a == Login.workerLogin)
		    	  {
		    		check = true;
		    	  }
		      }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return check;
		
	}
	
	
	/** We check where if the input date is on weekend or not. We have already generated a table for dates in our database.
	 * Table dates contains the information if a day is weekend or not.
	 * Weekend = 1 if that day is on weekend.
	 * @param idDate is a date in integer format.
	 * @return true if the input date is not a weekend day.
	 */
	public static boolean checkWeekend(int idDate)
	{
		
		try {
			
			String sql = "select weekend from dates where idDate = ? ";
			PreparedStatement pst = conn.prepareStatement(sql);
			
			pst.setInt(1,idDate);
			ResultSet rs = pst.executeQuery();
			rs.next();
			
		    return (rs.getInt(1) == 0);  
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		
	}
	
	/** the integer array is responsible for the abilities of the worker.<br>
	 * User has a choice choosing between many categories of appointment.<br>
	 * We have 5 different kinds of Appointments.<br>
	 * Lets say the user want to have an appointment with Bauen only. then the array will be 0-0-0-1-0.<br>
	 * If user chooses all , our array will be 0-0-0-0-0. <br>
	 * With the help of our integer array, we have easily access the database through a query<br>
	 * With abteilungquery we can generate a sub string for our query.<br>
	 * Back to the example where user only takes Bauen. the substring will be "select ... from ... where Bauen = 1 "<br>
	 * our String sql will return a possible that has an ability of handling all required kinds of appointments with least appointments.<br>
	 * idWorker is the id of the Available worker if found!
	 * @param idDate is the input Date in integer format
	 * @param radioBoxCounter is the counter of radio-boxes
	 * @param arr is an array of integer  with values 0 and 1
	 * @return the idWorker if found , else 0
	 */
	public static int lookForAvailableWorker(int idDate, int radioBoxCounter, int[ ] arr)
	{
		int check = 0;
		try {
			
			String sql = "select idWorker from (select idWorker from abteilung where " 
			+ BookingInterface.abteilungquery(arr) +
			") as a1 natural join worker natural join calendar natural join dates "
			+ "where weekend = 0 and idDate = ? and available = 0 "
			+ "group by idWorker having count(available) >= ?"
			+ " order by countAPP asc limit 1";
			
			PreparedStatement pst1 = conn.prepareStatement(sql);
			pst1.setInt(1,idDate);
			pst1.setInt(2, radioBoxCounter);
			Resultset rs1 = (Resultset) pst1.executeQuery();
			
			if(((ResultSet) rs1).next())
			{
				check = ((ResultSet) rs1).getInt(1);
				
			}
			else 
				check = 0;
			
		      
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return check;
	}
	
	/**
	 * This function is used in order to recheck if the appointments are still available to that moment. <br>
	 * User has the table of appointments in front of him. He has done something and went he came back, someone took his apopintments already.<br>
	 * Before selecting those appointments, the system will check after availability and give him a new table.
	 * @param slots is the array which store the time slots 
	 * @param idDate is the input Date in integer format
	 * @param idWorker is idWorker from chosen worker
	 * @return true if all selected appointments are still available 
	 */
	@SuppressWarnings("rawtypes")
	public static boolean checkAvailityOfSelectedAppointments(ArrayList slots,int idDate,int idWorker)
	{
		try {
			
			for(int i=0;i<slots.size();i++)
			{

					  String query = "select available from calendar where idDate = ? and timeslot = ? and idWorker = ? ";
				      PreparedStatement preparedStmt = conn.prepareStatement(query);
				      preparedStmt.setInt(1,idDate);
				      preparedStmt.setString(2, (String) slots.get(i));
				      preparedStmt.setInt(3, idWorker);
				      // execute the java preparedstatement
				      ResultSet rs = preparedStmt.executeQuery();
				      rs.next();
				      if(rs.getInt(1) == 1)
				      {
				    	  return false;
				      }
			}
			
		      
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
		
	}
	
	/**
	 * Users can choose different time slots in a day for their appointments.<br>
	 * This function will update the calendar table in the database with informations of users and their appointments. <br>
	 * This function will also update the number of appointments from the found worker.
	 * @param slots is an array , which is responsible for storing the timeslots 
	 * @param idDate is the input Date in integer format
	 * @param idWorker is the idWorker which is found in the function lookForAvailableWorker(int idDate, int radioBoxCounter, int[ ] arr)
	 * @param RadioBoxCounter is the counter of radiobox. if it is selected
	 */
	@SuppressWarnings("rawtypes")
	public static void insertIntoCalendar(ArrayList slots,int idDate,int idWorker,int RadioBoxCounter)
	{
		try {
					
					for(int i=0;i<slots.size();i++)
					{

							  String query = "update calendar set cMail = ? , available = 1 where idDate = ? and  timeslot = ? and idWorker = ? ";
						      PreparedStatement preparedStmt = conn.prepareStatement(query);
						      preparedStmt.setString(1,Customer.geteMail());
						      preparedStmt.setInt(2,idDate);
						      preparedStmt.setString(3, (String) slots.get(i));
						      preparedStmt.setInt(4, idWorker);
						      // execute the java preparedstatement
						      preparedStmt.executeUpdate();
					
					}
					
					  String query2 = "update worker set countAPP = countAPP + ? where idWorker = ? ";
				      PreparedStatement preparedStmt2 = conn.prepareStatement(query2);
				      preparedStmt2.setInt(1,RadioBoxCounter);
				      preparedStmt2.setInt(2,idWorker);
				     
				      // execute the java preparedstatement
				      preparedStmt2.executeUpdate();  
				      
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
	}
	
	/**
	 * this function is responsible for creating a new worker. all informations of this worker will be stored in the database.
	 * @param comboBox is the JCombobox with vollzeit or teilzeit
	 */
	@SuppressWarnings("rawtypes")
	public static void insertIntoTableWorker(JComboBox comboBox)
	{
		int fulltime = 0;
		if("vollzeit".equalsIgnoreCase(String.valueOf(comboBox.getSelectedItem())))
		 {
			fulltime = 1;
		 }
		else
		{
			fulltime = 0;
		}
		
		try {
			
			String sql ="INSERT INTO worker (wName,wNachname,wMail,wPassword,countAPP,fulltime) VALUES ( ? , ? , ? , ? ,0, ?)";
			PreparedStatement preparedStmt1 = conn.prepareStatement(sql);
			preparedStmt1.setString(1, CreateWorkerInterface.getFirstName());
			preparedStmt1.setString(2, CreateWorkerInterface.getLastName());
			preparedStmt1.setString(3, CreateWorkerInterface.getEmail());
			preparedStmt1.setString(4, CreateWorkerInterface.getPW());
			preparedStmt1.setInt(5, fulltime);
		      // execute the java preparedstatement
			preparedStmt1.executeUpdate();
			
		      
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * This function is responsible for creating a new worker. <br>
	 * Each worker will have his own table of appointments.<br>
	 * This function will generate a table of appointments for this worker in the database.<br>
	 * Informations that will be stored : art of working time, start day of work, end day of work
	 *
	 * @param comboBox displays the art of working time
	 * @param startText is the start day of work of worker
	 * @param EndText is the end day of work of user
	 */
	@SuppressWarnings("rawtypes")
	public static void fillUpCalendar(JComboBox comboBox, String startText, String EndText)
	{
		try {
			
			String sql = "select idWorker from worker where wmail = ? ";
			
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1,CreateWorkerInterface.getEmail());
			ResultSet rs = pst.executeQuery();
			rs.next();
			int idWorker = rs.getInt(1);
			String query = null;
			 //call procedure
			 if("vollzeit".equalsIgnoreCase(String.valueOf(comboBox.getSelectedItem())))
			 {
				 query = "{CALL fill_worker_calendar_vollzeit( ? , ? , ? )}";
			 }
			 else if("teilzeit(vormittag)".equalsIgnoreCase(String.valueOf(comboBox.getSelectedItem())))
			 {
				 query = "{CALL fill_worker_calendar_teilzeit_vormittag( ? , ? , ? )}";
			 }
			 else if("teilzeit(nachmittag)".equalsIgnoreCase(String.valueOf(comboBox.getSelectedItem())))
			 {
				  query = "{CALL fill_worker_calendar_teilzeit_nachmittag( ? , ? , ? )}";
			 }
			 
			java.sql.CallableStatement stmt = conn.prepareCall(query);
			stmt.setInt(1, idWorker);
			stmt.setInt(2, DateUlti.ConvertDateToInt( startText));
			stmt.setInt(3, DateUlti.ConvertDateToInt( EndText));
			stmt.executeQuery();
		      
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**Each of worker has his own abilities. for example worker A can only handle one kind of appointments , e.g : Fahrzeug
	 * <br> arr = {0,0,0,0,1};
	 * <br> This integer array will display the abilities of workers.
	 * <br> With the help of our array, our Insert Query is now much easier.
	 * @param arr is an array of integer  with values 0 and 1
	 */
	public static void insertIntoTableAbteilung(int[] arr)
	{
		try {
			
			String sql4 ="INSERT INTO abteilung (Arbeit,Pass,Berufsausbildung,Bauen,Fahrzeug) VALUES ( ?,?,?,?,?)";
			
			
			PreparedStatement preparedStmt2 = conn.prepareStatement(sql4);
			 preparedStmt2.setInt(1,arr[0]);
			 preparedStmt2.setInt(2,arr[1]);
			 preparedStmt2.setInt(3,arr[2]);
			 preparedStmt2.setInt(4,arr[3]);
			 preparedStmt2.setInt(5,arr[4]);
		      // execute the java preparedstatement
			preparedStmt2.executeUpdate();
		      // execute the java preparedstatement
		      
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * This function is responsible when a new customer is registered. It will stores his informations into database.<br>
	 * Informations of users are from createCustomer.java
	 */
	public static void insertIntoTableCustomer()
	{
		try {
			
			String sql2 = "INSERT INTO customer (cName,cNachname,cMail,cPassword) VALUES ( ? , ? , ? , ? )";
			PreparedStatement preparedStmt2 = conn.prepareStatement(sql2);
			preparedStmt2.setString(1, CreateCustomerInterface.getFirstName());
			preparedStmt2.setString(2, CreateCustomerInterface.getLastName());
			preparedStmt2.setString(3, CreateCustomerInterface.getEmail());
			preparedStmt2.setString(4, CreateCustomerInterface.getPW());
		      // execute the java preparedstatement
			preparedStmt2.executeUpdate();   
			
		} 
		 catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**Functions check if the Email has already been used or not. If return value is greater than 0 than we have a repeated email.
	 * @param email is the input Email from login frame
	 * @return 0 means that input Email has not been used.
	 */
	public static int repeatedEmail(String email)
	{
		int count = 0;
			try {
					
					String sql = "select * from customer where cMail = ? ";
					PreparedStatement preparedStmt= conn.prepareStatement(sql);
					preparedStmt.setString(1, email);
					ResultSet rs = preparedStmt.executeQuery();
					
				      // if Email is true but password is wrong
				      while(rs.next()) {
					     count++;
				      }
				      
				      String sql2 = "select * from worker where wMail = ? ";
						PreparedStatement preparedStmt2 = conn.prepareStatement(sql2);
						preparedStmt2.setString(1, email);
						ResultSet rs2 = preparedStmt2.executeQuery();
						while(rs2.next()) {
						     count++;
					      }
				
			} 
			 catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return count;
		
	}
	
	/**
	 * The selected rows will be deleted.<br>
	 * We update the calendar table <br>
	 * We update the count of appointments in the calendar worker <br>
	 * @param tb1Model is the table Model from CustomerInterface
	 */
	public static void deleteSelectedRowsFromTable(TableModel tb1Model)
	{
		for(int i=0;i<tb1Model.getRowCount();i++)
		{
			if(String.valueOf(tb1Model.getValueAt(i,3)).toString()=="true")
			{
				String timeslot = tb1Model.getValueAt(i,2).toString();
				String dates = tb1Model.getValueAt(i,1).toString();
				int idWorker = Integer.parseInt(tb1Model.getValueAt(i,0).toString()) ;
				
				try {
					String sql = " update calendar natural join dates set cMail = null, available = 0 where idWorker = ? and fulldate = ? and timeslot = ? ";
					PreparedStatement preparedStmt = conn.prepareStatement(sql);
					
				      preparedStmt.setInt(1,idWorker);
				      preparedStmt.setString(2,dates);
				      preparedStmt.setString(3,timeslot);
				      // execute the java preparedstatement
				      preparedStmt.executeUpdate();
				      
				      String sql2 = "update worker set countAPP = countAPP -1 where idWorker = ? ";
				      PreparedStatement preparedStmt2 = conn.prepareStatement(sql2);
				      preparedStmt2.setInt(1,idWorker);
				      preparedStmt2.executeUpdate();
				      
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * The function helps us displaying all the appointments that exist.
	 * @param tb1Model is the table Model from AdminInterface
	 */
	public static void displayAppointments(DefaultTableModel tb1Model) 
	{
		//TableUlti.RemoveAllRows(tb1Model);
		//select query
		try {
			String sql = "select idWorker,fulldate,timeslot,cMail from calendar natural join dates where available = 1; ";
			
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				String idWorker =	String.valueOf(rs.getInt("idWorker"));
				String date = String.valueOf(rs.getDate("fulldate"));
				String timeslot = rs.getString("timeslot");
				String cMail = rs.getString("cMail");

				String tbData[] = {idWorker,date,timeslot,cMail};
				tb1Model.addRow(tbData);
							
			}
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * The function gives us a list of Workers.
	 * @param tb1Model is the table Model from AdminInterface
	 */
	public static void workerDisplay(DefaultTableModel tb1Model) 
	{
		//TableUlti.RemoveAllRows(tb1Model);
		//select query
		try {
			String sql = "select * from worker ";
			
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				String idWorker =	String.valueOf(rs.getInt("idWorker"));
				String vorname = rs.getString("wName");
				String nachname = rs.getString("wNachname");
				String email = rs.getString("wMail");
				String password = rs.getString("wPassword");
				String count = String.valueOf(rs.getInt("countAPP"));

				String tbData[] = {idWorker,vorname,nachname,email,password,count};
				tb1Model.addRow(tbData);
							
			}
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**The function gives us the list of all customers.
	 * @param tb1Model is the table Model from AdminInterface
	 */
	public static void customerDisplay(DefaultTableModel tb1Model) 
	{
		//TableUlti.RemoveAllRows(tb1Model);
		//select query
		try {
			String sql = "select * from customer ";
			
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				String idWorker =	String.valueOf(rs.getInt("idCustomer"));
				String vorname = rs.getString("cName");
				String nachname = rs.getString("cNachname");
				String email = rs.getString("cMail");
				String password = rs.getString("cPassword");
				

				String tbData[] = {idWorker,vorname,nachname,email,password};
				tb1Model.addRow(tbData);
							
			}
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * The function will automatically delete all the old appointments from yesterday. (sql2) <br>
	 * The function will also update the count of appointment to each affected worker. (sql)
	 */
	public static void deleteOldAppointments()
	{
		try {
			String sql = " select idworker,count(*) from\r\n" + 
					"(select fulldate,idWorker \r\n" + 
					"from dates natural join calendar \r\n" + 
					"where fulldate < curdate() and available = 1) as a1\r\n" + 
					"group by idWorker ";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();	
			while(rs.next())
			{
				 String query2 = "update worker set countAPP = countAPP - ? where idWorker = ? ";
			      PreparedStatement preparedStmt2 = conn.prepareStatement(query2);
			      preparedStmt2.setInt(1,rs.getInt("count(*)"));
			      preparedStmt2.setInt(2,rs.getInt("idWorker"));
			    
			     
			      // execute the java preparedstatement
			      preparedStmt2.executeUpdate();
			}
			
			String sql2 = " delete from calendar where idDate in (\r\n" + 
					"select idDate\r\n" + 
					"from dates natural join calendar \r\n" + 
					"where fulldate < curdate()); ";
			PreparedStatement pst2 = conn.prepareStatement(sql2);
			pst2.executeUpdate();
			
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * The function display all available appointments to the day that user entered.<br>
	 * The available appointments are selected from the calendar of found worker.
	 * @param tb1Model is the table Model from Booking
	 * @param idDate is the input date
	 * @param idWorker is the found worker
	 */
	public static void displayTableAvailableAppointments(DefaultTableModel tb1Model, int idDate, int idWorker)
	{
		try {
			TableUlti.RemoveAllRows(tb1Model);
			//select query
			
			String sql = "select fulldate,timeslot from worker natural join calendar natural join dates where idDate = ? and available = 0 and weekend = 0 and idWorker = ? ";
			
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1,idDate);
			pst.setInt(2,idWorker);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{
				String id =rs.getString("fulldate");
				String timeslot = rs.getString("timeslot");

				String tbData[] = {id,timeslot};
				tb1Model.addRow(tbData);
							
			}
		      
		      
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	
	/**
	 * This Function gives us a list of all appointments from a specific user.
	 * @param tb1Model is the table Model from CustomerInterface
	 */
	public static void displayAppointmentsFromCustomer(DefaultTableModel tb1Model) 
	{
	try {
			
			String sql = "select idWorker,fulldate,timeslot from calendar natural join dates where cMail = ? ";
			
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1,Customer.geteMail());
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{
				String idWorker = String.valueOf(rs.getInt("idWorker"));
				String Date = String.valueOf(rs.getDate("fulldate"));
				String timeslot = rs.getString("timeslot");

				String tbData[] = {idWorker,Date,timeslot};
				tb1Model.addRow(tbData);
							
			}
		      
		      
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * This Function gives us a list of all appointments from a specific worker.
	 * @param tb1Model is the table Model from WorkerInterface
	 */
	public static void displayAppointmentsFromWorker(DefaultTableModel tb1Model)
	{
	try {
			TableUlti.RemoveAllRows(tb1Model);
			String sql = "select cMail,fulldate,timeslot from calendar natural join worker natural join dates where wMail = ? and available = 1";
			
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1,Worker.geteMail());
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{
				String customer = String.valueOf(rs.getString("cMail"));
				String Date = String.valueOf(rs.getDate("fulldate"));
				String timeslot = rs.getString("timeslot");

				String tbData[] = {customer,Date,timeslot};
				tb1Model.addRow(tbData);		
			}			
			
		      
		      
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @return 1 if he/she is fulltimeworker. return 0 if parttimeworker
	 */
	public static int fullTime()
	{
		int answer = 0;
		try {
			String sql = "select fulltime from worker where wMail = ? ";
			
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1,Login.getEmail());
			ResultSet rs = pst.executeQuery();
			rs.next();
			answer = (rs.getInt(1));
			
			      
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return answer;
	}
	
	/**
	 * @return total working hours in this month of input worker
	 */
	public static int getWorkingHours()
	{
		int answer = 0;
		try {
			String sql = "select count(fulldate) from dates natural join calendar natural join worker where month = ? and weekend = 0 and wMail = ? ";
			
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, DateUlti.thismonth());
			pst.setString(2,Login.getEmail());
			ResultSet rs = pst.executeQuery();
			rs.next();
			answer = (rs.getInt(1));
			
			      
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return answer;
	}
}
