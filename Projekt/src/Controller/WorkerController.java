package Controller;

import ConnectDB.ConnectDB;
import Model.Worker;
import View.Login;
import View.WorkerInterface;
/**
 * @author Trinh Thanh Tung
 * Control the flow of input data from login frame then pass into worker model. 
 */
public class WorkerController {
	private  Worker worker;
	private  WorkerInterface wView; 
	
	public WorkerController(Worker w, WorkerInterface wview)
	{
		worker = w;
		wView = wview;
		initView();
		initController();
	}
	/**
	 * auto filled up in worker interface frame
	 */
	@SuppressWarnings("static-access")
	public void initView()
	{
		wView.label.setText("Welcome back " +worker.geteMail() );
	}
	
	public void initController()
	{
		wView.btnSalary().addActionListener(e->CreateFile());
		wView.btnBack().addActionListener(e->Back());
	}
	
	private void Back() {
		ConnectDB.removeFromOnlineUsers(Worker.geteMail());
		Worker.setMail(null);
		
		Login frame = new Login();
		frame.setVisible(true);
		wView.dispose();
	}
	/**
	 *Create a salary text file for worker 
	 */
	private void CreateFile() {
		// TODO Auto-generated method stub
		File.CreateFile.CreateNewFile(Worker.geteMail(), Worker.getWorkinghours(), worker.workingTime(),worker.salary());
	}
	/**
	 * set input Email into model Customer to use in other functions
	 */
	public void setWorkerEmail()
	{
		Worker.setMail(Login.getEmail());
	}
}
