package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.exception.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    Game game;

    @BeforeEach
    void createGame(){
        game = new Game();
    }

    @Test
    @DisplayName("Start new round")
    void startNewRound() throws InvalidWordLength {
        String wordToGuess = "baard";
        game.startNewRound(wordToGuess);
        assertEquals(GameStatus.WAITING_FOR_ROUND, game.GetGameStatus());
    }

    @Test
    @DisplayName("Incorrect length of the word")
    void invalidWordLength(){
        String wordToGuess = "bard";
        assertThrows(
                InvalidWordLength.class,
                () -> game.startNewRound(wordToGuess)
        );
    }

    @Test
    @DisplayName("Correct length of the word")
    void validWordLength(){
        String wordToGuess = "baard";
        assertDoesNotThrow(
                () -> game.startNewRound(wordToGuess)
        );
    }

    @ParameterizedTest
    @MethodSource("score")
    @DisplayName("Calculate score")
    void correctScore(int attempts, int score) {
        game.startNewRound("baard");
        for (int i = 0; i < attempts - 1; i++) {
            game.makeGuess("board");
        }

        game.makeGuess("baard");
        assertEquals(game.getScore(), score);
    }

    static Stream<Arguments> score() {
        return Stream.of(
                Arguments.of(1, 30),
                Arguments.of(2, 25),
                Arguments.of(3, 20),
                Arguments.of(4, 15),
                Arguments.of(5, 10)
        );
    }
}