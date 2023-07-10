import com.dsddet.entity.Deck;
import com.dsddet.exception.GameException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeckTests {
    Deck deck;

    @BeforeEach
    void init() {
        this.deck=Deck.instance;
    }

    @Test
    public void player_picking_more_cards_than_in_deck(){
        assertThrows(GameException.class, () ->
                deck.getCards(90));
    }

}
