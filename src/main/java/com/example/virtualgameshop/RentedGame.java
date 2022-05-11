package com.example.virtualgameshop;

import com.example.virtualgameshop.game.domain.Game;
import com.example.virtualgameshop.player.domain.Player;

import java.time.LocalDateTime;

public class RentedGame {

    Game game;
    Player player;
    LocalDateTime startOfRenting;
    LocalDateTime endOfRenting;
    boolean isAvailable;

}
