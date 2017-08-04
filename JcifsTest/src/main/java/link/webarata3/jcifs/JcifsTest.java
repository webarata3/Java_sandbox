package link.webarata3.jcifs;

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

        String url = "smb://" + properties.getProperty("server") + "/public/old-ゴミ/鍵.txt";
        NtlmPasswordAuthentication auth =
            new NtlmPasswordAuthentication("",properties.getProperty("username"), properties.getProperty("password"));

        SmbFile smbFile = new SmbFile(url, auth);
        try (SmbFileInputStream sfis = new SmbFileInputStream(smbFile);
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
