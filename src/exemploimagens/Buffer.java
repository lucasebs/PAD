package exemploimagens;

import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Buffer {
    private LinkedList<Image> buffer;

    public Buffer(){
        this.buffer = new LinkedList<Image>();
    }

    public synchronized void insert(Image e) {
        buffer.add(e);
    }

    public synchronized Image remove() {
        Image v = buffer.remove();
        return v;
    }
}
