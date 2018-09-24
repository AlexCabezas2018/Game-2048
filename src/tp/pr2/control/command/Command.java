//Practica 3
//Alejandro Cabezas Garriguez
//Sergio Santa Clotilde Ruiz
/**
 * Paquete que define los comandos que introduce el usuario
 */
package tp.pr2.control.command;

import Excepciones.*;
import tp.pr2.logic.multigames.Game;
import java.util.Scanner;

public abstract class Command {
	private String helpText;
	private String commandText;
	protected final String commandName;
	public Command(String commandInfo, String helpInfo){
		commandText = commandInfo;
		helpText = helpInfo;
		String[] commandInfoWords = commandText.split("\\s+");
		commandName = commandInfoWords[0];
	}
	
	public abstract boolean execute(Game game) throws CommandExecuteException, gameOverException;
	public abstract Command parse(String[] commandWords, Scanner sc) throws ParseCommandException; //y gameOverException
	public String helpText(){return " " + commandText + ": " + helpText;}

}
