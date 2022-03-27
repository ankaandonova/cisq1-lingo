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

    @Test
    @DisplayName("Next word to guess is five letters word")
    void FiveLettersWord() {
        String wordToGuess = "baardje";
        Round round = new Round(wordToGuess);
        int actual = round.provideNextWordToGuess();
        int expected = 5;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Next word to guess is six letters word")
    void SixLettersWord() {
        String wordToGuess = "baard";
        Round round = new Round(wordToGuess);
        int actual = round.provideNextWordToGuess();
        int expected = 6;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Next word to guess is seven letters word")
    void SevenLettersWord() {
        String wordToGuess = "borden";
        Round round = new Round(wordToGuess);
        int actual = round.provideNextWordToGuess();
        int expected = 7;
        assertEquals(expected, actual);
    }
}