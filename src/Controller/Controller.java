package Controller;

import gui.FormEvent;
import model.*;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

public class Controller {

    Database db = new Database();

    public Controller()
    {
        try {
            db.connect();
            db.getFromDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addPerson(FormEvent ev)
    {

        Person temp = new Person();

        temp.setName(ev.getName());
        temp.setAgeCategory(ev.getAgeCatgory());
        temp.setUID(ev.getUID());
        temp.setIndian(ev.isIndian());
        temp.setGender(ev.getGender());
        temp.setOccupation(ev.getOccupation());
        temp.setEmployment(ev.getEmployment());
        db.addPersonToDatabase(temp);

    }

    public List<Person> getPeople()
    {
        return db.getPerson();
    }

    public void saveToFile(File file) throws Exception
    {
        db.SaveToFile(file);
    }
    public void loadFromFile(File file) throws Exception
    {
        db.LoadFromFile(file);
    }

    public void removeRow(int index)
    {
        db.removeRow(index);
    }
}
