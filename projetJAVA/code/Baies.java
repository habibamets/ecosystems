/**
 * @author : Habiba et Omar
 */

public class Baies extends Ressource  implements Toxique{
    //les baies apparaissent naturellement != (Herbe et HerbeToxique)
    private final static double P_AGRANDIR = 0.5;  
    
    /**
     * initialisation de la quantité a 0
     */
    public Baies(){
        super("Baies",0);
    }
    
    //agrandir tout seul
    public void agrandir(){
        if(Math.random() > P_AGRANDIR){
            setQuantite(super.getQuantite()+ 1);
        }
    }

    
}
