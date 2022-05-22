package com.example.virtualgameshop.game.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Duration;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Game {

    @Id
    @GeneratedValue
    Long id;
    String title;
    BigDecimal price;
    Duration availability;
    @Enumerated(EnumType.STRING)
    GameType gameType;
    Long coverId;

    public Game(String title, BigDecimal price, Duration availability, GameType gameType) {
        this.title = title;
        this.price = price;
        this.availability = availability;
        this.gameType = gameType;
    }
}
