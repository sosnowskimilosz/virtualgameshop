package com.example.virtualgameshop.game.application;

import com.example.virtualgameshop.game.application.port.GameUseCase;
import com.example.virtualgameshop.game.db.GameJpaRepository;
import com.example.virtualgameshop.game.domain.Game;
import com.example.virtualgameshop.game.domain.GameType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GameService implements GameUseCase {

    private final GameJpaRepository gameRepository;

    @Override
    public List<Game> findAll() {
        return gameRepository.findAll();
    }

    @Override
    public Optional<Game> findById(Long id) {
        return gameRepository.findById(id);
    }

    @Override
    public List<Game> findByType(GameType type) {
        return findAll().stream()
                .filter(game -> game.getGameType() == type)
                .collect(Collectors.toList());
    }

    @Override
    public void removeById(Long id) {
        gameRepository.deleteById(id);
    }

    @Override
    public Game addGame(CreateGameCommand command) {
        Game newGame = command.toGame();
        return gameRepository.save(newGame);
    }

    @Override
    public UpdateGameResponse updateGame(UpdateGameCommand command) {
        return gameRepository.findById(command.getId())
                .map(game -> {
                    Game gameToUpdate = command.updateFields(game);
                    gameRepository.save(gameToUpdate);
                    return UpdateGameResponse.SUCCESS;
                }).orElseGet(() -> new UpdateGameResponse(
                        false, Collections.singletonList("Game not found with id: " + command.getId())
                ));
    }
}
