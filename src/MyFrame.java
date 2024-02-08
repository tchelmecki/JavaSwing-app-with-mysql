import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;


import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class MyFrame extends JFrame implements ActionListener {
    public JButton button;
    public static  JButton button1;
    public static JButton button2;
    private JButton button3;
    private JLabel connectionStatusLabel;
    private static Connection conn;
    private DefaultTableModel model;
    public static JTable table;
    private JMenu aboutMenu;
    private JMenuItem aboutAuthor;
    private JMenuItem addItem;
    private JMenuItem updateItem;
    private JMenuItem deleteItem;
    private JMenuItem searchItem;
    private JMenuItem exit;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    static JComboBox selectTable;
    private JCheckBox sth;
    static JBDataModel dataModel;
    private JMenuItem popupAddItem;
    private JMenuItem popupUpdateItem;
    private JMenuItem popupDeleteItem;
    private JMenuItem popupExit;
    private JMenuItem viewMaxItem;
    private JMenuItem viewMinItem;
    private JMenuItem popupView;
    private JMenuItem popupViewMax;
    private JMenuItem popupViewMin;
    private JMenuItem searchItemPop;
    public static TableRowSorter<TableModel> sorter;
    JTextField searchRecord;

    static PropertyChangeSupport stanAplikacji;


    public MyFrame(){
        stanAplikacji = new PropertyChangeSupport(this);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(800, 670));

        this.setTitle("Database - Music Web Player");
        ImageIcon image = new ImageIcon("src/headphones.png");
        this.setIconImage(image.getImage());
        this.setLocationRelativeTo(null);

        panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());
        panel1.setBackground(Color.red);
        this.add(panel1, BorderLayout.CENTER);

        panel2 = new JPanel();
        panel2.setLayout(new GridLayout(2, 1));
        panel2.setPreferredSize(new Dimension(800,200));
        panel2.setBackground(Color.decode("#ff6257"));
        panel1.add(panel2, BorderLayout.SOUTH);

        // Panel 3 z button, button1, button2
        panel3 = new JPanel();
        panel3.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 30));
        panel3.setSize(800, 200);
        panel3.setBackground(Color.decode("#ff6257"));
        panel2.add(panel3,BorderLayout.NORTH);

        // Panel 4 z JLabel, JTextField, button3
        panel4 = new JPanel();
        panel4.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 30));
        panel4.setBackground(Color.decode("#ff6257"));
        panel2.add(panel4,BorderLayout.SOUTH);

        JLabel searchLabel = new JLabel("Search record:");
        searchLabel.setForeground(Color.white);
        searchLabel.setFont(new Font("Comic Sans", Font.BOLD,15));

        panel4.add(searchLabel);


        //=====================JComboBox=====================
        String[] tablesDB = {"songs","users"};
        selectTable = new JComboBox(tablesDB);
        selectTable.setPreferredSize(new Dimension(100,30));
        panel3.add(selectTable);
        selectTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateTable.updateTable();
                deleteItem.setEnabled(false);
                updateItem.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button1.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.decode("#bc9898"), 1),
                        BorderFactory.createEmptyBorder(5, 10, 5, 10)));
                button2.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.decode("#bc9898"), 1),
                        BorderFactory.createEmptyBorder(5, 10, 5, 10)));


            }
        });

        selectTable.setBackground(Color.WHITE);
        selectTable.setFont(new Font("Comic Sans", Font.BOLD,15));
//        selectTable.setFocusable(false);
        selectTable.setBorder(BorderFactory.createLineBorder(Color.white, 1));

        //=====================JBDataModel=====================
        dataModel = new JBDataModel(stanAplikacji);

//        dataModel.users = DataAccess.getUsersNull();
        table = new JTable(dataModel);

        table.setBounds(30, 40, 200, 300);
        JScrollPane scrollPane = new JScrollPane(table);
        panel1.add(scrollPane, BorderLayout.CENTER);

        //=====================BUTTONS=====================
        button = new JButton("Insert record");
        button1 = new JButton("Update record");
        button2 = new JButton("Delete record");
        button3 = new JButton("Search record");
        button.setBounds(100,200,60,30);
        panel3.add(button);
        panel3.add(button1);
        panel3.add(button2);

        Buttons.setButtonProperties(button);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.white, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        Buttons.setButtonProperties(button1);
        Buttons.setButtonProperties(button2);
        Buttons.setButtonProperties(button3);



        button1.setEnabled(false);
        button2.setEnabled(false);
        button1.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#bc9898"), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        button2.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#bc9898"), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));




//        frame.add(scrollPane, BorderLayout.CENTER);

        //=======================SEARCH==========================
        searchRecord = new JTextField();
        searchRecord.setPreferredSize(new Dimension(200, 30));
        searchRecord.setColumns(16);
        panel2.add(searchRecord);
        panel4.add(searchRecord);

        panel4.add(button3);
