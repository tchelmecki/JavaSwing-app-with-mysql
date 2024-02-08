import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddWindow {
    static JPanel panel;
    static JTextField field4;

    public static void showDialog() {
        JDialog dialog = new JDialog();
        initializeComponents(dialog);

        dialog.setTitle("Add record");
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setSize(340, 300);
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(null);
        dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);

        setDialogProperties(dialog);
        dialog.setVisible(true);
    }

    private static void initializeComponents(JDialog dialog) {
        dialog.setLayout(new BorderLayout());

        panel = new JPanel(new GridLayout(0, 2, 15, 15));
        panel.setBackground(Color.decode("#ff6257"));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        ImageIcon image = new ImageIcon("src/headphones.png");
        dialog.setIconImage(image.getImage());

        JLabel label2 = new JLabel();
        JLabel label3 = new JLabel();
        JLabel label4 = new JLabel();
        JLabel label5 = new JLabel();

        label2.setFont(new Font("Comic Sans", Font.BOLD, 15));
        label3.setFont(new Font("Comic Sans", Font.BOLD, 15));
        label4.setFont(new Font("Comic Sans", Font.BOLD, 15));
        label5.setFont(new Font("Comic Sans", Font.BOLD, 15));

        JTextField field1 = new JTextField();
        JTextField field2 = new JTextField();
        JTextField field3 = new JTextField();
        field4 = new JTextField();
        field1.setColumns(15);
        field2.setColumns(15);
        field3.setColumns(15);
        field4.setColumns(15);

        JButton button1 = new JButton("Add");
        JButton button2 = new JButton("Cancel");

        Buttons.setButtonProperties(button1);
        Buttons.setButtonProperties(button2);

        panel.add(label2);
        panel.add(field1);
        label2.setForeground(Color.WHITE);

        panel.add(label3);
        panel.add(field2);
        label3.setForeground(Color.WHITE);

        panel.add(label4);
        panel.add(field3);
        label4.setForeground(Color.WHITE);

        panel.add(label5);
        panel.add(field4);
        label5.setForeground(Color.WHITE);

        updateLabels(label2, label3, label4, label5);

        panel.add(button1);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ("users".equals(UpdateTable.selectedTable)) {
                    String username = field1.getText();
                    String email = field2.getText();
                    String password = field3.getText();
                    DataAccess.addUserToDatabase(username, email, password);
                } else if ("songs".equals(UpdateTable.selectedTable)) {
                    String title = field1.getText();
                    String artist = field2.getText();
                    String genre = field3.getText();
                    String album = field4.getText();
                    DataAccess.addSongToDatabase(title, artist, genre, album);
                }
                dialog.dispose();
            }
        });
        panel.add(button2);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        dialog.add(panel);
    }

    private static void updateLabels(JLabel label2, JLabel label3, JLabel label4, JLabel label5) {
        if ("users".equals(UpdateTable.selectedTable)) {
            label2.setText("Username:");
            label3.setText("Email:");
            label4.setText("Password:");


            panel.remove(label5);
            panel.remove(field4);

//            label5.setVisible(false);
//            field4.setVisible(false);
        } else if ("songs".equals(UpdateTable.selectedTable)) {
            label2.setText("Title");
            label3.setText("Artist:");
            label4.setText("Album:");
            label5.setText("Genre");

            label5.setVisible(true);
        }
    }

    private static void setDialogProperties(JDialog dialog) {
        dialog.getContentPane().setBackground(Color.decode("#242742"));
    }
}
