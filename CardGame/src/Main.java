import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Scanner;
import java.lang.*;

public class Main {
    static Card inPlay; //Card that user must play on
    static Hand a; //User hand
    static Hand b; //Com Hand
    static Deck d = new Deck();

    public static void main(String[] args) {
        setup();
    }
    public static void setup(){
        inPlay = new Card();
        a = new Hand();
        b = new Hand();
        System.out.println("User hand: " + a);
        System.out.println("Card: " + inPlay);
        turn();
    }

    public static void turn(){ //User decides to play or draw
        Scanner scanner = new Scanner(System.in);
        if(a.canPlay(inPlay.getSuit(),inPlay.getValue())){ //If user cannot play, they are forced to draw
            System.out.println("\n Play or Draw?");
            String ans = scanner.nextLine();
            ans = ans.toLowerCase();
            for(char c : ans.toCharArray()){
                if(c == 'p'){
                    play(a);
                }
                else if(c == 'd'){
                    draw(a);
                }
                else{
                    System.out.println("Please enter play or draw");
                    turn();
                }
            }
        }
        else{
            System.out.println("\nDraw?");
            String ans = scanner.nextLine();
            ans = ans.toLowerCase();
            for(char c : ans.toCharArray()){
                if(c == 'd'){
                    draw(a);
                }
            }
            System.out.println("You have no choice, you're drawing.");
            draw(a);
        }
    }

    public static void draw(Hand h) {
        h.addCard();
        System.out.println("You drew: " + h.cards.get(h.cards.size()-1));
        System.out.println(h);
        while(!h.canPlay(inPlay.getSuit(), inPlay.getValue())) { //Makes the user draw cards until they can play
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            h.addCard();
            System.out.println("You drew: " + h.cards.get(h.cards.size()-1));
            System.out.println(h);
        }
        play(h);
    }
    public static void play(Hand h){ //User picks which card to place
        Scanner scanner = new Scanner(System.in);
        System.out.println("Play which number or face card?");
        int n = 0;
        try {
            n = scanner.nextInt();
            if (!h.checkNum(n)) {
                System.out.println("You don't have that number");
                play(h);
            }
        } catch(Exception e){
            String s = scanner.nextLine();
            n = checkFace(s);
            if(n == 0){
                System.out.println("Enter a valid face card");
                play(h);
            }
            else if(!h.checkNum(n)){
                System.out.println("You don't have that face");
                play(h);
            }

        }
        scanner = new Scanner(System.in);
        System.out.println("Which suit?");
        String s = scanner.nextLine();
        //System.out.println(s);
        s = capFirst(s);
        if(s.equals("no")){
            System.out.println("Input a valid suit");
            play(h);
        }
        else if(!h.checkSuit(s)){
            System.out.println("You don't have that suit");
            play(h);
        }
      if(s.equals(inPlay.getSuit()) == false && n != inPlay.getValue()){
          System.out.println("Please enter a playable card");
          play(h);
        }
        inPlay = h.getCard(s,n);
        h.playCard(s,n);
        System.out.println("You played " + inPlay);
        if(h.hasWon()){
            playerWin();
        }
        System.out.println("Cards: " + h);
        comPlay();
    }

    public static void playerWin(){ //Allows player to restart if they win
        Scanner scanner = new Scanner(System.in);
        System.out.println("Congrats! You Win! Want to play again?");
        String s = scanner.nextLine();
        s = s.toLowerCase();
        for(char c : s.toCharArray()){
            if(c == 'y'){
                setup();
            }
            else if(c == 'n'){
                System.out.println("Goodbye");
                System.exit(0);
            }

            System.out.println("Goodbye");
            System.exit(0);

        }
    }

    public static int checkFace(String s){ //Converts face card inputs into numbers
        s = s.toLowerCase();
        char c = s.charAt(0);
        if(c == 'j'){
            return 11;
        }
        else if(c == 'q'){
            return 12;
        }
        else if(c == 'k'){
            return 13;
        }
        else if(c == 'a'){
            return 1;
        }
        return 0;
    }

    public static String capFirst(String s){ //returns suit based on user input
        s = s.toLowerCase();
        char c = s.charAt(0);
        if(c == 'd'){
            return "Diamonds";
        }
        else if(c == 'c'){
            return "Clubs";
        }
        else if(c == 's'){
            return "Spades";
        }
        else if(c == 'h'){
            return "Hearts";
        }
        return "no";
    }

    public static void comPlay(){ //Computer plays card if applicable; draws if not
        //System.out.println(inPlay);
        if(b.canPlay(inPlay.getSuit(), inPlay.getValue())){
            for(int i=0;i<b.cards.size();i++){
                //System.out.println("Checking: " + b.cards.get(i));
                if(b.cards.get(i).getSuit().equals(inPlay.getSuit()) || b.cards.get(i).getValue() == inPlay.getValue()){
                    inPlay = b.cards.get(i);
                    System.out.println("\nComputer played: " + inPlay);
                    if(b.hasWon()){
                        comWin();
                    }
                    b.playCard(b.cards.get(i).getSuit(), b.cards.get(i).getValue());
                    turn();
                }
            }
        }
        else{
            comDraw();
        }

    }

    public static void comWin(){ //Allows user to restart if computer wins
        Scanner scanner = new Scanner(System.in);
        System.out.println("You lose! Want to play again?");
        String s = scanner.nextLine();
        s = s.toLowerCase();
        for(char c : s.toCharArray()){
            if(c == 'y'){
                setup();
            }
            else if(c == 'n'){
                System.out.println("Goodbye");
                System.exit(0);
            }

            System.out.println("Goodbye");
            System.exit(0);

        }
    }

    public static void comDraw() { //Makes com draw until it can play
        while (!b.canPlay(inPlay.getSuit(), inPlay.getValue())) {
            b.addCard();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            //System.out.println(b);
        }
        comPlay();
    }

}
