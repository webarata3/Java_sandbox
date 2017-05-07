package link.webarata3.jcifs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Properties;

import jcifs.Config;
import jcifs.smb.SmbFileInputStream;

public class JcifsTest {
    public static void main(String[] args) {
        Properties properties = new Properties();
        URL url = ClassLoader.getSystemClassLoader().getResource("jcifs.properties");
        try {
            properties.load(url.openStream());
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Config.setProperties(properties);

        try (SmbFileInputStream sfis = new SmbFileInputStream("smb://192.168.1.11/public/鍵.txt");
                BufferedReader br = new BufferedReader(new InputStreamReader(sfis))) {

            String line = null;

            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
