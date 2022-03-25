package nl.hu.cisq1.lingo.trainer.domain.exception;

public class GameIsNotStarted extends RuntimeException {
    public GameIsNotStarted(){
        super("The game is not started yet");
    }
}
