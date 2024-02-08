import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class DataAccess {

    //=========================CONNECT=========================
    static Connection conn = null;

    public static Connection getConnection() {
        if (conn == null) {
            try {
                conn = DriverManager
                        .getConnection("jdbc:mysql://localhost:55555/project?user=root&serverTimezone=UTC");
                return conn;
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
        }
        return conn;
    }

    //===============================SELECT========================================
    public static ArrayList<User> getUsersAll() {
        getConnection();
        ArrayList<User> users = new ArrayList<User>();
        Statement stmt = null;
        String query = "SELECT * FROM users";

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                User user = new User(rs.getInt("id_users"), rs.getString("username"), rs.getString("email"), rs.getString("pswd"));
                users.add(user);
            }
            System.out.println(users.size());
            return users;
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return null;
    }


    public static ArrayList<User> getUsersNull() {
        getConnection();
        ArrayList<User> users = new ArrayList<>();
        Statement stmt = null;
        String query = "SELECT count(*) FROM users";

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            int count = rs.getInt(1);
            System.out.println(count);

            for (int i = 0; i < count; i++)
                users.add(null);
            // System.out.println(users.size());
            return users;
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return null;
    }

    public static ArrayList<User> getUsersPart(int offset) {
        getConnection();
        ArrayList<User> users = new ArrayList<User>();
        Statement stmt = null;
        String query = "SELECT * FROM users LIMIT 24 OFFSET " + offset;

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                User user = new User(rs.getInt("id_users"), rs.getString("username"), rs.getString("email"),
                        rs.getString("pswd"));
                users.add(user);
                // System.out.println(dept);
            }
            // System.out.println(users.size());
            return users;
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return null;
    }




    //========================================SONGS========================================
    public static ArrayList<Song> getSongsAll() {
        getConnection();
        ArrayList<Song> songs = new ArrayList<Song>();
        Statement stmt = null;
        String query = "SELECT songs_id ,title, author, album, genre FROM songs2";

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Song song = new Song(rs.getInt("songs_id"), rs.getString("title"), rs.getString("author"), rs.getString("album"), rs.getString("genre"));
                songs.add(song);
            }
            System.out.println(songs.size());
            return songs;
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return null;
    }


    public static ArrayList<Song> getSongsPart(int offset) {
        getConnection();
        ArrayList<Song> songs = new ArrayList<Song>();
        Statement stmt = null;
        String query = "SELECT songs_id, title, author, album, genre FROM songs2 LIMIT 24 OFFSET " + offset;

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int songId = rs.getInt("songs_id");
                String title = rs.getString("title");
                String author = rs.getString("author");
                String album = rs.getString("album");
                String genre = rs.getString("genre");

                Song song = new Song(songId, title, author, album, genre);
                songs.add(song);
            }
            return songs;
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return null;
    }

    //=============================ADD RECORD==============================================
    public static void addUserToDatabase(String username, String email, String password) {
        try {
            String query = "INSERT INTO users (username, email, pswd) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = DataAccess.conn.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "User added successfully!");
//                AddWindow.showDialog();
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


    public static void addSongToDatabase(String title, String artist, String genre, String album) {
        try {
            String query = "INSERT INTO songs2 (title, genre, author, album) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = DataAccess.conn.prepareStatement(query);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, artist);
            preparedStatement.setString(3, genre);
            preparedStatement.setString(4, album);

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "Song added successfully!");
//                AddWindow.dialog.dispose();
                MyFrame.dataModel.fireTableDataChanged();
                UpdateTable.updateTable();
                MyFrame.stanAplikacji.firePropertyChange("msg", "", "Record added successfully!");
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



    //======================================UPDATE======================================
    public static void updateRecord(int selectedRowInView, String newTitle, String newArtist, String newGenre, String newAlbum) {
        if (selectedRowInView != -1) {
            int selectedRowInModel = MyFrame.table.convertRowIndexToModel(selectedRowInView);

            boolean success = updateRecordInDatabase(selectedRowInModel, newTitle, newArtist, newGenre, newAlbum);
            if (success) {
                if ("users".equals(UpdateTable.selectedTable)) {
                    MyFrame.dataModel.users.get(selectedRowInModel).username = newTitle;
                    MyFrame.dataModel.users.get(selectedRowInModel).email = newArtist;
                    MyFrame.dataModel.users.get(selectedRowInModel).password = newGenre;
                } else if ("songs".equals(UpdateTable.selectedTable)) {
                    MyFrame.dataModel.songs.get(selectedRowInModel).title = newTitle;
                    MyFrame.dataModel.songs.get(selectedRowInModel).author = newArtist;
                    MyFrame.dataModel.songs.get(selectedRowInModel).genre = newGenre;
                    MyFrame.dataModel.songs.get(selectedRowInModel).album = newAlbum; // Add this line
                }

                MyFrame.dataModel.fireTableDataChanged();
                UpdateTable.updateTable();
                MyFrame.stanAplikacji.firePropertyChange("msg", "", "Record updated successfully!");
                MyFrame.button1.setEnabled(false);
                MyFrame.button2.setEnabled(false);
            }
        } else {
            MyFrame.stanAplikacji.firePropertyChange("err", "", "No rows are selected.");
        }
    }

    private static boolean updateRecordInDatabase(int selectedRow, String newTitle, String newArtist, String newGenre, String newAlbum) {
        try {
            int recordId;

            if ("users".equals(UpdateTable.selectedTable)) {
                recordId = MyFrame.dataModel.users.get(selectedRow).id;
            } else if ("songs".equals(UpdateTable.selectedTable)) {
                recordId = MyFrame.dataModel.songs.get(selectedRow).id;
            } else {
                // Handle other cases or throw an exception
                return false;
            }

            String query;

            if ("users".equals(UpdateTable.selectedTable)) {
                query = "UPDATE users SET username = ?, email = ?, pswd = ? WHERE id_users = ?";
            } else {
                query = "UPDATE songs2 SET title = ?, author = ?, genre = ?, album = ? WHERE songs_id = ?";
            }

            try (PreparedStatement preparedStatement = DataAccess.conn.prepareStatement(query)) {
                preparedStatement.setString(1, newTitle);
                preparedStatement.setString(2, newArtist);
                preparedStatement.setString(3, newGenre);
                if ("songs".equals(UpdateTable.selectedTable)) {
                    preparedStatement.setString(4, newAlbum);
                    preparedStatement.setInt(5, recordId);
                } else {
                    preparedStatement.setInt(4, recordId);
                }

                int rowsUpdated = preparedStatement.executeUpdate();

                return rowsUpdated > 0;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "SQLException: " + ex.getMessage());
            return false;
        }
    }


    //======================================DELETE======================================
    public static void deleteRecord() {
        int selectedRowInView = MyFrame.table.getSelectedRow();
        if (selectedRowInView != -1) {
            int selectedRowInModel = MyFrame.table.convertRowIndexToModel(selectedRowInView);

            boolean success = deleteRecordFromDatabase(selectedRowInModel);
            if (success) {
                // Refresh the table after deletion
                if ("users".equals(UpdateTable.selectedTable)) {
                    MyFrame.dataModel.users.remove(selectedRowInModel);
                } else if ("songs".equals(UpdateTable.selectedTable)) {
                    MyFrame.dataModel.songs.remove(selectedRowInModel);
                }

                MyFrame.dataModel.fireTableDataChanged();
                MyFrame.button1.setEnabled(false);
                MyFrame.button2.setEnabled(false);

                MyFrame.stanAplikacji.firePropertyChange("msg", "", "Record deleted successfully!");
            }
        } else {
            MyFrame.stanAplikacji.firePropertyChange("err", "", "No rows are selected.");
        }
    }

    private static boolean deleteRecordFromDatabase(int selectedRow) {
        try {
            int recordId;
            String query;

            if ("users".equals(UpdateTable.selectedTable)) {
                recordId = MyFrame.dataModel.users.get(selectedRow).id;
                query = "DELETE FROM users WHERE id_users = ?";
            } else if ("songs".equals(UpdateTable.selectedTable)) {
                recordId = MyFrame.dataModel.songs.get(selectedRow).id;
                query = "DELETE FROM songs2 WHERE songs_id = ?";
            } else {
                System.out.println("Error delete record");
                return false;
            }

            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, recordId);

            int rowsDeleted = preparedStatement.executeUpdate();

            preparedStatement.close();

            return rowsDeleted > 0;
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "SQLException: " + ex.getMessage());
            return false;
        }
    }
}