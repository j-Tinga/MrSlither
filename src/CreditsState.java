import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class CreditsState extends BasicGameState{
    
    private Image bg,credits,back;
    int Xpos, Ypos;
    
    @Override
    public int getID() {
        return 2;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
       bg = new Image("assets/bg.png");
       credits = new Image("assets/creditspage.png");
       back = new Image("assets/back.png");
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        bg.draw();
        credits.draw(300,200);
        back.draw(50,50);
        g.drawString(Xpos + "+" + Ypos,20,20);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Xpos = Mouse.getX();
        Ypos = Mouse.getY();
       
        if((Xpos > 50 && Xpos < 100) && (Ypos > 490 && Ypos < 545)){ //goes back
            if(Mouse.isButtonDown(0)){
                sbg.enterState(0);
            }          
        }
    }
}