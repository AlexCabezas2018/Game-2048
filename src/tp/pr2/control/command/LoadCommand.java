//Practica 3
//Alejandro Cabezas Garriguez
//Sergio Santa Clotilde Ruiz
package tp.pr2.control.command;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import Excepciones.CommandExecuteException;
import Excepciones.FileContentsException;
import Excepciones.ParseCommandException;
import tp.pr2.logic.multigames.Game;
import tp.pr2.logic.multigames.GameType;

public class LoadCommand extends Command {
	private String file;
	public LoadCommand(String fileName) {
		super("load", "cargar una partida a partir de un archivo");
		this.file = fileName;
	}
	
	public boolean execute(Game game) throws CommandExecuteException{
		try(BufferedReader br = new BufferedReader(new FileReader(this.file))){
			if(!br.readLine().equals("This file stores a saved 2048 game")) {
				throw new FileContentsException("El archivo no empieza de la forma correcta");
			}
			GameType g = game.load(br);
			System.out.println(g.toString() + " has been loaded successfully.");
			return true;
		}
		catch(FileNotFoundException e) {
			throw new CommandExecuteException("No se ha encontrado el archivo");
		}
		catch(IOException e) {
			throw new CommandExecuteException("Se ha producido un error en la lectura del archivo.");
		}
		catch(FileContentsException e) {
			throw new CommandExecuteException(e.getMessage());
		}
		catch(ArrayIndexOutOfBoundsException e) {
			throw new CommandExecuteException("El tablero no esta bien estructurado.");
		}
		catch(NumberFormatException e) {
			throw new CommandExecuteException("El archivo contenia letras en lugares donde no se podia.");
		}
	}
	
	public Command parse(String[] commandWords, Scanner sc) throws ParseCommandException {
		if(commandWords[0].toLowerCase().equals("load") && commandWords.length == 2) {
			return new LoadCommand(commandWords[1]);	
		}
		else if(commandWords[0].toLowerCase().equals("load") && commandWords.length == 1) {
			throw new ParseCommandException("Load debe ir acompañado por un nombre de archivo existente.");
		}
		else return null;
	}
	
}
