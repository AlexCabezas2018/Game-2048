//Practica 3
//Alejandro Cabezas Garriguez
//Sergio Santa Clotilde Ruiz
package tp.pr2.control.command;

import java.util.Random;
import java.util.Scanner;
import tp.pr2.logic.multigames.*;
import Excepciones.*;

public class PlayCommand extends Command {
	private GameType n;
	private int tamD = 4;
	private int celsD = 2;
	private long semilla = new Random().nextInt(1000);
	
	public PlayCommand() {
		super("play", "elige distintos modos de juego: " + GameType.externaliseAll());
		n = null;
	}
	
	public boolean execute(Game game) {
		game.changeBoard(n, this.tamD, this.celsD, this.semilla);
		
		/*Reasignamos los valores anteriores.*/
		this.tamD = 4;
		this.celsD = 2;
		this.semilla = new Random().nextInt(1000);
		return true;
		//x.close();
	}
	
	public Command parse(String[] commandWords, Scanner sc) throws ParseCommandException{
		if(commandWords.length == 2 && commandWords[0].equals("play")) {
			n = GameType.parse(commandWords[1]);
			if(n != null) {
				int tam, cels;
				long seed;
				boolean ok = false;
				while(!ok) {
					try {
						tam = this.chooseSize(sc);
						if(tam <= 0) {throw new NegativeNumberException("El tamaño no puede ser un numero negativo");}
						else if(tam == 1){
							throw new NoValidNumbersException("El numero debe ser mayor que 1");
						}
						else{ok = true; this.tamD = tam;}
					}
					catch(NumberFormatException e) {
						System.out.println("Por favor, introduzca un numero, no un caracter.");
					}
					catch(NegativeNumberException e) {
						System.out.println(e.getMessage());
					}
					catch(NoValidNumbersException e) {
						System.out.println(e.getMessage());
					}
						
				}
				ok = false;
				while(!ok) {
					try {
						cels = this.chooseCels(sc);
						if(cels <= 0) {throw new NegativeNumberException("Las celdas iniciales no pueden ser negativas");}
						else {ok = true; this.celsD = cels;}
					}
					catch(NumberFormatException e) {
						System.out.println("Por favor, introduzca un numero, no un caracter.");
					}
					catch(NegativeNumberException e) {
						System.out.println(e.getMessage());
					}
				}
				ok = false;
				while(!ok) {
					try {
						seed = this.chooseSeed(sc);
						if(seed <= 0) {throw new NegativeNumberException("No se permiten semillas menores que 0");}
						else {ok = true; this.semilla = seed;}
					}
					catch(NumberFormatException e) {
						System.out.println("La semilla debe ser un numero");
					}
					catch(NegativeNumberException e) {
						System.out.println(e.getMessage());
					}
				}			
				
				if(this.celsD > this.tamD * this.tamD) {
					throw new ParseCommandException("Las celdas iniciales no pueden ser mayor que el tamaño "
							+ "total del tablero.");
				}

				return this; //Devolvemos el mismo objeto que llama porque cambiamos sus atributos internamente
			}
			else {
				throw new ParseCommandException("Juego inválido. Juegos válidos: " + GameType.externaliseAll());
			}
				
		} else if(commandWords.length < 2 && commandWords[0].equals("play")) {
			throw new ParseCommandException("Play debe ir seguido por "  + GameType.externaliseAll());
		}
		else return null;
		
	
	}
	
	 /*Aqui divido en metodos que lanzan excepciones y luego en parse capturo cada uno de ellos
	  * Esto lo hago asi para que, cuando cazo la excepcion, rellamar al metodo para volver a pedir los datos.*/
	
	private int chooseSize(Scanner x) throws NumberFormatException{
		String tam;
		System.out.print("> Tamaño del nuevo tablero: ");
		tam = x.nextLine();
		if(tam.equals("")) {
			System.out.println("Usando valor por defecto: " + tamD);
			return tamD;
		}
		else {
			int t = Integer.parseInt(tam);
			return t;
		}
	}
	
	private int chooseCels(Scanner x) throws NumberFormatException{
		String celsI;
		System.out.print("> Celdas iniciales para empezar: ");
		celsI = x.nextLine();
		if(celsI.equals("")) {
			System.out.println("Usando valor por defecto: " + celsD);
			return celsD;
		}
		else {
			int c = Integer.parseInt(celsI);
			return c;
		}
	}
	
	private long chooseSeed(Scanner x) throws NumberFormatException{
		String semilla;
		System.out.print("> Introduzca semilla pseudo-aleatoria: ");
		semilla = x.nextLine();
		if(semilla.equals("")) {
			System.out.println("Usada la semilla por defecto: " + this.semilla);
			return this.semilla;
		}
		else {
			long ret = Integer.parseInt(semilla);
			return ret;
		}
	}
}
