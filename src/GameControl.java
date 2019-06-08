import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameControl extends KeyAdapter {
    private GameScreen gameScreen;
    private DrawingPanel drawingPanel;
    private InfoPanel infoPanel;


    GameControl(GameScreen gameScreen, DrawingPanel drawingPanel, InfoPanel infoPanel) {
        this.gameScreen = gameScreen;
        this.drawingPanel = drawingPanel;
        this.infoPanel = infoPanel;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && !gameScreen.checkWin())
            gameScreen.movePlayer(GameScreen.Directions.RIGHT);
        if (e.getKeyCode() == KeyEvent.VK_LEFT && !gameScreen.checkWin())
            gameScreen.movePlayer(GameScreen.Directions.LEFT);
        if (e.getKeyCode() == KeyEvent.VK_DOWN && !gameScreen.checkWin())
            gameScreen.movePlayer(GameScreen.Directions.DOWN);
        if (e.getKeyCode() == KeyEvent.VK_UP && !gameScreen.checkWin())
            gameScreen.movePlayer(GameScreen.Directions.UP);

        infoPanel.moveLabel.setText(Integer.toString(gameScreen.getMoveCount()));

        drawingPanel.repaint();
        if (gameScreen.checkWin() && !gameScreen.isCompleted()){
            JOptionPane.showMessageDialog(null, "You Completed This Level");
            gameScreen.setCompleted(true);
        }
    }
}