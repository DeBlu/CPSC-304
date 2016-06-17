package pkg304flighdbdebug;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

import oracle.jdbc.driver.OracleDriver;

import java.sql.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

public class DBManager {

	public Connection c;
	public Statement s;

	public DBManager() {
	};

	public static void main(String[] args) throws SQLException {
	 Passenger p = new Passenger();
	 System.out.println(p.searchByAirportsUsed("2016-06-09","2016-06-10","1237","1336").get(0));
	 System.out.println(p.searchMain("ZHP", "YXX", "2016-06-09").get(0));
	 System.out.println(p.searchByMinPrice());
	 System.out.println(p.phobia());
	//Employee e = new Employee();
	//System.out.println(p.searchByAirline("porter airlines"));
	//System.out.println(p.checkSeatAvailabilities("38092225"));
	//System.out.println(p.searchBySeatNo("63A", "38092225"));
	//System.out.println(p.searchMain("ZUC", "YAE", "2016-06-09"));
	// p.checkCredentials("GL139320");
	// p.addMember("cr123", "cr123@gmail.com", "GL139320");
	// p.changePersonalInformation("GL139320", "cr124@gmail.com", "cr1234");
	// p.deleteAccount("cr1234", "cr124@gmail.com", "GL139320");
	// p.purchaseTicket("nuss", "breaks", "07H", "38092225", "AB149865");
	// e.changeSeatType("42U","50X");
	// e.cancelTicket(13287754);
	}

	public void connect() {
		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			c = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost.ugrad.cs.ubc.ca:1522:ug",
					"ora_n1y8", "a53693123");
			s = c.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Failed to connect.");
		}
	}

	public void disconnect() {
		try {
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Failed to disconnect.");
		}
	}

	/* for insert, update and delete */
	public void iud(String x) {
		try {
			s.executeUpdate(x);
			System.out.println("Updated sucessfully.");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Failed to update.");
		}
	}

	/* for select */
	public ResultSet fetch(String y) {
		ResultSet rs = null;
		if (s == null)
			System.out.println("Empty result set.");
		try {
			rs = s.executeQuery(y);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Failed to retrieve data.");
		}
		return rs;
	}

	/*
	 * private BufferedReader in = new BufferedReader(new InputStreamReader(
	 * System.in));
	 * 
	 * public static void main(String[] args) throws SQLException { DBManager c1
	 * = new DBManager(); DriverManager.registerDriver(new
	 * oracle.jdbc.driver.OracleDriver()); con = DriverManager.getConnection(
	 * "jdbc:oracle:thin:@localhost:1522:ug", "ora_n1y8", "a53693123");
	 * System.out.println("\nConnected to Oracle!"); c1.showMenu(); }
	 */

	/* private static Connection con; */

	/*
	 * test function
	 */

	/*
	 * private void show() { String passportNo; String name; Statement stmt;
	 * ResultSet rs;
	 * 
	 * try { stmt = con.createStatement();
	 * 
	 * rs = stmt.executeQuery("SELECT * FROM passenger");
	 * 
	 * // get info on ResultSet ResultSetMetaData rsmd = rs.getMetaData();
	 * 
	 * // get number of columns int numCols = rsmd.getColumnCount();
	 * 
	 * System.out.println(" ");
	 * 
	 * // display column names; for (int i = 0; i < numCols; i++) { // get
	 * column name and print it
	 * 
	 * System.out.printf("%-15s", rsmd.getColumnName(i + 1)); }
	 * 
	 * System.out.println(" ");
	 * 
	 * while (rs.next()) { // for display purposes get everything from Oracle //
	 * as a string
	 * 
	 * // simplified output formatting; truncation may occur
	 * 
	 * passportNo = rs.getString("passportNo"); System.out.printf("%-10.10s",
	 * passportNo);
	 * 
	 * name = rs.getString("name"); System.out.printf("%-20.20s", name); }
	 * 
	 * // close the statement; // the ResultSet will also be closed
	 * stmt.close(); } catch (SQLException ex) { System.out.println("Message: "
	 * + ex.getMessage()); } }
	 */

	/*
	 * test function
	 */

	/*
	 * private void showMenu() { int choice; boolean quit;
	 * 
	 * quit = false;
	 * 
	 * try { // disable auto commit mode con.setAutoCommit(false);
	 * 
	 * while (!quit) {
	 * System.out.print("\n\nPlease choose one of the following: \n");
	 * System.out.print("1.  Insert passenger\n");
	 * System.out.print("2.  Show passenger\n");
	 * System.out.print("3.  Quit\n>> ");
	 * 
	 * choice = Integer.parseInt(in.readLine());
	 * 
	 * System.out.println(" ");
	 * 
	 * switch (choice) { case 1: insert(); break; case 2: show(); break; case 3:
	 * quit = true; } }
	 * 
	 * con.close(); in.close(); System.out.println("\nGood Bye!\n\n");
	 * System.exit(0); } catch (IOException e) {
	 * System.out.println("IOException!");
	 * 
	 * try { con.close(); System.exit(-1); } catch (SQLException ex) {
	 * System.out.println("Message: " + ex.getMessage()); } } catch
	 * (SQLException ex) { System.out.println("Message: " + ex.getMessage()); }
	 * }
	 */

	/*
	 * inserts a passenger (test function)
	 */
	/*
	 * private void insert() { int passportNo; String name; PreparedStatement
	 * ps;
	 * 
	 * try { ps = con.prepareStatement("INSERT INTO passenger VALUES (?,?)");
	 * 
	 * System.out.print("\nPassport Number: "); passportNo =
	 * Integer.parseInt(in.readLine()); ps.setInt(1, passportNo);
	 * 
	 * System.out.print("\nPassenger Name: "); name = in.readLine();
	 * ps.setString(2, name);
	 * 
	 * ps.executeUpdate();
	 * 
	 * // commit work con.commit();
	 * 
	 * ps.close(); } catch (IOException e) { System.out.println("IOException!");
	 * } catch (SQLException ex) { System.out.println("Message: " +
	 * ex.getMessage()); try { // undo the insert con.rollback(); } catch
	 * (SQLException ex2) { System.out.println("Message: " + ex2.getMessage());
	 * System.exit(-1); } } }
	 */

}