//        button3.setBorder(BorderFactory.createCompoundBorder(
//                BorderFactory.createLineBorder(Color.decode("#bc9898"), 1),
//                BorderFactory.createEmptyBorder(5, 10, 5, 10)));

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = searchRecord.getText();
                sorter.setRowFilter(new MyRowFilter(searchText));
            }
        });




        //========================MNEMONIKI========================
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(Color.white);
        JMenu fileMenu = new JMenu("File");
        JMenu helpMenu = new JMenu("View");
        aboutMenu = new JMenu("About");

        aboutAuthor = new JMenuItem("Credits");
        viewMaxItem = new JMenuItem("Full");
        viewMinItem = new JMenuItem("Minimize");
        addItem = new JMenuItem("Add");
        updateItem = new JMenuItem("Update");
        deleteItem = new JMenuItem("Delete");
        searchItem = new JMenuItem("Search");
        deleteItem.setEnabled(false);
        deleteItem.addActionListener(this);
        exit = new JMenuItem("Exit");

        updateItem.setEnabled(false);


        // Ustawienie preferowanej szerokości i wysokości dla JMenuItem
        addItem.setPreferredSize(new Dimension(100, 25));
        updateItem.setPreferredSize(new Dimension(100, 25));
        deleteItem.setPreferredSize(new Dimension(100, 25));
        searchItem.setPreferredSize(new Dimension(100,25));
        exit.setPreferredSize(new Dimension(100, 25));


        //ADDING MENU TO JMENU (FILE, EDIT ABOUT)
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        menuBar.add(aboutMenu);
        this.setJMenuBar(menuBar);

        //============ADDING INSIDE MENU FILE============
        fileMenu.add(addItem);
        fileMenu.add(updateItem);
        fileMenu.add(deleteItem);
        fileMenu.add(searchItem);
        fileMenu.add(exit);
        aboutMenu.add(aboutAuthor);

        helpMenu.add(viewMaxItem);
        helpMenu.add(viewMinItem);


        //JMENU
        fileMenu.setMnemonic(KeyEvent.VK_F);
        helpMenu.setMnemonic(KeyEvent.VK_V);
        aboutMenu.setMnemonic(KeyEvent.VK_A);
        aboutAuthor.setMnemonic(KeyEvent.VK_C);

        //INSIDE JMENU
        addItem.setMnemonic(KeyEvent.VK_A);
        updateItem.setMnemonic(KeyEvent.VK_U);
        deleteItem.setMnemonic(KeyEvent.VK_D);
        exit.setMnemonic(KeyEvent.VK_E);
        viewMaxItem.setMnemonic(KeyEvent.VK_F);
        viewMinItem.setMnemonic(KeyEvent.VK_M);
        searchItem.setMnemonic(KeyEvent.VK_S);


        //ACTION FILE
        addItem.addActionListener(this);
        updateItem.addActionListener(this);
        button.addActionListener(this);
        button1.addActionListener(this);
        button2.addActionListener(this);
        aboutAuthor.addActionListener(this);
        searchItem.addActionListener(this);
        exit.addActionListener(this);
        viewMaxItem.addActionListener(this);
        viewMinItem.addActionListener(this);




