package co.edu.icesi.mio.logic.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.edu.icesi.mio.exceptions.LogicException;
import co.edu.icesi.mio.logic.ITmio1ConductoreLogic;
import co.edu.icesi.mio.model.Tmio1Conductore;
import co.edu.icesi.mio.model.Tmio1Servicio;
import co.edu.icesi.mio.model.Tmio1ServiciosSitio;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
public class TestConductorLogic {
	
	private static Logger logger = LoggerFactory.getLogger(TestConductorLogic.class);

	@Autowired
	private ITmio1ConductoreLogic conductorLogic;

	@Test
	public void testCrearConductor() {

		assertNotNull(conductorLogic);
		
		Tmio1Conductore conductor = new Tmio1Conductore();
		
		conductor.setApellidos("Quintero");
		conductor.setCedula("1107598456");
		conductor.setFechaContratacion(new GregorianCalendar(2018, 8, 10).getTime());
		conductor.setFechaNacimiento(new GregorianCalendar(2000, 1, 1).getTime());
		conductor.setNombre("Daniel");
		conductor.setTmio1Servicios(new ArrayList<Tmio1Servicio>());
		conductor.setTmio1ServiciosSitios(new ArrayList<Tmio1ServiciosSitio>());
		
		try {
			conductorLogic.create(conductor);
			
			logger.info("Se creó el conductor satisfactoriamente");
			
			logger.info("Nombre: "+conductor.getNombre());
			logger.info("Apellido: "+conductor.getApellidos());
			logger.info("Cédula: "+conductor.getCedula());
			logger.info("Fecha de contratación: "+conductor.getFechaContratacion());
		} catch (LogicException e) {
			logger.error(e.getMessage());
		}
	
	}
	
	@Test
	public void testModificarConductor() {
		
		try {
			Tmio1Conductore conductor = conductorLogic.findByCedula("1107598456");
			
			conductor.setFechaContratacion(new GregorianCalendar(2018,9,14).getTime());
			logger.info("Fecha de contratacion" + conductor.getFechaContratacion());
			conductor.setApellidos("Asprilla");
			conductorLogic.update(conductor);
			
			
			logger.info("El conductor con cédula " + conductor.getCedula()+" se modificó correctamente");
			
		} catch (LogicException e) {
			logger.error(e.getMessage());
		}
	
	}
	
	@Test
	public void testEliminarConductor() {
		
		try {
			Tmio1Conductore conductor = conductorLogic.findByCedula("1107542316");
						
			conductorLogic.delete(conductor);
			
			logger.info("El conductor con cédula " + conductor.getCedula()+" se eliminó correctamente");
			
			
			
		} catch (LogicException e) {
			logger.error(e.getMessage());
		}
	
	}
	
	@Test
	public void testFindByName() {
		
		try {
			List<Tmio1Conductore> lConductores = conductorLogic.findByName("Daniel");
			
			logger.info("Los conductores que tiene como nombre Daniel son los siguientes:");
			
			printList(lConductores);
			
		} catch (LogicException e) {
			logger.error(e.getMessage());
		}
	
	}
	
	@Test
	public void testFindByLastName() {
		
		try {
			List<Tmio1Conductore> lConductores = conductorLogic.findByLastName("Quintero");
			
			logger.info("Los conductores que tiene apellido Quintero son los siguientes:");
			
			printList(lConductores);
		} catch (LogicException e) {
			logger.error(e.getMessage());
		}
	
	}
	
	@Test
	public void testFindByCedula() {
		
		try {
			Tmio1Conductore conductor = conductorLogic.findByCedula("1107598456");
			
			logger.info("Nombre: "+conductor.getNombre());
			logger.info("Apellido: "+conductor.getApellidos());
			logger.info("Cédula: "+conductor.getCedula());
			logger.info("Fecha de contratación: "+conductor.getFechaContratacion());
			
		} catch (LogicException e) {
			logger.error(e.getMessage());
		}
	
	}
	
	public void printList (List lista) {
		for (Object object : lista) {
			logger.info(object.toString());
		}
	}

}
