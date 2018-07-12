package model;

import java.io.Serializable;

public class Person implements Serializable{



    private int id;
    private String name;
    private String occupation;
    private AgeCategory AgeCategory;
    private Employment employment;
    private boolean Indian;
    private String UID;
    private Gender gender;

    public Person(int id,String name, String occupation, model.AgeCategory ageCategory, Employment employment, boolean indian, String UID, Gender gender) {

        this.id = id;

        this.name = name;
        this.occupation = occupation;
        AgeCategory = ageCategory;
        this.employment = employment;
        Indian = indian;
        this.UID = UID;
        this.gender = gender;
    }

    public Person(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public model.AgeCategory getAgeCategory() {
        return AgeCategory;
    }

    public void setAgeCategory(model.AgeCategory ageCategory) {
        AgeCategory = ageCategory;
    }

    public void setAgeCategory(String str){
        if(str.equals("child")) this.AgeCategory = model.AgeCategory.child;
        else if(str.equals("adult"))this.AgeCategory = model.AgeCategory.adult;
        else AgeCategory = model.AgeCategory.senior;
    }
    public void setAgeCategory(int str){
        if(str==0) this.AgeCategory = model.AgeCategory.child;
        else if(str==1)this.AgeCategory = model.AgeCategory.adult;
        else AgeCategory = model.AgeCategory.senior;
    }

    public Employment getEmployment() {
        return employment;
    }

    public void setEmployment(Employment employment) {
        this.employment = employment;
    }
    public void setEmployment(String str)
    {
        if (str.equals("employed")) this.employment = Employment.employed;
        else if(str.equals("selfemployed")) this.employment = Employment.selfemployed;
        else this.employment = Employment.unemployed;
    }

    public boolean isIndian() {
        return Indian;
    }

    public void setIndian(boolean indian) {
        Indian = indian;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setGender(String str)
    {
        if(str.equals("male"))gender = Gender.male;
        else gender = Gender.female;
    }
}
