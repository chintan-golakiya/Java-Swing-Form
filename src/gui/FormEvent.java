package gui;

import java.util.EventObject;

public class FormEvent extends EventObject {
    private String name;
    private String occupation;
    private int AgeCategory;
    private String employment;
    private boolean Indian;
    private String UID;
    private String gender;
    public FormEvent(Object source)
    {
        super(source);
    }

    public FormEvent(Object source,String name,String occupation,int AgeCat,String Employment,boolean isIndian,String UID,String gender)
    {
        super(source);
        this.name = name;
        this.occupation = occupation;
        this.AgeCategory = AgeCat;
        this.employment = Employment;
        this.Indian = isIndian;
        this.UID = UID;
        this.gender = gender;
    }

    public String getGender()
    {
        return gender;
    }

    public String getName()
    {
        return name;
    }

    public String getOccupation()
    {
        return occupation;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setOccupation(String occupation)
    {
        this.occupation = occupation;
    }

    public int getAgeCatgory(){ return AgeCategory;}

    public String getEmployment(){ return employment;}

    public boolean isIndian() {
        return Indian;
    }

    public String getUID()
    {
        return UID;
    }
}
