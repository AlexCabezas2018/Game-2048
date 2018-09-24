//Practica 3
//Alejandro Cabezas Garriguez
//Sergio Santa Clotilde Ruiz
package tp.pr2.control.command;

import java.util.Scanner;
import Excepciones.*;

public class CommandParser {
	
	private static Command[] availableCommands = { new HelpCommand(), new ResetCommand(),
	new ExitCommand(), new MoveCommand(null) , new UndoCommand(), new RedoCommand(), new PlayCommand(), new SaveCommand(null),
	new LoadCommand(null)};
	
	private static final String nuevalinea = System.getProperty("line.separator").toString();
	
	public static Command parseCommand(String[] cmd, Scanner sc) throws ParseCommandException {
		if(cmd.length == 0 || cmd.length > 2) { //Comprobamos que el usuario ha introducido un comando valido.
			throw new ParseCommandException("Has introducido valores innecesarios");
		}
		else {
			Command cm;
			for(Command c:availableCommands) {
				cm = c.parse(cmd, sc);
				if(cm != null) return cm;
			}
			return null;
		}
	}
	
	public static String showHelp() { //Muestra la ayuda.
		String s = "";
		for(Command c:availableCommands) {
			s+= c.helpText() + nuevalinea;
		}
		return s;
	}
}
