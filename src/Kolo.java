

import java.util.Random;
import java.util.Scanner;

/**
 *  Klasa, ktora jest symulatorem kola, na ktorym znajduja sie kwoty. Słuzy do wylosowania przez gracza kwoty.
 *  Kwoty zapisane są w tablicy
 */
public class Kolo {

    //tablica z kwotami występującymi na kole. '0' stanowi o bankrucie.
    private  int[] kwoty = {100,200,150,100,250,300,0,400,0,350,0,100,150,100,150,400,500,250,0,1500,100,250,300,1000,0,1500,450};
    private  int wylosowana; // zmienna przechowująca wylosowaną kwotę.
    Random rand= new Random();
    Scanner sc= new Scanner(System.in);
    private  int indeksWylosowany; //zmienna przechowująca wylosowany indeks z tablicy "koła".


    /**
     * / Metoda słuzaca wylosowaniu kwoty z tablicy. Z zastosowaniem wyjatku przerwania, w celu urealnienia wrazenie losowania.
     * @return zwraca wylosowana liczbe z tablicy, ktora jest kwota, o ktora gra Gracz
     * @throws InterruptedException wyjatek słuzacy przerwaniu programu, dzieki, ktoremu wyswietlane sa losowane kwoty w odstepach czasowych
     */
    public  int getWylosowana() throws InterruptedException {
        indeksWylosowany = rand.nextInt(kwoty.length);
        wylosowana = kwoty[indeksWylosowany];
        System.out.print("\nZakręć kołem wciskając 'z': ");
        String zakrec;
        zakrec=sc.nextLine();
        System.out.println("\nTrwa losowanie...\n");
        for(int i=0;i<=indeksWylosowany;i++){
            Thread.sleep(500);
            if(kwoty[i]==0) System.out.print(" Bankrut ");
            else System.out.print(kwoty[i]+" ");
        }
        System.out.println("\n");
        if(wylosowana==0){
            System.out.print("\n Wylosowano: Bankrut ");
        }
        else
            System.out.print("\nWylosowano: "+wylosowana+"zł");
        return wylosowana;
    }




}
