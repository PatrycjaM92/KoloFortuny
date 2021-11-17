

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Klasa, ktorej instancja sa obiekty, bedace graczami.
 * Rozszerza Klase Kolo w celu umozliwienia losowania przez danego Gracza kwoty na kole z Metody losujacej z Klasy Kolo
 */
public class Gracz extends Kolo {

    private String imie;
    private int aktualnyWynik;
    private int calkowityWynik;
    String czyOdgadniete;
    static boolean czyZgaduje;
    Scanner sc= new Scanner(System.in);

    /**
     * Konstruktor ustawiajacy wyniki na wartosc 0
     */
    public Gracz(){
        aktualnyWynik =0;
        calkowityWynik =0;
    }

    /**
     * Metoda zwracajaca wynik aktualny w danej rundzie
     * @return zwraca wynik aktualny w danej rundzie
     */
    public int getAktualnyWynik() {
        return aktualnyWynik;
    }

    /**
     * Metoda udostepniajaca imie gracza
     * @return zwraca imie Gracza
     */
    public String getImie() {
        return imie;
    }

    /**
     * Metoda zwracajaca wynik po zakonczonych rundach.
     * @return zwraca wynik, jesli gracz wygral dana runde
     */
    public int getCalkowityWynik() {
        return calkowityWynik;
    }

    /**
     * Metoda ustanawiajaca imie gracza
     * @param imie Imie wpisane przez gracza do zmiennej String
     */
    public void setImie(String imie) {
        this.imie = imie;
    }

    /**
     * Metoda Ustanawiajaca wynik, jesli gracz wylosuje kwote rozna od 0 i prawidlowo wytypuje litere.
     * @param AktualnyWynik parametrem jest pole danych aktualny wynik, w ktorym zapisane sa kwoty w danym momencie w danej rundzie
     */
    public void setAktualnyWynik(int AktualnyWynik){

        if(AktualnyWynik==0) this.aktualnyWynik =0; // Gdy gracz wylosuje '0', czyli Bankruta, traci zdobyte w danej rundzie pieniądze
        else this.aktualnyWynik +=AktualnyWynik;// w przypadku, gdy wylosowana kwota jest dodatnia, następuje sumowanie tej kwoty do zdobytej wcześniej w danym momencie gry.

    }

    /**
     * Metoda ustanawiajaca wynik w przypadku, gdy gracz prawidlowo odgadnie haslo.
     * @param CalkowityWynik Parametrem jest pole danych, ktore przechowuje wynik zebrany po wszystkich wygranych rundach
     */
    public void setCalkowityWynik(int CalkowityWynik){
        this.calkowityWynik +=CalkowityWynik;
    }

    /**
     * Metoda pokazujaca stan wynikow.
     */
    public void pokazWyniki(){
        System.out.println("\nWyniki gry przedstawiają się następująco: ");
        System.out.println(imie+" ma na koncie: "+ calkowityWynik+"zł");


    }

    /**
     *  Metoda zwracajaca 'true' lub 'false' w zaleznosci czy gracz prawidlowo odgadnal haslo.
     * @param zagad obiekt, ktory jest wylosowana kategoria  wraz z haslem
     * @return Prawda - jesli gracz prawidlowo odgadnal haslo oraz Falsz - jesli haslo wytypowane przez gracza jest inne niz wylosowane
     */

    public boolean zgaduje(Zagadnienia zagad){

        System.out.print("\nPodaj Hasło: ");
        Scanner scan= new Scanner(System.in);
        czyOdgadniete = scan.nextLine();

        if(czyOdgadniete.toUpperCase().equals(zagad.wylosowaneHaslo)){


            System.out.println("\n\nGratulacje!!!!");
            return true;

        }
        else {
            System.out.println("\nNiestety to nie to hasło");
            return false;

        }

    }

    /**
     *  Metoda zapytujaca gracza o probe odgadniecia hasla.
     * Zmienia wartosc pola statycznego CzyZgaduje w zaleznosci, czy gracz chce odgadnac haslo, czy tez nie.
     */

    public void czyOdgadnacHaslo(){
        System.out.print("\n\nCzy chcesz odgadnac hasło? Jeśli tak wciśnij 1. Jeśli nie wciśnij 0.");
        int czyOdgadnac;
        try {
            czyOdgadnac = sc.nextInt();
            if(czyOdgadnac==1){
                czyZgaduje=true;
            }
            else czyZgaduje=false;
        }catch (InputMismatchException e){
            System.out.println("Nie możesz użyć innych znaków niż liczba "+e);

        }


    }

}

