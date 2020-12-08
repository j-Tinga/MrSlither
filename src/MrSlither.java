import org.newdawn.slick.*;
import org.newdawn.slick.tiled.TiledMap;


public class MrSlither extends BasicGame {

    static int width = 900;
    static int height = 600;
    static boolean fullscreen = false;
    static boolean showFPS = true;
    static String title = "Mr Slither";
    static int fpslimit = 30;   //REMINDER: Increasing the fps will also increase snek speed, remember to change snek speed accordingly
    int buffer= 0;
    int objectLayer;
    
    private Image snakeUp, snakeRight, snakeDown, snakeLeft, body, activeHead;
    private TiledMap map, renderSnake;
    
    Snake snake= new Snake();
    int i, direction = 2;
    boolean collides;
    
    public MrSlither(String title) {
        super(title);
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        snake.initBody();
        map = new TiledMap("assets/Tileset.tmx");
        renderSnake = new TiledMap("assets/Snake.tmx");
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
    public void render(GameContainer gc, Graphics g) throws SlickException {
        map.render(0, 0);
        snake.getHitBox().forEach((renderHitbox) -> {
            g.draw(renderHitbox);
        });
        activeHead.draw((float)snake.getHeadPosition().getX(), (float)snake.getHeadPosition().getY());
        g.drawString("Snake Coordinates ", 20, 30);
        g.drawString("X: "+ snake.getHeadPosition().getX()%width, 20, 45);
        g.drawString("Y: "+ snake.getHeadPosition().getY()%height, 20, 60);
        g.drawString("Body X: "+ (snake.getBody().get(9).getX()%width - snake.getHeadPosition().getX()%width), 75, 45);
        g.drawString("Body Y: "+  (snake.getBody().get(9).getY()%width - snake.getHeadPosition().getY()%width), 75, 60);
        g.drawString("Collision: "+ collides, 20, 90);
    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
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
        objectLayer = map.getLayerIndex("Wall");
        buffer++;
        if(buffer == 15){
            snake.move(direction);
            buffer=0;
            if(map.getTileId(snake.getHeadPosition().getX()/32, snake.getHeadPosition().getY()/32, objectLayer)==1){
                collides = true;
            }
        }
        
    }
    
    
    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new MrSlither(title));
        app.setDisplayMode(width, height, fullscreen);
        app.setSmoothDeltas(true);
        app.setAlwaysRender(true);
        app.setTargetFrameRate(fpslimit);
        app.setShowFPS(showFPS);
        app.start();
    }
    
}