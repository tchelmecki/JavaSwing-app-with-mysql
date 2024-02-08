import javax.swing.*;
import java.awt.*;

public class Credits {

    JDialog dialog = new JDialog();
    JLabel label = new JLabel();
    JLabel label1 = new JLabel("Tomasz Chełmecki");
    JLabel label2 = new JLabel("Technologies:");
    JLabel label3 = new JLabel("Java");
    JLabel label4 = new JLabel("MySQL");
    JLabel label5 = new JLabel("student");
    JLabel label6 = new JLabel("");

    public Credits() {
        dialog.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JPanel panel = new JPanel(new GridLayout(0, 1));

        dialog.setTitle("Credits");
        ImageIcon image = new ImageIcon("src/headphones.png");
        dialog.setIconImage(image.getImage());

        setLabelProperties(label);
        setLabelProperties(label1);
        setLabelProperties(label5);
        setLabelProperties(label2);
        setLabelProperties(label3);
        setLabelProperties(label4);

        label.setText("<html><b>Programmer:</b></html>");

        panel.add(label);
        panel.add(label1);
        panel.add(label5);
        panel.add(label6);
        panel.add(label2);
        panel.add(label3);
        panel.add(label4);
        panel.setSize(200, 200);

        label.setFont(new Font("Comic Sans", Font.BOLD,15));
        label2.setFont(new Font("Comic Sans", Font.BOLD,15));

        dialog.add(panel);

        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setSize(200, 200);
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(null);
        dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        panel.setBackground(Color.decode("#242742"));
        setDialogProperties(dialog);

        dialog.setVisible(true);
    }

    private void setLabelProperties(JLabel label) {
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setForeground(Color.WHITE); // Ustawienie koloru czcionki
    }

    private void setDialogProperties(JDialog dialog) {
        dialog.getContentPane().setBackground(Color.decode("#242742")); // Ustawienie koloru tła
    }
}
