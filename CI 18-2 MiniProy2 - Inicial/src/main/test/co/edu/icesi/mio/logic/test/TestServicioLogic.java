package co.edu.icesi.mio.logic.test;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.edu.icesi.mio.exceptions.LogicException;
import co.edu.icesi.mio.logic.ITmio1BusLogic;
import co.edu.icesi.mio.logic.ITmio1ConductoreLogic;
import co.edu.icesi.mio.logic.ITmio1RutaLogic;
import co.edu.icesi.mio.logic.ITmio1ServicioLogic;
import co.edu.icesi.mio.model.Tmio1Bus;
import co.edu.icesi.mio.model.Tmio1Conductore;
import co.edu.icesi.mio.model.Tmio1Ruta;
import co.edu.icesi.mio.model.Tmio1Servicio;
import co.edu.icesi.mio.model.Tmio1ServicioPK;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
// @Rollback(false)
public class TestServicioLogic {

	private static Logger logger = LoggerFactory.getLogger(TestRutaLogic.class);

	@Autowired
	private ITmio1ServicioLogic servicioLogic;

	@Autowired
	private ITmio1BusLogic buslogic;

	@Autowired
	private ITmio1RutaLogic rutalogic;

	@Autowired
	private ITmio1ConductoreLogic conductoreslogic;

	@Test
	public void testCrearServicio() {

		assertNotNull(servicioLogic);
		assertNotNull(buslogic);
		assertNotNull(rutalogic);
		assertNotNull(conductoreslogic);

		Tmio1Servicio servicio = new Tmio1Servicio();

		try {
			// BUscar un bus para insertar
			servicio.setTmio1Bus(buslogic.findById(-43));
			// Buscar un conductor
			servicio.setTmio1Conductore(conductoreslogic.findByCedula("1107542311"));
			// Buscar una ruta
			servicio.setTmio1Ruta(rutalogic.findById(-37));

			Tmio1ServicioPK pk = new Tmio1ServicioPK();
			pk.setCedulaConductor("1107542311");
			pk.setIdBus(-43);
			pk.setIdRuta(-37);
			pk.setFechaInicio(new GregorianCalendar(2018, 8, 10).getTime());
			pk.setFechaFin(new GregorianCalendar(2018, 8, 24).getTime());

			servicio.setId(pk);

			servicioLogic.create(servicio);
			logger.info("Se cre� el conductor satisfactoriamente");

			logger.info("Id: " + servicio.getId());
			logger.info("Marca bus: " + servicio.getTmio1Bus().getMarca());
			logger.info("Nombre conductor: " + servicio.getTmio1Conductore().getNombre());
			logger.info("Numero ruta: " + servicio.getTmio1Ruta().getNumero());

		} catch (LogicException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}

	}

}