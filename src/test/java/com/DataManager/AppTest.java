package com.DataManager;

import com.DataManager.Beans.Person;
import com.DataManager.DBTransactions.DBTransactions;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
	 */
	public AppTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */

	public void testCount() {
		DBTransactions dbTrans = new DBTransactions();

		assertEquals(dbTrans.countData(), dbTrans.countData());
	}

	public void testpresentFalseCondition() {
		DBTransactions dbTrans = new DBTransactions();

		assertFalse(dbTrans.checkIDPresent(1000));
	}

	public void testpresentTrueCondition() {
		DBTransactions dbTrans = new DBTransactions();

		assertFalse(!dbTrans.checkIDPresent(2));
	}
}
