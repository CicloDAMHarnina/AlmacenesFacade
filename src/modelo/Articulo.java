package modelo;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;

public class Articulo implements Serializable,Indexable<String>{

	private int idArticulo;
	private String nombre;
	private String descripcion;
	private LinkedList<Precio> precios;

	public Articulo(int idArticulo, String nombre, String descripcion, float precio) {
		super();
		this.idArticulo = idArticulo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		precios = new LinkedList<>();
		precios.add(new Precio(precio, false));
	}

	public float getOldPrice(GregorianCalendar instant) {
		Iterator<Precio> iterator = precios.iterator();
		boolean encontrado = false;
		float precio = 0;
		while (iterator.hasNext() && !encontrado) {
			Precio next = iterator.next();
			if (next.isInToPeriod(instant)) {
				encontrado = true;
				precio = next.getPrecio();
			}
			;
		}
		return precio;
	}

	@Override
	public boolean equals(Object obj) {
		Articulo elemento=(Articulo)obj;
		boolean retorno=super.equals(elemento);
		if(!retorno){
			retorno=idArticulo==elemento.getIdArticulo();
		}
		return retorno;
	}
	public void insertarNuevoPrecio(float nuevoPrecio, boolean oferta) {
		assert nuevoPrecio > 0;
		precios.getLast().setFechaFinal();
		precios.addLast(new Precio(nuevoPrecio, oferta));
	}

	public float getCurrentPrice() {
		return precios.getLast().getPrecio();
	}

	public int getIdArticulo() {
		return idArticulo;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	@Override
	public String getKey() {
		return nombre;
	}


}
