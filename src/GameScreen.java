import java.util.ArrayList;

class GameScreen {
    private Levels levels;
    private int currentLevel = 0;

    private int x[];
    private int y[];

    private char[][] tiles;
    ArrayList<GameObject> objects = new ArrayList<>();
    private ArrayList<Movable> movableObjects = new ArrayList<>();
    static int SIDE;
    private int moveCount = 0;

    Player player;
    private int numberOfBoxes = 0;
    private boolean isCompleted = false;

    enum Directions {
        UP,
        RIGHT,
        DOWN,
        LEFT
    }


    GameScreen(Levels levels) {
        this.levels = levels;
        x = new int[30];
        y = new int[30];
        for (int i = 0; i < 30; i++) {
            x[i] = i * SIDE;
            y[i] = i * SIDE;
        }

        buildLevel();
    }

    private void buildLevel() {
        readLevel();

        for (int i = 0; i < tiles.length; i++) {
            boolean isWall = false;
            for (int j = 0; j < tiles[i].length; j++) {

                switch (tiles[i][j]) {
                    case ' ':
                        if (isWall)
                            objects.add(new Floor(x[j], y[i], true, this));
                        break;
                    case '#':
                        objects.add(new Wall(x[j], y[i], this));
                        isWall = true;
                        break;
                    case '$':
                        numberOfBoxes++;
                        Box box = new Box(x[j], y[i], this);
                        objects.add(box);
                        movableObjects.add(box);
                        objects.add(new Floor(x[j], y[i], false, this));
                        break;
                    case '*':
                        numberOfBoxes++;
                        Box box1 = new Box(x[j], y[i], this);
                        box1.isOnCross();
                        objects.add(box1);
                        movableObjects.add(box1);
                        objects.add(new GoalPoint(x[j], y[i], false, this));
                        break;
                    case '@':
                        player = new Player(x[j], y[i], this);
                        movableObjects.add(player);
                        objects.add(new Floor(x[j], y[i], true, this));
                        break;
                    case '.':
                        objects.add(new GoalPoint(x[j], y[i], true, this));
                        break;
                    default:
                        throw new IllegalArgumentException();
                }
            }
        }
    }

    void readLevel() {
        if (currentLevel >= levels.levels.size())
            currentLevel = 0;
        else if (currentLevel < 0) {
            currentLevel = levels.levels.size() - 1;
        }
        ArrayList<String> strings = levels.levels.get(currentLevel);

        int maxLength = 0;
        for (String string : strings) {
            if (string.length() > maxLength)
                maxLength = string.length();
        }
        tiles = new char[strings.size()][maxLength];
        for (int i = 0; i < strings.size(); i++)
            tiles[i] = strings.get(i).toCharArray();
    }

    boolean checkWin() {
        int counter = 0;
        for (GameObject object : objects) {
            for (GameObject object1 : objects) {
                if (object instanceof Box && object1 instanceof GoalPoint) {
                    if (object.getX() == object1.getX() && object.getY() == object1.getY())
                        counter++;
                }
            }
        }
        return counter == numberOfBoxes;
    }

    void movePlayer(Directions dir) {
        player.move(dir);
    }

    void stepBack() {
        if (player.isAbleToStepBack()) {
            for (Movable object : movableObjects)
                object.stepBack();
        }
    }

    int getMoveCount() {
        return moveCount;
    }

    void setMoveCount(int i) {
        moveCount = i;
    }

    int getCurrentLevel() {
        return currentLevel;
    }

    void setCurrentLevel(int i) {
        currentLevel = i;
    }

    void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    boolean isCompleted(){
        return isCompleted;
    }
    void reset() {
        objects.clear();
        movableObjects.clear();
        moveCount = 0;
        numberOfBoxes = 0;
        setCompleted(false);
        buildLevel();
    }
}