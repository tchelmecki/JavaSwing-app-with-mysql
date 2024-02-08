public class UpdateTable {
    public static String selectedTable;

    static void updateTable() {

        selectedTable = (String) MyFrame.selectTable.getSelectedItem();


        // W zależności od wybranej opcji, aktualizuj dane w tabeli
        if ("users".equals(selectedTable)) {
            MyFrame.dataModel.users = DataAccess.getUsersNull();

        } else if ("songs".equals(selectedTable)) {
            MyFrame.dataModel.songs = DataAccess.getSongsAll();

        }

        // Odśwież model tabeli
        MyFrame.dataModel.fireTableDataChanged();

        MyFrame.dataModel.fireTableStructureChanged();

    }
}
