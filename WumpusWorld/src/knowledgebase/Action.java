/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package knowledgebase;

import java.util.ArrayList;

/**
 *
 * @author ayoub
 */
public class Action {
    private int actionType;
    private int targetRoom[];
    private ArrayList<int []> path;
    private String message;
    private int time;

    public int getTime() {
        return time;
    }
    
    public Action(int action, int room[], ArrayList<int []> path, int time){
        actionType = action;
        targetRoom = room;
        this.path = path;
        this.time = time;
        this.message = generateMessage();
    }

    public int getActionType() {
        return actionType;
    }

    public int[] getTargetRoom() {
        return targetRoom;
    }

    public ArrayList<int[]> getPath() {
        return path;
    }

    public String getMessage() {
        return message;
    }
    
    private String generateMessage(){
        String msg = "--- Action ---\nBest action is to ";
        String currentRoomStr = "room("+path.get(0)[0]+","+path.get(0)[1]+")";
        String targetRoomStr = "room("+targetRoom[0]+","+targetRoom[1]+")";
        int pointsUsed = path.size()-1;
        switch(actionType){
            case 1:
                msg += "move from " + currentRoomStr + " to " + targetRoomStr;
                msg += "to kill adjacent wumpus";
                pointsUsed += 10;
                break;
            case 2:
                msg += "grab gold in " + targetRoomStr;
                break;
            case 3:
                msg += "move from " + currentRoomStr + " to safe " + targetRoomStr;
                break;
          case 4:
                msg += "move from " + currentRoomStr + " to unsafe " + targetRoomStr + " (because no other safe room has been found)";
                break;
            default:
                System.out.println("should not reach this !!\nActionType is " + actionType);
                return null;
        }
        msg += " at time t = "+time+", which costs a total of " + pointsUsed + " point(s).\n";
        if(actionType == 2)
            msg += "Grabbing gold also increases the points by 1000\n";
        return msg;
    }
    
    public String toString(){
        String s = "---- Best action ----\nactionType: " + actionType + "\ntargetRoom: room("+targetRoom[0]+","+targetRoom[1]+")\npath: [";
        for(int[] room : path){
            s += "room(" + room[0] + "," + room[1] + ") --> ";
        }
        s = s.substring(0, s.lastIndexOf(" --> "));
        s += "]\nmessage: " + message;
        return s;
    }
}
