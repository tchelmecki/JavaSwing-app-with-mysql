import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateWindow {
    JDialog dialog = new JDialog();
    JButton button1;
    JButton button2;
    JTextField usernameField;
    JTextField emailField;
    JTextField thirdField;
    JTextField pswdField;
    JLabel label2;
    JLabel label3;
    JLabel label4;
    JLabel label5;

    public UpdateWindow(int selectedRowInView) {
        dialog.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(0, 2, 15, 15));
        panel.setBackground(Color.decode("#ff6257"));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        ImageIcon image = new ImageIcon("src/headphones.png");
        dialog.setIconImage(image.getImage());

        usernameField = new JTextField();
        emailField = new JTextField();
        pswdField = new JTextField();
        thirdField = new JTextField();
        usernameField.setColumns(15);
        emailField.setColumns(15);
        pswdField.setColumns(15);
        thirdField.setColumns(15);

        if ("users".equals(UpdateTable.selectedTable)) {
            User selectedUser = MyFrame.dataModel.users.get(MyFrame.table.convertRowIndexToModel(selectedRowInView));

            label2 = new JLabel("Username:");
            label3 = new JLabel("Email:");
            label4 = new JLabel("Password");

            usernameField.setText(selectedUser.username);
            emailField.setText(selectedUser.email);
            pswdField.setText(selectedUser.password);
        } else {
            Song selectedSongs = MyFrame.dataModel.songs.get(MyFrame.table.convertRowIndexToModel(selectedRowInView));

            label2 = new JLabel("Title:");
            label3 = new JLabel("Artist:");
            label4 = new JLabel("Album:");
            label5 = new JLabel("Genre:");
            label5.setFont(new Font("Comic Sans", Font.BOLD, 15));

            usernameField.setText(selectedSongs.title);
            emailField.setText(selectedSongs.author);
            thirdField.setText(selectedSongs.album);
            pswdField.setText(selectedSongs.genre);

        }

        label2.setFont(new Font("Comic Sans", Font.BOLD, 15));
        label3.setFont(new Font("Comic Sans", Font.BOLD, 15));
        label4.setFont(new Font("Comic Sans", Font.BOLD, 15));

        button1 = new JButton("Update");
        button2 = new JButton("Cancel");

        Buttons.setButtonProperties(button1);
        Buttons.setButtonProperties(button2);

        panel.add(label2);
        panel.add(usernameField);
        label2.setForeground(Color.WHITE);

        panel.add(label3);
        panel.add(emailField);
        label3.setForeground(Color.WHITE);

        panel.add(label4);
        panel.add(pswdField);
        label4.setForeground(Color.WHITE);

        if ("songs".equals(UpdateTable.selectedTable)) {
            panel.add(label5);
            panel.add(thirdField);
            label5.setForeground(Color.WHITE);
        }

        panel.add(button1);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newUsername = usernameField.getText();
                String newEmail = emailField.getText();
                String newPassword = pswdField.getText();
                String newAlbum = "";

                if ("songs".equals(UpdateTable.selectedTable)) {
                    newAlbum = thirdField.getText();
                    panel.remove(label5);
                    panel.remove(thirdField);
                }

                DataAccess.updateRecord(selectedRowInView, newUsername, newEmail, newPassword, newAlbum);
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

        dialog.setTitle("Update record");
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setSize(290, 240);
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(null);
        dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);

        setDialogProperties(dialog);
        dialog.setVisible(true);
    }

    private void setDialogProperties(JDialog dialog) {
        dialog.getContentPane().setBackground(Color.decode("#242742"));
    }
}
