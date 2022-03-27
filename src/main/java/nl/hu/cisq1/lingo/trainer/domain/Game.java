package nl.hu.cisq1.lingo.trainer.domain;

import java.util.List;
import java.util.ArrayList;


import nl.hu.cisq1.lingo.trainer.domain.exception.InvalidWordLength;

import javax.persistence.*;

import static nl.hu.cisq1.lingo.trainer.domain.GameStatus.WAITING_FOR_ROUND;

@Entity
public class Game {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private int score;

    @OneToMany
    private final List<Round> rounds;

    @Enumerated(EnumType.STRING)
    private GameStatus gameStatus = WAITING_FOR_ROUND;

    private int wordToGuessLength;

    public Game() {
        this.score =0;
        this.rounds = new ArrayList<>();
    }

    public Game( int score, List<Round> rounds, GameStatus gameStatus, int wordToGuessLength) {

        this.score = score;
        this.rounds = rounds;
        this.gameStatus = gameStatus;
        this.wordToGuessLength = wordToGuessLength;
    }

    public Round getRound(){
        return this.rounds.get(this.rounds.size()-1);
    }
    public int getScore() {
        return score;
    }

    public GameStatus GetGameStatus() {
        return WAITING_FOR_ROUND;
    }

    public String startNewRound(String wordToGuess) throws InvalidWordLength{
        if (wordToGuess.length() != 5) {
            throw new InvalidWordLength();
        }

        Round round = new Round(wordToGuess);
        rounds.add(round);
        round.provideNextWordToGuess();
        return round.getHint();
    }

    public void makeGuess(String guess) {
        Round round = rounds.get(rounds.size() - 1);
        calculateScore();
        round.guess(guess);
    }


    public void calculateScore() {
        Round round = rounds.get(rounds.size() - 1);
        this.score = 5 * (5 - round.getAttemptsLength()) + 5;
    }

    public Progress getProgress() {
        Round currentRound = getRound();
        return new Progress(
                id,
                gameStatus,
                currentRound.getAttempts(),
                score
        );
    }


}