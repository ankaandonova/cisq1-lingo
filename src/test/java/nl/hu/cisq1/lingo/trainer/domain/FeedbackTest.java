package nl.hu.cisq1.lingo.trainer.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
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

    public static Stream<Arguments> examples() {
        return Stream.of(
                Arguments.of("baard", "bergen", List.of(INVALID, INVALID, INVALID, INVALID, INVALID)),
                Arguments.of("baard", "bonje", List.of(CORRECT, ABSENT, ABSENT, ABSENT, ABSENT)),
                Arguments.of("baard", "barst", List.of(CORRECT, CORRECT, PRESENT, ABSENT, ABSENT)),
                Arguments.of("baard", "bedde", List.of(CORRECT, ABSENT, PRESENT, ABSENT, ABSENT)),
                Arguments.of("baard", "baard", List.of(CORRECT, CORRECT, CORRECT, CORRECT, CORRECT))

        );
    }

    void guessIsInvalid(){

    }

    void guessIsNotInvalid(){

    }




}