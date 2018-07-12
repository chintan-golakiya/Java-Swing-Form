package gui;

import javax.swing.*;
import java.awt.*;


public class TextPanel extends JPanel {
    private JTextArea textArea;


    public TextPanel(){
        textArea = new JTextArea();
        setLayout(new BorderLayout());

        add(new JScrollPane(textArea),BorderLayout.CENTER);
    }

    void append(String str)
    {
        textArea.append(str);
    }
}
