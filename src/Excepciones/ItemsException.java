//Practica 3
//Alejandro Cabezas Garriguez
//Sergio Santa Clotilde Ruiz
package Excepciones;

public class ItemsException extends Exception{
	/**
	 * 
	 * @param reason
	 * Excepcion que salta cuando los items de entrada no son correctos
	 */
	public ItemsException(String reason) {
		super(reason);
	}
}
