package co.edu.icesi.mio.logic.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.edu.icesi.mio.logic.ITmio1ServicioLogic;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
//@Rollback(false)
public class TestServicioLogic {
	
	private static Logger logger = LoggerFactory.getLogger(TestRutaLogic.class);
	
	@Autowired
	private ITmio1ServicioLogic servicioLogic;

	@Test
	public void test() {

		assertNotNull(servicioLogic);
		
	}

}
