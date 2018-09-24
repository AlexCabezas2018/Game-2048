//Practica 3
//Alejandro Cabezas Garriguez
//Sergio Santa Clotilde Ruiz
package tp.pr2.logic;

import tp.pr2.logic.Cell;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import Excepciones.FileContentsException;
import tp.pr2.auxiliar.MyStringUtils;
import tp.pr2.logic.multigames.*;

public class Board {
	private static final String nuevalinea = System.getProperty("line.separator").toString(); //añade un salto de linea al String
	private Cell[][] board;
	private int boardSize;

	public Board(int size) {
		/**
		 * @param tamaño
		 * Clase que define las mecanicas del tablero.
		 */
		this.boardSize = size;
		this.board = new Cell[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				this.board[i][j] = new Cell();
			}
		}
	}
	public int getboardSize() {
		/**
		 * @return Integer
		 * Devuelve el tamaño del tablero.
		 */
		return this.boardSize;
	}
	
	public Cell getOneCell(Position pos) {
		/**
		 * @return Cell
		 * Devuvelve una de las celdas del tablero.
		 */
		return this.board[pos.getFila()][pos.getColumna()];
	}
	
	public void setCell(Position pos, int value) {
		/**
		 * @param posicion
		 * @param valor
		 * Coloca un valor en una posicion.
		 */
		this.board[pos.getFila()][pos.getColumna()].setValue(value);
	}

	public boolean isFull() {
		/**
		 * @returns boolean
		 * Comprueba si el tablero esta lleno
		 */
		boolean isF = true;
		for(int i = 0; i < this.boardSize; i++) {
			for(int j = 0; j < this.boardSize; j++) {
				if(this.board[i][j].isEmpty()) {
					isF = false;
				}
			}
		}
		return isF;
	}
	
	public String toString() {
		String tablero = "";
		int cellSize = 7;
		String space = " ";
		String vDelimiter = "|";
		String hDelimiter = "-";
		String lineaArriba = MyStringUtils.repeat(hDelimiter, cellSize);
		String line = MyStringUtils.repeat(lineaArriba + "-", this.boardSize);
		tablero += hDelimiter;
		tablero += line + nuevalinea; // Writes upper lines.
		for (int i = 0; i < this.boardSize; i++) {
			for (int j = 0; j < this.boardSize; j++) {
				String n = String.valueOf(this.board[i][j].getValue());
				if(this.board[i][j].isEmpty()) { //Writes the values of the cells
					tablero += vDelimiter + MyStringUtils.centre(space , cellSize);
				}
				else {
					tablero += vDelimiter + MyStringUtils.centre(n, cellSize);
				}
			}
			tablero += vDelimiter + nuevalinea;
			tablero += hDelimiter;
			tablero += line + nuevalinea;
		}
		
		return tablero;

	}
	
	public Board copy() {
		/**
		 * @returns Board
		 * Realiza una copia del tablero.
		 */
		Board aux = new Board(this.boardSize);
		for(int i = 0; i < this.boardSize; i++) {
			for(int j = 0; j < this.boardSize; j++) {
				if(!this.board[i][j].isEmpty()) {
					Position pos = new Position(i, j);
					int value = this.board[i][j].getValue();
					aux.setCell(pos, value);
				}
			}
		}
		
		return aux;
	}
	
	public void trasponer() { //traspone la matriz del tablero.
		/**
		 * Traspone el tablero
		 */
		Board aux = new Board(this.boardSize); //Creamos un tablero donde colocar el tablero traspuesto
		for(int i = 0; i < this.boardSize; i++) {
			for(int j = 0; j < this.boardSize; j++) {
				if(!this.board[j][i].isEmpty()) {
					Position pos2 = new Position(i, j); //Almacenamos la posicion
					int value1 = this.board[j][i].getValue(); //el valor tambien, pero el valor invertido.
					aux.setCell(pos2, value1);
					this.board[j][i].setValue(0);
				}
			}
		}
		/*Despues solo volvemos a poner los valores en la matriz original*/
		for(int i = 0; i < this.boardSize; i++) {
			for(int j = 0; j < this.boardSize; j++) {
				Position psi = new Position(i, j);
				int valor = aux.board[i][j].getValue();
				this.setCell(psi, valor);
			}
		}
	}
	/*Igual que trasponer, es decir, misma metodologia pero este metodo intercambia las filas de la matriz*/
	public void invertir() {
		/**
		 * Invierte el tablero
		 */
		Board auxi = new Board(this.boardSize);
		int limite = this.boardSize - 1;
		for(int i = 0; i < this.boardSize; i++) {
			for(int j = 0; j < this.boardSize; j++) {
				if(!this.board[j][i].isEmpty()) {
					Position n = new Position(limite - j,i);
					int valor = this.board[j][i].getValue();
					auxi.setCell(n, valor);
					this.board[j][i].setValue(0);
					
				}	
			}	
		}
		
		for(int i = 0; i < this.boardSize; i++) {
			for(int j = 0; j < this.boardSize; j++) {
				Position psi = new Position(j, i);
				int value = auxi.board[j][i].getValue();
				this.setCell(psi, value);
			}
		}
		
	}
	
	
	
	public void invertirColumnas() { //Igual que el metodo invertir, pero con columnas
		/**
		 * Invierte las columnas del tablero.
		 */
		int limite = this.boardSize-1;
		Board aux = new Board(this.boardSize);
		for(int i = 0; i < this.boardSize; i++) {
			for(int j = 0; j < this.boardSize; j++) {
				if(!this.board[i][j].isEmpty()) {
					Position n = new Position(i,limite - j);
					int valor = this.board[i][j].getValue();
					aux.setCell(n, valor);
					this.board[i][j].setValue(0);
				}	
			}
		}
		
		for(int i = 0; i < this.boardSize; i++) {
			for(int j = 0; j < this.boardSize; j++) {
				Position psi = new Position(i, j);
				int value = aux.board[i][j].getValue();
				this.setCell(psi, value);
			}
		}	
		
	}
	
	
	public MoveResult executeMove(Direction dir, GameRules rules) {
		/**
		 * @param direccion
		 * @param ReglasDelJuego
		 * @returns MoveResult
		 * Dada una direccion y un tipo de juego, realiza el movimiento conforme a ello.
		 */
		MoveResult finalMove = new MoveResult(0, false); //Inicializamos el moveresult, aunque este valor puede cambiar.
		switch(dir) {
		case UP:
			finalMove = this.mergeAndMove(rules);
			break;
		case DOWN://Invertimos, ejecutamos el mov y volvemos a invertir
			this.invertir();
			finalMove = this.mergeAndMove(rules);
			this.invertir();
			break;
		case LEFT:
			this.trasponer(); //Trasponemos, ejecutamos el mov y volvemos a trasponer
			finalMove = this.mergeAndMove(rules);
			this.trasponer();
			break;
		case RIGHT: //trasponemos, invertimos, ejecutamos el mov, trasponemos e invertimo columnas
			this.trasponer(); 
			this.invertir();
			finalMove = this.mergeAndMove(rules);
			this.trasponer();
			this.invertirColumnas();
			
		}
		
		return finalMove;
		}
		
	
	
	public MoveResult mergeAndMove(GameRules rules) {
		/**
		 * @param reglasDelJuego
		 * @return MoveResult
		 * Realiza las transformaciones necesarias para realizar el movimiento. Llamada por executeMove.
		 */
		/*Este algoritmo realiza el movimiento hacia arriba, luego nosotros acomodamos la matriz para que siempre tenga que hacer el mov hacia arriba*/
		int valorNuevo = 0;
		MoveResult total;
		boolean moved = false;
		Board auxTablero = new Board(this.boardSize); //Creamos un tablero auxiliar
			for(int j = 0; j < this.boardSize; j++) {
				int i;
				for(i = 0; i < this.boardSize; i++) {
					if(!this.board[i][j].isEmpty()) {
						Position cN = new Position(i, j); //Si la pos no esta vacia, guardamos sus coords
						int limite = i;
						cN = cN.neighbour(Direction.UP, this.boardSize); //Solicitamos su vecino de arria
						while(limite > 0) { //Dependiendo de que fila estemos observando, analizamos desde esa fila hasta la ultima.
							int x = this.board[i][j].doMerge(this.board[cN.getFila()][cN.getColumna()], rules);
							if(x != 0) { //Si se puede realizar mezcla, la hace pero el valor lo almacena en el aux 
								valorNuevo += x;
								auxTablero.setCell(cN, this.board[i][j].getValue());
								this.board[i][j].setValue(0); //Pone el valor de la mezcla tambien a 0, para que asi al acabar, el tablero queda "limpio" puesto que merge deja a 0 el valor del neighbour
								limite = 0; //Paramos porque esa celda ya no se puede mezclar con mas
								moved = true;
							}
							else if(!this.board[cN.getFila()][cN.getColumna()].isEmpty()) { //Si la celda no esta vacia y se han podido mezclar, entonces esa celda no tiene vecinos con los que juntarse
								int value = this.board[cN.getFila()][cN.getColumna()].getValue();
								auxTablero.setCell(cN, value); //Pero como no tiene vecinos, tambien debemos llevarla al aux para no perderla
								this.board[cN.getFila()][cN.getColumna()].setValue(0); //La ponemos a 0
								limite = 0;
							}
							else {
								cN = cN.neighbour(Direction.UP, this.boardSize); //Si esta vacio, vuelves a calcular el vecino del vecino
							}
							limite--;
						}	
					}
				}/*Guardamos tambien el ultimo valor porque puede ser que una celda no encuentre vecino nunca y por tanto no se guarda.*/
				Position ultimo = new Position(i - 1, j);
				int valor = this.board[i - 1][j].getValue();
				auxTablero.setCell(ultimo, valor);
				this.setCell(ultimo, 0);
			}//copiamos los valores del tablero auxiliar en el tablero original
			for(int i = 0; i < this.boardSize; i++) {
				for(int j = 0; j < this.boardSize; j++) {
					if(!auxTablero.board[i][j].isEmpty()) {
						Position pos = new Position(i, j);
						int valor = auxTablero.board[i][j].getValue();
						this.setCell(pos, valor);
					}
				}
			}
			
		/*Ahora desplazamos hacia arriba el tablero original*/
		for(int j = 0; j < this.boardSize; j++) {
			for(int i = 0; i < this.boardSize; i++) {
				if(!this.board[i][j].isEmpty()) {
					Position actual = new Position(i, j); //guardamos posicion y buscamos vecino
					Position vecino = actual.neighbour(Direction.UP, this.boardSize);
					while(this.board[vecino.getFila()][vecino.getColumna()].isEmpty()) { //Mientras el vecino este vacio
						Cell aux = this.board[vecino.getFila()][vecino.getColumna()]; //Guardamos el valor de la celda
						this.board[vecino.getFila()][vecino.getColumna()] = this.board[actual.getFila()][actual.getColumna()]; //Igualamos
						this.board[actual.getFila()][actual.getColumna()] = aux; //Como resultado hamos sustituido
						Position aux2 = new Position(vecino.getFila(), vecino.getColumna()); 
						actual = aux2; //Cambiamos el valor de actual
						moved = true; //Se ha realizado mov
						vecino = vecino.neighbour(Direction.UP ,this.boardSize); //Recalculamos el vecino
					}
				}				
			}
		}
		
		total = new MoveResult(valorNuevo, moved); //Calculamos el winvalue en Game con las respectivas reglas del juego
		return total;
	}
	
	public boolean Empty(int i, int j){ //Metodo para saber si una posicion de la matriz esta vacia. Se usa en la clase Game
		/**
		 * @param Posicion
		 * @return boolean
		 * Comprueba si la posicion del tablero esta vacia.
		 */
		return this.board[i][j].isEmpty();
	}
	
	public int valueBoard(int i, int j) {
		/**
		 * @param Posicion
		 * @return Integer
		 * Devuelve el valor de una celda dada una posicion.
		 */
		return this.board[i][j].getValue();
	}
	
	public int winValue() {
		/**
		 * @return Integer
		 * Devuelve la mejor puntuacion del juego. 
		 */
		int max = this.board[0][0].getValue();
		for(int i = 0; i < this.getboardSize(); i++) {
			for(int j = 0; j < this.boardSize; j++) {
				if(this.board[i][j].getValue() > max) {
					max = this.board[i][j].getValue();
				}
			}
		}
		return max;
	}
	
	public int[][] getState(){ //Obtenemos el tablero principal
		/**
		 * @return Matriz de enteros
		 * Convierte el tablero (Matriz de Celdas) en una matriz de enteros.
		 */
		int[][]tableroa = new int[this.boardSize][this.boardSize];
		for(int i = 0; i < this.boardSize; i++) {
			for(int j = 0; j < this.boardSize; j++) {
				tableroa[i][j] = this.board[i][j].getValue();
			}
		}
		return tableroa;
	}
	
	public void setState(int[][] aState) { //Cambiamos el tablero actual por el de los parametros
		/**
		 * Coloca la matriz de enteros como tablero principal.
		 */
		for(int i = 0; i < this.boardSize; i++) {
			for(int j = 0; j < this.boardSize; j++) {
				this.board[i][j].setValue(aState[i][j]);
			}
		}
		
	}
	
	public void store(BufferedWriter file) throws IOException{
		/**
		 * @throw IOException
		 * Guarda la partida.
		 */
		file.write(nuevalinea);
		for(int i = 0; i < this.boardSize; i++) {
			file.write(filaString(this.board[i]));
			file.write(nuevalinea);
		}
	}
	
	private static String filaString(Cell[] array) {
		String finals = "";
		for(int i = 0; i < array.length; i++) {
			finals += array[i].getValue() + " ";
		}
		return finals;
	}
	
	public void load(BufferedReader br) throws FileContentsException, IOException, NumberFormatException, ArrayIndexOutOfBoundsException{
		/**
		 * @throw FileContentsException
		 * @throw IOException
		 * @throw NumberFormatException
		 * @throw ArrayIndexOutOfBoundsException
		 * Carga la partida
		 */
		String[] aux = br.readLine().split(" ");
		int tam = aux.length;
		Cell[][] aux2 = new Cell[aux.length][aux.length];
		
		for(int i = 0; i < tam; i++) {
			if(aux[0].equals("")) {throw new FileContentsException("No se ha mantenido el formato del archivo (el tablero esta mal escrito).");}
			if(aux.length > tam) {throw new FileContentsException("El tablero no esta bien estructurado.");}
			for(int j = 0; j < tam; j++) {
				int valorNum = Integer.parseInt(aux[j]);
				aux2[i][j] = new Cell();
				aux2[i][j].setValue(valorNum);
			}
			aux = br.readLine().split(" ");
		}
		
		if(!aux[0].equals("")) {throw new FileContentsException("No se ha mantenido el formato del archivo.");}
		
		this.board = aux2;
		this.boardSize = tam;
		
		/*La primera linea va a determinar el tamaño del tablero. Si otra linea tiene mas numeros, saltara un filecontents
		 * si es mas pequeña saltara una arrayindexout, en ambos casos, no seria valido.*/
		
	}
}
