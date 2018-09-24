//Practica 3
//Alejandro Cabezas Garriguez
//Sergio Santa Clotilde Ruiz
package tp.pr2.logic.multigames;

public enum GameType{
	/**
	 * Define los distintos modos de juego.
	 */
	ORIG("2048, the original version", "orig", new RulesGame2048()),
	FIB("2048, the Fibonacci version", "fib", new RulesFib()),
	INV("2048, the inverse version", "inv", new RulesInv());
	
	
	private String userFrendlyName;
	private String parameterName;
	private GameRules correspondingRules;
	
	private GameType(String friendly, String param, GameRules rules) {
		userFrendlyName = friendly;
		parameterName = param;
		correspondingRules = rules;
	}
	
	public static GameType parse(String param) {
		for(GameType g : GameType.values()) {
			if(g.parameterName.equals(param)) {
				return g;
			}
		}
		return null;
	}
	
	public static String externaliseAll() {
		String s = "";
		for(GameType g : GameType.values()) {
			s += " " + g.parameterName + ", ";
		}
		return s.substring(1, s.length() - 1);
	}
	
	public GameRules getRules() {
		return correspondingRules;
	}
	
	public String externalise() {
		return parameterName;
	}
	
	public String toString() {
		return this.userFrendlyName;
	}
}


