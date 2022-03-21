/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package knowledgebase;
import java.util.ArrayList;
import java.util.Map;
import org.jpl7.Query;
import org.jpl7.JPLException;
import org.jpl7.Term;
/**
 *
 * @author ayoub
 */
public class KnowledgeBase {
    private Query q;
    
    public KnowledgeBase(String path){
        try{
            q = new Query("consult('" + path + "').");
            System.out.println("Consult file \'" + path + "\' ended  in "+(q.hasSolution()?"success":"failure")+"\n");

        } catch(JPLException e){
            System.err.println(e.getMessage());
        }
    }
    
    public boolean Tell(PerceptSentence sentence){
        try{
            int coordinates[] = sentence.getCoordinates();
            int x = coordinates[0];
            int y = coordinates[1];
            
            
            if(sentence.hasGlitter()){
                q = new Query("assertz(glitter(room("+x+","+y+"))).");
                if(!q.hasSolution())
                    return false;
            }
            
            if(sentence.hasStench()){
                q = new Query("assertz(stench(room("+x+","+y+"))).");
                if(!q.hasSolution())
                    return false;
            }
                        
            if(sentence.hasBreeze()){
                q = new Query("assertz(breeze(room("+x+","+y+"))).");
                if(!q.hasSolution())
                    return false;
            }
            
        } catch(JPLException e){
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }
    
    
    public boolean Tell(ActionSentence sentence){
        try{
            int actionType = sentence.getActionType();
            int oldRoom[] = sentence.getPreviousPosition();
            int newRoom[] = sentence.getNewPosition();
            int oldX = oldRoom[0], oldY = oldRoom[1], newX = newRoom[0], newY = newRoom[1];
            
            if(actionType != 2){            
            q = new Query("retractall(hunter(_)).");
                if(!q.hasSolution())
                    return false;
            
            q = new Query("assertz(hunter(room("+newX+","+newY+"))).");
                if(!q.hasSolution())
                    return false;
                
            q = new Query("assertz(visited(room("+newX+","+newY+"))).");
                if(!q.hasSolution())
                    return false;
                
            }
            
            switch(actionType){
                case 1:
                    q = new Query("retractall(stench(_)).");
                    if(!q.hasSolution())
                        return false;
                    // game is won, try to find a way to stop
                    break;
                case 2:
                    q = new Query("retract(glitter(room("+newX+","+newY+"))).");
                    if(!q.hasSolution())
                        return false;
                    q = new Query("assertz(hasGold(hunter)).");
                    if(!q.hasSolution())
                        return false;
                    break;
                case 3:
                    for(int room[] : sentence.getAdjacentRooms()){
                        int x = room[0];
                        int y = room[1];
                        q = new Query("assertz(room("+x+","+y+"))");
                        // System.err.println("asserting room " + x + " , " + y);
                        if(!q.hasSolution())
                            return false;
                    }
                    break;
                case 4:
                    for(int room[] : sentence.getAdjacentRooms()){
                        int x = room[0];
                        int y = room[1];
                        q = new Query("assertz(room("+x+","+y+"))");
                        System.err.println("asserting room " + x + " , " + y);
                        if(!q.hasSolution())
                            return false;
                    }
                    break;
                default:
                    System.out.println("should not reach this !!\nActionType is " + actionType);
                    return false;
            }
            
        } catch(JPLException e){
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }
    
    
    public Action Ask(int time){
        try{
            q = new Query("bestAction(X).");
            // System.out.println("Found action? "+(q.hasSolution()?"true":"false")+"\n");
            Map<String,Term>[] solution = q.allSolutions();
            Term term = solution[0].get("X");
            Term[] args = term.listToTermArray();
            int actionType = args[0].intValue();
            int targetRoom[] = {args[1].arg(1).intValue(),args[1].arg(2).intValue()};
            ArrayList<int[]> path = new ArrayList<int[]>();
            for(Term arg : args[2].listToTermArray()){
                int room[] = {arg.arg(1).intValue(),arg.arg(2).intValue()};
                path.add(room);
            }
            Action action = new Action(actionType,targetRoom,path,time);
            return action;
            
            
            
        } catch(JPLException e){
            System.err.println(e.getMessage());
        }
        return null;
    } 
}
