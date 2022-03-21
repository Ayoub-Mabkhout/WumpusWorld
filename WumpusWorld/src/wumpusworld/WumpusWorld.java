/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package wumpusworld;

import javax.swing.JOptionPane;
import wumpusworld.GUI.*;
import knowledgebase.Action;

/**
 *
 * @author CHIFAA
 */
public class WumpusWorld {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       // initialization phase
        GUI gui = new GUI();
        Action action;
        JOptionPane.showMessageDialog(null, "Started Wumpus Game", "Start", 1);
        
      // do loop that takes a new action and changes the GUI according to the action

      // main game loop
        while(!gui.w.gameover){
          // gui.wait = true;
          action  = gui.agent.run();
          gui.UpdateGUI(action.getActionType(), action);
          gui.w.gameover = gui.gameOver();
          // loop that gets user input to continue to   the next iteration of the game loop
          // equivalent to press spacebar or enter to continue
          
          /*System.out.println("\n---- Press Enter or Spacebar to continue----\n");
          while(gui.wait){
            continue;
          }*/
            
            }


      
    }
    
}
