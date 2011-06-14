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
	@SuppressWarnings("unchecked")
	public static String FILENAME="postiondata.dat";
	public static PositionList ReadData(String strPath)
	{
		PositionList listPostion=null;
		try
		{
			FileInputStream file=new FileInputStream(strPath);
			ObjectInputStream instream=new ObjectInputStream(file);
			listPostion =(PositionList)instream.readObject();
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
		return listPostion;
	}
	public static boolean WriteData(PositionList listPostion,String strPath)
	{
		boolean bResult=false;
		try
		{
			FileOutputStream file=new FileOutputStream(strPath);
			ObjectOutputStream outstream=new ObjectOutputStream(file);
			outstream.writeObject(listPostion);
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
