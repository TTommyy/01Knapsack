//Tomasz Koczar - 5
//Metody Programowania Baca-Zadanie 5
//23.04.2022AD

/*Pakowanie plecaka*/

/*Idea rozwiazania:
    1. Podejscie rekurencjne:
    * Sprawdzamy metoda silowa wszytkie mozlwie kombinacje.
    Dla kazdego elementu wywowujemy rekurencje dwa razy, raz uzglednijac obecnie
    rozwazany element, raz nie. Szczegoly w komentarzach przy kodzie.

    2. Podejcie iteracjne:
    * Logika stojaca za poprawnoscia rozwizania
    jest idenczyczna co w pierwszym przypadku.
    Roznica polega na tym, ze zamiast jawnie wywolywac
    rekursje, tworzymy stos na ktorym przechowujemy
    parametry funkcji, nastepnie dopoki stos sie nie
    oprozni wykonujemy funkcjie dla parametrow 
    ze szczytu stosu.

/*
Test jawny zwraca: 
REC:  20 = 8 7 5 
ITER: 20 = 8 7 5 
BRAK
Co jest zgodne z wyjsciem oczekiwanym
Test:
3
50
8
40 70 20 20 15 25 30 100
7
7
3 3 3 5 5 6 4
100 
10
7 10 7 20 7 30 7 40 7 50
Zwraca:
REC:  50 = 20 30 
ITER: 50 = 20 30 
REC:  7 = 3 4 
ITER: 7 = 3 4 
REC:  100 = 10 20 30 40 
ITER: 100 = 10 20 30 40 
Co zgadza sie z oczekiwaniami.
*/
import java.util.Scanner;

/**Klasa implementujaca strukture dancyh zwana stosem*/
class Stack{
    /*--------Pola--------*/
    static private int MAX_SIZE = 128;       //Maksymalny rozmiar stosu
    private int currentSize;                //Obecny rozmiar stosu
    private int[] stack;                    //Stos

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

    /**Wyswietla stos w kolejnosci od pierwszego wlozenego,
     * do ostatniego wlozonego.
     */
    public void display(){
        System.out.print("(");
        for(int i = 0; i < currentSize; i++){
            System.out.print(stack[i]+",");
        }
        System.out.print(")");
    }
}

class Parametry{
    /*-----Pola-------*/
    private Stack wartosci;
    private int wagaDocelowa,potencjalnyElement;
    /*--------Metody--------*/
    /**Konstruktor */
    public Parametry(Stack w, int wD,int pE ){
        wartosci = new Stack(w);
        wagaDocelowa = wD;
        potencjalnyElement = pE;
    }
    /*Geterry*/
    public Stack getStack(){
        return wartosci;
    }
    public int getWaga(){
        return wagaDocelowa;
    }
    public int getPE(){
        return potencjalnyElement;
    }
}

/**Klasa implementujaca strukture dancyh zwana stosem
 * przechowujaca pametry funkcji.
*/
class StackParam{
    /*--------Pola--------*/
    static private int MAX_SIZE = 64;           //Maksymalny rozmiar stosu
    private int currentSize;                    //Obecny rozmiar stosu
    private Parametry[] stack;                  //Stos

    /*------Metody--------*/
    /**Konstruktor*/
    public StackParam(){ stack = new Parametry[MAX_SIZE]; currentSize = 0; }

    public StackParam(StackParam stos){
        stack = new Parametry[MAX_SIZE];
        currentSize = stos.currentSize;
        stack = stos.stack.clone();
    }

    /**Wstawia nowa wartos na szczyt stosu*/
    public StackParam push(Parametry elem){
        stack[currentSize++] = elem;
        return this;
    }

    /**Zwraca szczyt stosu*/
    public Parametry top(){
        if(currentSize == 0 ) return null;
        return stack[currentSize-1];
    }

