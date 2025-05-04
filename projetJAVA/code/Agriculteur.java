/**
 * @author : habiba et omar
 */

public class Agriculteur extends Villageois{
    /** 
     * @param t : le terrain
     */
    public Agriculteur(Terrain t){
        super(t);
    }
    /**
     * @param x : abscisse
     * @param y : ordonnée
     * @param t : le terrain 
     */
    public Agriculteur(int x,int y,Terrain t){
        super(x,y,t);
    }
    
    /**
     * fait travailler le chasseur
     */
    public void travailler(){
        //gets ressource in (x,y) in terrain 
        Terrain t = getTerrain();
        Ressource r = t.getCase(x,y);
        if(r == null){
            r = new HerbeNormal(); 
            t.setCase(x, y, r); 
        }
        //augmente quantite par 1 si r n'est pas toxique
        if((r instanceof HerbeNormal)){
            r.setQuantite(r.getQuantite() + 1);
            System.out.println("l'agriculteur augmente la quantite de l'herbe" );
            //if herbe and quantite devient >= 4 donc devient toxique
            if(r.getQuantite() >= 3){
                t.videCase(x, y);
                r = new HerbeToxique();
                System.out.println("l'herbe est devenu toxique");
                r.setPosition(x, y);
                t.setCase(x, y, r);
            }
        }
        if((r instanceof Toxique)){
            System.out.println("L'agriculteur ne peut pas agrandir un herbe toxique");
        }
        
    }
    
    public String toString(){
        return super.toString() + " agriculteur";
    }
}
