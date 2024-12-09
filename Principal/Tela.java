package Principal;

import java.awt.Canvas;
import javax.swing.JFrame;

public class Tela extends Canvas{
    public Tela(int largura, int altura, String titulo, Jogo jogo){
        JFrame tela = new JFrame(titulo);

        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setVisible(true);
        tela.setSize(largura, altura);
        tela.setResizable(false);
        tela.setLocationRelativeTo(null);
        tela.add(jogo);
        jogo.start();
    }
}
