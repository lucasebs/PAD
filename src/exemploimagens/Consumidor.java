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
    private Image img;

    public Consumidor(Buffer buffer, Semaphore livre, Semaphore ocupado) {
        System.out.println("Consumidor criado...");
        this.buffer = buffer;
        this.livre = livre;
        this.ocupado = ocupado;
    }

    @Override
    public void run() {
        System.out.println("Consumidor rodando...");

        ProcessadorImagens proc = new ProcessadorImagens();

        while(true) {


            try {
                System.out.println("Consumidor - ocupa...");
                ocupado.acquire();
            } catch (InterruptedException ex) {
                Logger.getLogger(Consumidor.class.getName()).log(Level.SEVERE, null, ex);
            }

            this.img = buffer.remove();
            if (this.img.isEnd()){
                break;
            }

            livre.release();
            System.out.println("Consumidor - libera...");
            System.out.println("Imagem " + this.img.getFile_name() + this.img.getBrilho() +  " retirada do Buffer...");

            BufferedImage img_out = proc.brilho(this.img.getImg(), this.img.getBrilho());
            System.out.println("Imagem " + this.img.getFile_name() + this.img.getBrilho() +  " processada...");

            try {
//                File f = new File("test/imagem_saida"+cont+".jpg");
                File f = new File("test/" + this.img.getFile_name() + this.img.getBrilho() + ".jpg");
                ImageIO.write(img_out, "jpg", f);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Imagem " + this.img.getFile_name() + this.img.getBrilho() +  " salva...");
        }
    }
}
