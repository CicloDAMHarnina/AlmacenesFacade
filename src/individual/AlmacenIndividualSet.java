package individual;

import java.util.NavigableSet;

import acceso.DAO;

public class AlmacenIndividualSet<T> {
	private NavigableSet<T> conjunto;
	private String path;
	private DAO<NavigableSet<T>> dao;

	public AlmacenIndividualSet(NavigableSet<T> set, String path) {
		super();
		assert set!=null&& path!=null;
		this.conjunto = set;
		this.path = path;
		dao = new DAO<>();
	}

	public T first() {
		getSet();
		T retorno=null;
		try{
			retorno=conjunto.first();
		}catch(Exception e){
			
		}
		return  retorno;
	}
	public T last(){
		getSet();
		return conjunto.last();
	}
	
	public T obtener(int index){
		assert index>=0;
		T t=null;
		getSet();
		if(index<conjunto.size()){
			t=(T) conjunto.toArray()[index];
		}
		return t;
	}

	public boolean grabar(T t) {
		assert t!=null;
		boolean retorno = false;
		getSet();
		if (conjunto.add(t) && dao.grabar(path, conjunto)) {
			retorno = true;
		}
		getSet();
		return retorno;
	}

	private void getSet() {
		NavigableSet<T> temporal = dao.leer(path);
		if (temporal == null) {			
			dao.grabar(path, conjunto);
		}else{
			conjunto=temporal;
		}
	}
}
