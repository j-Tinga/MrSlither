import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author sam
 */
public class MainState extends StateBasedGame{

    public MainState(String title) {
        super(title);
    }
        
    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new MainState("Mr.Slither"));
        
        app.setDisplayMode(800,600,false);
        app.setAlwaysRender(true);
        
        app.start();
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        this.addState(new MainMenuState());
        this.addState(new HowToPlayState());
        this.addState(new MrSlither());
    }
    
}
