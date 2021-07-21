package Model;

/**
 * @author Trinh Thanh Tung 3120718
 * Customer model ! 
 */
public class Customer {
	   private static String eMail;
	   private String password;
	
	@SuppressWarnings("static-access")
	public Customer(String eMail,String password)
	{
		this.eMail = eMail;
		this.password = password;
	}
	   
	public static String geteMail() {
		return eMail;
	}
	@SuppressWarnings("static-access")
	public void setMail(String eMail) {
		this.eMail = eMail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}