package environment;

import gameCommons.Case;
import gameCommons.Game;
import graphicalElements.Element;

import java.awt.*;

public class Car {
	protected Game game;
	protected Case leftPosition;
	private boolean leftToRight;
	protected int length;
	private final Color colorLtR = Color.BLACK;
	private final Color colorRtL = Color.BLUE;

	//TODO Constructeur(s)
	public Car(Game game, Case BeforeCase, boolean leftToRight){
		this.game = game;
		this.leftToRight = leftToRight;
		this.length = game.randomGen.nextInt(3)+1;
		if (leftToRight){
			this.leftPosition = new Case(BeforeCase.absc - this.length,BeforeCase.ord);
		}else{
			this.leftPosition = new Case(BeforeCase.absc, BeforeCase.ord);
		}


	}

	//TODO : ajout de methodes


	public void move(boolean mv) {
		if (mv) {
			if (this.leftToRight){
				this.leftPosition = new Case(this.leftPosition.absc+1,this.leftPosition.ord);
			}else {
				this.leftPosition = new Case(this.leftPosition.absc-1, this.leftPosition.ord);
			}

		}

		this.addToGraphics();
	}




	
	/* Fourni : addToGraphics() permettant d'ajouter un element graphique correspondant a la voiture*/
	private void addToGraphics() {
		for (int i = 0; i < length; i++) {
			Color color = colorRtL;
			if (this.leftToRight){
				color = colorLtR;
			}
			game.getGraphic().add(new Element(leftPosition.absc + i, leftPosition.ord, color));
		}
	}

}
