package co.edu.icesi.mio.logic;

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

}
