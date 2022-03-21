package wumpusworld;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
public class WumpusPlanet {

Room[][] rooms = new Room [4][4];
Room startRoom = rooms[0][0];

int PlayerX = 0;
int PlayerY = 0;
boolean wumpusAlive = true;
boolean hasArrow = true;
boolean isinPit = false;
boolean hasgold = false;
static boolean gameover = false;
int score = 50;  //did 50 just as an example, can be changed afterward
  

public WumpusPlanet (int PlayerX, int PlayerY){
  WorldGenerator();
  // setRooms();
  this.PlayerX = PlayerX;
  this.PlayerY = PlayerY; 
  
}

ArrayList<int[]> getAdjacentRooms(int x, int y){
    
    ArrayList<int[]> adjacentRooms = new ArrayList<int[]>();
    if(y+1>=0 && y+1<4){
        int[] coordinates = {x, y+1};
        adjacentRooms.add(coordinates);
    }
    if(y-1>=0 && y-1<4){
        int[] coordinates = {x, y-1};
        adjacentRooms.add(coordinates);
    }
    if(x-1>=0 && x-1<4){
        int[] coordinates = {x-1, y};
        adjacentRooms.add(coordinates);
    }
    
    
    if(x+1>=0 && x+1<4){
        int[] coordinates = {x+1, y};
        adjacentRooms.add(coordinates);
    }
  
    
    return(adjacentRooms);
    
   }
  
void setRooms (){
    String file = "WumpusWorld.csv";
    BufferedReader reader = null;
    String line = "";
    try{
      reader = new BufferedReader (new FileReader(file));
      line = reader.readLine();
      while((line = reader.readLine()) != null){
        String[] row = line.split(",");
        int X = Integer.parseInt(row[0]);
        int Y = Integer.parseInt(row[1]);
        boolean breeze = Boolean.parseBoolean(row[2]);
        boolean stench = Boolean.parseBoolean(row[3]);
        boolean glitter = Boolean.parseBoolean(row[4]);
        boolean wumpus = Boolean.parseBoolean(row[5]);
        boolean pit = Boolean.parseBoolean(row[6]);
        boolean gold = Boolean.parseBoolean(row[7]);
        this.rooms[X][Y] = new Room (X, Y, breeze, stench, glitter, wumpus, gold, pit);
        
      }
    }
    catch(Exception e){
      
    }  
 } 

    int getPlayerX(){
        return(PlayerX);
    }
    
    int getPlayerY(){
        return(PlayerY);
    }
    int getScore(){
        return(score);
    }
   int getXWumpus(){
        
        for(int i=0; i<4; i++){
            for(int j=0 ; j<4; j++){
                if(hasWumpus(i, j)){
                    return (i);
                }
            }
        }
        return(-1);
    }
    
    int getYWumpus(){
        
        for(int i=0; i<4; i++){
            for(int j=0 ; j<4; j++){
                if(hasWumpus(i, j)){
                    return (j);
                }
            }
        }
        return(-1);
    }
    boolean gameOver(){
        return gameOver();
    }
    
    boolean isinPit (){
        return isinPit;
    }
    boolean isWumpusAlive(){
        return wumpusAlive;
    }
    
    boolean hasArrow(){
        return hasArrow ;
    }
    
    boolean hasgold (){
        return hasgold;
    }
    
    
    boolean hasPit(int X, int Y){
       return(rooms[X][Y].getPit());
    }
    boolean hasBreeze(int X, int Y){
       return(rooms[X][Y].getBreeze());
    }
    boolean hasStench(int X, int Y){
        return(rooms[X][Y].getStench());
    }
    boolean hasGlitter(int X, int Y){
       return(rooms[X][Y].getGlitter());
    }
    boolean hasGold(int X, int Y){
       return(rooms[X][Y].getGold());
    }
    boolean hasWumpus(int X, int Y){
       return(rooms[X][Y].getWumpus());
    }
    boolean hasPlayer(int X, int Y){
        
    return (X == PlayerX && Y == PlayerY);
    }
    //world generate import rand for pits prob of 20% dimensions of the world
    
    public void WorldGenerator(){
        int X, Y;
        for(X=0; X<=3; X++){
            for(Y=0; Y<=3; Y++){
                 this.rooms[X][Y] = new Room (X, Y, false, false, false, false, false, false);
            }
        }
        
        Random rand =  new Random();
        
        //put random pit and stench in adjacent rooms
        for(int i= 0; i<4; i++){
            for(int j=0; j<4; j++){
                
                if(i!=0 || j!=0){
                    double probability = rand.nextDouble();
                    if(probability<0.1){
                         addPit(i, j);
                     }
                }    
            }
        } 
        
        //put random gold and glitter in the same room
        int xgold;
        int ygold;
        do{
            xgold = rand.nextInt(4);
            ygold = rand.nextInt(4);
        }while(xgold == 0 && ygold ==0);
        addGold(xgold, ygold);
        
        //put wumpus in random room and add stench in adjacent rooms
       int xwumpus;
       int ywumpus;
        do{
            xwumpus = rand.nextInt(4);
            ywumpus = rand.nextInt(4); 
        }while(xwumpus ==0  && ywumpus ==0);
        addWumpus(xwumpus, ywumpus);
        
        
        
    }
    
    void addWumpus(int x, int y){
        
        rooms[x][y].setWumpus(true);
        if(y+1>=0 && y+1<=3)
            rooms[x][y+1].setStench(true);
        if(y-1>=0 && y-1<=3)
            rooms[x][y-1].setStench(true);
        if(x+1>=0 && x+1<=3)
            rooms[x+1][y].setStench(true);
        if(x-1>=0 && x-1<=3)
            rooms[x-1][y].setStench(true);
    }
    
    
    void addPit(int x, int y){
        // System.out.println("x:"+ x + "y:" +y);
        rooms[x][y].setPit(true);
        if(y+1>=0 && y+1<=3)
            rooms[x][y+1].setBreeze(true);
        if(y-1>=0 && y-1<=3)
            rooms[x][y-1].setBreeze(true);
        if(x+1>=0 && x+1<=3)
            rooms[x+1][y].setBreeze(true);
        if(x-1>=0 && x-1<=3)
            rooms[x-1][y].setBreeze(true);
    }
    
    void addGold(int x, int y){
        
        rooms[x][y].setGold(true);
        rooms[x][y].setGlitter(true);
    }
}