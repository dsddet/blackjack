package com.dsddet.entity;

import com.dsddet.exception.GameException;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

//Singleton
public class Deck {
    private LinkedList<Card> deck = new LinkedList<>();

    public Deck() {
        initDeck();
    }

    /**
     * Creates cards and inserts them into a deck object
     */
    private void initDeck() {
        int index = 0;

        for (String item : new String[] { "2,2", "3,3", "4,4", "5,5", "6,6", "7,7", "8,8", "9,9", "10,10", "J,10",
                "Q,10", "K,10", "A,11" }) {
            int count = 0;
            LinkedList<String> types = new LinkedList<String>(Arrays.asList("Hearts", "Spades", "Flowers", "Diamond"));

            while (count < 4) {
                this.deck.add(index,
                        new Card(item.split(",")[0], types.removeLast(), Integer.parseInt(item.split(",")[1])));
                count++;
                index++;
            }
        }

        System.out.println("Shuffling");
        Collections.shuffle(deck);

    }

    /**
     * Returns the specified number of cards
     * @param requestedNumber - requested number of cards
     * @return
     */
    public List<Card> getCards(Integer requestedNumber) {
        Collections.shuffle(deck);
        if (requestedNumber <= 0 || requestedNumber>this.deck.size()) {
            throw new GameException("Cards requested from deck should be more 0");
        }
        LinkedList<Card> picked = new LinkedList<>();
        while (requestedNumber > 0) {
            picked.add(this.deck.removeLast());
            requestedNumber--;
        }

        return picked;
    }


}
