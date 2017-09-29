package link.webarata3.file;

import java.io.File;
import java.util.Arrays;

public class FileUtil {
    public static boolean isAllFileInDir(String dirName) {
        File dir = new File(dirName);

        if (!dir.exists()) return false;
        if (!dir.isDirectory()) return false;

        File[] files = dir.listFiles();
        if (files == null) return true;

        return Arrays.stream(files).noneMatch(File::isDirectory);
    }
}
