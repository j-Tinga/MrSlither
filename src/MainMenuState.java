import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class MainMenuState extends BasicGameState{
    
    private Image bg,title,play,credits;
    int Xpos, Ypos;

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
       bg = new Image("assets/bg.png");
       title = new Image("assets/title.png");
       play = new Image("assets/play.png");
       credits = new Image("assets/credits.png");
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        bg.draw();
        title.draw(200,70);
        play.draw(310,300);
        credits.draw(310,390);
        g.drawString(Xpos + "+" + Ypos,20,20);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Xpos = Mouse.getX();
        Ypos = Mouse.getY();
        
        if((Xpos > 310 && Xpos < 565) && (Ypos > 220 && Ypos < 300)){ //goes to how to play
            if(Mouse.isButtonDown(0)){
                sbg.enterState(1,new FadeOutTransition(),new FadeInTransition());
            }         
        }
        
        if((Xpos > 310 && Xpos < 565) && (Ypos > 130 && Ypos < 210)){ //goes to highscore
            if(Mouse.isButtonDown(0)){
                sbg.enterState(2,new FadeOutTransition(),new FadeInTransition());
            }          
        }

    }
    
}
