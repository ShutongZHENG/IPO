package environment;

import gameCommons.Case;
import gameCommons.Game;
import graphicalElements.Element;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.util.ArrayList;
import java.util.Iterator;

public class Cases_speciales {
    private Game game;
    private ArrayList<Case> Cases_pieges = new ArrayList<Case>();
    private ArrayList<Case> Cases_terrains = new ArrayList<Case>();
    private ArrayList<Case> Cases_murs = new ArrayList<Case>();
    private ArrayList<Case> Cases_traverser = new ArrayList<Case>();
    private final Color colorCpie = Color.white;
    private final Color colorCter = Color.pink;
    private final Color colorCmur = Color.red;
    private final Color colorCtra = Color.yellow;

    public Cases_speciales(Game game){
        this.game = game;
        this.Cases_pieges.add(new Case(6,4)) ;
        this.Cases_pieges.add(new Case(10,8));
        this.Cases_pieges.add(new Case(12,16));
        this.Cases_pieges.add(new Case(17,14));
        this.Cases_pieges.add(new Case(22, 15));

        for (int i = 2 ; i<26 ; i=i+3)
        this.Cases_terrains.add(new Case(i,10));

        for (int i= 4 ; i<26 ; i = i+7)
        this.Cases_murs.add(new Case(i,12));

        this.Cases_traverser.add(new Case(7,18));
        this.Cases_traverser.add(new Case(10,17));
        this.Cases_traverser.add(new Case(11,16));
        this.Cases_traverser.add(new Case(14,18));
        this.Cases_traverser.add(new Case(20,17));
        this.Cases_traverser.add(new Case(22,16));



    }
    private void addToGraphics() {



        for (int i =0; i<this.Cases_pieges.size();i++){
            game.getGraphic().add(new Element(this.Cases_pieges.get(i).absc,this.Cases_pieges.get(i).ord,this.colorCpie));
        }

        for (int i =0 ; i<this.Cases_terrains.size(); i++){
            game.getGraphic().add(new Element(this.Cases_terrains.get(i).absc,this.Cases_terrains.get(i).ord,this.colorCter));
        }

        for (int i =0; i<this.Cases_murs.size(); i++){
            game.getGraphic().add(new Element(this.Cases_murs.get(i).absc,this.Cases_murs.get(i).ord,this.colorCmur));
        }
        for (int i =0; i<this.Cases_traverser.size(); i++){
            game.getGraphic().add(new Element(this.Cases_traverser.get(i).absc,this.Cases_traverser.get(i).ord,this.colorCtra));
        }
    }

    public void update(){
        addToGraphics();
    }

    public boolean Bsecurise(Case c){
        Iterator iter = this.Cases_pieges.iterator();

        while(iter.hasNext()) {
            Case cp = (Case)iter.next();
            if (c.absc == cp.absc && c.ord == cp.ord) {
                return false;
            }
        }

        return true;
    }
    public boolean getBonus(Case c){
        for (int i =0 ; i<this.Cases_traverser.size(); i++){

            if (c.absc == this.Cases_traverser.get(i).absc && c.ord == this.Cases_traverser.get(i).ord){
                //System.out.println(" Frog: "+ c.absc+ " "+c.ord+ "           Case_tra: "+this.Cases_traverser.get(i).absc+" "+this.Cases_traverser.get(i).ord);
                this.Cases_traverser.remove(i);
                return true;
            }
        }

        return false;
    }
    public boolean isTerrains(Case c){
        for (int i =0 ; i<this.Cases_terrains.size(); i++){
            if (c.absc == this.Cases_terrains.get(i).absc && c.ord == this.Cases_terrains.get(i).ord){
                return true;
            }
        }
        return false;
    }
    public boolean isMur(Case c){
        for (int i =0 ; i<this.Cases_murs.size(); i++){
            if (c.absc == this.Cases_murs.get(i).absc && c.ord == this.Cases_murs.get(i).ord){
                return true;
            }
        }
        return false;
    }
}
