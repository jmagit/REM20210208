package com.example;

import java.util.Scanner;

public class Principal {
	private static Scanner teclado = new Scanner(System.in);
	public static String leer() {
		return teclado.nextLine();
	}

	public static void main(String[] args) {
		ejemplos1();
	}
	
	public static void ejemplos1() {
		int i = 4_000_000, j, k = 1;
		final String nombre;
		nombre = "Mundo";
		j = 5;
		i = j + k;
		i = j = k = 0;
		System.out.println("Hola " + nombre + "\nResultado: " + i);
		var x = (float)4.0;
		long larga = i;
		i = (int)larga;
		byte s = Byte.parseByte("0");
		Object o = new Principal();
		int[] tabla = new int[10]; // 0..9
		String cad[] = {"Hola", "mund" };
		cad[1] = "mundo";
		boolean cond[][] = new boolean[2][2];
		cond[0] = new boolean[10];
		var aux = cond[0];
		cond[0] = cond[1];
		cond[1] = aux;
		cond[0] = null;
		System.out.println(cad[0].charAt(1));
	}

	public void algo() {
		
	}
}
