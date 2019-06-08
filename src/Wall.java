import javax.swing.*;

class Wall extends GameObject
{
    public static ImageIcon getIcon() {
        return icon;
    }

    public static void setIcon(ImageIcon icon) {
        Wall.icon = icon;
    }

    private static ImageIcon icon = new ImageIcon("src/Default/Wall.png");

    Wall(int x, int y, GameScreen gs) {
        super(x, y, true, gs);
        image = icon.getImage();
    }
}