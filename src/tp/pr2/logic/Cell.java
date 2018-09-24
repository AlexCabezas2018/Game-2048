//Practica 3
//Alejandro Cabezas Garriguez
//Sergio Santa Clotilde Ruiz
package tp.pr2.logic;
import tp.pr2.logic.multigames.*;

public class Cell {
	/**
	 * Clase para las celdas del tablero.
	 */
	private int value;
	
	public Cell() {
		/**
		 * @param valor
		 */
		this.value = 0;
	}

	public int getValue() {
		/**
		 * @returns Integer.
		 */
		return this.value;
	}
	
	public void setValue(int newValue) {
		/**
		 * @param nuevoValor
		 * Cambia el valor de la celda.
		 */
		this.value = newValue;
	}
	
	public boolean isEmpty() {
		/**
		 * @returns boolean
		 * Comprueba que la celda esta vacia
		 */
		return this.value == 0;
	}
	
	public int doMerge(Cell n, GameRules rules){
		/**
		 * @return Integer
		 * Realiza la mezcla conforme a las reglas del juego
		 */
		return rules.merge(this, n);
	}

}
