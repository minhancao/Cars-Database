package util;

import java.io.*;
import java.util.Properties;

import model.*;
import exception.*;

public class FileIO{
	
	public FileIO()
	{
		
	}
	
	public Automobile buildAutoObject(String filename) throws AutoException {
		
		Automobile fordZTW = new Automobile(); //filename will be the name of the model(automotive) of the car
		
		try {

			FileReader file = new FileReader(filename);
			BufferedReader buff = new BufferedReader(file);
			boolean eof = false;
			int basePrice = 0;
			String modelName = new String();
			int counter = 0;

			while (!eof) 
			{
				String line = buff.readLine();
	
				if (line == null) 
				{
					eof = true;
				}
				
				else
				{
					String[] testForBasePrice = line.split(" = ");
					
					if(testForBasePrice[0].equals("Make"))//puts in the brand or make of the car
					{
						fordZTW.setMake(testForBasePrice[1]);
					}
					
					//sets the model(automotive) name
					if(testForBasePrice[0].equals("Model"))//gets model name from text file and puts it into automotive name
					{
						
						boolean fixed = false; //if fixed=false(no fixing needed), the program would do as normal
						//if fixed=true, then the AutoException would have fixed the problem with missing base price in file
						try {
							strIsNumber(testForBasePrice[1]);
						}
						
						catch (ArrayIndexOutOfBoundsException e)
						{
							try{
							throw new AutoException(1, "No model name in text file!");
							}
							
							catch (AutoException ex)
							{
								fixed = true;
								modelName = ex.fix1();
							}
		
						}
						
						if(!fixed && !strIsNumber(testForBasePrice[1]))
						{
							modelName = testForBasePrice[1];
						}
						fordZTW.setModel(modelName);//sets name for model(automotive)
					}
					
					//sets the model(automotive) base price
					if(testForBasePrice[0].equals("Base Price"))//gets base price from text file and puts it in automotive basePrice
					{
						boolean fixed = false; //if fixed=false(no fixing needed), the program would do as normal
						//if fixed=true, then the AutoException would have fixed the problem with missing base price in file
						try {
							strIsNumber(testForBasePrice[1]);
						}
						
						catch (ArrayIndexOutOfBoundsException e)
						{
							try{
							throw new AutoException(2, "No base price in text file!");
							}
							
							catch (AutoException ex)
							{
								fixed = true;
								basePrice = ex.fix2();
							}
		
						}
						
						if (!fixed){
							basePrice = Integer.parseInt(testForBasePrice[1]);
						}
						fordZTW.setBasePrice(basePrice);//sets basePrice for model(automotive)
						
					}
					
					//if statement used to read in all the options
					if(!strIsNumber(line) && !testForBasePrice[0].equals("Model") && !testForBasePrice[0].equals("Base Price") && !testForBasePrice[0].equals("Make"))
					{
						String[] optionSetName = line.split(": ");
						String[] options;
						String[] optionPrice;
						String tempSetName = null; //holds setName of when it is fixed
		
						options = optionSetName[1].split(", ");//splits the rest in the text file for the options for each customization
						boolean fixed = false;
						
						//sets name for the OptionSet
						//the counter corresponds with which option is read first in the text file
						if((optionSetName[0].equals("") || optionSetName[0].equals(" ")) && counter==0)
						{
							try{
							throw new AutoException(4, "Missing color optionset from text file!");
							}
							
							catch(AutoException ex)
							{
								tempSetName = ex.fix4();
								fordZTW.setOptionSetName(tempSetName);
								fixed = true;
							}
						}
						
						if((optionSetName[0].equals("") || optionSetName[0].equals(" ")) && counter==1)
						{
							try{
							throw new AutoException(5, "Missing transmission optionset from text file!");
							}
							
							catch(AutoException ex)
							{
								tempSetName = ex.fix5();
								fordZTW.setOptionSetName(tempSetName);
								fixed = true;
							}
						}
						
						//no exception was caught
						if(!fixed)
						{
							fordZTW.setOptionSetName(optionSetName[0]);
							tempSetName = optionSetName[0]; //so I can put this in parameter when i read options in
						}
						
						//puts in option name and option price for each individual option
						for (int i = 0;i<options.length;i++)
						{
							optionPrice = options[i].split("=");
							boolean fixed1 = false;
							boolean fixed2 = false;
							String temp = null;
							float tempPrice = 0;
							
							try{
								tempPrice = Float.parseFloat(optionPrice[1]); //tests to see if there is a price next to the option
							}
								
							catch(NumberFormatException e)
							{
								try
								{
									throw new AutoException(3, "Missing price next to option in text file!");
								}
								
								catch(AutoException ex)
								{
									//the fix for this is to just set the default option price to $0
									tempPrice = ex.fix3();
									fixed1 = true;
								}
							}
							
							if (optionPrice[0].equals("") || optionPrice[0].equals(" ") && counter == 2)
							{
								try{
								throw new AutoException(6, "Missing standard option for brakes optionset from text file!");
								}
								
								catch(AutoException e)
								{
									temp = e.fix6();
									fixed2 = true;
								}
							}	
						
							//if both exceptions of missing price next to option and missing standard option occurs
							if(fixed1 && fixed2)
							{
								fordZTW.setOptionChoice(tempSetName, temp, tempPrice);
							}
							
							//if only missing price exception occurs
							if(fixed1 && !fixed2){
								//this sets the option name and price inside the current optionset
								fordZTW.makeOptionSetChoiceThruPrice(tempSetName, optionPrice[0], tempPrice);
							}
							
							//if only missing standard option occurs
							if(!fixed1 && fixed2){
								//this sets the option name and price inside the current optionset
								fordZTW.makeOptionSetChoiceThruName(tempSetName, temp, Float.parseFloat(optionPrice[1]));
							}
							
							//if no exception occurred
							if(!fixed1 && !fixed2){
							//this sets the option name and price inside the current optionset
							fordZTW.setOptionChoice(tempSetName, optionPrice[0], Float.parseFloat(optionPrice[1]));
							}
						}
					
					}
					
					//only increments counter by 1 if each line read is meant for an optionset with its options
					if(!strIsNumber(line) && !testForBasePrice[0].equals("Make") && !testForBasePrice[0].equals("Model") && !testForBasePrice[0].equals("Base Price") && counter<5)
					{
						counter++;
					}
				}
			}
			
			
			fordZTW.setChoiceArrayList(); //puts the choice ArrayList from all OptionSets into the choice ArrayList in Automobile class
			buff.close();
		}
		
			
			catch (IOException e) {
				System.out.println("Error нн " + e.toString());
			}
		
		return fordZTW;
	
	}
	
