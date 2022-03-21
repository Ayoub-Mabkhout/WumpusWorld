package wumpusworld;


public class Room{
    private int X;
    private int Y;
    private boolean breeze = false;
    private boolean stench = false;
    private boolean glitter = false;
    private boolean wumpus = false;
    private boolean gold = false;
    private boolean pit = false;

    public Room(int X, int Y){
        this.X = X;
        this.Y = Y;
    }
  
  public Room ( int X, int Y, boolean breeze, boolean stench,boolean glitter, boolean wumpus, boolean gold, boolean pit){
    this(X, Y);
    this.breeze = breeze;
    this.stench = stench;
    this.glitter = glitter;
    this.wumpus= wumpus;
    this.gold = gold;
    this.pit = pit;
  }

  void setBreeze (boolean breeze){
    this.breeze= breeze;
  }
  void setStench (boolean stench){
    this.stench= stench;
  }
  void setGlitter (boolean glitter){
    this.glitter= glitter;
  }
  void setWumpus (boolean wumpus){
    this.wumpus= wumpus;
  }
  void setGold(boolean gold){
    this.gold= gold;
  }
  void setPit (boolean pit){
    this.pit= pit;
  }
  
  boolean getBreeze (){
      return breeze;
  }
  boolean getStench (){
      return stench ;
  }
  
  boolean getGlitter (){
      return glitter;
  }
  
  boolean getWumpus (){
      return wumpus;
  }
  
  boolean getGold(){
      return gold;
  }
  
  boolean getPit(){
      return pit;
  }
}