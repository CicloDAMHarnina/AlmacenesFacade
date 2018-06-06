package modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Pedido implements Serializable {

	private int numero;
	private Cliente cliente;
	private LocalDate fecha;
	ArrayList<Linea> lineas;

	public Pedido(int numero, Cliente cliente) {
		super();
		this.numero = numero;
		this.cliente = cliente;
		this.fecha = LocalDate.now();
		lineas = new ArrayList<>();
	}

	public boolean insertarLinea(Linea linea) {
		assert linea != null;
		return lineas.add(linea);
	}

	public Linea getLinea(int numero){
		assert numero>0&&numero<=lineas.size();
		return lineas.get(numero-1);
	}

	public Linea getLast(){
		return getLinea(lineas.size());
	}
	public void setCliente(Cliente cliente2) {
		this.cliente=cliente2;
	}

	public int getNumero() {
		return numero;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public ArrayList<Linea> getLineas() {
		return lineas;
	}
	
}
