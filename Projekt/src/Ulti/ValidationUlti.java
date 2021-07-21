
package Ulti;


import ConnectDB.ConnectDB;

/**
 * @author Thanh Tung Trinh
 * Functions are used in Login, CreateWorkerInterface, CreateCustomerInterface
 */
public class ValidationUlti {
	
	public static String NAME_REGEX = "[a-zA-Z][a-zA-Z ]*";
	public static String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	
	
	/**
	 * @param name is input value from text field
	 * @return true if name matches the rules
	 */
	public static boolean nameValid(String name)
	{
		return (name.matches(NAME_REGEX)) ;
	}
	
	/**
	 * @param lastname is input value from text field
	 * @return true if it matches the rules
	 */
	public static boolean lastNameValid(String lastname)
	{
		return (lastname.matches(NAME_REGEX) );
	}
	
	/**
	 * @param password is the password input in password field
	 * @return true if password's length is between 7 and 16
	 */
	public static boolean passwordValid(String password)
	{
		return (password.length() > 7 && password.length() < 16) ;
	}
	
	/**
	 * @param email is input email from textfield
	 * @return true if email is not duplicated and it matches the rules
	 */
	public static boolean EmailValid(String email)
	{
		boolean check = false;
		if(email.matches(EMAIL_REGEX)) {
			if(ConnectDB.repeatedEmail(email) == 0) {
				check= true;
			} 
			else
				check= false;
		}
		return check;
	}
		
}

