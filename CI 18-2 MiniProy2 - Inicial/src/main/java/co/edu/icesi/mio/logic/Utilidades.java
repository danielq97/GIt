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

	// Valida si una cadena entrada por par�metro es numerica
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

	// Valida si una persona tiene la mayor�a de edad, valida si es fecha SQL o
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

	// Valida si una fecha de contrataci�n es antes a la fecha actual de ejecutar el
	// m�todo, verifica si es intancia SQL

	public static boolean validateFechaContrato(Date fechaContratacion) {
		if (fechaContratacion instanceof java.sql.Date) {
			LocalDate ahora = LocalDate.now();
			LocalDate sqllocalDate = ((java.sql.Date) fechaContratacion).toLocalDate();
			return sqllocalDate.isBefore(ahora);
		}

		else {
			LocalDate ahora = LocalDate.now();
			LocalDate contrato = fechaContratacion.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			return contrato.isBefore(ahora);
		}
	}

	// Valida si una fecha es antes de otra fecha
	public static boolean dateBeforeDateOrEqual(Date ini, Date fin) {
		LocalDate dIni = ini.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate dFin = fin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return dIni.isBefore(dFin) || dIni.isEqual(dFin);
	}

	// Valida si un n�mero se encuentra entre otros dos n�meros
	public static boolean isBetween(int ini, int fin, int number) {
		boolean resultado = true;
		if (ini > number || fin < number) {
			resultado = false;
		}
		return resultado;
	}
	
	public static boolean isBusAvailableC(Tmio1Bus bus, Tmio1Servicio servicio) {
		boolean resultado = true;
		List<Tmio1Servicio> serviciosBus = bus.getTmio1Servicios();
		if (serviciosBus.size() != 0) {
			Calendar iniServicio = toCalendar(servicio.getId().getFechaInicio());
			Calendar finServicio = toCalendar(servicio.getId().getFechaFin());
			for (int i = 0; i < serviciosBus.size() && resultado; i++) {
				Calendar iniServicioBus = toCalendar(serviciosBus.get(i).getId().getFechaInicio());
				Calendar finServicioBus = toCalendar(serviciosBus.get(i).getId().getFechaFin());
				if (!serviciosBus.get(i).getId().equals(servicio.getId())) {
					if (iniServicio.equals(iniServicioBus) || iniServicio.equals(finServicioBus)
							|| finServicio.equals(iniServicioBus) || finServicio.equals(finServicioBus)) {
						resultado = false;
					} else if (iniServicio.after(iniServicioBus) && iniServicio.before(finServicioBus)) {
						resultado = false;
					} else if (finServicio.after(iniServicioBus) && finServicio.before(finServicioBus)) {
						resultado = false;
					}
				} else {
					break;
				}
			}
		}
		return resultado;
	}
	
	public static boolean isConductorAvailableC(Tmio1Conductore conductor, Tmio1Servicio servicio) {
		boolean resultado = true;
		List<Tmio1Servicio> serviciosBus = conductor.getTmio1Servicios();
		if (serviciosBus.size() != 0) {
			Calendar iniServicio = toCalendar(servicio.getId().getFechaInicio());
			Calendar finServicio = toCalendar(servicio.getId().getFechaFin());
			for (int i = 0; i < serviciosBus.size() && resultado; i++) {
				Calendar iniServicioCon = toCalendar(serviciosBus.get(i).getId().getFechaInicio());
				Calendar finServicioCon = toCalendar(serviciosBus.get(i).getId().getFechaFin());
				if (!serviciosBus.get(i).getId().equals(servicio.getId())) {
					if (iniServicio.equals(iniServicioCon) || iniServicio.equals(finServicioCon)
							|| finServicio.equals(iniServicioCon) || finServicio.equals(finServicioCon)) {
						resultado = false;
					} else if (iniServicio.after(iniServicioCon) && iniServicio.before(finServicioCon)) {
						resultado = false;
					} else if (finServicio.after(iniServicioCon) && finServicio.before(finServicioCon)) {
						resultado = false;
					}
				} else {
					break;
				}
			}
		}
		return resultado;
	}

	// Convierte una fecha tipo Date a una fecha tipo Calendar
	public static Calendar toCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

}
