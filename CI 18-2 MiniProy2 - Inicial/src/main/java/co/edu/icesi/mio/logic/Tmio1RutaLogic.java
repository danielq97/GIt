package co.edu.icesi.mio.logic;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.mio.dao.ITmio1_Rutas_DAO;
import co.edu.icesi.mio.exceptions.LogicException;
import co.edu.icesi.mio.model.Tmio1Ruta;

public class Tmio1RutaLogic implements ITmio1RutaLogic {

	@Autowired
	private ITmio1_Rutas_DAO ir;

	@PersistenceUnit
	private EntityManager em;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void create(Tmio1Ruta ruta) throws LogicException{

		// Se valida que se ingrese una ruta
		if (ruta == null) {
			throw new LogicException("Debe ingresar una ruta");
		}
		
		// Se valida que la ruta posea un id
		if (ruta.getId() == null) {
			throw new LogicException("La ruta debe tener un id");
		}
		// Se valida que el id no sea menor a 0
		if (ruta.getId() < 0) {
			throw new LogicException("El id de la ruta no puede ser menor a 0");
		}
		
		// Se valida que el número de la ruta no sea nulo
		if (ruta.getNumero() == null || ruta.getNumero().trim().equals("")) {
			throw new LogicException("La ruta debe tener un número");
		}
		// Se valida que la ruta tenga 3 caracteres
		if (ruta.getNumero().length() != 3) {
			throw new LogicException("El número de ruta debe contener 3 caracteres");
		}
		
		// Se valida que se ingrese un día de inicio
		if (ruta.getDiaInicio() == null) {
			throw new LogicException("Debe ingresar el día de inicio de la ruta");
		}
		// Se valida que se ingrese un día de inicio entre 1 y 7
		if (!Utilidades.isBetween(1, 7, ruta.getDiaInicio().intValue())) {
			throw new LogicException("Debe ingresar un día de inicio entre 1 y 7");
		}
		// Se valida que se ingrese un día de finalización
		if (ruta.getDiaFin() == null) {
			throw new LogicException("Debe ingresar el día de finalización de la ruta");
		}
		// Se valida que se ingrese un día de finalización entre 1 y 7
		if (!Utilidades.isBetween(1, 7, ruta.getDiaFin().intValue())) {
			throw new LogicException("Debe ingresar un día de finalización entre 1 y 7");
		}
		// Se valida que el dia de inicio sea menor o igual al día de finalización de la ruta
		if (ruta.getDiaInicio().compareTo(ruta.getDiaFin()) > 0) {
			throw new LogicException("El día de inicio no puede ser después del día de finalización");
		}
		
		// Se valida que se ingrese una hora de inicio
		if (ruta.getHoraInicio() == null) {
			throw new LogicException("Debe ingresar la hora de inicio de la ruta");
		}
		// Se valida que se ingrese una hora de inicio entre 1 y 1440
		if (!Utilidades.isBetween(1, 1440, ruta.getHoraInicio().intValue())) {
			throw new LogicException("Debe ingresar una hora de inicio entre 1 y 1440");
		}
		// Se valida que se ingrese una hora de finalización
		if (ruta.getHoraFin() == null) {
			throw new LogicException("Debe ingresar la hora de finalización de la ruta");
		}
		// Se valida que se ingrese una hora de finalización entre 1 y 1440
		if (!Utilidades.isBetween(1, 1440, ruta.getHoraFin().intValue())) {
			throw new LogicException("Debe ingresar una hora de finalización entre 1 y 1440");
		}
		// Se valida que la hora de inicio sea menor o igual a la hora de finalización de la ruta
		if (ruta.getHoraInicio().compareTo(ruta.getHoraFin()) > 0) {
			throw new LogicException("La hora de inicio no puede ser después de la hora de finalización de la ruta");
		}
		
		// Se valida que se ingrese si la ruta se encuentra activa o no
		if (ruta.getActiva() == null || ruta.getActiva().trim().equals("")) {
			throw new LogicException("Debe indicar si la ruta se encuentra activa o no");
		}
		// Se valida que se ingrese S o N para saber si la ruta está operando
		if (ruta.getActiva().length() != 1 || !ruta.getActiva().equals("S") || !ruta.getActiva().equals("N")) {
			throw new LogicException("Sólo puede indicar con 'S' o 'N' si la ruta está activa o no");
		}
		
		// Se valida que la ruta no exista en la base de datos
		Tmio1Ruta r = ir.findById(em, ruta.getId());
		if (r != null) {
			throw new LogicException("La ruta con id: " + ruta.getId() + " ya existe");
		}
		
		ir.save(em, ruta);
		
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(Tmio1Ruta ruta) throws LogicException {

		// Se valida que se ingrese una ruta
		if (ruta == null) {
			throw new LogicException("Debe ingresar una ruta");
		}
		
		// Se valida que la ruta posea un id
		if (ruta.getId() == null) {
			throw new LogicException("La ruta debe tener un id");
		}
		// Se valida que el id no sea menor a 0
		if (ruta.getId() < 0) {
			throw new LogicException("El id de la ruta no puede ser menor a 0");
		}
		
		// Se valida que el número de la ruta no sea nulo
		if (ruta.getNumero() == null || ruta.getNumero().trim().equals("")) {
			throw new LogicException("La ruta debe tener un número");
		}
		// Se valida que la ruta tenga 3 caracteres
		if (ruta.getNumero().length() != 3) {
			throw new LogicException("El número de ruta debe contener 3 caracteres");
		}
		
		// Se valida que se ingrese un día de inicio
		if (ruta.getDiaInicio() == null) {
			throw new LogicException("Debe ingresar el día de inicio de la ruta");
		}
		// Se valida que se ingrese un día de inicio entre 1 y 7
		if (!Utilidades.isBetween(1, 7, ruta.getDiaInicio().intValue())) {
			throw new LogicException("Debe ingresar un día de inicio entre 1 y 7");
		}
		// Se valida que se ingrese un día de fin
		if (ruta.getDiaFin() == null) {
			throw new LogicException("Debe ingresar el día de finalización de la ruta");
		}
		// Se valida que se ingrese un día de finalización entre 1 y 7
		if (!Utilidades.isBetween(1, 7, ruta.getDiaFin().intValue())) {
			throw new LogicException("Debe ingresar un día de finalización entre 1 y 7");
		}
		// Se valida que el dia de inicio sea menor o igual al día de finalización de la ruta
		if (ruta.getDiaInicio().compareTo(ruta.getDiaFin()) > 0) {
			throw new LogicException("El día de inicio no puede ser después del día de finalización");
		}
		
		// Se valida que se ingrese una hora de inicio
		if (ruta.getHoraInicio() == null) {
			throw new LogicException("Debe ingresar la hora de inicio de la ruta");
		}
		// Se valida que se ingrese una hora de inicio entre 1 y 1440
		if (!Utilidades.isBetween(1, 1440, ruta.getHoraInicio().intValue())) {
			throw new LogicException("Debe ingresar una hora de inicio entre 1 y 1440");
		}
		// Se valida que se ingrese una hora de finalización
		if (ruta.getHoraFin() == null) {
			throw new LogicException("Debe ingresar la hora de finalización de la ruta");
		}
		// Se valida que se ingrese una hora de finalización entre 1 y 1440
		if (!Utilidades.isBetween(1, 1440, ruta.getHoraFin().intValue())) {
			throw new LogicException("Debe ingresar una hora de finalización entre 1 y 1440");
		}
		// Se valida que la hora de inicio sea menor o igual a la hora de finalización de la ruta
		if (ruta.getHoraInicio().compareTo(ruta.getHoraFin()) > 0) {
			throw new LogicException("La hora de inicio no puede ser después de la hora de finalización de la ruta");
		}
		
		// Se valida que se ingrese si la ruta se encuentra activa o no
		if (ruta.getActiva() == null || ruta.getActiva().trim().equals("")) {
			throw new LogicException("Debe indicar si la ruta se encuentra activa o no");
		}
		// Se valida que se ingrese S o N para saber si la ruta está operando
		if (ruta.getActiva().length() != 1 || !ruta.getActiva().equals("S") || !ruta.getActiva().equals("N")) {
			throw new LogicException("Sólo puede indicar con 'S' o 'N' si la ruta está activa o no");
		}
		
		// Se valida que la ruta exista en la base de datos
		Tmio1Ruta r = ir.findById(em, ruta.getId());
		if (r == null) {
			throw new LogicException("La ruta con id: " + ruta.getId() + " no existe");
		}
		
		ir.update(em, ruta);
		
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Tmio1Ruta ruta) throws LogicException {

		// Se valida que se ingrese una ruta
		if (ruta == null) {
			throw new LogicException("Debe ingresar una ruta");
		}
		
		// Se valida que la ruta posea un id
		if (ruta.getId() == null) {
			throw new LogicException("La ruta debe tener un id");
		}
		// Se valida que el id no sea menor a 0
		if (ruta.getId() < 0) {
			throw new LogicException("El id de la ruta no puede ser menor a 0");
		}
		
		// Se valida que la ruta exista en la base de datos
		Tmio1Ruta r = ir.findById(em, ruta.getId());
		if (r == null) {
			throw new LogicException("La ruta con id: " + ruta.getId() + " no existe");
		}
		
		ir.delete(em, ruta);
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<Tmio1Ruta> findByRangeOfDays(BigDecimal diaInicio, BigDecimal diaFin) throws LogicException {

		// Se valida que se ingrese un día de inicio
		if (diaInicio == null) {
			throw new LogicException("Debe ingresar el día de inicio de la ruta");
		}
		// Se valida que se ingrese un día de inicio entre 1 y 7
		if (!Utilidades.isBetween(1, 7, diaInicio.intValue())) {
			throw new LogicException("Debe ingresar un día de inicio entre 1 y 7");
		}
		// Se valida que se ingrese un día de fin
		if (diaFin == null) {
			throw new LogicException("Debe ingresar el día de finalización de la ruta");
		}
		// Se valida que se ingrese un día de finalización entre 1 y 7
		if (!Utilidades.isBetween(1, 7, diaFin.intValue())) {
			throw new LogicException("Debe ingresar un día de finalización entre 1 y 7");
		}
		// Se valida que el dia de inicio sea menor o igual al día de finalización de la ruta
		if (diaInicio.compareTo(diaFin) > 0) {
			throw new LogicException("El día de inicio no puede ser después del día de finalización");
		}
		
		// Se valida que existan rutas con el rango de fechas pasado por parámetro
		List<Tmio1Ruta> lR = ir.findByRangeOfDays(em, diaInicio, diaFin);
		if (lR == null) {
			throw new LogicException("No existen rutas en el rango de fechas desde: " + diaInicio + " hasta: " + diaFin);
		}
		
		return lR;
	}

	@Override
	public Tmio1Ruta findById(Integer id) throws LogicException {

		// Se valida que se ingrese un id
		if (id == null) {
			throw new LogicException("Debe ingresar el id de una ruta");
		}
		// Se valida que el id ingresado no sea menor a 0
		if (id < 0) {
			throw new LogicException("El id de la ruta no puede ser 0");
		}
		
		// Se valida que la ruta exista en la base de datos
		Tmio1Ruta r = ir.findById(em, id);
		if (r == null) {
			throw new LogicException("La ruta con id: "+id+" no existe");
		}
		
		return r;
	}

}
