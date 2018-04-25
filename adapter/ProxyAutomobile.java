package adapter;

import java.util.Iterator;
import java.util.LinkedHashMap;

import exception.*;
import model.*;
import scale.*;
import util.*;


public abstract class ProxyAutomobile {
	
	private static LinkedHashMap<String, Automobile> obj = new LinkedHashMap<String, Automobile>();
	
	//makes the auto and puts it in the linkedhashmap
	public void BuildAuto(String filename)
	{
		FileIO openFile = new FileIO();
		
		String [] key = filename.split(".txt");
		
		try {
			obj.put(key[0], openFile.buildAutoObject(filename));
		} catch (AutoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//makes the auto and puts it in the linkedhashmap
	public void BuildAuto(String filename, int filetype)
	{
		if (filetype == 1)
			{
			FileIO openFile = new FileIO();
			
			try {
				obj.put(filename, openFile.buildAutoObject(filename));
			} catch (AutoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (filetype == 2)
		{
			FileIO openFile = new FileIO();
			obj.put(filename, openFile.readPropertiesFile(filename));
		}
	}
	
	public void makeMap()
	{
		obj = new LinkedHashMap<String, Automobile>();
	}
	
	//this function searches and prints the properties of the certain model name in the parameter
	public void printAuto(String Modelname)
	{	
		Iterator<Automobile> iter = obj.values().iterator();
		while (iter.hasNext())
		{
			Automobile temp = iter.next();
			if(temp.getModel().equals(Modelname))
			{
				temp.print();
			}
		}
	}
	
	public void printChoiceInAutomobile(String Modelname)
	{
		Iterator<Automobile> iter = obj.values().iterator();
		while (iter.hasNext())
		{
			Automobile temp = iter.next();
			if(temp.getModel().equals(Modelname))
			{
				temp.printChoice();
			}
		}
	}
	
	public void printTotalPrice(String Modelname)
	{
		Iterator<Automobile> iter = obj.values().iterator();
		while (iter.hasNext())
		{
			Automobile temp = iter.next();
			if(temp.getModel().equals(Modelname))
			{
				temp.printTotalPrice();
			}
		}
	}

	//This function searches the Model for a given OptionSet and sets the name of OptionSet to
	// the parameter String newName.
	public void updateOptionSetName(String Modelname, String OptionSetName, String newName)
	{
		Iterator<Automobile> iter = obj.values().iterator();
		while (iter.hasNext())
		{
			Automobile temp = iter.next();
			if(temp.getModel().equals(Modelname))
			{
				temp.updateOptionSetNameThruName(OptionSetName, newName);
			}
		}
	}
	
	//This function searches the Model for a given OptionSet and Option name, and sets the price to
	//newPrice.
	public void updateOptionPrice(String Modelname, String OptionSetName, String OptionName, float newPrice)
	{
		Iterator<Automobile> iter = obj.values().iterator();
		while (iter.hasNext())
		{
			Automobile temp = iter.next();
			if(temp.getModel().equals(Modelname))
			{
				temp.updateOptionPriceThruName(OptionSetName, OptionName, newPrice);
			}
		}
	}
	
	//This function searches the Model for a given OptionSet and Option name, and sets the option name to
	//newName.
	public void updateOptionName(String Modelname, String OptionSetName, String OptionName, String newName)
	{
		Iterator<Automobile> iter = obj.values().iterator();
		while (iter.hasNext())
		{
			Automobile temp = iter.next();
			if(temp.getModel().equals(Modelname))
			{
				temp.updateOptionNameThruName(OptionSetName, OptionName, newName);
			}
		}
	}
	
	//This function searches the Model for a given OptionSet and Option name, and deletes the option with 
	//the name.
	public void deleteOptionName(String Modelname, String optionSetName, String optName)
	{
		Iterator<Automobile> iter = obj.values().iterator();
		while (iter.hasNext())
		{
			Automobile temp = iter.next();
			if(temp.getModel().equals(Modelname))
			{
				temp.deleteOptionThruName(optionSetName, optName);
			}
		}
	}
	
	public void fix(int errorno)//allows access to fix method in FixAuto interface
	{
		AutoException e = new AutoException(errorno);
		e.fix(errorno);	
	}
	
	//EditThread interface
	public void Edit(int ops, String [] input, boolean isSynchronized)
	{
		EditOptions editOp = new EditOptions(ops, input);
		
	}
	
	//AutoServer interface
	public void addAutoToLHM(Automobile auto)
	{
		obj.put(auto.getModel(), auto);
	}
	
	public void listOfAvailableModels() {
		Iterator<Automobile> iter = obj.values().iterator();
		while (iter.hasNext())
		{
			Automobile temp = iter.next();
			System.out.println("Model: " + temp.getModel());
		}
	}

	public void sendObjectToClient(String carName) {
		Iterator<Automobile> iter = obj.values().iterator();
		while (iter.hasNext())
		{
			Automobile temp = iter.next();
			if(temp.getModel().equals(carName))
			{
				FileIO file = new FileIO();
				file.serializeAuto(temp);//sends serialized object to client
			}
		}
	}
	
	
}
