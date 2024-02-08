import javax.swing.table.AbstractTableModel;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class JBDataModel extends AbstractTableModel {

    private static final long serialVersionUID = 1L;

    PropertyChangeSupport stanAplikacji;

    public JBDataModel(PropertyChangeSupport stanAplikacji) {
        super();
        this.stanAplikacji = stanAplikacji;
    }

    String[] column = { "ID", "Username", "Email", "Password" };
    String columnSongs[] = { "ID", "Title", "Artist", "Album", "Genre" };
    ArrayList<User> users = new ArrayList<>();
    ArrayList<Song> songs = new ArrayList<>();


    @Override
    public String getColumnName(int columnIndex) {

        if ("users".equals(UpdateTable.selectedTable)) {
            return column[columnIndex];
        } else if ("songs".equals(UpdateTable.selectedTable)) {
            return columnSongs[columnIndex];
        }


        return "";
    }


    @Override
    public int getColumnCount() {
        if ("users".equals(UpdateTable.selectedTable)) {
            return column.length;
        } else if ("songs".equals(UpdateTable.selectedTable)) {
            return columnSongs.length;
        }
        return 0;
    }


    @Override
    public int getRowCount() {

        if (MyFrame.selectTable.getSelectedItem().equals("users")) {
            return users.size();
        } else if (MyFrame.selectTable.getSelectedItem().equals("songs")) {
            return songs.size();
        }
        return 0;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
//        stanAplikacji.firePropertyChange("err", "", "" + rowIndex);

        if (MyFrame.selectTable.getSelectedItem().equals("users")) {
            if (users.size() <= rowIndex || users.get(rowIndex) == null) {
                System.out.println("getValueAt " + rowIndex + " " + columnIndex);
                ArrayList<User> chunk = DataAccess.getUsersPart(rowIndex);
                int i = 0;
                for (User user : chunk) {
                    if (users.size() <= rowIndex + i) {
                        users.add(user);
                    } else {
                        users.set(rowIndex + i, user);
                    }
                    i++;
                }
            }

            switch (columnIndex) {
                case 0:
                    return users.get(rowIndex).id;
                case 1:
                    return users.get(rowIndex).username;
                case 2:
                    return users.get(rowIndex).email;
                case 3:
                    return users.get(rowIndex).password;
            }
        } else if (MyFrame.selectTable.getSelectedItem().equals("songs")) {
            if (songs.size() <= rowIndex || songs.get(rowIndex) == null) {
                System.out.println("getValueAt " + rowIndex + " " + columnIndex);
                ArrayList<Song> chunk = DataAccess.getSongsPart(rowIndex);
                int i = 0;
                for (Song song : chunk) {
                    if (songs.size() <= rowIndex + i) {
                        songs.add(song);
                    } else {
                        songs.set(rowIndex + i, song);
                    }
                    i++;
                }
            }

            switch (columnIndex) {
                case 0:
                    return songs.get(rowIndex).id;
                case 1:
                    return songs.get(rowIndex).title;
                case 2:
                    return songs.get(rowIndex).author;
                case 3:
                    return songs.get(rowIndex).genre;
                case 4:
                    return songs.get(rowIndex).album;
            }
        }

        return "";
    }



}