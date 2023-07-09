package com.dsddet;

import com.dsddet.entity.Player;
import com.dsddet.exception.GameException;

import java.io.Console;

public class Main {
    public static void main(String[] args) {

        Console console = System.console();

        try {
            Integer playerCount = Integer.parseInt(args[0]);

            //validate nos of players input
            if (playerCount > 3 || playerCount <= 0) {
                throw new GameException("Invalid input, 1-3 players allowed, invalid nos of players");
            }

            // Instantiate Game
            Blackjack game = new Blackjack(playerCount);

            // Percentage used in computer's strategy
            Float stategy = 0.8F;

            // Playing
            while (game.isStillGoing()) {
                game.play(console, stategy);
                game.setStillGoing(false);
            }

            // Finding winner
            System.out.println("\n++++++ Announcing Winners !! +++++++\n");
            for (Player player : game.getPlayers()) {
                Player dealer = game.getPlayers().get(playerCount);
                if (player.isDealer()) {
                    continue;
                }

                game.findWinner(player, dealer);
            }
            System.out.println("\n++++++++++++++++++++++++++++++++++++\n");

        } catch (Exception ex) {
            throw new GameException(ex.getMessage());
        }

    }
}