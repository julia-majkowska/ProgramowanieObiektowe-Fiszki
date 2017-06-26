import java.util.*;
import java.io.*;

public class FieldData implements Serializable
{
    public String typ = "TEXT";
    public String nazwa = "name";
    public FieldData(){}
    public FieldData(String t, String n) {typ = t; nazwa = n;}
}