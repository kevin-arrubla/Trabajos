package com.ecodeup.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table(name="productos")

public class Producto {
	
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int id;
@Column
private String nombre;
@Column 
private double precio;


public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public double getPrecio() {
	return precio;
}
public void setPrecio(double precio) {
	this.precio = precio;
}

public String toString() {
	return "Producto [id="+id+", nombre="+nombre+", precio="+precio+"]";
}
	
	

}
