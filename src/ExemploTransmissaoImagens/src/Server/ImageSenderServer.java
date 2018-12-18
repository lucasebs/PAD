package Server;

import java.io.*;

public class ImageSenderServer {



//
//    try {
//
//        int i = 2120;
//        String file_name = "video/3840_RIGHT_0" + i;
//        System.out.println("Enviando " + file_name);
//        File f = new File(file_name + ".jpg");
//
//        //criando um input stream para ler os bytes do arquivo (não decodifica a imagem)
//        InputStream in = new FileInputStream(f);
//
//        long length = f.length();
//        byte[] bytes = new byte[16 * 1024]; //criando o array de bytes usado para enviar os dados da imgagem
//
//        //enviando a quantidade de bytes do arquivo
//        output.writeLong(length);
//
//        //enviadno nome do arquivo
//        output.writeUTF(file_name);
//
//        int brilho = 100;
//        //enviando o brilho a ser aplicado
//        output.writeInt(brilho);
//
//        //enviando o arquivo pela rede
//        int count;
//        //enquanto houver bytes para enviar, obtém do arquivo e manda pela rede
//        while ((count = in.read(bytes, 0, bytes.length)) > 0) { //count recebe a qtd de bytes lidos do arquivo para serem enviados
//            //  System.out.println("Enviou " + count + " bytes");
//            try {
//                output.write(bytes, 0, count); //envia count bytes pela rede
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//
//    } catch (
//    FileNotFoundException e) {
//        e.printStackTrace();
//    } catch (IOException e) {
//        e.printStackTrace();
//    }
}
