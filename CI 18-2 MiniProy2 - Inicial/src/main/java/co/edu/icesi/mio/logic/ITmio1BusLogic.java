package co.edu.icesi.mio.logic;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import co.edu.icesi.mio.exceptions.LogicException;
import co.edu.icesi.mio.model.Tmio1Bus;

public interface ITmio1BusLogic {

	public void create(Tmio1Bus bus) throws LogicException;

	public void update(Tmio1Bus bus) throws LogicException;

	public void delete(Tmio1Bus bus) throws LogicException;

	public List<Tmio1Bus> findByModel(EntityManager em, BigDecimal model) throws LogicException;

	public List<Tmio1Bus> findByType(EntityManager em, String type) throws LogicException;

	public List<Tmio1Bus> findByCapacity(EntityManager em, BigDecimal capacity) throws LogicException;

}