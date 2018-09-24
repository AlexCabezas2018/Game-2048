//Practica 3
//Alejandro Cabezas Garriguez
//Sergio Santa Clotilde Ruiz
package tp.pr2.logic.multigames;

import java.util.Random;

import tp.pr2.logic.Board;
import tp.pr2.logic.Cell;
import tp.pr2.logic.Position;

public class RulesInv implements GameRules{
	/**
	 * Clase que define el modo de 2048 inverso
	 */
	private final int winScore = 1;
	
	public void addNewCellAt(Board board, Position pos, Random rand) {
		int valor = rand.nextInt(10);
		if(valor < 1) {valor = 1024;}
		else {valor = 2048;}
		board.setCell(pos,valor);	
	}

	public int merge(Cell self, Cell other) {
		int aux = self.getValue();
		if(self.getValue() == other.getValue()) {
			int x = self.getValue() - (other.getValue()/2);
			self.setValue(x);
			other.setValue(0);
			int potencia = 0;
			while(aux % 2 == 0) { //Con esto obtenemos el exponente al que esta elevado 2 para llegar a aux.
				aux /= 2;
				potencia++;
			}
			return puntuacion(potencia); //Aqui hay que poner la funcion que devuelve la puntuacion en funcion de los puntos generados.
		}
		return 0;
	}
	
	public int getWinValue(Board board) {
		int min = 2048;
		for(int i = 0; i < board.getboardSize(); i++) {
			for(int j = 0; j < board.getboardSize(); j++) {
				if(board.valueBoard(i, j) < min && !board.Empty(i, j)) {
					min = board.valueBoard(i, j);
				}
			}
		}
		return min;
	}
	
	public boolean win(Board board) {
		return getWinValue(board) == winScore;
	}
	
	public int puntuacion(int k) {
		return (int)Math.pow(2, 12-k); //En funcion de k, obtiene la puntuacion.
	}
}
