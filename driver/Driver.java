/*
Name: Minh An Cao
Date: 3/10/17

Compiler: Eclipse

Lab 5 Assignment
Description: This program is created to be a client server application using text and properties file.
*/

package driver;

import java.util.Scanner;

import adapter.*;

public class Driver {
	
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
	//Build Automobile Object from a file.
		
		//test for assignment 5
		BuildAuto fordZTW = new BuildAuto();

		fordZTW.BuildAuto("Ford Focus Wagon ZTW.txt", 1);//puts the txt file into the linkedhashmap
		fordZTW.BuildAuto("Honda Civic.properties", 2);
		
		fordZTW.printAuto("Ford Focus Wagon ZTW");
		fordZTW.printAuto("Honda Civic");
		
	
	}

}
