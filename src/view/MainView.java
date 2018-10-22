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
    private JTextField txtBlokHoogte, txtboxBlokLengte, txtBlokBreede, txtboxCylinderStraal, txtboxCylinderHoogte;
    private JLabel lblBolAnswer, lblBolStraal, lblBlokHoogte, lblBlokLengte, lblBlokBreede, lblBlokAnswer, lblCylinderHoogte, lblCylinderStraal, lblCylinderAntwoord;
    private JFormattedTextField txtBoxBolStraal;
    private JPanel bolPane;
    private JButton calculateButton, button1, button2;
    private JPanel j1, blokPanel, cilinderPanel;
    private JTextField txtTranConeRadius1;
    private JTextField txtTranConeRadius2;
    private JTextField txtHollowCylRadius1;
    private JTextField txtHollowCylRadius2;
    private JTextField txtHollowCylHeight;
    private JTextField txtTranConeHeight;
    private JButton BtnTranculatedCone;
    private JButton btnHollowCylinder;
    private JLabel lblAnswerHollowCylinder;
    private JLabel lblAnswerTranculatedCone;

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
        historyListItems.addAll(Cube.getCubesFromDB());
        historyListItems.addAll(Sphere.getCylinderFromDB());
        historyListItems.addAll(Cylinder.getCylinderFromDB());
        historyListItems.addAll(TruncatedCone.getTruncatedConeFromDB());
        historyListItems.addAll(HollowCylinder.getCylinderFromDB());
        HistoryList.setListData(historyListItems);
    }

    void loadFromText() {
        historyListItems.clear();
        TextFile file = new TextFile();
        historyListItems.addAll(file.loadFile());
        HistoryList.setListData(historyListItems);
    }

    void loadFromJSON() {
        historyListItems.clear();
        JSONFile file = new JSONFile();
        historyListItems.addAll(file.loadFile());
        HistoryList.setListData(historyListItems);
    }

    void loadFromText(){
        historyListItems.clear();
        TextFile file = new TextFile();
        historyListItems.addAll(file.loadFile());
        HistoryList.setListData(historyListItems);
    }

    void loadFromJSON(){
        historyListItems.clear();
        JSONFile file = new JSONFile();
        historyListItems.addAll(file.loadFile());
        HistoryList.setListData(historyListItems);
    }

    void saveOnDB() {
        for (IShape i : historyListItems) {
            i.saveOnDB();
        }
    }

    void saveAsText(){
        for (IShape i : historyListItems){
            i.saveAsText();
        }
    }
    void saveAsJson(){
        for (IShape i : historyListItems){
            i.saveAsJson();
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

        BtnTranculatedCone.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateTranculatedCone();
            }
        });
        btnHollowCylinder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateHollowCylinder();
            }
        });
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
        saveMenuJson.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveAsJson();
            }
        });
        saveMenuItem.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveAsText();
            }
        });

        loadMenuItem.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadFromText();
            }
        });

        loadMenuJson.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadFromJSON();
            }
        });
        HistoryList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                JList source = (JList) e.getSource();
                IShape selected = (IShape) source.getSelectedValue();



                if (selected instanceof Cube) {
                    TabMain.setSelectedIndex(1);
                    Cube cb = (Cube) selected;
                    txtBlokHoogte.setText(cb.getHeight() + "");
                    txtBlokBreede.setText(cb.getWidth() + "");
                    txtboxBlokLengte.setText(cb.getLength() + "");
                    lblBlokAnswer.setText(cb.getVolume() + "");

                } else if (selected instanceof Cylinder) {
                    TabMain.setSelectedIndex(2);
                    Cylinder cl = (Cylinder) selected;
                    txtboxCylinderHoogte.setText(cl.getHeight() + "");
                    txtboxCylinderStraal.setText(cl.getRadius() + "");
                    lblCylinderAntwoord.setText(cl.getVolume() + "");

                } else if (selected instanceof Sphere) {
                    TabMain.setSelectedIndex(0);
                    Sphere sp = (Sphere) selected;
                    txtBoxBolStraal.setText(sp.getRadius() + "");
                    lblBolAnswer.setText(sp.getVolume() + "");
                }
            }
        });

    }


}

