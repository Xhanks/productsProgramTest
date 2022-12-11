package sqlFiles;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Connect.Connect;
import main.Producto;

public class sqlCalls {
	static Statement stmt;
	static ResultSet rs = null;
	static Connection reg;
	static ArrayList<Producto> arrayProductosDB = new ArrayList<Producto>();
	
	/**
	 * sqlQueryCall makes a Query Call to database 'dataBase'
	 * 
	 * @param con
	 * @param what     = what you want to display (database columns)
	 * @param dataBase
	 * @throws SQLException
	 */

	public static void sqlQueryCall(Connection con, String what, String dataBase) throws SQLException {
		if (what == "")
			what = "*";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("select " + what + " from " + dataBase);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (what == "*") {
			while (rs.next()) {
				System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getDouble(3) + "  "
						+ rs.getString(4) + "  " + rs.getInt(5));
			}
		} else {
			while (rs.next()) {
				System.out.println(rs.getString(1));
			}
		}
	}

	public static void sqlUpdateProducto(Producto prd) {
		Connection reg;
		try {
			Connect conn = new Connect();
			reg = conn.getConnect();
			stmt = conn.getConnect().createStatement();
			stmt.executeUpdate("INSERT INTO " + conn.getTableName() + "(Producto, Precio, Tienda) VALUES ('"
					+ prd.getProducto() + "', " + prd.getPrecio() + ", '" + prd.getTienda() + "')");
//			System.out.println("INSERT INTO " + conn.getTableName() + "(Producto, Precio, Tienda) VALUES ('"
//					+ prd.getProducto() + "', " + prd.getPrecio() + ", '" + prd.getTienda() + "')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en subir el producto");
			e.printStackTrace();
		}
	}
	
	public static ArrayList<Producto> sqlShowProductos() {
		try {
			Connect conn = new Connect();
			reg = conn.getConnect();
			stmt = reg.createStatement();
			rs = stmt.executeQuery("SELECT Producto, Precio, Tienda FROM " + conn.getTableName());
			while(rs.next()) {
				arrayProductosDB.add(new Producto(rs.getString(1), rs.getDouble(2), rs.getString(3)));
			}
			return arrayProductosDB;
//			System.out.println("INSERT INTO " + conn.getTableName() + "(Producto, Precio, Tienda) VALUES ('"
//					+ prd.getProducto() + "', " + prd.getPrecio() + ", '" + prd.getTienda() + "')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en subir el producto");
			e.printStackTrace();
		}
		return null;
	}

	public static void sqlInsertCall(Connection con, String dataBase, String Nombre, Integer Ano, String Genero) {
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
		try {
			stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM " + dataBase + " WHERE ID = " + Num);
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void sqlDeleteRepeat(Connection con, String dataBase) {
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
