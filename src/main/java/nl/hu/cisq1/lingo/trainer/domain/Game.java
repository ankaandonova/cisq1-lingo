package nl.hu.cisq1.lingo.trainer.domain;

import java.util.List;
import java.util.ArrayList;


import nl.hu.cisq1.lingo.trainer.domain.exception.GameIsNotStarted;
import nl.hu.cisq1.lingo.trainer.domain.exception.InvalidWordLength;


public class Game {
    private int score;
    private final List<Round> rounds;
    private int wordToGuessLength;

    public Game() {
        this.score =0;
        this.rounds = new ArrayList<>();
    }

    public int getScore() {
        return score;
    }

    public GameStatus GetGameStatus() {
        return GameStatus.WAITING_FOR_ROUND;
    }

    public String startNewRound(String wordToGuess) throws InvalidWordLength{
        if (wordToGuess.length() != 5) {
            throw new InvalidWordLength();
        }

        Round round = new Round(wordToGuess);
        rounds.add(round);
        provideNextWordToGuess();
        return round.getHint();
    }

    public void makeGuess(String guess) throws GameIsNotStarted {
        Round round = rounds.get(rounds.size() - 1);
        calculateScore();
        round.guess(guess);
    }

    public void provideNextWordToGuess(){
        int nextWordToGuess = rounds.get(rounds.size() - 1).getLengthWordToGuess();
        if (nextWordToGuess == 7) {
            this.wordToGuessLength = 5;
        } else if (nextWordToGuess == 6) {
            this.wordToGuessLength = 7;
        } else {
            this.wordToGuessLength = 6;
        }
    }

    public void calculateScore() {
        Round round = rounds.get(rounds.size() - 1);
        this.score = 5 * (5 - round.getAttemptsLength()) + 5;
    }
}