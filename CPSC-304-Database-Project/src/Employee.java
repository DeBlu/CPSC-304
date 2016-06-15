import java.sql.*;

public class Employee {

	DBManager dbm;

	public Employee() {
		dbm = new DBManager();
	}

	public void changeSeatType(String seatNo, String oriSN) {
		dbm.connect();
		ResultSet rs = dbm.fetch("select count(*) from seat where seatNo = '"
				+ seatNo + "'");
		int checkExist = 0;
		try {
			while (rs.next()) {
				checkExist = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("Failed to change seat type.");
			e.printStackTrace();
		}
		System.out.println("Available seat : " + checkExist);
		if (checkExist == 0) {
			System.out.println("seat " + seatNo
					+ "does not exist in the database.");
		}
		ResultSet rs1 = dbm
				.fetch("select available, stName from seat where seatNo = '"
						+ seatNo + "' AND available = 'TRUE'");
		String updatedSeatType = "";
		try {
			while (rs1.next()) {
				rs1.getString("available");
				updatedSeatType = rs1.getString("stName");
				System.out.println(updatedSeatType);
				dbm.iud("update seat set available = 'FALSE' where seatNo = '" + seatNo
						+ "'");
				dbm.iud("update seat set available = 'TRUE' where seatNo = '" + oriSN
						+ "'");
				System.out.println("Seat " + oriSN + " has been updated to "
						+ updatedSeatType + ", " + seatNo + ".");
			}
		} catch (SQLException e) {
			System.out.println("Failed to change seat type.");
			e.printStackTrace();
		}
		dbm.disconnect();
	}

	public void cancelTicket(int tID) {
		int count = 0;
		dbm.connect();
		dbm.iud("delete from ticket where tID = " + tID);
		count += 1;
		if (count != 1) {
			System.out.println("Failed to cancel Ticket:" + tID);
			return;
		}
		System.out.println("Ticket: " + tID + " has been cancelled.");
		dbm.disconnect();
	}

}
