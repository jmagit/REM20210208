package com.example.domains.entities;

import java.time.LocalDate;

import com.example.exceptions.CursoException;

public class Alumno extends Persona {

	public Alumno(int id, String nombre, String apellidos, LocalDate fechaNacimiento) throws CursoException {
		super(id, nombre, apellidos, fechaNacimiento);
	}

	public Alumno(int id, String nombre, String apellidos, String fechaNacimiento) throws CursoException {
		super(id, nombre, apellidos, fechaNacimiento);
	}

	@Override
	public void pintate() {
		System.out.println("Soy el alumno: " + getNombre() + " " + getApellidos());
	}

}
