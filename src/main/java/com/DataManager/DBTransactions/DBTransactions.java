package com.DataManager.DBTransactions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.DataManager.Beans.Person;
import com.DataManager.Connection.ConnectionManager;

public class DBTransactions {
	PreparedStatement ps = null;
	Statement stmt = null;
	Connection conn = null;

	public void addData(Person person) {
		try {
			conn = ConnectionManager.createConnection();
			String sql = "insert into persons(ID, firstName, lastName) values(?, ?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, person.getId());
			ps.setString(2, person.getFirstName());
			ps.setString(3, person.getLastName());
			ps.executeUpdate();
			// System.out.println("Data Inserted Successfully");
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

	}

	public void updateData(Person person) {
		try {
			conn = ConnectionManager.createConnection();
			String sql = "update persons set firstName= ? , lastName= ? where ID=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(3, person.getId());
			ps.setString(1, person.getFirstName());
			ps.setString(2, person.getLastName());
			ps.executeUpdate();
			// System.out.println("Data Updated Successfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteData(Person person) {
		try {
			conn = ConnectionManager.createConnection();
			String sql = "delete from persons  where ID=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, person.getId());

			ps.executeUpdate();
			// System.out.println("Data Deleted Successfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	public ArrayList<Person> listData() {
		ArrayList<Person> persons = new ArrayList<Person>();
		try {
			conn = ConnectionManager.createConnection();
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT * FROM PERSONS";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs != null) {
				while (rs.next()) {
					// Retrieve by column name
					int id = rs.getInt("ID");
					String firstName = rs.getString("FirstName");
					String lastName = rs.getString("LastName");

					Person person = new Person(id, firstName, lastName);
					persons.add(person);
				}
			} else {
				System.out.println("ResultSet is Empty");
			}

			return persons;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return persons;
	}

	public int countData() {
		int count = 0;
		try {
			conn = ConnectionManager.createConnection();
			stmt = conn.createStatement();
			String sql;

			sql = "SELECT * FROM PERSONS";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs != null) {
				while (rs.next()) {
					// Retrieve by column name
					count++;
				}
			} else {
				System.out.println("ResultSet is Empty");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return count;
	}

	public boolean checkIDPresent(int id) {
		boolean check = false;
		try {
			conn = ConnectionManager.createConnection();
			stmt = conn.createStatement();
			String sql;

			sql = "SELECT * FROM PERSONS where id=" + id;
			ResultSet rs = stmt.executeQuery(sql);
			if (rs != null) {
				while (rs.next()) {
					// Retrieve by column name
					check = true;
				}
			} else {
				check = false;
				System.out.println("ResultSet is Empty");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return check;
	}

}
