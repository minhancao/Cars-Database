package client;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Properties;
import java.util.Scanner;

import model.Automobile;

public class CarModelOptionsIO extends Thread {

    public void readDataFromPropertiesFile(Properties props)
	{
	
		String CarMake = props.getProperty("Make"); //this is how you read a property. It is like getting a value from HashTable.
	
		Automobile car = null;
		if(!CarMake.equals(null))
		{
			car = new Automobile();
			String CarModel = props.getProperty("Model");
			String Option1 = props.getProperty("Option1");
			String OptionValue1a = props.getProperty("OptionValue1a");
			String OptionValue1b = props.getProperty("OptionValue1b");
			String Option2 = props.getProperty("Option2");
			String OptionValue2a = props.getProperty("OptionValue2a");
			String OptionValue2b = props.getProperty("OptionValue1b");
			
			car.setModel(CarModel);
			car.setOptionSetName(Option1);
			car.setOptionChoice(Option1, OptionValue1a, -815);
			car.setOptionChoice(Option1, OptionValue1b, 0);
			car.setOptionSetName(Option2);
			car.setOptionChoice(Option2, OptionValue2a, 0);
			car.setOptionChoice(Option2, OptionValue2b, 400);
		}
	}

	public Properties createPropertiesObj(String filename)
	{
		
		Properties props= new Properties(); //

		FileInputStream in = null;
		try {
			in = new FileInputStream(filename);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			props.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //This loads the entire file in memory.
		
		return props;
	}

	public static void main (String arg[]) throws IOException{
		Socket socket = null;
		socket = new Socket("127.0.0.1", 5004);

		
	
		Scanner scanner = new Scanner(System.in);
		boolean isDone = false;
		ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());//outputs data to client
		
		CarModelOptionsIO b = new CarModelOptionsIO();
		
		Properties props = b.createPropertiesObj("Ford Focus Wagon ZTW.properties");
		Properties props2 = b.createPropertiesObj("Honda Civic.properties");
		Properties props3 = b.createPropertiesObj("Nissan Leaf.properties");
		
		out.writeObject(props);//writes new Properties obj from Properties file to server
		out.flush();
		out.writeObject(props2);
		out.flush();
		out.writeObject(props3);
		out.flush();

		out.close();

    }
	 
}


