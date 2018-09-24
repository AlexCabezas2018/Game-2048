//Practica 3
//Alejandro Cabezas Garriguez
//Sergio Santa Clotilde Ruiz

package tp.pr2.control.command;

import java.util.Scanner;
import tp.pr2.logic.Direction;
import tp.pr2.logic.multigames.Game;
import Excepciones.*;

public class MoveCommand extends Command{
	private Direction n;
	public MoveCommand(Direction n) {
		super("move", "mueve en las 4 direcciones: UP, DOWN, LEFT, LEFT");
		this.n = n;
	}
	public Command parse(String[] commandWords, Scanner sc) throws ParseCommandException {
		if(commandWords.length == 2 && commandWords[0].equals("move")) {
			commandWords[1] = commandWords[1].toUpperCase();
				for(Direction c:Direction.values()) {
					if(commandWords[1].equals(c.toString())) {
						return new MoveCommand(c);
					}
				}
			throw new ParseCommandException(commandWords[1] + " no es una direccion válida.");
		}
		else if(commandWords.length == 1 && commandWords[0].equals("move")) {
			throw new ParseCommandException("Move tiene que ir "
										+ " acompañado por las direcciones UP, DOWN, LEFT y RIGHT");
		}
		else return null;
	}
	
	public boolean execute(Game game) throws gameOverException { //De momento no asimilamos las excepciones. (GameOverException)
		game.move(this.n);
		return true;
	}

}
