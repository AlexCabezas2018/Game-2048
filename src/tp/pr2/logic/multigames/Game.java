//Practica 3
//Alejandro Cabezas Garriguez
//Sergio Santa Clotilde Ruiz
/**
 * Paquete que define los distintos tipos de juego
 */
package tp.pr2.logic.multigames;
import tp.pr2.logic.multigames.GameType;
import tp.pr2.logic.*;

import java.io.*;
import java.util.Random;
import Excepciones.*;

public class Game{
	/**
	 * Clase que define las mecanicas generales del juego.
	 * @param Tablero
	 * @param puntuacion
	 * @param reglasDelJuego
	 */
	private static final String nuevalinea = System.getProperty("line.separator").toString();
	private Board board;
	private int size, initCells;
	private Random myRandom;
	private MoveResult total;
	private boolean posibleMov; //Determina si la partida ha terminado o no
	private GameStateStack undoes = new GameStateStack();
	private GameStateStack redoes = new GameStateStack();
	private GameRules currentRules;
	private GameType gametype;
	

	public Game(GameType type, int size, int initCells, long semilla){
		this.currentRules = type.getRules();
		this.size = size;
		this.board = currentRules.createBoard(size);
		this.initCells = initCells;
		this.myRandom = new Random(semilla); //Generamos un random de la semilla.
		currentRules.initBoard(this.board, initCells, this.myRandom); //Una vez creado el tablero y la semilla, introducimos el valor en una posi Random
		this.total = new MoveResult(0, false);
		this.posibleMov = true; //inicializamos a true porque al inicio la partida no ha terminado.
		this.gametype = type;
		
	}
	public Board getBoard() {
		return this.board;
	}
	public boolean getPosibleMov() {
		return this.posibleMov;
	}
	public void setPosibleMov(boolean b) {
		this.posibleMov = b;
	}
	public void changeBoard(GameType newType, int tam, int celsI, long semilla) {
		/**
		 * Cambia el tablero por otro nuevo basado en las reglas del juego.
		 */
		/*Cambia el tablero, cambiando asi sus atribiutos.*/
		this.currentRules = newType.getRules();
		this.initCells = celsI;
		this.gametype = newType;
		this.board = this.currentRules.createBoard(tam);
		this.currentRules.initBoard(this.board, initCells, new Random(semilla));
		this.total = new MoveResult(0, false);
		this.undoes = new GameStateStack();
		this.redoes = new GameStateStack();
	}
	
	public void loadBoard(Board newBoard, GameType type, int initCels, int puntuacion) {
		/**
		 * Carga el tablero a partir de un archivo.
		 */
		this.board = newBoard;
		this.gametype = type;
		this.size = this.board.getboardSize();
		this.currentRules = type.getRules();
		this.initCells = initCels;
		this.myRandom = new Random();
		this.undoes = new GameStateStack();
		this.redoes = new GameStateStack();
		this.total = new MoveResult(puntuacion, false);
	}
	
	public void move(Direction dir) throws gameOverException{
		/**
		 * @throws gameOverException
		 * Ejecuta todo el proceso que conlleva un movimiento.
		 */
		MoveResult partida;
		int puntos = this.total.getScore(); //Guardamos los puntos que teniamos antes
		GameState aux = this.getState();
		partida = this.board.executeMove(dir, this.currentRules); //Ejecutamos el mov.
		if(!this.redoes.isEmpty()) { //Si se realizan nuevos movimientos, el array de redoes tiene que reiniciarse
			redoes = new GameStateStack();
		}
		this.total = new MoveResult(partida.getScore() + puntos, partida.getMoved()); //Creamos un nuevo moveResult con los puntos + antiguos
		if(this.currentRules.win(this.board)) {throw new gameOverException("Enhorabuena, has ganado!");}
		if(this.board.isFull()) {
			this.posibleMov = currentRules.lose(this.board); //Comprobamos si la partida ha terminado
		}
		if(this.posibleMov) {
			if(this.total.getMoved()) {
				//Si no ha terminado, comprobamos si ha habido movimiento para saber si debemos introducir un nuevo numero
				currentRules.initBoard(this.board, 1, this.myRandom);
				this.undoes.push(aux);
				if(this.board.isFull()) {
					this.posibleMov = currentRules.lose(this.board); //Volvemos a comprobarlo porque al introducir el num no sabemos si la partida ha acabado	
					if(!this.posibleMov) {
						throw new gameOverException("Lo siento, has perdido.");
					}
				}
			}
		}
		else {throw new gameOverException("Lo siento, has perdido.");}
		
	}
	
	
	public void reset(){ //Reinicia los atributos del objeto
		/**
		 * Reinicia el juego
		 */
		this.board = new Board(this.size);
		currentRules.initBoard(this.board, this.initCells, this.myRandom);
		this.total = new MoveResult(0 ,false);
		this.posibleMov = true;
		this.undoes = new GameStateStack();
		this.redoes = new GameStateStack();
	}
	
