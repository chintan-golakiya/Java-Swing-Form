package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class Toolbar extends JToolBar implements ActionListener {

    private JButton helloButton;
    private JButton goodbyeButton;
    private StringListener textListener;

    public Toolbar(){
        helloButton = new JButton();
        goodbyeButton = new JButton();

        helloButton.setIcon(createIcon("/images/helloicon.png"));
        goodbyeButton.setIcon(createIcon("/images/helloicon.png"));

        helloButton.addActionListener(this);
        goodbyeButton.addActionListener(this);

        add(helloButton);
        add(goodbyeButton);
    }

    public void setStringListener(StringListener listener)
    {
        this.textListener = listener;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JButton Clicked = (JButton) actionEvent.getSource();

        if(Clicked==helloButton)
        {
            if(textListener !=null)
            {
                textListener.textEmitted("Hello\n");
            }
        }
        else if(Clicked == goodbyeButton)
        {
            if(textListener != null)
            {
                textListener.textEmitted("Goodbye\n");
            }
        }
    }

    private ImageIcon createIcon(String path)
    {
        URL url = getClass().getResource(path);
        ImageIcon image = new ImageIcon(url);
        return image;
    }
}
