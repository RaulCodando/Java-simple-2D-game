package Principal;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Jogo extends Canvas implements Runnable{
    public static final int largura = 720, altura = 480;
    private Random r;
    private Thread thread;
    private boolean running = false;
    private Handler handler;
    private HUD hud;
    private Spawn spawner;

    public Jogo(){
        handler = new Handler();
        this.addKeyListener(new KeyInput(handler));
        new Tela(largura,altura,"Jogo",this);
        hud = new HUD();
        spawner = new Spawn(handler, hud);
        r = new Random();
        handler.addObject(new Jogador((largura/2)-20, (altura/2)-20, ID.Jogador, handler));
        handler.addObject(new InimigoBasico(r.nextInt(largura-50), r.nextInt(altura-50), ID.InimigoBasico, handler));
    }

    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop(){
        try{
            thread.join();
            running = false;
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void run(){
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000/amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                delta--;
            }
            if(running)
                render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick(){
        handler.tick();
        hud.tick();
        spawner.tick();
    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, largura, altura);

        handler.render(g);
        hud.render(g);

        g.dispose();
        bs.show();
    }

    public static int limitar(int var, int min, int max){
        if(var >= max)return var = max;
        if(var <= min)return var = min;
        else return var;
    } 

    public static void main(String args[]){
        new Jogo();
    }
}
