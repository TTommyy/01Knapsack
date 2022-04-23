//Tomasz Koczar - 5
//Metody Programowania Baca-Zadanie 5
//23.04.2022AD

/*Pakowanie plecaka*/


import java.util.Scanner;

/**Klasa reprezetujaca plecak */
class Plecak{
    /************Pola************/
    private int pojemnosc,iloscPotencjalnychElementow,iloscElementowRe,iloscElementowIter;
    private int[] potencjalneElementy,elementyRe,elementyIter;
    private boolean znalezioneRe,znalezioneIter;
    /*********Metody*************/
    /**Konstruktor */
    public Plecak(Scanner sc){
        /*Inicjalizujemy wartosci*/
        pojemnosc = sc.nextInt();
        iloscPotencjalnychElementow = sc.nextInt();
        potencjalneElementy = new int[iloscPotencjalnychElementow];
        for(int i = 0; i<iloscPotencjalnychElementow; i++)
            potencjalneElementy[i] = sc.nextInt();
        znalezioneRe = znalezioneIter = false;
        elementyRe = new int[iloscPotencjalnychElementow];
        elementyIter = new int[iloscPotencjalnychElementow];
        iloscElementowRe = iloscElementowIter = 0;
    }

}

/*----------Klasa glowna-------------*/
public class Source{
    /*Wejscie do programu*/
    public static Scanner sc = new Scanner(System.in);

    /**************Program *************/
    public static void main(String[] args) {
        int iloscZestawow = sc.nextInt();//Pobieramy liczbe zestawow z wejscia.
        Plecak plecak; // Plecak ktory bedziemy pakowac
        for(int zestaw = 0; zestaw < iloscZestawow; zestaw++){//Dla kazdego zestawu
            plecak = new Plecak(sc);
        }

    }
}