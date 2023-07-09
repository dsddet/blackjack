package com.dsddet;

import com.dsddet.entity.Player;
import com.dsddet.exception.GameException;

import java.io.Console;

public class Main {
    public static void main(String[] args) {

        Console console = System.console();

        try{
            Integer playerCount = Integer.parseInt(args[0]);
            if (playerCount>3){
                throw new GameException("Reached limit, Max players allowed are (3)");
            }

            // Instantiate Game
            Blackjack game = new Blackjack(playerCount);
            Float stategy = 0.8F;

            // Playing
            while (game.isStillGoing()) {
                game.play(console,stategy);
                game.setStillGoing(false);
            }

            // Finding winner
            for (Player player:game.getPlayers()){
                Player dealer=game.getPlayers().get(playerCount);
                if(player.isDealer()){
                    continue;
                }

                game.findWinner(player,dealer);

            }

        }catch (Exception ex){
            throw new GameException(ex.getMessage());
        }

    }
}