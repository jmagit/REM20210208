package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.example.domains.contracts.Grafico;
import com.example.domains.contracts.Repository;
import com.example.domains.entities.Alumno;
import com.example.domains.entities.Factura;
import com.example.domains.entities.Persona;
import com.example.domains.entities.Profesor;
import com.example.domains.utils.DiasDeLaSemana;
import com.example.domains.utils.Elemento;
import com.example.ejecicios.Calculadora;
import com.example.ejecicios.CalculadoraException;
import com.example.ejecicios.JuegoDelNumero;
import com.example.ejecicios.JuegoException;
import com.example.exceptions.CursoException;
import com.example.infraestructure.FacturaRepositoryImp;
import com.example.infraestructure.ProfesorRepositoyImp;

public class Principal {
	private static java.util.Scanner teclado = new java.util.Scanner(System.in);

	public static String leer() {
		return teclado.nextLine();
	}

	public static void main(String[] args) throws Exception {
		// juego();
		// ejemplos3();
//		juegoExt();
//		try {
//			calcula("3+4+3,4-7*1=");
//		} catch (CalculadoraException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		ejemplos5();
	}

	public static void juego() {
		var rnd = new Random();
		int numeroBuscado = rnd.nextInt(100);
		int numeroIntroducido;
		int intentos = 0;
		boolean encontrado = false;
		do {
			System.out.print("Dame tu numero (" + (intentos + 1) + " de 10): ");
			numeroIntroducido = Integer.parseInt(leer());
			intentos += 1;
			if (numeroBuscado == numeroIntroducido) {
				encontrado = true;
			} else if (numeroBuscado > numeroIntroducido) {
				System.out.println("Mi número es mayor.");
			} else {
				System.out.println("Mi número es menor.");
			}
		} while (intentos < 10 && !encontrado);
		if (encontrado) {
			System.out.println("Bieeen!!! Acertaste.");
		} else {
			System.out.println("Upsss! Se acabaron los intentos, el número era el " + numeroBuscado);
		}
	}

	public static void juegoExt() {
		final int INTENTOS = 5;
		final int MAX = 10;
		var juego = new JuegoDelNumero(INTENTOS, MAX);
		String cad;
		while (true) {
			juego.inicializar();
			while (true) {
				System.out.print(
						"Dame un numero de 0 al " + MAX + " (" + (juego.getIntentos() + 1) + " de " + INTENTOS + "): ");
				cad = leer();
				if ("".equals(cad) || cad.toUpperCase().equals("FIN"))
					break;
				try {
					juego.jugada(cad);
					System.out.println(juego.getResultado());
					if (juego.getFinalizado())
						break;
				} catch (JuegoException e) {
					System.out.println(e.getMessage());
				}
			}
			System.out.print("¿Otra? (S/N): ");
			if (!leer().toLowerCase().equals("s"))
				break;
		}
		System.out.println("Termine!!!");
	}

	public static void Ejer2() {
		int i = 1;
		Object o = i; // new Integer(i);
		i = (int) o; // o.get()
		String s = "";
		for (int j = 0; ++j < 1000;)
			s += "X";
		StringBuilder sb = new StringBuilder("");
		for (int j = 0; ++j < 1000;)
			sb.append("X");
		s = sb.toString();

	}

	public static double calcula(String expresion) throws CalculadoraException, Exception {
		String operando = "";
		Calculadora calculadora = new Calculadora();
		for (int i = 0; i < expresion.length(); i++) {
			char ch = expresion.charAt(i);
			if (Character.isDigit(ch)) {
				operando += ch;
			} else if (ch == ',') {
				if (operando.indexOf(ch) == -1) {
					operando += ch;
				} else {
					throw new Exception("Ya existe separador decimal.");
				}
			} else if ("+-*/%=".indexOf(ch) >= 0) {
//            } else if (calculadora.isOperacion(ch)) {
				if (operando.endsWith(",")) {
					operando += "0";
				}
				double op = Double.parseDouble(operando.replace(',', '.'));
				System.out.println(operando + " " + ch);
				calculadora.calcula(op, ch);
				if (ch == '=') {
					break;
				}
				operando = "";
			} else if (ch != ' ') {
				throw new Exception("Caracter no valido.");
			}
		}
		System.out.println(calculadora.getAcumulado());
		return calculadora.getAcumulado();
	}

