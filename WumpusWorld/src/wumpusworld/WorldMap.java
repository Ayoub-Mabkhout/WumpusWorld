package wumpusworld;

import java.util.Vector;
import java.awt.Point;

/**
 *
 * @author CHIFAA
 */
public class WorldMap {
    private int size;
    private Point wumpus;
    private Point gold;
    private Vector<Point> pits;
    
    public WorldMap(int size)
    {
        this.size = size;
        pits = new Vector<Point>();
    }
    public void addWumpus(int x, int y)
    {
        wumpus = new Point(x,y);
    }
     public void addGold(int x, int y)
    {
        gold = new Point(x,y);
    }
     public void addPit(int x, int y)
    {
        pits.add(new Point(x,y));
    }
    public int getSize()
    {
        return size;
    }
    public Point getWumpus()
    {
        return wumpus;
    }
     public Point getGold()
    {
        return gold;
    }
     public Vector<Point> getPits()
    {
        return pits;
    }
     
   /* public boolean hasPit(int x, int y)
    {
        for (Point p:pits)
        {
            if (p.x == x && p.y == y)
            {
                return true;
            }
        }
        return false;
    } not sure if we are allowed to do that*/
     
   /*  public WumpusWorld generateWorld()
    {
        WumpusWorld w = new WumpusWorld();
       /* w.addWumpus(wumpus.x, wumpus.y);
        w.addGold(gold.x, gold.y);
        for (int i = 0; i < pits.size(); i++)
        {
            w.addPit(pits.get(i).x, pits.get(i).y);
        }for now we are hardcoding this 
        return w; 
    }*/
    
    
}
