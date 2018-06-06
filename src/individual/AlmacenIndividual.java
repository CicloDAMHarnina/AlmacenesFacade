package individual;

import acceso.DAO;

public class AlmacenIndividual <T>{

	public T obtener(String path){
		return new DAO<T>().leer(path);
			
	}
	public boolean grabar(String path,T t){
		return new DAO<T>().grabar(path, t);
	}
}
