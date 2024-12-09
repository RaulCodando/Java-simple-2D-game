package Principal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class InimigoBasico extends GameObject{
    private Handler handler;

    public InimigoBasico(int x, int y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;

        velY = 5;
        velX = 5;
    }

    public Rectangle getBounds(){
        return new Rectangle(x,y,20,20);
    }

    public void tick(){
        x += velX;
        y += velY;

        if(y <= 0 || y >= Jogo.altura-20)velY *= -1;
        if(x <= 0 || x >= Jogo.largura-20)velX *= -1;

        handler.addObject(new Trail(x, y, ID.Trail, Color.yellow, 0.05, handler, 20, 20));
    }

    public void render(Graphics g){
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, 20, 20);
    }
}
