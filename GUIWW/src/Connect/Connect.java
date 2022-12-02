package Connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
	private static Connection conn;
	static String sqlName = "productosprogramdtb";
	static String sqlUserName = "ProductsAccount";
	static String sqlPassword = "nS5Mr4XTpTR5eLr";
	public Connect() {
		conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/" + sqlName, sqlUserName, sqlPassword);
			 if(conn != null) System.out.println("Conexión establecida exitosamente");
		} catch (Exception e) {
			System.out.println("No se ha podido conectar a la base de datos");
		}
	}
	public Connection getConnect() {
		return conn;
	}
	
	public void closeConnect() {
		try {
			conn.close();
			System.out.println();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
