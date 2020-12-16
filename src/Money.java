
import org.newdawn.slick.Graphics;

public class Money {
    Graphics g;
    private int money;
    
    //Constructors
    public Money(int money) {
        this.money = money;
    }
  
    public void setInitMoney(Graphics g){
        g.drawString("Money ", 700, 20);
        g.drawString(" $ "+ this.getMoney(), 680, 50);
    }
    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
