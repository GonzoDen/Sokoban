import javax.swing.*;
import java.awt.*;

public class DrawingPanel extends JPanel {
    private GameScreen gameScreen;

    DrawingPanel(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0,0,getWidth(),getHeight());
        int SIDE = GameScreen.SIDE;
        for (int i = 0; i < gameScreen.objects.size(); i++) {
            GameObject object = gameScreen.objects.get(i);
            if (object.isVisible())
                graphics.drawImage(object.getImage(), object.getX(), object.getY(), SIDE, SIDE, null);
        }
        graphics.drawImage(gameScreen.player.getImage(), gameScreen.player.getX(), gameScreen.player.getY(), SIDE, SIDE, null);
    }
}