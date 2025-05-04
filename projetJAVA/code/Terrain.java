
import java.util.ArrayList;
public final class Terrain {
    public static final int NBLIGNESMAX =20;
    public static final int NBCOLONNESMAX =20;
    public final int nbLignes;
    public final int nbColonnes;
    private Ressource[][]  terrain;
    public Terrain(int nbLignes, int nbColonnes){
        this.nbLignes = nbLignes;
        this.nbColonnes = nbColonnes;
        if(nbColonnes > NBCOLONNESMAX){
            nbColonnes = NBCOLONNESMAX;
        }
        if(nbLignes > NBLIGNESMAX){
            nbLignes = NBLIGNESMAX;
        }
        terrain = new Ressource [nbLignes][nbColonnes]; 
    }
    public Terrain(){
        this((int)(Math.random()*NBLIGNESMAX),(int)(Math.random()*NBCOLONNESMAX));
    }
    public Ressource getCase(int i, int j){
        return terrain[i][j];
    }
    public void videCase(int i,int j){
        terrain[i][j] = null;
    }
    public boolean setCase(int i,int j,Ressource r){
        if(caseEstVide(i,j)){
            r.setPosition(i, j);
            terrain[i][j] = (Ressource)r; 
            System.out.println("On a ajoute une " + r.toString());
            return true;
        }
        else{
            System.out.println("On n'a pas ajouete une " + r.toString());
            return false;
        }
    }
    public boolean caseEstVide(int i, int j){
        if(terrain[i][j] == null){
            return true;
        }
        else{
            return false;
        }
    }
    public ArrayList<Ressource> lesRessources(){
        ArrayList<Ressource> tab = new ArrayList<Ressource>();
        for(int i = 0 ; i< terrain.length; i++){
            for(int j = 0; j < terrain[i].length; j++){
                if(! caseEstVide(i, j)){
                    tab.add(terrain[i][j]);
                }
            }
        }
        return tab;
    }
    //ma3rafsh dee bete3mel eh el sara7a
    public boolean sontValide(int i, int j){return true;}
    //affiche betakhod parametre int bas mesh 3aref leh
    public void affiche(int ligne){
        for(Ressource r : terrain[ligne]){
            System.out.println(r.toString());
        }
    }
    public String toString(){
        String s="";
        String t = "";
        for (int i = 0; i<this.terrain.length; i++){
            t += "---+";
        }
        s += "+"+ t + "\n";
        for (int i = 0; i<terrain.length; i++){
            for(int j = 0; j<terrain[i].length; j++){
                if(terrain[i][j] == null){
                    s += "| - ";
                }
                else{
                    if(terrain[i][j] instanceof HerbeNormal){
                        s+= "| H ";
                    }
                    if(terrain[i][j] instanceof HerbeToxique){
                        s+= "| T ";
                    }
                    if(terrain[i][j] instanceof Baies){
                        s+= "| B ";
                    }
                }
            }
            s+= "| \n";
            s += "+"+ t + "\n";
        }        
        return s;
    }
   /* public void mourir(Ressource r){
        videCase(r.getX(), r.getY());
        r.setPosition(-1, -1);
    }
   
    public void sedeplacer(Mouton m){
         int x = (int)(Math.random()*nbLignes);
        int y = (int)(Math.random()*nbColonnes);
        if(!(terrain[x][y] instanceof Mouton)){
            if(terrain[x][y] instanceof Herbetoxique){
                mourir(m);
                System.out.println("le mouton meurt"); 
            }
            if(terrain[x][y] instanceof Herbes){
                m.manger((Herbes)terrain[x][y]);
                mourir((Ressource)terrain[x][y]); 
                videCase(m.getX(), m.getY());
                m.setPosition(x, y);
                terrain[x][y] = m; 
            }
        }
        if(terrain[x][y] == null){
            videCase(m.getX(), m.getY());
            m.setPosition(x, y);
            terrain[x][y] = m; 
        }
    }
    public void deplacerToutMouton(){
    for(int i =0; i < terrain.length ; i++){
        for(int j =0; j < terrain[i].length; j++){
            if(terrain[i][j] instanceof Mouton ){
                sedeplacer((Mouton)terrain[i][j]);
            }
        }
    }
}
    public void placerEnfant(Mouton adulte1, Mouton adulte2){
        if(adulte1.reproduction(adulte2) != null){
            placer(adulte1.reproduction(adulte2));
        }
    }
    public boolean placer(Ressource r){
        
        int x = (int)(Math.random()*nbLignes);
        int y = (int)(Math.random()*nbColonnes);
        if(terrain[x][y]== null){
            if(r.getX() != -1 && r.getY() != -1){
                terrain[r.getX()][r.getY()] = r; 
                return true; 
            }
            else {
            terrain[x][y] = r; 
            r.setPosition(x, y);
            return true; }
        }
        else{
            return false; 
        }
        
    }
    public void chasser(Mouton m, Chasseur c){
        if(c.getEstArmee()){
            c.addEnergie(-1); 
        }
        else{
            c.addEnergie(-3); 
        }
        System.out.println("il chasse le mouton");
        c.manger(m);
        mourir(m); 
        
    }
    public int getNbLignes(){
        return nbLignes; 
    }
    public int getNbColonnes(){
        return nbColonnes; 
    }*/
}
