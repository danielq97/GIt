package co.edu.icesi.mio.logic;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;

import co.edu.icesi.mio.model.Tmio1Ruta;
import co.edu.icesi.mio.model.Tmio1Servicio;

public interface ITmio1ServicioLogic {

	public void create(EntityManager em,Tmio1Servicio ruta);
	public void update(EntityManager em,Tmio1Servicio ruta);
	public void delete(EntityManager em,Tmio1Servicio ruta);
	
	public List<Tmio1Servicio> findByRangeOfDates(EntityManager em, Calendar fechaInicio, Calendar fechaFin);
	
}
