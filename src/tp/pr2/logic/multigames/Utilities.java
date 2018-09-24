//Practica 3
//Alejandro Cabezas Garriguez
//Sergio Santa Clotilde Ruiz
package tp.pr2.logic.multigames;
import tp.pr2.logic.*;

public class Utilities {
	/**
	 * Clase utilizada para controlar donde debe colocarse la celda nueva
	 */
	public static void eliminarAr(int desde, int limite, Position[] posLibres) { //Elimina un elemento del array
		for(int i = desde; i < limite - 1; i++) { 
			posLibres[i] = posLibres[i + 1];
		}	
	}
	
	public static void swap(Object[] anArray, int i, int j) {
		Object temp = anArray[i];
		anArray[i] = anArray[j];
		anArray[j] = temp;
	}

}
