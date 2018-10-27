package co.edu.icesi.mio.logic;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import co.edu.icesi.mio.exceptions.LogicException;
import co.edu.icesi.mio.model.Tmio1Bus;
import co.edu.icesi.mio.model.Tmio1Ruta;

public interface ITmio1RutaLogic {

	public void create(Tmio1Ruta ruta) throws LogicException;

	public void update(Tmio1Ruta ruta) throws LogicException;

	public void delete(Tmio1Ruta ruta) throws LogicException;

	public List<Tmio1Ruta> findByRangeOfDays(EntityManager em, BigDecimal diaInicio, BigDecimal diaFin);
}
