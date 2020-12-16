
import org.newdawn.slick.Graphics;

public class Score {
    private int score = 0;
    Graphics g;
    float delay = 30;//time it takes to increase (constant rate)


    //Constructors
    public Score() {

    }

    //Functions
    public void increaseScore(){
      
        if(delay > 0){
            delay--;
            if(delay == 0){
                this.score += 100;
                delay = 30;
            }
        } 

    }
    
    public void scoreBoard(Graphics g){
        g.drawString("Score ", 210, 20);
        g.drawString(this.getScore() +" pts ", 210, 35);
    }
    
    //Getters and Setters
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}
