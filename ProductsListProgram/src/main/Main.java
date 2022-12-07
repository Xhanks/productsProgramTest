package main;

import javax.swing.JFrame;

import Connect.Connect;
import guiFrame.MyFrame;
import sqlFiles.sqlCalls;

public class Main {
	public static void main(String[] args) {
		//JFrame mainFrame = MyFrame.createMyFrame();
		//mainFrame.setVisible(true);
		Producto pr = new Producto("Pollo",6.0,"Mercadona");
//		sqlCalls.sqlUpdateProducto(pr);
		Connect newConn = new Connect();
		if(newConn.getConnect() == null) System.out.println("Null");
	}

}
