package environment;

import java.util.ArrayList;
import java.util.Iterator;

import gameCommons.Case;
import gameCommons.Direction;
import gameCommons.Game;
import gameCommons.IEnvironment;

public class Environment implements IEnvironment {
		
	//TODO
    private ArrayList<Lane>  m_listLane = new ArrayList<Lane>();
    private Game game;
    private int  score;

    public Environment(Game game){
        this.game = game;
        Lane t_lane = new Lane(game,0,0.0);
        this.m_listLane.add(t_lane);
         t_lane = new Lane(game,1,0.0);
        this.m_listLane.add(t_lane);
        for(int i = 2; i < 2*game.height; i++) {
            t_lane = new Lane(game,i,game.defaultDensity);
            this.m_listLane.add(t_lane);

            System.out.println("height: "+ i);
        }



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
        int i;
        boolean res;
        for ( i=0; i<this.m_listLane.size();i++){
            if (this.m_listLane.get(i).getOrd() == 1){
                break;
            }
        }
        System.out.println("ord : "+i);
        res = this.m_listLane.get(i).Bsecurise(c);
        if (!res){
            this.score = i-2;
        }
        return (res);
    }

    /**
     * Teste si la case est une case d'arrivee
     *
     * @param c
     * @return vrai si la case est une case de victoire

    public boolean isWinningPosition(Case c){

        if (c.ord == this.game.height-1){
            return true;
        }else{
            return false;
        }
    }
*/
    /**
     * Effectue une étape d'actualisation de l'environnement
     * @param dir;
     */
    public void update(Direction dir){


        switch (dir){
            case up:
                for (int i =0; i<this.m_listLane.size();i++){

                    this.m_listLane.get(i).ChangOrd(this.m_listLane.get(i).getOrd()-1);
                }
                m_listLane.add(new Lane(this.game,m_listLane.size(),this.game.defaultDensity));
                break;
            case down:
                if (this.m_listLane.get(0).getOrd() < 0)
                    for(int i =0 ;i < this.m_listLane.size();i++ ){
                        this.m_listLane.get(i).ChangOrd(this.m_listLane.get(i).getOrd()+1);
                    }
                break;
            default:
                break;

        }


        Iterator iter = this.m_listLane.iterator();

        while(iter.hasNext()) {
            Lane t_lane = (Lane)iter.next();
            t_lane.update();
        }



    }


     public int getScore(){
        return this.score;
     }

}
