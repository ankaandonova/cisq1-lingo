package nl.hu.cisq1.lingo.trainer.domain.exception;

public class InvalideMove extends RuntimeException {
    public InvalideMove(){
        super("Invalide move");
    }
}
