package exception;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AutoException extends Exception {	
	private int errorno;
	private String errormsg;
	
	public AutoException(int errorno, String errormsg)
	{
		super();
		this.errorno = errorno;
		this.errormsg = errormsg;
	}
	
	public AutoException(int errorno)
	{
		super();
		this.errorno = errorno;
	}
	
	public AutoException(String errormsg)
	{
		super();
		this.errormsg = errormsg;
	}
	
	public AutoException()
	{
		super();
	}
	
	public int getErrorNo()
	{
		return errorno;
	}
	
	public String getErrorMsg()
	{
		return errormsg;
	}
	
	public void setErrorNo(int errorno)
	{
		this.errorno = errorno;
	}
	
	public void setErrorMsg(String errormsg)
	{
		this.errormsg = errormsg;
	}
	
	public String fix1()
	{
		print();
		log();	
		return "Ford Focus Wagon ZTW";
	}
	
	public int fix2()
	{
		print();
		log();	
		return 18445;
	}
	
	public float fix3()
	{
		print();
		log();	
		return 0;
	}
	
	public String fix4()
	{
		print();
		log();	
		return "Color";
	}
	
	public String fix5()
	{
		print();
		log();	
		return "Transmission";
	}
	
	public String fix6()
	{
		print();
		log();	
		return "Standard";
	}
	
	public void log()
	{
		try{
			File file = new File("log.txt");
			if(!file.exists())
			{
				file.createNewFile();
			}
			FileWriter f1 = new FileWriter(file.getAbsoluteFile(), true);//true part says append mode is on
			BufferedWriter b1 = new BufferedWriter(f1);
			
			String date = new SimpleDateFormat("yyyy.MM.dd.hh.mm").format(new Date());
			
			b1.write("Error #" + errorno + ": " + errormsg + " - " + date + "\r\n");
			b1.close();
			}
			
			catch(IOException e)
			{
				e.printStackTrace();
			}
	}
	
	public void print()
	{
		System.out.println("Error #" + errorno + ": " + errormsg);
	}
	
	public void fix(int errorno)
	// i am really confused on how to approach this because the return type is void and i need the return type to be whatever the specific problem is
	{
		Fix1to100 f1 = new Fix1to100();
		
		switch(errorno)
		{
		case 1: f1.fix1(); break;
		case 2: f1.fix2(); break;
		case 3: f1.fix3(); break;
		case 4: f1.fix4(); break;
		case 5: f1.fix5(); break;
		case 6: f1.fix6(); break;
		}
		
		print();
	}
	/*public void fix(int errno)// i am really confused on how to approach this because the return type is void and i need the return type to be whatever the specific problem is

	{

	Fix1to100 f1 = new Fix1to100();

	switch(errno)

	{

	case 1: f1.fix1(errno);break;


	}

	}*/
}