	public String toString() { //Juntamos los toString de las clases
		String juego = "";
		juego += this.board.toString();
		juego += System.lineSeparator();
		juego += "Best: " + currentRules.getWinValue(this.board) + "            " + "Score: " + this.total.getScore();
		return juego;
			
	}
	public void undo() throws EmptyStackException {
		/**
		 * @throws EmptyStackException
		 * Deshace un movimiento
		 */
		if(!this.undoes.isEmpty()) {System.out.println("> Undoing one move...");}
		GameState s = this.undoes.pop(); //Sacamos del array de undoes el tablero anterior
		this.redoes.push(this.getState()); //Lo metemos en el de redoes
		this.setState(s); //Lo establecemos principal
	}
	public void redo() throws EmptyStackException {
		/**
		 * @throws EmptyStackException
		 * rehace una movimiento
		 */
		if(!this.redoes.isEmpty()) {System.out.println("> Redoing one move...");}
		GameState s = this.redoes.pop(); //sacamos del array de redoes
		this.undoes.push(this.getState()); //lo metemos en el de undoes
		this.setState(s); //Lo hacemos principal
	}
	
	
	public GameState getState() { //Devuelve el GameState resultante de la jugada.
		/**
		 * @returns GameState
		 * Devuelve el estado resultante de la jugada.
		 */
		return new GameState(this.board.getState(), this.total.getScore(), this.total.getMoved());
	}
	
	public void setState(GameState aState) { //Introduce el estado.
		/**
		 * @param EstadoDelJuego
		 * Introduce un tablero y lo coloca como tablero principal
		 */
		this.board.setState(aState.getBoardState());
		this.total = new MoveResult(aState.getScore(),  aState.getMovedState());
	}
	
	public boolean notWinYet() {
		/**
		 * @return boolean
		 * Devuelve si la partida ha terminado o no
		 */
		return this.posibleMov && !this.currentRules.win(this.board);
	}
	
	public void store(BufferedWriter bw) throws IOException{ //Bloque try-with-resources
		/**
		 * @throws IOException
		 * Guarda una partida
		 */
		this.board.store(bw);
		bw.write(nuevalinea);
		bw.write(this.initCells + " " + this.total.getScore() + " " + this.gametype.externalise());
	}
	
	public GameType load(BufferedReader in) throws FileNotFoundException, IOException, NumberFormatException, FileContentsException, ArrayIndexOutOfBoundsException{
		/**
		 * @throw FileNotFoundException
		 * @throw IOException
		 * @throw NumberFormatException
		 * @throw FileContentsException
		 * @throw ArrayIndexOutOfBoundsException
		 * @return TipoDeJuego
		 * 
		 * Carga una partida a partir de un archivo de texto.
		 * 
		 */
		GameType playMode = null;
		Board auxBoard = this.board.copy();
		if(!in.readLine().equals("")) {throw new FileContentsException("No se ha mantenido el formato de archivo.");} //Comprueba que hay un espacio entre "this game.." y el tablero
		auxBoard.load(in);
		String[] lineaAux = in.readLine().split(" ");
		if(lineaAux.length > 3 || lineaAux.length < 3) {throw new FileContentsException("No se ha leido la puntuacion y el tipo de juego correctamente");}
		int iCels = Integer.parseInt(lineaAux[0]);
		int puntuacion = Integer.parseInt(lineaAux[1]);
		playMode = GameType.parse(lineaAux[2]);
		if(playMode != null) {
			this.loadBoard(auxBoard, playMode, iCels, puntuacion);
		}
		else {throw new FileContentsException("No se ha leido un tipo de juego correcto.");}
		
		return playMode;
	}
}
