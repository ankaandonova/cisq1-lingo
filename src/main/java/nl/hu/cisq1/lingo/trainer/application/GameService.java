package nl.hu.cisq1.lingo.trainer.application;

import nl.hu.cisq1.lingo.trainer.data.SpringGameRepository;
import nl.hu.cisq1.lingo.trainer.domain.Game;
import nl.hu.cisq1.lingo.trainer.domain.Progress;
import nl.hu.cisq1.lingo.words.application.WordService;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    private WordService wordService;
    private SpringGameRepository springGameRepository;

    private Game game;

    public GameService(WordService wordService, SpringGameRepository springGameRepository){
        this.wordService = wordService;
        this.springGameRepository = springGameRepository;
    }

    public Progress startNewGame(){
        String wordToGuess = this.wordService.provideRandomWord(5);
        Game game = new Game();
        game.startNewRound(wordToGuess);

        this.springGameRepository.save(game);
        return game.getProgress();

    }

    public Progress startNewRound(Long id){
        Game game = this.springGameRepository.findById(id).orElseThrow();;

        game.startNewRound(this.wordService.provideRandomWord(6));

        this.springGameRepository.save(game);
        return game.getProgress();
    }
}
