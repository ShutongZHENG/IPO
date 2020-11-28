package environment;

import gameCommons.Case;
import gameCommons.Game;
import graphicalElements.Element;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class LigneDeau {

    private Game game;
    private ArrayList<Case> LDE = new ArrayList<Case>();
    private final Color colorLDE = Color.CYAN;
    public LigneDeau(Game game) {
        this.game = game;
        for (int i = 0 ; i< game.width ; i++)
            this.LDE.add(new Case(i,6));
    }

    private void addToGraphics() {
        for (int i =0; i<this.LDE.size();i++){
            game.getGraphic().add(new Element(this.LDE.get(i).absc,this.LDE.get(i).ord,this.colorLDE));
        }
    }
    public void update(){
        addToGraphics();
    }


    public boolean Bsecurise(Case c){

            if (c.absc > this.game.width  && c.ord == 6) {
                return false;
            }


        return true;
    }

}