    /**Usuwa ze szczytu*/
    public StackParam pop(){
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

}


/**Klasa reprezetujaca plecak */
class Plecak{
    /************Pola************/
    private int pojemnosc,iloscPotencjalnychElementow,iloscElementowRe,iloscElementowIter; //Samo sie komentuje
    private int[] potencjalneElementy,elementyRe,elementyIter;                             //^^^ 
    private boolean znalezioneRe,znalezioneIter;                                           //^^^
    /*********Metody*************/
    /**Konstruktor */
    public Plecak(Scanner sc){
        /*Inicjalizujemy wartosci*/
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
   
    

    /**Pakowanie metoda iteracyjna */
    public void pakujIter(){

        StackParam stosWywolan = new StackParam();              //Przechowuje wartosc powrotu
        Stack stos = new Stack();                               //Przechowuje wagi elementow plecaka
        int potencjalnyElement = 0, elem,                       //Inicjalizacja potrzebnych zmiennych
        wagaDocelowa = pojemnosc;
        Parametry parametry = new Parametry(stos, pojemnosc, 0);
        stosWywolan.push(parametry);                            //Pchamy pierwsze wywolanie na stos;

        while(!stosWywolan.isEmpty()){                          //Petla wykonujaca
            potencjalnyElement = stosWywolan.top().getPE();     //Pobieramy parapetry ze stosu wywolan
            wagaDocelowa = stosWywolan.top().getWaga();
            stos = stosWywolan.top().getStack();

            stosWywolan.pop();                                  //Usuwamy wywolanie ze stosu

            elem = potencjalneElementy[potencjalnyElement];     //Obecnie rozwazany element
            if(wagaDocelowa < 0 ){
                continue;
            }
            if(wagaDocelowa == 0){                              //Znalezione!
                znalezioneIter = true;
                iloscElementowIter= stos.getCurrentSize();      //Przepisz wynik
                while(!stos.isEmpty()){
                    elementyIter[stos.getCurrentSize()-1] = stos.top();
                    stos.pop();
                }
                return;                                         //Zakoncz dzialanie
                }
            if(potencjalnyElement + 1 == iloscPotencjalnychElementow){  

                if(elem == wagaDocelowa){                   //Znalezione!
                    stos.push(elem);
                    znalezioneIter = true;                  //Przepisz wynik
                    iloscElementowIter = stos.getCurrentSize();
                    while(!stos.isEmpty()){
                        elementyIter[stos.getCurrentSize()-1] = stos.top();
                        stos.pop();
                    } 
                    return;                                 //Zakocz dzialanie                                    
    
                }
                continue;                                   //Nie znalezione
            }

            parametry = new Parametry(new Stack(stos), wagaDocelowa,potencjalnyElement+1);             //Nie uwzgledniaj akualengo elementu
            stosWywolan.push(parametry);

            parametry = new Parametry(new Stack(stos.push(elem)), wagaDocelowa-elem ,potencjalnyElement+1);  //Uwzglednij akutalny element    
            stosWywolan.push(parametry);                                          
                 
                   
        }
        
         

    }
    
    /**Pakwoanie rekurencjne */
    public void pakujRe(){
        Stack stos = new Stack();                                   // Do przechowywania elementow
        rekurencja(0, pojemnosc, stos);           // Pierwsze wywolanie funkcji
            
    }
    /**Funkcja pomocnicza */
    private boolean rekurencja(int potencjalnyElement, int wagaDocelowa, Stack stos ){

        int elem = potencjalneElementy[potencjalnyElement];                 //Pobieramy rozwazany element

        if(wagaDocelowa < 0 ) return false;                                 //Jezeli waga jest za duza zwroc false

        if(wagaDocelowa == 0){                                              //Znalezione!
            if(!znalezioneRe){
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
            if(elem == wagaDocelowa){                                    //Znalezione!
                if(!znalezioneRe){
                    stos.push(elem);
                    znalezioneRe = true;
                    iloscElementowRe = stos.getCurrentSize();
                    while(!stos.isEmpty()){
                        elementyRe[stos.getCurrentSize()-1] = stos.top();
                        stos.pop();
                    }
                }
                return true;

                }
            return false;                                                 //Nie znalezione
        }
                                                                     
        return rekurencja(potencjalnyElement+1,                         //Uwzglednij akutalny element   
        wagaDocelowa-elem, new Stack(stos.push(elem))) 
        || rekurencja(potencjalnyElement+1,                             //Nie uwzgledniaj akualengo elementu
        wagaDocelowa, new Stack(stos.pop())) ;              
    }

    public void prezentuj(){
        if(!znalezioneIter && !znalezioneRe){
            System.out.print("BRAK\n");
            return;
        }
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
        int iloscZestawow = sc.nextInt();                           //Pobieramy liczbe zestawow z wejscia.
        sc.nextLine();
        Plecak plecak;                                              //Plecak ktory bedziemy pakowac
        for(int zestaw = 0; zestaw < iloscZestawow; zestaw++){      //Dla kazdego zestawu
            plecak = new Plecak(sc);
            plecak.pakujRe();
            plecak.pakujIter();
            plecak.prezentuj();
        }

    }
}