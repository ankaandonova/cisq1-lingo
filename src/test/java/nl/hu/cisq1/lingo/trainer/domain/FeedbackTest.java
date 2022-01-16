package nl.hu.cisq1.lingo.trainer.domain;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static nl.hu.cisq1.lingo.trainer.domain.LetterFeedback.*;
import static org.junit.jupiter.api.Assertions.*;

class FeedbackTest {

    @ParameterizedTest
    @MethodSource("examples")
    @DisplayName("word is guessed if all letters are correct")
    void wordIsGuessed(String wordToGuess, String attempt, List<LetterFeedback> expected)  {
        Feedback feedback = Feedback.wordIsGuessed(wordToGuess, attempt);
        List<LetterFeedback> actual = feedback.getLettersFeedback();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Gives hint")
    void giveHint(){
        List<String> previousHint = List.of(".", ".", ".", "R", "D");
        Feedback feedback = Feedback.wordIsGuessed("BAARD", "WAARD");
        List<String> hint = feedback.giveHint(previousHint);
        List<String> expected = List.of(".", "A", "A", "R", "D");
        assertEquals(expected, hint);

    }


    public static @NotNull Stream<Arguments> examples() {
        return Stream.of(
                Arguments.of("BAARD", "BERGEN", List.of(INVALID, INVALID, INVALID, INVALID, INVALID)),
                Arguments.of("BAARD", "BONJE", List.of(CORRECT, ABSENT, ABSENT, ABSENT, ABSENT)),
                Arguments.of("BAARD", "BARST", List.of(CORRECT, CORRECT, PRESENT, ABSENT, ABSENT)),
                Arguments.of("BAARD", "BEDDE", List.of(CORRECT, ABSENT, PRESENT, ABSENT, ABSENT)),
                Arguments.of("BAARD", "BAARD", List.of(CORRECT, CORRECT, CORRECT, CORRECT, CORRECT))

        );
    }







}