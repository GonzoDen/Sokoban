import javax.swing.*;

class Skins {
    enum SkinPacks {
        DEFAULT,
        MARIO
    }

    static SkinPacks curSkin = SkinPacks.DEFAULT;

    private Skins() {

    }

    static void changeSkin(SkinPacks packs) {
        if (packs == SkinPacks.DEFAULT)
            changeSkin("src/Default/RobotU.png","src/Default/RobotR.png", "src/Default/RobotD.png", "src/Default/RobotL.png", "src/Default/BoxBlue.png", "src/Default/BoxRed.png",
                    "src/Default/Wall.png", "src/Default/Ground.png", "src/Default/Goal.png");
        else if (packs == SkinPacks.MARIO)
            changeSkin("src/Mario/PlayerMario.jpg","src/Mario/PlayerMario.jpg", "src/Mario/PlayerMario.jpg", "src/Mario/PlayerMario.jpg", "src/Mario/Box.jpg", "src/Mario/BoxCoin.jpg",
                    "src/Mario/Wall.jpg", "src/Default/Ground.png", "src/Default/Goal.png");
        curSkin = packs;
    }

    private static void changeSkin(String s, String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8) {
        Player.setIconU(new ImageIcon(s));
        Player.setIconR(new ImageIcon(s1));
        Player.setIconD(new ImageIcon(s2));
        Player.setIconL(new ImageIcon(s3));
        Box.setIconDefault(new ImageIcon(s4));
        Box.setIconWin(new ImageIcon(s5));
        Wall.setIcon(new ImageIcon(s6));
        Floor.setIcon(new ImageIcon(s7));
        GoalPoint.setIcon(new ImageIcon(s8));
    }
}