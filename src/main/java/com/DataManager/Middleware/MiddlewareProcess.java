package com.DataManager.Middleware;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXParseException;

import com.DataManager.Beans.Person;
import com.DataManager.DBTransactions.DBTransactions;

public class MiddlewareProcess implements MiddlewareProcessInterface {

	DBTransactions dbTransactions = new DBTransactions();

	public void addNewPerson(Scanner scan) {
		// TODO Auto-generated method stub

		System.out.println("Enter person's details.");
		System.out.println("Enter ID (Numeric)");

		try {
			int id = scan.nextInt();
			System.out.println("Enter First Name");
			String firsName = scan.next();
			System.out.println("Enter Second Name");
			String lastName = scan.next();
			Person person = new Person(id, firsName, lastName);
			if (dbTransactions.checkIDPresent(id)) {
				System.out.println("ID already present");
			} else {
				dbTransactions.addData(person);
			}
		} catch (InputMismatchException e) {
			System.out.println("Invalid Input");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Internal Error");
		}

	}

	public void updatePersonDetails(Scanner scanner) {
		// TODO Auto-generated method stub

		try {
			System.out.println("Enter ID (Numeric)");

			int id = scanner.nextInt();
			System.out.println("Enter new first name");

			String firsName = scanner.next();
			System.out.println("Enter new last name");

			String lastName = scanner.next();
			Person person = new Person(id, firsName, lastName);
			if (dbTransactions.checkIDPresent(id)) {
				dbTransactions.updateData(person);
			} else {
				System.out.println("ID not present");
			}
		} catch (InputMismatchException e) {
			System.out.println("Invalid Input");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Internal Error");
		}

	}

	public void listAllPersons() {
		// TODO Auto-generated method stub
		ArrayList<Person> persons = dbTransactions.listData();

		for (int i = 0; i < persons.size(); i++) {
			System.out.println("ID: " + persons.get(i).getId() + ", First Name: " + persons.get(i).getFirstName()
					+ ", Last Name: " + persons.get(i).getLastName());
		}
	}

	public void countAllPersons() {
		// TODO Auto-generated method stub
		int count = dbTransactions.countData();
		System.out.println("count of Persons present: " + count);
	}

	public void deletePerson(Scanner scanner) {
		// TODO Auto-generated method stub

		System.out.println("Enter person's id (Numeric) to delete ");
		try {
			int id = scanner.nextInt();

			Person person = new Person();
			person.setId(id);
			if (dbTransactions.checkIDPresent(id)) {
				dbTransactions.deleteData(person);
			} else {
				System.out.println("ID not present");
			}
		} catch (InputMismatchException e) {
			System.out.println("Invalid Input");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Internal Error");
		}

	}

	public void insertThroughXMLPath(Scanner scan) {
		// TODO Auto-generated method stub
		System.out.println("Enter path of XML file");
		String path = scan.next();
		try {
			File inputFile = new File(path);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("person");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					int id = Integer.parseInt(eElement.getAttribute("ID"));
					Person person = new Person(id, eElement.getElementsByTagName("firstName").item(0).getTextContent(),
							eElement.getElementsByTagName("lastName").item(0).getTextContent());
					if (dbTransactions.checkIDPresent(id)) {
						System.out.println("ID already present: " + id);
					} else {
						dbTransactions.addData(person);
					}
				}
			}
		} catch (FileNotFoundException e2) {
			System.out.println("Incorrect Path, Please re-enter path");
		} catch (SAXParseException e1) {
			System.out.println("Invalid XML");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void insertThroughXML(Scanner scan) {
		// TODO Auto-generated method stub
		System.out.println("Enter  XML and Press Enter");
		String input = "";
		scan = new Scanner(System.in);
		try {
			String line;
			while (scan.hasNextLine()) {
				line = scan.nextLine();
				if (line.isEmpty()) {
					break;
				}
				input += line + "\n";
			}

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			InputSource is = new InputSource(new StringReader(input));
			Document doc = dBuilder.parse(is);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("person");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					int id = Integer.parseInt(eElement.getAttribute("ID"));
					Person person = new Person(id, eElement.getElementsByTagName("firstName").item(0).getTextContent(),
							eElement.getElementsByTagName("lastName").item(0).getTextContent());
					if (dbTransactions.checkIDPresent(id)) {
						System.out.println("ID already present: " + id);
					} else {
						dbTransactions.addData(person);
					}
				}
			}
		} catch (SAXParseException e1) {
			System.out.println("Invalid XML");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