	public static void ejemplos5() throws Exception {
		List<Persona> lista = List.of(
				new Profesor(1, "Pepito", "Grillo", "01/01/1991"),
				new Alumno(2, "Carmelo", "Coton", "02/01/1993"),
				new Alumno(3, "Pedro", "Pica Piedra", "02/01/1987"),
				new Alumno(4, "Pablo", "Marmol", "02/01/1993"),
				new Profesor(5, "Otro", "Profesor", "01/01/1999")
				);
//		lista.stream().filter(e -> e instanceof Alumno)
//			.map(e -> e.getEdad())
//			.forEach(System.out::println);
//		lista.stream().filter(e -> e instanceof Profesor)
//			.map(e -> (Profesor)e)
//			.filter(e -> e.getApellidos().startsWith("G") && e.getNombre().endsWith("o"))
//			.peek(e -> e.jubilate())
//			.forEach(System.out::println);
		int pag = 1, rows = 3;
		lista.stream().
			sorted((a, b)-> a.getNombre().compareTo(b.getNombre()))
			.skip(pag * rows)
			.limit(rows)
			.forEach(System.out::println);
		
//		var rslt = fitra(personas, item -> item.getFechaNacimiento().getYear() == 1991);
		var rslt = lista.stream().filter(item -> item.getFechaNacimiento().getYear() == 1991)
				.collect(Collectors.toList());
		for (var item : rslt) {
			item.pintate();
		}
		
	}
	public static void ejemplos4() throws Exception {
		Grafico[] lista = { new Profesor(1, "Pepito", "Grillo", "01/01/1991"),
				new Alumno(2, "Carmelo", "Coton", "02/01/1993"), new Factura() };
		Object o = new Profesor(1, "Pepito", "Grillo", "01/01/1991");
		o = new JuegoDelNumero();

		for (var item : lista) {
			item.pintate();
		}

		if (o instanceof Grafico)
			((Grafico) o).pintate();

		Repository<Profesor, Integer> dao1 = new ProfesorRepositoyImp();
		Repository<Factura, Integer> dao2 = new FacturaRepositoryImp();
		Elemento<Integer> provincia = new Elemento<Integer>(28, "Madrid");
		Elemento<Character> genero = new Elemento<Character>('M', "Masculino");
		Elemento<String> persona = new Elemento<String>("12345678Z", "nombre apellido");
		Elemento<Persona> provincia2 = new Elemento<>(new Alumno(2, "Carmelo", "Coton", "02/01/1993"), "Alumno");
		;

		Persona[] personas = { new Profesor(1, "Pepito", "Grillo", "01/01/1991"),
				new Alumno(2, "Carmelo", "Coton", "02/01/1993"), new Alumno(3, "Pablo", "Marmol", "02/01/1993"), };
		// var rslt = fitra(personas, item -> item.getFechaNacimiento().getYear() ==
		// 1991);
		var rslt = fitra(personas, new Predicate<Persona>() {
			@Override
			public boolean test(Persona item) {
				return item.getFechaNacimiento().getYear() == 1991;
			}
		});
		for (var item : rslt) {
			item.pintate();
		}
		BiFunction<Integer, Integer, Integer> fn;
		fn = (a, b) -> {
			for (int i = a; i-- > 0;)
				b += b;
			return b;
		};
		// ...
		System.out.println(fn.apply(3, 4));
		fn = (a, b) -> a * b;
		System.out.println(fn.apply(3, 4));
		fn = Principal::suma;
		System.out.println(fn.apply(3, 4));
	}

	@Deprecated
	public static int suma(int a, int b) {
		return a + b;
	}

	public static List<Persona> fitra(Persona[] lista, Predicate<Persona> where) {
		List<Persona> rslt = new ArrayList<Persona>();
		for (var item : lista) {
			if (where.test(item))
				rslt.add(item);
		}
		return rslt;
	}

	public static void ejemplos3() {

		Persona p, p2;
		try {
			p = new Profesor(1, "Pepito", "Grillo", "01/01/1991");
			p2 = new Alumno(2, "Carmelo", "Coton", "02/01/1993");
			while (true) {
				try {
					System.out.println("Dame fecha: ");
					String cad = leer();
					p.setFechaNacimiento(cad == "" ? null : cad);
					System.out.println("Edad: " + p.getEdad());
					break;
				} catch (CursoException e) {
					System.out.println("Error: " + e.getMessage());
					// e.printStackTrace();
//				} catch (Exception e) {
////					System.out.println("Error: " + e.getMessage());
//					e.printStackTrace();
				}
			}
			p2.setFechaNacimiento((byte) 1, 2, 1989);
			if (p.equals(p2)) {

			}
			System.out.println(p);
			System.out.println("Edad: " + p.getEdad());
			((Profesor) p).jubilate();
			System.out.println(p);
			System.out.println(p2);
			System.out.println("Edad: " + p2.getEdad());
		} catch (CursoException e) {
			System.out.println("Error: " + e.getMessage());
			// e.printStackTrace();
		}
	}

	public static void ejemplos2() {
//		final byte DOMINGO = 0;
//		byte dia = 0;
//		if(dia == DOMINGO) {
//			// ...
//		}
//		// Error
//		dia = 13;

		DiasDeLaSemana elDiasDeLaSemana = DiasDeLaSemana.LUNES;
		if (elDiasDeLaSemana == DiasDeLaSemana.DOMINGO) {
			// ...
		}

		// Persona p = new Persona();
	}

	public static void ejemplos1() {
		int i = 4_000_000, j, k = 1;
		final String nombre;
		nombre = "Mundo";
		j = 5;
		i = j + k;
		i = j = k = 0;
		System.out.println("Hola " + nombre + "\nResultado: " + i);
		var x = (float) 4.0;
		long larga = i;
		i = (int) larga;
		byte s = Byte.parseByte("0");
		Object o = new Principal();
		int[] tabla = new int[10]; // 0..9
		String cad[] = { "Hola", "mund" };
		cad[1] = "mundo";
		boolean cond[][] = new boolean[2][2];
		cond[0] = new boolean[10];
		var aux = cond[0];
		cond[0] = cond[1];
		cond[1] = aux;
		cond[0] = null;
		System.out.println(cad[0].charAt(1));
		j = 0;
		switch (i) {
		case 2:
			j += 1;
			// break;
		case 1:
			j += 1;
			break;
		case 3:
		case 4:
			j = 0;
			break;
		case 10:
			j = 10;
			break;
		default:
			j = 1;
			break;
		}

		for (int ind = 0, otro = 1; ind < tabla.length; ind++, otro++) {
			tabla[ind] = ind * otro;
		}
		for (int valor : tabla) {
			// System.out.println(valor);
			valor = 0;
		}
		for (int valor : tabla) {
			System.out.println(valor);
		}
		try {
			throw new Exception();
		} catch (IndexOutOfBoundsException e) {
			// TODO: handle exception
		} catch (Exception e) {
			// TODO: handle exception
		} finally {

		}

	}

	public void algo() {

	}
}
