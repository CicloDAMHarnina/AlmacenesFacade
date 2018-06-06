package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

//Acordaos de tener -ea en la run configurations
@RunWith(Suite.class)
@SuiteClasses({ AlmacenIndiceTest.class, AlmacenIndividualListTest.class, AlmacenIndividualSetTest.class,
		AlmacenMapTest.class, AlmacenRutaDestinoTest.class,almacenRutaMapeadaTest.class })
public class AllTests {

}
