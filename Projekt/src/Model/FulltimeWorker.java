package Model;

/**
 * @author Trinh Thanh Tung 1320718
 * fulltime worker, extends from worker
 */
public class FulltimeWorker extends Worker {
	
	private int workinghours;
	
	public FulltimeWorker(String email,int workingday) {
		super(email);
		// TODO Auto-generated constructor stub
		this.workinghours = workingday;
	}
	public void setWorkingHours(int workinghours)
	{
		this.workinghours = workinghours;
	}

	@Override
	public long salary() {
		//full time worker get 12 euros per hour
		return workinghours * 12;
		
	}

	@Override
	public String workingTime() {
		return "FullTimeWorker";
	}

}
