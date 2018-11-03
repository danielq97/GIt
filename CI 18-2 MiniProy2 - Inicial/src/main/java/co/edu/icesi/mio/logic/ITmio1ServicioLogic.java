package co.edu.icesi.mio.logic;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import co.edu.icesi.mio.exceptions.LogicException;
import co.edu.icesi.mio.model.Tmio1Servicio;
import co.edu.icesi.mio.model.Tmio1ServicioPK;

public interface ITmio1ServicioLogic {

	public void create(Tmio1Servicio servicio) throws LogicException;

	public void update(Tmio1Servicio servicio) throws LogicException;

	public void delete(Tmio1Servicio servicio) throws LogicException;

	public List<Tmio1Servicio> findByRangeOfDates(Date fechaInicio, Date fechaFin) throws LogicException;

	public Tmio1Servicio findById(Tmio1ServicioPK id) throws LogicException;

}
