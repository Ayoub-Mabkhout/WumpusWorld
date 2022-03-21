/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package knowledgebase;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author ayoub
 */
public class ActionSentence {
    private int actionType;
    private int previousPosition[];
    private int newPosition[];
    private ArrayList<int[]> path;
    private ArrayList<int[]> adjacentRooms;
    private String message;
    private int time;
    
    public ActionSentence(int[] previousPos, Action action, ArrayList<int[]> adjacent){
        this.previousPosition = previousPos;
        this.actionType = action.getActionType();
        this.newPosition = action.getTargetRoom();
        this.path = action.getPath();
        this.adjacentRooms = adjacent;
        this.time = action.getTime();
        this.message = generateMessage();
    }

    public int getActionType() {
        return actionType;
    }

    public int[] getPreviousPosition() {
        return previousPosition;
    }

    public int[] getNewPosition() {
        return newPosition;
    }

    public ArrayList<int[]> getPath() {
        return path;
    }

    public ArrayList<int[]> getAdjacentRooms() {
        return adjacentRooms;
    }

    public String getMessage() {
        return message;
    }

    public int getTime() {
        return time;
    }
    
    
    
    private String generateMessage(){
        String oldRoomStr = "room("+previousPosition[0]+","+previousPosition[1]+")";
        String newRoomStr = "room("+newPosition[0]+","+newPosition[1]+")";
        String msg = "--- Action Sentence ---\n";
        if(!Arrays.equals(previousPosition, newPosition))
            msg += "Moving from " + oldRoomStr + " to " + newRoomStr;
        
        switch(actionType){
            case 1:
                msg += " to kill Wumpus in adjacent room";
                break;
            case 2:
                msg += "Grabbing gold in " + newRoomStr;
                break;
            case 3:
            case 4:
                break;
            default:
                System.out.println("Should not reach this in action sentence!!\nActionType is " + actionType);
                return null;
        }
        msg += " at time t = " + time + ".\n";
        return msg;
    }
}
