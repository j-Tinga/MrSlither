import java.util.Random;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class PlayGameState extends BasicGameState{
    @Override
    public int getID() {
        return 3;
    }
    
    private int direction=2, buffer= 3;
    private boolean died = false;
    private Food food;
    private Snake snake;
    private House house;
    private Position freePos;   
       
    private boolean paused = false;
    
    Random rnd = new Random();

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {  //For initializing objects and assets
        snake= new Snake();
        snake.initBody();
        house = new House();
        food = new Food();
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        house.getMap().render(0, 0, 8);   //renders background
        house.getMap().render(0, 0, house.getMapSize());    //renders wall (Changing the order of Layers in House.tmx will affect what is rendered)
        house.getMap().render(0, 0, house.getMapSize()+1);  //renders floor
        
        g.draw(snake.getHeadHitbox());  //render Hitboxes
        g.draw(food.getObjectHitbox());
        
        snake.getBody().forEach((renderBody) -> {   //render image of snakeBody
           snake.getBodyImg().draw((float)renderBody.getX(), (float)renderBody.getY());
        });   
        snake.getActiveHead().draw((float)snake.getHeadPosition().getX(), (float)snake.getHeadPosition().getY());  //render image of snake Head
        food.getObjectImage().draw((float)food.getObjectPosition().getX(), (float)food.getObjectPosition().getY()); //renders image of food
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        //Snake Controls
        Input input = gc.getInput();
        
        if(input.isKeyPressed(Input.KEY_ESCAPE)){
            System.out.println("Escape pressed");
            if(paused){
                paused = false;
            } else {
                paused = true;
                sbg.enterState(4);
            }
        }
        
        
        if ((input.isKeyDown(Input.KEY_W)|| input.isKeyDown(Input.KEY_UP))&& snake.getDirection() != 3){
            direction = 1;
            paused = false;
        }
        if ((input.isKeyDown(Input.KEY_D)|| input.isKeyDown(Input.KEY_RIGHT)) && snake.getDirection() != 4){
            direction = 2;
            paused = false;
        }
        if ((input.isKeyDown(Input.KEY_S)|| input.isKeyDown(Input.KEY_DOWN)) && snake.getDirection() != 1){
            direction = 3;
            paused = false;
        }
        if ((input.isKeyDown(Input.KEY_A)|| input.isKeyDown(Input.KEY_LEFT)) && snake.getDirection() != 2){
            direction = 4;
            paused = false;
        }
        
        if(!paused){
        buffer++;
        if(buffer == 4 && died != true){    //change Snake Speed through buffer ( Speed = fps/buffer, current snake moves at 7.5 tiles per second)
            snake.move(direction);
            died=house.checkWallCollision(snake) || snake.checkSnake(); //Check if snake is still alive (will be replaced with Game Over GameState soon)
            
            if(snake.getHeadHitbox().intersects(food.getObjectHitbox())){    //Check if Snake Collides with food
                freePos = findFreeSpace();  //Finds a free space in the board
                food.moveObject(freePos);  //Moves the food to new free space
                snake.eatFood(food);    //Snake eats food and stuff happens
            }
           
            buffer=0;
        }
        }
    }
    
    @Override
    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
        if(paused){
            System.out.println("Resuming game from minigame");
            snake.addMoney(100);
        }
    }
    
     public Position findFreeSpace(){
        Position freeSpace = new Position();
        do{
            freeSpace.setX(house.getMinWidth() + rnd.nextInt(house.getTilesWidth())*32);
            freeSpace.setY(house.getMinHeight() + rnd.nextInt(house.getTilesHeight())*32);
        }while(snake.checkBodyCollision(freeSpace));
       return freeSpace;
    }
    
}