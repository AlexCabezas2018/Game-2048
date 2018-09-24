//Practica 3
//Alejandro Cabezas Garriguez
//Sergio Santa Clotilde Ruiz
package tp.pr2.control.command;

import tp.pr2.logic.multigames.Game;
import Excepciones.*;

public class UndoCommand extends NoParamsCommand {
	public UndoCommand() {
		super("undo", "deshace un movimiento");
	}
	
	public boolean execute(Game game) throws CommandExecuteException {
		try {
			game.undo();
			return true;
		}
		catch(EmptyStackException e) {
			throw new CommandExecuteException("No hay movimientos para deshacer!");
		}
	}
}
