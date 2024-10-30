import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel {
    private String[] kanjiList = {
            "日", "一", "国", "人", "年", "大", "十", "二", "本", "中",
            "長", "出", "三", "時", "行", "見", "月", "分", "後", "前",
            "生", "五", "間", "上", "東", "四", "今", "金", "九", "入",
            "学", "高", "円", "子", "外", "八", "六", "下", "来", "気",
            "小", "七", "山", "話", "女", "北", "午", "百", "書", "先",
            "名", "川", "千", "水", "半", "男", "西", "電", "校", "語"
    };
    private List<JButton> kanjiButtons = new ArrayList<>();
    private JButton firstSelectedButton = null;
    private JButton secondSelectedButton = null;
    private int score = 0;
    private JLabel scoreLabel;

    public GamePanel() {
        setLayout(new BorderLayout());
        initializeKanjiGrid();
        initializeScorePanel();
    }

    private void initializeKanjiGrid() {
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(6, 6, 10, 10));  // 6x6 lưới

        List<String> kanjiPairs = new ArrayList<>();
        for (int i = 0; i < 18; i++) {  // 18 cặp Kanji
            kanjiPairs.add(kanjiList[i]);
            kanjiPairs.add(kanjiList[i]);
        }
        Collections.shuffle(kanjiPairs);

        for (String kanji : kanjiPairs) {
            JButton button = new JButton();
            button.setFont(new Font("SansSerif", Font.BOLD, 24));
            button.setText("");  // Để trống ban đầu
            button.putClientProperty("kanji", kanji);  // Lưu trữ Kanji trong nút
            button.addActionListener(new KanjiButtonListener());
            kanjiButtons.add(button);
            gridPanel.add(button);
        }

        add(gridPanel, BorderLayout.CENTER);
    }

    private void initializeScorePanel() {
        JPanel scorePanel = new JPanel();
        scoreLabel = new JLabel("Score: " + score);
        scoreLabel.setFont(new Font("SansSerif", Font.BOLD, 18));

        JButton restartButton = new JButton("Restart");
        restartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                restartGame();
            }
        });

        scorePanel.add(scoreLabel);
        scorePanel.add(restartButton);
        add(scorePanel, BorderLayout.SOUTH);
    }

    private void checkMatch() {
        if (firstSelectedButton != null && secondSelectedButton != null) {
            String kanji1 = (String) firstSelectedButton.getClientProperty("kanji");
            String kanji2 = (String) secondSelectedButton.getClientProperty("kanji");

            if (kanji1.equals(kanji2)) {
                firstSelectedButton.setEnabled(false);
                secondSelectedButton.setEnabled(false);
                firstSelectedButton.setBackground(Color.LIGHT_GRAY);
                secondSelectedButton.setBackground(Color.LIGHT_GRAY);
                score++;
                scoreLabel.setText("Score: " + score);
                resetSelection();
            } else {
                Timer timer = new Timer(500, new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        firstSelectedButton.setText("");
                        secondSelectedButton.setText("");
                        resetSelection();
                    }
                });
                timer.setRepeats(false);
                timer.start();
            }
        }
    }

    private void resetSelection() {
        firstSelectedButton = null;
        secondSelectedButton = null;
    }

    private class KanjiButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            if (button.getText().isEmpty() && button.isEnabled()) {
                button.setText((String) button.getClientProperty("kanji"));
                if (firstSelectedButton == null) {
                    firstSelectedButton = button;
                } else if (secondSelectedButton == null && button != firstSelectedButton) {
                    secondSelectedButton = button;
                    checkMatch();
                }
            }
        }
    }

    private void restartGame() {
        score = 0;
        scoreLabel.setText("Score: " + score);
        for (JButton button : kanjiButtons) {
            button.setEnabled(true);
            button.setText("");
            button.setBackground(null);
        }
        Collections.shuffle(kanjiButtons);
        resetSelection();
    }
}
