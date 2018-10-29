package co.edu.icesi.mio.logic;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;

import co.edu.icesi.mio.exceptions.LogicException;
import co.edu.icesi.mio.model.Tmio1Ruta;
import co.edu.icesi.mio.model.Tmio1Servicio;

public interface ITmio1ServicioLogic {

	public void create(Tmio1Servicio servicio) throws LogicException;

	public void update(Tmio1Servicio servicio) throws LogicException;

	public void delete(Tmio1Servicio servicio) throws LogicException;

	public List<Tmio1Servicio> findByRangeOfDates(Calendar fechaInicio, Calendar fechaFin) throws LogicException;

}
