package com.DataManager.Middleware;

import java.util.Scanner;

public interface MiddlewareProcessInterface {

	public void addNewPerson(Scanner scan);

	public void updatePersonDetails(Scanner scan);

	public void listAllPersons();

	public void countAllPersons();

	public void deletePerson(Scanner scan);

	public void insertThroughXMLPath(Scanner scan);

	public void insertThroughXML(Scanner scan);
}
