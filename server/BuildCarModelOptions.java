package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

import adapter.*;
import model.Automobile;

public class BuildCarModelOptions extends Thread implements AutoServer, Runnable{
	
	Socket csocket;
	
	public BuildCarModelOptions(Socket socket)
	{
		csocket = socket;
	}
	
	public void acceptAndCreateAuto(Properties prop)//accepts properties object from client socket over an ObjectStream
	{
        String CarMake = prop.getProperty("Make"); //this is how you read a property. It is like getting a value from HashTable.
        Automobile car = null;

		if(!CarMake.equals(null)) //makes the Automobile object from the properties object/file
		{
			car = new Automobile();
			String CarModel = prop.getProperty("Model");
			String Option1 = prop.getProperty("Option1");
			String OptionValue1a = prop.getProperty("OptionValue1a");
			String OptionValue1b = prop.getProperty("OptionValue1b");
			String Option2 = prop.getProperty("Option2");
			String OptionValue2a = prop.getProperty("OptionValue2a");
			String OptionValue2b = prop.getProperty("OptionValue1b");
			
			car.setMake(CarMake);
			car.setModel(CarModel);
			car.setOptionSetName(Option1);
			car.setOptionChoice(Option1, OptionValue1a, -815);
			car.setOptionChoice(Option1, OptionValue1b, 0);
			car.setOptionSetName(Option2);
			car.setOptionChoice(Option2, OptionValue2a, 0);
			car.setOptionChoice(Option2, OptionValue2b, 400);
		}
		
		addAutoToLHM(car); //adds the Automobile car to the LinkedHashMap
	}

	public void addAutoToLHM(Automobile auto) {
		// TODO Auto-generated method stub
		BuildAuto b = new BuildAuto();
		b.addAutoToLHM(auto); //calls addAutoToLHM method from ProxyAuto class
	}
	
	public static void main (String [] args) throws Exception
	{
		ServerSocket serverSocket = null;
		serverSocket = new ServerSocket(5004);

        while (true)
        {
        	Socket sock = serverSocket.accept();
        	System.out.println("Connected");
        	new Thread(new BuildCarModelOptions(sock)).start();
        }
		
	}
	
	public void run()
	{
		
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(csocket.getInputStream());
			Properties prop = null;
			Properties prop2 = null;
			Properties prop3 = null;
			

			prop = (Properties) in.readObject(); //reads in the Properties object/file from the client
			prop2 = (Properties) in.readObject(); //reads in the Properties object/file from the client
			prop3 = (Properties) in.readObject(); //reads in the Properties object/file from the client 


			acceptAndCreateAuto(prop);//calls acceptAndCreateAuto method to create an automobile out 
			//of the properties object and put it in LHM //Ford Focus Wagon ZTW
			acceptAndCreateAuto(prop2); //Honda Civic
			acceptAndCreateAuto(prop3); //Nissan Leaf
			listOfAvailableModels();//to debug/show that the 3 automobiles got added to the LHM
			
			in.close();
			csocket.close();
		} catch (IOException | ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}//gets data from client
		
	}

	public void listOfAvailableModels() {
		BuildAuto b = new BuildAuto();
		b.listOfAvailableModels();
	}

	public void sendObjectToClient(String carName) {
		BuildAuto b = new BuildAuto();
		b.sendObjectToClient(carName);
	}
	
	
}