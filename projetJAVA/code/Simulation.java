/**
 * @author  : Habiba et Omar
 */

import java.util.ArrayList;
/**
 * on fait une simulation ce ce qu'il se passe dans le terrain 
 */
public class Simulation {
    private Terrain t;
    private int nbVillageois;
    private GestionAgent agents;
    private int ressources;
    private ArrayList<Ressource> tab;
    
    /**
     * @param nbVillageois : nombres de villageois 
     * @param ressources : nombre de ressources 
     */
    public Simulation(int nbVillageois, int ressources){
        this.nbVillageois = nbVillageois;
        this.ressources = ressources;
        t = new Terrain();
        agents = new GestionAgent();
        tab = new ArrayList<Ressource>();
        
    }

    /** 
     * @return : GestionAgent , une liste de villaegois 
     */
    public GestionAgent getAgents(){
        return agents;
    }

    /**
     * @throws NBRplusgrandNBC  : si le nombre de ressource est supérieur au nombre de cases
     */
    //on initialise le terrain
    public void initTerrain() throws NBRplusgrandNBC{
        int n = ressources;
        if(n > t.nbLignes*t.nbColonnes){
            throw new NBRplusgrandNBC(); 
        }
        while( n > 0){
            int i = (int)(Math.random()*t.nbLignes); 
            int j = (int)(Math.random()*t.nbColonnes);
            
            if(t.caseEstVide(i,j)){
                // 1% des ressources sont des baies
                if(Math.random() > 0.8){
                    t.setCase(i,j,(new Baies()));
                }
                else{
                    t.setCase(i,j,(new HerbeNormal()));
                }
                n--;
            }
        }
    }
    
    /**initialisation des agents  */
    public void initAgents(){
        for(int i =0; i<nbVillageois;i++ ){
            agents.ajoute(agents.Village(t));
        }
    }
    /**
     * @throws NBRplusgrandNBC : si le nombre de ressource est supérieur au nombre de cases
     */
    public void initSimulation() throws NBRplusgrandNBC{
        initAgents();
        initTerrain();
    }

    /** mise a jour du terrain */
    public void miseAJour(){
        agents.agitAgent();
        tab = t.lesRessources();
        for(Ressource r : tab){
            if(r instanceof Toxique){
                ((Toxique)r).agrandir();
            }
        }
        t.affiche(2);
    }

    /**
     * @param x : nombre de fois qu'on veut faire un cycle
     */
    public void Sim(int x){
        for(int i = 0; i<x; i++){
            System.out.println("Cycle:"+ i);
            miseAJour();
        }
    }
}
