//Practica 3
//Alejandro Cabezas Garriguez
//Sergio Santa Clotilde Ruiz
package Excepciones;

public class FileContentsException extends Exception {
	/**
	 * 
	 * @param reason
	 * Excepcion que salta cuando el archivo de entrada no esta bien estructurado
	 */
	public FileContentsException(String reason) {
		super(reason);
	}
}
