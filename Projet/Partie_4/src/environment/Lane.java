package environment;

import gameCommons.Case;
import gameCommons.Game;

import java.util.ArrayList;
import java.util.Iterator;

public class Lane {
	private Game game;
	private int ord;
	private int speed;
	private ArrayList<Car> cars = new ArrayList<>();
	private boolean leftToRight;
	private double density;
	private int TDH; //tic d'horloge



	// TODO : Constructeur(s)

	public Lane(Game game, int ord, double density) {
		this.game = game;
		this.ord = ord;
		this.speed = game.minSpeedInTimerLoops;
		this.leftToRight = game.randomGen.nextBoolean();
		this.density = density;


		for(int i = 0; i < 2 * game.width; i++) {
			Iterator iter = this.cars.iterator();

			while(iter.hasNext()) {
				Car car = (Car)iter.next();
				car.move(true);
			}

			this.mayAddCar();
		}
	}

	public void update() {

		// TODO

		// Toutes les voitures se d?placent d'une case au bout d'un nombre "tic
		// d'horloge" ?gal ? leur vitesse
		// Notez que cette m?thode est appel?e ? chaque tic d'horloge

		// Les voitures doivent etre ajoutes a l interface graphique meme quand
		// elle ne bougent pas

		// A chaque tic d'horloge, une voiture peut ?tre ajout?e

		if (this.TDH == this.speed) {
			// les voitures bougent
			Iterator iter = this.cars.iterator();
			while(iter.hasNext()) {
				Car t_car = (Car)iter.next();
				t_car.move(true);
			}
            // supprimer les voitures

			ArrayList<Car> cp_cars = new ArrayList<Car>();
			cp_cars = this.cars;
			for (int i=0;i< this.cars.size();i++){
				if (!(this.cars.get(i).leftPosition.absc + this.cars.get(i).length > 0 || this.cars.get(i).leftPosition.absc < this.cars.get(i).game.width)){
					cp_cars.remove(i);
				}


			}
			this.cars  = cp_cars;
			this.mayAddCar();
			this.TDH = 0;
		}else{


			Iterator iter = this.cars.iterator();
			while(iter.hasNext()) {
				Car t_car = (Car)iter.next();
				t_car.move(false);
			}

		}
		this.TDH++;




	}

	// TODO : ajout de methodes




	public boolean Bsecurise(Case c) {
		Iterator iter = this.cars.iterator();

		while(iter.hasNext()) {
			Car car = (Car)iter.next();
			if (c.absc >= car.leftPosition.absc && c.absc < car.leftPosition.absc+car.length) {
				return false;
			}
		}

		return true;
	}



	/*
	 * Fourni : mayAddCar(), getFirstCase() et getBeforeFirstCase() 
	 */

	/**
	 * Ajoute une voiture au d?but de la voie avec probabilit? ?gale ? la
	 * densit?, si la premi?re case de la voie est vide
	 */
	private void mayAddCar() {
		if (Bsecurise(getFirstCase()) && Bsecurise(getBeforeFirstCase())) {
			if (game.randomGen.nextDouble() < density) {
				cars.add(new Car(game, getBeforeFirstCase(), leftToRight));
			}
		}
	}

	private Case getFirstCase() {
		if (leftToRight) {
			return new Case(0, ord);
		} else
			return new Case(game.width - 1, ord);
	}

	private Case getBeforeFirstCase() {
		if (leftToRight) {
			return new Case(-1, ord);
		} else
			return new Case(game.width, ord);
	}






}
