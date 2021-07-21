package MainApp;

import java.awt.EventQueue;
import ConnectDB.ConnectDB;
import View.Login;

/**
 * @author Trinh Thanh Tung
 * Start of the App
 */
public class MainApp {

	/**
	 * @param args default value
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					ConnectDB.getInstance().init();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
