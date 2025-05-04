/**
 * @author : Hbaiba et Omar
 */
public class HerbeNormal extends Ressource{
    /** 
     * @param quantite : la quantité de l'herbe normal 
     */
    public HerbeNormal(int quantite){
        super("Herbe normal",quantite);
    }
    /** initialisation de la quantite a 0 */
    public HerbeNormal(){
        this(0);
    }
    /**
     * @param h : un herbe normal 
     * contrucuteur par copie 
     */
    public HerbeNormal(HerbeNormal h){
        this(h.getQuantite()); 
    }
}