import javax.swing.JFrame;
import java.awt.Dimension;

public class MyFrame extends JFrame {
    public MyFrame() {
        this.setTitle("Kanji Matching Game");
        this.setSize(new Dimension(650, 750)); // Kích thước bao gồm viền
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); // Canh giữa màn hình
        this.setResizable(false);
    }
}
