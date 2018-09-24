//Practica 2
//Alejandro Cabezas Garriguez
//Sergio Santa Clotilde Ruiz
package tp.pr2.control.command;

import java.util.Scanner;


public abstract class NoParamsCommand extends Command {
	public NoParamsCommand(String commandInfo, String helpInfo) {
		super(commandInfo, helpInfo);
	}
	
	public Command parse(String[] commandWords, Scanner sc) {
		if(commandWords[0].equals(commandName) && commandWords.length == 1) {
			return this;
		} else return null;
	}

}
