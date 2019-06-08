import javax.swing.*;

class Floor extends GameObject {
    public static ImageIcon getIcon() {
        return icon;
    }

    public static void setIcon(ImageIcon icon) {
        Floor.icon = icon;
    }

    private static ImageIcon icon = new ImageIcon("src/Default/Ground.png");

    Floor(int x, int y, boolean bool, GameScreen gs) {
        super(x, y, bool, gs);
        image = icon.getImage();
    }
}