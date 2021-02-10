package com.example;

import java.util.Random;

/**
 * Juego de adivinar numeros
 * 
 * @author Javier
 * @version 1.0
 */
public class JuegoDelNumero {
	private static final Random RND = new Random();
	private final int NUM_MAS_ALTO;
	private final int MAX_INTENTOS;
	private int numeroBuscado = 0;
	private int intentos = 0;
	private boolean encontrado = false;
	private String resultado;

	public JuegoDelNumero() {
		MAX_INTENTOS = 10;
		NUM_MAS_ALTO = 100;
		inicializar();
	}

	public JuegoDelNumero(int intentos, int limite) {
		if(intentos < 1) throw new IllegalArgumentException("como mínimo un intento");
		if(limite < 1) throw new IllegalArgumentException("como limite mínimo el uno");
		MAX_INTENTOS = intentos;
		NUM_MAS_ALTO = limite;
		inicializar();
	}

	/**
	 * Inicializa el juego
	 */
	public void inicializar() {
		numeroBuscado = RND.nextInt(NUM_MAS_ALTO);
		intentos = 0;
		encontrado = false;
		resultado = "Pendiente de empezar";
	}

	public void jugada(String movimiento) throws JuegoException {
		if (getFinalizado()) {
			throw new JuegoException("El juego ha finalizado");
		}
		try {
			int numeroIntroducido = Integer.parseInt(movimiento);
			intentos += 1;
			if (numeroBuscado == numeroIntroducido) {
				encontrado = true;
				resultado = "Bieeen!!! Acertaste.";
			} else if (intentos >= MAX_INTENTOS) {
				resultado = "Upsss! Se acabaron los intentos, el número era el " + numeroBuscado;
			} else if (numeroBuscado > numeroIntroducido) {
				resultado = "Mi número es mayor.";
			} else {
				resultado = "Mi número es menor.";
			}
		} catch (NumberFormatException e) {
			throw new JuegoException("No es un número", e);
		}
	}

	/**
	 * Cadena con el mensaje de la ultima jugada
	 * @return mensaje
	 */
	public String getResultado() {
		return resultado;
	}

	/**
	 * Número de intentos realizados para adivinar el número
	 * @return Número de intentos consumidos
	 */
	public int getIntentos() {
		return intentos;
	}

	/**
	 * Informa si el juego ha finalizado
	 * @return true si ha finalizado
	 */
	public boolean getFinalizado() {
		return intentos >= MAX_INTENTOS || encontrado;
	}

}
