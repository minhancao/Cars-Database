package model;

import java.io.Serializable;
import java.util.ArrayList;

import model.OptionSet.Option;

public class Automobile implements Serializable {  //This class will represent the Model.
	private String make;
	private String model;
	private int basePrice;
	private ArrayList<OptionSet> optionSet;
	private ArrayList<Option> choice;
	
	public Automobile()
	{
		optionSet = new ArrayList<OptionSet>();
		make = "";
		model = "";
		basePrice = 0;
	}
	
	public String getMake()
	{
		return make;
	}
	
	public String getModel()
	{
		return model;
	}
	
	public int getBasePrice()
	{
		return basePrice;
	}

	public String getOptionSetNameWithName(String optionSetName)
	{
		for (int i = 0;i<optionSet.size();i++)
		{
			if (optionSet.get(i).getName().equals(optionSetName))
			{
				return optionSet.get(i).getName();
			}
		}
		
		return null;
	}
	
	//gets how many OptionSets there are
	public int getOptionSetSize()
	{
		return optionSet.size();
	}
	
	//gets how many options are in a specific OptionSet
	public int getOptionSetChoiceSize(int index)
	{
		return optionSet.get(index).getChoiceLength();
	}

	public OptionSet getOptionSetWithName(String n)
	{
		for(int i = 0; i<optionSet.size(); i++)
		{
			if(optionSet.get(i).getName().equals(n))
			{
				return optionSet.get(i);
			}
		}
		
		return null;
	}
	
	public String getOptionChoiceName(String setName, String choiceName)
	{
		for (int i = 0;i<optionSet.size();i++)
		{
			if (optionSet.get(i).getName().equals(setName))
			{
				return optionSet.get(i).getChoice(choiceName).getName(); //gets name of the option that is returned from calling the method getOptionChoice
			}
		}
		
		return null;
	}

	public float getOptionChoicePrice(String setName, String choiceName)
	{
		for (int i = 0;i<optionSet.size();i++)
		{
			if (optionSet.get(i).getName().equals(setName))
			{
				return optionSet.get(i).getChoice(choiceName).getPrice(); //gets name of the option that is returned from calling the method getOptionChoice
			}
		}
		return 0; //returns 0 as default price for the choice just in case 
	}

	public Option getOptionWithName(String optSetName, String optName)//optSet is not an array of 
	//OptionSet because you have already narrowed down to what option 
	//you want to look for in a specific OptionSet
	{
		for(int i = 0; i<optionSet.size(); i++)
		{
			if(optionSet.get(i).getName().equals(optSetName))
			{
				return optionSet.get(i).getChoice(optName);
			}
		}
		
		return null;
	}
	
	public void setMake(String n)
	{
		make = n;
	}
	
	public void setModel(String n)
	{
		model = n;
	}
	
	public void setBasePrice(int p)
	{
		basePrice = p;
	}
	
	public void setChoiceArrayList()
	{
		choice = new ArrayList<Option>();
		for (int i = 0;i<optionSet.size();i++)
		{
			choice.addAll(optionSet.get(i).getChoiceArrayList());
		}
	}
	
	public void setOptionSetName(String n)
	{
		OptionSet temp = new OptionSet(n); //creates a temporary OptionSet object with its name so I can add it into the arraylist of OptionSets	
		optionSet.add(temp);
	}
	
	public void makeOptionSetChoiceThruName(String optionSetName, String choiceName, float fixPrice)
	{
		for (int i = 0;i<optionSet.size();i++)
		{
			if (optionSet.get(i).getName().equals(optionSetName))
			{
				optionSet.get(i).setOptionChoice(choiceName, fixPrice);
			}
		}
	}
	
	public void makeOptionSetChoiceThruPrice(String optionSetName, String fixName, float Price)
	{
		for (int i = 0;i<optionSet.size();i++)
		{
			if (optionSet.get(i).getName().equals(optionSetName))
			{
				optionSet.get(i).setOptionChoice(fixName, Price);
			}
		}
	}
	
	public void setOptionChoice(String setName, String optionName, float optionPrice)
	{
		for (int i = 0;i<optionSet.size();i++)
		{
			if(optionSet.get(i).getName().equals(setName))
			{
				optionSet.get(i).setOptionChoice(optionName, optionPrice);
			}
		}
	}
	
	public void setOptionChoiceName(String setName, String optionName, float searchPrice)
	{
		for (int i = 0;i<optionSet.size();i++)
		{
			if(optionSet.get(i).getName().equals(setName))
			{
				optionSet.get(i).setOptionChoiceName(optionName, searchPrice);
			}
		}
	}
	
	public void setOptionChoicePrice(String setName, String searchName, float optionPrice)
	{
		for (int i = 0;i<optionSet.size();i++)
		{
			if(optionSet.get(i).getName().equals(setName))
			{
				optionSet.get(i).setOptionChoicePrice(searchName, optionPrice);
			}
		}
	}

	public void updateOptionSetNameThruName(String searchName, String replaceName)
	{
		for (int i = 0;i<optionSet.size();i++)
		{
			if(optionSet.get(i).getName().equals(searchName))
			{
				optionSet.get(i).updateOptionSetName(replaceName);
			}
		}
	}

	public void updateOptionNameThruName(String optionSetName, String optionName, String replaceOptionName)
	{
		for (int i = 0;i<optionSet.size();i++)
		{
			if(optionSet.get(i).getName().equals(optionSetName))
			{
				optionSet.get(i).updateOptionNameThruName(optionName, replaceOptionName);
			}
		}
	}

	public void updateOptionPriceThruName(String optionSetName, String optionName, float newPrice)
	{
		for (int i = 0;i<optionSet.size();i++)
		{
			if(optionSet.get(i).getName().equals(optionSetName))
			{
				optionSet.get(i).updateOptionPriceThruName(optionName, newPrice);
			}
		}
	}
	
	//removes the optionSet specified with the parameter inside the arraylist OptionSet
	public void deleteOptionSetThruName(String optionSetName)
	{
		for (int i = 0;i<optionSet.size();i++)
		{
			if(optionSet.get(i).getName().equals(optionSetName))
			{
				optionSet.remove(i);
			}
		}
	}

	public void deleteOptionThruName(String optionSetName, String optName)
	{
		for (int i = 0;i<optionSet.size();i++)
		{
			if(optionSet.get(i).getName().equals(optionSetName))
			{
				optionSet.get(i).deleteOptionThruName(optName);
			}
		}
	}
	
	public void print() {
		StringBuilder str = new StringBuilder();
		StringBuilder str2 = new StringBuilder();
		str.append("\nMake: ");
		str.append(make);
		str.append("   Model: ");
		str.append(model);
		str.append("   Base Price: $");
		str.append(basePrice);
		
		System.out.println(str);
		
		for(int i = 0;i<optionSet.size();i++)
		{
			str2.append(optionSet.get(i).print());
		}
		
		System.out.println(str2);
	}
	
	public void printChoice()
	{
		System.out.println("\nTesting to see if choice ArrayList in Automobile contains all choices: ");
		System.out.println("-----------------------------------------------------------------");
		for (int i = 0;i<choice.size();i++)
		{
			System.out.println(choice.get(i).getName() + "    $" + choice.get(i).getPrice());
		}
	}
	
	public void printTotalPrice()
	{
		float totalPrice = basePrice;
		
		for (int i = 0; i<choice.size();i++)
		{
			totalPrice += choice.get(i).getPrice();
		}
		
		System.out.println("\nTotal price of " + model + ": $" + totalPrice + "\n");
	}
	
}
