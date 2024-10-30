import javax.swing.SwingUtilities;

public class KanjiMatchingGame {
    private MyFrame frame1;

    public KanjiMatchingGame() {
        frame1 = new MyFrame();
        showMainMenu();
    }

    private void showMainMenu() {
        MainMenu menu = new MainMenu(this);
        frame1.setContentPane(menu);
        frame1.setVisible(true);
    }

    public void startGame() {
        GamePanel gamePanel = new GamePanel();
        frame1.setContentPane(gamePanel);
        frame1.revalidate();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new KanjiMatchingGame());
    }
}
