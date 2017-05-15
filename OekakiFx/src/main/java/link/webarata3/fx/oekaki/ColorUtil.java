package link.webarata3.fx.oekaki;

import javafx.scene.paint.Color;

public class ColorUtil {
    public static Color hsvToRgb(double h, double s, double v) {
        double max = v;
        double min = max - ((s / 255.0) * max);

        double r;
        double g;
        double b;

        if (h >= 0.0 && h <= 60.0) {
            r = max;
            g = (h / 60) * (max - min) + min;
            b = min;
        } else if (h >= 60.0 && h <= 120.0) {
            r = ((120 - h) / 60) * (max - min) + min;
            g = max;
            b = min;
        } else if (h >= 120.0 && h <= 180.0) {
            r = min;
            g = max;
            b = ((h - 120) / 60) * (max - min) + min;
        } else if (h >= 180.0 && h <= 240.0) {
            r = min;
            g = ((240 - h) / 60) * (max - min) + min;
            b = max;
        } else if (h >= 240.0 && h <= 300.0) {
            r = ((h - 240) / 60) * (max - min) + min;
            g = min;
            b = max;
        } else {
            r = max;
            g = min;
            b = ((360 - h) / 60) * (max - min) + min;
        }
        return Color.rgb((int) r, (int) g, (int) b);
    }
}
