package main;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

//import com.sun.tools.javac.launcher.Main;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;

public class Frame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textProduct;
	private JTextField textPrice;
	static BufferedImage checkBox = null;
	static ImageIcon checkBoxIcon = null;
	static Image dimg = null;
	private JTable tableProductos;
	JScrollPane scrollPane;
	Producto Pr;
	ArrayList<Producto> Produccion = new ArrayList<Producto>();
	String[] tiendas = { "Mercadona", "Carrefour", "Consum", "Masymas" };

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame frame = new Frame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Frame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		try {
			checkBox = ImageIO.read(Frame.class.getResource("checkbox.png"));
			dimg = checkBox.getScaledInstance(28, 29, Image.SCALE_SMOOTH);
			checkBoxIcon = new ImageIcon(dimg);
		} catch (Exception e) {
			System.out.println(e);
		}

		textProduct = new JTextField();
		textProduct.setBounds(10, 81, 86, 20);
		contentPane.add(textProduct);
		textProduct.setColumns(10);

		textPrice = new JTextField();
		textPrice.setBounds(10, 104, 86, 20);
		contentPane.add(textPrice);
		textPrice.setColumns(10);

		JLabel lblNewLabel = new JLabel("Ingresa un producto, precio y tienda");
		lblNewLabel.setBounds(10, 56, 200, 14);
		contentPane.add(lblNewLabel);

		JLabel labelCheckBox = new JLabel("");
		labelCheckBox.setBounds(194, 71, 48, 39);
		contentPane.add(labelCheckBox);

		JComboBox shopSelector = new JComboBox(tiendas);
		shopSelector.setBounds(10, 128, 86, 20);
		contentPane.add(shopSelector);

		JButton btnAnadir = new JButton("Añadir");
		btnAnadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String productoAct = textProduct.getText();
				String precioString = textPrice.getText();
				Integer indexTiendaAct = shopSelector.getSelectedIndex();
				String tiendaAct = tiendas[indexTiendaAct];
				Double precioAct = 0.0;
				try {
					precioAct = Double.parseDouble(precioString);
					labelCheckBox.setIcon(checkBoxIcon);
					Produccion.add(new Producto(productoAct, precioAct, tiendaAct));
				} catch (NumberFormatException e2) {
					System.out.println("Eror al introducir valores");
				}

				System.out.println(productoAct + "," + precioAct + "," + tiendaAct);
			}
		});
		btnAnadir.setBounds(10, 168, 86, 23);
		contentPane.add(btnAnadir);

		JButton btnMostrar = new JButton("Mostrar");
		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarDatosConTableModel();
			}
		});
		btnMostrar.setBounds(109, 168, 89, 23);
		contentPane.add(btnMostrar);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(220, 11, 254, 329);
		contentPane.add(scrollPane);

		Produccion.add(new Producto("Pollo", 12.0, "Mercadona"));
		Produccion.add(new Producto("Kiwi", 8.0, "Carrefour"));
		Produccion.add(new Producto("Manzana", 5.0, "Consum"));
	}

	private void mostrarDatosConTableModel() {
		DefaultTableModel model;
		model = new DefaultTableModel();// definimos el objeto tableModel
		tableProductos = new JTable();// creamos la instancia de la tabla
		tableProductos.setModel(model);
		model.addColumn("Producto");
		model.addColumn("Precio");
		model.addColumn("Tienda");

		tableProductos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableProductos.getTableHeader().setReorderingAllowed(false);

		for (int i = 0; i < Produccion.size(); i++) {
			model.addRow(new Object[] { Produccion.get(i).getProducto(), Produccion.get(i).getPrecio(),
					Produccion.get(i).getTienda() });
		}

		/*
		 * enviamos el objeto TableModel, como mandamos el objeto podemos manipularlo
		 * desde el metodo
		 */
		scrollPane.setViewportView(tableProductos);

		ListSelectionModel rowSelector = tableProductos.getSelectionModel();
		rowSelector.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!rowSelector.isSelectionEmpty()) {
					int selectedRow = rowSelector.getMinSelectionIndex();
					//JOptionPane rowPane = new JOptionPane();
					int rowOption = JOptionPane.showConfirmDialog(null, "¿Quieres eliminar el producto?", "Selecciona",
							JOptionPane.YES_OPTION);
					if (rowOption == 0) {
						deleteProduct(selectedRow);
					}
					System.out.println(rowOption);
				}
			}
		});

	}

	private void deleteProduct(int index) {
		Produccion.remove(index);
		mostrarDatosConTableModel();
	}

}
