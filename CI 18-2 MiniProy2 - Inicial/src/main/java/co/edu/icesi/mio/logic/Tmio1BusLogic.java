package co.edu.icesi.mio.logic;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import co.edu.icesi.mio.dao.ITmio1_Buses_DAO;
import co.edu.icesi.mio.exceptions.LogicException;
import co.edu.icesi.mio.model.Tmio1Bus;

public class Tmio1BusLogic implements ITmio1BusLogic{

	private ITmio1_Buses_DAO ib;
	
	@Override
	public void create(Tmio1Bus bus) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Tmio1Bus bus) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Tmio1Bus bus) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Tmio1Bus> findByModel(EntityManager em, BigDecimal model) throws LogicException {
		// TODO Auto-generated method stub
		return ib.findByModel(em, model);
	}

	@Override
	public List<Tmio1Bus> findByType(EntityManager em, String type) throws LogicException {
		// TODO Auto-generated method stub
		return ib.findByType(em, type);
	}

	@Override
	public List<Tmio1Bus> findByCapacity(EntityManager em, BigDecimal capacity) throws LogicException {
		// TODO Auto-generated method stub
		return ib.findByCapacity(em, capacity);
	}

	
}
