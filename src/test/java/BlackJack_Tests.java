
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.dsddet.entity.Card;
import com.dsddet.entity.Deck;
import com.dsddet.entity.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.dsddet.Blackjack;

public class BlackJack_Tests {

    //test_findWinner_when_dealer_is_busted
    //test_findWinner_when_player_is_busted
    //test_findWinner_when_both_dealer_and_player_are_busted
    //test_findWinner_when_dealer_scores_better_than_player
    //test_findWinner_when_player_scores_better_than_dealer
    //test_findWinner_when_both_dealer_and_player_have_equal_scores
    //test_execStrategy
    Deck deck;
    Player player;
    Player dealer;
    Card cardA;
    Card cardB;
    Blackjack game;

    @BeforeEach
    void init() {
        this.deck=Deck.instance;
        this.player=new Player("1",this.deck,"Player");
        this.dealer=new Player("2",this.deck,"Dealer");
        this.cardA=new Card();
        this.cardB=new Card();
        this.game=new Blackjack(2);
    }

    @Test
    public void test_findWinner_when_dealer_is_busted(){
        this.dealer.setCardTotal(25);
        this.player.setCardTotal(2);
        assertTrue(game.findWinner(this.player,this.dealer).getType().equals("Player"));
    }

    @Test
    public void test_findWinner_when_player_is_busted(){
        this.dealer.setCardTotal(2);
        this.player.setCardTotal(25);
        assertTrue(game.findWinner(this.player,this.dealer).getType().equals("Dealer"));
    }

    @Test
    public void test_findWinner_when_both_dealer_and_player_are_busted(){
        this.dealer.setCardTotal(25);
        this.player.setCardTotal(25);
        assertTrue(game.findWinner(this.player,this.dealer).getType().equals("Dealer"));
    }

    @Test
    public void test_findWinner_when_dealer_scores_better_than_player(){
        this.dealer.setCardTotal(21);
        this.player.setCardTotal(20);
        assertTrue(game.findWinner(this.player,this.dealer).getType().equals("Dealer"));
    }

    @Test
    public void test_findWinner_when_player_scores_better_than_dealer(){
        this.dealer.setCardTotal(20);
        this.player.setCardTotal(21);
        assertTrue(game.findWinner(this.player,this.dealer).getType().equals("Player"));
    }

    @Test
    public void test_findWinner_when_both_dealer_and_player_have_equal_scores(){
        this.dealer.setCardTotal(18);
        this.player.setCardTotal(18);
        assertTrue(game.findWinner(this.player,this.dealer).getType().equals("Dealer"));
    }




}
