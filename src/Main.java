import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main extends JFrame {

    private Main() throws IOException {
        setLayout(new BorderLayout());
        GameScreen.SIDE = 60;

        Levels levels = new Levels();
        GameScreen gameScreen = new GameScreen(levels);
        DrawingPanel drawingPanel = new DrawingPanel(gameScreen);
        InfoPanel infoPanel = new InfoPanel(gameScreen);

        infoPanel.setPreferredSize(new Dimension(150,getHeight()));
        add(infoPanel, BorderLayout.EAST);

        drawingPanel.setFocusable(true);

        drawingPanel.addKeyListener(new GameControl(gameScreen,drawingPanel,infoPanel));
        add(drawingPanel, BorderLayout.CENTER);

        infoPanel.addDrawingPanel(drawingPanel);

        setTitle("Sokoban");
        setSize(650, 530);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public static void main(String[] args) {
        try {
            new Main();
        }catch (IOException e){
            JOptionPane.showMessageDialog(null, "File Do Not Exist");
        }
    }
}