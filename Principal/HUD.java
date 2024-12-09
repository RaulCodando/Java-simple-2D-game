package Principal;

import java.awt.Color;
import java.awt.Graphics;

public class HUD{
    public static int HEALTH = 100;
    private int score = 0;
    private int level = 1;

    public void tick(){
        HEALTH = Jogo.limitar(HEALTH, 0, 100);
        score++;
    }

    public void render(Graphics g){
        g.setColor(Color.red);
        g.fillRect(15, 15, 200, 20);
        g.setColor(Color.green);
        g.fillRect(15, 15, HEALTH*2, 20);
        g.setColor(Color.white);
        g.drawRect(15, 15, 200, 20);
        g.drawString(HEALTH + "/100", 90, 30);
        g.drawString("Pontos: " + score, 15, 56);
        g.drawString("Nível: " + level, 15, 72);
    }

    public void setScore(int score){
        this.score = score;
    }

    public void setLevel(int level){
        this.level = level;
    }

    public int getScore(){
        return score;
    }

    public int getLevel(){
        return level;
    }
}
