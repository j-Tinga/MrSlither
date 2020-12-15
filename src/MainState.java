import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author sam
 */
public class MainState extends StateBasedGame{
    
    static boolean showFPS = true;
    static int fpslimit = 60;

    public MainState(String title) {
        super(title);
    }
        
    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new MainState("Mr.Slither"));
        
        app.setDisplayMode(900,600,false);
        app.setSmoothDeltas(true);
        app.setAlwaysRender(true);
        app.setTargetFrameRate(fpslimit);
        app.setShowFPS(showFPS);
        
        app.start();
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        this.addState(new MainMenuState());
        this.addState(new HowToPlayState());
        this.addState(new CreditsState());
        this.addState(new MrSlither());
    }
    
}
