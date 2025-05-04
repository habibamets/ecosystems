/**
 * @author : Habiba et Omar
 */


public abstract class Villageois {
    private final int id;
    private static int cpt =0;
    /** variable protected pour pouvoir accéder depuis les classes filles */
    protected int x;
    /** des variables protected pour pouvoir accéder depuis les classes filles */
    protected int y; 
    private Terrain t;
    private static boolean cases[][]; 

    /** 
     * @param x : abscisse
     * @param y : ordonnéé
     * @param t : terrain
     */
    public Villageois(int x,int y, Terrain t){
        this.x = x;
        this.y =y;
        this.t = t;
        id = cpt;
        cpt++;
        cases = new boolean[t.nbLignes][t.nbColonnes]; 
        for(int i = 0; i < t.nbLignes ; i++ ){
            for(int j =0 ; j < t.nbColonnes ; j++){
                if((i != x ) && (j!= y)){
                    cases[i][j] = true; 
                }
                else{
                    cases[i][j] = false; 
                }
            }
        }
    }

    /**
     * @return terrain
     */
    public Terrain getTerrain(){
        return t;
    }

    /**
     * @param t : un terrain
     */
    public Villageois(Terrain t){
        this((int)(Math.random()*t.nbLignes),(int)(Math.random()*t.nbColonnes),t);
    }

    /**
     * @param i : abscisse
     * @param j : ordonnée
     * @return la distance
     */
    public int distance(int i,int j){
        //cette fonction retourne la distanvce en cases non pas la distance normal avec un double; 
        double temp = Math.sqrt((i-x)*(i-x)+(j-y)*(j-y));
        return (int)(Math.ceil(temp));
    } 

    /**
     * @param xnew : nouveau abscisse 
     * @param ynew : nouveau ordonnée
     */
    public void seDeplacer(int xnew,int ynew ){
        if(cases[xnew][ynew]){
            cases[x][y] = true; 
            x = xnew; 
            y = ynew; 
            cases[x][y] = false; 
        }
        else{
            System.out.println(" On ne peut pas placer le villageois car la case est occupee"); 
        }
        
    }
    public String toString(){
        return "Villageois " + id + " ";
    }
    //Une heure de travail
    /** 
     * fait travailler le villageois pendant une heure. 
     */
    public abstract void travailler();
    
    /**un cycle a.k.a une jorunee de travail pour un villageois */
   public void cycle(){
        //il travaille 8 heures
        for(int i =0 ; i <8; i++){
            this.seDeplacer((int)(Math.random()*t.nbLignes),(int)(Math.random()*t.nbColonnes));
            this.travailler();
        }
        System.out.println(this.toString() + " a travailler pour 8h");
    }
    /**
     * @return l'abscisse
     */
    public int getX(){
        return x; 
    }

    /**
     * @return l'ordonnée
     */
    public int getY(){
        return y; 
    }

    /**
     * @return le nombre de villageois créer
     */
    public static int getCpt(){
        return cpt; 
    }

}