package nl.hu.cisq1.lingo.trainer.presentation;

import javassist.bytecode.ExceptionsAttribute;
import nl.hu.cisq1.lingo.trainer.application.GameService;
import nl.hu.cisq1.lingo.trainer.domain.Progress;

import nl.hu.cisq1.lingo.trainer.domain.exception.InvalideMove;
import org.springframework.http.HttpRange;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/lingo")
public class GameController {

    private GameService gameService;

    public GameController(GameService gameService){
        this.gameService= gameService;
    }

    @PostMapping("/game")
    public Progress startGame() {
        try{
            return this.gameService.startNewGame();
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping("/game/{id}/round")
    public Progress startNewRound(@PathVariable Long id) {
        try{
            return this.gameService.startNewRound(id);
        }catch(InvalideMove e){
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }

    }

}
