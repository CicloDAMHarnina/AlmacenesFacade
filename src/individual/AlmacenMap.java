package individual;

import java.util.Map;

import acceso.DAO;

public class AlmacenMap<K, V> {
	private Map<K, V> mapa;
	private String rutaMapa;
	private DAO<Map<K, V>> dao;

	public AlmacenMap(Map<K, V> mapa, String rutaMapa) {
		super();
		this.mapa = mapa;
		this.rutaMapa = rutaMapa;
		dao = new DAO<>();
		getMapa();
	}

	public V obtener(K k) {
		getMapa();
		V v = null;
		try {
			v = mapa.get(k);
		} catch (NullPointerException e) {
			System.out.println("no existe");
		}
		return v;
	}

	public boolean grabar(K k, V v) {
		boolean retorno = false;
		getMapa();
		if (mapa.put(k, v) == null && dao.grabar(rutaMapa, mapa)) {
			retorno = true;
		}
		getMapa();
		return retorno;
	}

	private void getMapa() {
		Map<K, V> temporal = dao.leer(rutaMapa);
		if (temporal == null) {
			dao.grabar(rutaMapa, mapa);
		} else {
			mapa = temporal;
		}
	}

	public String getRutaMapa() {
		return rutaMapa;
	}

	public int getSize() {
		return mapa.size();
	}

	public boolean borrar(K k) {
		boolean retorno = true;
		if (mapa.remove(k) == null)
			retorno = false;
		return retorno;
	}
}
