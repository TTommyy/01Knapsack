//Tomasz Koczar - 5
//Metody Programowania Baca-Zadanie 5
//23.04.2022AD

/*Pakowanie plecaka*/


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

    /**Wstawia nowa wartos na szczyt stosu*/
    public void push(int elem){
        stack[currentSize++] = elem;
    }

    /**Zwraca szczyt stosu*/
    public int top(){
        return stack[currentSize-1];
    }

    /**Usuwa ze szczytu*/
    public void pop(){
        if(currentSize == 0 ){
            System.out.println("Robisz cos zle!");
            return;
        }
        currentSize--;
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
        System.out.println("Tworze plecak");
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
    /*Pakowanie metoda iteracyjna*/
    public void pakujIter(){
        int wagaDocelowa = pojemnosc, potencjalnyElement;
        Stack stos;
        for(potencjalnyElement = 0; potencjalnyElement < iloscPotencjalnychElementow; potencjalnyElement++){
            stos = new Stack();
            if(iteracja(potencjalnyElement, wagaDocelowa, stos)){
                iloscElementowIter = stos.getCurrentSize();
                while(!stos.isEmpty()){
                    elementyIter[stos.getCurrentSize()-1] = stos.top();
                    stos.pop();
                }
                znalezioneIter = true;
                return;
            } 
        }
        znalezioneIter = false;
    }
    /**Funkjca pomocnicza */
    private boolean iteracja(int potencjalnyElement,int wagaDocelowa,Stack stos){
        
        stos.push(potencjalneElementy[potencjalnyElement]);
        wagaDocelowa -= stos.top();
        System.out.println(stos.top());
        if(wagaDocelowa == 0) return true;
        if(wagaDocelowa < 0){
            wagaDocelowa+=stos.top();
            stos.pop();
        }
        if(potencjalnyElement + 1 == iloscPotencjalnychElementow) return false;
        return iteracja(++potencjalnyElement,wagaDocelowa,stos);   
        
    }

    public void prezentuj(){
        if(!znalezioneIter) {
            System.out.print("Nie");
            return;
        }
        for(int i = 0; i< iloscElementowIter; i++)
            System.out.print(elementyIter[i] + ",");
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
            plecak.pakujIter();
            plecak.prezentuj();
        }

    }
}