package co.edu.icesi.mio.logic.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.edu.icesi.mio.logic.ITmio1BusLogic;
import co.edu.icesi.mio.model.Tmio1Bus;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
public class TestBusLogic {

	@Autowired
	private ITmio1BusLogic buslogic;

//	@Test
//	public void testCrearBus() {
//
//		assertNotNull(buslogic);
//
//		Tmio1Bus tbus = new Tmio1Bus();
//		
//		tbus.setCapacidad(new BigDecimal(230));
//		tbus.setMarca("Chev");
//		tbus.setModelo(new BigDecimal(2030));
//		tbus.setPlaca(placa);
//
//		TProgAlumno tprogalumno = new TProgAlumno();
//		tprogalumno.setSemestre("1");
//		tprogalumno.setCohorte("182");
//		tprogalumno.settbus(tbus);
//		try {
//			tprogalumno.setTPrograma(buslogic.getPrograma("08"));
//			TProgAlumnoPK tprogAlumnoPK = new TProgAlumnoPK();
//			tprogAlumnoPK.setbusCodigo("EEE");
//			tprogAlumnoPK.setPeriodoAcad("182");
//			tprogAlumnoPK.setProgramaCodigo(tprogalumno.getTPrograma().getCodigo());
//			tprogAlumnoPK.setPrincipal("S");
//			tprogalumno.setId(tprogAlumnoPK);
//
//		} catch (LogicException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		buslogic.create(bus);
//
//	}

}
