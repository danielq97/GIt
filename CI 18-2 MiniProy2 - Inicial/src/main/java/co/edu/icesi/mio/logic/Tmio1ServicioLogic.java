package co.edu.icesi.mio.logic;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.mio.dao.ITmio1_Servicios_DAO;
import co.edu.icesi.mio.exceptions.LogicException;
import co.edu.icesi.mio.model.Tmio1Servicio;
import co.edu.icesi.mio.model.Tmio1ServicioPK;

@Service
@Scope("singleton")
public class Tmio1ServicioLogic implements ITmio1ServicioLogic {

	@Autowired
	private ITmio1_Servicios_DAO is;

	@Autowired
	private ITmio1ConductoreLogic conductoreLogic;

	@Autowired
	private ITmio1BusLogic busLogic;

	@Autowired
	private ITmio1RutaLogic rutaLogic;

	@PersistenceContext
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

		// Se valida que exista un conductor con la cï¿½dula que posee la clave foranea
		conductoreLogic.findByCedula(kF.getCedulaConductor());
		// Se valida que el conductor del servicio exista en la base de datos
		conductoreLogic.findByCedula(servicio.getTmio1Conductore().getCedula());
		// Se valida que la cï¿½dula del conductor conincida con la cï¿½dula que posee
		// la clave foranea
		if (!kF.getCedulaConductor().equals(servicio.getTmio1Conductore().getCedula())) {
			throw new LogicException(
					"La cï¿½dula del conductor no coincide con la cï¿½dula que posee la clave foranea");
		}

		// Se valida que exista un bus con el id de la clave foranea
		busLogic.findById(kF.getIdBus());
		// Se valida que el bus asociado exista en la base de datos
		busLogic.findById(servicio.getTmio1Bus().getId());
		// Se valida que el id del bus asociado coincida con el id de bus de la clave
		// foranea
		if (servicio.getTmio1Bus().getId() != kF.getIdBus()) {
			throw new LogicException("El id del bus asociado no coincide con el id del bus de la clave foranea");
		}

		// Se valida que exista una ruta con el id de la clave foranea
		rutaLogic.findById(kF.getIdRuta());
		// Se valida que exista la ruta asociada en la base de datos
		rutaLogic.findById(servicio.getTmio1Ruta().getId());
		// Se valida que el id de la ruta asociada coincida con el id de ruta de la
		// clave foranea
		if (servicio.getTmio1Ruta().getId() != kF.getIdRuta()) {
			throw new LogicException("El id de la ruta asociada no coincide con el id de ruta de la clave foranea");
		}

		// Se valida que se ingrese la fecha de inicio del servicio
		if (kF.getFechaInicio() == null) {
			throw new LogicException("Debe ingresar una fecha de inicio del servicio");
		}
		// Se valida que se ingrese la fecha de finalizaciï¿½n del servicio
		if (kF.getFechaFin() == null) {
			throw new LogicException("Debe ingresar una fecha de finalizaciï¿½n del servicio");
		}

		// Se valida que la fecha de inicio sea antes o igual a la fecha de
		// finalizaciï¿½n del servicio
		if (!Utilidades.dateBeforeDateOrEqual(kF.getFechaInicio(), kF.getFechaFin())) {
			throw new LogicException(
					"La fecha de inicio no puede ser despuï¿½s de la fecha de finalizaciï¿½n del servicio");
		}

		// Se valida que el bus esté disponible entre las fechas de inicio y fin del
		// servicio
		if (!Utilidades.isBusAvailable(servicio.getTmio1Bus(), servicio)) {
			throw new LogicException(
					"El bus no se encuentra disponible para este servicio para las fechas establecidas");
		}

		// Se valida que el conductor esté disponible entre las fechas de inicio y fin
		// del
		// servicio
		if (!Utilidades.isConductorAvailable(servicio.getTmio1Conductore(), servicio)) {
			throw new LogicException(
					"El conductor no se encuentra disponible para este servicio para las fechas establecidas");
		}

		// Se valida que el servicio no exista en la base de datos
		Tmio1Servicio s = is.findById(em, servicio.getId());
		if (s != null) {
			throw new LogicException("El servicio ya existe");
		}

