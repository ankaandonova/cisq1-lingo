package nl.hu.cisq1.lingo.trainer.domain;

import java.util.List;

public class Progress {
   private Long id;
   private GameStatus gameStatus;
   private List<Feedback> attempts;
   private int score;

    public Progress(Long id, GameStatus gameStatus, List<Feedback> attempts, int score) {
        this.id = id;
        this.gameStatus = gameStatus;
        this.attempts = attempts;
        this.score = score;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public List<Feedback> getAttempts() {
        return attempts;
    }

    public int getScore() {
        return score;
    }
}
