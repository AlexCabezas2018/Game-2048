//Practica 3
//Alejandro Cabezas Garriguez
//Sergio Santa Clotilde Ruiz
package tp.pr2.control.command;
import Excepciones.*;
import tp.pr2.logic.multigames.Game;

public class RedoCommand extends NoParamsCommand {
	public RedoCommand() {
		super("redo", "vuelve a realizar un movimiento ya realizado.");
	}
	
	public boolean execute(Game game) throws CommandExecuteException {
		try {
			game.redo();
			return true;
		}
		catch(EmptyStackException e) {
			throw new CommandExecuteException("No se pueden rehacer mas movimientos!");
		}
	}
}
