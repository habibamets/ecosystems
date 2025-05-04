/** 
 * @author : Habiba et Omar
 */
public class HerbeToxique extends Ressource implements Toxique{
    private final static double P_AGRANDIR = 0.3;
    /** initialisation de la quantité a 5 */
    public HerbeToxique(){
        super("HToxique",5);
    }

    /**agrandir tout seul */
    public void agrandir(){
        if(Math.random() < P_AGRANDIR){
            setQuantite(getQuantite()+1);
        }
    }
}
