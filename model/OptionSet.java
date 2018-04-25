package model;

import java.io.Serializable;
import java.util.ArrayList;

public class OptionSet implements Serializable {
	
	public class Option implements Serializable {
		private String name;
		private float price;
		
		protected Option(){name = ""; price = 0;}
		
		protected Option(String n)
		{
			name = n;
		}
		
		protected Option(float p)
		{
			price = p;
		}
		
		protected Option(String n, float p)
		{
			name = n;
			price = p;
		}
		
		protected String getName()
		{
			return name;
		}
		
		protected float getPrice()
		{
			return price;
		}
		
		protected void setName(String n)
		{
			name = n;
		}
		
		protected void setPrice(float p)
		{
			price = p;
		}
		
		protected String print() {
			StringBuilder str = new StringBuilder();
			str.append(name);
			str.append(" = $");
			str.append(price);
			
			return str.toString();
		}
		
	}

	private String name;
	private ArrayList<Option> choice;
	
	protected OptionSet(){
		name = "";
		choice = new ArrayList<Option>();
	}
	
	protected OptionSet(String name)
	{
		this.name = name;
		choice = new ArrayList<Option>();
	}
	
	protected String getName()
	{
		return name;
	}
	
	protected int getChoiceLength()
	{
		return choice.size();
	}
	
	//this method allows access to the option's name and price because it returns the object itself
	protected Option getChoice(String choiceName)
	{
		for (int i = 0;i<choice.size();i++)
		{
			if(choice.get(i).getName().equals(choiceName))
			{
				return choice.get(i);
			}
		}
		return null;
	}
	
	//this method returns choice so it can be added to the choice arraylist in automobile
	protected ArrayList<Option> getChoiceArrayList()
	{
			return choice;
	}
	
	protected void makeNewChoiceName(String choiceName)
	{
		Option temp = new Option(choiceName);
		choice.add(temp);
	}
	
	protected void makeNewChoicePrice(float choicePrice)
	{
		Option temp = new Option(choicePrice);
		choice.add(temp);
	}
	
	//puts in the name of the option as well as the price
	protected void setOptionChoice(String optionName, float optionPrice)
	{
		Option temp = new Option(optionName, optionPrice);
		choice.add(temp);
	}
	
	protected void setOptionChoiceName(String optionName, float searchPrice)
	{
		for (int i = 0;i<choice.size();i++)
		{
			if (choice.get(i).getPrice() == searchPrice)
			{
				choice.get(i).setName(optionName);
			}
		}
	}
	
	protected void setOptionChoicePrice(String searchName, float optionPrice)
	{
		for (int i = 0;i<choice.size();i++)
		{
			if (choice.get(i).getName().equals(searchName))
			{
				choice.get(i).setPrice(optionPrice);
			}
		}
	}
	
	protected void setName(String n)
	{
		name = n;
	}
	
	protected void updateOptionSetName(String n)
	{
		name = n;
	}

	protected void updateOptionNameThruName(String optName, String replaceOptName)
	{
		for (int i = 0;i<choice.size();i++)
		{
			if(choice.get(i).getName().equals(optName))
			{
				choice.get(i).setName(replaceOptName);
			}
		}
	}

	protected void updateOptionPriceThruName(String optName, float newPrice)
	{
		for (int i = 0;i<choice.size();i++)
		{
			if(choice.get(i).getName().equals(optName))
			{
				choice.get(i).setPrice(newPrice);
			}
		}
	}
	
	protected void deleteOptionThruName(String optName)
	{
		for (int i = 0;i<choice.size();i++)
		{
			if(choice.get(i).getName().equals(optName))
			{
				choice.remove(i); //removes the option at the index where the optName is found
			}
		}
	}
	
	protected String print() {
		StringBuilder str = new StringBuilder();
		str.append("\nOptions for ");
		str.append(name);
		str.append(": ");
		for(int i = 0;i<choice.size();i++)
		{
			str.append(choice.get(i).print());
			
			if(i<choice.size()-1)
			{
				str.append(", ");
			}
			
		}
		
		return str.toString();
	}

}

