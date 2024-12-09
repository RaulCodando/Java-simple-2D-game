package Principal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class InimigoAgil extends GameObject{
    private Handler handler;

    public InimigoAgil(int x, int y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;

        velY = 15;
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

        handler.addObject(new Trail(x, y, ID.Trail, new Color(100,0,100), 0.1, handler, 20, 20));
    }

    public void render(Graphics g){
        g.setColor(new Color(100,0,100));
        g.fillRect(x, y, 20, 20);
    }
}
