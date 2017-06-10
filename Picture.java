import java.io.*;
public class Picture extends Field
{
    File source; 

    public Picture(File f){
        source = f;
    }

    public Picture(String path)
    {
        source = new File(path);
    }

    public File get(){return source;}

    public String toString(){
        try
        {
            return "Obraz" + source.getCanonicalPath()+ "\n";
        }
        catch(Exception e){}
        return "";
    }
}