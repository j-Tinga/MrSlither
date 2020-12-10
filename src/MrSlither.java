import org.newdawn.slick.*;


public class MrSlither extends BasicGame {

    static int width = 900;
    static int height = 600;
    static boolean fullscreen = false;
    static boolean showFPS = false;
    static String title = "Mr Slither";
    static int fpslimit = 30;   //REMINDER: Increasing the fps will also increase snek speed, remember to change snek speed accordingly
    int direction=2, buffer= 3;
    
    private Image snakeUp, snakeRight, snakeDown, snakeLeft, body, activeHead; //images used to render the snake
    boolean died;
    
    //Objects
    private Snake snake;
    private House house;
    
    public MrSlither(String title) {
        super(title);
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        snake= new Snake();
        snake.initBody();
        house = new House();
       
        body = new Image("assets/body.png");
        snakeUp = new Image("assets/beautifulman.jpg"); //current size is 32x32;
        snakeRight = new Image("assets/beautifulman.jpg");
        snakeRight.rotate(90);
        snakeDown = new Image("assets/beautifulman.jpg");
        snakeDown.rotate(180);
        snakeLeft = new Image("assets/beautifulman.jpg");
        snakeLeft.rotate(270);
        activeHead = snakeRight;
    }
    
    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        house.getMap().render(0, 0, 8);   //renders background
        house.getMap().render(0, 0, house.getMapSize());    //renders wall (Changing the order of Layers in House.tmx will affect what is rendered)
        house.getMap().render(0, 0, house.getMapSize()+1);  //renders floor
        
        snake.getBody().forEach((renderBody) -> {
           body.draw((float)renderBody.getX(), (float)renderBody.getY());
        });
        
        activeHead.draw((float)snake.getHeadPosition().getX(), (float)snake.getHeadPosition().getY());
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
        buffer++;
        if(buffer == 4 && died == false){    //change Snake Speed through buffer ( Speed = fps/buffer, current snake moves at 7.5 tiles per second)
            snake.move(direction);
            died=snake.checkBodyCollision(); //Check for any collisions
            if(house.checkWallCollision(snake)){
                house.increaseMapSize();
            }
            buffer=0;
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