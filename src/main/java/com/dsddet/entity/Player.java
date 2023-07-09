package com.dsddet.entity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Player {
    private String name;
    private List<Card> cards = new ArrayList<Card>();;
    private Integer cardTotal = 0;
    private String type;
    private Integer countOfA = 0;
    private boolean isDone;

    /**
     * Constructor
     * @param name - name of player
     * @param deck - deck of cards
     * @param type - type of player, dealer or regular player
     */
    public Player(String name, Deck deck, String type) {
        this.name = name;
        this.type = type;
        addCards(deck.getCards(1));

        System.out.println(this.type.equals("Dealer") ? "Dealing to computer, card: facedown"
                : String.format("%s %s", this.getState(), isBusted() ? "Busted over 21" : ""));

    }

    /**
     * Determines if player has score greater than 21
     * @return
     */
    public boolean isBusted() {
        return this.cardTotal > 21;
    }

    public void setCardTotal(Integer total){
        this.cardTotal=total;
    }

    public void setCards(List<Card> card){
        this.cards=card;
    }

    /**
     * Getter for name
     * @return
     */
    public String getName() {
        return this.name;
    }

    /**
     * Determines if player is a dealer
     * @return
     */
    public boolean isDealer() {
        return this.type.equalsIgnoreCase("Dealer");
    }

    /**
     * Retrieves cards
     * @param deck - Deck of cards
     */
    public void pickCard(Deck deck) {
        if (this.isDone) {
            return;
        }
        addCards(deck.getCards(1));

    }

    /**
     * Getter for cardTotal
     * @return
     */
    public int getCardTotal() {
        return this.cardTotal;
    }

    /**
     * Adds cards to a player's list of cards
     * @param cards - cards to be added to player's list
     */
    private void addCards(List<Card> cards) {
        Integer incomingCountOfA = cards.stream().filter(x -> x.getLabel().equals("A")).map(x -> 1).reduce(0,
                Integer::sum);
        this.countOfA = this.countOfA + incomingCountOfA;
        this.cardTotal = this.cardTotal + cards.stream().map(x -> x.getValue()).reduce(0, Integer::sum);
        this.cards.addAll(cards);

        if (this.countOfA > 0 && this.isBusted()) {
            this.countOfA = countOfA - 1;
            this.cardTotal = this.cardTotal - 10;
        }

        if (this.isBusted()) { // Over 21 is an automatic done, but 21 is not.
            this.setIsDone(true);
        }

    }

    /**
     * Provides text for console output
     * @return
     */
    public String getState() {
        return String.format("Dealing to player %s, score=(%d): %s", this.name,this.cardTotal, this.cards);
    }

    /**
     * isDone getter
     * @return
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    public String getType(){
        return this.type;
    }

    /**
     * isDone Setter
     * @param isDoneValue
     */
    public void setIsDone(boolean isDoneValue) {
        this.isDone = isDoneValue;
    }

    /**
     * To string methods
     * @return
     */
    @Override
    public String toString() {
        return String.format("%s has a score = %d, and cards = %s, countOfA = %d", this.name, this.cardTotal,
                this.cards, this.countOfA);
    }

}