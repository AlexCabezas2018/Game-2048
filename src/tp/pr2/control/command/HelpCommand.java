//Practica 3
//Alejandro Cabezas Garriguez
//Sergio Santa Clotilde Ruiz
package tp.pr2.control.command;

import tp.pr2.logic.multigames.Game;

public class HelpCommand extends NoParamsCommand {

	public HelpCommand() {
		super("help", "Muestra la ayuda por pantalla.");
	}
	
	public boolean execute(Game game){
		String g = CommandParser.showHelp();
		System.out.println(g);
		return false;
	}

}
