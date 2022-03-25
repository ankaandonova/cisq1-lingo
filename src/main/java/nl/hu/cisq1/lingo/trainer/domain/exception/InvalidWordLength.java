package nl.hu.cisq1.lingo.trainer.domain.exception;

public class InvalidWordLength extends RuntimeException {
    public InvalidWordLength(){
        super("Invalid length of the word to guess");
    }
}
