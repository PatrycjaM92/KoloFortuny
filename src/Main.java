

//import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        System.out.println( "\t\t\t\t\t\t\t****************************************************\n" +
                "\t\t\t\t\t\t\t*                                                  *\n" +
                "\t\t\t\t\t\t\t*            WITAMY W KOLE FORTUNY!!!              *\n" +
                "\t\t\t\t\t\t\t*                                                  *\n" +
                "\t\t\t\t\t\t\t****************************************************\n");


        Scanner sc =new Scanner(System.in);
        String zakoncz="w";
        Character zakonczChar='r';

        int wybor ;
        Gracz g1 = new Gracz();
        Gracz g2 = new Gracz();
        String imie, rozpocznij;

        //Pętla główna wykonująca się do momentu, gdy gracz wciśnie literę 'z'
        while (zakonczChar != 'z') {

            System.out.println("\nMenu:\n1.Instrukcja gry\n2.Nowa Gra\n3.Sprawdź punkty\n4.Zakończ\n\n");
            System.out.println("Wybierz 1, 2, 3 lub 4: ");

            wybor = sc.nextInt();
            switch(wybor){  //Instrukcja wyboru z menu
                case 1: //Instrukcja gry
                    System.out.println("Gra polega na odgadywaniu liter z hasła z zadanej kategorii."+
                            "\nPo wylosowaniu kwoty za poprawną literę, która występuje w haśle"+
                            "\ngracz zdobywa daną kwotę. Jeśli nastapi wylosowanie Bankruta, "+
                            "\ngracz traci wszystkie pieniądze zdobyte w danej rundzie oraz traci kolejkę."+
                            "\nUtrata kolejki następuje rownież po podaniu litery, która nie występuje w haśle"+
                            "\nW każdej chwili gracz ma możliwość podania rozwiązania hasła, Wówczas cała zgromadzona"+
                            "\nkwota w danej rundzie przechodzi na jego konto.");
                    break;
                case 2: // Wprowadzenie imion graczów oraz rozpoczęcie gry
                    System.out.print("Podaj imie gracza pierwszego:  ");
                    imie=sc.next();
                    g1.setImie(imie);
                    System.out.print("\nPodaj imie gracza drugiego: ");
                    imie = sc.next();
                    g2.setImie(imie);
                    System.out.print("\nWciśnij 'r', aby rozpocząć grę ");
                    rozpocznij=sc.next();
                    graStart(g1,g2);



                case 3:// przedstawienie salda graczów
                    System.out.println("Gracz "+g1.getImie()+" ma na swoim koncie: "+g1.getCalkowityWynik());
                    System.out.println("\nGracz "+g2.getImie()+" ma na swoim koncie: "+g2.getCalkowityWynik());
                    break;

                case 4: // zakończenie gry
                    System.out.println("\nAby zakończyć grę wciśnij 'z'.");
                    zakoncz=sc.next();
                    zakonczChar= zakoncz.toLowerCase().charAt(0);



            }



        }

    }

    /**
     * Metoda sterujaca glowna czescia gry.
     * @param g1 instancja Klasy Gracz, bedaca graczem nr 1
     * @param g2 instancja Klasy Gracz, bedaca graczem nr 2
     * @throws InterruptedException Wyjatek obslugujacy przerwanie programu, wykorzystany w metodzie obslugujacej wyswietlanie losowanych liczb
     */
    public static void graStart(Gracz g1, Gracz g2) throws InterruptedException {

        System.out.println("\n\n\t\t\t\t\t\tRozpoczynamy grę!!!\n\n");
        int runda=1;
        int graGraczNr=1; // zmienna pełniąca rolę przełącznika pomiędzy graczami
        int wylosowanaKwota; // zmienna przechowująca zwróconą(wylosowaną) przez metodę kwotę.

        while(runda<6){ //pętla wykonująca się 5 razy, ponieważ jest 5 rund
            System.out.println("\n\tRunda "+runda+"\n\n");
            boolean czyZakonczonaRunda = false;
            Zagadnienia z1 = new Zagadnienia(); // utworzenie obiektu z zagadnieniem
            z1.pokazKategorie();
            z1.pokazUkryteHaslo();

            //pętla wykonująca sie do momentu, gdy gracz odgadnie hasło.
            do {



                if (graGraczNr == 1) {

                    while(true){ // przerwanie pętli następuje w momencie, gdy gracz wylosuje bankruta lub nie odgadnie litery
                        //wówczas nastąpi przypisanie do zmiennej 'graGraczNr' innego numeru i wykona się instrukcja, która spełni ten warunek

                        System.out.println("\n\nKolej gracza: "+g1.getImie());
                        wylosowanaKwota= g1.getWylosowana();// przypisanie do zmiennej wylosowanej kwoty z metody z Klasy Koło.

                        //Przerwanie pętli w momencie wylosowania '0', czyli bankruta oraz wyzerowanie stanu konta gracza
                        if(wylosowanaKwota==0){
                            g1.setAktualnyWynik(wylosowanaKwota);
                            System.out.println("\n\nKolej gracza "+ g2.getImie());
                            graGraczNr=2;
                            break;
                        }

                        //wykonanie warunku następuje do momentu, gdy gracz prawidłowo odgaduje litery, po czym następuje przypisanie wylosowanej kwoty do jego salda aktualnego w danej rundzie
                        if(z1.odgadywanie()){
                            g1.setAktualnyWynik(wylosowanaKwota);
                            System.out.println("\t\tBardzo dobrze "+g1.getImie()+" zgarniasz "+wylosowanaKwota+"zł."+" Aktualnie masz na koncie "+g1.getAktualnyWynik()+"zł");
                            g1.czyOdgadnacHaslo();

                            // Metoda statyczna Gracz zwraca true lub false w zależności,czy gracz chce odgadnąć hasło
                            if(Gracz.czyZgaduje){
                                //warunek spełniający się w momencie, gdy gracz prawidłowo odgadnie hasło, po czym następuje przypisanie zebranej kwoty do salda końcowego i zakończenie rundy
                                if(g1.zgaduje(z1)) {
                                    g1.setCalkowityWynik(g1.getAktualnyWynik());
                                    System.out.println("\nBrawo " + g1.getImie() + "!!! Kończysz tę rundę z kwotą: " + g1.getAktualnyWynik());
                                    czyZakonczonaRunda = true;
                                    runda += 1;
                                    break;
                                }
                                // jeśli gracz nie odgadnie prawidłowo hasła, kończy się wykonanie jego pętli i kolejkę przejmuje drugi z graczy.
                                else {
                                    System.out.println("\nKolejkę przejmuje gracz: "+g2.getImie());
                                    graGraczNr=2;
                                    break;
                                }

                            }

                        }
                        // W przypadku, gdy gracz poda literę, która nie występuje w haśle nastąpi przerwanie pętli i przejęcie kolejki przez drugiego gracza
                        else{
                            System.out.println("Przykro mi nie ma takiej litery kolejkę przejmuje "+g2.getImie());
                            graGraczNr=2;
                            break;
                        }

                    }
                }



                if (graGraczNr == 2) {

                    while(true){

                        System.out.println("\n\nKolej gracza: "+g2.getImie());
                        wylosowanaKwota= g2.getWylosowana();

                        if(wylosowanaKwota==0){
                            g1.setAktualnyWynik(wylosowanaKwota);
                            System.out.println("\n\nKolej gracza "+ g1.getImie());
                            graGraczNr=1;
                            break;
                        }

                        if(z1.odgadywanie()){
                            g2.setAktualnyWynik(wylosowanaKwota);
                            System.out.println("\t\tBardzo dobrze "+g2.getImie()+" zgarniasz "+wylosowanaKwota+"zł."+" Aktualnie masz na koncie "+g2.getAktualnyWynik()+"zł");
                            g2.czyOdgadnacHaslo();

                            if(Gracz.czyZgaduje){

                                if(g2.zgaduje(z1)) {
                                    g2.setCalkowityWynik(g2.getAktualnyWynik());
                                    System.out.println("\nBrawo " + g2.getImie() + "!!! Kończysz tę rundę z kwotą: " + g2.getAktualnyWynik());
                                    czyZakonczonaRunda = true;
                                    runda += 1;
                                    break;
                                }
                                else {
                                    System.out.println("\nKolejkę przejmuje gracz: "+g1.getImie());
                                    graGraczNr=1;
                                    break;
                                }

                            }

                        }
                        else{
                            System.out.println("Przykro mi nie ma takiej litery kolejkę przejmuje "+g1.getImie());
                            graGraczNr=1;
                            break;
                        }

                    }


                }




            }while(!czyZakonczonaRunda);







        }


    }
}
