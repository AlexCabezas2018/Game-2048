//Practica 3
//Alejandro Cabezas Garriguez
//Sergio Santa Clotilde Ruiz
package Excepciones;

public class NoValidNumbersException extends Exception{
	/**
	 * Excepcion que salta cuando los numeros introducidos no son correctos
	 */
	public NoValidNumbersException() {
		super("El tamaño y las celdas iniciales introducidas no cumplen las condiciones necesarias.");
	}
	
	public NoValidNumbersException(String reason) {
		super(reason);
	}

}
