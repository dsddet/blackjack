package com.dsddet;

import com.dsddet.entity.Deck;
import com.dsddet.entity.Player;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

public class Blackjack {
    private List<Player> players = new ArrayList<>();
    private final Deck deck;
    private boolean isStillGoing;

    /**
     * Constructor
     * @param nosOfPlayers - number of players in the game besides the dealer
     */
    public Blackjack(Integer nosOfPlayers) {
        this.deck = new Deck(); // Make deck singleton
        this.isStillGoing = true;

        // Initializing players, basing off number passed as shell arg
        while (nosOfPlayers > 0) {
            this.players.add(new Player(nosOfPlayers.toString(), deck, "Player"));
            nosOfPlayers--;
        }

        // Init Player of type dealer
        this.players.add(new Player("Computer", deck, "Dealer")); // Use an enum

    }

    /**
     * Returns a list containing all players in the game
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
     * Iterates through all players to determine who won i.e player vs dealer
     */
    public void findWinner() {
        System.out.println("++++++ Winners are !! +++++++");
        Player dealer = this.players.get(players.size() - 1);// Dealer is at end of the ordered List

        for (Player player : this.getPlayers()) {

            if(player.isDealer()){
                continue;
            }

            if (dealer.isBusted() && !player.isBusted()) {
                isPlayerWinner(true, player, dealer);
                continue;
            }

            if ((!dealer.isBusted() && player.isBusted()) || (dealer.isBusted() && player.isBusted())) {
                isPlayerWinner(false, player, dealer);
                continue;
            }

            if (player.getCardTotal() > dealer.getCardTotal()) {
                isPlayerWinner(true, player, dealer);
                continue;
            } else {
                isPlayerWinner(false, player, dealer);
            }

        }

    }

    /**
     * Helper for findWinner. Generates and Console logs the winner
     * @param doesPlayerWin - states if player won
     * @param player - player object
     * @param dealer - dealer object
     */
    private void isPlayerWinner(boolean doesPlayerWin, Player player, Player dealer) {
        System.out.println(String.format("Scoring: Player %s has %d, Dealer has %d. %s wins",
                player.getName(), player.getCardTotal(), dealer.getCardTotal(), doesPlayerWin ? "Player " + player.getName() : "Dealer"));
    }

    /**
     * Defines how computer/dealer should play
     * Uses a percentage score to determine when the dealer should stand
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