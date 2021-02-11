package com.example.domains.utils;

public class Elemento<T> {
	private T clave;
	private String valor;
	
	public Elemento(T clave, String valor) {
		this.clave = clave;
		this.valor = valor;
	}

	public T getClave() {
		return clave;
	}

	public String getValor() {
		return valor;
	}

	@Override
	public String toString() {
		return "Elemento [clave=" + clave + ", valor=" + valor + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clave == null) ? 0 : clave.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Elemento other = (Elemento) obj;
		if (clave == null) {
			if (other.clave != null)
				return false;
		} else if (!clave.equals(other.clave))
			return false;
		return true;
	}

	
}

