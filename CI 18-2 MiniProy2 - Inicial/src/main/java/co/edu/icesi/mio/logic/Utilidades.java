package co.edu.icesi.mio.logic;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

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
	
	// Valida si una persona tiene la mayoría de edad
	public static boolean isLegalAge(Date fechaNacimiento) {
		LocalDate nFechaNacimiento = fechaNacimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate ahora = LocalDate.now();
		Period periodo = Period.between(nFechaNacimiento, ahora);
		boolean resultado = true;
		if (periodo.getYears() < 18) {
			resultado = false;
		}
		return resultado;
	}
	
	// Valida si una fecha de contratación es antes a la fecha actual de ejecutar el método
	public static boolean validateFechaContrato(Date fechaContratacion) {
		LocalDate ahora = LocalDate.now();
		LocalDate contrato = fechaContratacion.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return contrato.isBefore(ahora);
	}
	
	// Valida si una fecha es antes de otra fecha
	public static boolean dateBeforeDateOrEqual(Date ini, Date fin) {
		LocalDate dIni = ini.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate dFin = fin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return dIni.isBefore(dFin) && dIni.isEqual(dFin);
	}
	
	// Valida si un número se encuentra entre otros dos números
	public static boolean isBetween(int ini, int fin, int number) {
		boolean resultado = true;
		if ( ini>number || fin<number) {
			resultado = false;
		}
		return resultado;
	}

}
