package co.edu.icesi.mio.testlogic;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.edu.icesi.mio.exceptions.LogicException;
import co.edu.icesi.mio.logic.ITmio1BusLogic;
import co.edu.icesi.mio.logic.ITmio1RutaLogic;
import co.edu.icesi.mio.model.Tmio1Ruta;
import co.edu.icesi.mio.model.Tmio1Servicio;
import co.edu.icesi.mio.model.Tmio1ServiciosSitio;
import co.edu.icesi.mio.model.Tmio1SitiosRuta;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
public class TestRutaLogic {

	
	@Autowired
	private ITmio1RutaLogic rutalogic;

	@Test
	public void testCrearRuta() {

		assertNotNull(rutalogic);

		Tmio1Ruta truta = new Tmio1Ruta();
		
		 
			truta.setActiva("S");
			truta.setDescripcion("Por la pasoancho");
			truta.setDiaFin(new BigDecimal(20));
			truta.setDiaInicio(new BigDecimal(30));
			truta.setHoraFin(new BigDecimal(12));
			truta.setHoraInicio(new BigDecimal(10));
			truta.setNumero("201");
			truta.setTmio1Servicios(new ArrayList<Tmio1Servicio>());
			
			truta.setTmio1ServiciosSitios(new ArrayList<Tmio1ServiciosSitio>());
			truta.setTmio1SitiosRutas1(new ArrayList<Tmio1SitiosRuta>());
			

	

		try {
			rutalogic.create(truta);
		} catch (LogicException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}

	}


}
