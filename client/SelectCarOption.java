package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SelectCarOption {
	
	public static void main(String [] args) throws IOException
	{
		ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(5004);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 5004.");
            System.exit(1);
        }

        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();  //accepts request from client to connect
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }

        clientSocket.close();
        serverSocket.close();

		
	}

}
