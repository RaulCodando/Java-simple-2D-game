package Principal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Jogador extends GameObject{
    Handler handler;

    public Jogador(int x, int y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;
    }

    public Rectangle getBounds(){
        return new Rectangle(x,y,40,40);
    }

    public void tick(){
        x += velX;
        y += velY;

        x = Jogo.limitar(x, 0, Jogo.largura-55);
        y = Jogo.limitar(y, 0, Jogo.altura-79);

        handler.addObject(new Trail(x, y, ID.Trail, Color.blue, 0.1, handler, 40, 40));

        collision();
    }

    public void collision(){
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ID.InimigoBasico){
                if(getBounds().intersects(tempObject.getBounds())){
                    HUD.HEALTH -= 2;
                }
            }
            else if(tempObject.getId() == ID.InimigoAgil){
                if(getBounds().intersects(tempObject.getBounds())){
                    HUD.HEALTH -= 1;
                }
            }
        }
    }

    public void render(Graphics g){
        g.setColor(Color.blue);
        g.fillRect(x, y, 40, 40);
    }
}
