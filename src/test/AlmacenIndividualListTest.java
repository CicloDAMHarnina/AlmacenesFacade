package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import acceso.DAO;
import individual.AlmacenIndividualList;
import modelo.Cliente;
import modelo.Socio;

public class AlmacenIndividualListTest {
	private static final String CLIENTES_LIST = "clientes.list";
	Cliente cliente = new Cliente("1", "uno");
	AlmacenIndividualList<Cliente> instancia;

	@Before
	public void setUp() throws Exception {
		// para probar el assert
		try {
			instancia = new AlmacenIndividualList<>(null, CLIENTES_LIST);
			assertNull(instancia.obtener(0));
			fail();
		} catch (AssertionError e) {
			//si falla todo correcto
		}
		instancia = new AlmacenIndividualList<>(new ArrayList<>(), CLIENTES_LIST);
	}

	@After
	public void tearDown() throws Exception {
		new DAO<>().borrar(CLIENTES_LIST);
	}

	@Test
	public void testObtner() {
		assertNull(instancia.obtener(0));
		assertTrue(instancia.grabar(cliente));
		assertNotNull(instancia.obtener(0));
		assertNull(instancia.obtener(1));
	}
	@Test
	public void testGrabar() {
		//La parametrizasda no permite otro tipo de objeto que el declarado
		//instancia.grabar(new Socio(1));
		assertTrue(instancia.grabar(cliente));
		assertTrue(instancia.grabar(cliente));
		assertEquals(instancia.obtener(0), instancia.obtener(1));
		try{
			//no puede grabar un objeto null
			assertTrue(instancia.grabar(null));
			fail();
		}catch(AssertionError e){
			
		}
	}
	
}
