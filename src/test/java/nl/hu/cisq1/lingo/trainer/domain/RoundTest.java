package nl.hu.cisq1.lingo.trainer.domain;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RoundTest {
    @Test
    @DisplayName("Round is won")
    void waitingForRound() {
        Round round = new Round("baard");
        assertEquals(GameStatus.WAITING_FOR_ROUND,  round.guess("baard"));
    }

    @Test
    @DisplayName("Round playing")
    void roundPlaying() {
        Round round = new Round ("baard");
        assertEquals(GameStatus.PLAYING, round.guess("board"));
    }

    @Test
    @DisplayName("Round is lost")
    void eliminated() {
        Round round = new Round("baard");
        round.guess("bergen");
        round.guess("bonje");
        round.guess("barst");
        round.guess("bedde");
        assertEquals(GameStatus.ELIMINATED, round.guess("board"));
    }
}