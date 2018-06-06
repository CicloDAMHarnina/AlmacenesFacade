package modelo;
import java.io.Serializable;

public class Cliente implements Serializable,Comparable<Cliente>,Indexable<String>{
	private String dni;
	private String datos;

	public Cliente(String dni, String datos) {
		super();
		this.dni = dni;
		this.datos = datos;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getDatos() {
		return datos;
	}

	public void setDatos(String datos) {
		this.datos = datos;
	}

	@Override
	public boolean equals(Object obj) {
		boolean retorno=super.equals(obj);
		if(!retorno){
			Cliente cli=(Cliente) obj;
			retorno=this.dni.equals(cli.dni);
		}
		return retorno;
	}

	@Override
	public int compareTo(Cliente o) {
		return dni.compareTo(o.dni);
	}

	@Override
	public String getKey() {
		return dni;
	}
	
}
