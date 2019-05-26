package com.DataManager.Main;

import java.io.IOException;
import java.util.Scanner;

import com.DataManager.Middleware.MiddlewareProcess;

public class MainClass {

	public static void main(String args[]) throws IOException {
		MiddlewareProcess middlewareProcess = null;
		Scanner scanner = null;
		try {
			while (true) {
				scanner = new Scanner(System.in);
				System.out.println("Welcome to Person's Detail DataBase");
				System.out.println("Press 1 to Add new person");
				System.out.println("Press 2 to Edit person details");
				System.out.println("Press 3 to Delete person");
				System.out.println("Press 4 to Count person");
				System.out.println("Press 5 to List person");
				System.out.println("Press 6 to insert data from XML file");
				System.out.println("Press 7 to insert data from XML commandline input");
				System.out.println("Press 8 to Exit");

				middlewareProcess = new MiddlewareProcess();
				int input = 0;
				try {
					input = scanner.nextInt();
				} catch (Exception e) {
					System.out.println("Invalid Input");
				}
				if (input == 0) {
					System.out.println("Enter Numeric value");
				}

				else if (input == 1) {
					middlewareProcess.addNewPerson(scanner);
				} else if (input == 2) {
					middlewareProcess.updatePersonDetails(scanner);
				} else if (input == 3) {
					middlewareProcess.deletePerson(scanner);
				} else if (input == 4) {
					middlewareProcess.countAllPersons();

				} else if (input == 5) {
					middlewareProcess.listAllPersons();

				} else if (input == 6) {
					middlewareProcess.insertThroughXMLPath(scanner);

				} else if (input == 7) {
					middlewareProcess.insertThroughXML(scanner);

				} else if (input == 8) {
					scanner.close();
					break;
				} else {
					System.out.println("Invalid Input");
				}
			}
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
			scanner.close();
			e.printStackTrace();
		}
	}

}
