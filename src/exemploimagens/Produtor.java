package exemploimagens;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Produtor implements Runnable {
    private Buffer buffer;
    private Semaphore livre;
    private Semaphore ocupado;

    private Random r = new Random();

    public Produtor(Buffer buffer, Semaphore livre, Semaphore ocupado) {
        this.buffer = buffer;
        this.livre = livre;
        this.ocupado = ocupado;
    }

    @Override
    public void run() {
        for (int i=0;i<100;i++) {
            try {
                livre.acquire();
            } catch (InterruptedException ex) {
                Logger.getLogger(Produtor.class.getName()).log(Level.SEVERE, null, ex);
            }
            BufferedImage img = null;
            try {
                File f = new File("imagem_exemplo.jpg");
                img = ImageIO.read(f);
            } catch (IOException e) {
                System.out.println("Erro: " + e);
            }
            System.out.println("Adicionando Imagem " + i );
            buffer.insert(img);
            ocupado.release();


        }
    }
}
