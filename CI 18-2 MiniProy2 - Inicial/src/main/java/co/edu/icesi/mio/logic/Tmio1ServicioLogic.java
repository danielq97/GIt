package co.edu.icesi.mio.logic;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;


import co.edu.icesi.mio.dao.ITmio1_Servicios_DAO;
import co.edu.icesi.mio.model.Tmio1Servicio;

public class Tmio1ServicioLogic implements ITmio1ServicioLogic {

	private ITmio1_Servicios_DAO is;
	
	
	@Override
	public void create(EntityManager em, Tmio1Servicio ruta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(EntityManager em, Tmio1Servicio ruta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(EntityManager em, Tmio1Servicio ruta) {
		// TODO Auto-generated method stub
     		
	}

	@Override
	public List<Tmio1Servicio> findByRangeOfDates(EntityManager em, Calendar fechaInicio, Calendar fechaFin) {
		// TODO Auto-generated method stub
		return is.findByRangeOfDates(em, fechaInicio, fechaFin);
	}

}
