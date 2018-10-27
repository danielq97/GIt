package co.edu.icesi.mio.logic;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.mio.dao.ITmio1_Buses_DAO;
import co.edu.icesi.mio.exceptions.LogicException;
import co.edu.icesi.mio.model.Tmio1Bus;

public class Tmio1BusLogic implements ITmio1BusLogic {

	@Autowired
	private ITmio1_Buses_DAO ib;

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void create(Tmio1Bus bus) throws LogicException {

		// Se valida que se ingrese un bus
		if (bus == null) {
			throw new LogicException("Debe ingresar un bus");
		}

		// Se valida que se ingrese una placa
		if (bus.getPlaca() == null) {
			throw new LogicException("Debe ingresar una placa");
		}
		// Se valida que la placa tenga seis caracteres
		if (bus.getPlaca().length() != 6) {
			throw new LogicException("Debe ingresar una placa que sea de 6 caracteres");
		}
		// Se valida otro bus no tenga la misma placa
		if (false) {
			// TODO: Preguntar si no se debe hacer esta validación, y si es así ¿se
			// consultaría desde aquí a la base de datos?
		}

		// Se valida que se ingrese una marca
		if (bus.getMarca() == null) {
			throw new LogicException("Debe ingresar una marca");
		}
		// Se valida que la marca contenga minimo 3 caracteres
		if (bus.getMarca().length() < 3) {
			throw new LogicException("Debe ingresar una marca que tenga como minimo 3 caracteres");
		}

		// Se valida que se ingrese un modelo
		if (bus.getModelo() == null) {
			throw new LogicException("Debe ingresar un modelo");
		}
		// Se valida que el modelo sea de 4 digitos
		if (bus.getModelo().toString().length() != 4) {
			throw new LogicException("Debe ingresar un modelo de 4 digitos");
		}

		// Se valida que se ingrese un tipo de bus válido
		if (bus.getTipo() == null) {
			throw new LogicException("Debe ingresar un tipo de bus");
		}
		// Se valida que el tipo ingresado corresponda a P, A o T
		if (bus.getTipo().length() != 1 || !bus.getTipo().equals("P") || !bus.getTipo().equals("A")
				|| !bus.getTipo().equals("T")) {
			throw new LogicException("El tipo de bus debe ser 'P', 'A' o 'T'");
		}

		// Se valida que se ingrese una capacidad no nula
		if (bus.getCapacidad() == null) {
			throw new LogicException("Debe ingresar una capacidad válida");
		}
		// Se valida que se ingrese una capacidad mayor a cero
		if (bus.getCapacidad().compareTo(new BigDecimal(0)) < 1) {
			throw new LogicException("Se debe ingresar una capacidad mayor a cero");
		}

		// TODO: no sé si es necesario
		// Se valida que el bus no exista en la base de datos (si sí lo es, faltan más
		// validaciones)
		Tmio1Bus b = ib.findById(em, bus.getId());
		if (b != null) {
			throw new LogicException("El bus con id: " + bus.getId() + " ya existe");
		}

		ib.save(em, bus);

	}

