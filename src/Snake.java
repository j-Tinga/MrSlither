import java.util.ArrayList;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;


public class Snake {
    private int direction; //Direction goes Clockwise 1=UP, 2=RIGHT, 3=DOWN, 4=LEFT
    private Position headPosition;  //Current coordinates of the head
    private ArrayList<Position> body;   //the body of the snake is a Queue arraylist that contains the Positions of the body of the snake.
    private final Image snakeUp, snakeRight, snakeDown, snakeLeft, bodyImg;
    private final Shape headHitbox;
    private Image activeHead;
    private int length; //length pixels = legnth + speed of snake
    private int money;

    private int speed;  //Reminder: changing speed will change how long the snake looks
    int buffer;
    
    public Snake() throws SlickException {  //Default Snake
        direction = 2;
        headPosition = new Position(320,256);
        length = 3;  
        money = 100;
        speed = 32;   //Should be equal to the pixel dimensions of the snake
        body = new ArrayList<>();
        
        //initialize snake assets (current size is 32x32;)
        bodyImg = new Image("assets/body.png");
        snakeUp = new Image("assets/head.png");
        snakeRight = new Image("assets/head.png");
        snakeRight.rotate(90);
        snakeDown = new Image("assets/head.png");
        snakeDown.rotate(180);
        snakeLeft = new Image("assets/head.png");
        snakeLeft.rotate(270);
        activeHead = snakeRight;
        headHitbox = new Rectangle(320, 256, 16, 16);
    }

    //-------======Getters and Setters======-------

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public Position getHeadPosition() {
        return headPosition;
    }

    public void setHeadPosition(Position headPosition) {
        this.headPosition = headPosition;
    }

    public ArrayList<Position> getBody() {
        return body;
    }

    public void setBody(ArrayList<Position> body) {
        this.body = body;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }


    public float getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Image getActiveHead() {
        return activeHead;
    }

    public Image getBodyImg() {
        return bodyImg;
    }

    public Shape getHeadHitbox() {
        return headHitbox;
    }
    
    
    //-------======Getters and Setters END======-------
    
    public void initBody(){ 
        int i, x, y;
        for (i=length; i>=0; i--){
            x = headPosition.getX()-(i*speed);
            y = headPosition.getY();
            body.add(new Position(x, y));
        }
    }
 
    public void move(int direction){ //method receieves direction from main class and moves the snake
            this.setDirection(direction);
            body.remove(0);
            body.add(headPosition);//adds the Position of the head to the first index of body
            headHitbox.setX(headPosition.getX()+10);    //move snake hitbox
            headHitbox.setY(headPosition.getY()+10);
            switch (direction){
            case 1:
                this.setHeadPosition(new Position(headPosition.getX(), headPosition.getY()-speed));
                activeHead = snakeUp;
                break;
                 
            case 2:
                this.setHeadPosition(new Position(headPosition.getX()+speed, headPosition.getY()));
                activeHead = snakeRight;
                break;
                
            case 3:
                this.setHeadPosition(new Position(headPosition.getX(), headPosition.getY()+speed));
                activeHead = snakeDown;
                break;
            case 4:
                this.setHeadPosition(new Position(headPosition.getX()-speed, headPosition.getY()));
                activeHead = snakeLeft;
                break;    
            }    
    }
    
    public void eatFood (Food food){ //method recieves food object and increases its length by foodvalue
        body.add(0,body.get(length)); //adds the one tile to the tail
        length++;
    }
    
    public void addMoney(int money){
        this.money += money;
    }
    //for collision actions
    public void increaseHungerLength( Meter hungerbar, Food food){
        hungerbar.setWidth(hungerbar.getWidth()+ food.getFoodVal());
   }

    public void increaseRentLength( Meter rentbar, PayRent payRent){
          rentbar.setWidth(rentbar.getWidth()+ payRent.getPayRent());
   }
    //end of collision actions 
    
    //After it hits an object
    public void eatFood (Food food, Meter hungerbar){ //method recieves food object and increases its length by foodvalue
        if(money > food.getFoodCost()){
            body.add(0,body.get(length)); //adds the one tile to the tail
            length++;
            increaseHungerLength(hungerbar, food);
            money -= food.getFoodCost();
        }
    }
    
     public void eatDiet (Diet diet){ //method recieves diet object and decreases length by one at tail
        if (money > diet.getDietCost() && length > 1){
            body.remove(0);
            length--;
            money -= diet.getDietCost();
        }
    }
      public void eatWork (Work work){ //method recieves 
        money += work.getSal();
    }
    
    public void eatRent(PayRent payRent, Meter rentbar){ //method recieves 
        if (money > payRent.getPayRent()){
            increaseRentLength(rentbar, payRent);
            money -= 50;
        }
    }
    //end
    
    public void deductMoney(int deduct){
        money -= deduct;
    }
    
    public boolean checkSnake(Meter rentbar, Meter hungerbar){  //method checks if Snake is still alive (no collision with body, hungerbar not empty)
        return (checkBodyCollision(headPosition) || hungerbar.isEmpty() || rentbar.isEmpty());
    }
    
    public boolean checkBodyCollision(Position position){
        int i;
        boolean retval = false;
        
        for(i=0; i < length && retval == false; i++){ //Bery monke brain solution, will improve if I can
            retval = body.get(i).comparePosition(position);
        }
        return retval;
    }
}

