
import com.dsddet.entity.Card;
import com.dsddet.entity.Deck;
import com.dsddet.entity.Player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTests {

    Deck deck;
    Player player;
    Card aHearts;
    Card kSpades;


    @BeforeEach
    void init() {
        this.deck=new Deck();
        this.player=new Player("1",this.deck,"Player");
        aHearts=new Card("A","Hearts",11);
        kSpades=new Card("K","Spades",10);
    }

    @Test
    public void check_adding_cards(){
        Integer cardsBefore=player.getCards().size();
        player.pickCard(this.deck,1);
        Integer cardsAfter=player.getCards().size();
        assertTrue(cardsAfter>cardsBefore);
    }

    @Test
    public void checking_when_player_has_two_As(){
        Deck deck = Mockito.mock(Deck.class);
        Mockito.when(deck.getCards(1)).thenReturn(List.of(aHearts,kSpades));
        Integer totalBeforePickingA=player.getCardTotal();
        player.pickCard(deck,1);
        assertEquals(player.getCardTotal(),10+1+totalBeforePickingA);
    }

    @Test
    public void checking_is_busted_when_exceeds_21(){
        System.out.println(player.getCards());
        player.pickCard(deck,10);
        assertTrue(player.isBusted());
    }

    @Test
    public void checking_if_player_is_done_when_busted(){
        player.pickCard(deck,20);
        assertTrue(player.getIsDone());
    }




}
