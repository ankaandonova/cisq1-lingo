package nl.hu.cisq1.lingo.trainer.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Round {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String wordToGuess;

    @OneToMany
    private List<Feedback> attempts;


    public Round(String wordToGuess) {
        this.wordToGuess = wordToGuess;
        this.attempts = new ArrayList<>();
    }

    public Round() {}

    public List<Feedback> getAttempts() { return attempts; }
    public int getAttemptsLength(){ return this.attempts.size();}

    public String getHint() {
        String[] lettersToGuess = wordToGuess.split("");
        return lettersToGuess[0] + ".".repeat(lettersToGuess.length - 1);
    }

    public int provideNextWordToGuess(){
        int nextWordToGuess = this.wordToGuess.length();
        if (nextWordToGuess == 7) {
            return 5;
        } else if (nextWordToGuess == 6) {
           return 7;
        } else {
            return 6;
        }
    }


    public GameStatus guess(String guess) {
        Feedback feedback = Feedback.wordIsGuessed(this.wordToGuess, guess);
        attempts.add(feedback);

        if (feedback.isWordGuessed()) {
            return GameStatus.WAITING_FOR_ROUND;
        } else if (attempts.size() < 5) {
            return GameStatus.PLAYING;
        } else {
            return GameStatus.ELIMINATED;
        }
    }
}