		is.save(em, servicio);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(Tmio1Servicio servicio) throws LogicException {

		// Se valida que se ingrese un servicio
		if (servicio == null) {
			throw new LogicException("Debe ingresar un servicio");
		}

		Tmio1ServicioPK kF = servicio.getId();

		// Se valida que se ingrese la llave foranea
		if (kF == null) {
			throw new LogicException("Debe ingresar una llave foranea");
		}

		// Se valida que exista un conductor con la cï¿½dula que posee la clave foranea
		conductoreLogic.findByCedula(kF.getCedulaConductor());
		// Se valida que el conductor del servicio exista en la base de datos
		conductoreLogic.findByCedula(servicio.getTmio1Conductore().getCedula());
		// Se valida que la cï¿½dula del conductor conincida con la cï¿½dula que posee
		// la clave foranea
		if (!kF.getCedulaConductor().equals(servicio.getTmio1Conductore().getCedula())) {
			throw new LogicException(
					"La cï¿½dula del conductor no coincide con la cï¿½dula que posee la clave foranea");
		}

		// Se valida que exista un bus con el id de la clave foranea
		busLogic.findById(kF.getIdBus());
		// Se valida que el bus asociado exista en la base de datos
		busLogic.findById(servicio.getTmio1Bus().getId());
		// Se valida que el id del bus asociado coincida con el id de bus de la clave
		// foranea
		if (servicio.getTmio1Bus().getId() != kF.getIdBus()) {
			throw new LogicException("El id del bus asociado no coincide con el id del bus de la clave foranea");
		}

		// Se valida que exista una ruta con el id de la clave foranea
		rutaLogic.findById(kF.getIdRuta());
		// Se valida que exista la ruta asociada en la base de datos
		rutaLogic.findById(servicio.getTmio1Ruta().getId());
		// Se valida que el id de la ruta asociada coincida con el id de ruta de la
		// clave foranea
		if (servicio.getTmio1Ruta().getId() == kF.getIdRuta()) {
			throw new LogicException("El id de la ruta asociada no coincide con el id de ruta de la clave foranea");
		}

		// Se valida que se ingrese la fecha de inicio del servicio
		if (kF.getFechaInicio() == null) {
			throw new LogicException("Debe ingresar una fecha de inicio del servicio");
		}
		// Se valida que se ingrese la fecha de finalizaciï¿½n del servicio
		if (kF.getFechaFin() == null) {
			throw new LogicException("Debe ingresar una fecha de finalizaciï¿½n del servicio");
		}

		// Se valida que la fecha de inicio sea antes o igual a la fecha de
		// finalizaciï¿½n del servicio
		if (!Utilidades.dateBeforeDateOrEqual(kF.getFechaInicio(), kF.getFechaFin())) {
			throw new LogicException(
					"La fecha de inicio no puede ser despuï¿½s de la fecha de finalizaciï¿½n del servicio");
		}

		// Se valida que el bus esté disponible entre las fechas de inicio y fin del
		// servicio
		if (!Utilidades.isBusAvailable(servicio.getTmio1Bus(), servicio)) {
			throw new LogicException(
					"El bus no se encuentra disponible para este servicio para las fechas establecidas");
		}

		// Se valida que el conductor esté disponible entre las fechas de inicio y fin
		// del
		// servicio
		if (!Utilidades.isConductorAvailable(servicio.getTmio1Conductore(), servicio)) {
			throw new LogicException(
					"El conductor no se encuentra disponible para este servicio para las fechas establecidas");
		}

		// Se valida que el servicio exista en la base de datos
		Tmio1Servicio s = is.findById(em, servicio.getId());
		if (s == null) {
			throw new LogicException("El servicio no existe");
		}

		is.update(em, servicio);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Tmio1Servicio servicio) throws LogicException {

		// Se valida que se ingrese un servicio
		if (servicio == null) {
			throw new LogicException("Debe ingresar un servicio");
		}

		Tmio1ServicioPK kF = servicio.getId();

		// Se valida que se ingrese la llave foranea
		if (kF == null) {
			throw new LogicException("Debe ingresar una llave foranea");
		}

		// Se valida que exista un conductor con la cï¿½dula que posee la clave foranea
		conductoreLogic.findByCedula(kF.getCedulaConductor());
		// Se valida que el conductor del servicio exista en la base de datos
		conductoreLogic.findByCedula(servicio.getTmio1Conductore().getCedula());
		// Se valida que la cï¿½dula del conductor conincida con la cï¿½dula que posee
		// la clave foranea
		if (!kF.getCedulaConductor().equals(servicio.getTmio1Conductore().getCedula())) {
			throw new LogicException(
					"La cï¿½dula del conductor no coincide con la cï¿½dula que posee la clave foranea");
		}

		// Se valida que exista un bus con el id de la clave foranea
		busLogic.findById(kF.getIdBus());
		// Se valida que el bus asociado exista en la base de datos
		busLogic.findById(servicio.getTmio1Bus().getId());
		// Se valida que el id del bus asociado coincida con el id de bus de la clave
		// foranea
		if (servicio.getTmio1Bus().getId() != kF.getIdBus()) {
			throw new LogicException("El id del bus asociado no coincide con el id del bus de la clave foranea");
		}

		// Se valida que exista una ruta con el id de la clave foranea
		rutaLogic.findById(kF.getIdRuta());
		// Se valida que exista la ruta asociada en la base de datos
		rutaLogic.findById(servicio.getTmio1Ruta().getId());
		// Se valida que el id de la ruta asociada coincida con el id de ruta de la
		// clave foranea
		if (servicio.getTmio1Ruta().getId() == kF.getIdRuta()) {
			throw new LogicException("El id de la ruta asociada no coincide con el id de ruta de la clave foranea");
		}

		// Se valida que el servicio exista en la base de datos
		Tmio1Servicio s = is.findById(em, servicio.getId());
		if (s == null) {
			throw new LogicException("El servicio no existe");
		}

		is.delete(em, servicio);

	}

	@Override
	@Transactional(readOnly = true)
	public List<Tmio1Servicio> findByRangeOfDates(Date fechaInicio, Date fechaFin) throws LogicException {

		// Se valida que se ingrese una fecha de inicio
		if (fechaInicio == null) {
			throw new LogicException("Debe ingresar una fecha de inicio");
		}

		// Se valida que se ingrese una fecha de finalización
		if (fechaFin == null) {
			throw new LogicException("Debe ingresar una fecha de finalización");
		}

		// Se valida que la fecha de inicio sea antes o igual a la fecha de
		// finalizaciï¿½n
		if (!Utilidades.dateBeforeDateOrEqual(fechaInicio, fechaFin)) {
			throw new LogicException(
					"La fecha de inicio no puede ser despuï¿½s de la fecha de finalizaciï¿½n del servicio");
		}

		// Se valida que existan servicios en el rango de fechas pasadas por parámetro
		List<Tmio1Servicio> lS = is.findByRangeOfDates(em, Utilidades.toCalendar(fechaInicio),
				Utilidades.toCalendar(fechaFin));
		if (lS == null) {
			throw new LogicException("No existen servicios en este rango de fechas");
		}

		return lS;
	}

}
