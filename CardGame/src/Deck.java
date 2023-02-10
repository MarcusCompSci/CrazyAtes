import java.util.ArrayList;
import java.util.HashMap;

public class Deck {
    private static HashMap<String,ArrayList<Integer>> ava = new HashMap<String,ArrayList<Integer>>();
    public Deck(){
        //Creates an ArrayList with nums 1-13 for each suit
        ArrayList<Integer> x = new ArrayList<Integer>();
        ArrayList<Integer> y = new ArrayList<Integer>();
        ArrayList<Integer> z = new ArrayList<Integer>();
        ArrayList<Integer> a = new ArrayList<Integer>();

        for(int i=1; i<14; i++){
            x.add(i);
            y.add(i);
            z.add(i);
            a.add(i);
        }

        ava.put("Clubs",x);
        ava.put("Spades",y);
        ava.put("Hearts",z);
        ava.put("Diamonds",a);

    }

    public static boolean checkCard(String suit, int val){ //Checks if card is still in deck
        for(int n : ava.get(suit)){
            if(n == val){
                return true;
            }
        }
        return false;
    }

    public static void removeCard(String suit, int val){ //removes card from hashmap to prevent duplicates
        ava.get(suit).remove(Integer.valueOf(val));
    }

    public static void newDeck(){ //Creates new deck if all cards have been used allowing all cards to be played again
        ArrayList<Integer> x = new ArrayList<Integer>();
        ArrayList<Integer> y = new ArrayList<Integer>();
        ArrayList<Integer> z = new ArrayList<Integer>();
        ArrayList<Integer> a = new ArrayList<Integer>();

        for(int i=1; i<14; i++){
            x.add(i);
            y.add(i);
            z.add(i);
            a.add(i);
        }

        ava.put("Clubs",x);
        ava.put("Spades",y);
        ava.put("Hearts",z);
        ava.put("Diamonds",a);
    }

}
