package com.example.virtualgameshop.player.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
public class Player {

    @Id
    @GeneratedValue
    Long id;
    String email;
    String password;
    BigDecimal credits;
    Long idOfAvatar;
//    Set<RentedGame> rentedGames;


    public Player(String email, String password, BigDecimal credits) {
        this.email = email;
        this.password = password;
        this.credits = credits;
    }
}
