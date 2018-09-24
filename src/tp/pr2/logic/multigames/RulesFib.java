//Practica 3
//Alejandro Cabezas Garriguez
//Sergio Santa Clotilde Ruiz
package tp.pr2.logic.multigames;

import java.util.Random;
import tp.pr2.auxiliar.MyMathsUtils;
import tp.pr2.logic.Board;
import tp.pr2.logic.Cell;
import tp.pr2.logic.Position;

public class RulesFib implements GameRules{
	/**
	 * Clase que define el modo de juefo de Fibonnacci
	 */
	private final int winScore = 144;
	public void addNewCellAt(Board board, Position pos, Random rand) {
		int valor = rand.nextInt(10);
		if(valor < 1) {valor = 2;}
		else {valor = 1;}
		board.setCell(pos,valor);
	}
	public int merge(Cell self, Cell other) {
		int aux = self.getValue() + other.getValue();
		if(estaEnFib(aux) && !other.isEmpty()) { //true si el valor esta en la secuencia de fibonacci
			self.setValue(aux);
			other.setValue(0);
			return self.getValue();
		}
		return 0;
	}
	public int getWinValue(Board board) {
		return board.winValue();
	}
	public boolean win(Board board) {
		return getWinValue(board) == winScore;
	}
	
	public boolean estaEnFib(int numero) {
		int valor = 2; //Es el minimo numero con el que vamos a comparar.
		while(valor < numero) {
			valor = MyMathsUtils.nextFib(valor);
		}
		//En este punto del bucle, valor sera igual o mayor que el numero. Si es mayor, es que no esta.
		return valor == numero;
	}
	
	public boolean canMergeNeighbours(Cell a, Cell b) {
		if(estaEnFib(a.getValue() + b.getValue())) {
			return true;
		}
		return false;
	}

}
