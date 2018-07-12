import model.AgeCategory;
import model.Database;

public class Testdatabase {
    public static void main(String []args)
    {


        Database db = new Database();
        try{
            db.connect();
            db.count();
            db.disconnect();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
