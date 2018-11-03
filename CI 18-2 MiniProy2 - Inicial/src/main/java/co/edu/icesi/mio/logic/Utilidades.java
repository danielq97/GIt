package co.edu.icesi.mio.logic;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import co.edu.icesi.mio.model.Tmio1Bus;
import co.edu.icesi.mio.model.Tmio1Conductore;
import co.edu.icesi.mio.model.Tmio1Servicio;

public class Utilidades {

	// Valida si una cadena entrada por parámetro es numerica
	public static boolean isNumeric(String cadena) {
		boolean resultado;

		try {
			Integer.parseInt(cadena);
			resultado = true;
		} catch (NumberFormatException excepcion) {
			resultado = false;
		}

		return resultado;
	}

	// Valida si una persona tiene la mayoría de edad, valida si es fecha SQL o
	// java.util, para que no presente problemas
	public static boolean isLegalAge(Date fechaNacimiento) {
		if (fechaNacimiento instanceof java.sql.Date) {
			LocalDate sqllocalDate = ((java.sql.Date) fechaNacimiento).toLocalDate();
			LocalDate ahora = LocalDate.now();
			Period periodo = Period.between(sqllocalDate, ahora);
			boolean resultado = true;
			if (periodo.getYears() < 18) {
				resultado = false;
			}
			return resultado;
		} else {
			LocalDate nFechaNacimiento = fechaNacimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			LocalDate ahora = LocalDate.now();
			Period periodo = Period.between(nFechaNacimiento, ahora);
			boolean resultado = true;
			if (periodo.getYears() < 18) {
				resultado = false;
			}
			return resultado;
		}
	}

	// Valida si una fecha de contratación es antes a la fecha actual de ejecutar el
	// método, verifica si es intancia SQL
	
	public static boolean validateFechaContrato(Date fechaContratacion) {
		if(fechaContratacion instanceof java.sql.Date) {
			LocalDate ahora = LocalDate.now();
			LocalDate sqllocalDate = ((java.sql.Date) fechaContratacion).toLocalDate();
		  return sqllocalDate.isBefore(ahora);}
		
		else {
			LocalDate ahora = LocalDate.now();
			LocalDate contrato = fechaContratacion.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			return contrato.isBefore(ahora);}
		}
	

	// Valida si una fecha es antes de otra fecha
	public static boolean dateBeforeDateOrEqual(Date ini, Date fin) {
		LocalDate dIni = ini.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate dFin = fin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return dIni.isBefore(dFin) || dIni.isEqual(dFin);
	}

	// Valida si un número se encuentra entre otros dos números
	public static boolean isBetween(int ini, int fin, int number) {
		boolean resultado = true;
		if (ini > number || fin < number) {
			resultado = false;
		}
		return resultado;
	}

	// Valida si el bus pasado por parámetro está disponible para el servicio pasado
	// por parámetro
	public static boolean isBusAvailable(Tmio1Bus bus, Tmio1Servicio servicio) {
		boolean disponible = true;
		List<Tmio1Servicio> serviciosBus = bus.getTmio1Servicios();
		LocalDate iniServicio = servicio.getId().getFechaInicio().toInstant().atZone(ZoneId.systemDefault())
				.toLocalDate();
		LocalDate finServicio = servicio.getId().getFechaInicio().toInstant().atZone(ZoneId.systemDefault())
				.toLocalDate();
		for (int i = 0; i < serviciosBus.size() && disponible; i++) {
			LocalDate iniServicioBus = serviciosBus.get(i).getId().getFechaInicio().toInstant()
					.atZone(ZoneId.systemDefault()).toLocalDate();
			LocalDate finServicioBus = serviciosBus.get(i).getId().getFechaFin().toInstant()
					.atZone(ZoneId.systemDefault()).toLocalDate();
			if (iniServicio.isEqual(iniServicioBus) || iniServicio.isEqual(finServicioBus)
					|| finServicio.isEqual(iniServicioBus) || finServicio.isEqual(finServicioBus)) {
				disponible = false;
			} else if (iniServicio.isAfter(iniServicioBus) && iniServicio.isBefore(finServicioBus)) {
				disponible = false;
			} else if (finServicio.isAfter(iniServicioBus) && finServicio.isBefore(finServicioBus)) {
				disponible = false;
			}
		}
		return disponible;
	}

	// Valida si el conductor pasado por parámetro está disponible para el servicio
	// pasado
	// por parámetro
	public static boolean isConductorAvailable(Tmio1Conductore con, Tmio1Servicio servicio) {
		boolean disponible = true;
		List<Tmio1Servicio> serviciosCon = con.getTmio1Servicios();
		LocalDate iniServicio = servicio.getId().getFechaInicio().toInstant().atZone(ZoneId.systemDefault())
				.toLocalDate();
		LocalDate finServicio = servicio.getId().getFechaInicio().toInstant().atZone(ZoneId.systemDefault())
				.toLocalDate();
		for (int i = 0; i < serviciosCon.size() && disponible; i++) {
			LocalDate iniServicioCon = serviciosCon.get(i).getId().getFechaInicio().toInstant()
					.atZone(ZoneId.systemDefault()).toLocalDate();
			LocalDate finServicioCon = serviciosCon.get(i).getId().getFechaFin().toInstant()
					.atZone(ZoneId.systemDefault()).toLocalDate();
			if (iniServicio.isEqual(iniServicioCon) || iniServicio.isEqual(finServicioCon)
					|| finServicio.isEqual(iniServicioCon) || finServicio.isEqual(finServicioCon)) {
				disponible = false;
			} else if (iniServicio.isAfter(iniServicioCon) && iniServicio.isBefore(finServicioCon)) {
				disponible = false;
			} else if (finServicio.isAfter(iniServicioCon) && finServicio.isBefore(finServicioCon)) {
				disponible = false;
			}
		}
		return disponible;
	}

	// Convierte una fecha tipo Date a una fecha tipo Calendar
	public static Calendar toCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

}
