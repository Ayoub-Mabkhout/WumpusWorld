package wumpusworld;


import javax.swing.*;
import knowledgebase.*;

import java.awt.event.*;
import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author CHIFAA
 */
public class GUI implements ActionListener{
    
    
    private JFrame frame;
    private JPanel gamepanel;
    private JLabel score;
    private JLabel status;
    public WumpusPlanet w;
    public Agent agent;
    private knowledgebase.Action action;
    private JPanel[][] blocks;
  
    
    private ImageIcon l_breeze;
    private ImageIcon l_stench;
    private ImageIcon l_pit;
    private ImageIcon l_glitter;
    private ImageIcon l_wumpus;
    private ImageIcon l_gold;
    private ImageIcon l_agent;
    
   
    protected static boolean wait;
  
    /**
     * Creates and start the GUI.
     */
    public GUI()
    {
        if (!checkResources())
        {
            JOptionPane.showMessageDialog(null, "Unable to start GUI. Missing icons.", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        String path = "src//wumpusworld//icons//";
        l_breeze = new ImageIcon(path + "Breeze.png");
        l_stench = new ImageIcon(path +"Stench.png");
        l_pit = new ImageIcon(path + "Pit.png");
        l_glitter = new ImageIcon(path + "Glitter.png");
        l_wumpus = new ImageIcon(path + "Wumpus.png");
        l_gold = new ImageIcon(path + "Gold.png");
        l_agent = new ImageIcon (path + "Agent.png");
        agent = new Agent(0,0,0);
        w = agent.w;
       
        createWindow();
    }
    
    /**
     * Checks if all resources (icons) are found.
     * 
     * @return True if all resources are found, false otherwise. 
     */
    private boolean checkResources()
    {
        try
        {
            String path = "src//wumpusworld//icons//";
            File f;
            f = new File(path + "Breeze.png");
            if (!f.exists())
                return false;
            
            f = new File(path + "Stench.png");
            if (!f.exists()) 
                return false;
            
            f = new File(path +"Pit.png");
            if (!f.exists()) 
                return false;
            
            f = new File(path +"Gold.png");
            if (!f.exists()) 
                return false;
            
            f = new File(path +"Wumpus.png");
            if (!f.exists())
                return false;
            
            f = new File(path +"Glitter.png");
            if (!f.exists()) 
                return false;
            f = new File(path +"Agent.png");
            if (!f.exists()) 
                return false;
        }
        catch (Exception ex)
        {
            return false;
        }
        return true;
    }
    
    /**
     * Creates all window components.
     */
    private void createWindow()
    {
        frame = new JFrame("Wumpus World");
        frame.setSize(820, 640);
        frame.getContentPane().setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        gamepanel = new JPanel();
        gamepanel.setPreferredSize(new Dimension(600,600));
        gamepanel.setBackground(Color.GRAY);
        gamepanel.setLayout(new GridLayout(4,4));
        
        //Add blocks
        blocks = new JPanel[4][4];
        for (int j = 3; j >=0; j--)
        {
            for (int i = 0; i < 4; i++)
            {   
                blocks[i][j] = new JPanel();
                blocks[i][j].setBackground(Color.white);
                blocks[i][j].setPreferredSize(new Dimension(150,150));
                blocks[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
                blocks[i][j].setLayout(new GridLayout(2,2));
                
                
                if(w.rooms[i][j].getPit())
                    blocks[i][j].add(new JLabel (l_pit));
                
                if(w.rooms[i][j].getBreeze())
                    blocks[i][j].add(new JLabel(l_breeze));
                
                if(w.rooms[i][j].getGlitter())
                    blocks[i][j].add(new JLabel(l_glitter));
                
                
                if(w.rooms[i][j].getWumpus())
                   
                    blocks[i][j].add(new JLabel(l_wumpus));
                

                if(w.rooms[i][j].getStench())
                    blocks[i][j].add(new JLabel (l_stench));
                

                if(w.rooms[i][j].getGold())
                    blocks[i][j].add(new JLabel (l_gold));
                

                if(w.hasPlayer(i, j))
                    blocks[i][j].add(new JLabel (l_agent));
                
                gamepanel.add(blocks[i][j]);

            }
        }
        frame.getContentPane().add(gamepanel);
        //Add a delimiter
        JLabel l = new JLabel("");
        l.setPreferredSize(new Dimension(200,25));
        gamepanel.addKeyListener(new KeyEventListener(this));     
       //Show window
        frame.setVisible(true);

    }
   
    /**
     * Button commands.
     * 
     * @param e Button event.
     */
    public void actionPerformed(ActionEvent e)
    {
       
    }
    
    /**
     * Updates the game GUI to a new world state.
     */
    
    public void UpdateGUI(int actionType, knowledgebase.Action action){
        
        int[] TargetRoom = action.getTargetRoom();
         
        if(actionType == 1){
            
            blocks[TargetRoom[0]][TargetRoom[1]].add(new JLabel(l_agent));
            agent.AgentX = TargetRoom[0];
            agent.AgentY = TargetRoom [1];
            w.PlayerX = agent.AgentX;
            w.PlayerY = agent.AgentY;
            w.wumpusAlive = false ; 
            frame.setVisible(false);
            createWindow();
            
            JOptionPane.showMessageDialog(null, "Hurray! You Killed the Wumpus", "Congrats", 1);  
        }
        
        else if (actionType == 2){
            
           
            JOptionPane.showMessageDialog(null, "You grabbed the Gold", "Congrats", 1);
             
            w.rooms[agent.AgentX][agent.AgentY].setGlitter(false);
            w.rooms[agent.AgentX][agent.AgentY].setGold(false);
            frame.setVisible(false);
            createWindow();
        }
        else if (actionType == 3 || actionType == 4){

            agent.AgentX= TargetRoom[0];
            agent.AgentY = TargetRoom [1];
            w.PlayerX = agent.AgentX;
            w.PlayerY = agent.AgentY;
            blocks[TargetRoom[0]][TargetRoom[1]].add(new JLabel(l_agent));
            frame.setVisible(false);
            createWindow();
            
            if(actionType == 3)
                JOptionPane.showMessageDialog(null, "You moved to a safe room", "Great!",1);
            else
                JOptionPane.showMessageDialog(null, "You moved to a unsafe room", "Meeh!", 1);
          
        }

          
      }
        
   
  public boolean gameOver (){

     if(w.hasPit(agent.AgentX, agent.AgentY) || w.hasWumpus(agent.AgentX, agent.AgentY)){
            if(w.hasPit(agent.AgentX, agent.AgentY))
              JOptionPane.showMessageDialog(null, "You Lost: Got stuck in a Pit", "Oops!", 1);
            else
                JOptionPane.showMessageDialog(null, "You Lost: Got eaten by Wumpus", "Oops!", 1);
            return true;
          }
     else if (!w.wumpusAlive)
         return true ; 
    return false;
  }
   
  
    
    public static void main(String[] args){

    }

}
