//Practica 3
//Alejandro Cabezas Garriguez
//Sergio Santa Clotilde Ruiz
package Excepciones;

public class ParseCommandException extends Exception{
	/**
	 * 
	 * @param reason
	 * Excepcion que salta cuando el comando no se parsea bien
	 */
	public ParseCommandException(String reason) {
		super(reason);
	}

}
