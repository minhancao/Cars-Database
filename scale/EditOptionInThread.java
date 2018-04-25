package scale;

import adapter.*;
import model.*;


public class EditOptionInThread extends ProxyAutomobile{

	public EditOptionInThread(String [] input) {
	}
	
	//update optionset name
	public void op1(String [] input)
	{
		synchronized(OptionSet.class){
		updateOptionSetName("Ford Focus Wagon ZTW", input[0], input[1]);
		}
	}
	
	//update option price
	public void op2(String [] input)
	{
		synchronized(OptionSet.class){
			//changing price of option automatic to $999
			this.updateOptionPrice("Ford Focus Wagon ZTW", input[0], input[1], Float.parseFloat(input[2]));
			}
	}
	
	//update option name
	public void op3(String [] input)
	{
		synchronized(OptionSet.class){
			//changing price of option automatic to $999
			this.updateOptionName("Ford Focus Wagon ZTW", input[0], input[1], input[2]);
			}
		//input[0] has optionsetname, input[1] has optionname, input[2] has newoptionname
	}
	
	//delete option name
	public void op4(String [] input)
	{
		synchronized(OptionSet.class){
			//changing price of option automatic to $999
			this.deleteOptionName("Ford Focus Wagon ZTW", input[0], input[1]);
			}
		//input[0] has optionsetname, input[1] has optionname, input[2] has newoptionname
	}

	
}
