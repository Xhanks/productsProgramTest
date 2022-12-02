package sqlFiles;

import java.sql.*;

public class sqlTest {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/javabase";
		String user = "javacode";
		String password = "javaXDcode";
		String dataBase = "videojuegos";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, password);
			System.out.println("Connected");

			// sqlCalls.sqlDeleteCall(con, 3);
//			for (int i = 0; i < 6; i++) {
//			sqlCalls.sqlInsertCall(con, "videojuegos", "Elden Ring", 2022, "Acción");
//			}
			sqlCalls.sqlQueryCall(con, "", dataBase);
			//sqlCalls.sqlDeleteRepeat(con, dataBase);
			//sqlCalls.sqlQueryCall(con, "", dataBase);
			
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

	}

}
