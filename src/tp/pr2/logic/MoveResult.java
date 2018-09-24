//Practica 3
//Alejandro Cabezas Garriguez
//Sergio Santa Clotilde Ruiz
package tp.pr2.logic;

public class MoveResult {
	/**
	 * @param score
	 * @param seHaMovido
	 * Clase que define las puntuaciones
	 * 
	 */
	private int score;
	private boolean moved;
	
	public MoveResult(int score, boolean moved) {
		this.score = score;
		this.moved = moved;
	}
	/*Metodos accesores*/	
	public int getScore() {
		return this.score;
	}
	
	public boolean getMoved() {
		return this.moved;
	}
	
}
