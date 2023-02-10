import java.util.ArrayList;

public class Hand {
    ArrayList<Card> cards = new ArrayList<Card>();
    public Hand(){ //Hand is initialized with seven cards
        for(int i=0;i<7;i++){
            cards.add(new Card());
        }
    }

    public boolean checkSuit(String suit){ //Checks if the hand has a certain suit
        for(Card c : cards){
            if(c.getSuit() == suit){
                return true;
            }
        }
        return false;
    }

    public boolean checkNum(int num){ //Checks if the hand has a certain num/face card
        for(Card c : cards){
            if(c.getValue() == num){
                return true;
            }
        }
        return false;
    }

    public boolean canPlay(String suit, int num){ //Checks if the hand has any playable cards
        if(checkSuit(suit) || checkNum(num)){
            return true;
        }
        return false;
    }

    public Card getCard(String suit, int num){ //Returns a specific card in the hand
        for(Card c : cards){
            if(c.getSuit().equals(suit) && c.getValue() == num){
                return c;
            }
        }
        return null;
    }

    public void playCard(String suit, int num){ //Removes card
        for(int i=0;i<cards.size();i++){
            if(cards.get(i).getValue() == num && cards.get(i).getSuit().equals(suit)){
                cards.remove(i);
            }
        }
    }

    public void addCard(){ //Adds card to hand/shuffles deck if needed
        cards.add(new Card());
        if(Card.getTotal() == 52){
            Deck.newDeck();
        }
    }

    public String toString(){
        return cards + "";
    }

    public boolean hasWon(){ //Checks if the hand has any remaining cards
        if(cards.size() == 0){
            return true;
        }
        return false;
    }

}
