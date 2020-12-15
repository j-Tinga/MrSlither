import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class HowToPlayState extends BasicGameState{
    
    private Image inst,play;
    int Xpos, Ypos;

    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
       inst = new Image("assets/how2play.png");
       play = new Image("assets/play.png");  
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        inst.draw();
        play.draw(40,490);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Xpos = Mouse.getX();
        Ypos = Mouse.getY();
       
        if((Xpos > 40 && Xpos < 295) && (Ypos > 35 && Ypos < 110)){ //goes to how to play
            if(Mouse.isButtonDown(0)){
                sbg.enterState(3);
            }          
        }
    }
    
}
