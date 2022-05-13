package com.example.virtualgameshop.game.domain;

import java.util.List;
import java.util.Optional;

public interface GameRepository {

    List<Game> findAll();

    Game save(Game game);

    Optional<Game> findById(Long id);

    void removeById(Long id);
}
