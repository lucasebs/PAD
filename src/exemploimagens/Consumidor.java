package exemploimagens;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumidor implements  Runnable {
    private Buffer buffer;
    private Semaphore livre;
    private Semaphore ocupado;

    private Random r = new Random();

    public Consumidor(Buffer buffer, Semaphore livre, Semaphore ocupado) {
        this.buffer = buffer;
        this.livre = livre;
        this.ocupado = ocupado;
    }

    @Override
    public void run() {
        int cont = 0;

        ProcessadorImagens proc = new ProcessadorImagens();

        while (true) {
            try {
                ocupado.acquire();
            } catch (InterruptedException ex) {
                Logger.getLogger(Consumidor.class.getName()).log(Level.SEVERE, null, ex);
            }

            BufferedImage img = buffer.remove();
            livre.release();
            System.out.println("Imagem " + cont + " retirada do Buffer...");

            BufferedImage img_out = proc.brilho(img, r.nextInt(100));
            System.out.println("Imagem " + cont + " processada...");

            try {
                File f = new File("/test/imagem_saida"+cont+".jpg");
                ImageIO.write(img_out, "jpg", f);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Imagem " + cont + " salva...");

            cont++;
        }
    }
}
