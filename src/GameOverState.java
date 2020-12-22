import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class GameOverState extends BasicGameState{
    
    private Image gameover;
    int Xpos, Ypos;
    
    @Override
    public int getID() {
        return 5;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
       gameover = new Image("assets/gameover.png");
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        gameover.draw();
        g.drawString(Xpos + "+" + Ypos,20,20);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Xpos = Mouse.getX();
        Ypos = Mouse.getY();
       
        if((Xpos > 330 && Xpos < 580) && (Ypos > 70 && Ypos < 140)){ //goes back
            if(Mouse.isButtonDown(0)){
                sbg.enterState(0,new FadeOutTransition(),new FadeInTransition());
            }          
        }
    }
}