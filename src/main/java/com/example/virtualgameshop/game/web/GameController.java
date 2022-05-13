package com.example.virtualgameshop.game.web;

import com.example.virtualgameshop.CreatedURI;
import com.example.virtualgameshop.game.application.port.GameUseCase;
import com.example.virtualgameshop.game.application.port.GameUseCase.CreateGameCommand;
import com.example.virtualgameshop.game.application.port.GameUseCase.UpdateGameCommand;
import com.example.virtualgameshop.game.application.port.GameUseCase.UpdateGameResponse;
import com.example.virtualgameshop.game.domain.Game;
import com.example.virtualgameshop.game.domain.GameType;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.net.URI;
import java.time.Duration;
import java.util.List;

@RequestMapping("/game")
@RestController
@AllArgsConstructor
public class GameController {

    private final GameUseCase service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id){
        service.removeById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> addGame(@Valid @RequestBody RestGameCommand command){
        Game game = service.addGame(command.toCreateCommand());
        return ResponseEntity.created(createdGameUri(game)).build();
    }

    private URI createdGameUri(Game game){
        return new CreatedURI("/"  + game.getId().toString()).uri();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateGame (@PathVariable Long id, @RequestBody RestGameCommand command){
        UpdateGameResponse response =  service.updateGame(command.toUpdateCommand(id));
        if(!response.isSuccess()){
            String message = String.join(", ", response.getErrors());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
        }
    }

    @Data
    private static class RestGameCommand{

        @NotBlank(message = "Title cannot be empty!")
        String title;

        @DecimalMin(message = "Price cannot be less than zero!", value = "0")
        BigDecimal price;

        @NotNull
        Duration availability;

        @NotBlank(message = "Game should have a type")
        GameType gameType;

        CreateGameCommand toCreateCommand(){
            return new CreateGameCommand(title, price, availability,gameType);
        }

        UpdateGameCommand toUpdateCommand(Long id){
            return new UpdateGameCommand(id,title, price, availability,gameType);
        }
    }
}