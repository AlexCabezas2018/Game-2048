//Practica 3
//Alejandro Cabezas Garriguez
//Sergio Santa Clotilde Ruiz

package tp.pr2.logic;

public class GameState {
	private int score;
	private int[][] boardState;
	private boolean movedState;
	
	//Constructora con parametros
	public GameState(int[][]board, int s, boolean movedState) {
		this.boardState = board;
		this.score = s;
		this.movedState = movedState;
	}
	
	public int[][] getBoardState(){
		return this.boardState;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public boolean getMovedState() {
		return this.movedState;
	}
	
}
