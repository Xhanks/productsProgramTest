package main;

import java.util.ArrayList;

public class Producto {
	private String Producto;
	private Double Precio;
	private String Tienda;
	private Double PrecioAnterior;
	String [] tiendas = {"Mercadona", "Carrefour", "Consum", "Masymas"};
	ArrayList<Double> HistorialPrecio = new ArrayList<Double>();
	
	public Producto(String Producto, Double Precio, String Tienda) {
		this.Producto = Producto;
		this.Precio = Precio;
		this.Tienda = Tienda;
		//this.PrecioAnterior = setPrecioAnterior(Precio);
	}
	
	public String getProducto() {
		return Producto;
	}
	
	public void setProducto(String producto) {
		Producto = producto;
	}
	
	public Double getPrecio() {
		return Precio;
	}
	
	public String getTienda() {
		return Tienda;
	}
	
	public Double getPrecioAnterior() {
		return PrecioAnterior;
	}
	
	private Double setPrecioAnterior(Double Precio) {
		Integer last = HistorialPrecio.size()-1;
		return HistorialPrecio.get(last);
	}
	
}
