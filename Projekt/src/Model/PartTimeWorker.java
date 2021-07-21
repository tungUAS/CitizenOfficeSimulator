package Model;

/**
 * @author Trinh Thanh Tung 1320718
 * part time worker, extends from worker
 */
public class PartTimeWorker extends Worker
{
	private int workinghours;
	
	public PartTimeWorker(String email,int workingday) {
		super(email);
		this.workinghours = workingday;
	}
	public void setWorkingHours(int workinghours)
	{
		this.workinghours = workinghours;
	}
	

	@Override
	public long salary() {
		//part time worker get 10 euros per hour
		return workinghours * 10;
		
	}


	@Override
	public String workingTime() {
		return "PartTimeWorker";
	}
}
