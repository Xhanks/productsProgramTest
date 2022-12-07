package Connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
	private static Connection conn;
	static String sqlName = "productosprogramdtb";
	static String sqlUserName = "ProductsAccount";
	static String sqlPassword = "Mx5PzPad5A49p4s";
	public Connect() {
		conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
//			conn = DriverManager.getConnection("jdbc:mysql://localhost/" + sqlName, sqlUserName, sqlPassword);
			
//			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(
			  "jdbc:mysql://eu-central.connect.psdb.cloud/productsprogramdtb?sslMode=VERIFY_IDENTITY",
			  "8gu68c44vqtegg27bbxq",
			  "pscale_pw_rzrvz5EHOtdo8aDXoqD7IDogdubKFeoX1d0YnYPALGJ");


			 if(conn != null) System.out.println("Conexión establecida exitosamente");
		} catch (Exception e) {
			System.out.println("No se ha podido conectar a la base de datos");
			e.printStackTrace();
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
	
//	public String getDataBase() {
//		return sqlName;
//	}
	
	public String getTableName() {
		String table = "productos";
		return table;
	}
}
