package environment;

import gameCommons.Case;
import gameCommons.Game;
import gameCommons.IEnvironment;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.PrimitiveIterator;

public class Environment implements IEnvironment {
		
	//TODO
    private ArrayList<Lane>  m_Lane = new ArrayList<Lane>();
    private Game game;
    private Cases_speciales cs;
    private int score;
    private LigneDeau LDE;
    public Environment(Game game){
        this.game = game;
        Lane t_lane = new Lane(game,0,0.0);
        this.m_Lane.add(t_lane);

        for(int i = 1; i < game.height - 1; i++) {
            if (i==10||i==6){
                t_lane = new Lane(game,i,0.0);
                this.m_Lane.add(t_lane);
            }else{
                t_lane = new Lane(game,i,game.defaultDensity);
                this.m_Lane.add(t_lane);
            }

        }
        t_lane = new Lane(game,game.height-1,0.0);
        this.m_Lane.add(t_lane);
        this.cs = new Cases_speciales(this.game);
        this.LDE = new LigneDeau(this.game);
        this.score=0;

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

        if (this.cs.Bsecurise(c) && (this.m_Lane.get(c.ord)).Bsecurise(c)){
            if (c.ord > this.score )
            this.score = c.ord;
        }
        return this.cs.Bsecurise(c) && (this.m_Lane.get(c.ord)).Bsecurise(c) && this.LDE.Bsecurise(c);

    }

    /**
     * Teste si la case est une case d'arrivee
     *
     * @param c
     * @return vrai si la case est une case de victoire
     */
    public boolean isWinningPosition(Case c){

        if (c.ord == this.game.height-1){
            this.score = c.ord;
            return true;
        }else{
            return false;
        }
    }

    public boolean getBonus(Case c){
        return this.cs.getBonus(c);
    }

    /**
     * Effectue une étape d'actualisation de l'environnement
     */
    public boolean update(){
        this.cs.update();
        this.LDE.update();
        boolean res = false;
        Iterator iter = this.m_Lane.iterator();

        while(iter.hasNext()) {
            Lane t_lane = (Lane)iter.next();
            res = t_lane.update();
        }
        return res;



    }


    public int getScore(){
        return this.score;
    }

    public boolean isMurs(Case c){
        return this.cs.isMur(c);
    }

    public boolean isTerrains(Case c){
        return this.cs.isTerrains(c);
    }

}
