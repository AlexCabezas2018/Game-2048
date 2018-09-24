//Practica 3
//Alejandro Cabezas Garriguez
//Sergio Santa Clotilde Ruiz
/**
 * Paquete que define las posibles excepciones del juego
 */
package Excepciones;

public class CommandExecuteException extends Exception {
	/**
	 * 
	 * @param reason
	 * Excepcion que es lanzada cuando el comando no se ejecuta correctamente
	 */
	public CommandExecuteException(String reason) {
		super(reason);
	}

}
