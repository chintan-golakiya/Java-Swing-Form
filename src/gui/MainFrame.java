package gui;

import Controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.prefs.Preferences;


public class MainFrame extends JFrame implements StringListener{

    private TextPanel textPanel;
    private Toolbar toolbar;
    private FormPanel formPanel;
    private JFileChooser fileChooser;
    private Controller controller;
    private TablePanel tablePanel;
    private PreferencesDialog prefDialog;
    private Preferences prefs;

    public MainFrame(){
        super("Java Swing Form");


        textPanel = new TextPanel();
        toolbar = new Toolbar();
        formPanel = new FormPanel();
        fileChooser = new JFileChooser();
        controller = new Controller();
        tablePanel = new TablePanel();
        prefDialog = new PreferencesDialog(this);

        prefs = Preferences.userRoot().node("db");

        tablePanel.setPersonTableListener(new PersonTableListener() {
            public void rowDeleted(int row)
            {
                controller.removeRow(row);
            }
        });

        fileChooser.addChoosableFileFilter(new PersonFileFilter());
        tablePanel.setData(controller.getPeople());

        setLayout(new BorderLayout());
        setJMenuBar(createMenuBar());
        toolbar.setStringListener(this);
        formPanel.setFormListener(new FormListener()
        {
            public void formEventOccured(FormEvent e)
            {
                controller.addPerson(e);
                tablePanel.refresh();
            }
        });

        prefDialog.setPrefsListener(new PrefsListener() {
            @Override
            public void preferencesSet(String user, String password, int port) {
                prefs.put("user",user);
                prefs.put("password",password);
                prefs.putInt("port",port);
            }
        });

        String user = prefs.get("user","Localhost");
        String password = prefs.get("password","");
        int port = prefs.getInt("port",3306);

        prefDialog.setDefaults(user,password,port);

        add(formPanel,BorderLayout.WEST);
       // add(toolbar,BorderLayout.PAGE_START);
        add(tablePanel,BorderLayout.CENTER);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(700,400));
        setSize(800,500);
    }

    @Override
    public void textEmitted(String text)
    {
        textPanel.append(text);
    }

    private JMenuBar createMenuBar()
    {
        JMenuBar menubar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenu windowMenu = new JMenu("Window");

        JMenuItem exportDataItem = new JMenuItem("Export Data ...");
        JMenuItem importDataItem = new JMenuItem("Import Data ...");
        JMenuItem exitItem = new JMenuItem("Exit");

        fileMenu.add(exportDataItem);
        fileMenu.add(importDataItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        JMenu ShowMenu = new JMenu("Show ");
        JMenuItem prefMenu = new JMenuItem("Preferences");
        JCheckBoxMenuItem showFormItem = new JCheckBoxMenuItem("Show Form ");
        showFormItem.setSelected(true);
        ShowMenu.add(showFormItem);
        windowMenu.add(ShowMenu);
        windowMenu.add(prefMenu);


        showFormItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JCheckBoxMenuItem menuItem =(JCheckBoxMenuItem) actionEvent.getSource();
                formPanel.setVisible(menuItem.isSelected());
            }
        });

        menubar.add(fileMenu);
        menubar.add(windowMenu);

        fileMenu.setMnemonic(KeyEvent.VK_F);
        exitItem.setMnemonic(KeyEvent.VK_X);
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK));
        importDataItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,ActionEvent.CTRL_MASK));

        importDataItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(fileChooser.showOpenDialog(MainFrame.this)==JFileChooser.APPROVE_OPTION)
                {
                    try {
                        controller.loadFromFile(fileChooser.getSelectedFile());
                        tablePanel.refresh();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(MainFrame.this,"Could not load file","Error",JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });

        exportDataItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent){
                if(fileChooser.showSaveDialog(MainFrame.this)==JFileChooser.APPROVE_OPTION)
                {
                    try {
                        controller.saveToFile(fileChooser.getSelectedFile());
                        tablePanel.refresh();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(MainFrame.this,"Could not save file","Error",JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });

        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                int action =JOptionPane.showConfirmDialog(MainFrame.this,"Do you Really want to exit the application?","Confirm Exit",JOptionPane.OK_CANCEL_OPTION);

                if(action==JOptionPane.OK_OPTION)
                    System.exit(0);
            }
        });

        prefMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                prefDialog.setVisible(true);
            }
        });
        return menubar;
    }

}
