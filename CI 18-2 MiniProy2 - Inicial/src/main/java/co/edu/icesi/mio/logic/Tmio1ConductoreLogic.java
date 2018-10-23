package co.edu.icesi.mio.logic;

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

}
