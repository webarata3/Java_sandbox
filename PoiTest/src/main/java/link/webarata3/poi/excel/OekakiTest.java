package link.webarata3.poi.excel;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;

public class OekakiTest {
    private static final int WIDTH = 100;
    private static final int height = WIDTH;
    private static final float ROW_HEIGHT = 500f / WIDTH;
    private static final int COLUMN_WIDTH = (int) (ROW_HEIGHT * 41f);

    public static void main(String[] args) throws Exception {
        long start = System.nanoTime();

        BufferedImage image = ImageIO.read(new File("src/main/resources/100x100.png"));

        try (SXSSFWorkbook wb = new SXSSFWorkbook(100)) {
            Sheet sheet = wb.createSheet("sheet1");
            sheet.setDisplayGridlines(false);
            for (int y = 0; y < height; y++) {
                Row row = sheet.createRow(y);
                row.setHeightInPoints(ROW_HEIGHT);
                for (int x = 0; x < WIDTH; x++) {
                    if (y == 0) {
                        sheet.setColumnWidth(x, COLUMN_WIDTH);
                    }
                    Cell cell = row.createCell(x);

                    int argb = image.getRGB(x, y);
                    // int a = (argb >>> 24) & 0xff;
                    int r = (argb >>> 16) & 0xff;
                    int g = (argb >>> 8) & 0xff;
                    int b = argb & 0xff;

                    XSSFCellStyle style = (XSSFCellStyle) wb.createCellStyle();
                    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                    style.setFillForegroundColor(new XSSFColor(new Color(r, g, b, 255)));
                    cell.setCellStyle(style);
                }
            }

            try (FileOutputStream fos = new FileOutputStream("src/test/resources/sxssf.xlsx")) {
                wb.write(fos);
            }
        }
        long end = System.nanoTime();
        System.out.println("Time:" + (end - start) / 1000000f + "ms");

    }
}
