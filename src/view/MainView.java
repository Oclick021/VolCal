package view;

import classes.*;
import interfaces.IShape;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;

import java.util.Vector;

public class MainView {

    private JFrame frame;
    private JMenu menu, saveSubMenu, loadSubMenu;
    private JMenuItem saveMenuItem, saveMenuJson, saveInDBMenuITem, loadMenuItem, loadMenuJson, loadFromDBMenuITem;
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
    private JPanel bolPane;
    private JButton calculateButton;
    private JButton button1;
    private JButton button2;
    private JPanel j1;
    private JPanel blokPanel;
    private JPanel cilinderPanel;

    private Vector<IShape> historyListItems = new Vector<>();

    public MainView() {
        createMenubar();
        createWindow();

        addEvents();


    }

    void calculateCube() {
        if (tryParseDouble(txtBlokBreede.getText()) && tryParseDouble(txtBlokHoogte.getText()) && tryParseDouble(txtboxBlokLengte.getText())) {
            double length = Double.parseDouble(txtboxBlokLengte.getText());
            double height = Double.parseDouble(txtBlokHoogte.getText());
            double width = Double.parseDouble(txtBlokBreede.getText());
            Cube cube = new Cube(length, width, height);
            lblBlokAnswer.setText("" + cube.getVolume());
            historyListItems.add(cube);
            HistoryList.setListData(historyListItems);
        }


    }

    void calculateCylinder() {
        if (tryParseDouble(txtboxCylinderHoogte.getText()) && tryParseDouble(txtboxCylinderStraal.getText())) {

            double height = Double.parseDouble(txtboxCylinderHoogte.getText());
            double radius = Double.parseDouble(txtboxCylinderStraal.getText());

            Cylinder cylinder = new Cylinder(height, radius);
            lblCylinderAntwoord.setText(Double.toString(cylinder.getVolume()));
            historyListItems.add(cylinder);
            HistoryList.setListData(historyListItems);
        }
    }

    void calculateSphere() {
        if (txtBoxBolStraal != null) {
            if (tryParseDouble(txtBoxBolStraal.getText())) {
                double n = Double.parseDouble(txtBoxBolStraal.getText());
                Sphere sphere = new Sphere(n);
                lblBolAnswer.setText("" + sphere.getVolume());
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
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    void createMenubar() {
        menu = new JMenu("Menu");
        saveSubMenu = new JMenu("Save");
        saveMenuItem = new JMenuItem("Save in file");
        saveMenuJson = new JMenuItem("Save in Json");

        saveInDBMenuITem = new JMenuItem("Save in database");

        saveSubMenu.add(saveMenuItem);
        saveSubMenu.add(saveMenuJson);
        saveSubMenu.add(saveInDBMenuITem);

        menu.add(saveSubMenu);

        loadSubMenu = new JMenu("Load");
        loadMenuItem = new JMenuItem("Load from file");
        loadMenuJson = new JMenuItem("Load from Json");
        loadFromDBMenuITem = new JMenuItem("Load from databse");


        loadSubMenu.add(loadMenuItem);
        loadSubMenu.add(loadMenuJson);
        loadSubMenu.add(loadFromDBMenuITem);
        menu.add(loadSubMenu);


        mb = new JMenuBar();
        mb.add(menu);
    }

    void loadFromDB() {

        historyListItems.clear();
        historyListItems.addAll(Cube.GetCubesFromDB());
        historyListItems.addAll(Sphere.GetCylinderFromDB());
        historyListItems.addAll(Cylinder.GetCylinderFromDB());
        HistoryList.setListData(historyListItems);
    }

    void saveOnDB() {
        for (IShape i : historyListItems) {
            i.saveOnDB();
        }
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
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateCube();
            }
        });
        loadFromDBMenuITem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadFromDB();
            }
        });
        saveInDBMenuITem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveOnDB();
            }
        });
        HistoryList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                JList source = (JList)e.getSource();
                IShape selected =(IShape)source.getSelectedValue();



                if (selected instanceof Cube) {
                    TabMain.setSelectedIndex(1);
                    Cube cb = (Cube) selected;
                    txtBlokHoogte.setText(cb.getHeight()+"");
                    txtBlokBreede.setText(cb.getWidth()+"");
                    txtboxBlokLengte.setText(cb.getLength()+"");
                    lblBlokAnswer.setText(cb.getVolume()+"");

                } else if (selected instanceof Cylinder) {
                    TabMain.setSelectedIndex(2);
                    Cylinder cl = (Cylinder)selected;
                    txtboxCylinderHoogte.setText(cl.getHeight()+"");
                    txtboxCylinderStraal.setText(cl.getRadius()+"");
                    lblCylinderAntwoord.setText(cl.getVolume()+"");

                } else if (selected instanceof Sphere) {
                    TabMain.setSelectedIndex(0);
                    Sphere sp = (Sphere)selected;
                    txtBoxBolStraal.setText(sp.getRadius()+"");
                    lblBolAnswer.setText(sp.getVolume()+"");
                }
            }
        });

    }


}

