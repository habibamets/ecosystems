public class Ressource {
    public final int ident;
    public final String type;
    private static int cpt =0;
    private int posx;
    private int posy;
    private int quantite;
    public Ressource(String type, int quantite){
        this.quantite = quantite;
        this.type = type;
        ident = cpt;
        cpt++;
        posx = -1;
        posy = -1;
    }
    public int getX(){
        return posx;
    }
    public int getY(){
        return posy;
    }
    public int getQuantite(){
        return quantite;
    }
    public void setQuantite(int q){
        quantite = q;
    }
    public void setPosition(int i ,int j){
        posx = i;
        posy = j;
    }
    public void initialPosition(){

    }
    public String toString(){
        return "Type: " + type +" id:"+ ident;
    }

}
