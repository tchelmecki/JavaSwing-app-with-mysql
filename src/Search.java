import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Search {
    JDialog dialog;
    JTextField searchField;
    JLabel searchLabel;
    JButton searchButton;
    public Search() {
        dialog = new JDialog();
        dialog.setLayout(new BorderLayout());
        dialog.setSize(500, 150);
        JPanel panel = new JPanel(new BorderLayout());

        panel.setBackground(Color.decode("#ff6257"));

        ImageIcon image = new ImageIcon("src/headphones.png");
        dialog.setIconImage(image.getImage());

        searchField = new JTextField(16);
        searchLabel = new JLabel("Search record: ");
        searchLabel.setForeground(Color.white);
        searchLabel.setFont(new Font("Comic Sans", Font.BOLD,15));
        searchButton = new JButton("Search");
        Buttons.setButtonProperties(searchButton);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button clicked!");

                String searchText = searchField.getText();
                System.out.println("Search text: " + searchText);

                MyFrame.sorter.setRowFilter(new MyRowFilter(searchText));
                dialog.dispose();
            }
        });

        searchField.setPreferredSize(new Dimension(400, 30));
        searchButton.setPreferredSize(new Dimension(80, 30));

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 40));
        searchPanel.setBackground(Color.decode("#ff6257"));
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

//        panel.add(searchPanel, BorderLayout.CENTER);

        dialog.add(searchPanel, BorderLayout.CENTER);

        dialog.setTitle("Find an record");
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(null);
        dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);

        // AddUser.setDialogProperties(dialog);
        dialog.setVisible(true);



    }


}
