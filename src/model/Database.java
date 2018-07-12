package model;


import java.io.*;
import java.sql.*;
import java.util.*;

public class Database {
    private List<Person> people;
    private Connection con;

    public Database() {
        people = new LinkedList<Person>();
    }

    private void addPerson(Person person) {
        people.add(person);
    }

    public void removeRow(int index){
        Person p = people.get(index);
        people.remove(index);
        removeFromDatabase(p.getId());
    }

    public List<Person> getPerson() {
        return Collections.unmodifiableList(people);
    }

    public void SaveToFile(File file) throws IOException
    {
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        Person[] persons = people.toArray(new Person[people.size()]);

        oos.writeObject(persons);
        oos.close();
    }

    public void LoadFromFile(File file) throws Exception
    {
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);

        Person[] persons  = (Person[]) ois.readObject();
        people.clear();
        people.addAll(Arrays.asList(persons));
    }

    public void connect() throws SQLException {
        if(con!=null) return ;

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String url = "jdbc:mysql://localhost:3306/swing";

        con = DriverManager.getConnection(url,"root","5nake>Rat");

    }
    public void disconnect(){
        if(con!=null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void count() throws SQLException
    {
        String sql = "SELECT count(*) from people";
        PreparedStatement sqlStmt = con.prepareStatement(sql);
        ResultSet checkResult = sqlStmt.executeQuery();

        checkResult.next();
        int count = checkResult.getInt(1);

        System.out.println("Count for person is "+count+".");

        sqlStmt.close();
    }

    public void addPersonToDatabase(Person person)
    {
        String name = person.getName();
        String age = person.getAgeCategory().toString();
        String taxid = person.getUID();
        Boolean indian = person.isIndian();
        String gender = person.getGender().toString();
        String occupation = person.getOccupation();
        String empCat = person.getEmployment().toString();

        String Sql = "Insert into people(name,age,taxid,indian,gender,occupation,empCat) values ('"+name+"','"+age+"','"+taxid+"',"+indian+",'"+gender+"','"+occupation+"','"+empCat+"')";

        try {
            PreparedStatement preparedStatement = con.prepareStatement(Sql);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        getFromDatabase();

    }

    public void getFromDatabase()
    {
        people.clear();

        String sql = "Select * from people";

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet set = stmt.executeQuery();
            while(set.next())
            {
                Person temp = new Person();

                temp.setId(set.getInt("id"));
                temp.setName(set.getString("name"));
                temp.setAgeCategory(set.getString("age"));
                temp.setUID(set.getString("taxid"));
                temp.setIndian(set.getBoolean("indian"));
                temp.setGender(set.getString("gender"));
                temp.setOccupation(set.getString("occupation"));
                temp.setEmployment(set.getString("empCat"));

                addPerson(temp);

            }

            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void removeFromDatabase(int id)
    {
        String sql = "Delete from people where id = ?";

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1,id);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
