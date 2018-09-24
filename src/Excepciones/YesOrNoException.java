//Practica 3
//Alejandro Cabezas Garriguez
//Sergio Santa Clotilde Ruiz
package Excepciones;

public class YesOrNoException extends Exception {
	/**
	 * 
	 * @param reason
	 * Excepcion que salta cuando el usuario una opcion distinta de "Y" o "N". Se usa a la hora de pedir una nuevo archivo de 
	 * guardado.
	 */
	public YesOrNoException(String reason) {
		super(reason);
	}

}
