/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exemploimagens;

import java.awt.image.BufferedImage;
import static java.awt.image.BufferedImage.TYPE_INT_RGB;
import java.io.File;
import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import javax.imageio.ImageIO;



/**
 *
 * @author ruangomes
 *
 */
public class Principal {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ArrayList<Buffer> buffers = new ArrayList<Buffer>();
        ArrayList<Semaphore> livres = new ArrayList<Semaphore>();
        ArrayList<Semaphore> ocupados = new ArrayList<Semaphore>();
        ArrayList<Consumidor> consumidores = new ArrayList<Consumidor>();
        ArrayList<Thread> threads = new ArrayList<Thread>();
        Integer quantidadeThreads = 3;


        for (int i=0;i<quantidadeThreads;i++) {
            Semaphore livre = new Semaphore(10);
            Semaphore ocupado = new Semaphore(0);
            Buffer buffer = new Buffer();
            Consumidor c = new Consumidor(buffer,livre,ocupado);
            Thread tc = new Thread(c);

            livres.add(livre);
            ocupados.add(ocupado);
            buffers.add(buffer);
            consumidores.add(c);
            threads.add(tc);
        }

        Produtor p = new Produtor(buffers,livres, ocupados);
        Thread tp = new Thread(p);
        tp.start();

        for (int i=0;i<quantidadeThreads;i++) {
            threads.get(i).start();
        }




//        ProcessadorImagens proc = new ProcessadorImagens();
//        try {
//            File f = new File("imagem_exemplo.jpg"); //image file path
//            System.out.println(f.getCanonicalPath());
//            BufferedImage img = ImageIO.read(f);
//
//            System.out.println("Imagem lida...");
//
//            BufferedImage img_out = proc.brilho(img, -100);
//            System.out.println("Imagem processada...");
//
//            File f2 = new File("imagem_saida.jpg");  //output file path
//            ImageIO.write(img_out, "jpg", f2);
//            System.out.println("Imagem salva...");
//
//            BufferedImage img_out_lim = new BufferedImage(img.getWidth(), img.getHeight(), TYPE_INT_RGB);
//
//            proc.brilho_intervalo(img, 100, 2000, img_out_lim);
//            System.out.println("Imagem 2 processada...");
//
//            File f3 = new File("imagem_saida_limite.jpg");  //output file path
//            ImageIO.write(img_out_lim, "jpg", f3);
//            System.out.println("Imagem 2 salva...");
//
//        } catch (IOException e) {
//            System.out.println("Erro: " + e);
//        }
    }
}
