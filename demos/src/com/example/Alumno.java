package com.example;

import java.time.LocalDate;

public class Alumno extends Persona {

	public Alumno(int id, String nombre, String apellidos, LocalDate fechaNacimiento) throws CursoException {
		super(id, nombre, apellidos, fechaNacimiento);
	}

	public Alumno(int id, String nombre, String apellidos, String fechaNacimiento) throws CursoException {
		super(id, nombre, apellidos, fechaNacimiento);
	}

}
