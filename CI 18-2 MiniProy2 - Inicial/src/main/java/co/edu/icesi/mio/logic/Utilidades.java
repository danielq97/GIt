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

}
