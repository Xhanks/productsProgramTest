package guiFrame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import Connect.Connect;
import main.Producto;
import sqlFiles.sqlCalls;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Point;
import javax.swing.JComboBox;

public class MyFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int xMouse, yMouse;
	private JTextField txtProducto;
	private JTextField txtPrecio;
	private JComboBox listaTiendas;
	private JTable table;
	String [] tiendas = {"Mercadona", "Carrefour", "Consum", "Masymas"};
	ArrayList<Producto> arrayProductos = new ArrayList<Producto>();
	Connect conn;
	Connection reg;
	Statement stmt;
	ResultSet rs = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyFrame frame = new MyFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static MyFrame createMyFrame() {
		MyFrame frame = new MyFrame();
		frame.setVisible(true);
		return frame;
	}

	/**
	 * Create the frame.
	 */
	public MyFrame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		getContentPane().setLayout(null);
		setUndecorated(true);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 600, 500);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel AnadirProducto = new JLabel("Añadir");
		AnadirProducto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				AnadirProducto.setBackground(new Color(225, 141, 0));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				AnadirProducto.setBackground(Color.ORANGE);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				String productoAct = txtProducto.getText();
				String precioString = txtPrecio.getText();
				Integer indexTiendaAct = listaTiendas.getSelectedIndex();
				String tiendaAct = tiendas[indexTiendaAct];
				Double precioAct = 0.0;
				try {
					precioAct = Double.parseDouble(precioString);
					arrayProductos.add(new Producto(productoAct, precioAct, tiendaAct));
					sqlCalls.sqlUpdateProducto(new Producto(productoAct, precioAct, tiendaAct));
				} catch (NumberFormatException e2) {
					System.out.println("Eror al introducir valores");
				}
				System.out.println(productoAct + "," + precioAct + "," + tiendaAct);
			}
		});
		AnadirProducto.setBounds(27, 232, 63, 33);
		AnadirProducto.setOpaque(true);
		AnadirProducto.setHorizontalAlignment(SwingConstants.CENTER);
		AnadirProducto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		AnadirProducto.setBackground(Color.ORANGE);
		panel.add(AnadirProducto);
		
		JLabel MostrarProductos = new JLabel("Mostrar");
		MostrarProductos.setBounds(122, 232, 63, 33);
		MostrarProductos.setOpaque(true);
		MostrarProductos.setHorizontalAlignment(SwingConstants.CENTER);
		MostrarProductos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		MostrarProductos.setBackground(Color.CYAN);
		panel.add(MostrarProductos);
		
		txtProducto = new JTextField();
		txtProducto.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				txtProducto.setText("");
			}
		});
		txtProducto.setBounds(27, 92, 106, 21);
		txtProducto.setText("Producto");
		txtProducto.setForeground(Color.GRAY);
		txtProducto.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtProducto.setColumns(10);
		panel.add(txtProducto);
		
		txtPrecio = new JTextField();
		txtPrecio.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				txtPrecio.setText("");
			}
		});
		txtPrecio.setBounds(27, 124, 106, 21);
		txtPrecio.setText("Precio");
		txtPrecio.setForeground(Color.GRAY);
		txtPrecio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtPrecio.setColumns(10);
		panel.add(txtPrecio);
		
		JPanel header = new JPanel();
		HeaderDragListener headerDragListener = new HeaderDragListener(this);
		this.addMouseListener(headerDragListener);
		this.addMouseMotionListener(headerDragListener);
		header.setBounds(0, 0, 600, 26);
		header.setLayout(null);
		header.setBackground(Color.GRAY);
		panel.add(header);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(570, 0, 30, 26);
		header.add(panel_1);
		
		JLabel panelClose = new JLabel("x");
		panelClose.setBounds(0, 0, 30, 26);
		panel_1.add(panelClose);
		panelClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		panelClose.setHorizontalAlignment(SwingConstants.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(242, 37, 348, 452);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		listaTiendas = new JComboBox(tiendas);
		listaTiendas.setBounds(27, 156, 103, 21);
		panel.add(listaTiendas);
	}

	public static class HeaderDragListener extends MouseAdapter {

        private final JFrame frame;
        private Point mouseDownCompCoords = null;

        public HeaderDragListener(JFrame frame) {
            this.frame = frame;
        }

        public void mouseReleased(MouseEvent e) {
            mouseDownCompCoords = null;
        }

        public void mousePressed(MouseEvent e) {
            mouseDownCompCoords = e.getPoint();
        }

        public void mouseDragged(MouseEvent e) {
            Point currCoords = e.getLocationOnScreen();
            frame.setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y - mouseDownCompCoords.y);
        }
    }
}
