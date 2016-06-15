package pkg304flightdbpass;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;

import com.sun.rowset.internal.Row;

public class Passenger {

	DBManager dbm;

	public Passenger() {
		dbm = new DBManager();
	}

	public boolean addMember(String userid, String email, String passportNo) {
		dbm.connect();
		ResultSet rs = dbm
				.fetch("select count(*) from passenger where passportNo = '"
						+ passportNo + "'");
		int count = 0;
		try {
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("PassportNo does not exist.");
			e.printStackTrace();
		}
		int i = 0;
		if (count != 0) {
			dbm.iud("insert into member values(" + "'" + userid + "'," + "'"
					+ email + "','" + passportNo + "'," + "0" + ")");
			System.out.println("Congratulations, you are now a member.");
		}
		i++;
		dbm.disconnect();
		if (i == 1)
			return true;
		else
			return false;
	}

	public boolean deleteAccount(String userid, String email, String passportNo) {
		dbm.connect();
		int i = 0;
		dbm.iud("delete from member where " + "userid ='" + userid + "'"
				+ " and email ='" + email + "'" + " and passportNo ='"
				+ passportNo + "'");
		System.out.println("Your personal account has been deleted.");
		i++;
		dbm.disconnect();
		if (i == 1)
			return true;
		else
			return false;
	}

	public boolean changePersonalInformation(String passportNo, String nEmail,
			String nUserid) {
		dbm.connect();
		int i = 0;
		ResultSet rs = dbm
				.fetch("select count(*) from passenger where passportNo ='"
						+ passportNo + "'");
		int count = 0;
		try {
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("Passenger does not exist in the DB.");
			e.printStackTrace();
		}
		if (count == 0) {
			System.out.println("Failed to update your personal information.");
		}
		dbm.iud("update member set " + "email ='" + nEmail + "'"
				+ ", userid ='" + nUserid + "'" + " where passportNo ='"
				+ passportNo + "'");
		System.out.println("Your personal account has been updated.");
		i++;
		dbm.disconnect();
		if (i == 1)
			return true;
		else
			return false;
	}

	public void checkCredentials(String passportNo) {
		dbm.connect();
		ResultSet rs = dbm
				.fetch("select count(*) from passenger where passportNo ="
						+ "'" + passportNo + "'");
		int count = 0;
		try {
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("Failed.");
			e.printStackTrace();
		}
		if (count != 0) {
			ResultSet rs1 = dbm
					.fetch("select count(*) from member where passportNo ="
							+ "'" + passportNo + "'");
			int count1 = 0;
			try {
				while (rs1.next()) {
					count1 = rs1.getInt(1);
				}
			} catch (SQLException e) {
				System.out.println("Passenger does not exist in the DB.");
				e.printStackTrace();
			}
			if (count1 != 0) {
				System.out
						.println("An account already exists with this passportNo.");
			} else {
				System.out
						.println("You are eligible to apply for our membership program.");
			}
			dbm.disconnect();
		} else {
			System.out.println("Passenger does not exist in the DB.");
		}
	}

