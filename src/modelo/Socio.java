package modelo;

import java.io.Serializable;

public class Socio implements Serializable {
	private String numero;

	public Socio(String numero) {
		super();
		this.numero = numero;
	}

	private String dato;

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getDato() {
		return dato;
	}

	public void setDato(String dato) {
		this.dato = dato;
	}

}
