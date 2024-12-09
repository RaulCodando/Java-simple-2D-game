package Principal;

import java.util.Random;

public class Spawn{
    private Random r = new Random();

    private Handler handler;
    private HUD hud;
    private int scoreKeep = 0;

    public Spawn(Handler handler, HUD hud){
        this.handler = handler;
        this.hud = hud;
    }

    public void tick(){
        scoreKeep++;
        if(scoreKeep >= 500){
            scoreKeep = 0;
            hud.setLevel(hud.getLevel()+1);

            if(hud.getLevel() < 5 && hud.getLevel() > 1){
                handler.addObject(new InimigoBasico(r.nextInt(Jogo.largura-50), r.nextInt(Jogo.altura-50), ID.InimigoBasico, handler));
            }
            else if(hud.getLevel() == 5){
                int j = 0;
                for(int i = 0; i < handler.object.size(); i++){
                    GameObject tempObject = handler.object.get(i);

                    if(tempObject.id == ID.InimigoBasico){
                        if(j < 4){
                            handler.removeObject(tempObject);
                            i = 0;
                            j++;
                        }
                    }  
                }
                handler.addObject(new InimigoAgil(r.nextInt(Jogo.largura-50), r.nextInt(Jogo.altura-50), ID.InimigoAgil, handler));
            }
            else if(hud.getLevel() > 5 && hud.getLevel() < 9){
                handler.addObject(new InimigoAgil(r.nextInt(Jogo.largura-50), r.nextInt(Jogo.altura-50), ID.InimigoAgil, handler));
            }
            else if(hud.getLevel() == 9){
                handler.addObject(new InimigoBasico(r.nextInt(Jogo.largura-50), r.nextInt(Jogo.altura-50), ID.InimigoBasico, handler));
            }
        }
    }
}