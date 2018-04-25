package adapter;

public interface CreateAuto {

	//Given a text file name a method called BuildAuto can be written to build an instance of
	//Automobile. This method does not have to return the Auto instance.
	public void BuildAuto(String filename);
	
	public void BuildAuto(String filename, int filetype); //if filetype = 1, use txt file; if filetype = 2
	//, use properties file
	
	//this function searches and prints the properties of the certain model name in the parameter
	public void printAuto(String Modelname);
}
