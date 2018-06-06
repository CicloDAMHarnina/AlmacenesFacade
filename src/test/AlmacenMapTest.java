package test;

import static org.junit.Assert.*;

import java.util.TreeMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import acceso.DAO;
import individual.AlmacenMap;
import modelo.Cliente;

public class AlmacenMapTest {
	private static final String MAPA_MAP = "mapa.map";
	AlmacenMap<String, String> mapa;
	Cliente Cliente=new Cliente("1", "uno");

	@Before
	public void setUp() throws Exception {
		mapa=new AlmacenMap<>(new TreeMap<>(), MAPA_MAP);
		Cliente Cliente2=new Cliente("1", "uno");
		Cliente Cliente3=new Cliente("2", "uno");
		Cliente Cliente4=new Cliente("3", "uno");
		Cliente Cliente5=new Cliente("4", "uno");
		
	}

	@After
	public void tearDown() throws Exception {
		new DAO<>().borrar(MAPA_MAP);
	}

	@Test
	public void test() {
		assertTrue(mapa.grabar(Cliente.getDni(), Cliente.getDatos()));
		Cliente Cliente2=new Cliente("2", "uno");
		Cliente Cliente3=new Cliente("3", "uno");
		Cliente Cliente4=new Cliente("4", "uno");
		Cliente Cliente5=new Cliente("1", "uno");
		assertTrue(mapa.grabar(Cliente2.getDni(), Cliente2.getDatos()));
		assertTrue(mapa.grabar(Cliente3.getDni(), Cliente3.getDatos()));
		assertTrue(mapa.grabar(Cliente4.getDni(), Cliente4.getDatos()));
		assertFalse(mapa.grabar(Cliente5.getDni(), Cliente5.getDatos()));
		assertEquals(Cliente2.getDatos(), mapa.obtener(Cliente2.getDni()));
	}

}
