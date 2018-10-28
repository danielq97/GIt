package co.edu.icesi.mio.logic;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.mio.dao.ITmio1_Conductores_DAO;
import co.edu.icesi.mio.exceptions.LogicException;
import co.edu.icesi.mio.model.Tmio1Conductore;

@Service
public class Tmio1ConductoreLogic implements ITmio1ConductoreLogic {

	@Autowired
	private ITmio1_Conductores_DAO tc;

	@PersistenceUnit
	private EntityManager em;

	@Override
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
		// if (validateFechaNac(conductor.getFechaNacimiento())) {
		//
		// }

	}

	@Override
	public void update(Tmio1Conductore conductor) throws LogicException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Tmio1Conductore conductor) throws LogicException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Tmio1Conductore> findByName(String name) throws LogicException {
		// TODO Auto-generated method stub
		return tc.findByName(em, name);
	}

	@Override
	public List<Tmio1Conductore> findByLastName(String lastname) throws LogicException {
		// TODO Auto-generated method stub
		return tc.findByLastName(em, lastname);
	}

	@Override
	public Tmio1Conductore findByCedula(String cedula) throws LogicException {
		// TODO Auto-generated method stub
		return tc.findByCedula(em, cedula);
	}

	// Falta comprobar que sea numerica
	public boolean validateCedula(String cedula) {
		return cedula != null;
	}

	// Validar nombre
	public boolean validateNombre(String nombre) {
		return nombre != null && nombre.length() >= 3;
	}

	// Validar apellidos
	public boolean validateApellidos(String apellidos) {
		return apellidos != null && apellidos.length() >= 3;
	}

	// José: Ve Daniel creo que no es preciso el método porque si alguien nació en
	// el 2000 pero en diciembre y hace la comparación por los años con la fecha
	// actual entonces va a decir que sí tiene la mayoría de edad lo cual es un error.
	// Validar en la fecha de nacimiento que sea mayor de edad, revisar
	@SuppressWarnings("deprecation")
	public boolean validateFechaNac(Date fechaNacimiento) {
		LocalDate ahora = LocalDate.now();
		int anioAhora = ahora.getYear();
		int anioNacimiento = fechaNacimiento.getYear();

		// Creo que va (anioahora - anioNacimiento)
		return (anioNacimiento - anioAhora) >= 18;
	}

	// Lo terminaré luego lo saqué de : https://es.stackoverflow.com/questions/13777/obtener-edad-a-partir-de-la-fecha-de-nacimiento-en-java
	private boolean isLegal(Date fechaNacimiento) {
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate fechaNac = LocalDate.parse("15/08/1993", fmt);
		LocalDate ahora = LocalDate.now();

		Period periodo = Period.between(fechaNac, ahora);

		if (false) {

		}
		return true;
	}

	// Este quedo bueno, pero toca probar
	public boolean validateFechaContrato(Date fechaContratacion) {
		LocalDate ahora = LocalDate.now();
		LocalDate contrato = fechaContratacion.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return contrato.isBefore(ahora);
	}



}
