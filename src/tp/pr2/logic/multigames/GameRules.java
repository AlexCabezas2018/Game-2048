//Practica 3
//Alejandro Cabezas Garriguez
//Sergio Santa Clotilde Ruiz
package tp.pr2.logic.multigames;
import tp.pr2.logic.*;
import java.util.Random;

public interface GameRules {
	/**
	 * 
	 * @param board
	 * @param pos
	 * @param rand
	 * Interfaz que indica cómo deben comportarse los tipos de juego y qué deben hacer
	 */
	void addNewCellAt(Board board, Position pos, Random rand);
	int merge(Cell self, Cell other);
	int getWinValue(Board board);
	boolean win(Board board);
	
	default boolean lose(Board board) { 
		/**
		 * @returns boolean
		 * Devuelve si has perdido la partida.
		 */
		Direction[] direcciones = Direction.values();
		int dir = 0;
		while(dir < direcciones.length) {
			for(int i = 0; i < board.getboardSize(); i++) {
				for(int j = 0; j< board.getboardSize(); j++) {
					Position posActual = new Position(j, i);
					Position posVecino = posActual.neighbour(direcciones[dir], board.getboardSize());
					if(posVecino.getFila() != posVecino.getFila() || posVecino.getColumna() != posActual.getColumna()) {
						Cell actual = board.getOneCell(posActual);
						Cell vecino = board.getOneCell(posVecino);
						if(this.canMergeNeighbours(actual, vecino)) {return true;}
					}
					
				}
			}
			dir++;
		}
		return false;
		
	} //Default
	
	default boolean canMergeNeighbours(Cell a, Cell b) {
		/**
		 * @returns boolean
		 * Devuelve si dos celdas pueden juntarse. Hay tipos de juego que no siguen estas reglas.
		 */
		if(a.getValue() == b.getValue()) {
			return true;
		}
		return false;
	}
	
	default Board createBoard(int size) {
		/**
		 * @param tam
		 * Crea un nuevo tablero de tamaño tam
		 */
		return new Board(size); 
	} //Default
	
	default void initBoard(Board board, int numCells, Random rand) {
		/**
		 * @param numeroDeCelas
		 * @param Tablero
		 * @param semilla
		 * Coloca numerodeCeldas en Tablero segun semilla
		 */
		for(int i = 0; i < numCells; i++) {
			addNewCell(board, rand);
		}
		
	} //default
	
	default void addNewCell(Board board, Random rand) {
		/**
		 * @param tablero
		 * @param semilla
		 * Añade una casilla a tablero segun semilla
		 */
		int contador = 0;
		Position[] posLibres = new Position[board.getboardSize() * board.getboardSize()];
		for(int i = 0; i < board.getboardSize(); i++) { // Rellena el array de posLibres.
			for(int j = 0; j < board.getboardSize(); j++) {
				if(board.Empty(i, j)) {
					posLibres[contador] = new Position(i, j);
					contador++;
				}
			}
		}
		for(int i = contador; i > 0; i--) { //Baraja el contenido del array
			Utilities.swap(posLibres,i - 1, rand.nextInt(i));
		}
		int numRandom = rand.nextInt(contador);
		Position randomPos = posLibres[numRandom];
		addNewCellAt(board, randomPos, rand);	//Escoge una y le introduce un valor
	}
}
