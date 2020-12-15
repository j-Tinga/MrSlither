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
    
    //added by jed
    private Work work;
    private PayRent rent;
    Meter hungerbar, rentbar;
    Score score = new Score();
    Money money;
    
    //

    Random rnd = new Random();

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {  //For initializing objects and assets
        snake= new Snake();
        snake.initBody();
        house = new House();
        food = new Food();
        
        //added by jed
        work = new Work();
        rent = new PayRent();
        //meter dimensions
        hungerbar = new Meter(440, 15, 150, 20);
          rentbar = new Meter(440, 50, 150, 20);
        //money value initialized
        money = new Money(snake.getMoney());
          
        

    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        house.getMap().render(0, 0, 8);   //renders background
        house.getMap().render(0, 0, house.getMapSize());    //renders wall (Changing the order of Layers in House.tmx will affect what is rendered)
        house.getMap().render(0, 0, house.getMapSize()+1);  //renders floor
        
        g.draw(snake.getHeadHitbox());  //render Hitboxes
        //added by jed
        g.draw(food.getObjectHitbox());
        g.draw(work.getObjectHitbox());
        g.draw(rent.getObjectHitbox());
        //
        
        snake.getBody().forEach((renderBody) -> {   //render image of snakeBody
           snake.getBodyImg().draw((float)renderBody.getX(), (float)renderBody.getY());
        });   
        snake.getActiveHead().draw((float)snake.getHeadPosition().getX(), (float)snake.getHeadPosition().getY());  //render image of snake Head
        food.getObjectImage().draw((float)food.getObjectPosition().getX(), (float)food.getObjectPosition().getY()); //renders image of food
        
        //added by jed
        work.getObjectImage().draw((float) work.getObjectPosition().getX(), (float) work.getObjectPosition().getY()); //renders image of work
        rent.getObjectImage().draw((float) rent.getObjectPosition().getX(), (float) rent.getObjectPosition().getY()); //renders image of rent
        //
        
        //score initialization - added by jed
           score.scoreBoard(g);
        //money score initialization
//           g.drawString("Money ", 700, 20);
//           g.drawString(" $ "+ money.getMoney(), 680, 50);
//            Money mon = new Money(snake.getMoney());
            money.setInitMoney(g);
        // meter initialization
          hungerbar.setHungerBar(g);
          rentbar.setRentBar(g);
        //

    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        //Snake Controls
        Input input = gc.getInput();
        if ((input.isKeyDown(Input.KEY_W)|| input.isKeyDown(Input.KEY_UP))&& snake.getDirection() != 3){
            direction = 1;
        }
        if ((input.isKeyDown(Input.KEY_D)|| input.isKeyDown(Input.KEY_RIGHT)) && snake.getDirection() != 4){
            direction = 2;
        }
        if ((input.isKeyDown(Input.KEY_S)|| input.isKeyDown(Input.KEY_DOWN)) && snake.getDirection() != 1){
            direction = 3;
        }
        if ((input.isKeyDown(Input.KEY_A)|| input.isKeyDown(Input.KEY_LEFT)) && snake.getDirection() != 2){
            direction = 4;     
        }
        
        
        // increases score at a constant rate
          score.increaseScore();
        //makes the meter reduces at a constant rate
            // Hunger Meter
          hungerbar.reduceHungerBar();
            //Rent Meter
          rentbar.reduceRentBar();
        //
       
       //COLLISIONS 
        buffer++;
        if(buffer == 4 && died != true){    //change Snake Speed through buffer ( Speed = fps/buffer, current snake moves at 7.5 tiles per second)
            snake.move(direction);
            died=house.checkWallCollision(snake) || snake.checkSnake(); //Check if snake is still alive (will be replaced with Game Over GameState soon)
            
            if(snake.getHeadHitbox().intersects(food.getObjectHitbox())){    //Check if Snake Collides with food
               
                freePos = findFreeSpace();  //Finds a free space in the board
                food.moveObject(freePos);  //Moves the food to new free space
                snake.eatFood(food);    //Snake eats food and stuff happens
                food.increaseHungerLength(hungerbar); // increases the hunger bar
                food.decreaseMoney(money);// decreases the current money
                
            }else if(snake.getHeadHitbox().intersects(work.getObjectHitbox())){    //Check if Snake Collides with work
                freePos = findFreeSpace();  //Finds a free space in the board
                work.moveObject(freePos);  //Moves the food to new free space
                work.increaseMoney(money); // increases the current money
                
            }else if(snake.getHeadHitbox().intersects(rent.getObjectHitbox())){    //Check if Snake Collides with rent
                freePos = findFreeSpace();  //Finds a free space in the board
                rent.moveObject(freePos);  //Moves the food to new free space
                rent.increaseRentLength(rentbar); // increases the hunger bar
                rent.decreaseMoney(money); // decreases the current money
            }
               
                  buffer=0;      

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