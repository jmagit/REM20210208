package com.example;

import java.util.Date;

public abstract class Persona {
	private int id = 1;
	private String nombre;
	private String apellidos;
	private Date fechaNacimiento;
	
	public int getId() {
		return id;
	}
	public void setId(int valor) {
		if(valor == id) return;
		if(valor <= 0) {
			// error
		}
		id = valor;
		// Notificar ...
	}
	public int getEdad() {
		// return new Date() - fechaNacimiento;
		return 0;
	}
	
}
