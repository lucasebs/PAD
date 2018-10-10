package exemploimagens;

import java.awt.image.BufferedImage;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumidor implements  Runnable {
    private Buffer buffer;
    private Semaphore livre;
    private Semaphore ocupado;

    public Consumidor(Buffer buffer, Semaphore livre, Semaphore ocupado) {
        this.buffer = buffer;
        this.livre = livre;
        this.ocupado = ocupado;
    }

    @Override
    public void run() {

        ProcessadorImagens proc = new ProcessadorImagens();

        int cont = 0;
        while (true) {
            try {
                ocupado.acquire();
            } catch (InterruptedException ex) {
                Logger.getLogger(Consumidor.class.getName()).log(Level.SEVERE, null, ex);
            }
            BufferedImage img = buffer.remove();
            livre.release();
            System.out.println("Removida Imagem " + cont);


        }
    }
}
