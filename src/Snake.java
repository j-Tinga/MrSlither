import java.util.ArrayList;

public class Snake {
    private int direction; //Direction goes Clockwise 1=UP, 2=RIGHT, 3=DOWN, 4=LEFT
    private Position headPosition;
    private ArrayList<Position> body;   //the body of the snake is a Queue arraylist that contains the Positions of the body of the snake.
    private int length; //length pixels = legnth + speed of snake
    private int money;
    private int hunger;
    private int speed;  //Reminder: changing speed will change how long the snake looks
    int buffer;
    
    public Snake() {  //Default Snake
        direction = 2;
        headPosition = new Position(320,256);
        length = 3;  
        money = 100;
        hunger = 100;
        speed = 32;   //Should be equal to the pixel dimensions of the snake
        body = new ArrayList<>();
    }

    public Snake(Position headPosition) { //Default Snake but changes starting Position
        this.headPosition = headPosition;
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

    public int getHunger() {
        return hunger;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    //-------======Getters and Setters END======-------
    
    public void initBody(){
        int i, x, y;
        for (i=length; i>=0; i--){
            x = this.getHeadPosition().getX()-(i*speed);
            y = this.getHeadPosition().getY();
            body.add(new Position(x, y));
        }
    }
 
    public void move(int direction){ //method receieves direction from main class and moves the snake
            this.setDirection(direction);
            switch (direction){
            case 1:
                this.setHeadPosition(new Position(headPosition.getX()%900, (headPosition.getY()-speed)%600));
                break;
            case 2:
                this.setHeadPosition(new Position((headPosition.getX()+speed)%900, headPosition.getY()%600));
                break;
            case 3:
                this.setHeadPosition(new Position(headPosition.getX()%900, headPosition.getY()+speed%600));
                break;
            case 4:
                this.setHeadPosition(new Position((headPosition.getX()-speed)%900, headPosition.getY()%600));
                break;    
            }      
            body.remove(0);
            body.add(headPosition);//adds the Position of the head to the first index of body
    }
    
    public void eatFood (int foodValue){ //method recieves foodValue and increases its length by foodvalue
        ++length;
        body.add(headPosition); //adds the Position of the head to the first index of body
    }
    
    public boolean checkBodyCollision(){    //Bery monke brain solution, will improve if I can
        int i, x, y;
        boolean retval= false;
        
        for(i=0; i < length && retval == false; i++){
            x = body.get(i).getX();
            y = body.get(i).getY();
            if(headPosition.getX() == x && headPosition.getY() == y){
                retval = true;
            }
        }
        return retval;
    }
}
