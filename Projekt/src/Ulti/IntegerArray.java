package Ulti;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JRadioButton;

/**
 * @author Trinh Thanh Tung
 * responsible for converting selected boxes into a logical array of integers
 */
public class IntegerArray {
	
	/**
	 * The CheckboxCounter is used in CreateWorker and Booking
	 * @param buttonsarr array of buttons that need to be went through
	 * @param arr is the integer array which represent the 5 kinds of appointments
	 */
	@SuppressWarnings("deprecation")
	public static void returnIntArr(JRadioButton[] buttonsarr,int[] arr)
	{
		
		for(int i=0;i<buttonsarr.length;i++)
		{
			final Integer innerI = new Integer(i);
			buttonsarr[i].addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					
					if(e.getStateChange() == ItemEvent.SELECTED)
					{
						arr[innerI]=1;
					}
					else
					{
						arr[innerI]=0;
					}
					
				}
			});
		} 
	}
	

	
}

