package Model;

/**
 * @author Trinh Thanh Tung 1320718
 * worker model !
 */
public abstract class Worker  {
	
	protected static String email;
	protected static int workinghours;
	protected long salary;
	
	public Worker(String email) {
		Worker.email = email;
	}
	//abstract
	public abstract String workingTime();
	//abstract
	public abstract long salary();
	
	public static String geteMail()
	{
		return email;
	}
	
	public static void setMail(String email)
	{
		Worker.email = email;
	}
	public static int getWorkinghours() {
		return workinghours;
	}
}
