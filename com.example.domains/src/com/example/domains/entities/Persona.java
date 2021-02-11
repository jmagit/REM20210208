package com.example.domains.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.example.domains.contracts.*;
import com.example.domains.utils.Genero;
import com.example.exceptions.CursoException;

public abstract class Persona implements Grafico {
	private int id = 1;
	private String nombre;
	private String apellidos;
	private LocalDate fechaNacimiento;
	private Genero genero = Genero.DESCONOCIDO;

	public Persona(int id, String nombre, String apellidos, LocalDate fechaNacimiento) throws CursoException {
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		setFechaNacimiento(fechaNacimiento);
	}

	public Persona(int id, String nombre, String apellidos, String fechaNacimiento) throws CursoException {
		this(id, nombre, apellidos, LocalDate.parse(fechaNacimiento, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
	}

	public int getId() {
		return id;
	}

	public void setId(int valor) {
		if (valor == id)
			return;
		if (valor <= 0) {
			// error
		}
		id = valor;
		// Notificar ...
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		// if(nombre == null)
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(int dia, int mes, int año) throws CursoException {
		setFechaNacimiento(LocalDate.of(año, mes, dia));
	}

	public void setFechaNacimiento(String fecha, String formato) throws CursoException {
//		try {
			setFechaNacimiento(LocalDate.parse(fecha, DateTimeFormatter.ofPattern(formato)));
//		} catch (Exception e) {
//			throw new CursoException("Error en la fecha", e);
//		}
	}

	public void setFechaNacimiento(String fecha) throws CursoException {
		setFechaNacimiento(fecha, "dd/MM/yyyy");
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) throws CursoException {
		if (fechaNacimiento == null)
			throw new IllegalArgumentException("Falta la fecha de nacimiento.");
		if (LocalDate.now().compareTo(fechaNacimiento) < 0)
			throw new CursoException("No puede nacer en el futuro.");
		this.fechaNacimiento = fechaNacimiento;
		this.edad = calculaEdad();
	}

	private transient int edad;

	public int getEdad() {
		return edad;
	}

	protected int calculaEdad() {
		LocalDate hoy = LocalDate.now();
		return hoy.getYear() - fechaNacimiento.getYear()
				- (hoy.getDayOfYear() < fechaNacimiento.getDayOfYear() ? 1 : 0);
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	@Override
	public String toString() {
		return "Persona [id=" + id + ", nombre=" + nombre + " " + apellidos + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Persona))
			return false;
		return id == ((Persona) obj).id;
	}

}
