import java.awt.*;

abstract class GameObject {
    private int x;
    private int y;

    private boolean isVisible;

    Image image;
    GameScreen gameScreen;

    GameObject(int x, int y, boolean bool, GameScreen gameScreen) {
        this.x = x;
        this.y = y;
        isVisible = bool;
        this.gameScreen = gameScreen;
    }

    boolean isVisible() {
        return isVisible;
    }

    void setVisible(boolean bool) {
        isVisible = bool;
    }

    Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    void setX(int x) {
        this.x = x;
    }

    void setY(int y) {
        this.y = y;
    }

}