package nl.hu.cisq1.lingo.trainer.domain;


import nl.hu.cisq1.lingo.trainer.domain.exception.HintDoesNotMatchException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static nl.hu.cisq1.lingo.trainer.domain.LetterFeedback.*;

public class Feedback {
    private final String attempt;
    private final List<LetterFeedback> letterFeedback;


    public Feedback(String attempt, List<LetterFeedback> letterFeedback) {
        this.letterFeedback = letterFeedback;
        this.attempt = attempt;
    }

    public static Feedback  wordIsGuessed(String wordToGuess, String attempt) {
        if (wordToGuess.length() != attempt.length()) {
            return new Feedback(attempt, Collections.nCopies(wordToGuess.length(), INVALID));
        }

        List<String> letterToGuess = Arrays.asList(wordToGuess.split(""));
        String[] attemptLetters = attempt.split("");
        List<LetterFeedback> marked = new ArrayList<>(Collections.nCopies(wordToGuess.length(), ABSENT));

        for (int i = 0; i < attemptLetters.length; i++) {

            if (letterToGuess.get(i).equals(attemptLetters[i])) {
                marked.set(i, CORRECT);
                letterToGuess.set(i, "");

            }
        }
        for (int i = 0; i < attemptLetters.length; i++) {
            if (marked.get(i) == CORRECT) {
                continue;
            }
            if (letterToGuess.contains(attemptLetters[i])) {
                marked.set(i, PRESENT);
                letterToGuess.set(letterToGuess.indexOf(attemptLetters[i]), " ");
            }
        }
        return new Feedback(attempt, marked);
    }

    public List<LetterFeedback> getLettersFeedback() {
        return this.letterFeedback;
    }

    public List<String> giveHint(List<String> previousHint){
        if(previousHint.size() != getLettersFeedback().size()){
            throw new HintDoesNotMatchException();
        }
        String[] letters = attempt.split("");
        List<String> hint = new ArrayList<>(previousHint);

        for (int i = 0; i < getLettersFeedback().size(); i++){
            if(getLettersFeedback().get(i) == CORRECT){
                hint.set(i, letters[i]);
            }
        }
        return hint;
    }

    public boolean isWordGuessed() {
        return this.letterFeedback.stream().allMatch(CORRECT::equals);
    }
}