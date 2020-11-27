package gameCommons;

import graphicalElements.Element;
import graphicalElements.IFroggerGraphics;

import java.awt.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;
public class Game {

	public final Random randomGen = new Random();

	// Caracteristique de la partie
	public final int width;
	public final int height;
	public final int minSpeedInTimerLoops;
	public final double defaultDensity;

	// Lien aux objets utilis?s
	private IEnvironment environment;
	private IFrog frog;
	private IFroggerGraphics graphic;
	private long startTime;
	private int one_run = 0;

	/**
	 * 
	 * @param graphic
	 *            l'interface graphique
	 * @param width
	 *            largeur en cases
	 * @param height
	 *            hauteur en cases
	 * @param minSpeedInTimerLoop
	 *            Vitesse minimale, en nombre de tour de timer avant d?placement
	 * @param defaultDensity
	 *            densite de voiture utilisee par defaut pour les routes
	 */
	public Game(IFroggerGraphics graphic, int width, int height, int minSpeedInTimerLoop, double defaultDensity) {
		super();
		this.graphic = graphic;
		this.width = width;
		this.height = height;
		this.minSpeedInTimerLoops = minSpeedInTimerLoop;
		this.defaultDensity = defaultDensity;
		startTime = System.currentTimeMillis(); //??????
	}

	/**
	 * Lie l'objet frog ? la partie
	 * 
	 * @param frog
	 */
	public void setFrog(IFrog frog) {
		this.frog = frog;
	}

	/**
	 * Lie l'objet environment a la partie
	 * 
	 * @param environment
	 */
	public void setEnvironment(IEnvironment environment) {
		this.environment = environment;
	}

	/**
	 * 
	 * @return l'interface graphique
	 */
	public IFroggerGraphics getGraphic() {
		return graphic;
	}

	/**
	 * Teste si la partie est perdue et lance un ?cran de fin appropri? si tel
	 * est le cas
	 * 
	 * @return true si le partie est perdue
	 */
	public boolean testLose() {
		// TODO
		if (!this.environment.isSafe(this.frog.getPosition())){
			System.out.println("Lose");
			this.one_run ++;
		}

		return !this.environment.isSafe(this.frog.getPosition());
	}

	/**
	 * Teste si la partie est gagnee et lance un ?cran de fin appropri? si tel
	 * est le cas
	 * 
	 * @return true si la partie est gagn?e
	 */
	public boolean testWin() {
		// TODO
		if (this.environment.isWinningPosition(this.frog.getPosition())){
			System.out.println("Win");
			this.one_run ++;
		}
		return this.environment.isWinningPosition(this.frog.getPosition());


	}

	private String calTemp(long milliseconds){
				final long day = TimeUnit.MILLISECONDS.toDays(milliseconds);
				final long hours = TimeUnit.MILLISECONDS.toHours(milliseconds)
						- TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(milliseconds));
				final long minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds)
						- TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(milliseconds));
				final long seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds)
						- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliseconds));
				final long ms = TimeUnit.MILLISECONDS.toMillis(milliseconds)
						- TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(milliseconds));
				String res;
				res = "    Time   "+hours+" : "+minutes+" : "+seconds;
				return res;


		}

	/**
	 * Actualise l'environnement, affiche la grenouille et verifie la fin de
	 * partie.
	 */
	public void update() {
		graphic.clear();
		environment.update();
		this.graphic.add(new Element(frog.getPosition(), Color.GREEN));





		if (testLose()){

			if (this.one_run == 1){
				long endTime = System.currentTimeMillis();

				graphic.endGameScreen("Lose   "+ calTemp(endTime - startTime));
			}

		}else if (testWin()){

			if (this.one_run == 1){
				long endTime = System.currentTimeMillis();

				graphic.endGameScreen("Win   "+ calTemp(endTime - startTime));
			}
		}

	}

}
