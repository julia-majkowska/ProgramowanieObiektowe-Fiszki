import java.util.*;
import java.io.*;

public class FieldData implements Serializable
{
    public String typ = "TEKST";
    public String nazwa = "nazwa";
    public FieldData(){}
    public FieldData(String t, String n) {typ = t; nazwa = n;}
}