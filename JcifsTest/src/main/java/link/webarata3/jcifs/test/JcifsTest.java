package link.webarata3.jcifs.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import jcifs.Config;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;
import link.webarata3.jcifs.Jcifs;

public class JcifsTest {
    public static void main(String[] args) throws MalformedURLException {
        Properties properties = new Properties();
        URL propertyUrl = ClassLoader.getSystemClassLoader().getResource("jcifs.properties");
        try {
            properties.load(propertyUrl.openStream());
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

//        Config.setProperties(properties);
        Jcifs jcifs = new Jcifs(
            properties.getProperty("server"),
            properties.getProperty("username"),
            properties.getProperty("password"));

        try (SmbFileInputStream sfis = new SmbFileInputStream(jcifs.getSmbFile("/public/old-ゴミ/鍵.txt"));
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
