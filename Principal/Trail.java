package Principal;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Trail extends GameObject{
    private float alpha = 1;
    private Handler handler;
    private Color color;
    private double vida;
    int largura;
    int altura;

    Trail(int x, int y, ID id, Color color, double vida, Handler handler, int largura, int altura){
        super(x, y, id);
        this.color = color;
        this.handler = handler;
        this.vida = vida;
        this.altura = altura;
        this.largura = largura;
    }

    public void tick(){
        if(alpha > vida){
            alpha -= vida - 0.0001f;
        }else handler.removeObject(this);
    }

    public void render(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(transparente(alpha));
        g.setColor(color);
        g.fillRect(x, y, largura, altura);
        g2d.setComposite(transparente(1));
    }

    private AlphaComposite transparente(float alpha){
        int type = AlphaComposite.SRC_OVER;
        return(AlphaComposite.getInstance(type,alpha));
    }

    public Rectangle getBounds(){
        return null;
    }
}
