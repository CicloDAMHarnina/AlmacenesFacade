package ruta;

import java.io.File;
import java.util.TreeMap;

import acceso.DAO;
import individual.AlmacenMap;

/**
 * 
 * @author estar
 *Para saber como usar esta clase observa como se usa en su test correspondiente
 *
 * Esta clase pertmite grabar un objeto en cada fichero cuyo nombre sera un numero entero. 
 * En el mapa asociado se almacena la clave de busqueda del elemento y el numero con el 
 * que se nombra el archivo donde se almacena el elemento
 * 
 * @param <T>
 * @param <K>
 */
public class AlmacenRutaMapeada<T, K> {
	private DAO ruta;
	private AlmacenMap<K, Integer> mapa;
	private String extension;
	private String pathDatos, nombreArchivoMapa;

	/**
	 * En este constructor se definen los diferentes parametros necesario
	 * @param mapa es un mapa con clave de tipo K (la que useis en el tipo) e integet como 
	 * un numero que será el nombre que tenga el fichero que almacena un unico objeto
	 * @param extension la extension de los archivos 
	 * @param pathDatosyMapa la ruta donde se almacenarán lso archivos de indice y todos los archivos de objeto
	 * un archivo por objeto
	 * @param nombreArchivoMapa la ruta completa para llegar a cualquier archivo de este tipo de objetos
	 *
	 *
	 */
	public AlmacenRutaMapeada(String extension, String pathDatosyMapa, String nombreArchivoMapa) {
		super();
		this.pathDatos = pathDatosyMapa;
		this.extension = extension;
		this.nombreArchivoMapa = nombreArchivoMapa;
		String pathname = "./"+pathDatosyMapa ;
		assert validate();	
		File file = new File(pathname);
		if (!file.exists()) {
			file.mkdirs();
		}
		pathname=pathname+ "/" + nombreArchivoMapa;
		this.mapa = new AlmacenMap<>(new TreeMap<K,Integer>(),pathname);
		ruta = new DAO();
	}

	private boolean validate() {
		return this.pathDatos != null && extension != null;
	}

	private String estableceRuta(int v) {
		return pathDatos + "/" + String.valueOf(v) + "." + extension;
	}

	public T obtener(K k) {
		Integer v = mapa.obtener(k);
		T t = null;
		if (v != null) {
			t = (T) ruta.leer(estableceRuta(v));
		}
		return t;
	}

	public boolean grabar(T t, K k, Integer i) {
		boolean retorno = false;
		if (mapa.grabar(k, i)) {
			if (ruta.grabar(estableceRuta(i),t)) {
				retorno = true;
			} else {
				mapa.borrar(k);
			}
		}
		return retorno;
	}

	public int obtenNumero() {
		return mapa.getSize();
	}

}
