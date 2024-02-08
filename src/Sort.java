import java.util.Comparator;

public class Sort {

   public static void sort(){
        // Dla każdej kolumny dodaj komparator, ale tylko dla kolumny "ID"
        for (int i = 0; i < MyFrame.table.getColumnCount(); i++) {
            final int columnIndex = i;

            if (columnIndex == 0) { // Sprawdź, czy to kolumna "ID"
                MyFrame.sorter.setComparator(columnIndex, new Comparator<Object>() {
                    @Override
                    public int compare(Object o1, Object o2) {
                        if (o1 instanceof Integer && o2 instanceof Integer) {
                            // Sortowanie od największej do najmniejszej
                            return ((Integer) o2).compareTo((Integer) o1);
                        } else if (o1 instanceof String && o2 instanceof String) {
                            try {
                                Integer num1 = Integer.parseInt((String) o1);
                                Integer num2 = Integer.parseInt((String) o2);
                                // Sortowanie od największej do najmniejszej
                                return num2.compareTo(num1);
                            } catch (NumberFormatException e) {
                                return ((String) o2).compareTo((String) o1);
                            }
                        } else {
                            return 0;
                        }
                    }
                });
            }
        }
    }
}
