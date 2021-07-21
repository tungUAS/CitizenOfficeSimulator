package Controller;

import java.awt.HeadlessException;
import java.text.ParseException;

import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import ConnectDB.ConnectDB;
import Ulti.DateUlti;
import Ulti.IntegerArray;
import Ulti.ValidationUlti;
import View.AdminInterface;
import View.CreateWorkerInterface;

/**
 * @author Trinh Thanh Tung
 * Control the flow of data after a worker is succesfully created
 */
public class CreateWorkerController {
	private CreateWorkerInterface crWorker;
	
	public CreateWorkerController(CreateWorkerInterface crworker)
	{
		crWorker = crworker;
		initController();
	}

	private void initController() {
		// TODO Auto-generated method stub
		crWorker.btnBack().addActionListener(e->backToAdminFrame());
		crWorker.btnCreateWorker().addActionListener(e->createCustomer());
	}

	@SuppressWarnings("static-access")
	private void createCustomer() {
		try {
			//check if all inputs are correct
			if(ValidationUlti.EmailValid(crWorker.getEmail()) && ValidationUlti.nameValid(crWorker.getFirstName())
					&& ValidationUlti.lastNameValid(crWorker.getLastName()) && ValidationUlti.passwordValid(crWorker.getPW())
					 && DateUlti.dateValid(crWorker.getStartWorkingDay()) && DateUlti.dateValid(crWorker.getEndWorkingDay()) )
			{
				//return a logical integer array which can be used for ABTEILUNG
				JRadioButton[] checkboxsarr = {crWorker.rdbtn1(),crWorker.rdbtn2(),crWorker.rdbtn2(),crWorker.rdbtn3(),crWorker.rdbtn4()};		
				IntegerArray.returnIntArr(checkboxsarr, crWorker.arr());
						// Database
						ConnectDB.insertIntoTableWorker(crWorker.comboBox());
						ConnectDB.fillUpCalendar(crWorker.comboBox(), crWorker.getStartWorkingDay(), crWorker.getEndWorkingDay());
						ConnectDB.insertIntoTableAbteilung(crWorker.arr());
						
						JOptionPane.showMessageDialog(null,"Succesful");
						AdminInterface frame = new AdminInterface();
						new AdminController(frame);
						frame.setVisible(true);
						crWorker.dispose();
						
			}
			else
			{
				
				//inheritance
				crWorker.checkName();
				crWorker.checkLastName();
				crWorker.checkEmail();
				crWorker.checkPW();
				//own method
				crWorker.counter();
				crWorker.startdateCheck();
				crWorker.enddateCheck();
				
				JOptionPane.showMessageDialog(null,"Please check your Input");
				
			}
		} catch (HeadlessException | ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/**
	 * back to admin frame
	 */
	private void backToAdminFrame() {
		AdminInterface frame = new AdminInterface();
		new AdminController(frame);
		frame.setVisible(true);
		crWorker.dispose();
	}
}