//        JButton button = new JButton("Load");
//        button.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                table.setAutoCreateRowSorter(true);
//                TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
//                table.setRowSorter(sorter);
//
//                dataModel.users = DataAccess.getUsersNull();
//
//                dataModel.fireTableDataChanged();
//            }
//        });
//
//        frame.add(button, BorderLayout.EAST);

        JPanel statusBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel label1 = new JLabel("Hello there!");
        label1.setPreferredSize(new Dimension(200, 15));

        label1.setOpaque(true);
        JLabel label2 = new JLabel("");

        label2.setPreferredSize(new Dimension(200, 15));

        stanAplikacji.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("msg"))
                    label1.setText(evt.getNewValue().toString());
                if (evt.getPropertyName().equals("err"))
                    label2.setText(evt.getNewValue().toString());
                if (evt.getPropertyName().equals("news"))
                    label2.setText(evt.getNewValue().toString());
            }
        });

        this.add(statusBar, BorderLayout.SOUTH);

        statusBar.add(label1);
        statusBar.add(label2);

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        ListSelectionModel rowSM = table.getSelectionModel();

        rowSM.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                // Ignore extra messages.
                if (e.getValueIsAdjusting())
                    return;

                ListSelectionModel lsm = (ListSelectionModel) e.getSource();
                if (lsm.isSelectionEmpty()) {
                    stanAplikacji.firePropertyChange("err", "", "No rows are selected.");
                } else {
                    int selectedRow = table.getSelectedRow();
//                    int selectedRow = lsm.getMinSelectionIndex();
                    if ("users".equals(UpdateTable.selectedTable)) {
                        if (dataModel.users.get(selectedRow) != null)
                        stanAplikacji.firePropertyChange("msg", "", "" + dataModel.users.get(selectedRow).username);
                        stanAplikacji.firePropertyChange("err", "", "" + dataModel.users.get(selectedRow).email);

                    } else if ("songs".equals(UpdateTable.selectedTable)) {
                        if (dataModel.songs.get(selectedRow) != null)
                        stanAplikacji.firePropertyChange("msg", "", "" + dataModel.songs.get(selectedRow).author);
                        stanAplikacji.firePropertyChange("err", "", "" + dataModel.songs.get(selectedRow).title);

                    }

//                    if (dataModel.users.get(selectedRow) != null)
//                        stanAplikacji.firePropertyChange("msg", "", "" + dataModel.users.get(selectedRow).username);
                    button1.setEnabled(true);
                    button1.setBorder(BorderFactory.createCompoundBorder(
                            BorderFactory.createLineBorder(Color.white, 1),
                            BorderFactory.createEmptyBorder(5, 10, 5, 10)));

                    button2.setEnabled(true);
                    button2.setBorder(BorderFactory.createCompoundBorder(
                            BorderFactory.createLineBorder(Color.white, 1),
                            BorderFactory.createEmptyBorder(5, 10, 5, 10)));


                    deleteItem.setEnabled(true);
                    updateItem.setEnabled(true);
                    popupUpdateItem.setEnabled(true);
                    popupDeleteItem.setEnabled(true);
                }
            }
        });


        //========================JPOPUPMENU==========================
        final JPopupMenu popupmenu = new JPopupMenu("Edit");
        popupmenu.setPreferredSize(new Dimension(150, 150));

        popupAddItem = new JMenuItem("Add");
        popupAddItem.addActionListener(this);

        popupUpdateItem = new JMenuItem("Update");
        popupUpdateItem.addActionListener(this);
        popupUpdateItem.setEnabled(false);

        popupDeleteItem = new JMenuItem("Delete");
        popupDeleteItem.addActionListener(this);
        popupDeleteItem.setEnabled(false);

        JMenu popupViewMenu = new JMenu("View");
        popupViewMax = new JMenuItem("Full");
        popupViewMin = new JMenuItem("Min");

        popupViewMenu.add(popupViewMax);
        popupViewMenu.add(popupViewMin);

        popupViewMax.addActionListener(this);
        popupViewMin.addActionListener(this);

        searchItemPop = new JMenuItem("Search");
        searchItemPop.addActionListener(this);

        popupExit = new JMenuItem("Exit");
        popupExit.addActionListener(this);

        popupmenu.add(popupAddItem);
        popupmenu.add(popupUpdateItem);
        popupmenu.add(popupDeleteItem);
        popupmenu.add(popupViewMenu);
        popupmenu.add(searchItemPop);
        popupmenu.add(popupExit);

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e) && !e.isConsumed()) {
                    popupmenu.show(table, e.getX(), e.getY());
                    e.consume();
                }
            }
        });
        UpdateTable.updateTable();

//        TableRowSorter sorter = new TableRowSorter(model);
//        table.setRowSorter(sorter);

        sorter = new TableRowSorter(table.getModel());
        table.setRowSorter(sorter);
//        Sort.sort();



        this.setLocationRelativeTo(null);
        this.setMinimumSize(new Dimension(800, 670));

        this.setVisible(true);
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button || e.getSource() == popupAddItem) {
            AddWindow.showDialog();
        } else if (e.getSource() == button1 || e.getSource() == updateItem || e.getSource() == popupUpdateItem) {
            int selectedRow = MyFrame.table.getSelectedRow();
            UpdateWindow updateWindow = new UpdateWindow(selectedRow);

        } else if (e.getSource() == button2 || e.getSource() == deleteItem || e.getSource() == popupDeleteItem) {
            DataAccess.deleteRecord();
        } else if (e.getSource() == addItem || e.getSource() == popupAddItem) {
            AddWindow addWindow = new AddWindow();
        } else if (e.getSource() == aboutAuthor) {
            Credits credits = new Credits();
        } else if (e.getSource() == exit || e.getSource() == popupExit) {
            System.exit(0);
        } else if (e.getSource() == viewMinItem || e.getSource() == popupViewMin) {
            this.setSize(800, 600);
            this.setLocationRelativeTo(null);
            repaint();
        } else if (e.getSource() == viewMaxItem || e.getSource() == popupViewMax) {
            this.setExtendedState(JFrame.MAXIMIZED_BOTH);
            this.setLocationRelativeTo(null);
            repaint();
        } else if (e.getSource() == searchItem || e.getSource() == searchItemPop) {
            Search search = new Search();

        }
    }


}