	@Override
	public void update(Tmio1Bus bus) throws LogicException {

		// Se valida que se ingrese un bus
		if (bus == null) {
			throw new LogicException("Debe ingresar un bus");
		}

		// Se valida que se ingrese una placa
		if (bus.getPlaca() == null) {
			throw new LogicException("Debe ingresar una placa");
		}
		// Se valida que la placa tenga seis caracteres
		if (bus.getPlaca().length() != 6) {
			throw new LogicException("Debe ingresar una placa que sea de 6 caracteres");
		}
		// Se valida otro bus no tenga la misma placa
		if (false) {
			// TODO: Preguntar si no se debe hacer esta validación, y si es así ¿se
			// consultaría desde aquí a la base de datos?
		}

		// Se valida que se ingrese una marca
		if (bus.getMarca() == null) {
			throw new LogicException("Debe ingresar una marca");
		}
		// Se valida que la marca contenga minimo 3 caracteres
		if (bus.getMarca().length() < 3) {
			throw new LogicException("Debe ingresar una marca que tenga como minimo 3 caracteres");
		}

		// Se valida que se ingrese un modelo
		if (bus.getModelo() == null) {
			throw new LogicException("Debe ingresar un modelo");
		}
		// Se valida que el modelo sea de 4 digitos
		if (bus.getModelo().toString().length() != 4) {
			throw new LogicException("Debe ingresar un modelo de 4 digitos");
		}

		// Se valida que se ingrese un tipo de bus válido
		if (bus.getTipo() == null) {
			throw new LogicException("Debe ingresar un tipo de bus");
		}
		// Se valida que el tipo ingresado corresponda a P, A o T
		if (bus.getTipo().length() != 1 || !bus.getTipo().equals("P") || !bus.getTipo().equals("A")
				|| !bus.getTipo().equals("T")) {
			throw new LogicException("El tipo de bus debe ser 'P', 'A' o 'T'");
		}

		// Se valida que se ingrese una capacidad no nula
		if (bus.getCapacidad() == null) {
			throw new LogicException("Debe ingresar una capacidad válida");
		}
		// Se valida que se ingrese una capacidad mayor a cero
		if (bus.getCapacidad().compareTo(new BigDecimal(0)) < 1) {
			throw new LogicException("Se debe ingresar una capacidad mayor a cero");
		}

		// TODO: no sé si es necesario (si sí lo es, faltan más validaciones)
		// Se valida que el bus exista en la base de datos
		Tmio1Bus b = ib.findById(em, bus.getId());
		if (b == null) {
			throw new LogicException("El bus cono id: " + bus.getId() + " no existe");
		}

		ib.update(em, bus);

	}

	@Override
	public void delete(Tmio1Bus bus) throws LogicException {

		// Se valida que se ingrese un bus
		if (bus == null) {
			throw new LogicException("Debe ingresar un bus");
		}
		// TODO: no sé si es necesario
		// Se valida que el bus exista en la base de datos (si sí lo es, faltan más
		// validaciones)
		Tmio1Bus b = ib.findById(em, bus.getId());
		if (b == null) {
			throw new LogicException("El bus con id: " + bus.getId() + " no existe");
		}

	}

	@Override
	public List<Tmio1Bus> findByModel(BigDecimal model) throws LogicException {

		//Se valida que se ingrese un modelo
		if (model == null) {
			throw new LogicException("Debe ingresar un modelo");
		}
		//Se valida que se ingrese un modelo de 4 dígitos
		if (model.toString().length() != 4) {
			throw new LogicException("El modelo debe ser de 4 dígitos");
		}
		
		// Se valida que existan buses con el modelo pasado por parámetro
		List <Tmio1Bus> lB = ib.findByModel(em, model);
		if (lB == null) {
			throw new LogicException("No existen buses con modelo: " + model);
		}
		
		return lB;
	}

	@Override
	public List<Tmio1Bus> findByType(String type) throws LogicException {
		
		// Se valida que se ingrese un tipo de bus
		if (type == null) {
			throw new LogicException("Debe ingresar un tipo de bus");
		}
		// Se valida que el tipo de bus sea de tipo 'P', 'A' o 'T'
		if (type.length() != 1 || !type.equals("P") || !type.equals("A") || !type.equals("T")) {
			throw new LogicException("Debe ingresar un tipo de bus 'P', 'A' o 'T'");
		}
		
		// Se valida que existan buses con el tipo de bus pasado por parámetro
		List<Tmio1Bus> lB = ib.findByType(em, type);
		if (lB == null) {
			throw new LogicException("No existen buses con el tipo de bus: " + type);
		}
		
		return lB;
	}

	@Override
	public List<Tmio1Bus> findByCapacity(BigDecimal capacity) throws LogicException {
		
		// Se valida que se ingrese una capacidad no nula
		if (capacity == null) {
			throw new LogicException("Debe ingresar una capacidad válida");
		}
		// Se valida que se ingrese una capacidad mayor a cero
		if (capacity.compareTo(new BigDecimal(0)) < 1) {
			throw new LogicException("Se debe ingresar una capacidad mayor a cero");
		}
		
		// Se valida que existan buses con la capacidad pasada por parámetro
		List<Tmio1Bus> lB = ib.findByCapacity(em, capacity);
		if (lB == null) {
			throw new LogicException("No existen buses con la capacidad: " + capacity);
		}

		return lB;
	}

}
