package nl.hu.cisq1.lingo.trainer.application;

import nl.hu.cisq1.lingo.trainer.data.SpringGameRepository;
import nl.hu.cisq1.lingo.trainer.domain.Game;
import nl.hu.cisq1.lingo.words.application.WordService;

public class GameService {
    private WordService wordService;
    private SpringGameRepository springGameRepository;

    private Game game;

    public GameService(WordService wordService, SpringGameRepository springGameRepository){
        this.wordService = wordService;
        this.springGameRepository = springGameRepository;
    }
}
