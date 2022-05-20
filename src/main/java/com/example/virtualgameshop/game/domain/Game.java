package com.example.virtualgameshop.game.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Duration;

@Data
@AllArgsConstructor
public class Game {
    Long id;
    String title;
    BigDecimal price;
    Duration availability;
    GameType gameType;
    Long idOfCover;

    public Game(String title, BigDecimal price, Duration availability, GameType gameType) {
        this.title = title;
        this.price = price;
        this.availability = availability;
        this.gameType = gameType;
    }
}
