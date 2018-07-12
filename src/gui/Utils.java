package gui;

public class Utils {

    public static String getFileExtension(String name)
    {
        int pointIndex = name.lastIndexOf(".");
        if(pointIndex==-1 || pointIndex == name.length()-1)
            return null;
        else return name.substring(pointIndex+1);
    }
}
