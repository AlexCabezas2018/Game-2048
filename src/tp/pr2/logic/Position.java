//Practica 3
//Alejandro Cabezas Garriguez
//Sergio Santa Clotilde Ruiz
package tp.pr2.logic;

import tp.pr2.logic.Direction;

public class Position {
	/**
	 * @param fila
	 * @param columna
	 * Clase que define las posiciones
	 */
	private int fila, columna;
	
	public Position(int fila, int columna) {
		this.fila = fila;
		this.columna = columna;
	}
	
	public int getFila() {
		return this.fila;
	}
	
	public int getColumna() {
		return this.columna;
	}
	
	public Position neighbour(Direction dir, int size) {
		/**
		 * @param Direccion
		 * @param tamaño
		 * Dada una direccion y el tamaño del tablero, calcula la posicion vecina.
		 */
		Position x;
		int f = 0, c = 0;
		switch(dir) {
		case UP:
			if(this.fila - 1 >= 0) { //Si no nos hemos salido del tablero...
			f = -1;
			}
			break;
		case DOWN:
			if(this.fila + 1 < size) {
			f = 1;
			}
			break;
		case LEFT:
			if(this.columna - 1 >= 0) {
			c = -1;
			}
			break;
		case RIGHT:
			if(this.columna + 1 < size) {
			c = 1;
			}
			break;
		}
		
		x = new Position(this.fila + f, this.columna + c); //Envia la coordenas del vecino. Si no tiene vecino, envia las mismas que la entrada
		
		return x;
		
	}

}
