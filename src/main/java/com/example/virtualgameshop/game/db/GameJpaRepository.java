package com.example.virtualgameshop.game.db;

import com.example.virtualgameshop.game.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameJpaRepository extends JpaRepository<Game,Long> {
}
