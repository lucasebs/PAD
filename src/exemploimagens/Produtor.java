package exemploimagens;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Produtor implements Runnable {
    private ArrayList<Buffer> buffers;
    private ArrayList<Semaphore> livres;
    private ArrayList<Semaphore> ocupados;
    private Integer consumidores;
    private Integer quantidadeImagens;
//    int[] qtds;
//    private Buffer buffer;
//    private Semaphore livre;
//    private Semaphore ocupado;

    private Random r = new Random();

    public Produtor(ArrayList<Buffer> buffers, ArrayList<Semaphore> livres, ArrayList<Semaphore> ocupados) {
        System.out.println("Produtor criado...");
        this.buffers = buffers;
        this.livres = livres;
        this.ocupados = ocupados;
        this.consumidores = buffers.size();
    }

    @Override
    public void run() {
//        this.qtds = new int[this.consumidores];

//        for (int i=0;i<this.consumidores;i++ ) {
//            this.qtds[i] = r.nextInt(8);
//        };

        System.out.println("Produtor rodando...");

        int cont = 0;
        this.quantidadeImagens = 50;
        int i = 0;

        while (true) {
            if (cont >= this.quantidadeImagens) {
                break;
            }

            Buffer buffer = buffers.get(i);

            try {
                livres.get(i).acquire();
                System.out.println("Produtor ocupa...");

            } catch (InterruptedException ex) {
                Logger.getLogger(Produtor.class.getName()).log(Level.SEVERE, null, ex);
            }
            Image img = null;
            System.out.println(cont-(this.quantidadeImagens/2));
            try {
                File f = new File("imagem_exemplo" + ".jpg");
                img = new Image(ImageIO.read(f),"imagem_exemplo_processada_",cont-(this.quantidadeImagens/2));
            } catch (IOException e) {
                System.out.println("Erro: " + e);
            }
            System.out.println("Adicionando Imagem " + i );
            buffer.insert(img);
            ocupados.get(i).release();
            System.out.println("Produtor libera...");

            cont++;
            i = cont % this.consumidores;
        }

        for (int h=0; h<consumidores;h++){
            Image img = new Image();
            try {
                livres.get(h).acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Buffer buffer = buffers.get(h);
            buffer.insert(img);
            ocupados.get(h).release();
        }
    }
}
