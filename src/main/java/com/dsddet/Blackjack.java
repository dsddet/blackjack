package com.dsddet;

import com.dsddet.entity.Deck;
import com.dsddet.entity.Player;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

public class Blackjack {
    private List<Player> players;
    private final Deck deck;
    private boolean isStillGoing;

    /**
     * Constructor
     * @param nosOfPlayers - number of players in the game besides the dealer
     */
    public Blackjack(Integer nosOfPlayers) {
        this.players=new ArrayList<>();
        this.deck = Deck.instance;
        this.isStillGoing = true;

        // Initializing players in game
        while (nosOfPlayers > 0) {
            this.players.add(new Player(nosOfPlayers.toString(), deck, "Player"));
            nosOfPlayers--;
        }

        // Adding dealer
        this.players.add(new Player("Computer", deck, "Dealer"));

    }

    /**
     * Returns a list of all players in the game
     * @return
     */
    public List<Player> getPlayers() {
        return this.players;
    }

    /**
     * Determins if game is still on going
     * @return
     */
    public boolean isStillGoing() {
        return this.isStillGoing;
    }

    /**
     * Set's value that determines game playing status i.e stopped or still playing
     * @param cont
     */
    public void setStillGoing(boolean cont) {
        this.isStillGoing = cont;
    }

    /**
     * Returns instance of deck of cards
     * @return
     */
    public Deck getDeck() {
        return this.deck;
    }

    /**
     * Given player & dealer, determines who won based on their scores
     */
    public Player findWinner(Player player, Player dealer) {

            if (dealer.isBusted() && !player.isBusted()) {
                declarePlayerAsWinner(true, player, dealer);
                return player;
            }

            if ((!dealer.isBusted() && player.isBusted()) || (dealer.isBusted() && player.isBusted())) {
                declarePlayerAsWinner(false, player, dealer);
                return dealer;
            }

            if (player.getCardTotal() > dealer.getCardTotal()) {
                declarePlayerAsWinner(true, player, dealer);
                return player;
            } else {
                declarePlayerAsWinner(false, player, dealer);
                return dealer;
            }


    }

    /**
     * Helper for findWinner. prints winner string
     * @param doesPlayerWin - states if player won
     * @param player - player object
     * @param dealer - dealer object
     */
    private void declarePlayerAsWinner(boolean doesPlayerWin, Player player, Player dealer) {
        System.out.println(String.format("Scoring: Player %s has %d, Dealer has %d. %s wins",
                player.getName(), player.getCardTotal(), dealer.getCardTotal(), doesPlayerWin ? "Player " + player.getName() : "Dealer"));
    }

    /**
     * Defines how computer/dealer should play
     * Uses a percentage of 21 to determine when the dealer should stand or hit
     * @param score - dealer's current total scroe
     * @param percent - percentage value, taken of 21 to determine limit score baseline before standing
     * @return
     */
    public boolean execStategy(Integer score, Float percent) {
        return (score <= (21 * percent)) ? true : false;
    }

    /**
     * Play engine
     * Provides interactive shell to player
     * @param console - object receives input from player/user
     * @param stategy - dealer's approach to the game
     */
    public void play(Console console, Float stategy) {

        for (Player player : this.getPlayers()) {
            String action = "hit";

            if (player.getIsDone()) {
                continue;
            }


            if (player.isDealer()) {
                while (execStategy(player.getCardTotal(), stategy)) {
                    player.pickCard(this.getDeck());
                    System.out.println(String.format("%s. (hit/stand)? %s",
                            player.getState(), player.isBusted() ? "Busted over 21." : ""));

                    if (player.isBusted()) {
                        player.setIsDone(true);
                        break;
                    }
                }

            } else {

                while (action.equals("hit")) {
                    action = console.readLine(String.format("%s. (hit/stand)? %s", player.getState(),
                            player.isBusted() ? "Busted over 21." : "")).toLowerCase(); //Optional isBusted phrase in prompt
                    if (player.isBusted()) {
                        action = "stand";
                    }

                    //only pick card in hit state
                    if (action.equals("hit")) {
                        player.pickCard(this.getDeck());
                    }
                }

            }
        }
    }

}