//Tomasz Koczar - 5
//Metody Programowania Baca-Zadanie 5
//23.04.2022AD

/*Pakowanie plecaka*/

/*
Test jawny zwraca: 
REC:  20 = 8 7 5 
ITER: 20 = 8 7 5 
BRAK
Co jest zgodne z wyjsciem oczekiwanym
Test:


*/
import java.util.Scanner;

/**Klasa implementujaca strukture dancyh zwana stosem*/
class Stack{
    /*--------Pola--------*/
    static private int MAX_SIZE = 256; // maksymalny rozmiar stosu
    private int currentSize; // obecny rozmiar stosu
    private int[] stack; // stos

    /*------Metody--------*/
    /**Konstruktor*/
    public Stack(){ stack = new int[MAX_SIZE]; currentSize=0; }

    public Stack(Stack stos){
        stack = new int[MAX_SIZE];
        currentSize = stos.currentSize;
        stack = stos.stack.clone();
    }

    /**Wstawia nowa wartos na szczyt stosu*/
    public Stack push(int elem){
        stack[currentSize++] = elem;
        return this;
    }

    /**Zwraca szczyt stosu*/
    public int top(){
        if(currentSize == 0 ) return 0;
        return stack[currentSize-1];
    }

    /**Usuwa ze szczytu*/
    public Stack pop(){
        if(currentSize == 0 ){
            //System.out.println("Robisz cos zle!");
            return this;
        }
        currentSize--;
        return this;
    }

    /**Sprawcza czy stos jest pusty*/
    public boolean isEmpty(){
        if(currentSize==0)
            return true;
        return false;
    }

    /**Zwraca obecny rozmiar stosu */
    public int getCurrentSize(){
        return currentSize;
    }

    public void display(){
        System.out.print("(");
        for(int i = currentSize -1; i >-1; i--){
            System.out.print(stack[i]+",");
        }
        System.out.print(")");
    }
}

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
        //System.out.println("Tworze plecak");
        pojemnosc = sc.nextInt();
        sc.nextLine();
        iloscPotencjalnychElementow = sc.nextInt();
        sc.nextLine();
        potencjalneElementy = new int[iloscPotencjalnychElementow];
        for(int i = 0; i<iloscPotencjalnychElementow; i++)
            potencjalneElementy[i] = sc.nextInt();
        znalezioneRe = znalezioneIter = false;
        elementyRe = new int[iloscPotencjalnychElementow];
        elementyIter = new int[iloscPotencjalnychElementow];
        iloscElementowRe = iloscElementowIter = 0;
    }
   
    /**Pakowanie metada iteracyjna */


    /**Pakwoanie rekurencjne */
    public void pakujRe(){
        Stack stos = new Stack();//do przechowawania elementow
        rekurencja(0, pojemnosc, stos);
            
    }
    /**Funkcja pomocnicza */
    private boolean rekurencja(int potencjalnyElement, int wagaDocelowa, Stack stos){
        //System.out.print("Stos: "); stos.display(); System.out.println("Waga: " + wagaDocelowa);
        int elem = potencjalneElementy[potencjalnyElement];
        if(wagaDocelowa < 0 ) return false;
        if(wagaDocelowa == 0){
            if(!znalezioneRe){
                //stos.display();
                znalezioneRe = true;
                iloscElementowRe = stos.getCurrentSize();
                while(!stos.isEmpty()){
                    elementyRe[stos.getCurrentSize()-1] = stos.top();
                    stos.pop();
                }
            }
            return true;
            }
            if(potencjalnyElement + 1 == iloscPotencjalnychElementow){
                if(elem == wagaDocelowa){
                    if(!znalezioneRe){
                        stos.push(elem);
                        //stos.display();
                        znalezioneRe = true;
                        iloscElementowRe = stos.getCurrentSize();
                        while(!stos.isEmpty()){
                            elementyRe[stos.getCurrentSize()-1] = stos.top();
                            stos.pop();
                        }
                    }
                    return true;

                }
                return false;
            }
            
            return rekurencja(potencjalnyElement+1, wagaDocelowa-elem, new Stack(stos.push(elem))) || rekurencja(potencjalnyElement+1, wagaDocelowa, new Stack(stos.pop())) ; 
        }

    public void prezentuj(){
        /*if(!znalezioneIter && !znalezioneRe){
            System.out.print("BRAK\n");
            return;
        }*/
        System.out.print("REC:  " + pojemnosc + " = ");
        for(int i = 0; i< iloscElementowRe; i++)
            System.out.print(elementyRe[i] + " ");
        System.out.print("\n");
        System.out.print("ITER: "+ pojemnosc + " = ");
        for(int i = 0; i< iloscElementowIter; i++)
            System.out.print(elementyIter[i] + " ");
        System.out.print("\n");
    }
}

/*----------Klasa glowna-------------*/
public class Source{
    /*Wejscie do programu*/
    public static Scanner sc = new Scanner(System.in);

    /**************Program *************/
    public static void main(String[] args) {
        int iloscZestawow = sc.nextInt();//Pobieramy liczbe zestawow z wejscia.
        sc.nextLine();
        Plecak plecak; // Plecak ktory bedziemy pakowac
        for(int zestaw = 0; zestaw < iloscZestawow; zestaw++){//Dla kazdego zestawu
            plecak = new Plecak(sc);
            plecak.pakujRe();
            plecak.prezentuj();
        }

    }
}