/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package kb.api;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import org.jpl7.*;
import knowledgebase.*;
/**
 *
 * @author ayoub
 */
public class KBAPI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Map<String, Term>[] solutions;
        Query q;
        String answer;
        Scanner sc = new Scanner(System.in);
        try{
            Action action;
            PerceptSentence ps;
            ActionSentence as;
            int room1[] = new int[2];
            KnowledgeBase kb = new KnowledgeBase("knowledge_base.pl");
            room1[0] = 0; room1[1] = 0;
            ps = new PerceptSentence(room1,false,false,false,1);
            kb.Tell(ps);
            System.out.println(ps.getMessage());
            action = kb.Ask(1);
            System.out.println(action.getMessage());
            int room2[] = {0,2};
            int room3[] = {1,1};
            ArrayList<int[]> adj = new ArrayList<int[]>();
            adj.add(room2);
            adj.add(room3);
            as = new ActionSentence(room1,action,adj);
            System.out.println(as.getMessage());
            kb.Tell(as);
            room1 = action.getTargetRoom();
            ps = new PerceptSentence(room1,false,false,true,2);
            System.out.println(ps.getMessage());
            kb.Tell(ps);
            action = kb.Ask(2);
            System.out.println(action.getMessage());
            return;
            //q = new Query("consult('knowledge_base.pl').");
            //System.out.println("consult(knowledge_base.pl) ended in " + (q.hasSolution()?"success":"failure"));
        } catch(JPLException e){
            System.out.println(e.getMessage());
        }
        /*
        answer = sc.next();
        
        while(!answer.equals("exit")){
            try{
                q = new Query(answer);
                System.out.println((q.hasSolution()?"true":"false"));
                for(Map<String,Term> solution : q.allSolutions())
                    System.out.println(solution);
                answer = sc.next();
            } catch(JPLException e){
            System.out.println("\n"+e.getMessage());
            answer = sc.next();
            }
        }
        */
    }   

}
