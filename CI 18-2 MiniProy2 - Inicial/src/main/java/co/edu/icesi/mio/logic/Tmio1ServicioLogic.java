package co.edu.icesi.mio.logic;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;


import co.edu.icesi.mio.dao.ITmio1_Servicios_DAO;
import co.edu.icesi.mio.exceptions.LogicException;
import co.edu.icesi.mio.model.Tmio1Bus;
import co.edu.icesi.mio.model.Tmio1Conductore;
import co.edu.icesi.mio.model.Tmio1Ruta;
import co.edu.icesi.mio.model.Tmio1Servicio;
import co.edu.icesi.mio.model.Tmio1ServicioPK;

public class Tmio1ServicioLogic implements ITmio1ServicioLogic {

	private ITmio1_Servicios_DAO is;

	@Override
	public void create(EntityManager em, Tmio1Servicio ruta) throws LogicException {
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
	//Validar que exista el bus, y que este disponible el servicio (revisar)
	public boolean validateBus(Tmio1Bus bus, Tmio1Servicio servicio) {
		boolean r = true;
		if(bus!=null) {
			List <Tmio1Servicio> servicios = bus.getTmio1Servicios();
			for (Tmio1Servicio tmio1Servicio : servicios) {
				if(tmio1Servicio.getId().getFechaInicio().getDay()==servicio.getId().getFechaInicio().getDay()) {
					r= false;
					break;
				
				}
			}
			
		}
		return r;
	}
	//Validar que exista la ruta
	public boolean validateRuta(Tmio1Ruta ruta) {
		
		return ruta!=null;
	}
	//Validar que exista el conductor
	public boolean validateConductor(Tmio1Conductore conductor) {
		return conductor!=null;
	}
	//Validar que la fecha de inicio anterior a la de fin
	public boolean validateServicio (Tmio1ServicioPK id) {
		if(id!=null) {
			return id.getFechaInicio().before(id.getFechaFin());
		}
		return false;
	}

	

}
