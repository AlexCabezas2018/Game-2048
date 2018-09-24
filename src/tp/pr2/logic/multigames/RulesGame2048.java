//Practica 3
//Alejandro Cabezas Garriguez
//Sergio Santa Clotilde Ruiz
package tp.pr2.logic.multigames;

import java.util.Random;
import tp.pr2.logic.Board;
import tp.pr2.logic.Cell;
import tp.pr2.logic.Position;

public class RulesGame2048 implements GameRules{
	/**
	 * Clase que define el modo de juego original de 2048
	 */
	private final int winScore = 2048;
	public void addNewCellAt(Board board, Position pos, Random rand) {
		int valor = rand.nextInt(10);
		if(valor < 3) {valor = 4;}
		else {valor = 2;}
		board.setCell(pos,valor);
	}
	
	public int getWinValue(Board board) {
		return board.winValue();
	}
	
	public boolean win(Board board) {
		return getWinValue(board) == winScore;
	}
	
	public int merge(Cell self, Cell another) {
		if(self.getValue() == another.getValue()) {
			int x = self.getValue() + another.getValue();
			self.setValue(x);
			another.setValue(0);
			return self.getValue();
		}
		return 0;
	}

}
