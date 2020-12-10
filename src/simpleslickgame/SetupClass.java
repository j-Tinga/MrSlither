package simpleslickgame;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class SetupClass extends StateBasedGame
{       
        public static int finished = 0;
        
	public SetupClass(String gamename)
	{
		super(gamename);
	}
        
	public static void main(String[] args)
	{
		try
		{
			AppGameContainer appgc;
			appgc = new AppGameContainer(new SetupClass("Test"));
			appgc.setDisplayMode(900, 600, false);
                        appgc.setTargetFrameRate(60);
			appgc.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(SetupClass.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        this.addState(new Minigame());
        this.addState(new StateTest());
    }
}