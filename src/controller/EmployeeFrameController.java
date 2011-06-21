package controller;
/**
 * Author: Le Duy Phong

 * Purpose of this class: this class control the process between EmployeeFrame and some concern class in model
 */
import view.EmployeeFrame;

public class EmployeeFrameController {
	public static EmployeeFrameController singleton = new EmployeeFrameController();
	private EmployeeFrame view;

	public void visible(){
		this.view=new EmployeeFrame();
		this.view.setVisible(true);
	}

}
