package co.edu.icesi.mio.logic;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
	private ITmio1ConductoreLogic conductoreLogic;
	
	@Autowired
	private ITmio1BusLogic busLogic;

	private ITmio1RutaLogic rutaLogic;
	
	@PersistenceUnit
	private EntityManager em;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void create(Tmio1Servicio servicio) throws LogicException {
		
		// Se valida que se ingrese un servicio
		if (servicio == null) {
			throw new LogicException("Debe ingresar un servicio");
		}
		
		Tmio1ServicioPK kF = servicio.getId();
		
		// Se valida que se ingrese la llave foranea
		if (kF == null) {
			throw new LogicException("Debe ingresar una llave foranea");
		}
		
		// Se valida que exista un conductor con la c�dula que posee la clave foranea
		conductoreLogic.findByCedula(kF.getCedulaConductor());
		// Se valida que el conductor del servicio exista en la base de datos
		conductoreLogic.findByCedula(servicio.getTmio1Conductore().getCedula());
		// Se valida que la c�dula del conductor conincida con la c�dula que posee la clave foranea
		if (kF.getCedulaConductor().equals(servicio.getTmio1Conductore().getCedula())) {
			throw new LogicException("La c�dula del conductor no coincide con la c�dula que posee la clave foranea");
		}
		
		// TODO: c�mo valido que los buses existan y que las rutas tambi�n si sus respectivas clases de la l�gica no poseen un m�todo para hacer la consulta por su id??
		
		// Se valida que se ingrese la fecha de inicio del servicio
		if (kF.getFechaInicio() == null) {
			throw new LogicException("Debe ingresar una fecha de inicio del servicio");
		}
		// Se valida que se ingrese la fecha de finalizaci�n del servicio
		if (kF.getFechaFin() == null) {
			throw new LogicException("Debe ingresar una fecha de finalizaci�n del servicio");
		}
		
		// Se valida que la fecha de inicio sea antes que la fecha de finalizaci�n del servicio
		if (!Utilidades.dateBeforeDateOrEqual(kF.getFechaInicio(), kF.getFechaFin())) {
			throw new LogicException("La fecha de inicio no puede ser despu�s de la fecha de finalizaci�n del servicio");
		}
		
		// Se valida que el bus est� disponible
		if (false) {
			
		}

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(Tmio1Servicio servicio) {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Tmio1Servicio servicio) {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional(readOnly = true)
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
