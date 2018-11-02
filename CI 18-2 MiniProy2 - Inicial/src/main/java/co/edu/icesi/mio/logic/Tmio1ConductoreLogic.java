package co.edu.icesi.mio.logic;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.mio.dao.ITmio1_Conductores_DAO;
import co.edu.icesi.mio.exceptions.LogicException;
import co.edu.icesi.mio.model.Tmio1Conductore;

@Service
@Scope("singleton")
public class Tmio1ConductoreLogic implements ITmio1ConductoreLogic {

	@Autowired
	private ITmio1_Conductores_DAO tc;

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void create(Tmio1Conductore conductor) throws LogicException {

		// Se valida que se ingrese un conductor
		if (conductor == null) {
			throw new LogicException("Debe ingresar un conductor");
		}

		// Se valida que se ingrese una cédula
		if (conductor.getCedula() == null || conductor.getCedula().trim().equals("")) {
			throw new LogicException("Debe ingresar una cédula");
		}
		// Se valida que la cédula sea numérica
		if (!Utilidades.isNumeric(conductor.getCedula())) {
			throw new LogicException("La cédula debe ser de tipo numérica");
		}

		// Se valida que el conductor tenga nombre
		if (conductor.getNombre() == null || conductor.getNombre().trim().equals("")) {
			throw new LogicException("Debe ingresar el nombre del conductor");
		}
		// Se valida que el nombre contenga al menos 3 caracteres
		if (conductor.getNombre().length() < 3) {
			throw new LogicException("El nombre del conductor debe contener al menos 3 caracteres");
		}

		// Se valida que se ingrese el apellido del conductor
		if (conductor.getApellidos() == null || conductor.getApellidos().trim().equals("")) {
			throw new LogicException("Debe ingresar el apellido del conductor");
		}
		// Se valida que el apellido sea almenos de 3 caracteres
		if (conductor.getApellidos().length() < 3) {
			throw new LogicException("El apellido del conductor debe contener al menos 3 caracteres");
		}

		// Se valida que se ingrese la fecha de nacimiento del conductor
		if (conductor.getFechaNacimiento() == null) {
			throw new LogicException("Debe ingresar la fecha de nacimiento del conductor");
		}
		// Se valida que el conductor sea mayor de edad
		if (!Utilidades.isLegalAge(conductor.getFechaNacimiento())) {
			throw new LogicException("El conductor debe ser mayor de edad");
		}

		// Se valida que se ingrese una fecha de contrataci�n
		if (conductor.getFechaContratacion() == null) {
			throw new LogicException("Debe ingresar una fecha de contrataci�n");
		}
		// Se valida que la fecha de contrataci�n sea menor a la fecha actual
		if (!Utilidades.validateFechaContrato(conductor.getFechaContratacion())) {
			throw new LogicException("La fecha de contrataci�n debe ser antes de la fecha actual");
		}

		// Se valida que el conductor no exista en la base de datos
		Tmio1Conductore c = tc.findByCedula(em, conductor.getCedula());
		if (c != null) {
			throw new LogicException("El conductor con n�mero de c�dula: " + conductor.getCedula() + " ya existe");
		}

		tc.save(em, conductor);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(Tmio1Conductore conductor) throws LogicException {

		// Se valida que se ingrese un conductor
		if (conductor == null) {
			throw new LogicException("Debe ingresar un conductor");
		}

		// Se valida que se ingrese una cédula
		if (conductor.getCedula() == null || conductor.getCedula().trim().equals("")) {
			throw new LogicException("Debe ingresar una cédula");
		}
		// Se valida que la cédula sea numérica
		if (!Utilidades.isNumeric(conductor.getCedula())) {
			throw new LogicException("La cédula debe ser de tipo numérica");
		}

		// Se valida que el conductor tenga nombre
		if (conductor.getNombre() == null || conductor.getNombre().trim().equals("")) {
			throw new LogicException("Debe ingresar el nombre del conductor");
		}
		// Se valida que el nombre contenga al menos 3 caracteres
		if (conductor.getNombre().length() < 3) {
			throw new LogicException("El nombre del conductor debe contener al menos 3 caracteres");
		}

		// Se valida que se ingrese el apellido del conductor
		if (conductor.getApellidos() == null || conductor.getApellidos().trim().equals("")) {
			throw new LogicException("Debe ingresar el apellido del conductor");
		}
		// Se valida que el apellido sea almenos de 3 caracteres
		if (conductor.getApellidos().length() < 3) {
			throw new LogicException("El apellido del conductor debe contener al menos 3 caracteres");
		}

		// Se valida que se ingrese la fecha de nacimiento del conductor
		if (conductor.getFechaNacimiento() == null) {
			throw new LogicException("Debe ingresar la fecha de nacimiento del conductor");
		}
		// Se valida que el conductor sea mayor de edad
		if (!Utilidades.isLegalAge(conductor.getFechaNacimiento())) {
			throw new LogicException("El conductor debe ser mayor de edad");
		}

		// Se valida que se ingrese una fecha de contrataci�n
		if (conductor.getFechaContratacion() == null) {
			throw new LogicException("Debe ingresar una fecha de contrataci�n");
		}
		// Se valida que la fecha de contrataci�n sea menor a la fecha actual
		if (!Utilidades.validateFechaContrato(conductor.getFechaContratacion())) {
			throw new LogicException("La fecha de contrataci�n debe ser antes de la fecha actual");
		}

		// Se valida que el conductor exista en la base de datos
		Tmio1Conductore c = tc.findByCedula(em, conductor.getCedula());
		if (c == null) {
			throw new LogicException("El conductor con n�mero de c�dula: " + conductor.getCedula() + " no existe");
		}

		tc.update(em, conductor);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Tmio1Conductore conductor) throws LogicException {

		// Se valida que se ingrese un conductor
		if (conductor == null) {
			throw new LogicException("Debe ingresar un conductor");
		}

		// Se valida que se ingrese una cédula
		if (conductor.getCedula() == null || conductor.getCedula().trim().equals("")) {
			throw new LogicException("Debe ingresar una cédula");
		}
		// Se valida que la cédula sea numérica
		if (!Utilidades.isNumeric(conductor.getCedula())) {
			throw new LogicException("La cédula debe ser de tipo numérica");
		}

		// Se valida que el conductor no exista en la base de datos
		Tmio1Conductore c = tc.findByCedula(em, conductor.getCedula());
		if (c == null) {
			throw new LogicException("El conductor con n�mero de c�dula: " + conductor.getCedula() + " no existe");
		}

		tc.delete(em, conductor);

	}

	@Override
	@Transactional(readOnly = true)
	public List<Tmio1Conductore> findByName(String name) throws LogicException {

		// Se valida que se ingrese el nombre del conductor
		if (name == null || name.trim().equals("")) {
			throw new LogicException("Debe ingresar el nombre del conductor");
		}
		// Se valida que el nombre contenga al menos 3 caracteres
		if (name.length() < 3) {
			throw new LogicException("El nombre del conductor debe contener al menos 3 caracteres");
		}

		// Se valida que existan conductores con el nombre pasado por par�metro
		List<Tmio1Conductore> lC = tc.findByName(em, name);
		if (lC == null) {
			throw new LogicException("No existen conductores con nombre: " + name);
		}

		return lC;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Tmio1Conductore> findByLastName(String lastname) throws LogicException {

		// Se valida que se ingrese el apellido del conductor
		if (lastname == null || lastname.trim().equals("")) {
			throw new LogicException("Debe ingresar el apellido del conductor");
		}
		// Se valida que el apellido sea almenos de 3 caracteres
		if (lastname.length() < 3) {
			throw new LogicException("El apellido del conductor debe contener al menos 3 caracteres");
		}

		// Se valida que existan conductores con el apellido pasado por par�metro
		List<Tmio1Conductore> lC = tc.findByLastName(em, lastname);
		if (lC == null) {
			throw new LogicException("No existen conductores con el apellido: " + lastname);
		}

		return lC;
	}

	@Override
	@Transactional(readOnly = true)
	public Tmio1Conductore findByCedula(String cedula) throws LogicException {

		// Se valida que se ingrese una cédula
		if (cedula == null || cedula.trim().equals("")) {
			throw new LogicException("Debe ingresar una cédula");
		}
		// Se valida que la cédula sea numérica
		if (!Utilidades.isNumeric(cedula)) {
			throw new LogicException("La cédula debe ser de tipo numérica");
		}
		
		// Se valida que el conductor exista en la base de datos
		Tmio1Conductore c = tc.findByCedula(em, cedula);
		if (c == null) {
			throw new LogicException("El conductor con n�mero de c�dula: " + cedula + " no existe");
		}
		
		return c;
	}
	
}
