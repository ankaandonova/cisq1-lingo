package nl.hu.cisq1.lingo.trainer.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProgressTest {

    @Test
    @DisplayName("Get progress")
    void GameProgress() {
        String wordToGuess = "baard";
        String attempt = "board";
        Round round = new Round(wordToGuess);
        List<Round> rounds = new ArrayList<>();
        rounds.add(round);
        Game game = new Game(0, rounds, GameStatus.WAITING_FOR_ROUND , 5);

        Progress progress = game.getProgress();
        List<String> expectedHint = List.of("B", ".", ".", ".", ".");
        game.makeGuess(attempt);

        assertEquals(0, progress.getScore());
        assertEquals(GameStatus.WAITING_FOR_ROUND, progress.getGameStatus());
        assertEquals(round.getAttempts(), progress.getAttempts());


    }
}