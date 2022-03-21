/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package knowledgebase;

/**
 *
 * @author ayoub
 */
public class PerceptSentence {
    private int coordinates[];
    private boolean glitter;
    private boolean breeze;
    private boolean stench;
    private String message;
    private int time;


    public boolean hasGlitter() {
        return glitter;
    }

    public boolean hasBreeze() {
        return breeze;
    }

    public boolean hasStench() {
        return stench;
    }
    
    public PerceptSentence(int coordinates [], boolean glitter, boolean breeze, boolean stench, int time){
        this.coordinates = coordinates;
        this.glitter = glitter;
        this.breeze= breeze;
        this.stench = stench;
        this.time = time;
        message = generateMessage();
    }

    public int[] getCoordinates() {
        return coordinates;
    }

    public String getMessage() {
        return message;
    }
    
    
    private String generateMessage(){
        String currentRoomStr = "room("+coordinates[0]+","+coordinates[1]+")";
        String msg = "--- Percept Sentence ---\nSensing " + currentRoomStr + " at t = " + time +":\n";

        if(glitter)
            msg += "I sense glitter.\n";
        if(stench)
            msg += "I sense stench.\n";
        if(breeze)
            msg += "I sense breeze.\n";
        if(!glitter && !stench && !breeze)
            msg += "I sense nothing in " + currentRoomStr + ".\n";
        return msg;
    }
    
}
