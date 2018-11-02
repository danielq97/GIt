package co.edu.icesi.mio.logic.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.edu.icesi.mio.exceptions.LogicException;
import co.edu.icesi.mio.logic.ITmio1BusLogic;
import co.edu.icesi.mio.model.Tmio1Bus;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
//@Rollback(false)
public class TestBusLogic {

	private static Logger logger = LoggerFactory.getLogger(TestRutaLogic.class);

	@Autowired
	private ITmio1BusLogic buslogic;

	@Test
	public void testCrearBus() {

		assertNotNull(buslogic);

		Tmio1Bus tbus = new Tmio1Bus();

		tbus.setCapacidad(new BigDecimal(230));
		tbus.setMarca("Chev");
		tbus.setModelo(new BigDecimal(2030));
		tbus.setTipo("A");
		tbus.setPlaca("AZW456");

		try {
			buslogic.create(tbus);
		} catch (LogicException e) {
			logger.error(e.getMessage());
		}

	}
	
	@Test
	public void testModificarBus() {

		assertNotNull(buslogic);

		Tmio1Bus tbus;
		try {
			tbus = buslogic.findById(-44);
		} catch (LogicException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
