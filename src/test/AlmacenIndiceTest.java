package test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import indice.AlmacenIndice;
import modelo.Cliente;

public class AlmacenIndiceTest {

	private static final String CLIENTES_DAT = "clientes.dat";
	private static final String INDICE_DAT = "indice.dat";
	AlmacenIndice<Cliente, String> instancia;
	
	@Before
	public void setUp(){
		instancia=new AlmacenIndice<>(INDICE_DAT, CLIENTES_DAT);
		
	}
	
	@Test
	public void testObtener() {
		Cliente cliente=new Cliente("1", "josefo");
		instancia.grabar(cliente,cliente.getDni());
		Cliente cli2=new Cliente("6", "arturo");
		instancia.grabar(cli2, cli2.getDni());
		Cliente cli3=new Cliente("6", "arturo");
		instancia.grabar(cli3, cli3.getDni());
		assertEquals(cli2, instancia.obtener(cli2.getDni()));
		assertEquals(cli3, instancia.obtener(cli3.getDni()));
		assertNull(instancia.obtener("no"));
		Cliente cli4=new Cliente("3", "pedro");
		instancia.grabar(cli4, cli4.getDni());
	}

	@Test
	public void testGrabar() {
		Cliente cliente=new Cliente("1", "josefo");
		assertTrue(instancia.grabar(cliente,cliente.getDni()));
		Cliente cli2=new Cliente("2", "arturo");
		assertTrue(instancia.grabar(cli2, cli2.getDni()));
		Cliente cli=instancia.obtener(cliente.getDni());
		assertEquals(cli,cliente);
	}
	
	@Test 
	public void testborrar(){
		Cliente cliente=new Cliente("4", "josefo");
		assertTrue(instancia.grabar(cliente,cliente.getDni()));
		Cliente cli2=new Cliente("1", "arturo");
		assertTrue(instancia.grabar(cli2, cli2.getDni()));
		Cliente cli3=new Cliente("7", "felipe");
		assertTrue(instancia.grabar(cli3, cli3.getDni()));
		Cliente cli4=new Cliente("3", "froliam");
		assertTrue(instancia.grabar(cli4, cli4.getDni()));
//		System.out.println(instancia.obtener("3").getDatos());
		instancia.borrar("7");
//		System.out.println(instancia.obtener("4").getDatos());
//		System.out.println(instancia.obtener("1").getDatos());
		assertNotNull(instancia.obtener("3"));
//		System.out.println("ultimo"+ instancia.obtener("3").getDatos());
		
	}
	@After
	public void tearDown(){
		File file=new File(INDICE_DAT);
		file.delete();
		file=new File(CLIENTES_DAT);
		file.delete();
	}
}
