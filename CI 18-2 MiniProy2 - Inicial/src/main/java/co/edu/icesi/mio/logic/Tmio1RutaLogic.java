package co.edu.icesi.mio.logic;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import co.edu.icesi.mio.dao.ITmio1_Rutas_DAO;
import co.edu.icesi.mio.model.Tmio1Ruta;

public class Tmio1RutaLogic implements ITmio1RutaLogic{

	
	private ITmio1_Rutas_DAO ir;
	
	@Override
	public void create(Tmio1Ruta ruta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Tmio1Ruta ruta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Tmio1Ruta ruta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Tmio1Ruta> findByRangeOfDays(EntityManager em, BigDecimal diaInicio, BigDecimal diaFin) {
		// TODO Auto-generated method stub
		return ir.findByRangeOfDays(em, diaInicio, diaFin);
	}

	
}
