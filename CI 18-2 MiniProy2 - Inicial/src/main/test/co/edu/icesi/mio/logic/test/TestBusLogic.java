package co.edu.icesi.mio.logic.test;

import java.math.BigDecimal;
import java.util.List;

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

		Tmio1Bus tbus = new Tmio1Bus();

		tbus.setCapacidad(new BigDecimal(230));
		tbus.setMarca("Ferrari");
		tbus.setModelo(new BigDecimal(2040));
		tbus.setTipo("T");
		tbus.setPlaca("AZW447");

		try {
			buslogic.create(tbus);
		} catch (LogicException e) {
			logger.error(e.getMessage());
		}

	}

	@Test
	public void testModificarBus() {

		try {
			Tmio1Bus tbus = buslogic.findById(-40);
			tbus.setCapacidad(new BigDecimal(23));
			buslogic.update(tbus);
			logger.info("El bus con placa " + tbus.getPlaca() + " se modificó correctamente");
		} catch (LogicException e) {
			logger.error(e.getMessage());
		}
	}

	@Test
	public void testEliminarBus() {

		try {
			Tmio1Bus tbus = buslogic.findById(-33);
			buslogic.delete(tbus);
			logger.info("El bus con placa " + tbus.getPlaca() + " se eliminó correctamente");
		} catch (LogicException e) {
			logger.error(e.getMessage());
		}
	}

	@Test
	public void testFindByModel() {

		try {
			List<Tmio1Bus> tbus = buslogic.findByModel(new BigDecimal(2030));

			printList(tbus);
		} catch (LogicException e) {
			logger.error(e.getMessage());
		}
	}

	@Test
	public void testFindByType() {

		try {
			List<Tmio1Bus> tbus = buslogic.findByType("A");

			printList(tbus);
		} catch (LogicException e) {
			logger.error(e.getMessage());
		}
	}

	@Test
	public void testFindByCapacity() {

		try {
			List<Tmio1Bus> tbus = buslogic.findByCapacity(new BigDecimal(23));

			printList(tbus);
		} catch (LogicException e) {
			logger.error(e.getMessage());
		}
	}

	public void printList(List lista) {
		logger.info("Lista");
		for (Object object : lista) {
			logger.info(object.toString() + "\n");
		}
	}

}
