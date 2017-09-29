package link.webarata3.jcifs.test;

import link.webarata3.jcifs.Jcifs;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class Test2 {
    public static void main(String[] args) {
        Properties properties = new Properties();
        URL propertyUrl = ClassLoader.getSystemClassLoader().getResource("jcifs.properties");
        try {
            properties.load(propertyUrl.openStream());
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Jcifs jcifs = new Jcifs(
            properties.getProperty("server"),
            properties.getProperty("username"),
            properties.getProperty("password"));


        Thread thread = new Thread(new CopyThread(jcifs));
        thread.start();
    }
}

class CopyThread implements Runnable {
    private Jcifs jcifs;

    public CopyThread(Jcifs jcifs) {
        this.jcifs = jcifs;
    }

    public void run() {
        System.out.println("start");
        jcifs.coppyLocalFileToServer("/Users/arata/Desktop/pic20160401/test.pdf", "/public/test.pdf");
        System.out.println("end");
    }
}
