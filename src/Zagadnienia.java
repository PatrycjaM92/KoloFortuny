

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Klasa 'Zagadnienia' tworzy obiekt w danej rundzie. Obiektem jest wylosowane z tekstowego pliku haslo wraz z kategoria.
 */

public class Zagadnienia {

    ArrayList<String> plik;
    ArrayList<String> kategoria;
    ArrayList<String> hasla;

    private int WylosowanyIndeks;
    String wylosowaneHaslo;
    ArrayList<Character> wylosowane;
    Random rand =new Random();
    ArrayList<Character> ukryteHaslo;

    /**
     * Konstruktor tworzacy obiekt, zawierajacy wylosowana liste kategorii, hasla oraz hasla ukrytego pod znakiem podkreslenia
     */
    public Zagadnienia(){
        plik= new ArrayList<>(); // utworzenie listy, w której mają znajdować sie kategorie i hasla z pliku tekstowego
        // wiersze stanowią kolejno kategorię oraz hasło
        try{
            Scanner sc =new Scanner(Paths.get("zagadnienia.txt"));
            while (sc.hasNextLine()){
                plik.add(sc.nextLine()); //dodanie do listy zawartości pliku tekstowego
            }
        }catch (Exception e){
            System.out.println("Błąd pliku "+e);
        }

        kategoria = new ArrayList<>(); //lista z kategoriami
        hasla = new ArrayList<>();    // lista z hasłami

        for(int i=0;i< plik.size();i++){
            if(i%2==0){
                kategoria.add(plik.get(i));  // dodanie do listy kategorii parzyste linie pliku, którymi sa kategorie haseł
            }
            else {
                hasla.add(plik.get(i));     // dodanie do listy haseł nieparzystych linii pliku, którymi są hasła
            }
        }

        WylosowanyIndeks = rand.nextInt(kategoria.size()); //wylosowanie indeksu z listy kategorii
        wylosowaneHaslo= hasla.get(WylosowanyIndeks); //przypisanie do zmiennej hasła z listy haseł po wylosowaniu indeksu
        wylosowane = new ArrayList<>(); // utworzenie nowej listy, w której będą się znajdować litery z wylosowanego hasła
        for(int i=0;i<wylosowaneHaslo.length();i++){
            wylosowane.add(wylosowaneHaslo.charAt(i)); // dodanie do listy znakowej pojedynczych liter z których złożone jest hasło
        }

        //ukrycie liter pod znakiem "_"
        ukryteHaslo = new ArrayList<>();
        for(int i=0;i<wylosowane.size();i++){
            if(wylosowane.get(i)==' '){ // jeśli w haśle są spacje w ukrytym również maja być
                ukryteHaslo.add(' ');
            }
            else ukryteHaslo.add('_');  //zmiana liter na znak '_'
        }


    }

    /**
     * Metoda pokazujaca wylosowana kategorie
     */
    public void pokazKategorie(){
        System.out.println("\n\t\t\t\t\t\tKategoria hasła");
        System.out.println("\n\t\t\t\t\t\t"+kategoria.get(WylosowanyIndeks));
    }

    /**
     *  Metoda pokazujaca ukryte haslo
     */
    public void pokazUkryteHaslo(){
        System.out.print("\n\n\t\t\t\t");
        for(Character letter:ukryteHaslo){
            System.out.print(letter+" ");
        }
        System.out.println("\n\n");
    }

    /**
     * metoda odpowiedzialna za odgadywanie liter w hasle
     * @return zwraca Prawde, jesli litera podana przez gracz wystepuje w hasle lub Falsz, jesli jej nie ma
     */
    public boolean odgadywanie(){
        Scanner sc = new Scanner(System.in);
        System.out.print("\t\t\t\t\t\tPodaj litere: ");
        String literaStr=sc.next();  //wprowadzenie przez użytkownika litery do zmiennej typu String
        Character litera =literaStr.toUpperCase().charAt(0); // przypisanie litery do zmiennej char jako dużą literę, w celu porównania z literą zapisaną w pliku tekstowym

        // dzięki czemu gracz może podać małą lub dużą literę
        boolean jestLitera=false;// zmienna wskazująca, czy w podana przez gracza litera występuje w haśle.

        //pętla iterująca po wszystkich literach i sprawdzająca, czy podana przez gracza litera występuje w haśle. Jeśli tak, to zmienna 'jestLitera' przyjmuje wartość 'true'

        for(int i=0; i <wylosowane.size();i++){
            if(wylosowane.get(i).equals(litera)){
                ukryteHaslo.set(i,litera);
                jestLitera=true;
            }
        }
        System.out.println("\n\n");
        pokazKategorie();
        System.out.print("\n\n\t\t\t\t");

        // pokazanie stanu odgadnięcia hasła po podaniu przez gracza litery.
        for (Character liter:ukryteHaslo) System.out.print(liter+" ");
        System.out.println("\n\n\n");

        // w przypadku braku litery metoda zwraca 'false'
        if(!jestLitera) {
            System.out.println("\nBrak litery");
            jestLitera=false;
            return false;
        }

        else return true;






    }




}
