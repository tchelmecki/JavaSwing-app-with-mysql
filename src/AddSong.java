import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddSong {

    JDialog dialog = new JDialog();
    JButton button1;
    JButton button2;

    public AddSong() {
        dialog.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(0, 2, 15, 15));
        panel.setBackground(Color.decode("#ff6257"));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        ImageIcon image = new ImageIcon("src/headphones.png");
        dialog.setIconImage(image.getImage());

        JLabel label2 = new JLabel("Title:");
        JLabel label3 = new JLabel("Artist:");
        JLabel label4 = new JLabel("genre:");
        label2.setFont(new Font("Comic Sans", Font.BOLD,15));
        label3.setFont(new Font("Comic Sans", Font.BOLD,15));
        label4.setFont(new Font("Comic Sans", Font.BOLD,15));

        JTextField usernameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField pswdField = new JTextField();
        usernameField.setColumns(15); // Ustaw szerokość pola tekstowego
        emailField.setColumns(15);
        pswdField.setColumns(15);
//        usernameField.setFont(new Font("Comic Sans", Font.BOLD,15));
//        emailField.setFont(new Font("Comic Sans", Font.BOLD,15));
//        pswdField.setFont(new Font("Comic Sans", Font.BOLD,15));


        button1 = new JButton("Add");
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


        panel.add(button1);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String email = emailField.getText();
                String password = pswdField.getText();
                addUserToDatabase(username, email, password);
            }
        });
        panel.add(button2);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose(); // Zamknij okno dialogowe
            }
        });

        dialog.add(panel);

        dialog.setTitle("Add record");
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setSize(290, 240); // Zwiększam wysokość dla lepszego dopasowania
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(null);
        dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);

        setDialogProperties(dialog);
        dialog.setVisible(true);
    }

    private void addUserToDatabase(String username, String email, String password) {
        try {
            String query = "INSERT INTO users (username, email, pswd) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = DataAccess.conn.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "User added successfully!");
                dialog.dispose();
                MyFrame.dataModel.fireTableDataChanged();
                UpdateTable.updateTable();
                MyFrame.stanAplikacji.firePropertyChange("msg", "", "Record added successfully!");


//                showTable();
//                updateConnectionStatus("User added successfully!");
            } else {
                MyFrame.stanAplikacji.firePropertyChange("err", "", "Record not added successfully!");
                JOptionPane.showMessageDialog(null, "Failed to add record.");
            }

            preparedStatement.close();
        } catch (SQLException ex) {
            MyFrame.stanAplikacji.firePropertyChange("err", "", "Record not added successfully!");

            JOptionPane.showMessageDialog(null, "SQLException: " + ex.getMessage());
        }
    }


    private void setLabelProperties(JLabel label) {
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setForeground(Color.WHITE); // Ustawienie koloru czcionki
    }

    private void setDialogProperties(JDialog dialog) {
        dialog.getContentPane().setBackground(Color.decode("#242742")); // Ustawienie koloru tła
    }

}
