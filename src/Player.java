import javax.swing.*;
import java.util.ArrayList;

class Player extends GameObject implements Movable {

    static private ImageIcon iconU = new ImageIcon("src/Default/RobotU.png");
    static private ImageIcon iconR = new ImageIcon("src/Default/RobotR.png");
    static private ImageIcon iconD = new ImageIcon( "src/Default/RobotD.png");
    static private ImageIcon iconL = new ImageIcon("src/Default/RobotL.png");

    private ArrayList<Integer> Xs;
    private ArrayList<Integer> Ys;
    private boolean hasMoved = false;


    Player(int x, int y, GameScreen gameScreen) {
        super(x, y, true, gameScreen);
        image = iconU.getImage();

        Xs = new ArrayList<>();
        Xs.add(x);
        Ys = new ArrayList<>();
        Ys.add(y);
    }

    static ImageIcon getIconU() {
        return iconU;
    }

    static void setIconU(ImageIcon icon) {
        Player.iconU = icon;
    }

    static ImageIcon getIconR() {
        return iconR;
    }

    static void setIconR(ImageIcon icon) {
        Player.iconR = icon;
    }

    static ImageIcon getIconD() {
        return iconD;
    }

    static void setIconD(ImageIcon icon) {
        Player.iconD = icon;
    }

    static ImageIcon getIconL() {
        return iconL;
    }

    static void setIconL(ImageIcon icon) {
        Player.iconL = icon;
    }

    @Override
    public void move(GameScreen.Directions dir) {
        int x = this.getX();
        int y = this.getY();
        if (dir == GameScreen.Directions.DOWN) {
            setY(getY() + GameScreen.SIDE);
            setImage(iconD.getImage());
        }
        if (dir == GameScreen.Directions.UP) {
            setY(getY() - GameScreen.SIDE);
            setImage(iconU.getImage());
        }
        if (dir == GameScreen.Directions.LEFT) {
            setX(getX() - GameScreen.SIDE);
            setImage(iconL.getImage());
        }
        if (dir == GameScreen.Directions.RIGHT) {
            setX(getX() + GameScreen.SIDE);
            setImage(iconR.getImage());
        }
        hasMoved = true;
        checkCollision(x, y, dir);
    }

    private void checkCollision(int x, int y, GameScreen.Directions dir) {
        for (GameObject object : gameScreen.objects) {

            if (this.getX() == object.getX() && this.getY() == object.getY()) {
                if (object instanceof Wall) {
                    setX(x);
                    setY(y);
                    hasMoved = false;
                    break;
                } else if (object instanceof Floor || object instanceof GoalPoint)
                    object.setVisible(true);

                else if (object instanceof Box) {
                    Box box = (Box) object;
                    if (!box.moveBox(dir)) {
                        setX(x);
                        setY(y);
                        hasMoved = false;
                    }
                }
            }
        }
        if (hasMoved) {
            Xs.add(getX());
            Ys.add(getY());
            gameScreen.setMoveCount(gameScreen.getMoveCount() + 1);
            hasMoved = false;
            for (GameObject object : gameScreen.objects) {
                if (object instanceof Box) {
                    ((Box) object).XsAdd(object.getX());
                    ((Box) object).YsAdd(object.getY());
                }
            }
        }
    }

    @Override
    public void stepBack() {
        setX(Xs.get(Xs.size() - 2));
        setY(Ys.get(Ys.size() - 2));
        Xs.remove(Xs.size() - 1);
        Ys.remove(Ys.size() - 1);
        gameScreen.setMoveCount(gameScreen.getMoveCount() - 1);
    }

    boolean isAbleToStepBack() {
        return Xs.size() > 1;
    }
}