package com.example.virtualgameshop.game.application.port;

import com.example.virtualgameshop.game.domain.Game;
import com.example.virtualgameshop.game.domain.GameType;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;

public interface GameUseCase {

    List<Game> findAll();

    Optional<Game> findById(Long id);

    List<Game> findByType(GameType type);

    void removeById(Long id);

    Game addGame(CreateGameCommand command);

    UpdateGameResponse updateGame(UpdateGameCommand command);

    @Value
    class CreateGameCommand {
        String title;
        BigDecimal price;
        Duration availability;
        GameType gameType;

        public Game toGame() {
            return new Game(title, price, availability, gameType);
        }
    }

    @Value
    class UpdateGameResponse {
        public static UpdateGameResponse SUCCESS = new UpdateGameResponse(true, emptyList());

        boolean success;
        List<String> errors;
    }

    @Value
    class UpdateGameCommand {
        Long id;
        String title;
        BigDecimal price;
        Duration availability;
        GameType gameType;

        public Game updateFields(Game game){
            if(title != null){
                game.setTitle(title);
            }
            if(price != null){
                game.setPrice(price);
            }
            if(availability != null){
                game.setAvailability(availability);
            }
            if(gameType != null){
                game.setGameType(gameType);
            }
            return game;
        }
    }
}
