package co.edu.icesi.mio.logic;

import java.util.List;

import javax.persistence.EntityManager;

import co.edu.icesi.mio.exceptions.LogicException;
import co.edu.icesi.mio.model.Tmio1Conductore;

public interface ITmio1ConductoreLogic {

	public void create(Tmio1Conductore conductor) throws LogicException;

	public void update(Tmio1Conductore conductor) throws LogicException;

	public void delete(Tmio1Conductore conductor) throws LogicException;

	public List<Tmio1Conductore> findByName(EntityManager em, String name) throws LogicException;

	public List<Tmio1Conductore> findByLastName(EntityManager em, String lastname) throws LogicException;

	public Tmio1Conductore findByCedula(EntityManager em, String cedula) throws LogicException;

}
