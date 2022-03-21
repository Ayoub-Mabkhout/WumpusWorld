package wumpusworld;
import java.util.ArrayList;
import knowledgebase.*;
import kb.api.KBAPI;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author CHIFAA
 */
public class Agent {
    
    WumpusPlanet w = new WumpusPlanet (0,0);
    String Path;
    KnowledgeBase KB;
    
   // PerceptSentence ps;
   // ActionSentence as;
    int AgentX = 0;
    int AgentY  = 0;
    int t = 0;
    Action ac;
    
    public Agent (int AgentX, int AgentY, int t){
        
        this.AgentX = AgentX;
        this.AgentY = AgentY;
        this.t = t;
        KB = new KnowledgeBase("knowledge_base.pl");
       
 
    }
    
    PerceptSentence MakePerceptSentence(int X, int Y, int t){
        // System.out.println("agent currently sensing room "+X+","+Y);

        boolean breeze = w.rooms[X][Y].getBreeze();
        boolean glitter = w.rooms[X][Y].getGlitter();
        boolean stench = w.rooms[X][Y].getStench();
        int Coordinates [] = {X, Y};
        PerceptSentence ps = new PerceptSentence (Coordinates, glitter, breeze, stench, t);
        System.out.println(ps.getMessage());
        return(ps);   
        
    }
    
    ActionSentence MakeActionSentence (int x, int y, Action ac){
        
        ArrayList<int[]> Adjacent = w.getAdjacentRooms(ac.getTargetRoom()[0], ac.getTargetRoom()[1]);
        int [] previouspos = {x, y};
        ActionSentence as = new ActionSentence(previouspos,ac, Adjacent);
        System.out.println(as.getMessage());
        return(as);
        
    }
    
   Action run(){
        
        KB.Tell(MakePerceptSentence(AgentX, AgentY, t));
        ac =  KB.Ask(t);
        System.out.println(ac.getMessage());
        KB.Tell(MakeActionSentence(AgentX, AgentY, ac));
        t += 1;
        return ac;
   }
    
    
    
    
}
