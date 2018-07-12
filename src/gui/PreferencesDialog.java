package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PreferencesDialog extends JDialog {

    private JButton okbtn;
    private JButton cancelbtn;
    private JSpinner spinner;
    private SpinnerNumberModel spinnerNumberModel;
    private JTextField userField;
    private JPasswordField passField;
    private PrefsListener prefsListener;

    public PreferencesDialog(JFrame parent)
    {
        super(parent,"Preferences",true);
        setSize(400,300);

        okbtn = new JButton(" OK ");
        cancelbtn = new JButton( "cancel");
        userField = new JTextField(10);
        passField = new JPasswordField(10);

        passField.setEchoChar('*');

        spinnerNumberModel = new SpinnerNumberModel(3306,0,9999,1);
        spinner = new JSpinner(spinnerNumberModel);

        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();
        gc.weightx =1;
        gc.weighty =1;
        gc.fill = GridBagConstraints.NONE;

        gc.gridy =0;

        gc.gridx =0;
        add(new JLabel("Port : "),gc);

        gc.gridx++;
        add(spinner,gc);

        // Second Row
        gc.gridy++;

        gc.gridx = 0;
        add(new JLabel("User :"),gc);

        gc.gridx++;
        add(userField,gc);
        //Third Row
        gc.gridy++;

        gc.gridx = 0;
        add(new JLabel("Password :"),gc);

        gc.gridx++;
        add(passField,gc);
        //Forth Row
        gc.gridy++;

        gc.gridx = 0;
        add(okbtn,gc);

        gc.gridx++;
        add(cancelbtn,gc);

        okbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Integer port = (Integer)spinner.getValue();
                String user = userField.getText();
                char[] password = passField.getPassword();
                String pass =new String(passField.getPassword());

                if(prefsListener!=null)
                {
                    prefsListener.preferencesSet(user,pass,port);
                }

                setVisible(false);
            }
        });

        cancelbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setVisible(false);
            }
        });

        setLocationRelativeTo(parent);

    }

    public void setPrefsListener(PrefsListener listener)
    {
        this.prefsListener = listener;
    }

    public void setDefaults(String user,String password,int port)
    {
        userField.setText(user);
        passField.setText(password);
        spinner.setValue(port);
    }
}
