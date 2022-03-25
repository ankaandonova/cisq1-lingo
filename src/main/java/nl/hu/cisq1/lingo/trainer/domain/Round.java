package nl.hu.cisq1.lingo.trainer.domain;



import java.util.ArrayList;
import java.util.List;

public class Round {
    private final String wordToGuess;
    private final List<Feedback> attempts;


    public Round(String wordToGuess) {
        this.wordToGuess = wordToGuess;
        this.attempts = new ArrayList<>();
    }

    public int getLengthWordToGuess() {
        return this.wordToGuess.length();
    }

    public int getAttemptsLength(){
        return this.attempts.size();
    }

    public String getHint() {
        String[] lettersToGuess = wordToGuess.split("");
        return lettersToGuess[0] + ".".repeat(lettersToGuess.length - 1);
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