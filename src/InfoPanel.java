import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class InfoPanel extends JPanel {
    private GameScreen gameScreen;
    private DrawingPanel drawingPanel;

    JLabel moveLabel;
    JLabel levelLabel;

    InfoPanel(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        setLayout(new GridLayout(5, 0));

        addGimmePanel();
        addMovePanel();
        addLevelPanel();
        addStepBackPanel();
        addResetPanel();

    }

    private void addStepBackPanel() {
        JPanel stepBackPanel = new JPanel();
        stepBackPanel.setLayout(new BorderLayout());

        JButton stepBack = new JButton("Back");

        stepBack.setBackground(new Color(0x659F3E));
        stepBack.setFont(new Font("Arial", Font.PLAIN, 15));
        stepBack.setFocusable(false);
        stepBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    gameScreen.stepBack();
                    drawingPanel.repaint();
                }
            }
        });
        stepBackPanel.add(stepBack);

        add(stepBackPanel);

    }

    private void addResetPanel() {
        JPanel resetPanel = new JPanel();
        resetPanel.setLayout(new BorderLayout());

        JButton resetButton = new JButton("Reset");

        resetButton.setBackground(new Color(0x659F3E));
        resetButton.setFont(new Font("Arial", Font.PLAIN, 15));
        resetButton.setFocusable(false);
        resetButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    gameScreen.reset();
                    drawingPanel.repaint();
                }
            }
        });
        resetPanel.add(resetButton);

        add(resetPanel);

    }

    private void addGimmePanel() {
        JPanel gimmePanel = new JPanel();
        gimmePanel.setLayout(new BorderLayout());

        TextField text = new TextField("GIMME");
        text.setFocusable(false);
        text.setFont(new Font("Arial", Font.PLAIN, 16));
        gimmePanel.add(text, BorderLayout.NORTH);

        JButton changeSkin = new JButton("A");
        changeSkin.setBackground(new Color(0x659F3E));
        changeSkin.setFont(new Font("Arial", Font.PLAIN, 30));
        changeSkin.setFocusable(false);
        changeSkin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    switch (Skins.curSkin) {
                        case DEFAULT:
                            Skins.changeSkin(Skins.SkinPacks.MARIO);
                            break;
                        case MARIO:
                            Skins.changeSkin(Skins.SkinPacks.DEFAULT);
                            break;
                    }
                    gameScreen.reset();
                    drawingPanel.repaint();
                }
            }
        });
        gimmePanel.add(changeSkin);

        add(gimmePanel);
    }

    private void addMovePanel() {
        JPanel movePanel = new JPanel();

        movePanel.setBackground(new Color(0x659F3E));
        movePanel.setLayout(new BorderLayout());

        JTextField text = new JTextField("Steps");
        text.setFocusable(false);
        text.setFont(new Font("Arial", Font.PLAIN, 16));
        movePanel.add(text, BorderLayout.NORTH);

        moveLabel = new JLabel();
        moveLabel.setHorizontalAlignment(JLabel.CENTER);
        moveLabel.setFont(new Font(moveLabel.getFont().getName(), Font.PLAIN, 36));
        moveLabel.setText(Integer.toString(gameScreen.getMoveCount()));
        movePanel.add(moveLabel, BorderLayout.CENTER);

        JButton prevTurn = new JButton("UNDO");
        prevTurn.setFocusable(false);
        prevTurn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    gameScreen.stepBack();
                    drawingPanel.repaint();
                    moveLabel.setText(Integer.toString(gameScreen.getMoveCount()));
                }
            }
        });
        movePanel.add(prevTurn, BorderLayout.SOUTH);

        add(movePanel);
    }

    private void addLevelPanel() {

        JPanel levelPanel = new JPanel();

        levelPanel.setBackground(new Color(0x659F3E));
        levelPanel.setLayout(new BorderLayout());

        TextField text = new TextField("Level");
        text.setFocusable(false);
        text.setFont(new Font("Arial", Font.PLAIN, 16));
        levelPanel.add(text, BorderLayout.NORTH);


        levelLabel = new JLabel();
        levelLabel.setHorizontalAlignment(JLabel.CENTER);
        levelLabel.setFont(new Font(levelLabel.getFont().getName(), Font.PLAIN, 36));
        levelLabel.setText(Integer.toString(gameScreen.getCurrentLevel() + 1));
        levelPanel.add(levelLabel, BorderLayout.CENTER);

        JPanel levelControl = new JPanel();
        levelControl.setLayout(new GridLayout(0, 2));
        levelPanel.add(levelControl, BorderLayout.SOUTH);

        JButton prevLevel = new JButton("<<");
        prevLevel.setFocusable(false);
        prevLevel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    gameScreen.setCurrentLevel(gameScreen.getCurrentLevel() - 1);
                    gameScreen.readLevel();
                    gameScreen.reset();
                    drawingPanel.repaint();
                    levelLabel.setText(Integer.toString(gameScreen.getCurrentLevel() + 1));
                }
            }
        });
        levelControl.add(prevLevel);
        add(levelPanel);

        JButton nextLevel = new JButton(">>");
        nextLevel.setFocusable(false);
        nextLevel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    gameScreen.setCurrentLevel(gameScreen.getCurrentLevel() + 1);
                    gameScreen.readLevel();
                    gameScreen.reset();
                    drawingPanel.repaint();
                    levelLabel.setText(Integer.toString(gameScreen.getCurrentLevel() + 1));
                }
            }
        });
        levelControl.add(nextLevel);

    }

    void addDrawingPanel(DrawingPanel cp) {
        this.drawingPanel = cp;
    }
}