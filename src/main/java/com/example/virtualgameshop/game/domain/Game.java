package com.example.virtualgameshop.game.domain;

import java.math.BigDecimal;
import java.time.Duration;

public class Game {
    Long id;
    String title;
    BigDecimal price;
    Duration availability;
    GameType gameType;
}
