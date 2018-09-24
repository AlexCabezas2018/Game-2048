//Practica 3
//Alejandro Cabezas Garriguez
//Sergio Santa Clotilde Ruiz
package tp.pr2.control.command;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import Excepciones.*;
import tp.pr2.auxiliar.MyStringUtils;
import tp.pr2.logic.multigames.Game;

public class SaveCommand extends Command{
	private String fileName;
	public SaveCommand(String file) {
		super("save", "guarda la partida actual.");
		fileName = file;	
	}
	public boolean execute(Game game) throws CommandExecuteException {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.fileName))){
			bw.write("This file stores a saved 2048 game");
			bw.write(System.getProperty("line.separator"));
			game.store(bw);
			System.out.println("Juego guardado con éxito");
			return false;
		}
		catch(IOException e) {
			throw new CommandExecuteException("Error en la escritura de archivo");
		}
	}
	public Command parse(String[] commandWords, Scanner sc) throws ParseCommandException {
		if(commandWords[0].toLowerCase().equals("save") && commandWords.length == 2) {
			String archivoFinal = confirmFileNameStringForWrite(commandWords[1] ,sc);
			return new SaveCommand(archivoFinal);	
		}
		else if(commandWords[0].toLowerCase().equals("save") && commandWords.length == 1) {
			throw new ParseCommandException("Save debe ir acompañado por un nombre de archivo.");
		}
		else return null;
	}
	

	private static String confirmFileNameStringForWrite(String files, Scanner in) throws ParseCommandException {
		String loadName = files;
		boolean filename_confirmed = false;
		while(!filename_confirmed) {
			if(MyStringUtils.validFileName(loadName)) {
				File file = new File(loadName);
				if(!file.exists()) {
					filename_confirmed = true;
				}
				else {
					loadName = getLoadName(files, in);
					filename_confirmed = true;
				}
			}
			else {
				throw new ParseCommandException("El nombre del archivo no es valido.");
			}
		}
		return loadName;
	}
	
	public static String getLoadName(String fileNameString, Scanner in) {
		String newFilename = null;
		boolean yesOrNo = false;
		while(!yesOrNo) {
			try {
				System.out.println("El achivo ya existe, ¿desea reemplazarlo? Y/N:");
				String[] respuesta = in.nextLine().toLowerCase().trim().split("\\s+");
				if(respuesta.length == 1) {
					switch(respuesta[0]) {
					case "y":
						yesOrNo = true;
						newFilename = new String(fileNameString);
						break;
					case "n":
						yesOrNo = true;
						System.out.println("Introduce otro archivo distinto.");
						newFilename = in.nextLine();
						while(!MyStringUtils.validFileName(newFilename) || new File(newFilename).exists()) {
							System.out.println("Nombre no válido, introduzca un nombre válido: ");
							newFilename = in.nextLine();
						}
						break;
					default:
						throw new YesOrNoException("La respuesta debe ser 'Y' para si y 'N' para no!");
					}
				}
				else {
					throw new YesOrNoException("La respuesta debe contener una palabra!");
				}
			}
			catch(YesOrNoException e) {
				System.out.println(e.getMessage());
			}
		}
		return newFilename;
	}
	
}
