package gameCommons;

import util.Case;
import util.Direction;

public interface IFrog {
	
	/**
	 * Donne la position actuelle de la grenouille
	 * @return la position case
	 */
	public Case getPosition();
	
	/**
	 * Donne la direction de la grenouille, c'est ? dire de son dernier mouvement 
	 * @return la direction Direction
	 */
	public Direction getDirection();
	
	/**
	 * D?place la grenouille dans la direction donne et teste la fin de partie
	 * @param key
	 */
	public void move(Direction key);

}
