package com.example.virtualgameshop.game.infrastructure;

import com.example.virtualgameshop.game.domain.Game;
import com.example.virtualgameshop.game.domain.GameRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class MemoryGameRepository implements GameRepository {

    private final Map<Long, Game> storage = new HashMap<>();
    private final AtomicLong ID_Value = new AtomicLong(0);


    @Override
    public List<Game> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public Game save(Game game) {
        if(game.getId() != null){
            storage.put(game.getId(),game);
        }else{
            Long nextId= nextId();
            game.setId(nextId);
            storage.put(nextId, game);
        }
        return game;
    }

    private long nextId() {
        return ID_Value.getAndIncrement();
    }

    @Override
    public Optional<Game> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public void removeById(Long id) {
        storage.remove(id);
    }
}
