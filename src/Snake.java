import java.util.ArrayList;


public class Snake {
    private int direction; //Direction goes Clockwise 1=UP, 2=RIGHT, 3=DOWN, 4=LEFT
    private Position headPosition;
    private ArrayList<Position> body;   //the body of the snake is a Queue arraylist that contains the Positions of the body of the snake.
    private int length; //length pixels = legnth + speed of snake
    private int money;
    private int hunger;
    private int speed;  //Reminder: changing speed will change how long the snake looks
    
    public Snake() {  //Default Snake
        direction = 1;
        headPosition = new Position(300,250);
        length = 32;  
        money = 100;
        hunger = 100;
        speed = 3;
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

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    //-------======Getters and Setters END======-------
    
    public void initBody(){
        int i;
        
        for (i=length; i>0; i--){
            body.add(new Position((this.getHeadPosition().getX()-(i*speed)), this.getHeadPosition().getY()));
        }
    }
    
    public void move(int direction){
        body.remove(0);  //removes the tail of the snake (last index of body)
        body.add(headPosition); //adds the Position of the head to the first index of body
        
        this.setDirection(direction);
        switch (direction){
            case 1:
                this.setHeadPosition(new Position((this.getHeadPosition().getX())%900, (this.getHeadPosition().getY()-speed)%600));
                break;
            case 2:
                this.setHeadPosition(new Position((this.getHeadPosition().getX()+speed)%900, (this.getHeadPosition().getY())%600));
                break;
            case 3:
                this.setHeadPosition(new Position((this.getHeadPosition().getX())%900, (this.getHeadPosition().getY()+speed)%600));
                break;
            case 4:
                this.setHeadPosition(new Position((this.getHeadPosition().getX()-speed)%900, (this.getHeadPosition().getY())%600));
                break;    
        }
        
    }
}
