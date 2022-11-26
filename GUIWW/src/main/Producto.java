package main;

public class Producto {
	private String Producto;
	private Double Precio;
	private String Tienda;
	String [] tiendas = {"Mercadona", "Carrefour", "Consum", "Masymas"};
	
	public Producto(String Producto, Double Precio, String Tienda) {
		this.Producto = Producto;
		this.Precio = Precio;
		this.Tienda = Tienda;
	}
	
	public String getProducto() {
		return Producto;
	}
	
	public Double getPrecio() {
		return Precio;
	}
	
	public String getTienda() {
		return Tienda;
	}
}
