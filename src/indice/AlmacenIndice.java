package indice;

import java.util.TreeMap;

import acceso.DAO;
import modelo.Indexable;

public class AlmacenIndice<T, K> {
	private String pathIndice;
	private String pathDatos;
	private TreeMap<K, Integer> indice;
	DAO<Object> dao;

	public AlmacenIndice(String pathIndice, String pathDatos) {
		super();
		this.pathIndice = pathIndice;
		this.pathDatos = pathDatos;
		assert validate();
		this.indice = new TreeMap<>();
		dao = new DAO<>();
	}

	private boolean validate() {
		return this.pathIndice != null && this.pathDatos != null;
	}

	public boolean borrar(K k){
		leerIndice();
		boolean retorno=false;
		if(indice.containsKey(k)){
			Integer posicion=indice.remove(k);
			if(posicion!=null){
				retorno=dao.borrarElemento(pathDatos,posicion);
				if(!retorno){
					leerIndice();
				}else{
					recargaIndice();
					dao.grabar(pathIndice, indice);
				}
				
			}
		}
		return retorno;
	}

	private void recargaIndice() {
		indice=new TreeMap<>();
		int posicion=0;
		T t=(T) dao.leer(pathDatos, posicion);
		while (t!=null){
			Indexable<K> elemento=(Indexable<K>) t;
			K k=elemento.getKey();
			indice.put(k, posicion);
			posicion++;
			t=(T) dao.leer(pathDatos, posicion);
		}
		
	}

	private void leerIndice() {
		indice = (TreeMap<K, Integer>) dao.leer(pathIndice);
	}
	
	public T obtener(K k) {
		leerIndice();
		if (indice == null) {
			indice = new TreeMap<>();
			dao.grabar(pathIndice, indice);
		}
		T retorno = null;
		Integer posicion = indice.get(k);
		if (posicion != null) {
			retorno = (T) dao.leer(pathDatos, posicion);
		}
		return retorno;
	}

	/**
	 * Almacen el elemnto de clase T con Clave K, hay que pasarla
	 * 
	 * @param t
	 *            el objeto a grabar
	 * @param k
	 *            la propiedad clave o indice del objeto t
	 * @return true si ha almacenado y false en caso contrario
	 */
	public boolean grabar(T t, K k) {
		boolean retorno = false;
		// miro el ultimo indice. siempre hay un mapa aqui
		Integer value = indice.size();
		// si es el primer elemento lastentry sera null
				// si al meterlo el valor es null es que NO esta repetido
		if (indice.put(k, value) == null) {
			// si se almacena bien en el archivo de datos
			if (dao.grabar(pathDatos, t, true)) {
				retorno = true;
				dao.grabar(pathIndice, indice);
			} else {
				leerIndice();
			}
		}
		return retorno;
	}
	
}