	//used to test if the string is a number
	public boolean strIsNumber(String str)
	{
		for(int i = 0;i<str.length();i++)
		{
			if(!Character.isDigit(str.charAt(i)))
			{
				return false;
			}
		}
		
		return true;
	}
	
	//serializes file
	public void serializeAuto(Automobile FordZTW)
	{
		try
		{

		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("FordZTW.ser"));

		out.writeObject(FordZTW);

		out.close();

		}

		catch(Exception e)

		{ System.out.print("Error: " + e);

		System.exit(1);

		}
	}
	
	//deserializes file
	public Automobile deserializeAuto(String filename)
	{
		Automobile newFordZTW = null;
		
		try{
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));

		newFordZTW = (Automobile) in.readObject();
		
		in.close();
		}
		
		catch(Exception e)
		{
			System.out.print("Error: " + e);
			System.exit(1);
		}
		
		return newFordZTW;
	}
	
	public Automobile readPropertiesFile(String filename)
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
			
			car.setMake(CarMake);
			car.setModel(CarModel);
			car.setOptionSetName(Option1);
			car.setOptionChoice(Option1, OptionValue1a, -815);
			car.setOptionChoice(Option1, OptionValue1b, 0);
			car.setOptionSetName(Option2);
			car.setOptionChoice(Option2, OptionValue2a, 0);
			car.setOptionChoice(Option2, OptionValue2b, 400);
		}
		
		return car;
	}
}
