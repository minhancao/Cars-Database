package server;

import java.io.ObjectInputStream;

import model.Automobile;

public interface AutoServer {
	
	public void addAutoToLHM(Automobile auto);
	
	public void listOfAvailableModels();
	
	public void sendObjectToClient(String carName);

}
