/** 
 * @author : Hbaiba et Omar
 */

public class TestSimulation {
    
    /**
     * @param args 
     * on fait un test de simulation
     */
    public static void main(String []args){
        Simulation s = new Simulation(6,57);
        try{ s.initSimulation();
        } catch (NBRplusgrandNBC erreur){
            System.out.println(erreur.toString());
        }
        
        (s.getAgents()).afficheAgents();
        s.Sim(8);
        (s.getAgents()).afficheAgents();
    }
}
