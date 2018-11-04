package co.edu.icesi.mio.logic.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.GregorianCalendar;
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
import co.edu.icesi.mio.logic.ITmio1ConductoreLogic;
import co.edu.icesi.mio.logic.ITmio1RutaLogic;
import co.edu.icesi.mio.logic.ITmio1ServicioLogic;
import co.edu.icesi.mio.model.Tmio1Servicio;
import co.edu.icesi.mio.model.Tmio1ServicioPK;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
public class TestServicioLogic {

	private static Logger logger = LoggerFactory.getLogger(TestServicioLogic.class);

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
			servicio.setTmio1Conductore(conductoreslogic.findByCedula("123456789"));
			// Buscar una ruta
			servicio.setTmio1Ruta(rutalogic.findById(-37));

			Tmio1ServicioPK pk = new Tmio1ServicioPK();
			pk.setCedulaConductor("123456789");
			pk.setIdBus(-43);
			pk.setIdRuta(-37);
			pk.setFechaInicio(new GregorianCalendar(2018, 8, 20).getTime());
			pk.setFechaFin(new GregorianCalendar(2018, 8, 30).getTime());

			servicio.setId(pk);

			servicioLogic.create(servicio);
			logger.info("Se creó el servicio satisfactoriamente");

			logger.info("Id: " + servicio.getId());
			logger.info("Marca bus: " + servicio.getTmio1Bus().getMarca());
			logger.info("Nombre conductor: " + servicio.getTmio1Conductore().getNombre());
			logger.info("Numero ruta: " + servicio.getTmio1Ruta().getNumero());

		} catch (LogicException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}

	}
	
	@Test
	public void testModificarServicio() {
		
		Tmio1ServicioPK pk = new Tmio1ServicioPK();
		pk.setCedulaConductor("123456789");
		pk.setIdBus(-43);
		pk.setIdRuta(-37);
		pk.setFechaInicio(new GregorianCalendar(2018, 8, 20).getTime());
		pk.setFechaFin(new GregorianCalendar(2018, 8, 30).getTime());
		


		try {
			Tmio1Servicio servicio = servicioLogic.findById(pk);
			Tmio1Servicio servicioDelete = servicio; 
			servicioLogic.delete(servicioDelete);
			
			servicio.getId().setIdBus(-34);
			servicio.setTmio1Bus(buslogic.findById(-34));
			servicio.getId().setFechaInicio(new GregorianCalendar(2018, 8, 1).getTime());

			servicioLogic.update(servicio);
			logger.info("Se actualizó el conductor satisfactoriamente");

			logger.info("Id: " + servicio.getId());
			logger.info("Marca bus: " + servicio.getTmio1Bus().getMarca());
			logger.info("Nombre conductor: " + servicio.getTmio1Conductore().getNombre());
			logger.info("Numero ruta: " + servicio.getTmio1Ruta().getNumero());

		} catch (LogicException e) {
			logger.error(e.getMessage());
		}

	}

	@Test
	public void testEliminarServicio() {
		
		Tmio1ServicioPK pk = new Tmio1ServicioPK();
		pk.setCedulaConductor("1107542311");
		pk.setIdBus(-37);
		pk.setIdRuta(-37);
		pk.setFechaInicio(new GregorianCalendar(2018, 8, 1).getTime());
		pk.setFechaFin(new GregorianCalendar(2018, 8, 2).getTime());

		try {
			Tmio1Servicio servicio = servicioLogic.findById(pk);
			servicioLogic.delete(servicio);
			
			logger.info("Se eliminó el bus satisfactoriamente");

		} catch (LogicException e) {
			logger.error(e.getMessage());
		}

	}
	
	@Test
	public void testFindByRangeOfDates() {
		
		Date fechaIni = new GregorianCalendar(2018, 8, 1).getTime();
		Date fechaFin = new GregorianCalendar(2018, 8, 30).getTime();

		try {
			List<Tmio1Servicio> lServicio = servicioLogic.findByRangeOfDates(fechaIni, fechaFin);
			
			logger.info("Los servicios encontrados desde "+fechaIni+" hasta "+fechaFin+" fueron:");
			
			printList(lServicio);

		} catch (LogicException e) {
			logger.error(e.getMessage());
		}

	}
	
	public void printList(List lista) {
		for (Object object : lista) {
			logger.info(object.toString() + "\n");
		}
	}

}