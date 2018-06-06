package modelo;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.GregorianCalendar;

public class Precio implements Serializable{

	private LocalDate fechaInicial, fechaFinal;
	private float precio;
	private boolean current = true;
	private boolean oferta;

	public Precio(float precio, boolean oferta) {
		super();
		this.fechaInicial = fechaInicial;
		this.precio = precio;
		this.oferta = oferta;
		this.fechaInicial=LocalDate.now();
	}

	public boolean isInToPeriod(GregorianCalendar fecha) {
		return isInToPeriod(toLocalDate(fecha));
	}

	// Si es el valor acxtual hoy el campo current me ayuda a no tener que
	// comprobar
	public boolean isInToPeriod(LocalDate fecha) {
		return current || fechaInicial.isBefore(fecha) && fechaFinal.isAfter(fecha);
	}

	private LocalDate toLocalDate(GregorianCalendar inicial) {
		/*
		 * Despite its name, java.util.Date represents an instant on the
		 * time-line, not a "date". The actual data stored within the object is
		 * a long count of milliseconds since 1970-01-01T00:00Z (midnight at the
		 * start of 1970 GMT/UTC).
		 */
		return inicial.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	// Otra forma ser;ia tener una referencia en el articulo al historico actual
	// (pero crea una
	// redundancia ciclica). aunque debr;ia ser el ;ultimo
	// de la lista.

	// Al poner la fecha final le estamos diciendiendo que ya no es el current
	public void setFechaFinal() {
		fechaFinal = LocalDate.now();
		current = false;
	}

	public LocalDate getFechaInicial() {
		return fechaInicial;
	}

	public LocalDate getFechaFinal() {
		return fechaFinal;
	}

	public float getPrecio() {
		return precio;
	}

	public boolean isOferta() {
		return oferta;
	}

}
