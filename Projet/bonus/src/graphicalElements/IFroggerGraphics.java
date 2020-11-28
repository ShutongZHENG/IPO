package graphicalElements;

import gameCommons.IFrog;
import gameCommons.IFrog_p2;

public interface IFroggerGraphics {
	
	/**
	 * Ajoute l'?l?ment aux ?l?ments ? afficher
	 * @param e
	 */
    public void add(Element e);
    
    /**
     * Enl?ve tous les ?l?ments actuellement affich?s
     */
    public void clear();
    
    /***
     * Actualise l'affichage
     */
    public void repaint();
    
    /**
     * Lie la grenouille ? l'environneemnt graphique
     * @param frog
     */
    public void setFrog(IFrog frog, IFrog_p2 frog_p2);
    
    /**
     * Lance un ?cran de fin de partie
     * @param message le texte ? afficher
     */
    public void endGameScreen(String message);
}
