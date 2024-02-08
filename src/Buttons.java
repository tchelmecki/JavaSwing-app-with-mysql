import javax.swing.*;
import java.awt.*;

public class Buttons {
    public static void setButtonProperties(JButton button) {
        button.setBackground(Color.decode("#ff6257"));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Comic Sans", Font.BOLD,15));
        button.setFocusable(false);
//        button.setMargin(new Insets(10, 100, 10, 100));
//        button.setBorder(new LineBorder(Color.white));

        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.white, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
    }
}
