package nl.hu.cisq1.lingo.trainer.application;

import nl.hu.cisq1.lingo.trainer.data.SpringGameRepository;
import nl.hu.cisq1.lingo.trainer.domain.Game;
import nl.hu.cisq1.lingo.trainer.domain.GameStatus;
import nl.hu.cisq1.lingo.trainer.domain.Progress;
import nl.hu.cisq1.lingo.trainer.domain.Round;
import nl.hu.cisq1.lingo.words.application.WordService;
import nl.hu.cisq1.lingo.words.domain.Word;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

class GameServiceTest {
    private static GameService gameService;
    private static SpringGameRepository springGameRepository;
    private static WordService wordService;

    @BeforeAll
    static void beforeAll() {
        springGameRepository = mock(SpringGameRepository.class);
        wordService = mock(WordService.class);
        gameService = new GameService(wordService, springGameRepository);
    }

    @Test
    @DisplayName("Start game")
    void startGame() {
        when(wordService.provideRandomWord(5)).thenReturn("BAARD");
        Progress progress = gameService.startNewGame();

        assertThat(progress).isInstanceOf(Progress.class);
    }

    @Test
    @DisplayName("Start new round")
    void startNewRound(){
        Round round = new Round("BAARD");
        List<Round> rounds = new ArrayList<>();
        rounds.add(round);
        Game game = new Game(0,rounds,GameStatus.WAITING_FOR_ROUND,5);
        springGameRepository.save(game);

        when(springGameRepository.findById(game.getProgress().getId()))
                .thenReturn(Optional.of(new Game(0,rounds,GameStatus.WAITING_FOR_ROUND,5)));
        when(wordService.provideRandomWord(game.getRound().provideNextWordToGuess())).thenReturn("BAARD");

        Progress progress = gameService.startNewRound(game.getProgress().getId());
        assertThat(progress).isInstanceOf(Progress.class);
    }



}