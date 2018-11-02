package co.edu.icesi.mio.logic.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.edu.icesi.mio.exceptions.LogicException;
import co.edu.icesi.mio.logic.ITmio1ConductoreLogic;
import co.edu.icesi.mio.model.Tmio1Conductore;
import co.edu.icesi.mio.model.Tmio1Servicio;
import co.edu.icesi.mio.model.Tmio1ServiciosSitio;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
//@Rollback(false)
public class TestConductorLogic {
	
	private static Logger logger = LoggerFactory.getLogger(TestRutaLogic.class);

	@Autowired
	private ITmio1ConductoreLogic conductorLogic;

	@Test
	public void testCrearConductor() {

		assertNotNull(conductorLogic);
		
		Tmio1Conductore conductor = new Tmio1Conductore();
		
		conductor.setApellidos("Quintero");
		conductor.setCedula("1107542316");
		conductor.setFechaContratacion(new Date());
		conductor.setFechaNacimiento(new Date(2000, 1, 1));
		conductor.setNombre("Daniel");
		conductor.setTmio1Servicios(new ArrayList<Tmio1Servicio>());
		conductor.setTmio1ServiciosSitios(new ArrayList<Tmio1ServiciosSitio>());
		
		try {
			conductorLogic.create(conductor);
			
			logger.info("Se creó el conductor satisfactoriamente");
			
			logger.info("");
			logger.info("");
			logger.info("");
			logger.info("");
			logger.info("");
		} catch (LogicException e) {
			logger.error(e.getMessage());
		}
	
	}
	
	@Test
	public void testModificarConductor() {
		
		Tmio1Conductore conductor = new Tmio1Conductore();
		
		conductor.setApellidos("Quintero");
		conductor.setCedula("1107542316");
		conductor.setFechaContratacion(new Date());
		conductor.setFechaNacimiento(new Date());
		conductor.setNombre("Daniel");
		conductor.setTmio1Servicios(new ArrayList<Tmio1Servicio>());
		conductor.setTmio1ServiciosSitios(new ArrayList<Tmio1ServiciosSitio>());
		
		try {
			conductorLogic.create(conductor);
			
			logger.info("Se creó el conductor satisfactoriamente");
			
			logger.info("");
			logger.info("");
			logger.info("");
			logger.info("");
			logger.info("");
		} catch (LogicException e) {
			logger.error(e.getMessage());
		}
	
	}

}
