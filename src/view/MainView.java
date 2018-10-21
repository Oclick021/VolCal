package view;

import classes.*;
import interfaces.IShape;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.*;

import java.util.Vector;

public class MainView {

    private JFrame frame;
    private JMenu menu, saveSubMenu, loadSubMenu;
    private JMenuItem saveMenuItem, saveInDBMenuITem, loadMenuItem, loadFromDBMenuITem;
    private JMenuBar mb;
    private JTabbedPane TabMain;
    private JPanel panel1;
    private JList HistoryList;
    private JTextField txtBlokHoogte;
    private JTextField txtboxBlokLengte;
    private JTextField txtBlokBreede;
    private JTextField txtboxCylinderStraal;
    private JTextField txtboxCylinderHoogte;
    private JLabel lblBolAnswer;
    private JLabel lblBolStraal;
    private JLabel lblBlokHoogte;
    private JLabel lblBlokLengte;
    private JLabel lblBlokBreede;
    private JLabel lblBlokAnswer;
    private JLabel lblCylinderHoogte;
    private JLabel lblCylinderStraal;
    private JLabel lblCylinderAntwoord;
    private JFormattedTextField txtBoxBolStraal;
    private JPanel panel2;
    private JButton calculateButton;
    private JButton button1;

    private Vector<IShape> historyListItems = new Vector<>();
    public MainView() {
        fillHistory();
        createWindow();
        createMenubar();
        addEvents();



        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               calculateSphere();
            }
        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateCylinder();

            }
        });
    }
    void calculateCylinder(){
        if (tryParseDouble(txtboxCylinderHoogte.getText())&&tryParseDouble(txtboxCylinderStraal.getText())){

            double height = Double.parseDouble(txtboxCylinderHoogte.getText());
            double radius = Double.parseDouble(txtboxCylinderStraal.getText());

            Cylinder cylinder = new Cylinder(height,radius);
            lblCylinderAntwoord.setText(Double.toString(cylinder.getVolume()));
            historyListItems.add(cylinder);
            HistoryList.setListData(historyListItems);
            cylinder.saveAsText();
        }
    }
void  calculateSphere(){
    if (txtBoxBolStraal != null){
        if (tryParseDouble(txtBoxBolStraal.getText())){
            double n = Double.parseDouble(txtBoxBolStraal.getText());
            Sphere sphere = new Sphere(n);
            lblBolAnswer.setText(""+sphere.getVolume());
            historyListItems.add(sphere);
            HistoryList.setListData(historyListItems);
        }
    }
}
    void createWindow() {
        frame = new JFrame("MainView");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(800, 800);
        frame.setJMenuBar(mb);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    void createMenubar() {
        menu = new JMenu("Menu");
        saveSubMenu = new JMenu("Save");
        saveMenuItem = new JMenuItem("Save in file");

        saveInDBMenuITem = new JMenuItem("Save in database");

        saveSubMenu.add(saveMenuItem);
        saveSubMenu.add(saveInDBMenuITem);

        menu.add(saveSubMenu);

        loadSubMenu = new JMenu("Load");
        loadMenuItem = new JMenuItem("Load from file");

        loadFromDBMenuITem = new JMenuItem("Load from databse");


        loadSubMenu.add(loadMenuItem);
        loadSubMenu.add(loadFromDBMenuITem);
        menu.add(loadSubMenu);


        mb = new JMenuBar();
        mb.add(menu);
    }

    void fillHistory() {
        DbConnector db = new DbConnector();
        Vector<String> result = db.get("Select * from cube");
        HistoryList.setListData(result.toArray());
    }

    boolean tryParseDouble(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    void load(boolean isLocal) {

    }

    void save(boolean isLocal) {

    }


    void addEvents() {
        saveMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save(true);
            }
        });

        saveInDBMenuITem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save(false);
            }
        });
        loadMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                load(true);
            }
        });
        loadFromDBMenuITem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                load(false);
            }
        });


        HistoryList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                ///History Selectionchanged
            }
        });

        frame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {

            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }


        });
    }


}

