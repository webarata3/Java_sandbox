package link.webarata3.fx.oekaki;

public class LineWidthItem {
    private String iconPath;
    private int lineWidth;

    public LineWidthItem(String iconPath, int lineWidth) {
        this.iconPath = iconPath;
        this.lineWidth = lineWidth;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public int getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(int lineWidth) {
        this.lineWidth = lineWidth;
    }
}