	public int purchaseTicket(String fName, String lName, String seatNo,
			String flightNo, String passportNo) {
		dbm.connect();
		ResultSet rs = dbm
				.fetch("select count(*) from passenger where passportNo = '"
						+ passportNo + "'");
		int checkExist = 0;
		try {
			while (rs.next()) {
				checkExist = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (checkExist != 1) {
			dbm.iud("insert into passenger values(" + "'" + passportNo + "'"
					+ ", " + "'" + fName + "'" + ", " + "'" + lName + "'" + ")");
		}
		ResultSet rs1 = dbm.fetch("select MAX(tID) from ticket");
		int newtID = -1;
		try {
			while (rs1.next()) {
				newtID = rs1.getInt(1);
				System.out.println(newtID);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		newtID++;
		dbm.iud("insert into ticket values(" + newtID + ", " + "'" + passportNo
				+ "'" + ", " + "'" + flightNo + "'" + ", " + 0 + ")");
		dbm.iud("update seat set available ='FALSE'" + " where seatNo ='"
				+ seatNo + "'");
		dbm.iud("update ticket set price = (select sum(fcost) as total from (select flightNo,fcost from flight where flightNo ='"
				+ flightNo
				+ "' union all select flightNo,scost as fcost from seat where seatNo = '"
				+ seatNo + "')) where tID =" + newtID);
		dbm.disconnect();
		return newtID;
	}

	public ArrayList<String> searchByCity(String city) {
		dbm.connect();
		ArrayList<String> aCode = new ArrayList<String>();
		ResultSet rs = dbm
				.fetch("select acode from airport where upper(city) = upper('"
						+ city + "')");
		try {
			while (rs.next()) {
				ResultSetMetaData metadata = rs.getMetaData();
				int numCols = metadata.getColumnCount();

				for (int i = 0; i < numCols; i++) {
					aCode.add(rs.getString(i + 1));
				}
			}
		} catch (SQLException e) {
			System.out
					.println("City either does not exist or does not contain an airport.");
			e.printStackTrace();
		}
		dbm.disconnect();
		return aCode;
	}

	public ArrayList<String> searchByAirline(String airline) {
		dbm.connect();
		ArrayList<String> flightNo = new ArrayList<String>();
		ResultSet rs = dbm
				.fetch("select acode from airport where upper(city) = upper('"
						+ airline + "')");
		try {
			while (rs.next()) {
				ResultSetMetaData metadata = rs.getMetaData();
				int numCols = metadata.getColumnCount();

				for (int i = 0; i < numCols; i++) {
					flightNo.add(rs.getString(i + 1));
				}
			}
		} catch (SQLException e) {
			System.out.println("The airline does not exist.");
			e.printStackTrace();
		}
		dbm.disconnect();
		return flightNo;
	}

	public ArrayList<HashMap<String, Object>> searchByFlightNo(int flightNo) {
		dbm.connect();

		ArrayList<HashMap<String, Object>> rows = new ArrayList<HashMap<String, Object>>();

		ResultSet rs = dbm.fetch("select * from flight where flightNo = '"
				+ flightNo + "'");

		try {
			while (rs.next()) {
				ResultSetMetaData rsmd = rs.getMetaData();
				int numCols = rsmd.getColumnCount();

				ArrayList<String> columns = new ArrayList<String>();
				for (int i = 0; i < numCols; i++) {
					columns.add(rsmd.getColumnName(i + 1));
				}

				HashMap<String, Object> row = new HashMap<String, Object>();
				for (String colName : columns) {
					Object value = rs.getObject(colName);
					row.put(colName, value);
				}
				rows.add(row);
			}
		} catch (SQLException e) {
			System.out.println("The flight does not exist.");
			e.printStackTrace();
		}
		dbm.disconnect();
		return rows;
	}

	public ArrayList<HashMap<String, Object>> searchByAirport(String aCode) {
		dbm.connect();

		ArrayList<HashMap<String, Object>> rows = new ArrayList<HashMap<String, Object>>();

		ResultSet rs = dbm
				.fetch("select * from airport where upper(acode) = upper('"
						+ aCode + "')");

		try {
			while (rs.next()) {
				ResultSetMetaData rsmd = rs.getMetaData();
				int numCols = rsmd.getColumnCount();

				ArrayList<String> columns = new ArrayList<String>();
				for (int i = 0; i < numCols; i++) {
					columns.add(rsmd.getColumnName(i + 1));
				}

				HashMap<String, Object> row = new HashMap<String, Object>();
				for (String colName : columns) {
					Object value = rs.getObject(colName);
					row.put(colName, value);
				}
				rows.add(row);
			}
		} catch (SQLException e) {
			System.out.println("The airport does not exist.");
			e.printStackTrace();
		}
		dbm.disconnect();
		return rows;
	}

	public ArrayList<String> searchByDepartureDate(String deptDate) {
		dbm.connect();
		ArrayList<String> flightNos = new ArrayList<String>();
		ResultSet rs = dbm
				.fetch("select flightNo from flight where departureDate = to_date('"
						+ deptDate + "', 'YY-MM-DD')");
		try {
			while (rs.next()) {
				ResultSetMetaData metadata = rs.getMetaData();
				int numCols = metadata.getColumnCount();

				for (int i = 0; i < numCols; i++) {
					flightNos.add(rs.getString(i + 1));
				}
			}
		} catch (SQLException e) {
			System.out.println("Invalid date.");
			e.printStackTrace();
		}
		dbm.disconnect();
		return flightNos;
	}

	public ArrayList<String> searchByDepartureTime(String deptTime) {
		dbm.connect();
		ArrayList<String> flightNos = new ArrayList<String>();
		ResultSet rs = dbm
				.fetch("select flightNo from flight where departureTime = '"
						+ deptTime + "'");
		try {
			while (rs.next()) {
				ResultSetMetaData metadata = rs.getMetaData();
				int numCols = metadata.getColumnCount();

				for (int i = 0; i < numCols; i++) {
					flightNos.add(rs.getString(i + 1));
				}
			}
		} catch (SQLException e) {
			System.out.println("Invalid time.");
			e.printStackTrace();
		}
		dbm.disconnect();
		return flightNos;
	}

	public ArrayList<String> searchByArrivalDate(String arrivalDate) {
		dbm.connect();
		ArrayList<String> flightNos = new ArrayList<String>();
		ResultSet rs = dbm
				.fetch("select flightNo from flight where arrivalDate = to_date('"
						+ arrivalDate + "', 'YY-MM-DD')");
		try {
			while (rs.next()) {
				ResultSetMetaData metadata = rs.getMetaData();
				int numCols = metadata.getColumnCount();

				for (int i = 0; i < numCols; i++) {
					flightNos.add(rs.getString(i + 1));
				}
			}
		} catch (SQLException e) {
			System.out.println("Invalid date.");
			e.printStackTrace();
		}
		dbm.disconnect();
		return flightNos;
	}

	public ArrayList<HashMap<String, Object>> searchByMinPrice() {
		dbm.connect();

		ArrayList<HashMap<String, Object>> rows = new ArrayList<HashMap<String, Object>>();

		ResultSet rs = dbm
				.fetch("select seatNo, s.flightNo, (sCost+fCost) as totalCost from seat s, flight f where s.flightNo = f.flightNo order by sCost+fCost");

		try {
			while (rs.next()) {
				ResultSetMetaData rsmd = rs.getMetaData();
				int numCols = rsmd.getColumnCount();

				ArrayList<String> columns = new ArrayList<String>();
				for (int i = 0; i < numCols; i++) {
					columns.add(rsmd.getColumnName(i + 1));
				}

				HashMap<String, Object> row = new HashMap<String, Object>();
				for (String colName : columns) {
					Object value = rs.getObject(colName);
					row.put(colName, value);
				}
				rows.add(row);
			}
		} catch (SQLException e) {
			System.out.println("Error.");
			e.printStackTrace();
		}
		dbm.disconnect();
		return rows;
	}

	public ArrayList<HashMap<String, Object>> searchByMaxPrice() {
		dbm.connect();

		ArrayList<HashMap<String, Object>> rows = new ArrayList<HashMap<String, Object>>();

		ResultSet rs = dbm
				.fetch("select seatNo, s.flightNo, (sCost+fCost) as totalCost from seat s, flight f where s.flightNo = f.flightNo order by sCost+fCost desc");

		try {
			while (rs.next()) {
				ResultSetMetaData rsmd = rs.getMetaData();
				int numCols = rsmd.getColumnCount();

				ArrayList<String> columns = new ArrayList<String>();
				for (int i = 0; i < numCols; i++) {
					columns.add(rsmd.getColumnName(i + 1));
				}

				HashMap<String, Object> row = new HashMap<String, Object>();
				for (String colName : columns) {
					Object value = rs.getObject(colName);
					row.put(colName, value);
				}
				rows.add(row);
			}
		} catch (SQLException e) {
			System.out.println("Error.");
			e.printStackTrace();
		}
		dbm.disconnect();
		return rows;
	}

	/*
	 * public ArrayList<HashMap<String, Object>>
	 * searchByDestinationMinPrice(String aCode) { dbm.connect();
	 * 
	 * ArrayList<HashMap<String, Object>> rows = new ArrayList<HashMap<String,
	 * Object>>();
	 * 
	 * ResultSet rs = dbm .fetch(
	 * "create view seatsOnFlight(seatNo, flightNo) as select seatNo, s.flightNo from seat s, flight f where s.flightNo = f.flightNo"
	 * ); ResultSet rs2 = dbm .fetch(
	 * "select seatNo, s.flightNo, (sCost+fCost) as totalCost from seatsOnFlight s, (select * from flight f2 join airportsused au on f2.departureDate=au.departureDate and f2.departureTime=au.departureTime and f2.arrivalDate=au.arrivalDate and f2.arrivalTime=au.arrivalTime) where au.destination_acode = '"
	 * + aCode + "' order by sCost+fCost");
	 * 
	 * //
	 * "select * from flight f2 join airportsused au on f2.departureDate=au.departureDate and f2.departureTime=au.departureTime and f2.arrivalDate=au.arrivalDate and f2.arrivalTime=au.arrivalTime)"
	 * try { while(rs.next()) { ResultSetMetaData rsmd = rs.getMetaData(); int
	 * numCols = rsmd.getColumnCount();
	 * 
	 * ArrayList<String> columns = new ArrayList<String>(); for (int i = 0; i <
	 * numCols; i++) { columns.add(rsmd.getColumnName(i + 1)); }
	 * 
	 * HashMap<String, Object> row = new HashMap<String, Object>(); for (String
	 * colName:columns) { Object value = rs.getObject(colName); row.put(colName,
	 * value); } rows.add(row); } } catch (SQLException e) {
	 * System.out.println("The airport does not exist."); e.printStackTrace(); }
	 * dbm.disconnect(); return rows; }
	 */
	
	public ArrayList<String> sortSeatBySeatType(String flightNo, String stName) {
		dbm.connect();
		ArrayList<String> seatNos = new ArrayList<String>();
		ResultSet rs = dbm
				.fetch("select seatNo from seat where available = 'TRUE' and flightNo = '"
						+ flightNo + "' and stName = '" + stName + "'");
		try {
			while (rs.next()) {
				ResultSetMetaData metadata = rs.getMetaData();
				int numCols = metadata.getColumnCount();

				for (int i = 0; i < numCols; i++) {
					seatNos.add(rs.getString(i + 1));
				}
			}
		} catch (SQLException e) {
			System.out.println("Invalid data.");
			e.printStackTrace();
		}
		dbm.disconnect();
		return seatNos;		
	}
	
	public ArrayList<String> checkSeatAvailabilities(String flightNo) {
		dbm.connect();
		ArrayList<String> seatNos = new ArrayList<String>();
		ResultSet rs = dbm
				.fetch("select seatNo from seat where available = 'TRUE' and flightNo = '"
						+ flightNo + "'");
		try {
			while (rs.next()) {
				ResultSetMetaData metadata = rs.getMetaData();
				int numCols = metadata.getColumnCount();

				for (int i = 0; i < numCols; i++) {
					seatNos.add(rs.getString(i + 1));
				}
			}
		} catch (SQLException e) {
			System.out.println("Invalid date.");
			e.printStackTrace();
		}
		dbm.disconnect();
		return seatNos;		
	}
	
	public ArrayList<HashMap<String, Object>> searchBySeatNo(String seatNo, String flightNo) {
		dbm.connect();

		ArrayList<HashMap<String, Object>> rows = new ArrayList<HashMap<String, Object>>();

		ResultSet rs = dbm
				.fetch("select * from seat where flightNo = '" + flightNo + "' and seatNo = '" + seatNo + "'");

		try {
			while (rs.next()) {
				ResultSetMetaData rsmd = rs.getMetaData();
				int numCols = rsmd.getColumnCount();

				ArrayList<String> columns = new ArrayList<String>();
				for (int i = 0; i < numCols; i++) {
					columns.add(rsmd.getColumnName(i + 1));
				}

				HashMap<String, Object> row = new HashMap<String, Object>();
				for (String colName : columns) {
					Object value = rs.getObject(colName);
					row.put(colName, value);
				}
				rows.add(row);
			}
		} catch (SQLException e) {
			System.out.println("The seatNo/flightNo does not exist.");
			e.printStackTrace();
		}
		dbm.disconnect();
		return rows;
	}
	
	public ArrayList<HashMap<String, Object>> searchMain(String origin, String dest, String date) {
		dbm.connect();

		ArrayList<HashMap<String, Object>> rows = new ArrayList<HashMap<String, Object>>();

		ResultSet rs = dbm
				.fetch("select * from airportsused where origin_aCode = '" + origin + "' and destination_aCode = '" + dest + "' and departureDate = to_date('"
						+ date + "', 'YY-MM-DD')");

		try {
			while (rs.next()) {
				ResultSetMetaData rsmd = rs.getMetaData();
				int numCols = rsmd.getColumnCount();

				ArrayList<String> columns = new ArrayList<String>();
				for (int i = 0; i < numCols; i++) {
					columns.add(rsmd.getColumnName(i + 1));
				}

				HashMap<String, Object> row = new HashMap<String, Object>();
				for (String colName : columns) {
					Object value = rs.getObject(colName);
					row.put(colName, value);
				}
				rows.add(row);
			}
		} catch (SQLException e) {
			System.out.println("Error.");
			e.printStackTrace();
		}
		dbm.disconnect();
		return rows;
	}
}
