package com.example.virtualgameshop.player.db;

import com.example.virtualgameshop.player.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
