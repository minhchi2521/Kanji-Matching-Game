import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JPanel {
    private KanjiMatchingGame game;

    public MainMenu(KanjiMatchingGame game) {
        this.game = game;
        this.setLayout(null);
        initComponents();
    }

    private void initComponents() {
        JLabel title = new JLabel("Kanji Matching", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 36));
        title.setBounds(150, 100, 350, 50);
        this.add(title);

        JButton playButton = new JButton("Play");
        playButton.setBounds(250, 300, 150, 50);
        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.startGame();
            }
        });
        this.add(playButton);

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(250, 400, 150, 50);
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        this.add(exitButton);
    }
}
