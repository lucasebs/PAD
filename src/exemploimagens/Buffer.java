package exemploimagens;

import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Buffer {
    private LinkedList<BufferedImage> buffer;

    public Buffer(){
        this.buffer = new LinkedList<BufferedImage>();
    }

    public synchronized void insert(BufferedImage e) {
        buffer.add(e);
    }

    public synchronized BufferedImage remove() {
        BufferedImage v = buffer.remove();
        return v;
    }
}
