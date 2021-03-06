package frog;

import gameCommons.Game;
import gameCommons.IFrog;
import util.Case;
import util.Direction;

public class Frog implements IFrog {
	
	private Game game;
	private Case m_case;
	private Direction m_Direction;
	public Frog(Game game){

		this.game = game;
		m_case= new Case(this.game.width/2,0);
	}
	/**
	 * Donne la position actuelle de la grenouille
	 * @return la position case
	 */
	public Case getPosition(){

			return  this.m_case;
	}

	/**
	 * Donne la direction de la grenouille, c'est à dire de son dernier mouvement
	 * @return la direction Direction
	 */
	public Direction getDirection(){
		return  this.m_Direction;
	}

	/**
	 * Déplace la grenouille dans la direction donnée et teste la fin de partie
	 * @param key
	 */
	public void move(Direction key){
		this.m_Direction = key;
		if (key == Direction.up){
			if (this.m_case.ord<this.game.height-1)
			this.m_case = new Case(m_case.absc,m_case.ord+1);
		}
		else if (key == Direction.down){
			if (this.m_case.ord>0)
			this.m_case = new Case(m_case.absc,m_case.ord-1);
		}
		else if (key == Direction.right){
			if (this.m_case.absc<this.game.width-1)
				this.m_case = new Case(m_case.absc+1,m_case.ord);
		}
		else{
			if (this.m_case.absc>0)
				this.m_case = new Case(m_case.absc-1, m_case.ord);
		}
	}

}
