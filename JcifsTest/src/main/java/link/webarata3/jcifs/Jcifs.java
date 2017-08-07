package link.webarata3.jcifs;

import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;

import java.net.MalformedURLException;

public class Jcifs {
    private final String server;
    private final NtlmPasswordAuthentication auth;

    public Jcifs(String server, String username, String password) {
        this.server = server;
        auth = new NtlmPasswordAuthentication("", username, password);
    }

    public SmbFile getSmbFile(String path) throws MalformedURLException {
        String url = "smb://" + server + "/public/old-ゴミ/鍵.txt";
        return new SmbFile(url, auth);
    }
}
