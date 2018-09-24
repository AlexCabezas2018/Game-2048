//Practica 3
//Alejandro Cabezas Garriguez
//Sergio Santa Clotilde Ruiz
package tp.pr2.logic;

import Excepciones.*;

public class GameStateStack {
	private static final int CAPACITY = 20;
	private GameState[] gameSt;
	private boolean empty; //Representa si hay un hueco libre, es decir, si array no esta lleno
	private int index;
	
	public GameStateStack() {
		this.gameSt = new GameState[CAPACITY];
		this.empty = true;
		this.index = 0;
	}
	
	public GameState pop() throws EmptyStackException {
		if(!this.isEmpty()) {
			GameState aux = this.gameSt[this.index - 1]; //Aqui nunca entra index = 0, entra minimo index = 1
			this.index--;
			this.empty = true; //Si sacamos cosas del array, este nunca estara lleno
			return aux;
		}
		else throw new EmptyStackException();
	}
	
	public void push(GameState state) {
		if(!this.empty) { //La pila esta llena
		    for(int i = 0; i < CAPACITY - 1; i++) { //Movemos el array hacia la izquierda, eliminando el primero v[0]
		        this.gameSt[i] = this.gameSt[i + 1];
		    }
		    this.gameSt[this.index - 1] = state; //es index - 1 porque en este momento index = capacity y daria arrayindexout
		}
		else { //Si no esta lleno
			this.index++;
			this.gameSt[this.index - 1] = state;
			if(this.index == CAPACITY) {this.empty = false;}
		}
	}
	
	public boolean isEmpty() { return this.index == 0; }	

}
