//Practica 3
//Alejandro Cabezas Garriguez
//Sergio Santa Clotilde Ruiz
/**
 * Paquete con las clases que forman las mecanicas del juego.
 */
package tp.pr2.logic;

import tp.pr2.control.Controller;
import Excepciones.*;
import tp.pr2.logic.multigames.*;
/**
 * 
 * @author Alejandro Cabezas Garriguez and Sergio Santa Clotilde Ruiz.
 *
 */

public class Game2048 {
	/**
	 * 
	 * @param Args
	 * Recibe los parametros a traves del lanzamiento de la aplicacion.
	 */
	public static void main(String[] Args) {
		try {
			checkSize(Args);
			int size = Integer.parseInt(Args[0]); //Pasamos el tamaño por argumentos del main.
			int celsI = Integer.parseInt(Args[1]); //Igual que el tamaño
			long seed = Integer.parseInt(Args[2]);
			checkNumbers(celsI, size);
			Controller juego = new Controller(GameType.ORIG, seed, size, celsI); //Creamos el juego.
			juego.run(); //Lo ejecutamos
		}
		catch(NumberFormatException e) {
			System.out.println("Por favor, introduzca numeros en lugar de otros caracteres.( " + e.getMessage() + " )");
			System.out.println("Aplicacion detenida.");
		}
		catch(ItemsException e) {
			System.out.println(e.getMessage());
			System.out.println("Aplicacion detenida.");
		}
		catch(NoValidNumbersException e) {
			System.out.println(e.getMessage());
			System.out.println("Aplicacion detenida.");
		}
	}
	public static void checkSize(String[] Args) throws ItemsException {
		/**
		 * @throws ItemsException
		 * Comprueba que los parametros son correctos.
		 */
		if(Args.length > 3) {throw new ItemsException("Has introducido mas valores de los necesarios");}
		else if(Args.length <= 2) {throw new ItemsException("Faltan valores iniciales.");}
	}
	
	public static void checkNumbers(int celdasIniciales, int tamanio) throws NoValidNumbersException{ 
		/**
		 * @throws NoValidNumbersException
		 * Comprueba que los parametros son correctos.
		 */
		if(celdasIniciales > tamanio * tamanio) {throw new NoValidNumbersException();}
	}
}

