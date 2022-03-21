package wumpusworld;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
        
public class KeyEventListener implements KeyListener{

    private GUI gui;
    public KeyEventListener(GUI gui){
      this.gui = gui;
    }

    @Override
    public void keyTyped(KeyEvent e){
      return;
    }

    public void keyPressed(KeyEvent e){
      int keyCode = e.getKeyCode();
      if(keyCode == 10 || keyCode == 32)
          gui.wait = false;
      return;
    }

    public void keyReleased(KeyEvent e){
      return;
    }
}