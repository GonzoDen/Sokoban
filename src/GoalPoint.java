import javax.swing.*;

class GoalPoint extends GameObject {
    public static ImageIcon getIcon() {
        return icon;
    }

    public static void setIcon(ImageIcon icon) {
        GoalPoint.icon = icon;
    }

    private static ImageIcon icon = new ImageIcon("src/Default/Goal.png");

    GoalPoint(int x, int y, boolean bool, GameScreen gs) {
        super(x, y, bool,gs);
        image = icon.getImage();
    }
}