
/** 
 * @author Habiba et Omar
 * cette classe permet de gerer les chasseurs
 */


public class Chasseur extends Villageois{
    //possede un outil ou non
    private boolean aOutil;
    //signifie sa sante
    private int hp;
    /** 
     * @param x : abscisse 
     * @param y : ordonnée
     * @param t : terrain
     */
    public Chasseur(int x,int y, Terrain t){
        super(x,y,t);
        aOutil = Math.random() < 0.7; 
        hp = 10;
    }
    /** 
     * @param t : terrain
     */
    public Chasseur(Terrain t){
        super(t);
        aOutil = Math.random() < 0.7;
        hp = 10;
    }
    
    public void travailler(){
        //gets ressource in (x,y) in terrain 
        Terrain t = getTerrain();
        Ressource r = t.getCase(x,y);
        if(r != null){
            //checks if not toxic
            if(!(r instanceof Toxique)){
            //gets ressources more effeciently if a Outil
            
            if(aOutil){
                if(r.getQuantite() > 2){
                    r.setQuantite(r.getQuantite() -2);
                    System.out.println("il mange"); 
                }
                else{
                    t.videCase(x, y);
                    System.out.println("le chasseur enleve la ressource de la case"); 
                }
            }
            //moins efficace (n'a pas outil)
            else{
                if(r.getQuantite() > 0){
                    r.setQuantite(r.getQuantite() -1);
                }
                else{
                    t.videCase(x, y);
                    System.out.println("le chasseur enleve la ressource de la case"); 
                }
            }
        }
        //eats toxic
        if(r instanceof Toxique){
            //si c'est toxique, le fait qu'il possede un outil ou non n'affecte pas sa chasse d'herbe toxique
            hp = hp -1;
            System.out.println(this.toString() +" a mange quelque chose toxique...");
            if(r.getQuantite() > 0){
                r.setQuantite(r.getQuantite() -1);
            }
            else{
                t.videCase(x, y);
                System.out.println("le chasseur enleve la ressource toxique de la case"); 
            }

        }
    }
    }
    /**
     * @return la santé 
     */
    public int getHp(){
        return hp;
    }
    /**
     * @return le chasseur 
     */
    public String toString(){
        return super.toString() + " chasseur";
    }
}
