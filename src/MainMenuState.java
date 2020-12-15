import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

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
        play.draw(300,300);
        credits.draw(300,390);
        g.drawString(Xpos + "+" + Ypos,20,20);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Xpos = Mouse.getX();
        Ypos = Mouse.getY();
        
        if((Xpos > 300 && Xpos < 800) && (Ypos > 220 && Ypos < 300)){ //goes to how to play
            if(Mouse.isButtonDown(0)){
                sbg.enterState(1);
            }          
        }
        
        if((Xpos > 300 && Xpos < 800) && (Ypos > 130 && Ypos < 210)){ //goes to highscore
            if(Mouse.isButtonDown(0)){
                sbg.enterState(2);
            }          
        }

    }
    
}
