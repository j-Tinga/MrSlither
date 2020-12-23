import java.awt.Font;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;

public class Score {
    private static int score = 0;
    Graphics g;
    Font awtFont = new Font("Courier", Font.BOLD, 24);
    TrueTypeFont font = new TrueTypeFont(awtFont, false);
    float delay = 30;//time it takes to increase


    //Constructors
    public Score() {

    }

    //Functions
    public void increaseScore(){
        if(delay > 0){
            delay--;
            if(delay == 0){
                score += 10;
                delay = 30;
            }
        }
    }
    
    public void scoreBoard(Graphics g){
        font.drawString(100, 20, "Score ", Color.black);
        font.drawString(95,50, score +" pts ", Color.black);
    }
    
    //Getters and Setters
    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        Score.score = score;
    }

}
