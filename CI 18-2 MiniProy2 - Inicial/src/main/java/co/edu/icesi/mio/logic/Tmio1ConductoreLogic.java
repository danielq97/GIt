package co.edu.icesi.mio.logic;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.mio.dao.ITmio1_Conductores_DAO;
import co.edu.icesi.mio.exceptions.LogicException;
import co.edu.icesi.mio.model.Tmio1Conductore;
@Service
public class Tmio1ConductoreLogic implements ITmio1ConductoreLogic{
	
	@Autowired
	private ITmio1_Conductores_DAO tc;

	@Override
	public void create(Tmio1Conductore conductor) {
		// TODO Auto-generated method stub
		
		
		
	}

	@Override
	public void update(Tmio1Conductore conductor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Tmio1Conductore conductor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Tmio1Conductore> findByName(EntityManager em, String name) throws LogicException {
		// TODO Auto-generated method stub
		return tc.findByName(em, name);
	}

	@Override
	public List<Tmio1Conductore> findByLastName(EntityManager em, String lastname) throws LogicException {
		// TODO Auto-generated method stub
		return tc.findByLastName(em, lastname);
	}

	@Override
	public Tmio1Conductore findByCedula(EntityManager em, String cedula) throws LogicException {
		// TODO Auto-generated method stub
		return tc.findByCedula(em, cedula);
	}
	//Falta comprobar que sea numerica
	public boolean validateCedula(String cedula) {
		return cedula!=null;
	}
	//Validar nombre
	public boolean validateNombre(String nombre) {
		return nombre!=null && nombre.length()>=3;
	}
	//Validar apellidos
	public boolean validateApellidos (String apellidos) {
		return apellidos!=null && apellidos.length()>=3;
	}
	//Validar en la fecha de nacimiento que sea mayor de edad, revisar 
	@SuppressWarnings("deprecation")
	public boolean validateFechaNac(Date fechaNacimiento) {
    LocalDate ahora = LocalDate.now();
    int añoahora = ahora.getYear();
    ahora.getYear();
    int añoNacimiento=fechaNacimiento.getYear();

		
		return fechaNacimiento!=null && (añoNacimiento-añoahora)>=18;
	}
	//Este quedo bueno, pero toca probar
	public boolean validateFechaContrato(Date fechaContratacion) {
		 LocalDate ahora = LocalDate.now();
		 LocalDate contrato = fechaContratacion.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		 return contrato.isBefore(ahora);
	}
	
	

}
