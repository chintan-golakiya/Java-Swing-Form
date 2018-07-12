package gui;

import model.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class TablePanel extends JPanel {
    private JTable table;
    private PersonTableModel tableModel;
    private JPopupMenu popup;
    private PersonTableListener personTableListener;

    public TablePanel()
    {
        tableModel = new PersonTableModel();
        table = new JTable(tableModel);

        popup = new JPopupMenu();

        JMenuItem deleteRow = new JMenuItem("Delete Row");
        popup.add(deleteRow);

        table.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                int row = table.rowAtPoint(mouseEvent.getPoint());
                table.getSelectionModel().setSelectionInterval(row,row);
                if(mouseEvent.getButton()==MouseEvent.BUTTON3)
                {
                    popup.show(table,mouseEvent.getX(),mouseEvent.getY());
                }
            }
        });

        deleteRow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int row = table.getSelectedRow();
                personTableListener.rowDeleted(row);
                tableModel.fireTableRowsDeleted(row,row);
            }
        });
        setLayout(new BorderLayout());
        add(new JScrollPane(table),BorderLayout.CENTER);
    }

    public void setData(List<Person> db)
    {
        tableModel.setData(db);
    }

    public void refresh()
    {
        tableModel.fireTableDataChanged();
    }

    public void setPersonTableListener(PersonTableListener ptl)
    {
        this.personTableListener = ptl;
    }
}
