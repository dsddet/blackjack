
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.dsddet.entity.Card;
import com.dsddet.entity.Deck;
import com.dsddet.entity.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.dsddet.Blackjack;
import org.mockito.Mockito;

import java.io.Console;
import java.util.List;

public class BlackjackTests {

    Deck deck;
    Player player;
    Player dealer;
    Card cardA;
    Card cardB;
    Blackjack game;

    @BeforeEach
    void init() {
        this.deck=new Deck();
        this.player=new Player("1",this.deck,"Player");
        this.dealer=new Player("2",this.deck,"Dealer");
        this.cardA=new Card("2","Spades",2);
        this.cardB=new Card("2","Hearts",2);
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

    @Test
    public void test_strategy_for_computer_to_keep_playing(){
        assertTrue(this.game.execStategy(5,0.5F));
    }

    @Test
    public void test_strategy_for_computer_to_stop_playing(){
        assertEquals(this.game.execStategy(15,0.5F),false);
    }

    @Test
    public void test_if_everyone_plays(){
        Console console = Mockito.mock(Console.class);
        Deck deck = Mockito.mock(Deck.class);
        Mockito.when(console.readLine("Prompt")).thenReturn("hit");
        Mockito.when(deck.getCards(1)).thenReturn(List.of(cardB,cardA));
        game.play(console,0.8F);

        Integer numberOfPlayersDone = game.getPlayers().stream().map(x -> x.getIsDone())
                .filter(x ->x==true)
                .map(x -> 1)
                .reduce(0, Integer::sum);

        assertEquals(numberOfPlayersDone,game.getPlayers().size());
    }


}
