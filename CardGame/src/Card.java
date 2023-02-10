import java.util.Random;

public class Card {
    private String suit = "";
    private int value = 0;
    private String face = "";
    private static int total = 0;
    public Card(){

        boolean cardFound = false;
        while(!cardFound){
            Random r = new Random();
            //Creates a random value of the card
            int temp = r.nextInt(4)+1; //Assigns the card a random suit
            if(temp == 1){
                suit = "Clubs";
            }
            else if(temp == 2){
                suit = "Spades";
            }
            else if(temp == 3){
                suit = "Diamonds";
            }
            else{
                suit = "Hearts";
            }

            value = r.nextInt(13) + 1;
            if(Deck.checkCard(suit,value)){
                Deck.removeCard(suit,value);
                cardFound = true;
            }
            total++;
        }



        if(value > 10 || value == 1){ //Assigns the card a face value if it's a face card
            if(value == 11){
                face = "Jack";
            }
            else if(value == 12){
                face = "Queen";
            }
            else if(value == 13){
                face = "King";
            }
            else{
                face = "Ace";
            }
        }
        else{
            face = null;
        }




    }

    public int getValue(){
        return value;
    }
    public static int getTotal(){
        return total;
    }

    public boolean isFace(){ //Checks if the card has a face for conversion
        if(face != null){
            return true;
        }
        return false;
    }

    public String getFace(){
        if(isFace()){
            return face;
        }
        return null;
    }

    public String getSuit(){
        return suit;
    }

    public String toString(){ //States face if face card; states num otherwise
        String s = "";
        if(getFace() != null){
            s += getFace();
        }
        else{
            s = getValue() + "";
        }
        return s + " of " + getSuit();
    }
}
