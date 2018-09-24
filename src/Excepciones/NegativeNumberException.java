//Practica 3
//Alejandro Cabezas Garriguez
//Sergio Santa Clotilde Ruiz
package Excepciones;

public class NegativeNumberException extends Exception{
	/**
	 * 
	 * @param reason
	 * Excepcion que salta cuando se introduce un numero negativo
	 */
	public NegativeNumberException(String reason) {
		super(reason);
	}

}
