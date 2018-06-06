package test;

import static org.junit.Assert.*;

import java.util.TreeMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import acceso.DAO;
import individual.AlmacenMap;
import modelo.Articulo;
import ruta.AlmacenRutaMapeada;

public class almacenRutaMapeadaTest {
	private static final String RUTARELATIVA_ARTICULOS = "data/articulos";
	private static final String ART = "art";
	private static final String ARTICULO_MAP = "articulo.map";
	AlmacenRutaMapeada<Articulo, String> instamcia;

	@Before
	public void setUp() throws Exception {
		instamcia=new AlmacenRutaMapeada<>(ART, RUTARELATIVA_ARTICULOS, ARTICULO_MAP);
		
	}

	@After
	public void tearDown() throws Exception {
		assertTrue(new DAO<>().borrar("./data/articulos/"+ARTICULO_MAP));	
		assertTrue(new DAO<>().borrar("./data/articulos/0.art"));
		assertTrue(new DAO<>().borrar("./data/articulos/1.art"));
	}

	@Test
	public void test() {
		assertNull(instamcia.obtener("uno"));
		Articulo articulo=new Articulo(instamcia.obtenNumero(),"uno","uno",1f);
		assertTrue(instamcia.grabar(articulo, articulo.getNombre(),articulo.getIdArticulo()));
		assertEquals(articulo, instamcia.obtener(articulo.getNombre()));
		articulo=new Articulo(instamcia.obtenNumero(),"dos","uno",1f);
		assertTrue(instamcia.grabar(articulo, articulo.getNombre(),articulo.getIdArticulo()));
		assertEquals(articulo, instamcia.obtener(articulo.getNombre()));
		articulo=new Articulo(1,"dos","uno",1f);
		assertFalse(instamcia.grabar(articulo, articulo.getNombre(),articulo.getIdArticulo()));
		assertEquals(2, instamcia.obtenNumero());
		assertNull(instamcia.obtener("tres"));
	}

}
