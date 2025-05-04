/** 
 * @author : Habiba et Omar
 */

import java.util.ArrayList;
/** on gere une liste de vilageois  */
public class GestionAgent {
    private ArrayList<Villageois> tab;
    /**
     * on créé une liste de vilageois 
     */
    public GestionAgent(){
        tab = new ArrayList<Villageois>();
    }

    /**
     * on affiche la liste des vilageois 
     */
    public void afficheAgents(){
        for(Villageois v : tab){
            System.out.println(v.toString());
        }
    }

    /**
     * @param v : un villageois (soit un chasseur ou un agriculteur)
     */
    public void ajoute(Villageois v){
        tab.add((Villageois)v);
    }

    /**
     * creer un villageois aleatoire 
     * @param t : le terrain 
     * @return le villageois crée
     */
    public Villageois Village(Terrain t){
        Villageois v;
        double k = Math.random();
        if(k < 0.5){
            v = new Chasseur(t);
        }
        else{
            v = new Agriculteur(t);
        }
        return v;
    }
    
    /**les agents agissent et font un cycle de travail */
    public void agitAgent(){
        for(int i= 0; i< tab.size(); i++){
            tab.get(i).cycle();
            if(tab.get(i) instanceof Chasseur){
                //if chasseur mange beaucoup de ressources toxiques il meurt
                int k = ((Chasseur)tab.get(i)).getHp();
                if(k < 0){
                    System.out.println((tab.get(i)).toString()+" meurt");
                    tab.remove(tab.get(i));
                }
            }
        }
    }
}
