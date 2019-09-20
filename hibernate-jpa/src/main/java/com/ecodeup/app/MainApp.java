package com.ecodeup.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import com.ecodeup.model.Producto;

public class MainApp {
	public static void main(String[] args) {
// TODO Auto-generated method stub
		int opcion = 0;

		Scanner scanner = new Scanner(System.in);
		String nombre;
		double precio;
		int id;
		Producto producto;
		EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();
		while (opcion != 5) {
			String menu = "";
			menu += "1. Crear Producto\n";
			menu += "2. Buscar Producto\n";
			menu += "3. Actualizar Producto\n";
			menu += "4. Eliminar Producto\n";
			menu += "5. Salir\n";
			menu += "Elija una opción:";
			opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));
			switch (opcion) {
			case 1:
				producto = new Producto();
				producto.setId(0);
				nombre = JOptionPane.showInputDialog("ingrese el nombre del produco");
				producto.setNombre(nombre);
				precio = Double.parseDouble(JOptionPane.showInputDialog("ingrese el precio del producto"));
				producto.setPrecio(precio);
				JOptionPane.showConfirmDialog(null, producto);

				entity.getTransaction().begin();
				entity.persist(producto);
				entity.getTransaction().commit();
				JOptionPane.showConfirmDialog(null, "producto registrado");

				break;
			case 2:

				producto = new Producto();
				id = Integer.parseInt(JOptionPane.showInputDialog("ingrese el id del producto que desea buscar"));
				producto = entity.find(Producto.class, id);
				if (producto != null) {
					JOptionPane.showConfirmDialog(null, producto);

				} else {

					JOptionPane.showConfirmDialog(null, "Producto no encontrado... Lista de productos completa");

					List<Producto> listaProductos = new ArrayList<Producto>();
					Query query = entity.createQuery("SELECT p FROM Producto p");
					listaProductos = query.getResultList();
					for (Producto p : listaProductos) {
						System.out.println(p);
					}

				}
				break;
			case 3:
				producto = new Producto();
				id = Integer.parseInt(JOptionPane.showInputDialog("ingrese el id de el producto que desea actualizar"));
				producto = entity.find(Producto.class, id);
				if (producto != null) {
					JOptionPane.showConfirmDialog(null, producto);
					nombre = JOptionPane.showInputDialog("ingrese el nombre del producto");
					producto.setNombre(nombre);
					precio = Double.parseDouble(JOptionPane.showInputDialog("ingrese el precio del producto"));
					producto.setPrecio(precio);
					entity.getTransaction().begin();
					entity.merge(producto);
					entity.getTransaction().commit();
					JOptionPane.showConfirmDialog(null, "producto actualizado");

				} else {
					JOptionPane.showInternalMessageDialog(null, "producto no encontrado...");

				}
				break;
			case 4:
				producto = new Producto();
				id = Integer.parseInt(JOptionPane.showInputDialog("ingrese el id del producto que desea borrar"));
				producto = entity.find(Producto.class, id);
				if (producto != null) {
					System.out.println(producto);
					entity.getTransaction().begin();
					entity.remove(producto);
					entity.getTransaction().commit();
					JOptionPane.showConfirmDialog(null, "producto eliminado corctamente ");
				} else {
					System.out.println("Producto no encontrado...");
				}
				break;
			case 5:
				entity.close();
				JPAUtil.shutdow();
				break;
			default:
				System.out.println("Opción no válida\n");
				break;
			}
		}
	}
}
