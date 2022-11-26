package sqlFiles;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class sqlCalls {

	/**
	 * sqlQueryCall makes a Query Call to database 'dataBase'
	 * 
	 * @param con
	 * @param what     = what you want to display (database columns)
	 * @param dataBase
	 * @throws SQLException
	 */

	public static void sqlQueryCall(Connection con, String what, String dataBase) throws SQLException {
		Statement stmt;
		ResultSet rs = null;
		if (what == "")
			what = "*";
		try {
			stmt = con.createStatement();

			rs = stmt.executeQuery("select " + what + " from " + dataBase);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		while(rs.next()) {
//			System.out.println(rs.getString(1));
//		}

		if (what == "*") {
			while (rs.next()) {
				System.out.println(
						rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + "  " + rs.getString(4));
			}
		}
	}

	public static void sqlInsertCall(Connection con, String dataBase, String Nombre, Integer Ano, String Genero) {
		Statement stmt;

		try {
			stmt = con.createStatement();
			stmt.executeUpdate(
					"INSERT INTO " + dataBase + " VALUES ('NULL','" + Nombre + "','" + Ano + "',' " + Genero + "')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void sqlDeleteCall(Connection con, Integer Num, String dataBase) {
		Statement stmt;
		try {
			stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM " + dataBase + " WHERE ID = " + Num);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void sqlDeleteRepeat(Connection con, String dataBase) {
		Statement stmt;
		ResultSet rs;
		Integer ID = 0;
		String eliminarID = "";
		Map<String, Integer> map = new HashMap<String, Integer>();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM " + dataBase);
			while (rs.next()) {
				ID = rs.getInt(1);
				String nombreJuegoAct = rs.getString(2);
				if (map.containsKey(nombreJuegoAct)) {
					eliminarID += ", " + ID;
				}
				map.put(nombreJuegoAct, ID);
			}
			rs.close();
			System.out.println(eliminarID.substring(2));
			stmt.executeUpdate("DELETE FROM " + dataBase + " WHERE ID IN (" + eliminarID.substring(2) + ")");
		} catch (Exception e) {
			// TODO: handle exception
//			System.out.println("Eror en Delete Repeat xd");
			 e.printStackTrace();
		}

	}
}
