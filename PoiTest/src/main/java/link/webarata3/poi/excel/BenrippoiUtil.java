package link.webarata3.poi.excel;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Optional;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class BenrippoiUtil {
    public static Optional<Workbook> open(String fileName) {
        try {
            InputStream is = Files.newInputStream(Paths.get(fileName));
            return open(is);
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    public static Optional<Workbook> open(InputStream is) {
        Objects.requireNonNull(is, "InputStreamにnullは許可されていません");
        try (Workbook wb = WorkbookFactory.create(is)) {
            return Optional.of(wb);
        } catch (IOException | EncryptedDocumentException | InvalidFormatException e) {
            return Optional.empty();
        }
    }

    public static String cellIndexToCellName(int x, int y) {
        String cellName = dec26(x, 0);
        return cellName + (y + 1);
    }

    private static String dec26(int num, int first) {
        if (num > 25) {
            return dec26(num / 26, 1) + (char) ('A' + (num - first) % 26);
        }
        return String.valueOf((char) ('A' + (num - first) % 26));
    }
}
