//Practica 3
//Alejandro Cabezas Garriguez
//Sergio Santa Clotilde Ruiz
package tp.pr2.control.command;
import tp.pr2.logic.multigames.Game;

public class ResetCommand extends NoParamsCommand {

	public ResetCommand() {
		super("reset", "Reinicia la partida.");
	}
		
	public boolean execute(Game game) {
		game.reset();
		return true;
	}
}
