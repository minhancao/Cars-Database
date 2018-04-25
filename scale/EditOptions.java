package scale;

import model.OptionSet;
import adapter.*;

//this is a thread
public class EditOptions extends ProxyAutomobile implements Runnable{
	private Thread t;
	private String input [];
	private int ops;
	public EditOptions(int ops, String [] input)
	{
		t = new Thread(this);
		this.input = input;
		this.ops = ops;
		t.start();
	}
	
	public EditOptions(){}
	
	public void run(){
		EditOptionInThread editOp = new EditOptionInThread(input);
		
		switch(ops)
		{
		case 1: editOp.op1(input); break;
		case 2: editOp.op2(input); break;
		case 3: editOp.op3(input); break;
		case 4: editOp.op4(input); break;
		}
	}


}


