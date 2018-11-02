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
import co.edu.icesi.mio.logic.ITmio1RutaLogic;
import co.edu.icesi.mio.model.Tmio1Ruta;
import co.edu.icesi.mio.model.Tmio1Servicio;
import co.edu.icesi.mio.model.Tmio1ServiciosSitio;
import co.edu.icesi.mio.model.Tmio1SitiosRuta;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
//@Rollback(false)
public class TestRutaLogic {
	
	private static Logger logger = LoggerFactory.getLogger(TestRutaLogic.class);
	
	@Autowired
	private ITmio1RutaLogic rutalogic;

	@Test
	public void testCrearRuta() {

		assertNotNull(rutalogic);

		Tmio1Ruta truta = new Tmio1Ruta();
		 
			truta.setActiva("S");
			truta.setDescripcion("Por la pasoancho");
			truta.setDiaFin(new BigDecimal(4));
			truta.setDiaInicio(new BigDecimal(2));
			truta.setHoraFin(new BigDecimal(12));
			truta.setHoraInicio(new BigDecimal(10));
			truta.setNumero("201");
			truta.setTmio1Servicios(new ArrayList<Tmio1Servicio>());
			
			truta.setTmio1ServiciosSitios(new ArrayList<Tmio1ServiciosSitio>());
			truta.setTmio1SitiosRutas1(new ArrayList<Tmio1SitiosRuta>());

		try {
			rutalogic.create(truta);
			
			logger.info("La ruta se cre� satisfactoriamente");
			
			logger.info("Id de ruta: "+truta.getId());
			logger.info("N�mero de Ruta: "+truta.getNumero());
			logger.info("D�a de inicio: "+truta.getDiaInicio());
			logger.info("D�a de finalizaci�n: "+truta.getDiaFin());
			logger.info("Se encuentra activa: "+truta.getActiva());
			
		} catch (LogicException e) {
			logger.error(e.getMessage());
		}

	}
	
	@Test
	public void testModificarRuta() {

		assertNotNull(rutalogic);

		try {
			Tmio1Ruta truta = rutalogic.findById(-39);
			
			truta.setDiaFin(new BigDecimal(5));
			
			rutalogic.update(truta);
			
			logger.info("La ruta se modific� satisfactoriamente");
			
			logger.info("Id de ruta: "+truta.getId());
			logger.info("N�mero de Ruta: "+truta.getNumero());
			logger.info("D�a de inicio: "+truta.getDiaInicio());
			logger.info("D�a de finalizaci�n: "+truta.getDiaFin());
			logger.info("Se encuentra activa: "+truta.getActiva());
			
		} catch (LogicException e) {
			logger.error(e.getMessage());
		}

	}

	@Test
	public void testEliminarRuta() {

		assertNotNull(rutalogic);

		try {
			Tmio1Ruta truta = rutalogic.findById(-39);
						
			rutalogic.delete(truta);
			
			logger.info("La ruta se elimin� satisfactoriamente");
			
		} catch (LogicException e) {
			logger.error(e.getMessage());
		}

	}
	
}
