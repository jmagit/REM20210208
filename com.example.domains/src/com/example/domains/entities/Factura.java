package com.example.domains.entities;

import java.util.List;

import com.example.domains.contracts.Grafico;

public class Factura implements Grafico {
	public class Linea {
		int numLinea;
		
		public int getNumFactura() {
			return numFactura;
		}
	}
	
	private int numFactura;
	private Persona cliente;
	private List<Linea> lineas;
	
	@Override
	public void pintate() {
		System.out.println("Factura nº: " + numFactura);
	}
	
}
