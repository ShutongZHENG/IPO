package environment;

import java.util.ArrayList;
import java.util.Iterator;

import gameCommons.Case;
import gameCommons.Game;
import gameCommons.IEnvironment;

public class Environment implements IEnvironment {
		
	//TODO
    private ArrayList<Lane>  m_Lane = new ArrayList<Lane>();
    private Game game;

    public Environment(Game game){
        this.game = game;
        Lane t_lane = new Lane(game,0,0.0);
        this.m_Lane.add(t_lane);

        for(int i = 1; i < game.height - 1; i++) {
            t_lane = new Lane(game,i,game.defaultDensity);
            this.m_Lane.add(t_lane);
        }
        t_lane = new Lane(game,game.height-1,0.0);
        this.m_Lane.add(t_lane);

    }
    /**
     * Teste si une case est sure, c'est à dire que la grenouille peut s'y poser
     * sans mourir
     *
     * @param c
     *            la case à tester
     * @return vrai s'il n'y a pas danger
     */
    public boolean isSafe(Case c){

        return (this.m_Lane.get(c.ord)).Bsecurise(c);
    }

    /**
     * Teste si la case est une case d'arrivee
     *
     * @param c
     * @return vrai si la case est une case de victoire
     */
    public boolean isWinningPosition(Case c){

        if (c.ord == this.game.height-1){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Effectue une étape d'actualisation de l'environnement
     */
    public void update(){

        Iterator iter = this.m_Lane.iterator();

        while(iter.hasNext()) {
            Lane t_lane = (Lane)iter.next();
            t_lane.update();
        }



    }





}
