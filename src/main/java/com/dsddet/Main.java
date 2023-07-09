package com.dsddet;

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

            Blackjack game = new Blackjack(playerCount);
            Float stategy = 0.8F;

            while (game.isStillGoing()) {
                game.play(console,stategy);
                game.setStillGoing(false);
            }

            game.findWinner();

        }catch (Exception ex){
            throw new GameException(ex.getMessage());
        }

    }
}