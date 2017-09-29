package link.webarata3.jcifs;

import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;

public class Jcifs {
    private final String server;
    private final NtlmPasswordAuthentication auth;

    public Jcifs(String server, String username, String password) {
        this.server = server;
        auth = new NtlmPasswordAuthentication("", username, password);
    }

    private SmbFile getSmbFile(String path) throws MalformedURLException {
        String url = "smb://" + server + path;
        return new SmbFile(url, auth);
    }

    public void coppyLocalFileToServer(String localPath, String serverPath) {
        SmbFile smbFile;
        try {
            smbFile = getSmbFile(serverPath);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        try (InputStream is = new FileInputStream(localPath);
             OutputStream os = smbFile.getOutputStream()) {
            final byte[] buffer = new byte[8192];
            int count = 0;
            int n = 0;
            while ((n = is.read(buffer)) != -1) {
                System.out.println(count);
                os.write(buffer, 0, n);
                count += n;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
