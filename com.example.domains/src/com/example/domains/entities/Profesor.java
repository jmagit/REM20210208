package com.example.domains.entities;

import java.time.LocalDate;

import com.example.exceptions.CursoException;

public class Profesor extends Persona {
	private boolean activo = true;
	@SuppressWarnings("unused")
	private LocalDate fechaJubilacion = null;
	
	public Profesor(int id, String nombre, String apellidos, LocalDate fechaNacimiento, boolean activo) throws CursoException {
		super(id, nombre, apellidos, fechaNacimiento);
		this.activo = activo;
	}

	public Profesor(int id, String nombre, String apellidos, String fechaNacimiento) throws CursoException {
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

	@Override
	public void pintate() {
		System.out.println(toString());
	}

}
