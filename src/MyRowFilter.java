import javax.swing.RowFilter;

public class MyRowFilter extends RowFilter<Object, Object> {
    private String searchText;

    MyRowFilter(String searchText) {
        this.searchText = searchText.toLowerCase();
    }

    @Override
    public boolean include(Entry<? extends Object, ? extends Object> entry) {
        for (int i = entry.getValueCount() - 1; i >= 0; i--) {
            Object value = entry.getValue(i);
            if (value != null) {
                String stringValue = value.toString().toLowerCase();
                if (stringValue.contains(searchText)) {
                    return true;
                }
            }
        }
        return false;
    }

//    @Override
//    public boolean include(Entry entry) {
//        return entry.getStringValue(1).indexOf(searchText) >= 0;
//    }

}
