//Practica 3
//Alejandro Cabezas Garriguez
//Sergio Santa Clotilde Ruiz
package Excepciones;

public class EmptyStackException extends Exception{
	public EmptyStackException() {
		/**
		 * Excepcion que salta cuando la memoria de undoes y redoes esta vacia
		 */
		super("Pila vacia!");
	}

}
