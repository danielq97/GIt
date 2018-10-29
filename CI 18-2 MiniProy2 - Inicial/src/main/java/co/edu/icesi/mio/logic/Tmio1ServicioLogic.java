package co.edu.icesi.mio.logic;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;

import org.springframework.beans.factory.annotation.Autowired;

import co.edu.icesi.mio.dao.ITmio1_Conductores_DAO;
import co.edu.icesi.mio.dao.ITmio1_Servicios_DAO;
import co.edu.icesi.mio.exceptions.LogicException;
import co.edu.icesi.mio.model.Tmio1Bus;
import co.edu.icesi.mio.model.Tmio1Conductore;
import co.edu.icesi.mio.model.Tmio1Ruta;
import co.edu.icesi.mio.model.Tmio1Servicio;
import co.edu.icesi.mio.model.Tmio1ServicioPK;

public class Tmio1ServicioLogic implements ITmio1ServicioLogic {

	@Autowired
	private ITmio1_Servicios_DAO is;
	
	@Autowired
	private ITmio1_Conductores_DAO tc;

	@PersistenceUnit
	private EntityManager em;

	@Override
	public void create(Tmio1Servicio servicio) throws LogicException {
		
		// Se valida que se ingrese un servicio
		if (servicio == null) {
			throw new LogicException("Debe ingresar un servicio");
		}
		
		// Se valida que se ingrese la llave foranea
		Tmio1ServicioPK kF = servicio.getId();
		if (kF == null) {
			throw new LogicException("Debe ingresar una llave foranea");
		}
		// Se valida que se ingrese la cédula del conductor
		if (kF.getCedulaConductor() == null || kF.getCedulaConductor().trim().equals("")) {
			throw new LogicException("Debe ingresar la cédula del coductor asignado para el servicio");
		}
		// Se valida que el conductor con la cédula pasada por parámetro exista
		Tmio1Conductore c = tc.findByCedula(em, kF.getCedulaConductor());
		if (c == null) {
			throw new LogicException("El conductor con id: "+kF.getCedulaConductor()+" no existe");
		}
		// Se valida que se ingrese 
		if (false) {
			
		}

	}

	@Override
	public void update(Tmio1Servicio servicio) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Tmio1Servicio servicio) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Tmio1Servicio> findByRangeOfDates(Calendar fechaInicio, Calendar fechaFin) throws LogicException {
		// TODO Auto-generated method stub
		return is.findByRangeOfDates(em, fechaInicio, fechaFin);
	}

	// Validar que exista el bus, y que este disponible el servicio (revisar)
	public boolean validateBus(Tmio1Bus bus, Tmio1Servicio servicio) {
		boolean r = true;
		if (bus != null) {
			List<Tmio1Servicio> servicios = bus.getTmio1Servicios();
			for (Tmio1Servicio tmio1Servicio : servicios) {
				if (tmio1Servicio.getId().getFechaInicio().getDay() == servicio.getId().getFechaInicio().getDay()) {
					r = false;
					break;

				}
			}

		}
		return r;
	}

	// Validar que exista la ruta
	public boolean validateRuta(Tmio1Ruta ruta) {

		return ruta != null;
	}

	// Validar que exista el conductor
	public boolean validateConductor(Tmio1Conductore conductor) {
		return conductor != null;
	}

	// Validar que la fecha de inicio anterior a la de fin
	public boolean validateServicio(Tmio1ServicioPK id) {
		if (id != null) {
			return id.getFechaInicio().before(id.getFechaFin());
		}
		return false;
	}

}
