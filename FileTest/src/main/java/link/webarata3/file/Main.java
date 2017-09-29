package link.webarata3.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String dir = scanner.nextLine();

        System.out.println(FileUtil.isAllFileInDir(dir));

        copy(new File(dir), new File("/Users/arata/temp/dest"));
    }

    private static void copy(File srcFile, File destFile) {
        try (FileChannel input = new FileInputStream(srcFile).getChannel();
             FileChannel output = new FileOutputStream(destFile).getChannel()) {
            output.transferFrom(input, 0, input.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
