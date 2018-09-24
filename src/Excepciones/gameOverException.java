//Practica 3
//Alejandro Cabezas Garriguez
//Sergio Santa Clotilde Ruiz
package Excepciones;

public class gameOverException extends Exception{
	/**
	 * 
	 * @param reason
	 * Excepcion que salta cuando la partida se termina (No confundir con perder)
	 */
	public gameOverException(String reason) {
		super(reason);
	}

}
