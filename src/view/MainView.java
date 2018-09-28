package view;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;

public class MainView {

    private static JFrame frame;

    private JTabbedPane TabMain;
    private JPanel panel1;
    private JList HistoryList;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;

    public MainView() {
        HistoryList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                ///History Selectionchanged
            }
        });


    }

    public static void main(String[] args) {

        JMenu menu, saveSubMenu, loadSubMenu;
        JMenuItem saveMenuItem, saveInDBMenuITem, loadMenuItem, loadFromDBMenuITem;

        menu = new JMenu("Menu");
        saveSubMenu = new JMenu("Save");
        saveMenuItem = new JMenuItem("Save in file");
        saveMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save(true);
            }
        });

        saveInDBMenuITem = new JMenuItem("Save in database");
        saveInDBMenuITem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save(false);
            }
        });

        saveSubMenu.add(saveMenuItem);
        saveSubMenu.add(saveInDBMenuITem);

        menu.add(saveSubMenu);

        loadSubMenu = new JMenu("Load");
        loadMenuItem = new JMenuItem("Load from file");
        loadMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                load(true);
            }
        });

        loadFromDBMenuITem = new JMenuItem("Load from databse");
        loadFromDBMenuITem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                load(false);
            }
        });

        loadSubMenu.add(loadMenuItem);
        loadSubMenu.add(loadFromDBMenuITem);
        menu.add(loadSubMenu);


        JMenuBar mb = new JMenuBar();
        mb.add(menu);
        frame = new JFrame("MainView");
        frame.setContentPane(new MainView().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(800, 800);
        frame.setJMenuBar(mb);
        frame.setLayout(null);
        frame.setVisible(true);
    }


    static void load(boolean isLocal) {

    }


    static void save(boolean isLocal) {

    }


}

