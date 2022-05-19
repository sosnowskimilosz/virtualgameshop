package com.example.virtualgameshop.player.application;

import com.example.virtualgameshop.player.application.port.PlayerUseCase;
import com.example.virtualgameshop.player.db.PlayerRepository;
import com.example.virtualgameshop.player.domain.Player;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PlayerService implements PlayerUseCase {

    private final PlayerRepository repository;

    @Override
    public List<Player> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Player> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void removeById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Player addNewPlayer(CreatePlayerCommand command) {
        Player newPlayer=command.toPlayer();
        return repository.save(newPlayer);
    }

    @Override
    public UpdatePlayerResponse updatePlayer(UpdatePlayerCommand command) {
        return repository.findById(command.getId())
                .map(player -> {
                Player updatedPlayer = command.updateFields(player);
                repository.save(updatedPlayer);
                return UpdatePlayerResponse.SUCCESS;
                }).orElse(new UpdatePlayerResponse(false, Collections.singletonList("Player not found with following ID: " + command.getId())));
    }
}
