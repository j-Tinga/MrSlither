import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MrSlither extends BasicGameState {

    static int width = 900;
    static int height = 600;
    static boolean fullscreen = false;
    static boolean showFPS = true;
    static String title = "Mr Slither";
    static int fpslimit = 60;   //REMINDER: Increasing the fps will also increase snek speed, remember to change snek speed accordingly
    
    private Image snakeUp, snakeRight, snakeDown, snakeLeft, body, activeHead;
    
    Snake snake= new Snake();
    int i, direction = 2;

    @Override
    public int getID() {
        return 2;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        snake.initBody();
        body = new Image("assets/body.png");
        snakeUp = new Image("assets/beautifulman.png"); //current size is 45x45;
        snakeRight = new Image("assets/beautifulman.png");
        snakeRight.rotate(90);
        snakeDown = new Image("assets/beautifulman.png");
        snakeDown.rotate(180);
        snakeLeft = new Image("assets/beautifulman.png");
        snakeLeft.rotate(270);
        activeHead = snakeRight;
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
         snake.getBody().forEach((renderBody) -> {
            body.draw((float)renderBody.getX(), (float)renderBody.getY());
        });
        activeHead.draw((float)snake.getHeadPosition().getX(), (float)snake.getHeadPosition().getY());
        g.drawString("Snake Coordinates ", 20, 30);
        g.drawString("X: "+ snake.getHeadPosition().getX()%width, 20, 45);
        g.drawString("Y: "+ snake.getHeadPosition().getY()%height, 20, 60);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
         Input input = gc.getInput();
        
        if ((input.isKeyDown(Input.KEY_W)|| input.isKeyDown(Input.KEY_UP))&& snake.getDirection() != 3){
            direction = 1;
            activeHead = snakeUp;
        }
        if ((input.isKeyDown(Input.KEY_D)|| input.isKeyDown(Input.KEY_RIGHT)) && snake.getDirection() != 4){
            direction = 2;
            activeHead = snakeRight;
        }
        if ((input.isKeyDown(Input.KEY_S)|| input.isKeyDown(Input.KEY_DOWN)) && snake.getDirection() != 1){
            direction = 3;
            activeHead = snakeDown;
        }
        if ((input.isKeyDown(Input.KEY_A)|| input.isKeyDown(Input.KEY_LEFT)) && snake.getDirection() != 2){
            direction = 4;    
            activeHead = snakeLeft;
        }
        snake.move(direction);
    }
/*
    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new MrSlither(title));
        app.setDisplayMode(width, height, fullscreen);
        app.setSmoothDeltas(true);
        app.setAlwaysRender(true);
        app.setTargetFrameRate(fpslimit);
        app.setShowFPS(showFPS);
        app.start();
    }
*/
}