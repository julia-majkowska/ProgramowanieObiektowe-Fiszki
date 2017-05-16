class Lista implements Serializable
{
    ArrayList<Fiszka> Fiszki;
    Lista();
    void AddFiszka();
    void RemoveFiszka();

}

class Fiszka implements Serializable
{
    int number_of_fields;
    Field [] pola;
    Fiszka();
}

interface DodajSlowka
{
    Lista get_list();
    void save_list();
}

class DodajSlowkaIO implements DodajSlowka
{
    ObjectInputStream StrumienWejscia; 
    ObjectOutputStream StrumienWyjscia;
    Lista get_list();
    void save_list();
}

class DodajSlowkaOkienko implements DodajSlowka
{
    Okienko;
    Lista get_list();
    void save_list(); //otwiera okienko do wyboru pliku wyjscia i zapisuje do pliku wybranego
    void load_list_from_file() //otwiera okienko do wyboru pliku wyjscia i wczytuje do pliku wybranego
}