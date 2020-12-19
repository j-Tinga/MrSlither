
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Score {
    private static int score = 0;
    Graphics g;
    float delay = 30;//time it takes to increase


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
        g.setColor(Color.black);
        g.drawString("Score ", 100, 20);
        g.drawString(this.getScore() +" pts ", 95, 50);
    }
    
    //Getters and Setters
    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        Score.score = score;
    }

}
