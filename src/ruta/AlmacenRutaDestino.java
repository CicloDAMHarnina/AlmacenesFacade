package ruta;

import java.io.File;

import acceso.DAO;

public class AlmacenRutaDestino<T> {
	private DAO daoRuta;
	private String pathInicial;
	private String extension;
	
	

	public AlmacenRutaDestino(String pathInicial,String extension) {
		super();
		this.pathInicial = pathInicial;
		this.extension=extension;
		daoRuta=new DAO<>();
	}

	/**
	 * Introduce el nombre de la carpeta destino donde se encuentran los archivos a tratar
	 * introduce el nombre del elemento sin usar la extension
	 * @param pathDestino
	 * @param nombreElemento
	 * @param t
	 * @return
	 */
	public boolean grabar(String pathDestino, String nombreElemento,T t){
		File path=new File(pathInicial+"/"+pathDestino);
		if(!path.exists()){
			path.mkdirs();
		}
		return daoRuta.grabar(pathInicial+"/"+pathDestino+"/"+nombreElemento+"."+extension,t );
	}
	

	/**
	 * Introduce el nombre de la carpeta destino donde se encuentran los archivos a tratar
	 * introduce el nombre del elemento sin usar la extension
	 * @param pathDestino
	 * @param nombreElemento
	 * @return
	 */
	public T obtener(String pathDestino,String nombreElemento){
		
		return (T) daoRuta.leer(pathInicial+"/"+pathDestino+"/"+nombreElemento+"."+extension);
	}
	
	/**
	 * Devuelve los nombres de los archivos que estan dentro de la carpeta destino sin extension 
	 * @param pathDestino
	 * @return
	 */
	public String[] getFiles(String pathDestino){
		String[] elements=null;
		File carpeta=new File(pathInicial+"/"+pathDestino);
		if(carpeta.exists()&&carpeta.isDirectory()){
			elements=carpeta.list();
			quitarExtension(elements);
		}
		return elements;
	}

	private void quitarExtension(String[] elements) {
		String[] definitivos=new String[elements.length];
		for (int i = 0; i < elements.length; i++) {
			definitivos[i]=elements[i].split("\\.")[0];
		}
	}
}
