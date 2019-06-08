import javax.swing.*;
import java.util.ArrayList;

class Box extends GameObject implements Movable {

    private static ImageIcon iconDefault = new ImageIcon("src/Default/BoxBlue.png");
    private static ImageIcon iconWin = new ImageIcon("src/Default/BoxRed.png");
    private ArrayList<Integer> Xs;
    private ArrayList<Integer> Ys;

    Box(int x, int y, GameScreen gs) {
        super(x, y, true, gs);
        image = iconDefault.getImage();

        Xs = new ArrayList<>();
        Xs.add(x);
        Ys = new ArrayList<>();
        Ys.add(y);
    }

    public static ImageIcon getIconDefault() {
        return iconDefault;
    }

    public static void setIconDefault(ImageIcon iconDefault) {
        Box.iconDefault = iconDefault;
    }

    public static ImageIcon getIconWin() {
        return iconWin;
    }

    public static void setIconWin(ImageIcon iconWin) {
        Box.iconWin = iconWin;
    }

    boolean moveBox(GameScreen.Directions dir) {
        int x = this.getX();
        int y = this.getY();
        move(dir);
        for (GameObject object : gameScreen.objects) {
            if (this.getX() == object.getX() && this.getY() == object.getY()) {
                if (object instanceof Wall || (object instanceof Box) && object != this) {
                    setX(x);
                    setY(y);
                    return false;
                }
                if (object instanceof Floor || object instanceof GoalPoint) {
                    object.setVisible(false);
                    if (object instanceof GoalPoint)
                        image = iconWin.getImage();
                    else {
                        if (image == iconWin.getImage())
                            image = iconDefault.getImage();
                    }
                }

            }

        }
        return true;
    }

    @Override
    public void move(GameScreen.Directions dir) {
        if (dir == GameScreen.Directions.DOWN)
            setY(getY() + GameScreen.SIDE);
        if (dir == GameScreen.Directions.UP)
            setY(getY() - GameScreen.SIDE);
        if (dir == GameScreen.Directions.LEFT)
            setX(getX() - GameScreen.SIDE);
        if (dir == GameScreen.Directions.RIGHT)
            setX(getX() + GameScreen.SIDE);

    }

    @Override
    public void stepBack() {
        for (GameObject object : gameScreen.objects) {
            if (this.getX() == object.getX() && this.getY() == object.getY() && object != this)
                object.setVisible(true);
        }
        setX(Xs.get(Xs.size() - 2));
        setY(Ys.get(Ys.size() - 2));
        Xs.remove(Xs.size() - 1);
        Ys.remove(Ys.size() - 1);
        for (GameObject object : gameScreen.objects) {
            if (this.getX() == object.getX() && this.getY() == object.getY() && object != this) {
                object.setVisible(false);
                if (object instanceof GoalPoint && image == iconDefault.getImage())
                    image = iconWin.getImage();
                else if (object instanceof Floor && image == iconWin.getImage())
                    image = iconDefault.getImage();
            }
        }
    }

    void isOnCross(){
        image = iconWin.getImage();
    }

    void XsAdd(int x){
        Xs.add(x);
    }

    void YsAdd(int y){
        Ys.add(y);
    }
}