package com.example.virtualgameshop.player.domain;

import com.example.virtualgameshop.RentedGame;

import java.math.BigDecimal;
import java.util.Set;

public class Player {
    Long id;
    String email;
    BigDecimal credits;
    Set<RentedGame> rentedGames;
}
