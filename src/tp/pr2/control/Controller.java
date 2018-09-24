//Practica 3
//Alejandro Cabezas Garriguez
//Sergio Santa Clotilde Ruiz

/**
 * Paque que define al controlador del juego, es decir, la interaccion del juego y el usuario
 */
package tp.pr2.control;
import Excepciones.*;
import tp.pr2.logic.multigames.*;
import tp.pr2.control.command.*;

import java.util.Scanner;

public class Controller {
	private Game game;
	private Scanner in;
	
	public Controller(GameType type, long semilla, int size, int celdasIniciales) {
		this.game = new Game(type ,size, celdasIniciales, semilla); 
		this.in = new Scanner(System.in);
	}
	
	public void run() {
		System.out.println(this.game.toString());
		while(this.game.notWinYet()){ //Mientras el movimiento sea posible, y no hayamos "ganado"...
			System.out.print("Command > ");
			String words[] = this.in.nextLine().toLowerCase().trim().split(" ");
			try {
				Command com = CommandParser.parseCommand(words, this.in);
				if(com != null) {
					try {
						if(com.execute(game)) {
							System.out.println(this.game.toString());
						}
					}
					catch(CommandExecuteException e) {
						System.out.println(e.getMessage());
					}
					catch(gameOverException e) {
						System.out.println(this.game.toString());
						System.out.println(e.getMessage());
					}
				}
				else {
					System.out.println("> Comando no valido");
				}
			}
			catch(ParseCommandException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}		

