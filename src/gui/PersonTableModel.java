package gui;

import model.Person;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class PersonTableModel extends AbstractTableModel {

    private List<Person> db;

    private String[] ColsName = {"Id","Name","Occupation","AgeCategory","Employment","Indian","UID","Gender"};

    public void setData(List<Person> db)
    {
        this.db = db;
    }

    @Override
    public String getColumnName(int i) {
        return ColsName[i];
    }

    @Override
    public int getRowCount() {
        return db.size();
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public Object getValueAt(int row, int column) {
        Person per = db.get(row);

        switch(column)
        {
            case 0:
                return per.getId();
            case 1:
                return per.getName();
            case 2:
                return per.getOccupation();
            case 3:
                return per.getAgeCategory();
            case 4:
                return per.getEmployment();
            case 5:
                return per.isIndian();
            case 6:
                return per.getUID();
            case 7:
                return per.getGender();
        }
        return null;
    }
}
