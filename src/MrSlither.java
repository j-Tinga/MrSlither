import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author sam
 */
public class MrSlither extends StateBasedGame{
    
    static boolean showFPS = true;
    static int fpslimit = 30;

    public MrSlither(String title) {
        super(title);
    }
        
    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new MrSlither("Mr.Slither"));
        
        app.setDisplayMode(900,600,false);
        app.setSmoothDeltas(true);
        app.setAlwaysRender(true);
        app.setTargetFrameRate(fpslimit);
        app.setShowFPS(showFPS);
        
        app.start();
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        this.addState(new MainMenuState()); //id = 0
        this.addState(new HowToPlayState());    //id = 1
        this.addState(new CreditsState()); // id =2
        this.addState(new PlayGameState()); // id = 3
        this.addState(new GameOverState()); // id = 5
    }
    
}
