package Model;

/**
 * @author Trinh Thanh Tung 1320718
 * Admin Model. FIXED!
 */
public class Admin {
	  
	   private static String eMail = "admin@buerger.amt";
	   private static String password = "admin";
	   
	 /**
	 * There is only one admin with this email and password!
	 */
	public Admin()
	   {
		   eMail = "admin@buerger.amt";
		   password = "admin";
	   }

	public static String geteMail() {
		return eMail;
	}

	public static String getPassword() {
		return password;
	}
}
