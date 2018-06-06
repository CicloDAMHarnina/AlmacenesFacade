package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.TreeSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import acceso.DAO;
import individual.AlmacenIndividualSet;
import modelo.Cliente;

public class AlmacenIndividualSetTest {
	private static final String CLIENTES_LIST = "clientes.list";
	Cliente cliente = new Cliente("1", "uno");
	AlmacenIndividualSet<Cliente> instancia;

	@Before
	public void setUp() throws Exception {
		// para probar el assert
		try {
			instancia = new AlmacenIndividualSet<>(null, CLIENTES_LIST);
			assertNull(instancia.first());
			fail();
		} catch (AssertionError e) {
			//si falla todo correcto
		}
		instancia = new AlmacenIndividualSet<>(new TreeSet<>(), CLIENTES_LIST);
	}

	@After
	public void tearDown() throws Exception {
		new DAO<>().borrar(CLIENTES_LIST);
	}

	@Test
	public void testObtner() {
		assertNull(instancia.obtener(0));
		assertNull(instancia.first());
		assertTrue(instancia.grabar(cliente));
		assertNotNull(instancia.first());
		assertEquals(instancia.first(),instancia.last());
		assertEquals(cliente, instancia.obtener(0));
	}
	@Test
	public void testGrabar() {
		//La parametrizasda no permite otro tipo de objeto que el declarado
		//instancia.grabar(new Socio(1));
		assertTrue(instancia.grabar(cliente));
		assertFalse(instancia.grabar(cliente));
		assertEquals(instancia.first(), instancia.last());
		assertNull(instancia.obtener(1));
		try{
			//no puede grabar un objeto null
			assertTrue(instancia.grabar(null));
			fail();
		}catch(AssertionError e){
			
		}
	}
	
}
