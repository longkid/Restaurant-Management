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
