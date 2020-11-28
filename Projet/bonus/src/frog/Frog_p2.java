package frog;

import gameCommons.*;

public class Frog_p2 implements IFrog_p2 {

	private Game game;
	private Case m_case;
	private Direction m_Direction;
	public Frog_p2(Game game){

		this.game = game;
		m_case= new Case(2*this.game.width/3,0);
	}
	/**
	 * Donne la position actuelle de la grenouille
	 * @return la position case
	 */
	public Case getPosition(){

			return  this.m_case;
	}
	public void setPosition(Case c){
		this.m_case =c;
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
