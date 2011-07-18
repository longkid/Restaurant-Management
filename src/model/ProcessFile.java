package model;
/*
 * @author Tu Thi Xuan Hien
 */
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ProcessFile 
{
	public static String FILENAME_POSITION="postiondata.dat";
	public static String FILENAME_EMPLOYEE="employeedata.dat";
	public static String FILENAME_CONSTRACT="constractdata.dat";
	public static String FILENAME_USERLOGIN="userlogin.dat";
	/* *******************************************************************************
	 * Description: 
	 * 		this method is used to read one object
	 * 		return an Object, so must be convert type 
	 * 		example: to read the list employee from file:
	 * 			CListEmployee list=(CListEmployee) ProcessFile.ReadData(ProcessFile.FILENAME_EMPLOYEE);	
	 * Parameters:
	 * 		String strPath - the path to the file
	 * Exception:
	 * 		Error if the file does not exist, or can't be accessed
	 * return type: Object
	 * *******************************************************************************/
	public static Object ReadData(String strPath)
	{
		Object obj=null;
		try
		{
			FileInputStream file=new FileInputStream(strPath);
			ObjectInputStream instream=new ObjectInputStream(file);
			obj =instream.readObject();
			instream.close();
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
		catch(ClassNotFoundException ex)
		{
			ex.printStackTrace();
		}
		return obj;
	}
	/* *******************************************************************************
	 * Description: 
	 * 		this method is used to write an object into file
	 * 		list: type CListEmployee	
	 * 		if the saving is successful, return TRUE, otherwise return FALSE
	 * Parameters:
	 * 		Object obj : object implements Serializable that we want to save into file
	 * 		String strPath - the path of the file
	 * Exception:
	 * 		error if the path is invalid or can't be accessed to drive 
	 * return type: boolean
	 * *******************************************************************************/
	public static boolean WriteData(Object obj,String strPath)
	{
		boolean bResult=false;
		try
		{
			FileOutputStream file=new FileOutputStream(strPath);
			ObjectOutputStream outstream=new ObjectOutputStream(file);
			outstream.writeObject(obj);
			outstream.close();
			bResult=true;
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
		return bResult;
	}
}
