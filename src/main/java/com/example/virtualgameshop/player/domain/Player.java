package com.example.virtualgameshop.player.domain;

import com.example.virtualgameshop.RentedGame;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Set;

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
//    Set<RentedGame> rentedGames;


    public Player(String email, String password, BigDecimal credits) {
        this.email = email;
        this.password = password;
        this.credits = credits;
    }
}
