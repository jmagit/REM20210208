package com.example;

import java.time.LocalDate;

public class Profesor extends Persona {
	private boolean activo = true;
	private LocalDate fechaJubilacion = null;
	
	public Profesor(int id, String nombre, String apellidos, LocalDate fechaNacimiento, boolean activo) {
		super(id, nombre, apellidos, fechaNacimiento);
		this.activo = activo;
	}

	public Profesor(int id, String nombre, String apellidos, String fechaNacimiento) {
		super(id, nombre, apellidos, fechaNacimiento);
	}

	public void jubilate() {
		// if(calculaEdad() <= 67)
		activo = false;
		fechaJubilacion = LocalDate.now();
	}
	
	@Override
	public String toString() {
		return "Profesor [id=" + getId() + ", nombre=" + getNombre() 
			+ " " + getApellidos() + (activo ? " En activo" : " Jubilado") + "]";
	}

}
