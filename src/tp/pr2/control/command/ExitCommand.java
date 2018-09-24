//Practica 3
//Alejandro Cabezas Garriguez
//Sergio Santa Clotilde Ruiz
package tp.pr2.control.command;
import tp.pr2.logic.multigames.Game;

public class ExitCommand extends NoParamsCommand {

	public ExitCommand() {
		super("exit", "Salir de la partida.");
	}
	
	public boolean execute(Game game){
		game.setPosibleMov(false);
		return false;
	}
	
}
	